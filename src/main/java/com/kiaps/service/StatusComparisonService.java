package com.kiaps.service;

import com.kiaps.form.StatusComparisonSearchForm;

import java.text.ParseException;

/**
 * author         : Jieun Lee
 * date           : 2022/05/24
 * description    : 상태비교 화면 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        Jieun Lee          최초 생성
 */
public interface StatusComparisonService {

    /*
    * 상태비교 화면 검색 처리
    * @param 검색조건
    * @return StatusComparisonSearchForm searchForm
    * */
    StatusComparisonSearchForm searchStatusComparison(String fromDate, String toDate) throws ParseException;
}
