package org.inspection.nationalk1.congressperson.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ElectionNumber {

    @Id
    @Column
    private Long electionNumber;
}
