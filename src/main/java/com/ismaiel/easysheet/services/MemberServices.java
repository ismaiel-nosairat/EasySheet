package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Share;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.exceptions.BadOperationException;
import com.ismaiel.easysheet.repositories.MemberRepo;
import com.ismaiel.easysheet.repositories.SheetRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MemberServices {

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    SheetRepo sheetRepo;

    @Autowired
    Tools tools;

    public ResponseEntity list(Sheet sheet) {
        Sheet original = sheetRepo.findOne(sheet.getId());
        if (original == null) {
            return ResponseEntity.notFound().build();
        } else {
            tools.checkPermission(sheet, original);
            List<Member> findBySheet = memberRepo.findBySheet(sheet);
            return ResponseEntity.ok(findBySheet);

        }
    }

    public ResponseEntity addMember(Member member) {
        Sheet sheet = sheetRepo.findOne(member.getSheet().getId());
        if (sheet == null) {
            return ResponseEntity.notFound().build();
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
            return ResponseEntity.notFound().build();
        } else {
            tools.checkAdminPermission(sheet, member.getSheet());
            member = memberRepo.findOne(member.getId());
            if (member == null) {
                return ResponseEntity.notFound().build();
            }
            List<Share> shares = member.getShares();
            double balance = 0;
            if (shares != null) {
                balance = shares.stream().map((share) -> share.getAmount()).reduce(balance, (accumulator, _item) -> accumulator + _item);
            }
            if (balance != 0) {
                throw new BadOperationException();
            }
            memberRepo.delete(member.getId());
            return ResponseEntity.ok(member);
        }
    }

}
