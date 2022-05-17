package com.kiaps.service;

import com.kiaps.repository.ObsVisualizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
@RequiredArgsConstructor
@Service
public class ObsVisualizationServiceImpl implements ObsVisualizationService {

    private final ObsVisualizationRepository obsRepository;

    @Override
    public final List<String> searchObsVisualization() {

        List<String> returnList = this.obsRepository.findAllSurfaceData();
        return returnList;

    }
}
