package com.kiaps.service;

import com.kiaps.form.SingleVisualizationSearchForm;
import com.kiaps.repository.SingleVisualizationRepository;
import com.kiaps.vo.ResponseAmsuaVO;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.text.ParseException;
import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/06/03
 * description    : 단일종 시각화 화면 service
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/03        Jieun Lee          최초 생성
 */
@RequiredArgsConstructor
@Service
public class SingleVisualizationServiceImpl implements SingleVisualizationService {

    @PersistenceContext
    EntityManager em;

    private final SingleVisualizationRepository singleRepository;

    @Override
    public final SingleVisualizationSearchForm searchSurface(String datetime) throws ParseException {

        SingleVisualizationSearchForm returnForm = new SingleVisualizationSearchForm();

        returnForm.setSurfaceList(this.singleRepository.findAllSurfaceData(datetime));

        return returnForm;

    }

    @Override
    public SingleVisualizationSearchForm searchAmsua(String datetime, String channelType) throws ParseException {

        SingleVisualizationSearchForm returnForm = new SingleVisualizationSearchForm();

        JpaResultMapper jpaResultMapper = new JpaResultMapper();

        Query q = em.createNativeQuery(
                "select lat as amsuaLat, "+
                "lon as amsuaLon, `" + channelType + "` as amsuaTemp " +
                "from amsua " +
                "where `datetime` = :datetime " +
                "and `" + channelType +"` <> -999.99 ")
                .setParameter("datetime", datetime);

        List<ResponseAmsuaVO> amsuaList = jpaResultMapper.list(q, ResponseAmsuaVO.class);

        returnForm.setAmsuaList(amsuaList);

        return returnForm;
    }

}
