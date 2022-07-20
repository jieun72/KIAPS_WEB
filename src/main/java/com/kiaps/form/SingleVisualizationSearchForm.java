package com.kiaps.form;

import com.kiaps.vo.ResponseAmsuaVO;
import com.kiaps.vo.ResponseSondeVO;
import com.kiaps.vo.ResponseStationVO;
import com.kiaps.vo.ResponseSurfaceVO;
import lombok.*;

import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/06/03
 * description    : 단일종 시각화 화면 form
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/03        Jieun Lee          최초 생성
 */
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleVisualizationSearchForm {

    /* 검색조건-날짜 */
    private String searchDate;

    /* 검색조건-타입 */
    private String searchType;

    /* 검색조건-채널(AMSU-A) */
    private String channelType;

    /* 검색조건-stnId(SONDE) */
    private String stnId;

    /* surface 결과리스트 */
    private List<ResponseSurfaceVO> surfaceList;

    /* sonde 결과리스트 */
    private List<ResponseSondeVO> sondeList;

    /* amsua 결과리스트 */
    private List<ResponseAmsuaVO> amsuaList;

    /* station 리스트 */
    private List<ResponseStationVO> stnList;

}
