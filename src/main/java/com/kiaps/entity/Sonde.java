package com.kiaps.entity;

import com.kiaps.embed.SondeKey;
import com.kiaps.embed.SurfaceKey;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : Sonde 원시테이블 Entity
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@Entity
@Table(name = "sonde")
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sonde {

    /* PK */
    @Type(type = "pg-uuid")
    @EmbeddedId
    private SondeKey sondeKey;

    /* 번호 */
    @Column(name = "nobs", nullable = false, insertable = false, updatable = false)
    private int nobs;

    /* 종류 */
    @Column(name = "type")
    private int type;

    /* 지점번호 */
    @Column(name = "stnID")
    private String stnID;

    /* 위도 */
    @Column(name = "lat")
    private double lat;

    /* 경도 */
    @Column(name = "lon")
    private double lon;

    /* 지점고도(m) */
    @Column(name = "stnHgt")
    private String stnHgt;

    /* 관측 레벨 갯수 */
    @Column(name = "nlev")
    private String nlev;

    /* 관측시간 */
    @Column(name = "ObsTime")
    private String ObsTime;

    /* 레벨 */
    @Column(name = "lev")
    private int lev;

    /* 지상기압(Pa) */
    @Column(name = "Pressure")
    private double pressure;

    /* 관측고도 */
    @Column(name = "Height")
    private double height;

    /* 지오퍼텐셜고도 */
    @Column(name = "GPM")
    private double gpm;

    /* 기온 */
    @Column(name = "T")
    private double temp;

    /* 노점온도 */
    @Column(name = "Td")
    private double td;

    /* 상대습도 */
    @Column(name = "RH")
    private double rh;
        
    /* 풍향(°) */
    @Column(name = "Wd")
    private double wd;

    /* 풍속(m/s) */
    @Column(name = "Ws")
    private double ws;

    /* 바람 U */
    @Column(name = "u")
    private double windU;

    /* 바람 V */
    @Column(name = "v")
    private double windV;

    /* 비습 */
    @Column(name = "q")
    private double q;

    /* vertical sounding significance */
    @Column(name = "vsign")
    private double vsign;

    /* 경도(type:2105) */
    @Column(name = "vlon")
    private double vlon;

    /* 위도(type:2105) */
    @Column(name = "vlat")
    private double vlat;

    /* 관측시간 */
    @Column(name = "`time`")
    private String time;

}
