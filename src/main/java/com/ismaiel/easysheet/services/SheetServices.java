package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.GC;
import com.ismaiel.easysheet.controllers.models.Report;
import com.ismaiel.easysheet.controllers.models.SheetItem;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
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
            return ResponseEntity.notFound().build();
        } else if (password.equals(sheet.getAdminPassword()) || password.equals(sheet.getPassword())) {
//            for (Member ob : sheet.getMembers()) {
//                System.out.println(ob.getName());
//            }

            return ResponseEntity.ok(sheet);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    public ResponseEntity newSheet(Sheet sheet) {
        try {
            sheet.setDate(new Date());
            sheet.setMembers(new ArrayList<Member>());
            Sheet save = sheetRepo.save(sheet);
            return ResponseEntity.ok(save);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity delete(long id) {
        try {
            Sheet sh = sheetRepo.findOne(id);
            if (sh == null) {
                return ResponseEntity.notFound().build();
            }
            sheetRepo.delete(id);
            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity clear(long id) {
        try {
            Sheet sh = sheetRepo.findOne(id);
            if (sh == null) {
                return ResponseEntity.notFound().build();
            }

            entryRepo.deleteBySheetId(id);
            memberRepo.deleteBySheetId(id);

            return ResponseEntity.ok(null);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    public ResponseEntity report(Sheet sheet) {
        Sheet sh = sheetRepo.findOne(sheet.getId());
        if (sh == null) {
            return ResponseEntity.notFound().build();
        }
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
        Iterator<Double> itr=resultList.iterator();
        ArrayList<Double> items = new ArrayList<>();
            while(itr.hasNext()) {
                items.add(itr.next());
            }
        report.total=items.get(0);
        return ResponseEntity.ok(report);

    }

}
