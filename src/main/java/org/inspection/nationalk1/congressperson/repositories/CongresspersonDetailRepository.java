package org.inspection.nationalk1.congressperson.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.inspection.nationalk1.congressperson.domains.CongresspersonDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
public interface CongresspersonDetailRepository extends JpaRepository<CongresspersonDetail, Long> {
	
    public CongresspersonDetail findByName(String name);
}
