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

    @Query(value =
            "select " +
            "   s1.lat as surfaceLat, " +
            "   s1.T2m as surfaceTemp " +
            "from surface_grqc s1 " +
            "where s1.T2m  <> -999.99 " +
            "and s1.`datetime` = :datetime",
            nativeQuery = true
    )
    List<ResponseSurfaceVO> findAllSurfaceData(String datetime);

    @Query(value =
            "select " +
            "   s2.lat as sondeLat, " +
            "   s2.lon as sondeLon, " +
            "   s2.T as sondeTemp " +
            "from sonde_grqc s2 " +
            "inner join (" +
            "select s3.nobs as nobs," +
            "       max(s3.Pressure) as pr " +
            "from sonde_grqc s3 " +
            "where s3.T != -999.99 " +
            "group by s3.nobs) b " +
            "on s2.nobs = b.nobs and s2.Pressure = b.pr " +
            "and s2.T != -999.99 " +
            "and s2.`datetime` = :datetime",
            nativeQuery = true
    )
    List<ResponseSondeVO> findAllSondeData(String datetime);

    @Query(value =
            "SELECT " +
            "   s.T2m as surfaceTemp, " +
            "   s.lat as surfaceLat " +
            "FROM surface_from_sonde s " +
            "where s.T2m <> -999.99 and s.`datetime` = :datetime ",
            nativeQuery = true
    )
    List<ResponseSurfaceVO> findSurfaceByDistance(String datetime);
}
