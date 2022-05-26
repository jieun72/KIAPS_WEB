package com.kiaps.form;

import com.kiaps.vo.ResponseSondeVO;
import com.kiaps.vo.ResponseSurfaceVO;
import lombok.*;

import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/05/24
 * description    : 상태비교 화면 form
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        Jieun Lee          최초 생성
 */
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusComparisonSearchForm {

    /* 검색조건-날짜From */
    private String fromDate;

    /* 검색조건-날짜To */
    private String toDate;

    /* surface 결과리스트 */
    private List<ResponseSurfaceVO> surfaceList;

    /* surface 결과리스트2 */
    private List<ResponseSurfaceVO> surfaceList2;

}
