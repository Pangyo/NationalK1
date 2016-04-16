package org.inspection.nationalk1.congressperson.services;

import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonDetailRepository;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@Service
public class CongresspersonServiceImpl implements CongresspersonService {

    @Autowired private CongresspersonRepository congresspersonRepository;
    @Autowired private CongresspersonDetailRepository congressPersonDetailRepository;


    @Override
    @Transactional
    public Long save(Congressperson congressperson) {

        if(congressperson.getCongresspersonDetail() != null) {
            congressperson.getCongresspersonDetail().setCongressperson(congressperson);
            congressPersonDetailRepository.save(congressperson.getCongresspersonDetail());
        }else {
            congresspersonRepository.save(congressperson);
        }
        return congressperson.getCongresspersonId();
    }


    @Override
    public Congressperson getCongresspersonById(Long congresspersonId) {
        return congresspersonRepository.findOne(congresspersonId);
    }
}
