$(document).ready(function(){

    console.log("ObsVisualization테스트");

    $(document).on('click', '#searchButton', function(){
        
        // 검색처리
        $.ajax({
            url: "/obsVisualization",
            type: "POST",
            data: $("#searchForm").serialize()

        }).done(function (data, textStatus, jqXHR) {
            var result = $(data).find("#resultArea");
            $("#resultArea").html(result);

            console.log("success");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + jqXHR);
        });
    });

});