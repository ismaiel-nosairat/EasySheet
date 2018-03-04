package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Share;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.exceptions.BadOperationException;
import com.ismaiel.easysheet.exceptions.NotFoundException;
import com.ismaiel.easysheet.repositories.EntryRepo;
import com.ismaiel.easysheet.repositories.MemberRepo;
import com.ismaiel.easysheet.repositories.ShareRepo;
import com.ismaiel.easysheet.repositories.SheetRepo;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
            throw new NotFoundException("003",null);
        } else {
            tools.checkPermission(sheet, original);
            return ResponseEntity.ok(entryRepo.findBySheet(sheet));
        }
    }
    
    public Entry getEntry(Entry entry) {

        Sheet sheet = sheetRepo.findOne(entry.getSheet().getId());
        if (sheet == null) {
            throw new NotFoundException("003",null);
        } else {
            tools.checkPermission(sheet, entry.getSheet());
            
            Entry out =entryRepo.findOne(entry.getId());
            
            return (out);
        }
    }
    

    public ResponseEntity deleteEntry(Entry entry) {
        Sheet sheet = sheetRepo.findOne(entry.getSheet().getId());
        if (sheet == null) {
            throw new NotFoundException("003",null);
        } else {
            tools.checkAdminPermission(sheet, entry.getSheet());
            entry = entryRepo.findOne(entry.getId());
            if (entry == null) {
                throw new NotFoundException("009",null);
            }
            entryRepo.delete(entry.getId());
            return ResponseEntity.ok(entry);
        }
    }

    public ResponseEntity test() {
        Entry entry = entryRepo.findOne(1L);
        return ResponseEntity.ok(entry);
    }
    
    
    
    public Entry addEntry(Entry entry) {
        if (entry.getShares().size() < 1) {
            throw new BadOperationException("008",null);
        }
        Sheet sheet = sheetRepo.findOne(entry.getSheet().getId());
        if (sheet == null) {
            throw new NotFoundException("003",null);
        } else {
            tools.checkAdminPermission(sheet, entry.getSheet());
            //entry.setDate(new Date());
            tools.checkIntegrity(entry);
            entry.setSheet(sheet);
            entry=entryRepo.save(entry);
            
            for (Share sh : entry.getShares()) {
                Member m = memberRepo.findOne(sh.getMember().getId());
                if (m == null || m.getSheet().getId()!= sheet.getId()) {
                    throw new BadOperationException("010",null);
                }
                shareRepo.save(new Share(m, entry, sh.getAmount()));
            } 
            return entry;
        }
    }
    

}
