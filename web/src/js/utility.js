
        function setTradeData(code,name){
            $("#code").val(code);
            $("#name_label").html("将要操作的项目为："+name);
        }
        function code2type(code){
            var flag=code.substr(0,2);
            if(flag=="nf"){return 1;}
            else if(flag=="hf"){return 2;}
            else if( flag=="sh" || flag=="sz" ){return 4;}
            else if(flag=="hk"){return 5;}
            else {return 3;}
        }
        function trade(){
            var base="../future_data?url="
            code=$("#code").val();
            volume=$("#volume").val();
            trade_operation=$("#trade_operation").val()=="卖出"?1:0;
            
            $.ajax({
                //注意一下，港股的code
                url:base+encodeURIComponent("http://hq.sinajs.cn/list="+code),
                success:function(data){
                    var now
                    if(code2type(code)==1){
                        now=/".*?,.*?,.*?,.*?,.*?,.*?,.*?,(.*?),/g.exec(data)[1];
                    }
                    else if(code2type(code)==2){
                        now=/"(.*?),.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?[",]/g.exec(data)[1];
                    }
                    else if(code2type(code)==3){
                        now=/".*?,(.*?),.*?,.*?,.*?,.*?,.*?,.*?,.*?,.*?,/g.exec(data)[1];
                    }
                    else if(code2type(code)==4){
                        now=/".*?,.*?,.*?,(.*?),.*?,.*?,.*?,.*?,.*?,.*?,/g.exec(data)[1];
                    }
                    else if(code2type(code)==5){
                        now=/".*?,.*?,.*?,.*?,.*?,.*?,(.*?),.*?,.*?,.*?,/g.exec(data)[1];
                    }
                    $.ajax({
                        url:"../business?action=business",
                        method:"post",
                        data:{"future_name":code,"do_what":trade_operation,"number":volume,"price":now},
                        success:function(){
                            console.log("处理成功")
                            location.reload();
                        }
                    })
                }
            })
        }