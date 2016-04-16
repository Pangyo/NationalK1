package org.inspection.nationalk1.congressperson.domains;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 11..
 */
@Entity
@Table(name = "congressperson_detail")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
@ToString(exclude={"congressperson", "electionNum"})
@EqualsAndHashCode(exclude={"congressperson", "electionNum"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class CongresspersonDetail {
	
	@Id
    @Column // UID
    private Long congresspersonId;

    @MapsId
    @OneToOne
    @NonNull
    @JoinColumn(name = "congressperson_id", updatable = false, insertable = false)
    private Congressperson congressperson;

    @Column
    private String assemEmail;

    @Column
    private String assemHomep;

    @Column
    private String assemTel;

    @Column
    @Temporal(TemporalType.DATE)
    private Date bthDate;

    @ManyToMany
    @JoinTable(name = "congressperson_electionnumber"
                , joinColumns = @JoinColumn(name = "congressperson_id")
                , inverseJoinColumns = @JoinColumn(name = "election_number"))
    private List<ElectionNumber> electionNumberList;
    
    @Column
    private String examCd;

    @Column
    private String hbbyCd;

    @Column
    private String hjNm;

    @Lob
    private String memTitle;

    @Column
    private String secretary;

    @Column
    private String secretary2;

    @Column
    private String shrtNm;

    @Column
    private String staff;

    //API 결과 값을 위한 field
    @Transient
    private String electionNum;
    
    private String name;
    
    public CongresspersonDetail setElectionNum(String value) {
    	electionNumberList = new ArrayList<ElectionNumber>();
    	value = StringUtils.replace(value, "대", "");
    	String[]  electionNumList = StringUtils.split(value, ",");
    	for(String electionNum : electionNumList) {
    		try {
    			electionNumberList.add(new ElectionNumber(Long.parseLong(electionNum)));
    		}catch(NumberFormatException e) {
    			e.printStackTrace();
    		}
    	}
    	return this;
    }
    
}
