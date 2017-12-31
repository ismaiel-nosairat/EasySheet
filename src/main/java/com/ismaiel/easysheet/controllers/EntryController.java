
package com.ismaiel.easysheet.controllers;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.services.EntryServices;
import com.ismaiel.easysheet.services.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/entries")
public class EntryController
{
    @Autowired
    EntryServices entryServices;
    
    @GetMapping("/test")
    public ResponseEntity test(){
        return entryServices.test();
    }
    
    @PostMapping("/list")
    public ResponseEntity listEntries(@RequestBody Sheet sheet)
    {
        return entryServices.list(sheet);
      
    }
    
    @PostMapping("/add")
    public ResponseEntity addEntry(@RequestBody Entry entry)
    {
        return entryServices.addEntry(entry);
    }
    
    @PostMapping("/delete")
    public ResponseEntity deleteEntry(@RequestBody Entry entry)
    {
        return entryServices.deleteEntry(entry);
    }
    
    
}
