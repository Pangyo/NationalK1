package org.inspection.nationalk1.congressperson.services;

import org.inspection.nationalk1.Application;
import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.congressperson.domains.CongresspersonDetail;
import org.inspection.nationalk1.local.enums.OriginCode;
import org.inspection.nationalk1.poly.domains.Poly;
import org.inspection.nationalk1.poly.repositories.PolyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CongresspersonServiceTest {

    @Autowired private CongresspersonService congresspersonService;
    @Autowired private PolyRepository polyRepository;


    @Transactional

    @Test
    public void congresspersonWithoutDetailSaveTest() {
        Poly poly = new Poly(10000L, "새누리당");
        polyRepository.save(poly);

        Congressperson congressperson = new Congressperson().setOriginCode(OriginCode.DAEGU).setPoly(poly);
        Long savedCongresspersonId = congresspersonService.save(congressperson);

        Congressperson selectedCongressperson = congresspersonService.getCongresspersonById(savedCongresspersonId);
        assertEquals(congressperson.hashCode(), selectedCongressperson.hashCode());

    }

    @Transactional
    @Rollback(false)
    @Test
    public void congresspersonWithDetailSaveTest() {
        Poly poly = new Poly(10000L, "새누리당");
        polyRepository.save(poly);

        CongresspersonDetail congresspersonDetail = new CongresspersonDetail().setName("김무성");
        Congressperson congressperson = new Congressperson().setOriginCode(OriginCode.DAEGU).setPoly(poly);
        congressperson.setCongresspersonDetail(congresspersonDetail);

        Long savedCongresspersonId = congresspersonService.save(congressperson);

        Congressperson selectedCongressperson = congresspersonService.getCongresspersonById(savedCongresspersonId);
        assertEquals(congressperson.hashCode(), selectedCongressperson.hashCode());
        assertEquals(congresspersonDetail.hashCode(), selectedCongressperson.getCongresspersonDetail().hashCode());
        congresspersonDetail.setName("정귀영");


    }
}
