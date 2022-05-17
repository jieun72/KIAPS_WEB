package com.kiaps.embed;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * author         : Jieun Lee
 * date           : 2022/05/17
 * description    : Sonde 원시테이블 PK
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/05/17        Jieun Lee          최초 생성
 */
@Embeddable
@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SondeKey implements Serializable {

    /* 날짜 */
    @Column(name = "`datetime`", nullable = false)
    private String datetime;

    /* 번호 */
    @Column(name = "seq", nullable = false)
    private int seq;

}
