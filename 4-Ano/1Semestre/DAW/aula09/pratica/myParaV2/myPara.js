$(function(){

    $.get('http://localhost:7709/paras',function(data){
        data.forEach(p => {
            $("#paraList").append("<li>" + p.text + "</li>")
        });
    })

    $("#addPara").click(function(){
        $("#paraList").append("<li>" + $("#paraText").val() + "</li>")
        $.post('http://localhost:7709/paras',$("#paraText").serialize())
        alert('Record inserted: ' + JSON.stringify($("#paraText").serialize()))
        $("#paraText").val("")
    })
})
