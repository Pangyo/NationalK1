package org.inspection.nationalk1.congressperson.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
@Data
public class Congressperson {

    @Id @GeneratedValue
    @Column(name = "CONGRESSPERSON_ID")
    private Long id;

//    @OneToOne(mappedBy = "congressperson")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CONGRESSPERSON_ID")
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
