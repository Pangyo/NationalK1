package org.inspection.nationalk1.congressperson.domains;

import lombok.*;

import lombok.experimental.Accessors;
import org.hibernate.annotations.DynamicUpdate;
import org.inspection.nationalk1.converters.OriginCodeConverter;
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
public class Congressperson {

    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    private Long congresspersonId;

    @OneToOne(mappedBy="congressperson")
    private CongresspersonDetail congresspersonDetail;

    @ManyToOne
    @JoinColumn(name = "poly_cd")
    @NonNull
    private Poly poly;

    @Convert(converter = OriginCodeConverter.class)
    @NonNull
    private OriginCode originCode;

    public void setCongresspersonDetail(CongresspersonDetail congresspersonDetail){
        this.congresspersonDetail = congresspersonDetail;
//        congresspersonDetail.setCongressperson(this);
    }


}
