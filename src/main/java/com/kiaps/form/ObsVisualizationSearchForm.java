package com.kiaps.form;

import lombok.*;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : 이종관측 시각화 화면 form
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ObsVisualizationSearchForm {

    /* 검색조건-날짜 */
    private String searchDate;

    /* 검색조건-타입 */
    private String searchType;

}
