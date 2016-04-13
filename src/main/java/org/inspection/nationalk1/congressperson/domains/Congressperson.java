package org.inspection.nationalk1.congressperson.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import org.hibernate.annotations.DynamicUpdate;
import org.inspection.nationalk1.converters.OriginCodeConverter;
import org.inspection.nationalk1.local.enums.OriginCode;
import org.inspection.nationalk1.poly.domains.Poly;

import javax.persistence.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 11..
 */
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@ToString(exclude="congresspersonDetail")
@Data
@DynamicUpdate
public class Congressperson {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "CONGRESSPERSON_ID")
    private Long id;


    @OneToOne(mappedBy="congressperson")
    private CongresspersonDetail congresspersonDetail;

    @ManyToOne
    @JoinColumn(name = "POLY_CD")
    @NonNull
    private Poly poly;

    @Convert(converter = OriginCodeConverter.class)
    @NonNull
    private OriginCode originCode;

    public void setCongresspersonDetail(CongresspersonDetail congresspersonDetail){
        this.congresspersonDetail = congresspersonDetail;
        congresspersonDetail.setCongressperson(this);
    }


}
