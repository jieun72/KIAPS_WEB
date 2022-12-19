package com.kiaps.repository;

import com.kiaps.embed.SurfaceKey;
import com.kiaps.entity.Surface;
import com.kiaps.vo.ResponseSondeVO;
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
 * 2022/06/23        Jieun Lee          AI QC, KPOP QC 검색기능 구현
 */
@Repository
public interface StatusComparisonRepository extends JpaRepository<Surface, SurfaceKey> {

    @Query(value=
            "select DATE_FORMAT(ObsTime , '%Y/%m/%d') as dateTime, count(*) as count " +
            "from sonde_innoqc " +
            "where ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findSondeTotalCount(String fromDate, String toDate);

    @Query(value=
            "select DATE_FORMAT(ObsTime , '%Y/%m/%d') as dateTime, count(*) as count " +
            "from sonde_innoqc " +
            "where T_KPOP_QC = 0 and ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findKpopQCCount(String fromDate, String toDate);

    @Query(value=
            "select DATE_FORMAT(ObsTime , '%Y/%m/%d') as dateTime, count(*) as count " +
            "from sonde_innoqc " +
            "where T_AI_QC = 0 and ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findAiQCCount(String fromDate, String toDate);

    @Query(value=
            "select DATE_FORMAT(ObsTime , '%Y/%m/%d') as dateTime, count(*) as count " +
            "from sonde_innoqc " +
            "where T_AI_QC is null and ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findOnlyKpopCount(String fromDate, String toDate);
}
