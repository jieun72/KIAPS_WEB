package com.kiaps.repository;

import com.kiaps.embed.SurfaceKey;
import com.kiaps.entity.Surface;
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
        "   s.dateTime " +
        "from Surface s "
    )
    List<String> findAllSurfaceData();
}
