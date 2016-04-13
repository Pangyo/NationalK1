package org.inspection.nationalk1.poly.domains;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Poly {

    @Id
    @Column(name = "POLY_CD")
    private Long polyCd;

    @Column(name = "POLY_NM")
    private String polyNm;

}
