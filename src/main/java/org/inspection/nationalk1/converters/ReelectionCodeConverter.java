package org.inspection.nationalk1.converters;

import org.apache.commons.lang3.StringUtils;
import org.inspection.nationalk1.congressperson.enums.ReelectionCode;

import javax.persistence.AttributeConverter;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 16..
 */
public class ReelectionCodeConverter implements AttributeConverter<ReelectionCode, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ReelectionCode attribute) {
        if(attribute == null)
            return null;
        return attribute.getCode();
    }

    @Override
    public ReelectionCode convertToEntityAttribute(Integer dbData) {
        ReelectionCode[] reelectionCodes = ReelectionCode.values();

        for (ReelectionCode reelectionCode : reelectionCodes) {
            if (Integer.compare(reelectionCode.getCode(), dbData) == 0) {
                return reelectionCode;
            }
        }
        return null;
    }

    public ReelectionCode convertToEntityAttribute(String value) {
        ReelectionCode[] reelectionCodes = ReelectionCode.values();

        for (ReelectionCode reelectionCode : reelectionCodes) {
            if (StringUtils.equals(reelectionCode.getName(), value)) {
                return reelectionCode;
            }
        }
        return null;
    }
}

