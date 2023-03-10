$(document).ready(function(){

    // 화면 초기처리 : 버튼 활성화
    $("#menuTypes").removeClass("active");
    $("#statusComparison").addClass("active");

    // 화면 초기처리 : 로딩바 숨김
    $('#loading').hide();

    $(document).on('click', '#searchButton', function(){

        // 검색처리
        $.ajax({
            url: "/statusComparison",
            type: "POST",
            data: $("#searchForm").serialize()

        }).done(function (data, textStatus, jqXHR) {
            // const surfaceList = data.surfaceList;
            // const surfaceList2 = data.surfaceList2;
            //
            // let dateArr = [];
            // let surfaceArr = [];
            // surfaceList.forEach(obj => {
            //     dateArr.push([obj.dateTime])
            //     surfaceArr.push(obj.count);
            // });
            //
            // let surfaceArr2 = [];
            // surfaceList2.forEach(obj => {
            //     surfaceArr2.push(obj.count);
            // });
            const sondeTotalList = data.sondeTotalList;
            const kpopQCList = data. kpopQCList;
            const aiQCList = data.aiQCList;
            const sondeOnlyKpopList = data.sondeOnlyKpopList;

            let sondeTotalArr = [];
            let kpopQCArr = [];
            let aiQCArr = [];
            let sondeOnlyKpopArr = [];

            sondeTotalList.forEach(obj => {
                sondeTotalArr.push([obj.dateTime,obj.count]);
            });

            kpopQCList.forEach(obj => {
                kpopQCArr.push([obj.dateTime,obj.count]);
            });

            aiQCList.forEach(obj => {
                aiQCArr.push([obj.dateTime,obj.count]);
            });

            sondeOnlyKpopList.forEach(obj => {
                sondeOnlyKpopArr.push([obj.dateTime,obj.count]);
            });

            setChart('AI QC vs KPOP QC 자료수 비교', sondeTotalArr, kpopQCArr, aiQCArr, sondeOnlyKpopArr);

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + jqXHR);
        });
    });
});

function setChart(title, sondeTotalArr, kpopQCArr, aiQCArr, sondeOnlyKpopArr) {

    var theme = {
        color: [
            '#26B99A', '#34495E', '#3498DB', '#BDC3C7',
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
            color: ['#1f610a', '#97b58d', '#bfd3b7', '#ffffff']
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

    var echartLine = echarts.init($('#echart_line')[0], theme);

    echartLine.setOption({
        title: {
            text: title
        },
        tooltip: {
            trigger: 'axis',
            textStyle: {
                color: '#FFF'
            }
        },
        legend: {
            x: 220,
            y: 40,
            data: ['일별 전체 관측 자료', '일별 KPOP QC 통과 자료',
                   '일별 AI QC 통과 자료', '일별 AI QC 제외 관측 자료']
        },
        toolbox: {
            show: true,
            feature: {
                magicType: {
                    show: true,
                    title: {
                        line: 'Line',
                        bar: 'Bar',
                    },
                    type: ['line', 'bar']
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
        calculable: true,
        xAxis: [{
            type: 'category',
            name: 'Date/Time',
            boundaryGap: false,
            nameLocation: 'middle',
            nameGap: 25,
        }],
        yAxis: [{
            type: 'value',
            name: 'count',
            nameLocation: 'middle',
            nameGap: 50,
            axisLabel: {
                formatter: '{value}'
            }
        }],
        series: [{
            name: '일별 전체 관측 자료',
            type: 'line',
            smooth: true,
            data: sondeTotalArr
        }, {
            name: '일별 KPOP QC 통과 자료',
            type: 'line',
            smooth: true,
            data: kpopQCArr
        }, {
            name: '일별 AI QC 통과 자료',
            type: 'line',
            smooth: true,
            data: aiQCArr
        }, {
            name: '일별 AI QC 제외 관측 자료',
            type: 'line',
            smooth: true,
            data: sondeOnlyKpopArr
        }]
    });
}