$(document).ready(function(){

    console.log("ObsVisualization테스트");

    $(document).on('click', '#searchButton', function(){
        
        // 검색처리
        $.ajax({
            url: "/obsVisualization",
            type: "POST",
            data: $("#obsVisualization_form").serialize()

        }).done(function (data, textStatus, jqXHR) {
            var result = $(data).find("#result").text();

            console.log("success");
        }).fail(function (jqXHR, textStatus, errorThrown) {
            alert(textStatus + jqXHR);
        });
    });

});