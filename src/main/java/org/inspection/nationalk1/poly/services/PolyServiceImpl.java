package org.inspection.nationalk1.poly.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.type.TypeReference;
import org.inspection.nationalk1.common.domain.PublicApiResponse;
import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.congressperson.domains.CongresspersonDetail;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonRepository;
import org.inspection.nationalk1.poly.domains.Poly;
import org.inspection.nationalk1.poly.repositories.PolyRepository;
import org.inspection.nationalk1.utils.NullSafeUtils;
import org.inspection.nationalk1.utils.RestApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.xml.XmlMapper;

/**
 * @author jgy0726@gmail.com on 
 * 2016. 4. 19.
 */
@Component
public class PolyServiceImpl implements PolyService {

	@Autowired private RestApiUtils restApiUtils;
	@Autowired private PolyRepository polyRepository;
	@Autowired private CongresspersonRepository congresspersonRepository;

	@Override
	@Transactional
	public Long save(Poly poly) {
		polyRepository.save(poly);
		return poly.getPolyCd();
	}

	@Override
	public Poly getPolyById(Long polyCd) {
		return polyRepository.findOne(polyCd);
	}
	
	@Override
	public List<Poly> getAllPoly() {
		return polyRepository.findAll();
	}

	@Transactional
	@Override
	public void updateAllPolyFromPublicDataApi() {
		String xmlData = restApiUtils.getExcute("public.data.curr.poly");
        XmlMapper xmlMapper = new XmlMapper();
        try {
        	PublicApiResponse<Poly> response = xmlMapper.readValue(xmlData, new TypeReference<PublicApiResponse<Poly>>(){});
            if(response.isSuccessful()) {
               List<Poly> polyList = response.getBody().getItems();
        	   for(Poly poly : NullSafeUtils.list(polyList)) {
        		   polyRepository.save(poly);
        	   }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@Transactional
	@Override
	public void updateAllPolyInCongressperson() {
		XmlMapper xmlMapper = new XmlMapper();
		List<Poly> polyList = polyRepository.findAll();
		
		for(Poly poly : polyList) {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("polyCd", Long.toString(poly.getPolyCd()));
			
			String xmlData = restApiUtils.getExcute("public.data.curr.member.by.poly", paramMap);
			try {
				PublicApiResponse<Congressperson> response = xmlMapper.readValue(xmlData, new TypeReference<PublicApiResponse<Congressperson>>(){});
				if(response.isSuccessful()) {
					List<Congressperson> congresspersonList = response.getBody().getItems();
					for(Congressperson congressperson : congresspersonList) {
						Congressperson selectedCongressperson = congresspersonRepository.findBydeptCd(congressperson.getDeptCd());
						if(selectedCongressperson != null) {
							selectedCongressperson.setPoly(poly);
						}
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
