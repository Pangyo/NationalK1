package org.inspection.nationalk1.congressperson.domains;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 11..
 */
@Entity
@Table(name = "congressperson_detail")
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude="congressperson")
public class CongresspersonDetail {
	
	@Id
    @Column
    private Long congresspersonId;

    @MapsId
    @OneToOne
    //congresspersonDetail의 congresspersonId는 congresspersonDetail의  PK이면서 congressperson의 id를 FK로 참조
    @NonNull
    @JoinColumn(name = "congressperson_id", updatable = false, insertable = false)
    private Congressperson congressperson;

    @Column
    @NonNull
    private String name;
    
}
