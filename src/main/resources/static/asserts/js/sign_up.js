function checkMACode() {

}

function sendCapt() {
    console.info("使用");
    var pn = $("#phoneNumbers").val();
    if(pn == null || pn == ""){
        var error = "<span id='phoneNumbers-error' style='color: red'>手机号不能为空</span>";
        $("#phoneNumbers-error").remove();
        $("#sign_up")
            .find( "label[for='" + 'phoneNumbers' + "']")
            .append( error );
        return;
    }
    $.ajax({
        url:  "sign_up/send_capt",
        data: {"phoneNumbers": pn},
        type: "get",
        success: function (data) {
            var isSend = ""+data;
            if(isSend == "true"){
                $("#get_capt").text("已发送").attr("disabled",true);
            }else {
                alert("验证码获取失败，请稍后再试");
            }
        }
    });
}

// $.validator.setDefaults({
//     submitHandler: function() {
//         alert("测试");
//         $("#sign_up").submit();
//     }
// });

$().ready(function() {
    $("#sign_up").validate({
        submitHandler:function(form){
            //提交前校验验证码
            var capt =  $("#capt").val();
            if (capt == null){
                return;
            }
            $.ajax({
                url: "sign_up/check_capt",
                data: {"inputCode": capt},
                type: "get",
                success: function(data){
                    var ans = ""+data;
                    console.info(ans);
                    if(ans == "true"){
                        form.submit();
                    } else {
                        alert("验证码错误！");
                    }
                }
            });
        },
        // debug: true,
        errorPlacement: function(error, element) {
            // Append error within linked label
            $( element )
                .closest( "form" )
                .find( "label[for='" + element.attr( "id" ) + "']" )
                .append( error );
        },
        errorElement: "span",
        rules: {
            username: {
                required: true,
                minlength: 1,
                maxlength: 20
            },
            email: {
                email: true
            },
            phoneNumbers: {
                required: true,
                minlength: 11
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 30
            },
            confirm_password: {
                required: true,
                equalTo: "#password"
            },
            capt: "required"
            // agree: "required"
        },
        messages: {
            username: {
                required: "请输入用户名",
                minlength: "用户名至少一个字符"
            },
            phoneNumbers: {
                required: function () {
                    $("#phoneNumbers-error").remove();
                    return "请输入正确号码";
                } ,
                minlength: function () {
                    $("#phoneNumbers-error").remove();
                    return "请输入正确号码";
                }
            },
            password: {
                required: "请输入密码",
                minlength: "密码长度不能小于 5 个字符"
            },
            confirm_password: {
                required: "请确认密码",
                equalTo: "两次密码输入不一致"
            },
            email: "请输入一个正确的邮箱",
            capt: "请输入验证码"
        }

    });
});

