package org.inspection.nationalk1.congressperson.servies;

import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@Service
public class CongresspersonService {
    @Autowired
    private CongresspersonRepository congresspersonRepository;


    public void save(Congressperson congressperson){
        congresspersonRepository.save(congressperson);
    }

    public void get(Congressperson congressperson){

    }
}
