package com.kiaps.vo;


public interface ResponseSondeVO {

    /* 위도 */
    Double getSondeLat();

    /* 경도 */
    Double getSondeLon();

    /* 기온 */
    Double getSondeTemp();

    /* 고도 */
    Double getSondePressure();

    /* 날짜시간 */
    String getDateTime();

    /* 자료수 */
    Integer getCount();
}
