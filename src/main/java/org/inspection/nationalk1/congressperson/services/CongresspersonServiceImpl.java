package org.inspection.nationalk1.congressperson.services;

import com.fasterxml.jackson.xml.XmlMapper;
import org.codehaus.jackson.type.TypeReference;
import org.inspection.nationalk1.common.domain.PublicApiResponse;
import org.inspection.nationalk1.congressperson.domains.Congressperson;
import org.inspection.nationalk1.congressperson.domains.CongresspersonDetail;
import org.inspection.nationalk1.congressperson.domains.ElectionNumber;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonDetailRepository;
import org.inspection.nationalk1.congressperson.repositories.CongresspersonRepository;
import org.inspection.nationalk1.congressperson.repositories.ElectionNumberRepository;
import org.inspection.nationalk1.utils.RestApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@Service
public class CongresspersonServiceImpl implements CongresspersonService {

    @Autowired private CongresspersonRepository congresspersonRepository;
    @Autowired private CongresspersonDetailRepository congressPersonDetailRepository;
    @Autowired private ElectionNumberRepository electionNumberRepository;
    @Autowired private RestApiUtils restApiUtils;

    @Override
    @Transactional
    public Long save(Congressperson congressperson) {

        if(congressperson.getCongresspersonDetail() != null) {
            CongresspersonDetail congresspersonDetail = congressperson.getCongresspersonDetail();
            if(congresspersonDetail.getElectionNum() != null
                && !congresspersonDetail.getElectionNum().isEmpty()) {
                for(ElectionNumber electionNumber : congresspersonDetail.getElectionNumberList()) {
                    electionNumberRepository.save(electionNumber);
                }
            }

            congresspersonDetail.setCongressperson(congressperson);
            congressPersonDetailRepository.save(congresspersonDetail);
        }else {
            congresspersonRepository.save(congressperson);
        }
        return congressperson.getCongresspersonId();
    }

    @Override
    public Congressperson getCongresspersonById(Long congresspersonId) {
        return congresspersonRepository.findOne(congresspersonId);
    }

    @Override
    public void updateAllCongresspersonFromPublicDataApi() {
        String xmlData = restApiUtils.getExcute("public.data.curr.member");
        XmlMapper xmlMapper = new XmlMapper();
        try {
            PublicApiResponse<Congressperson> response = xmlMapper.readValue(xmlData, new TypeReference<PublicApiResponse<Congressperson>>(){});
            if(response.isSuccessful()) {
               List<Congressperson> congresspersonList = response.getBody().getItems();
               for(Congressperson congressperson : congresspersonList) {
                   Map<String, String> paramMap = new HashMap<String, String>();
                   paramMap.put("deptcd", congressperson.getDeptCd());
                   String detailXmlData = restApiUtils.getExcute("public.data.detail.member", paramMap);

                   PublicApiResponse<CongresspersonDetail> detailResponse = xmlMapper.readValue(detailXmlData, new TypeReference<PublicApiResponse<CongresspersonDetail>>(){});
                   if(detailResponse.isSuccessful()) {
                       CongresspersonDetail congresspersonDetail = detailResponse.getBody().getItem();
                       congresspersonDetail.setCongressperson(congressperson);
                       congressPersonDetailRepository.save(congresspersonDetail);
                   }else {
                       congresspersonRepository.save(congressperson);
                   }
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
