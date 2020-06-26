//检查完毕后提交表单
function checkCap() {
    var code = $("#capt").val();
    if (code == null || code == "") {
        alert("验证码不能为空！");
        return;
    }
    $.ajax({
        url:"/sign_in/check_capt",
        data:{"captcha": code},
        type:"get",
        success:function (data) {
            var s = ""+data;
            console.info(s);
            if(s == "false"){
                alert("验证码错误！");
            }else{
                $("#fm").submit();
            }
        }
    });
}

function loadXMLDoc() {
    console.info("刷新图片");
    $("#img_code").attr("src","/sign_in/img_code?flag="+Math.random());
    //此处需要加上不同参数，否则无法刷新
}

// actually,这是juqery方式的ajax，之前是原生方式
