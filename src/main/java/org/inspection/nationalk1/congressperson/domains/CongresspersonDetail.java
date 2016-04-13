package org.inspection.nationalk1.congressperson.domains;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 11..
 */
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class CongresspersonDetail {

    @Id
    @Column(name = "CONGRESSPERSON_ID")
    private Long congresspersonId;

    @MapsId
//    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @PrimaryKeyJoinColumn(name = "CONGRESSPERSON_ID", referencedColumnName = "CONGRESSPERSON_ID")
//    @JoinColumn(name = "CONGRESSPERSON_ID")
    @OneToOne(mappedBy = "congresspersonDetail")
    @NonNull
    private Congressperson congressperson;

    @Column
    @NonNull
    private String name;

}
