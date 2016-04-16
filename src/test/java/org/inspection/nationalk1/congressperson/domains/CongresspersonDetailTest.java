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
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;

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

    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private JpaTransactionManager transactionManager;
    
    Poly tempPoly;
    Congressperson tempCongressperson;

    @Before
    public void setUp(){
        tempPoly = new Poly(10000L, "새누리당");
        polyRepository.save(tempPoly);


    }
//    @Test
//    public void test0(){
//        String name = "아무개1";
//
//        tempCongressperson = new Congressperson(tempPoly, OriginCode.BUSAN);
////        congresspersonRepository.save(tempCongressperson);
//
//        CongresspersonDetail congresspersonDetail = new CongresspersonDetail();
//        congresspersonDetail.setName(name);
//        congresspersonDetail.setCongressperson(tempCongressperson);
//
//        congresspersonDetailRepository.save(congresspersonDetail);
//
//        congresspersonDetail.setName("아무개2");
//        congresspersonDetailRepository.save(congresspersonDetail);
//    }
    
    @Test
    public void test1(){
        String name = "김무성";

        tempCongressperson = new Congressperson(tempPoly, OriginCode.BUSAN);
        congresspersonRepository.save(tempCongressperson);

        CongresspersonDetail congresspersonDetail = new CongresspersonDetail();
        congresspersonDetail.setName(name);
        congresspersonDetail.setCongressperson(tempCongressperson);
        congresspersonDetail.setCongresspersonId(tempCongressperson.getCongresspersonId());
        congresspersonDetail = congresspersonDetailRepository.save(congresspersonDetail);

        tempCongressperson.setOriginCode(OriginCode.DAEGU);
        congresspersonRepository.save(tempCongressperson);


        CongresspersonDetail selectedCongresspersonDetail = congresspersonDetailRepository.findByName(name);
        assertNotSame(congresspersonDetail.toString(), selectedCongresspersonDetail.toString());
//        assertEquals(congresspersonDetail.getCongressperson().toString(), selectedCongresspersonDetail.getCongressperson().toString());
//        assertEquals(congresspersonDetail.getCongressperson().toString(), tempCongressperson.toString());
        //selectedCongresspersonDetail과 congresspersonDetail내부의 Poly 객체는 값은 같지만 객체의 hashcode 값은 다르다.


        Congressperson selectedCongressperson = congresspersonRepository.findOne(tempCongressperson.getCongresspersonId());
        assertEquals(tempCongressperson.toString(), selectedCongressperson.toString());
        assertEquals(congresspersonDetail.toString(), selectedCongressperson.getCongresspersonDetail().toString());
        assertEquals(selectedCongresspersonDetail.toString(), selectedCongressperson.getCongresspersonDetail().toString());


        //CongresspsersonDetail 에서 congressperson 수정후
        selectedCongresspersonDetail.getCongressperson().setOriginCode(OriginCode.JEJU);
        //CongresspersonDetail 저장시
        congresspersonDetailRepository.save(selectedCongresspersonDetail);
        CongresspersonDetail selectedCongresspersonDetail1 = congresspersonDetailRepository.findByName(name);
        //저장 실패
        //assertEquals(selectedCongresspersonDetail1.getCongressperson().getOriginCode(), OriginCode.JEJU);

        //Congressperson저장시
        congresspersonRepository.save(selectedCongresspersonDetail.getCongressperson());
        CongresspersonDetail selectedCongresspersonDetail2 = congresspersonDetailRepository.findByName(name);
        //저장 성공
        assertEquals(selectedCongresspersonDetail2.getCongressperson().getOriginCode(), OriginCode.JEJU);

        //Congressperson에서 congresspersonDetail을 수정후
        selectedCongressperson.getCongresspersonDetail().setName("정귀영");
        //Congressperson 저장시
        congresspersonRepository.save(selectedCongressperson);
        Congressperson selectedCongressperson1 = congresspersonRepository.findOne(tempCongressperson.getCongresspersonId());
        //실패
        //assertEquals(selectedCongressperson1.getCongresspersonDetail().getName(), "정귀영");

        //CongresspersonDetail 저장시
        congresspersonDetailRepository.save(selectedCongressperson.getCongresspersonDetail());
        Congressperson selectedCongressperson2 = congresspersonRepository.findOne(tempCongressperson.getCongresspersonId());
        //성공
        assertEquals(selectedCongressperson2.getCongresspersonDetail().getName(), "정귀영");

        //Detail을 저장할시 DetailRepository person을 저장시 personRepository만을 사용 해야 함.
        //여기서 같은 table row에 대한 값인 congresspersonDetail, selectedCongresspersonDetail1, selectedCongresspersonDetail2 객체의 hashcode값은 전부다 다름.
        //전부다 다른 객체에 값만 같음.

        //자동 update 되지 않음??

    }

    @Test(expected=JpaSystemException.class)
    public void saveDetailFailTest() {
    	String name = "정귀영";

        tempCongressperson = new Congressperson(tempPoly, OriginCode.BUSAN);
//        congressperson을 save하지 않음.
//        congresspersonRepository.save(tempCongressperson);

        CongresspersonDetail congresspersonDetail = new CongresspersonDetail();
        congresspersonDetail.setName(name);
        congresspersonDetail.setCongressperson(tempCongressperson);
        congresspersonDetail.setCongresspersonId(tempCongressperson.getCongresspersonId());
        //this line occured org.springframework.orm.jpa.JpaSystemException: ids for this class must be manually assigned before calling save(): org.inspection.nationalk1.congressperson.domains.CongresspersonDetail; nested exception is org.hibernate.id.IdentifierGenerationException: ids for this class must be manually assigned before calling save(): org.inspection.nationalk1.congressperson.domains.CongresspersonDetail
        congresspersonDetailRepository.save(congresspersonDetail);
    }

    @Transactional
    @Test
    public void persistanceContextTest() {
    	String name = "정귀영";

        tempCongressperson = new Congressperson(tempPoly, OriginCode.BUSAN);
        em.persist(tempCongressperson);

        CongresspersonDetail congresspersonDetail = new CongresspersonDetail();
        congresspersonDetail.setName(name);
        congresspersonDetail.setCongressperson(tempCongressperson);
        congresspersonDetail.setCongresspersonId(tempCongressperson.getCongresspersonId());
        em.persist(congresspersonDetail);

        CongresspersonDetail foundedCongresspersonDetail = em.find(CongresspersonDetail.class, congresspersonDetail.getCongresspersonId());
        assertEquals(congresspersonDetail.hashCode(), foundedCongresspersonDetail.hashCode());

        Congressperson foundedCongressperson = em.find(Congressperson.class, tempCongressperson.getCongresspersonId());
        assertEquals(tempCongressperson.hashCode(), foundedCongressperson.hashCode());


        CongresspersonDetail newCongresspersonDetail = new CongresspersonDetail();
        newCongresspersonDetail.setCongressperson(tempCongressperson);
        newCongresspersonDetail.setCongresspersonId(congresspersonDetail.getCongresspersonId());
        newCongresspersonDetail.setName("김무성");
        CongresspersonDetail mergedCongresspersonDetail = em.merge(newCongresspersonDetail);
        assertEquals(congresspersonDetail.hashCode(), mergedCongresspersonDetail.hashCode());
        //congresspersonDetail, foundedCongressperson, mergedCongresspersonDetail는 동일한 객체. 셋중에 하나만 값을 변경하면 같이 변경됨.
        em.flush(); //실제 DB 업데이트.

        em.detach(mergedCongresspersonDetail);
        mergedCongresspersonDetail.setCongresspersonId(3L);
        mergedCongresspersonDetail.setName("정귀영");
        em.merge(mergedCongresspersonDetail);
        em.flush(); //실제 DB 업데이트. 하지만 detach 되었기 때문에 name, id가 업데이트 되지 않음.

        em.persist(mergedCongresspersonDetail);
        em.flush(); //실제 DB 업데이트. 새로운 객체로 인식 새로운 Detail이 db에 insert됨.

        //flush를 수행하면 실제 query를 수행하지만 transaction이 걸려있기 때문에 db를 조회했을때 값이 나오지 않음.
        //commit하는 방법을 찾아봐야함.
    }


}
