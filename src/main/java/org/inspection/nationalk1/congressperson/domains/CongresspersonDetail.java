package org.inspection.nationalk1.congressperson.domains;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import org.hibernate.annotations.Columns;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 11..
 */
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude="congressperson")
public class CongresspersonDetail {
	
	@Id
    @Column(name = "CONGRESSPERSON_ID")
    private Long congresspersonId;
	
    @OneToOne
    //congresspersonDetail의 congresspersonId는 congresspersonDetail의  PK이면서 congressperson의 id를 FK로 참조 
    @PrimaryKeyJoinColumn(name="CONGRESSPERSON_ID", referencedColumnName="CONGRESSPERSON_ID")
    @NonNull
    private Congressperson congressperson;

    @Column
    @NonNull
    private String name;
    
}
