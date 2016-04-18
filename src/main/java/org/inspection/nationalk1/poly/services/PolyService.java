package org.inspection.nationalk1.poly.services;

import java.util.List;

import org.inspection.nationalk1.poly.domains.Poly;
import org.springframework.stereotype.Service;

/**
 * @author jgy0726@gmail.com on 
 * 2016. 4. 19.
 */
@Service
public interface PolyService {

	public Long save(Poly poly);
    public Poly getPolyById(Long polyCd);
    public List<Poly> getAllPolies();
    
    public void updateAllPoliesFromPublicDataApi();
    public void updateAllPoliesInCongressperson();
}
