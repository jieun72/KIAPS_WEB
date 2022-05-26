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
 */
@RequiredArgsConstructor
@Service
public class StatusComparisonServiceImpl implements StatusComparisonService {

    private final StatusComparisonRepository stcRepository;

    @Override
    public final StatusComparisonSearchForm searchStatusComparison(String fromDate, String toDate) throws ParseException {

        StatusComparisonSearchForm returnForm = new StatusComparisonSearchForm();

        returnForm.setSurfaceList(this.stcRepository.findAllCount1(fromDate, toDate));
        returnForm.setSurfaceList2(this.stcRepository.findAllCount2(fromDate, toDate));

        return returnForm;

    }

}
