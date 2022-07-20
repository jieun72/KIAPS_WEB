package com.kiaps.repository;

import com.kiaps.embed.SurfaceKey;
import com.kiaps.entity.Surface;
import com.kiaps.vo.ResponseAmsuaVO;
import com.kiaps.vo.ResponseSondeVO;
import com.kiaps.vo.ResponseStationVO;
import com.kiaps.vo.ResponseSurfaceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/06/03
 * description    : 단일종 시각화 화면 repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/06/03        Jieun Lee          최초 생성
 */
@Repository
public interface SingleVisualizationRepository extends JpaRepository<Surface, SurfaceKey> {

    static final String OB_COL = "\n(CASE\n"
            + "    WHEN :#{#channelType} = 'ob(01)' THEN `ob(01)`\n"
            + "    WHEN :#{#channelType} = 'ob(02)' THEN `ob(02)`\n"
            + "    WHEN :#{#channelType} = 'ob(03)' THEN `ob(03)`\n"
            + "    WHEN :#{#channelType} = 'ob(04)' THEN `ob(04)`\n"
            + "    WHEN :#{#channelType} = 'ob(05)' THEN `ob(05)`\n"
            + "    WHEN :#{#channelType} = 'ob(06)' THEN `ob(06)`\n"
            + "    WHEN :#{#channelType} = 'ob(07)' THEN `ob(07)`\n"
            + "    WHEN :#{#channelType} = 'ob(08)' THEN `ob(08)`\n"
            + "    WHEN :#{#channelType} = 'ob(09)' THEN `ob(09)`\n"
            + "    WHEN :#{#channelType} = 'ob(10)' THEN `ob(10)`\n"
            + "    WHEN :#{#channelType} = 'ob(11)' THEN `ob(11)`\n"
            + "    WHEN :#{#channelType} = 'ob(12)' THEN `ob(12)`\n"
            + "    WHEN :#{#channelType} = 'ob(13)' THEN `ob(13)`\n"
            + "    WHEN :#{#channelType} = 'ob(14)' THEN `ob(14)`\n"
            + "    WHEN :#{#channelType} = 'ob(15)' THEN `ob(15)`\n"
            + "END)";

    @Query(
            "select " +
            "   s1.lat as surfaceLat, " +
            "   s1.lon as surfaceLon, " +
            "   s1.t2m as surfaceTemp " +
            "from Surface s1 " +
            "where s1.t2m <> -999.99 " +
            "and s1.surfaceKey.datetime = :datetime"
    )
    List<ResponseSurfaceVO> findAllSurfaceData(String datetime);

    @Query(value =
            "select lat, lon, StnID as stnId from station s " +
            "where StnType ='SONDE' " +
            "order by cast(StnID as UNSIGNED) asc ",
            nativeQuery = true
    )
    List<ResponseStationVO> findSondeStationList();

    @Query(value=
            "select T as sondeTemp, Pressure as sondePressure " +
            "from sonde s " +
            "where s.`datetime` = :datetime " +
            "and s.StnID = :stnId " +
            "and T <> -999.99 " +
            "order by Pressure asc " ,
            nativeQuery = true
    )
    List<ResponseSondeVO> findVerticalSondeData(String datetime, String stnId);
}
