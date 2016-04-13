package org.inspection.nationalk1.congressperson.domains;

import org.inspection.nationalk1.Application;
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
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class CongresspersonTest {

    @Autowired
    private CongresspersonRepository congresspersonRepository;

    @Autowired
    private PolyRepository polyRepository;

    Poly tempPoly;
    @Before
    public void setUp(){
        tempPoly = new Poly(10000L, "새누리당");
        polyRepository.save(tempPoly);

    }
    @Test
    public void test1(){
        Congressperson congressperson = new Congressperson(tempPoly, OriginCode.BUSAN);
        congresspersonRepository.save(congressperson);
        assertThat(congressperson.getId(), is(notNullValue()));
    }


}
