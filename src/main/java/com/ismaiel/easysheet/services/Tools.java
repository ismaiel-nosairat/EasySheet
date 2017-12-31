package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.exceptions.DuplicatedException;
import com.ismaiel.easysheet.exceptions.UnAuthorizedException;
import org.springframework.stereotype.Service;

@Service
public class Tools {

    void checkDuplicated(Sheet sheet, Member member) {
        for (Member ob : sheet.getMembers()) {
            if (ob.getName().equalsIgnoreCase(member.getName())) {
                throw new DuplicatedException("002",null);
            }
        }
    }
    
    
    

    @Deprecated
    void chechPermission(Sheet sheet, Member member) {
        if (!sheet.getPassword().equalsIgnoreCase(member.getSheet().getPassword())) {
            throw new UnAuthorizedException("",null);
        }
    }

    void checkPermission(Sheet sheet, Sheet original) {
        if ( sheet.getPassword()!=null  &&   sheet.getPassword().equals(original.getPassword())) {
        } else {
            if (sheet.getViewPassword()!=null  &&  sheet.getViewPassword().equals(original.getViewPassword())) {
            } else {
                throw new UnAuthorizedException("001",null);
            }
        }
    }

    void checkAdminPermission(Sheet sheet, Sheet original) {
        if (sheet.getPassword()!=null  && sheet.getPassword().equals(original.getPassword())) {

        } else {
            throw new UnAuthorizedException("001",null);
        }
    }
    
    String generatePasswor(String adminPassword) {
        return adminPassword.toUpperCase();
               
    }

}
