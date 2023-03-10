package com.kiaps.service;

import com.kiaps.form.ObsVisualizationSearchForm;
import com.kiaps.repository.ObsVisualizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : 이종관측 시각화 화면 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@RequiredArgsConstructor
@Service
public class ObsVisualizationServiceImpl implements ObsVisualizationService {

    private final ObsVisualizationRepository obsRepository;

    @Override
    public final ObsVisualizationSearchForm searchObsVisualization(String datetime) throws ParseException {

        ObsVisualizationSearchForm returnForm = new ObsVisualizationSearchForm();

        returnForm.setSurfaceList(this.obsRepository.findAllSurfaceData(datetime));
        returnForm.setSondeList(this.obsRepository.findAllSondeData(datetime));
        returnForm.setSurfaceList2(this.obsRepository.findSurfaceByDistance(datetime));

        return returnForm;

    }

}
