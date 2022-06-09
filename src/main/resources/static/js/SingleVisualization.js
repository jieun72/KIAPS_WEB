$(document).ready(function(){

    // 화면 초기처리 : 버튼 활성화
    $("#menuTypes").removeClass("active");
    $("#singleVisualization").addClass("active");

    // 화면 초기처리 : 로딩바, StnID드롭다운 숨김
    $('#loading').hide();
    $('#stationList').hide();

    // 라디오버튼 변경 시
    $("input[name='searchType']:radio").change(function () {
        var radioVal = this.value;
        
        if(radioVal == '1') {
            // AMSU-A 선택 시
            $('#channelList').show();
            $('#stationList').hide();
        } else if(radioVal == '2') {
            // SONDE 선택 시
            $('#channelList').hide();
            $('#stationList').show();
        } else {
            // SURFACE 선택 시
            $('#channelList').hide();
            $('#stationList').hide();
        }
    });

    $(document).on('click', '#searchButton', function(){

        // 검색처리
        $.ajax({
            url: "/singleVisualization",
            type: "POST",
            data: $("#searchForm").serialize()

        }).done(function (data, textStatus, jqXHR) {

            var searchType = $(':radio[name="searchType"]:checked').val();

            if(searchType == '1') {
                // AMSU-A
                // 결과 데이터 배열 처리
                const amsuaList = data.amsuaList;

                // 차트 작성
                setChart(amsuaList, 1);

                $('#echart_world').css("border","1px solid #111111");
            } else if(searchType == '2') {
                // SONDE
            } else {
                // SURFACE
                // 결과 데이터 배열 처리
                const surfaceList = data.surfaceList;

                // 차트 작성
                setChart(surfaceList, 2);

                $('#echart_world').css("border","1px solid #111111");
            }

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + jqXHR);
        });
    });
});

// 지도 차트 표시
function setChart(resultList, type) {

    const projection = d3.geoProjection((x, y) => ([x, y / 0.75])).rotate([-180, 0]);

    var max = 0;
    var min = 300;

    if(type == '1') {
        resultList.forEach(function (itemOpt) {
            if (itemOpt.amsuaTemp > max) {
                max = itemOpt.amsuaTemp;
            }
            if (itemOpt.amsuaTemp < min) {
                min = itemOpt.amsuaTemp;
            }
        });
    } else {
        resultList.forEach(function (itemOpt) {
            if (itemOpt.surfaceTemp > max) {
                max = itemOpt.surfaceTemp;
            }
            if (itemOpt.surfaceTemp < min) {
                min = itemOpt.surfaceTemp;
            }
        });
    }

    var mychart = echarts.init($('#echart_world')[0]);

    mychart.setOption({
        backgroundColor: '#fff',
        toolbox: {
            show: true,
            orient: 'horizontal',
            x: 'right',
            y: 'top',
            feature: {
                mark: {
                    show: true
                },
                restore: {
                    show: true,
                    title: "Restore"
                },
                saveAsImage: {
                    show: true,
                    title: "Save Image"
                }
            }
        },
        visualMap: {
            left: 'center',
            min: min,
            max: max,
            inRange: {
                color: [
                    '#313695',
                    '#4575b4',
                    '#74add1',
                    '#abd9e9',
                    '#e0f3f8',
                    '#ffffbf',
                    '#fee090',
                    '#fdae61',
                    '#f46d43',
                    '#d73027',
                    '#a50026'
                ]
            },
            calculable: true,
            textStyle: {
                color: '#000'
            },
            orient: 'horizontal'
        },
        geo: {
            type: 'map',
            projection: {
                project: (point) => projection(point),
                unproject: (point) => projection.invert(point),
                stream: projection.stream
            },
            map: 'world',
            roam: false,            // 줌조작 비활성화
            zoom: 1.2,
            silent: true,           // 지도화면조작 비활성화
            emphasis : {
                disable: true       // 국가 선택 비활성화
            },
            itemStyle: {
                normal: {
                    areaColor: '#fff',
                    borderColor: 'rgba(0,0,0,0.5)'
                }
            }
        },
        series: {
            type: 'scatter',
            coordinateSystem: 'geo',
            symbolSize: 3,
            roam: false,
            mapLocation: {
                y: 60
            },
            data: resultList.map(function (itemOpt) {
                if(type == '1') {
                    return {
                        value: [
                            itemOpt.amsuaLon,
                            itemOpt.amsuaLat,
                            itemOpt.amsuaTemp
                        ]
                    };
                } else {
                    return {
                        value: [
                            itemOpt.surfaceLon,
                            itemOpt.surfaceLat,
                            itemOpt.surfaceTemp
                        ]
                    };
                }
            }),
            progressive: 2000
        }
    });
}