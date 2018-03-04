package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.GC;
import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Share;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.exceptions.BadOperationException;
import com.ismaiel.easysheet.exceptions.NotFoundException;
import com.ismaiel.easysheet.exceptions.UnAuthorizedException;
import com.ismaiel.easysheet.models.MemberBalance;
import com.ismaiel.easysheet.models.Report;
import com.ismaiel.easysheet.models.RichShare;
import com.ismaiel.easysheet.models.SheetUpdateInfo;
import com.ismaiel.easysheet.repositories.EntryRepo;
import com.ismaiel.easysheet.repositories.MemberRepo;
import com.ismaiel.easysheet.repositories.SheetRepo;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberServices {

     @Autowired
    EntityManager em;
    @Autowired
    MemberRepo memberRepo;

    @Autowired
    SheetRepo sheetRepo;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    Tools tools;

    public ResponseEntity list(Sheet sheet) {
        Sheet original = sheetRepo.findOne(sheet.getId());
        if (original == null) {
            throw new NotFoundException("003", null);
        } else {
            tools.checkPermission(sheet, original);
            List<Member> findBySheet = memberRepo.findBySheet(sheet);
            return ResponseEntity.ok(findBySheet);

        }
    }

    public ResponseEntity addMember(Member member) {
        Sheet sheet = sheetRepo.findOne(member.getSheet().getId());
        if (member.getName() == null || member.getName().length() < 3) {
            throw new BadOperationException("011", null);
        }
        if (sheet == null) {
            throw new NotFoundException("003", null);
        } else {
            tools.checkAdminPermission(sheet, member.getSheet());
            tools.checkDuplicated(sheet, member);
            member.setSheet(sheet);
            memberRepo.save(member);
            return ResponseEntity.ok(member);
        }
    }

    public ResponseEntity deleteMember(Member member) {
        Sheet sheet = sheetRepo.findOne(member.getSheet().getId());
        if (sheet == null) {
            throw new NotFoundException("003", null);
        } else {
            tools.checkAdminPermission(sheet, member.getSheet());
            member = memberRepo.findOne(member.getId());
            if (member == null) {
                throw new NotFoundException("006", null);
            }
            List<Share> shares = member.getShares();
            double balance = 0;
            if (shares != null) {
                balance = shares.stream().map((share) -> share.getAmount()).reduce(balance, (accumulator, _item) -> accumulator + _item);
            }
            if (balance != 0) {
                throw new BadOperationException("007", null);
            }
            memberRepo.delete(member.getId());
            return ResponseEntity.ok(member);
        }
    }

    public ResponseEntity detailsMember(Member member) {
        Sheet sheet = sheetRepo.findOne(member.getSheet().getId());
        if (sheet == null) {
            throw new NotFoundException("003", null);
        } else {
            tools.checkPermission(sheet, member.getSheet());
            member = memberRepo.findOne(member.getId());
            if (member == null) {
                throw new NotFoundException("006", null);
            }
            List<Entry> entries = entryRepo.findByCreditor(member);

            return ResponseEntity.ok(entries);
        }
    }

    public ResponseEntity memberBalance(Member member) {
        Sheet sheet = sheetRepo.findOne(member.getSheet().getId());
        if (sheet == null) {
            throw new NotFoundException("003", null);
        } else {

            member = memberRepo.findOne(member.getId());
            if (member == null) {
                throw new NotFoundException("006", null);
            }
            tools.checkPermission(sheet, member.getSheet());
            
            MemberBalance memberBalance=new MemberBalance();
            
            String x = GC.reportMemberBalance;
            Query q = em.createNativeQuery(x);
            q.setParameter(1, member.getId());
            List<Object[]> list = q.getResultList();
            //BigInteger s=new BigInteger
            for (Object[] ob : list) {
                RichShare rs=new RichShare();
                rs.setEntryId( new BigInteger(ob[0].toString()).longValue());
                rs.setEntryName(ob[1].toString());
                rs.setShareId( new BigInteger(ob[2].toString()).longValue());
                rs.setAmount( (Double) ob[3]);
                memberBalance.getShares().add(rs);
                memberBalance.setBalance(memberBalance.getBalance()+rs.getAmount() );
            }
                
            return ResponseEntity.ok(memberBalance);
        }
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

        //   return ResponseEntity.ok(entries);
       
    }

}
