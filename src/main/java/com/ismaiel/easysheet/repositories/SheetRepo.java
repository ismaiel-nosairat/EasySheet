
package com.ismaiel.easysheet.repositories;

import com.ismaiel.easysheet.entities.Entry;
import com.ismaiel.easysheet.entities.Sheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SheetRepo  extends JpaRepository<Sheet, Long>{
 

 }
