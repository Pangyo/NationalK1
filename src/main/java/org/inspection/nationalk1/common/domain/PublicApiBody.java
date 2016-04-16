package org.inspection.nationalk1.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 17..
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicApiBody<T> {

    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;
    private List<T> items;
    private T item;
}
