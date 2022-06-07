package com.kiaps.service;

import com.kiaps.form.SingleVisualizationSearchForm;

import java.text.ParseException;

/**
 * author         : Jieun Lee
 * date           : 2022/06/03
 * description    : 단일종 시각화 화면 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/03        Jieun Lee          최초 생성
 */
public interface SingleVisualizationService {

    /*
    * 단일종 시각화 화면 검색 처리-Surface
    * @param 검색조건
    * @return SingleVisualizationSearchForm searchForm
    * */
    SingleVisualizationSearchForm searchSurface(String datetime) throws ParseException;

    /*
     * 단일종 시각화 화면 검색 처리-Amsu-A
     * @param 검색조건
     * @return SingleVisualizationSearchForm searchForm
     * */
    SingleVisualizationSearchForm searchAmsua(String datetime, String channelType) throws ParseException;
}
