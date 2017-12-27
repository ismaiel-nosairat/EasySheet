package com.ismaiel.easysheet.repositories;

import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long> {

    public List<Member> findBySheet(Sheet sheet);

    @Transactional
    Long deleteBySheetId(long id);

//       @Query(value = "SELECT * FROM MEMBER WHERE SHEET_ID = ?1", nativeQuery = true)
//       public List<Member> findBySheetId(long id);
//       
//       @Query(value = "DELETE * FROM MEMBER WHERE SHEET_ID = ?1", nativeQuery = true)
//       public void deleteBySheetId(long id);
}
