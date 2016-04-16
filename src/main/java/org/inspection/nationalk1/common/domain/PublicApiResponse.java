package org.inspection.nationalk1.common.domain;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicApiResponse<T> {

    private PublicApiHeader header;
    private PublicApiBody<T> body;

    public boolean isSuccessful() {
        if(header != null) {
            return header.isSuccessful();
        }else {
            return false;
        }
    }
}
