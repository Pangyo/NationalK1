package org.inspection.nationalk1.common.domain;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
@Data
public class PublicApiHeader {

    private final String SUCCESS_CODE = "00";

    private String resultCode;
    private String resultMsg;

    public boolean isSuccessful() {
        return StringUtils.equals(resultCode, SUCCESS_CODE);
    }
}
