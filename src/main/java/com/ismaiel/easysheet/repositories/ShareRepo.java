
package com.ismaiel.easysheet.repositories;

import com.ismaiel.easysheet.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShareRepo extends JpaRepository<Share, Long>
 {

 }
