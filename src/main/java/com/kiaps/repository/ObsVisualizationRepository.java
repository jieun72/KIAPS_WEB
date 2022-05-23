package com.kiaps.repository;

import com.kiaps.embed.*;
import com.kiaps.entity.*;
import com.kiaps.vo.ResponseSondeVO;
import com.kiaps.vo.ResponseSurfaceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : 이종관측 시각화 화면 repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@Repository
public interface ObsVisualizationRepository extends JpaRepository<Surface, SurfaceKey> {

    @Query(
            "select " +
            "   s1.lat as surfaceLat, " +
            "   s1.t2m as surfaceTemp " +
            "from Surface s1 " +
            "where s1.t2m <> -999.99 " +
            "and s1.surfaceKey.datetime = :datetime"
    )
    List<ResponseSurfaceVO> findAllSurfaceData(String datetime);

    @Query(value =
            "select " +
            "   s2.lat as sondeLat, " +
            "   s2.lon as sondeLon, " +
            "   s2.T as sondeTemp " +
            "from sonde s2 " +
            "inner join (" +
            "select s3.nobs as nobs," +
            "       max(s3.Pressure) as pr " +
            "from sonde s3 " +
            "where T != -999.99 " +
            "group by s3.nobs) b " +
            "on s2.nobs = b.nobs and s2.Pressure = b.pr " +
            "and s2.`datetime` = :datetime",
            nativeQuery = true
    )
    List<ResponseSondeVO> findAllSondeData(String datetime);

}
