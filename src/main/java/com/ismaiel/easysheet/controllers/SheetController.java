package com.ismaiel.easysheet.controllers;


import com.ismaiel.easysheet.models.Login;
import com.ismaiel.easysheet.models.SheetItem;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.models.SheetUpdateInfo;
import com.ismaiel.easysheet.services.SheetServices;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/sheets")
public class SheetController {

    @Autowired
    SheetServices sheetServices;
    
    @Deprecated
    @GetMapping("/getAll")
    public @ResponseBody
    List<Sheet> getAll() {
        return sheetServices.getAll();
    }

    @Deprecated
    @GetMapping(value = "/list")
    public ResponseEntity getAllSheets(@RequestParam("page") int pageNum) {
        return new ResponseEntity(sheetServices.listSheets(pageNum), HttpStatus.OK);
    }

    
    @PostMapping(value = "/signin")
    public ResponseEntity login(@RequestBody Login infoLogin) {
        return sheetServices.login(infoLogin.getId(),infoLogin.getPassword());  
    }
    
   
    @PostMapping(value = "/signup")
    public ResponseEntity create(@RequestBody Sheet sheet) {
        return sheetServices.newSheet(sheet);  
    }
    
    @PostMapping(value = "/delete")
    public ResponseEntity delete(@RequestBody Sheet sheetItem) {
        return sheetServices.delete(sheetItem.getId());  
    }
    
    @PostMapping(value = "/clear")
    public ResponseEntity clear(@RequestBody Sheet sheetItem) {
        return sheetServices.clear(sheetItem.getId());  
    }
    
    
    
    @PostMapping("/report")
    public ResponseEntity<?> getReport(@RequestBody Sheet sheet )
    {
       return  sheetServices.report(sheet);
    }
    
    @PostMapping("/update")
    public ResponseEntity<?> updateSheet(@RequestBody SheetUpdateInfo info )
    {
       return  sheetServices.updateInfo(info);
    }
    
}
