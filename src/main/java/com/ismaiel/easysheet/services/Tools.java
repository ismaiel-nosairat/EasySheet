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
                throw new DuplicatedException();
            }
        }
    }
    
    
    

    @Deprecated
    void chechPermission(Sheet sheet, Member member) {
        if (!sheet.getAdminPassword().equalsIgnoreCase(member.getSheet().getAdminPassword())) {
            throw new UnAuthorizedException();
        }
    }

    void checkPermission(Sheet sheet, Sheet original) {
        if ( sheet.getAdminPassword()!=null  &&   sheet.getAdminPassword().equalsIgnoreCase(original.getAdminPassword())) {
        } else {
            if (sheet.getPassword()!=null  &&  sheet.getPassword().equalsIgnoreCase(original.getPassword())) {
            } else {
                throw new UnAuthorizedException();
            }
        }
    }

    void checkAdminPermission(Sheet sheet, Sheet original) {
        if (sheet.getAdminPassword()!=null  && sheet.getAdminPassword().equalsIgnoreCase(original.getAdminPassword())) {

        } else {
            throw new UnAuthorizedException();
        }
    }

}
