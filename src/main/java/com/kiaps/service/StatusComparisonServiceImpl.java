package com.kiaps.service;

import com.kiaps.form.StatusComparisonSearchForm;
import com.kiaps.repository.StatusComparisonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * author         : Jieun Lee
 * date           : 2022/05/24
 * description    : 상태비교 화면 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        Jieun Lee          최초 생성
 * 2022/06/23        Jieun Lee          AI QC, KPOP QC 검색기능 구현
 */
@RequiredArgsConstructor
@Service
public class StatusComparisonServiceImpl implements StatusComparisonService {

    private final StatusComparisonRepository stcRepository;

    @Override
    public final StatusComparisonSearchForm searchStatusComparison(String fromDate, String toDate) throws ParseException {

        StatusComparisonSearchForm returnForm = new StatusComparisonSearchForm();

        // 일별 전체 관측자료수
        returnForm.setSondeTotalList(this.stcRepository.findSondeTotalCount(fromDate, toDate));

        // 일별 KPOP QC 통과 자료수
        returnForm.setKpopQCList(this.stcRepository.findKpopQCCount(fromDate, toDate));

        // 일별 AI QC 통과 자료수
        returnForm.setAiQCList(this.stcRepository.findAiQCCount(fromDate, toDate));

        // 일별 AI QC 제외 자료수
        returnForm.setSondeOnlyKpopList(this.stcRepository.findOnlyKpopCount(fromDate, toDate));


        return returnForm;

    }

}
