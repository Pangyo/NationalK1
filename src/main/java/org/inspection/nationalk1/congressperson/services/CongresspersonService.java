package org.inspection.nationalk1.congressperson.services;

import java.util.List;

import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.springframework.stereotype.Service;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@Service
public interface CongresspersonService {

    public Long save(Congressperson congressperson);
    public List<Congressperson> getAllCongresspersons();
    public Congressperson getCongresspersonById(Long congresspersonId);
    public Congressperson getCongresspersonByDeptCd(String deptCd);
    public List<Congressperson> getCongresspersonsByPolyCd(Long deptCd);

    public void updateAllCongresspersonFromPublicDataApi();

}
