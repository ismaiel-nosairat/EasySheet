package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.GC;
import com.ismaiel.easysheet.models.Report;
import com.ismaiel.easysheet.models.SheetItem;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.exceptions.BadOperationException;
import com.ismaiel.easysheet.exceptions.InternalServerException;
import com.ismaiel.easysheet.exceptions.NotFoundException;
import com.ismaiel.easysheet.exceptions.UnAuthorizedException;
import com.ismaiel.easysheet.models.SheetUpdateInfo;
import com.ismaiel.easysheet.repositories.EntryRepo;
import com.ismaiel.easysheet.repositories.MemberRepo;
import com.ismaiel.easysheet.repositories.SheetRepo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SheetServices {

    @Autowired
    SheetRepo sheetRepo;
    @Autowired
    MemberRepo memberRepo;
    @Autowired
    EntryRepo entryRepo;

    @Autowired
    Tools tools;

    @Autowired
    EntityManager em;

    public List<Sheet> getAll() {
        return sheetRepo.findAll();
    }

    public List<SheetItem> listSheets(int pageNumber) {

        Query query = em.createQuery("select id,name from Sheet");
        //Query query= em.createQuery("select sh from Sheet sh");
        query.setFirstResult(pageNumber * 10);
        query.setMaxResults(10);
        List<SheetItem> out = new ArrayList<>();
        List<Object[]> list = query.getResultList();
        for (Object[] obj : list) {
            out.add(new SheetItem((Long) obj[0], obj[1].toString()));
        }
        return out;
    }

    public ResponseEntity login(long id, String password) {

        Sheet sheet = sheetRepo.findOne(id);
        if (sheet == null) {
            throw new NotFoundException("003", null);
        }
        
        if (password.equals(sheet.getPassword()) || password.equals(sheet.getViewPassword())) {
            if (password.equals(sheet.getViewPassword()))
                sheet.setPassword("");
            return ResponseEntity.ok(sheet);
        } else {
            throw new UnAuthorizedException("001", null);
        }
    }

    /**
     * Register new Sheet
     *
     * @param Sheet which must has: 1- name mandatory 3- adminPassword mandatory
     *
     * @return when 200: Sheet with extra( id and password ); else code 500 if
     * unknown error; code 400 when mandatory input is missing;
     */
    public ResponseEntity newSheet(Sheet sheet) {
        try {
            if (sheet.getPassword() == null || sheet.getName() == null || sheet.getPassword() == "" || sheet.getName() == "") {
                return ResponseEntity.badRequest().body("004");
            }
            sheet.setDate(new Date());
            sheet.setViewPassword(tools.generatePasswor(sheet.getPassword()));
            sheet.setMembers(new ArrayList<>());
            Sheet save = sheetRepo.save(sheet);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            throw new InternalServerException("005", e);
        }
    }

    public ResponseEntity delete(long id) {
        try {
            Sheet sh = sheetRepo.findOne(id);
            if (sh == null) {
                throw new NotFoundException("003", null);
            }
            sheetRepo.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            throw new InternalServerException("005", e);
        }
    }

    public ResponseEntity clear(long id) {
        try {
            Sheet sh = sheetRepo.findOne(id);
            if (sh == null) {
                throw new NotFoundException("003", null);
            }

            entryRepo.deleteBySheetId(id);
           // memberRepo.deleteBySheetId(id);

            return ResponseEntity.ok(null);
        } catch (Exception e) {
            throw new InternalServerException("005", e);
        }
    }

    public ResponseEntity report(Sheet sheet) {
        Sheet sh = sheetRepo.findOne(sheet.getId());
        if (sh == null) {
            throw new NotFoundException("003", null);
        }
        tools.checkPermission(sh, sheet);
        Report report = new Report();
        String x = GC.reportBalanceQuery;
        Query q = em.createNativeQuery(x);
        q.setParameter(1, sheet.getId());
        List<Object[]> list = q.getResultList();
        
        for (Object[] ob : list) {
            report.balances.add((Double) ob[0]);
            report.members.add(ob[2].toString());
        }
        report.id = sheet.getId();
        Query q2 = em.createNativeQuery(GC.reportTotalQuery);
        q2.setParameter(1, sheet.getId());
        List resultList = q2.getResultList();
        Iterator<Double> itr = resultList.iterator();
        ArrayList<Double> items = new ArrayList<>();
        while (itr.hasNext()) {
            items.add(itr.next());
        }
        if (items.size()>0)
            report.total = items.get(0);
        else
            report.total =0d;
        return ResponseEntity.ok(report);

    }

    public ResponseEntity<?> updateInfo(SheetUpdateInfo info) {
        Sheet original = sheetRepo.findOne(info.getId());
        if (original == null) {
            return ResponseEntity.notFound().build();
        } else {
            // Check permission
            if (!original.getPassword().equals(info.getPassword())) {
                throw new UnAuthorizedException("001", null);
            }
            original.setPassword(info.getNewPassword());
            original.setViewPassword(info.getNewViewPassword());
            sheetRepo.save(original);
            return ResponseEntity.ok(original);
        }
    }

}
