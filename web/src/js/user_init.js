var user_name
var user_email
var user_city
var money
!function(){

    $.ajax({
        url:"../user_data?action=get_user_data",
        method:"post",
        success:function(data){
            data=data["data"][0];
            user_name=data["user_name"]
            user_email=data["email"]
            user_city=data["city"]
            money=data["money"]
            $("#user_name").html("用户名:"+data["user_name"]);
            $("#user_email").html("邮箱:<br>"+data["email"]);
            if(data["city"]==""){         $("#user_city").html("城市:"+"成都");}
            else{
                $("#user_city").html("城市:"+data["city"]);
            }


            $("#user_money").html(money)
        }
    })
}()