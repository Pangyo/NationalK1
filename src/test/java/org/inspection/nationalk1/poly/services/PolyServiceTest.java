package org.inspection.nationalk1.poly.services;

import org.inspection.nationalk1.Application;
import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.congressperson.domains.CongresspersonDetail;
import org.inspection.nationalk1.congressperson.domains.ElectionNumber;
import org.inspection.nationalk1.congressperson.services.CongresspersonService;
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

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class PolyServiceTest {

	@Autowired private CongresspersonService congresspersonService;
    @Autowired private PolyService polyService;

    @Transactional
    @Test
    public void congresspersonWithDetailSaveTest() {
    	Long id = 1000L;
        Poly poly = new Poly(id, "새누리당");
        polyService.save(poly);
        Poly selectedPoly = polyService.getPolyById(id);
        assertEquals(poly, selectedPoly);
    }

    @Transactional
    @Rollback(false)
    @Test
    public void getXmlDataFromPublicApiTest() {
        polyService.updateAllPolyFromPublicDataApi();
    }
    
    @Transactional
    @Rollback(false)
    @Test
    public void updateAllPolyInCongresspersonTest() {
    	if(congresspersonService.getAllCongressperson().isEmpty()) {
    		congresspersonService.updateAllCongresspersonFromPublicDataApi();
    	}
    	if(polyService.getAllPoly().isEmpty()) {
    		polyService.updateAllPolyFromPublicDataApi();
    	}
    	polyService.updateAllPolyInCongressperson();
    }
    
    
}
