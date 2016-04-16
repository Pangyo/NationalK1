package org.inspection.nationalk1.congressperson.domains;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;
import org.inspection.nationalk1.congressperson.enums.ReelectionCode;
import org.inspection.nationalk1.converters.OriginCodeConverter;
import org.inspection.nationalk1.converters.ReelectionCodeConverter;
import org.inspection.nationalk1.local.enums.OriginCode;
import org.inspection.nationalk1.poly.domains.Poly;

import javax.persistence.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 11..
 */
@Entity
@Table(name = "congressperson")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude={"congresspersonDetail", "poly"})
@EqualsAndHashCode(exclude={"congresspersonDetail", "poly"})
@Data
@Accessors(chain = true)
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
public class Congressperson {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column // UID
    private Long congresspersonId;

    @Column
    private Long num;

    @Column // 위원회 코드
    private String deptCd;

    @Column // 이름
    private String empNm;

    @Column // 영어 이름
    private String engNm;

    @Column // 사진
    private String jpgLink;

    @Convert(converter =  ReelectionCodeConverter.class)
    @Column(nullable = true) // 재선 - 코드
    private ReelectionCode reelectionCode;


    @OneToOne(mappedBy="congressperson")
    private CongresspersonDetail congresspersonDetail;

    @ManyToOne
    @JoinColumn(name = "poly_cd")
    @NonNull
    // 정당 - 코드
    private Poly poly;

    @Convert(converter = OriginCodeConverter.class)
    @NonNull
    // 지역 - 코드
    private OriginCode originCode;

    //API 결과 값을 위한 field
    @Transient
    private String origNm;
    @Transient
    private String reeleGbnNm;

    public Congressperson setReeleGbnNm(String value) {
        this.reelectionCode = new ReelectionCodeConverter().convertToEntityAttribute(value);
        return this;
    }

    public Congressperson setOrigNm(String value) {
        String localName = StringUtils.split(value, " ")[0];
        this.originCode = new OriginCodeConverter().convertToEntityAttributeByLocalName(localName);
        if(this.originCode == OriginCode.OTHER) {
            System.out.println(localName);
        }
        return this;
    }


}
