package com.kiaps.vo;

public interface ResponseSurfaceVO {

    /* 위도 */
    Double getSurfaceLat();

    /* 경도 */
    Double getSurfaceLon();

    /* 기온 */
    Double getSurfaceTemp();

    /* 날짜시간 */
    String getDateTime();

    /* 자료수 */
    Integer getCount();
}
