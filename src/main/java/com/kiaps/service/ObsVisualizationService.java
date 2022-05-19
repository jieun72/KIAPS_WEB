package com.kiaps.service;

import com.kiaps.form.ObsVisualizationSearchForm;
import com.kiaps.vo.ResponseSondeVO;

import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : 이종관측 시각화 화면 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
public interface ObsVisualizationService {

    /*
    * 이종관측 시각화 화면 검색 처리
    * @param 검색조건
    * @return ObsVisualizationSearchForm searchForm
    * */
    ObsVisualizationSearchForm searchObsVisualization();
}
