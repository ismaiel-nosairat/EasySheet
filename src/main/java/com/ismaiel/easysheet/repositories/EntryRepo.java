
package com.ismaiel.easysheet.repositories;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Member;
import com.ismaiel.easysheet.entities.Sheet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EntryRepo extends JpaRepository<Entry, Long>{
    public List<Entry> findBySheet(Sheet sheet);
    
//       @Transactional
//       @Query(value = "DELETE * FROM MEMBER WHERE SHEET_ID = ?1", nativeQuery = false)
//       public void deleteBySheetId(long id);
           
        @Transactional
        Long deleteBySheetId(long id);
    
}
