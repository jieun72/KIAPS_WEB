package com.kiaps.entity;

import com.kiaps.embed.SurfaceKey;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : Surface 원시테이블 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@Entity
@Table(name = "surface")
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Surface {

    /* PK */
    @Type(type = "pg-uuid")
    @EmbeddedId
    private SurfaceKey surfaceKey;

    /* 시간 */
    @Column(name = "`Date/Time`")
    private String dateTime;

    /* 위도 */
    @Column(name = "lat")
    private double lat;

    /* 경도 */
    @Column(name = "lon")
    private double lon;

    /* 지점번호 */
    @Column(name = "stnID")
    private String stnID;

    /* 관측종류 */
    @Column(name = "stntype")
    private String stnType;

    /* 지점고도(m) */
    @Column(name = "stnHgt")
    private String stnHgt;

    /* 플래그 */
    @Column(name = "Flag")
    private String flag;

    /* 지상기압(Pa) */
    @Column(name = "Pressure")
    private double pressure;

    /* 지점해면기압 */
    @Column(name = "Pmsl")
    private double pmsl;

    /* 지상기온(K) */
    @Column(name = "T2m")
    private double t2m;

    /* 지상노점온도 */
    @Column(name = "Td2m")
    private double td2m;

    /* 상대습도(%) */
    @Column(name = "RH2m")
    private double rh2m;

    /* 풍향(°) */
    @Column(name = "Wd10m")
    private double wd10m;

    /* 풍속(m/s) */
    @Column(name = "Ws10m")
    private double ws10m;

}
