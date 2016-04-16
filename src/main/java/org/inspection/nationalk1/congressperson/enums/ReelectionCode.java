package org.inspection.nationalk1.congressperson.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
@Getter
@AllArgsConstructor
public enum ReelectionCode {

    NEWLY_ELECTED(1, "초선")
    , RE_2_ELECTED(2, "재선")
    , RE_3_ELECTED(3, "3선")
    , RE_4_ELECTED(4, "4선")
    , RE_5_ELECTED(5, "5선")
    , RE_6_ELECTED(6, "6선")
    , RE_7_ELECTED(7, "7선")
    , RE_8_ELECTED(8, "8선")
    , RE_9_ELECTED(9, "9선")
    , RE_10_ELECTED(10, "10선");

    private final Integer code;
    private final String name;

}
