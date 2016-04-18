package org.inspection.nationalk1.congressperson.repositories;

import java.util.List;

import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.poly.domains.Poly;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
public interface CongresspersonRepository extends JpaRepository<Congressperson, Long> {
	
	public Congressperson findByDeptCd(String deptCd);
	public List<Congressperson> findByPoly(Poly poly);
}
