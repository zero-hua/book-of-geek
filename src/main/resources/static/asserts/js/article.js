//editormd模块
editormd.markdownToHTML("my-editormd", {
    htmlDecode      : "style,script,iframe",
    emoji           : true,
    taskList        : true,
    tex             : true,  // 默认不解析
    flowChart       : true,  // 默认不解析
    sequenceDiagram : true  // 默认不解析
});


function like() {
    console.info("test"+$("#aId").val());

    var ob = $("#like");

    $.ajax({
        url:"/article/like",
        data:{"aId": $("#aId").val()},
        type:"get",
        success:function (data) {
            var s = data+"";
            if(s == "true") {
               var count = parseInt(ob.val())+1;
               ob.text("赞("+count+")");
            }
        }
    });
}
// function submitComment() {
//     $.ajax({
//         type: "POST",
//         url: "/save_comment",
//         data:$('#commentForm').serialize(),
//         async: false,
//         error: function(request) {
//             alert("Connection error");
//         },
//         success: function(data) {
//             //接收后台返回的结果
//             if(data == true){
//
//             }else {
//
//             }
//         }
//     });
// }


