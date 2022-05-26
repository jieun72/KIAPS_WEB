package com.kiaps.repository;

import com.kiaps.embed.SurfaceKey;
import com.kiaps.entity.Surface;
import com.kiaps.vo.ResponseSurfaceVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author         : Jieun Lee
 * date           : 2022/05/24
 * description    : 상태비교 화면 repository
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/24        Jieun Lee          최초 생성
 */
@Repository
public interface StatusComparisonRepository extends JpaRepository<Surface, SurfaceKey> {

    @Query(value=
            "select " +
            "   DATE_FORMAT(`datetime`, '%Y/%m/%d %H') as datetime, count(*) as count " +
            "from surface " +
            "where `datetime` between :fromDate and :toDate " +
            "group by `datetime`",
            nativeQuery = true
    )
    List<ResponseSurfaceVO> findAllCount1(String fromDate, String toDate);

    @Query(value=
            "select " +
            "   DATE_FORMAT(`datetime`, '%Y/%m/%d %H') as datetime, count(*) as count " +
            "from surface_grqc " +
            "where `datetime` between :fromDate and :toDate " +
            "and Flag = 3 " +
            "group by `datetime`",
            nativeQuery = true
    )
    List<ResponseSurfaceVO> findAllCount2(String fromDate, String toDate);
}
