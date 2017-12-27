package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Share;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.exceptions.BadOperationException;
import com.ismaiel.easysheet.repositories.EntryRepo;
import com.ismaiel.easysheet.repositories.MemberRepo;
import com.ismaiel.easysheet.repositories.ShareRepo;
import com.ismaiel.easysheet.repositories.SheetRepo;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EntryServices {

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ShareRepo shareRepo;

    @Autowired
    SheetRepo sheetRepo;

    @Autowired
    MemberRepo memberRepo;

    @Autowired
    Tools tools;

    public ResponseEntity list(Sheet sheet) {
        Sheet original = sheetRepo.findOne(sheet.getId());
        if (original == null) {
            return ResponseEntity.notFound().build();
        } else {
            tools.checkPermission(sheet, original);
            return ResponseEntity.ok(entryRepo.findBySheet(sheet));
        }
    }

    public ResponseEntity addEntry(Entry entry) {
        if (entry.getShares().size() < 1) {
            throw new BadOperationException();
        }
        Sheet sheet = sheetRepo.findOne(entry.getSheet().getId());
        if (sheet == null) {
            return ResponseEntity.notFound().build();
        } else {
            tools.checkAdminPermission(sheet, entry.getSheet());
            entry.setDate(new Date());
            entry.setSheet(sheet);
            entryRepo.save(entry);
            for (Share sh : entry.getShares()) {
                Member m = memberRepo.findOne(sh.getMember().getId());
                if (m == null) {
                    throw new BadOperationException();
                }
                shareRepo.save(new Share(m, entry, sh.getAmount()));
            }
            return ResponseEntity.ok(entry);
        }
    }

    public ResponseEntity deleteEntry(Entry entry) {
        Sheet sheet = sheetRepo.findOne(entry.getSheet().getId());
        if (sheet == null) {
            return ResponseEntity.notFound().build();
        } else {
            tools.checkAdminPermission(sheet, entry.getSheet());
            entry = entryRepo.findOne(entry.getId());
            if (entry == null) {
                return ResponseEntity.notFound().build();
            }
            entryRepo.delete(entry.getId());
            return ResponseEntity.ok(entry);
        }
    }

}
