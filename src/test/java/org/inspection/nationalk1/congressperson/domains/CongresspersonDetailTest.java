package org.inspection.nationalk1.congressperson.domains;

import org.inspection.nationalk1.Application;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonDetailRepository;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonRepository;
import org.inspection.nationalk1.local.enums.OriginCode;
import org.inspection.nationalk1.poly.domains.Poly;
import org.inspection.nationalk1.poly.repositories.PolyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CongresspersonDetailTest {

    @Autowired
    private CongresspersonRepository congresspersonRepository;

    @Autowired
    private CongresspersonDetailRepository congresspersonDetailRepository;

    @Autowired
    private PolyRepository polyRepository;

    Poly tempPoly;
    Congressperson tempCongresspserson;

    @Before
    public void setUp(){
        tempPoly = new Poly(10000L, "새누리당");
        polyRepository.save(tempPoly);


    }
    @Test
    public void test1(){
        String name = "김무성";

        tempCongresspserson = new Congressperson(tempPoly, OriginCode.BUSAN);
//        congresspersonRepository.save(tempCongresspserson);

        CongresspersonDetail congresspersonDetail = new CongresspersonDetail();
        congresspersonDetail.setName("김무성");

        tempCongresspserson.setCongresspersonDetail(congresspersonDetail);


        congresspersonRepository.save(tempCongresspserson);
        congresspersonDetail.setName("박광용");
        congresspersonDetailRepository.save(congresspersonDetail);
//        tempCongresspserson.getCongresspersonDetail().setName("박공용 ");
//        congresspersonRepository.save(tempCongresspserson);
//
//        tempCongresspserson.getCongresspersonDetail().setName("박공용111 ");
//        congresspersonRepository.save(tempCongresspserson);


//        congresspersonDetailRepository.save(congresspersonDetail);

//        congresspersonDetail.setCongressperson(tempCongresspserson);
//        congresspersonDetailRepository.save(congresspersonDetail);

//        tempCongresspserson.setCongresspersonDetail(congresspersonDetail);
//        assertThat(resultCongresspersonDetail, is(notNullValue()));
//
//        assertThat(resultCongresspersonDetail, is(congresspersonDetailRepository.findByName("김무성")));
    }





}
