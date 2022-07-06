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
            "from sonde_kpop_qc " +
            "where ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findSondeTotalCount(String fromDate, String toDate);

    @Query(value=
            "select DATE_FORMAT(ObsTime , '%Y/%m/%d') as dateTime, count(*) as count " +
            "from sonde_kpop_qc " +
            "where KPOP_QC = 'P' and ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findKpopQCCount(String fromDate, String toDate);

    @Query(value=
            "select DATE_FORMAT(ObsTime , '%Y/%m/%d') as dateTime, count(*) as count " +
            "from sonde_ai_qc " +
            "where AI_QC_PASS = 'P' and ObsTime BETWEEN :fromDate and :toDate " +
            "group by DATE_FORMAT(ObsTime , '%Y/%m/%d') " +
            "order by DATE_FORMAT(ObsTime , '%Y/%m/%d') asc",
            nativeQuery = true
    )
    List<ResponseSondeVO> findAiQCCount(String fromDate, String toDate);

    @Query(value=
            "select c.obs as datetime, cnt2-cnt1 as count from ( " +
            "   select DATE_FORMAT(a.ObsTime, '%Y/%m/%d') as obs, count(*) as cnt1 " +
            "   from sonde_ai_qc a " +
            "   where a.ObsTime BETWEEN :fromDate and :toDate " +
            "   group by DATE_FORMAT(a.ObsTime, '%Y/%m/%d')) c, ( " +
            "   select DATE_FORMAT(b.ObsTime, '%Y/%m/%d') as obs, count(*) as cnt2 " +
            "   from sonde_kpop_qc b " +
            "   where b.ObsTime BETWEEN :fromDate and :toDate " +
            "   group by DATE_FORMAT(b.ObsTime, '%Y/%m/%d')) d " +
            "where c.obs = d.obs",
            nativeQuery = true
    )
    List<ResponseSondeVO> findOnlyKpopCount(String fromDate, String toDate);
}
