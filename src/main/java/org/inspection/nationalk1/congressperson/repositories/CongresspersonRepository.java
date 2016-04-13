package org.inspection.nationalk1.congressperson.repositories;

import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
public interface CongresspersonRepository extends JpaRepository<Congressperson, Long> {

}
