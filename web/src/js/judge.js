! function () {
    $.ajaxSetup( {
        //设置ajax请求结束后的执行动作
        complete : function(XMLHttpRequest, textStatus) {
            // 通过XMLHttpRequest取响应头中信息,判断是否是重定向
            var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");
            if (redirect == "REDIRECT") {
                alert("未登录，请先登录");//提示
               window.top.location.href= XMLHttpRequest.getResponseHeader("PATH");//取出路径，重定向
            }
        }
    });
}();