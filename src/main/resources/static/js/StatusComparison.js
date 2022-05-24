$(document).ready(function(){

    // 화면 초기처리 : 버튼 활성화
    $("#menuTypes").removeClass("active");
    $("#statusComparison").addClass("active");

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
