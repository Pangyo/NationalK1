package org.inspection.nationalk1.congressperson.repositories;

import org.inspection.nationalk1.congressperson.domains.ElectionNumber;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
public interface ElectionNumberRepository extends JpaRepository<ElectionNumber, Long> {

}
