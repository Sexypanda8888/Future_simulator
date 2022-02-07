var echart1;
var echart2;
var map3=[];
var moneydata=[];
var moneycount=[];
var stockdata=[];
var newsdata=[];
var tododate=[];
var todocount=[];
var noticedate=[];
var noticecount=[];
var myChart1 = echarts.init(document.getElementById('echarts_1'));
var myChart2 = echarts.init(document.getElementById('echarts_2'));
var myChartmap = echarts.init(document.getElementById('map'));
var myChart3 = echarts.init(document.getElementById('echarts_3'));
var myChart4 = echarts.init(document.getElementById('echarts_4'));
var myChart5 = echarts.init(document.getElementById('echarts_5'));
var myChart6 = echarts.init(document.getElementById('echarts_6'));


function echarts_1() {
    $.ajax({

        url: '../user_data?action=get_stock_data',
        async: true,
        success: function (data) {
            data=data['data'];
            for(var i in data)
                stockdata.push({value:data[i]["value"],name:data[i]["name"]});
            
    // 基于准备好的dom，初始化echarts实例


    var data = stockdata;

    option = {
        backgroundColor: 'rgba(0,0,0,0)',
        tooltip: {
            trigger: 'item',
            formatter: "{b}: <br/>{c} ({d}%)"
        },
        color: [ '#20b9cf', '#2089cf', '#205bcf'],
        legend: { //图例组件，颜色和名字
            x: '70%',
            y: 'center',
            orient: 'vertical',
            itemGap: 12, //图例每项之间的间隔
            itemWidth: 10,
            itemHeight: 10,
            icon: 'rect',
            data: ['沪A股', '深A股', '港股'],
            textStyle: {
                color: [],
                fontStyle: 'normal',
                fontFamily: '微软雅黑',
                fontSize: 12,
            }
        },
        series: [{
            name: '行业占比',
            type: 'pie',
            clockwise: false, //饼图的扇区是否是顺时针排布
            minAngle: 20, //最小的扇区角度（0 ~ 360）
            center: ['35%', '50%'], //饼图的中心（圆心）坐标
            radius: [40, 60], //饼图的半径
          //  avoidLabelOverlap: true, ////是否启用防止标签重叠
            itemStyle: { //图形样式
                normal: {
                    borderColor: 'transparent',
                    borderWidth: 2,
                },
            },
            label: { //标签的位置
                normal: {
                    show: true,
                    position: 'inside', //标签的位置
                    formatter: "{d}%",
                    textStyle: {
                        color: '#fff',
                    }
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontWeight: 'bold'
                    }
                }
            },
            data: data
        }, {
            name: '',
            type: 'pie',
            clockwise: false,
            silent: true,
            minAngle: 20, //最小的扇区角度（0 ~ 360）
            center: ['35%', '50%'], //饼图的中心（圆心）坐标
            radius: [0, 40], //饼图的半径
            itemStyle: { //图形样式
                normal: {
                    borderColor: '#1e2239',
                    borderWidth: 1.5,
                    opacity: 0.21,
                }
            },
            label: { //标签的位置
                normal: {
                    show: false,
                }
            },
            data: data
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart1.setOption(option);
    window.addEventListener("resize",function(){
        myChart1.resize();
    });
        },
        error: function () {
            setContainer('ERROR!');
        }
    });
}




function echarts_2() {
    $.ajax({

        url: '../user_data?action=get_money',
        async: true,
        success: function (data) {
            data=data['data'];
            for(var i in data)
                moneycount.push(data[i]["count"]);
                    // 基于准备好的dom，初始化echarts实例
    

    option = {
        backgroundColor: 'rgba(0,0,0,0)',
        tooltip: {
            trigger: 'item',
            formatter: "{b}  <br/>{c}"
        },
        legend: {
            x: 'center',
            y: '2%',
            data: ['<5000', '<0', '>0', '>5000'],
            icon: 'circle',
            textStyle: {
                color: '#fff',
            }
        },
        calculable: true,
        series: [{
            name: '持仓',
            type: 'pie',
            //起始角度，支持范围[0, 360]
            startAngle: 0,
            //饼图的半径，数组的第一项是内半径，第二项是外半径
            radius: [51, 80],
            //支持设置成百分比，设置成百分比时第一项是相对于容器宽度，第二项是相对于容器高度
            center: ['50%', '20%'],
            //是否展示成南丁格尔图，通过半径区分数据大小。可选择两种模式：
            // 'radius' 面积展现数据的百分比，半径展现数据的大小。
            //  'area' 所有扇区面积相同，仅通过半径展现数据大小
            roseType: 'area',
            //是否启用防止标签重叠策略，默认开启，圆环图这个例子中需要强制所有标签放在中心位置，可以将该值设为 false。
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: true,
                    formatter: '{c}'
                },
                emphasis: {
                    show: true
                }
            },
            labelLine: {
                normal: {
                    show: true,
                    length2: 1,
                },
                emphasis: {
                    show: true
                }
            },
            data: [{
                value: moneycount[0],
                name: '<5000',
                itemStyle: {
                    normal: {
                        color: '#33b565'
                    }
                }
            },
                {
                    value: moneycount[1],
                    name: '<0',
                    itemStyle: {
                        normal: {
                            color: '#20cc98'
                        }
                    }
                },
                {
                    value: moneycount[2],
                    name: '>=0',
                    itemStyle: {
                        normal: {
                            color: '#2089cf'
                        }
                    }
                },
                {
                    value: moneycount[3],
                    name: '>5000',
                    itemStyle: {
                        normal: {
                            color: '#205bcf'
                        }
                    }
                },
               

                {
                    value: 0,
                    name: "",
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                },
                {
                    value: 0,
                    name: "",
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                },
                {
                    value: 0,
                    name: "",
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                },
                {
                    value: 0,
                    name: "",
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                },
                {
                    value: 0,
                    name: "",
                    label: {
                        show: false
                    },
                    labelLine: {
                        show: false
                    }
                }
            ]
        }]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart2.setOption(option);
    window.addEventListener("resize",function(){
        myChart2.resize();
    });
        },
        error: function () {
            setContainer('ERROR!');
        }
    });

}







function map() {

    $.ajax({

        url: '../user_data?action=get_user_city',
        async: true,
        success: function (data) {
            data=data['data'];
            for(var i in data)
            map3.push({name:data[i]["city"],value:data[i]["count"]});



            var data = map3;
            var geoCoordMap = {
                '海门':[121.15,31.89],
                '鄂尔多斯':[109.781327,39.608266],
                '招远':[120.38,37.35],
                '舟山':[122.207216,29.985295],
                '齐齐哈尔':[123.97,47.33],
                '盐城':[120.13,33.38],
                '赤峰':[118.87,42.28],
                '青岛':[120.33,36.07],
                '乳山':[121.52,36.89],
                '金昌':[102.188043,38.520089],
                '泉州':[118.58,24.93],
                '莱西':[120.53,36.86],
                '日照':[119.46,35.42],
                '胶南':[119.97,35.88],
                '南通':[121.05,32.08],
                '拉萨':[91.11,29.97],
                '云浮':[112.02,22.93],
                '梅州':[116.1,24.55],
                '文登':[122.05,37.2],
                '上海':[121.48,31.22],
                '攀枝花':[101.718637,26.582347],
                '威海':[122.1,37.5],
                '承德':[117.93,40.97],
                '厦门':[118.1,24.46],
                '汕尾':[115.375279,22.786211],
                '潮州':[116.63,23.68],
                '丹东':[124.37,40.13],
                '太仓':[121.1,31.45],
                '曲靖':[103.79,25.51],
                '烟台':[121.39,37.52],
                '福州':[119.3,26.08],
                '瓦房店':[121.979603,39.627114],
                '即墨':[120.45,36.38],
                '抚顺':[123.97,41.97],
                '玉溪':[102.52,24.35],
                '张家口':[114.87,40.82],
                '阳泉':[113.57,37.85],
                '莱州':[119.942327,37.177017],
                '湖州':[120.1,30.86],
                '汕头':[116.69,23.39],
                '昆山':[120.95,31.39],
                '宁波':[121.56,29.86],
                '湛江':[110.359377,21.270708],
                '揭阳':[116.35,23.55],
                '荣成':[122.41,37.16],
                '连云港':[119.16,34.59],
                '葫芦岛':[120.836932,40.711052],
                '常熟':[120.74,31.64],
                '东莞':[113.75,23.04],
                '河源':[114.68,23.73],
                '淮安':[119.15,33.5],
                '泰州':[119.9,32.49],
                '南宁':[108.33,22.84],
                '营口':[122.18,40.65],
                '惠州':[114.4,23.09],
                '江阴':[120.26,31.91],
                '蓬莱':[120.75,37.8],
                '韶关':[113.62,24.84],
                '嘉峪关':[98.289152,39.77313],
                '广州':[113.23,23.16],
                '延安':[109.47,36.6],
                '太原':[112.53,37.87],
                '清远':[113.01,23.7],
                '中山':[113.38,22.52],
                '昆明':[102.73,25.04],
                '寿光':[118.73,36.86],
                '盘锦':[122.070714,41.119997],
                '长治':[113.08,36.18],
                '深圳':[114.07,22.62],
                '珠海':[113.52,22.3],
                '宿迁':[118.3,33.96],
                '咸阳':[108.72,34.36],
                '铜川':[109.11,35.09],
                '平度':[119.97,36.77],
                '佛山':[113.11,23.05],
                '海口':[110.35,20.02],
                '江门':[113.06,22.61],
                '章丘':[117.53,36.72],
                '肇庆':[112.44,23.05],
                '大连':[121.62,38.92],
                '临汾':[111.5,36.08],
                '吴江':[120.63,31.16],
                '石嘴山':[106.39,39.04],
                '沈阳':[123.38,41.8],
                '苏州':[120.62,31.32],
                '茂名':[110.88,21.68],
                '嘉兴':[120.76,30.77],
                '长春':[125.35,43.88],
                '胶州':[120.03336,36.264622],
                '银川':[106.27,38.47],
                '张家港':[120.555821,31.875428],
                '三门峡':[111.19,34.76],
                '锦州':[121.15,41.13],
                '南昌':[115.89,28.68],
                '柳州':[109.4,24.33],
                '三亚':[109.511909,18.252847],
                '自贡':[104.778442,29.33903],
                '吉林':[126.57,43.87],
                '阳江':[111.95,21.85],
                '泸州':[105.39,28.91],
                '西宁':[101.74,36.56],
                '宜宾':[104.56,29.77],
                '呼和浩特':[111.65,40.82],
                '成都':[104.06,30.67],
                '大同':[113.3,40.12],
                '镇江':[119.44,32.2],
                '桂林':[110.28,25.29],
                '张家界':[110.479191,29.117096],
                '宜兴':[119.82,31.36],
                '北海':[109.12,21.49],
                '西安':[108.95,34.27],
                '金坛':[119.56,31.74],
                '东营':[118.49,37.46],
                '牡丹江':[129.58,44.6],
                '遵义':[106.9,27.7],
                '绍兴':[120.58,30.01],
                '扬州':[119.42,32.39],
                '常州':[119.95,31.79],
                '潍坊':[119.1,36.62],
                '重庆':[106.54,29.59],
                '台州':[121.420757,28.656386],
                '南京':[118.78,32.04],
                '滨州':[118.03,37.36],
                '贵阳':[106.71,26.57],
                '无锡':[120.29,31.59],
                '本溪':[123.73,41.3],
                '克拉玛依':[84.77,45.59],
                '渭南':[109.5,34.52],
                '马鞍山':[118.48,31.56],
                '宝鸡':[107.15,34.38],
                '焦作':[113.21,35.24],
                '句容':[119.16,31.95],
                '北京':[116.46,39.92],
                '徐州':[117.2,34.26],
                '衡水':[115.72,37.72],
                '包头':[110,40.58],
                '绵阳':[104.73,31.48],
                '乌鲁木齐':[87.68,43.77],
                '枣庄':[117.57,34.86],
                '杭州':[120.19,30.26],
                '淄博':[118.05,36.78],
                '鞍山':[122.85,41.12],
                '溧阳':[119.48,31.43],
                '库尔勒':[86.06,41.68],
                '安阳':[114.35,36.1],
                '开封':[114.35,34.79],
                '济南':[117,36.65],
                '德阳':[104.37,31.13],
                '温州':[120.65,28.01],
                '九江':[115.97,29.71],
                '邯郸':[114.47,36.6],
                '临安':[119.72,30.23],
                '兰州':[103.73,36.03],
                '沧州':[116.83,38.33],
                '临沂':[118.35,35.05],
                '南充':[106.110698,30.837793],
                '天津':[117.2,39.13],
                '富阳':[119.95,30.07],
                '泰安':[117.13,36.18],
                '诸暨':[120.23,29.71],
                '郑州':[113.65,34.76],
                '哈尔滨':[126.63,45.75],
                '聊城':[115.97,36.45],
                '芜湖':[118.38,31.33],
                '唐山':[118.02,39.63],
                '平顶山':[113.29,33.75],
                '邢台':[114.48,37.05],
                '德州':[116.29,37.45],
                '济宁':[116.59,35.38],
                '荆州':[112.239741,30.335165],
                '宜昌':[111.3,30.7],
                '义乌':[120.06,29.32],
                '丽水':[119.92,28.45],
                '洛阳':[112.44,34.7],
                '秦皇岛':[119.57,39.95],
                '株洲':[113.16,27.83],
                '石家庄':[114.48,38.03],
                '莱芜':[117.67,36.19],
                '常德':[111.69,29.05],
                '保定':[115.48,38.85],
                '湘潭':[112.91,27.87],
                '金华':[119.64,29.12],
                '岳阳':[113.09,29.37],
                '长沙':[113,28.21],
                '衢州':[118.88,28.97],
                '廊坊':[116.7,39.53],
                '菏泽':[115.480656,35.23375],
                '合肥':[117.27,31.86],
                '武汉':[114.31,30.52],
                '大庆':[125.03,46.58]
            };
            var convertData = function (data) {
                var res = [];
                for (var i = 0; i < data.length; i++) {
                    var geoCoord = geoCoordMap[data[i].name];
                    if (geoCoord) {
                        res.push({
                            name: data[i].name,
                            value: geoCoord.concat(data[i].value)
                        });
                    }
                }
                return res;
            };
            
            option = {
               // backgroundColor: '#404a59',
              /***  title: {
                    text: '实时行驶车辆',
                    subtext: 'data from PM25.in',
                    sublink: 'http://www.pm25.in',
                    left: 'center',
                    textStyle: {
                        color: '#fff'
                    }
                },**/
                tooltip : {
                    trigger: 'item',
                    formatter: function (params) {
                        if(typeof(params.value)[2] == "undefined"){
                            return params.name + ' : ' + params.value;
                        }else{
                            return params.name + ' : ' + params.value[2];
                        }
                      }
                },
              
                geo: {
                    map: 'china',
                    label: {
                        emphasis: {
                            show: false
                        }
                    },
                    roam: true,
                    itemStyle: {
                        normal: {
                            areaColor: '#3eabff',
                            borderColor: '#fff'
                        },
                        emphasis: {
                            areaColor: '#006be4'
                        }
                    }
                },
                series : [
                    {
                        name: '用户数量',
                        type: 'scatter',
                        coordinateSystem: 'geo',
                        data: convertData(data),
                        symbolSize: function (val) {
                            return val[2]*10;
                        },
                        label: {
                            normal: {
                                formatter: '{b}',
                                position: 'right',
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        itemStyle: {
                            normal: {
                                color: '#fff'
                            }
                        }
                    },
                   
                ]
            };
                    
                    myChartmap.setOption(option);
                    window.addEventListener("resize",function(){
                        myChartmap.resize();
                    });

        },
        error: function () {
            setContainer('ERROR!');
        }
    });

}








function echarts_3() {
    $.ajax({

        url: '../user_data?action=get_notice_data',
        async: true,
        success: function (data) {
            data=data['data'];
            for(var i in data)
            {
                noticedate.push(data[i]["date"]);
                noticecount.push(data[i]["count"]);
            }
            
    // 基于准备好的dom，初始化echarts实例


    option = {

        tooltip : {
            trigger: 'axis'
        },
      
        grid: {
            left: '3%',
            right: '5%',
            top:'8%',
            bottom: '5%',
            containLabel: true
        },
        color:['#a4d8cc','#25f3e6'],
        toolbox: {
            show : false,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },

        calculable : true,
        xAxis : [
            {
                type : 'category',
                axisTick:{show:false},
                boundaryGap : false,
                axisLabel: {
                    textStyle:{
                        color: 'rgba(255,255,255,.6)',
                        fontSize:'12'
                    },
                    lineStyle:{
                        color:'rgba(255,255,255,.1)',
                    },
                    interval: {default: 0},
                 //   rotate:50,
                    formatter : function(params){
                        var newParamsName = "";// 最终拼接成的字符串
                        var paramsNameNumber = params.length;// 实际标签的个数
                        var provideNumber = 4;// 每行能显示的字的个数
                        var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
                        /**
                         * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
                         */
                        // 条件等同于rowNumber>1
                        if (paramsNameNumber > provideNumber) {
                            /** 循环每一行,p表示行 */
                            var tempStr = "";
                            tempStr=params.substring(0,4);
                            newParamsName = tempStr+"...";// 最终拼成的字符串
                        } else {
                            // 将旧标签的值赋给新标签
                            newParamsName = params;
                        }
                        //将最终的字符串返回
                        return newParamsName
                    }

                },
                data: noticedate
            }
        ],
        yAxis : {
            min:0,
            type : 'value',
            axisLabel: {
                textStyle: {
                    color: '#ccc',
                    fontSize:'12',
                }
            },
            axisLine: {
                lineStyle:{
                    color:'rgba(160,160,160,0.2)',
                }
            },
            splitLine: {
                lineStyle:{
                    color:'rgba(160,160,160,0.2)',
                }
            },

        },
        
        series : [
            {
                // name:'简易程序案件数',
                 lineStyle:{
                    color:'#72b0f9',
                },
                
                type:'line',
                areaStyle: {

                    normal: {type: 'default',
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 0.8, [{
                            offset: 0,
                            color: 'rgba(129,197,255,.6)'
                        }, {
                            offset: 1,
                            color: 'rgba(129,197,255,.0)'
                        }], false)
                    }
                },
                smooth:true,
                itemStyle: {
                    normal: {areaStyle: {type: 'default'}}
                },
                data:noticecount
            }
        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart3.setOption(option);
    window.addEventListener("resize",function(){
        myChart3.resize();
    });

        },
        error: function () {
            setContainer('ERROR!');
        }
    });
}











function echarts_4() {
    $.ajax({

        url: '../user_data?action=get_user_money',
        async: true,
        success: function (data) {
            data=data['data'];
            for(var i in data)
                moneydata.push({value:data[i]["money"],name:data[i]["name"]});
// 基于准备好的dom，初始化echarts实例


option = {

tooltip : {
    trigger: 'item',
    formatter: "{b}: <br/>  {c} ({d}%)"
},

toolbox: {
    show : false,
    feature : {
        mark : {show: true},
        dataView : {show: true, readOnly: false},
        magicType : {
            show: true,
            type: ['pie', 'funnel']
        },
        restore : {show: true},
        saveAsImage : {show: true}
    }
},
calculable : true,
series : [

    {
        name:'排名',
        type:'pie',
        color: ['#33b565', '#20cc98', '#20b9cf', '#2089cf', '#205bcf'],
        radius : [20, 70],
        center : ['50%', '50%'],
        roseType : 'area',
        data:moneydata
    }
]
};


// 使用刚指定的配置项和数据显示图表。
myChart4.setOption(option);
window.addEventListener("resize",function(){
myChart4.resize();
});


        },
        error: function () {
            setContainer('ERROR!');
        }
    });
    
}











function echarts_5() {
    $.ajax({

        url: '../user_data?action=get_todo_data',
        async: true,
        success: function (data) {
            data=data['data'];
            for(var i in data){
                tododate.push(data[i]["date"]);
                todocount.push(data[i]["count"]);
            }
            // 基于准备好的dom，初始化echarts实例


    var xData = function() {
        var data = tododate;

        return data;
    }();

    var data = todocount;

    option = {
        // backgroundColor: "#141f56",

        tooltip: {
            show: "true",
            trigger: 'item',
            backgroundColor: 'rgba(0,0,0,0.4)', // 背景
            padding: [8, 10], //内边距
            // extraCssText: 'box-shadow: 0 0 3px rgba(255, 255, 255, 0.4);', //添加阴影
            formatter: function(params) {
                if (params.seriesName != "") {
                    return params.name + ' ：  ' + params.value + '条';
                }
            },

        },
        grid: {
            borderWidth: 0,
            top: 20,
            bottom: 35,
            left:40,
            right:10,
            textStyle: {
                color: "#fff"
            }
        },
        xAxis: [{
            type: 'category',

            axisTick: {
                show: false
            },
             
            axisLine: {
                show: true,
                lineStyle: {
                     color:'rgba(255,255,255,0.2)',
                }
            },
            axisLabel: {
                inside: false,
                textStyle: {
                    color: '#bac0c0',
                    fontWeight: 'normal',
                    fontSize: '12',
                },
                // formatter:function(val){
                //     return val.split("").join("\n")
                // },
            },
            data: xData,
        }, {
            type: 'category',
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            },
            axisLabel: {
                show: false
            },
            splitArea: {
                show: false
            },
            splitLine: {
                show: false
            },
            data: xData,
        }],
        yAxis: {
            min:0,
            type: 'value',
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(255,255,255,0.2)',
                }
            },
            splitLine: {
                show: true,
                lineStyle: {
                    color: 'rgba(255,255,255,0.1)',
                }
            },
            axisLabel: {
                textStyle: {
                    color: '#bac0c0',
                    fontWeight: 'normal',
                    fontSize: '12',
                },
                formatter: '{value}',
            },
        },
        series: [{
            type: 'bar',
            itemStyle: {
                normal: {
                    show: true,
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                        offset: 0,
                        color: '#00c0e9'
                    }, {
                        offset: 1,
                        color: '#3b73cf'
                    }]),
                    barBorderRadius: 50,
                    borderWidth: 0,
                },
                emphasis: {
                    shadowBlur: 15,
                    shadowColor: 'rgba(105,123, 214, 0.7)'
                }
            },
            zlevel: 2,
            barWidth: '20%',
            data: data,
        },
            {
                name: '',
                type: 'bar',
                xAxisIndex: 1,
                zlevel: 1,
                itemStyle: {
                    normal: {
                        color: 'transparent',
                        borderWidth: 0,
                        shadowBlur: {
                            shadowColor: 'rgba(255,255,255,0.31)',
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowOffsetY: 2,
                        },
                    }
                },
                barWidth: '20%',
                data: [2,3,3,4,2,5,3]
            }
        ]
    }


    // 使用刚指定的配置项和数据显示图表。
    myChart5.setOption(option);
    window.addEventListener("resize",function(){
        myChart5.resize();
    });


        },
        error: function () {
            setContainer('ERROR!');
        }
    });
    
}













function echarts_6() {
    $.ajax({

        url: '../user_data?action=get_news_data',
        async: false,
        success: function (data) {
            data=data['data'];
            for(var i in data)
                newsdata.push({count:data[i]["count"],date:data[i]["date"]});
        },
        error: function () {
            setContainer('ERROR!');
        }
    });
    // 基于准备好的dom，初始化echarts实例


    var data = {
        "chart": newsdata
    }


    var xAxisMonth = [],
        barData = [],
        lineData = [];
    for (var i = 0; i < data.chart.length; i++) {
        xAxisMonth.push(data.chart[i].date);
        barData.push({
            "name": xAxisMonth[i],
            "value": data.chart[i].count
        });
        lineData.push({
            "name": xAxisMonth[i],
            "value": data.chart[i].ratio
        });
    }

    option = {
        // backgroundColor: "#020d22",
        title: '',
        grid: {
            top: '10%',
            left: '30',
            bottom: '0',
            right:'10',
            containLabel: true
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'none'
            },
            formatter: function(params) {
                return params[0]["data"].name + "<br/>" + '新闻量 ' + params[1]["data"].value+'条' ;
            }
        },
        xAxis: [{
            type: 'category',
            show: false,
            data: ['NO.1', 'NO.2', 'NO.3', 'NO.4', 'NO.5','NO.6','NO.7'],
            axisLabel: {
                textStyle: {
                    color: '#b6b5ab'
                }
            }
        },
            {
                type: 'category',
                position: "bottom",
                data: xAxisMonth,
                boundaryGap: true,
                // offset: 40,
                splitLine: {
                    show: false,
                    lineStyle: {
                        color: 'rgba(255,255,255,0.2)'
                    }
                },
                axisTick: {
                    show: false
                },
                axisLine: {
                    show: true,
                    color: "rgba(255,255,255,0.2)"
                },
                axisLabel: {
                    textStyle: {
                        color: '#b6b5ab'
                    }
                }
            }

        ],
        yAxis: [{
            show: true,
            offset: 15,
            splitLine: {
                show: false,
                lineStyle: {
                    color: "rgba(255,255,255,0.1)"
                }
            },
            
            axisTick: {
                show: false
            },
            axisLine: {
                show: true,
                color: "rgba(255,255,255,0.1"
            },
            axisLabel: {
                show: true,
                color: '#b6b5ab'
            }
        }, {
            show: false,
            type: "value",
            // name: "合格率(%)",
            nameTextStyle: {
                color: '#ccc'
            },
            axisLabel: {
                color: '#ccc'
            },
            splitLine: {
                show: false
            },
            axisLine: {
                show: true
            },
            axisTick: {
                show: true
            }
        }],
        color: ['#e54035'],
        series: [{
            name: '训练人次',
            type: 'pictorialBar',
            xAxisIndex: 1,
            barCategoryGap: '-40%',
            // barCategoryGap: '-5%',
            symbol: 'path://d="M150 50 L130 130 L170 130  Z"',
            itemStyle: {
                normal: {
                    color: function(params) {
                        var colorList = [
                            'rgba(13,177,205,0.8)', 'rgba(29,103,182,0.6)',
                            'rgba(13,177,205,0.8)', 'rgba(29,103,182,0.6)',
                            'rgba(13,177,205,0.8)', 'rgba(29,103,182,0.6)'
                        ];
                        return colorList[params.dataIndex];
                    }
                },
                emphasis: {
                    opacity: 1
                }
            },
            data: barData,
        },
            {
                symbol: 'image://data:image/png;base64,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',
                symbolSize: 42,
              
                type: "line",
                yAxisIndex: 1,
                data: lineData,
                itemStyle: {
                    normal: {
                        borderWidth: 5,
                        color: {
                            colorStops: [{
                                offset: 0,
                                color: '#821eff'
                            },

                                {
                                    offset: 1,
                                    color: '#204fff'
                                }
                            ],
                        }
                    }
                }
            }
        ]
    }


    // 使用刚指定的配置项和数据显示图表。
    myChart6.setOption(option);
    window.addEventListener("resize",function(){
        myChart6.resize();
    });
}




(function(){

    $.ajax({

        url: '../user_data?action=get_user_quantity',
        async: true,
        success: function (data) {
            var html = "";
                html +="<img src=\"picture/info-img-1.png\" alt="+""+ "><span>用户数量</span><p>"+data['data'][0]["count"]+"</p>";
            $("#quantity").html(html); //在html页面id=test的标签里显示html内容

        },
        error: function () {
            setContainer('ERROR!');
        }
    });
    $.ajax({

        url: '../user_data?action=get_notice',
        async: true,
        success: function (data) {
            var html = "";
            html +="<img src=\"picture/info-img-2.png\" alt="+""+"><span>公告统计</span><p>"+data['data'][0]["count"]+"</p>";
            $("#notice").html(html); //在html页面id=test的标签里显示html内容

        },
        error: function () {
            setContainer('ERROR!');
        }
    });
    $.ajax({

        url: '../user_data?action=get_todo',
        async: true,
        success: function (data) {
            var html = "";

            html +="<img src=\"picture/info-img-3.png\" alt="+""+ "><span>待办统计</span><p>"+data['data'][0]["count"]+"</p>";
            $("#todolist").html(html); //在html页面id=test的标签里显示html内容

        },
        error: function () {
            setContainer('ERROR!');
        }
    });
    $.ajax({

        url: '../user_data?action=get_news',
        async: true,
        success: function (data) {
            var html = "";
            html +="<img src=\"picture/info-img-4.png\" alt="+""+ "><span>新闻统计</span><p>"+data['data'][0]["count"]+"</p>";
            $("#news").html(html); //在html页面id=test的标签里显示html内容

        },
        error: function () {
            setContainer('ERROR!');
        }
    });

    echarts_1();
    echarts_2();
    map();
    echarts_3();
    echarts_4();
    echarts_5();
    echarts_6();


})()


var count=60
setInterval(function(){
    if (count==0){
        count=60;
        map3=[];
        moneydata=[];
        moneycount=[];
        stockdata=[];
        newsdata=[];
        tododate=[];
        todocount=[];
        noticedate=[];
        noticecount=[];
        $.ajax({

            url: '../user_data?action=get_user_quantity',
            async: true,
            success: function (data) {
                var html = "";
                html +="<img src=\"picture/info-img-1.png\" alt="+""+ "><span>用户数量</span><p>"+data['data'][0]["count"]+"</p>";
                $("#quantity").html(html); //在html页面id=test的标签里显示html内容

            },
            error: function () {
                setContainer('ERROR!');
            }
        });
        $.ajax({

            url: '../user_data?action=get_notice',
            async: true,
            success: function (data) {
                var html = "";
                html +="<img src=\"picture/info-img-2.png\" alt="+""+"><span>公告统计</span><p>"+data['data'][0]["count"]+"</p>";
                $("#notice").html(html); //在html页面id=test的标签里显示html内容

            },
            error: function () {
                setContainer('ERROR!');
            }
        });
        $.ajax({

            url: '../user_data?action=get_todo',
            async: true,
            success: function (data) {
                var html = "";

                html +="<img src=\"picture/info-img-3.png\" alt="+""+ "><span>待办统计</span><p>"+data['data'][0]["count"]+"</p>";
                $("#todolist").html(html); //在html页面id=test的标签里显示html内容

            },
            error: function () {
                setContainer('ERROR!');
            }
        });
        $.ajax({

            url: '../user_data?action=get_news',
            async: true,
            success: function (data) {
                var html = "";
                html +="<img src=\"picture/info-img-4.png\" alt="+""+ "><span>新闻统计</span><p>"+data['data'][0]["count"]+"</p>";
                $("#news").html(html); //在html页面id=test的标签里显示html内容

            },
            error: function () {
                setContainer('ERROR!');
            }
        });

        echarts_1();
        echarts_2();
        map();
        echarts_3();
        echarts_4();
        echarts_5();
        echarts_6();





    }
    else{
        count-=1
    }
    document.getElementById("demo1").innerHTML = count;
    console.log(count);
},1000);

