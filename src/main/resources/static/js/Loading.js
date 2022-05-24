//AJAX 통신 시작
$(document).ajaxStart(function(){
    $('#loading').show();
});
//AJAX 통신 종료
$(document).ajaxStop(function(){
    $('#loading').hide();
});