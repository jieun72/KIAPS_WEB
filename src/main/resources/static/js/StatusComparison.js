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

            console.log("success");

        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + jqXHR);
        });
    });
});
