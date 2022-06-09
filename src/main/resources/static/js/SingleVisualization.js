$(document).ready(function(){

    // 화면 초기처리 : 버튼 활성화
    $("#menuTypes").removeClass("active");
    $("#singleVisualization").addClass("active");

    // 화면 초기처리 : 로딩바, StnID드롭다운 숨김
    $('#loading').hide();
    $('#stationList').hide();
    $('#zeroCount').hide();

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

                $('#echart_graph').hide();
                $('#zeroCount').hide();
                $('#echart_world').show();

                const amsuaList = data.amsuaList;

                if(amsuaList != null && amsuaList.length > 0) {
                    // 차트 작성
                    setChart(amsuaList, 1);

                    $('#echart_world').css("border","1px solid #111111");
                } else {
                    $('#echart_world').hide();
                    $('#zeroCount').show();
                }

            } else if(searchType == '2') {
                // SONDE

                $('#echart_graph').show();
                $('#zeroCount').hide();

                const sondeList = data.sondeList;

                if(sondeList != null && sondeList.length > 0) {

                    let sondeArr = [];
                    let max = 0;
                    let min = 300;

                    sondeList.forEach(obj => {
                        sondeArr.push([obj.sondeTemp, obj.sondePressure]);
                        if (obj.sondeTemp < min) {
                            min = obj.sondeTemp;
                        }
                        if (obj.sondeTemp > max) {
                            max = obj.sondeTemp;
                        }
                    });

                    // 차트 작성
                    setGraph(sondeArr, Math.floor(min), Math.ceil(max));
                } else {
                    $('#echart_graph').hide();
                    $('#zeroCount').show();
                }

                $('#echart_world').hide();
            } else if(searchType == '3') {
                // SURFACE

                $('#echart_graph').hide();
                $('#zeroCount').hide();
                $('#echart_world').show();

                const surfaceList = data.surfaceList;

                if(surfaceList != null && surfaceList.length > 0) {
                    // 차트 작성
                    setChart(surfaceList, 2);

                    $('#echart_world').css("border","1px solid #111111");
                } else {
                    $('#echart_world').hide();
                    $('#zeroCount').show();
                }

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

// 연직 그래프 표시
function setGraph(resultArr, min, max) {

    var theme = {
        color: [
            '#26B99A', '#34495E', '#BDC3C7', '#3498DB',
            '#9B59B6', '#8abb6f', '#759c6a', '#bfd3b7'
        ],

        title: {
            x: 'center',
            y: 'top',
            itemGap: 20,
            textStyle: {
                fontWeight: 'bolder',
                fontsize: 20
            }
        },

        dataRange: {
            color: ['#1f610a', '#97b58d']
        },

        toolbox: {
            color: ['#408829', '#408829', '#408829', '#408829']
        },

        tooltip: {
            backgroundColor: 'rgba(0,0,0,0.5)',
            axisPointer: {
                type: 'line',
                lineStyle: {
                    color: '#408829',
                    type: 'dashed'
                },
                crossStyle: {
                    color: '#408829'
                },
                shadowStyle: {
                    color: 'rgba(200,200,200,0.3)'
                }
            }
        },

        grid: {
            borderWidth: 0
        },

        categoryAxis: {
            axisLine: {
                lineStyle: {
                    color: 'rgba(0,0,0,0.5)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: ['#eee']
                }
            }
        },

        valueAxis: {
            axisLine: {
                lineStyle: {
                    color: 'rgba(0,0,0,0.5)'
                }
            },
            splitArea: {
                show: true,
                areaStyle: {
                    color: ['rgba(250,250,250,0.1)', 'rgba(200,200,200,0.1)']
                }
            },
            splitLine: {
                lineStyle: {
                    color: ['#eee']
                }
            }
        },
        textStyle: {
            fontFamily: 'Arial, Verdana, sans-serif'
        }
    };

    var mychart = echarts.init($('#echart_graph')[0], theme);

    mychart.setOption({
        tooltip: {
            trigger: 'axis',
            showDelay: 0,
            axisPointer: {
                type: 'cross',
                lineStyle: {
                    type: 'dashed',
                    width: 1
                }
            }
        },
        xAxis: {
            name: 'temp.(K)',
            type: 'value',
            boundaryGap: false,
            nameLocation: 'middle',
            nameGap: 25,
            min: min,
            max: max,
            axisLabel: {
                formatter: '{value}'
            }
        },
        yAxis: {
            name: 'Pressure(Pa)',
            type: 'value',
            nameLocation: 'middle',
            nameGap: 55,
            axisLine: { onZero: false },
            axisLabel: {
                formatter: '{value}'
            },
        },
        series: [
            {
                type: 'line',
                smooth: true,
                tooltip: {
                    trigger: 'item',
                    formatter: function(params) {
                        return ' T : ' + params.value[0] + ' / Presuure : ' + params.value[1] + ' ';
                    },
                    textStyle: {
                        color: '#FFF'
                    }
                },
                data: resultArr
            }
        ]
    });
}