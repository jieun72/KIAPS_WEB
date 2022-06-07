package com.kiaps.vo;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAmsuaVO {

    /* 위도 */
    Double amsuaLat;

    /* 경도 */
    Double amsuaLon;

    /* 기온 */
    Double amsuaTemp;

}
