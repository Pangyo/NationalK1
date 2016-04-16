package org.inspection.nationalk1.local.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Park Kwang Yong(pky1030@gmail.com) on 16. 4. 13..
 */
@Getter
@AllArgsConstructor
public enum OriginCode {

    SEOUL("서울", "021001")
    , BUSAN("부산", "021002")
    , DAEGU("대구", "021003")
    , INCHEON("인천", "021004")
    , GWANGJU("광주", "021005")
    , DAEJEON("대전", "021006")
    , ULSAN("울산", "021007")
    , GYEONGGI_DO("경기", "021008")
    , GANGWON("강원", "021009")
    , CHUNGBUK("충북", "021010")
    , CHUNGNAM("충남", "021011")
    , JEONBUK("전북", "021012")
    , JEONNAM("전남", "021013")
    , KYUNGPOOK("경북", "021014")
    , GYEONGNAM("경남", "021015")
    , JEJU("제주", "021016")
    , OTHER("기타", "021017")
    , SEJONG("세종특별자치시", "021168");

    private String name;
    private String origCd;


}
