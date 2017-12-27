package com.ismaiel.easysheet.services;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Share;
import com.ismaiel.easysheet.entities.Sheet;
import com.ismaiel.easysheet.repositories.EntryRepo;
import com.ismaiel.easysheet.repositories.MemberRepo;
import com.ismaiel.easysheet.repositories.ShareRepo;
import com.ismaiel.easysheet.repositories.SheetRepo;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DbService {

    @Autowired
    SheetRepo sheetRepo;

    @Autowired
    EntryRepo entryRepo;

    @Autowired
    ShareRepo shareRepo;

    @Autowired
    MemberRepo memberRepo;

    
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        Sheet y=new Sheet("my first sheet", new Date(), "root", "pa");
        y =sheetRepo.save(y);
        Member m1 = new Member("Mahmoud", y);
        Member m2 = new Member("Othman", y);
        m1=memberRepo.save(m1);
        m2=memberRepo.save(m2);
        
        Entry e1=new Entry("Banana",1000, new Date(), m1,y);
        e1=entryRepo.save(e1);
        Share sh1=new Share(m1, e1, 500);
        Share sh2=new Share(m2, e1, -500);
        sh1=shareRepo.save(sh1);
        sh2=shareRepo.save(sh2);
        
        
        Entry e2=new Entry("Potatos",2000, new Date(), m2,y);
        e2=entryRepo.save(e2);
        Share sh3=new Share(m1, e2, -1000);
        Share sh4=new Share(m2, e2, 1000);
        sh3=shareRepo.save(sh3);
        sh4=shareRepo.save(sh4);
        
        




//Sheet y1=new Sheet("my second sheet", new Date(), "root", "pa");

        //Sheet x1 =sheetRepo.save(y1);
        
        //x.getMembers().add(m3);
        

//        
//        
//        Sheet x=new Sheet("my first sheet", new Date(), "root", "pa");
//        Sheet s=sheetRepo.save(x);
//        
//        //List<Member> members=new ArrayList<Member>();
//        Member m1=memberRepo.save(new Member("Ismaiel", s));
//        s.addMember(m1);
//        Member m2=new Member("Ibrahem", s);
//        
////        Member m3=new Member("Mahmoud", s);
////        Member m4=new Member("Othman", s);
//        
//        
//     memberRepo.save(m1);
//  memberRepo.save(m2);
////        memberRepo.save(m3);
////        memberRepo.save(m4);
//        
//        
    }
    

}
