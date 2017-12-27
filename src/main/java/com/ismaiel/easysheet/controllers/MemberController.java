
package com.ismaiel.easysheet.controllers;

import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.services.MemberServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController
{
    @Autowired
    MemberServices memberServices;
    
    @PostMapping("/list")
    public ResponseEntity listMembers(@RequestBody Sheet sheet)
    {
        return memberServices.list(sheet);
    }
    
    @PostMapping("/add")
    public ResponseEntity addMember(@RequestBody Member member)
    {
        return memberServices.addMember(member);
    }
    
    @PostMapping("/delete")
    public ResponseEntity deleteMember(@RequestBody Member member)
    {
        return memberServices.deleteMember(member);
    }
}
