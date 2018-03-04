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
        Sheet y=new Sheet("my first sheet", new Date(), "root", "view");
        y =sheetRepo.save(y);
        Member m1 = new Member("Mahmoud", y);
        Member m2 = new Member("Othman", y);
        Member m3 = new Member("Ibrahem", y);
        Member m4 = new Member("Bassam", y);
        m1=memberRepo.save(m1);
        m2=memberRepo.save(m2);
        m3=memberRepo.save(m3);
        m4=memberRepo.save(m4);
        
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
        
        Entry e3=new Entry("Drink",8000, new Date(), m3,y);
        e3=entryRepo.save(e3);
        Share sh5=new Share(m1, e3, -2000);
        Share sh6=new Share(m2, e3, -2000);
        Share sh7=new Share(m3, e3, 6000);
        Share sh8=new Share(m4, e3, -2000);
        sh5=shareRepo.save(sh5);
        sh6=shareRepo.save(sh6);
        sh7=shareRepo.save(sh7);
        sh8=shareRepo.save(sh8);
        
        another();
  
    }
    
    public void another(){
        Sheet y=new Sheet("حسابات شهر واحد", new Date(), "جذر", "عرض");
        y =sheetRepo.save(y);
        Member m1 = new Member("محمود", y);
        Member m2 = new Member("عثمان", y);
        Member m3 = new Member("ابراهيم", y);
        Member m4 = new Member("بسام", y);
        m1=memberRepo.save(m1);
        m2=memberRepo.save(m2);
        m3=memberRepo.save(m3);
        m4=memberRepo.save(m4);
        
        Entry e1=new Entry("موز",1000, new Date(), m1,y);
        e1=entryRepo.save(e1);
        Share sh1=new Share(m1, e1, 500);
        Share sh2=new Share(m2, e1, -500);
        sh1=shareRepo.save(sh1);
        sh2=shareRepo.save(sh2);
        
        
        Entry e2=new Entry("بطاطا",2000, new Date(), m2,y);
        e2=entryRepo.save(e2);
        Share sh3=new Share(m1, e2, -1000);
        Share sh4=new Share(m2, e2, 1000);
        sh3=shareRepo.save(sh3);
        sh4=shareRepo.save(sh4);
        
        Entry e3=new Entry("مشروب",8000, new Date(), m3,y);
        e3=entryRepo.save(e3);
        Share sh5=new Share(m1, e3, -2000);
        Share sh6=new Share(m2, e3, -2000);
        Share sh7=new Share(m3, e3, 6000);
        Share sh8=new Share(m4, e3, -2000);
        sh5=shareRepo.save(sh5);
        sh6=shareRepo.save(sh6);
        sh7=shareRepo.save(sh7);
        sh8=shareRepo.save(sh8);
        
    }
    

}
