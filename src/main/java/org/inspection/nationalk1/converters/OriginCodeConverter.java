package org.inspection.nationalk1.converters;

import org.inspection.nationalk1.local.enums.OriginCode;

import javax.persistence.AttributeConverter;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
public class OriginCodeConverter implements AttributeConverter<OriginCode, String> {

    public String convertToDatabaseColumn(OriginCode originCode) {
        return originCode.getOrigCd();
    }

    public OriginCode convertToEntityAttribute(String dbData) {
        OriginCode[] originCodes = OriginCode.values();
        for (OriginCode originCode: originCodes) {
            if (originCode.getOrigCd().equals(dbData)) {
                return originCode;
            }
        }
        return null;
    }
}
