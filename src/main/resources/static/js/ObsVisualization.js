$(document).ready(function(){

    // 화면 초기처리 : 버튼 활성화
    $("#menuTypes").removeClass("active");
    $("#obsVisualization").addClass("active");

    $(document).on('click', '#searchButton', function(){

        // 검색처리
        $.ajax({
            url: "/obsVisualization",
            type: "POST",
            data: $("#searchForm").serialize()

        }).done(function (data, textStatus, jqXHR) {

            // 결과 데이터 배열 처리
            const sondeList = data.sondeList;
            const surfaceList = data.surfaceList;

            let surfaceArr = [];
            surfaceList.forEach(obj => {
                surfaceArr.push([obj.surfaceLat, obj.surfaceTemp]);
            });

            let sondeArr = [];
            sondeList.forEach(obj => {
                sondeArr.push([obj.sondeLat, obj.sondeTemp]);
            });

            // 차트 작성
            setChart(sondeArr, surfaceArr);

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + jqXHR);
        });
    });
});

function setChart(sondeArr, surfaceArr) {

    var theme = {
        color: [
            '#26B99A', '#34495E', '#BDC3C7', '#3498DB',
            '#9B59B6', '#8abb6f', '#759c6a', '#bfd3b7'
        ],

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
                    color: '#408829'
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

    var echartScatter = echarts.init($('#echart_scatter')[0], theme);

    echartScatter.setOption({
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
        legend: {
            data: ['Sonde', 'Surface']
        },
        toolbox: {
            show: true,
            feature: {
                saveAsImage: {
                    show: true,
                    title: "Save Image"
                }
            }
        },
        xAxis: [{
            type: 'value',
            scale: true,
            axisLabel: {
                formatter: '{value}'
            }
        }],
        yAxis: [{
            type: 'value',
            scale: true,
            axisLabel: {
                formatter: '{value}'
            }
        }],
        series: [{
            name: 'Surface',
            type: 'scatter',
            symbolSize: 7,
            tooltip: {
                trigger: 'item',
                formatter: function(params) {
                    if (params.value.length > 1) {
                        return params.seriesName + ' :<br/>' + ' lat : ' + params.value[0] + ' / temp : ' + params.value[1] + ' ';
                    } else {
                        return params.seriesName + ' :<br/>' + params.name + ' : ' + params.value + ' ';
                    }
                },
                textStyle: {
                    color: '#FFF'
                }
            },
            data : surfaceArr
        }, {
            name: 'Sonde',
            type: 'scatter',
            symbolSize: 7,
            tooltip: {
                trigger: 'item',
                formatter: function(params) {
                    if (params.value.length > 1) {
                        return params.seriesName + ' :<br/>' + ' lat : ' + params.value[0] + ' / temp : ' + params.value[1] + ' ';
                    } else {
                        return params.seriesName + ' :<br/>' + params.name + ' : ' + params.value + ' ';
                    }
                },
                textStyle: {
                    color: '#FFF'
                }
            },
            data: sondeArr
        }]
    });
}