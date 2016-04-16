package org.inspection.nationalk1.poly.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@Data
public class Poly {

    @Id
    @Column
    private Long polyCd;

    @Column
    private String polyNm;

}
