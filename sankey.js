var nodes =[
    {"name":"Andriod3"},
    {"name":"服务频道2"},
    {"name":"其它2"},
    {"name":"服务频道4"},
    {"name":"服务频道3"},
    {"name":"乙方2"},
    {"name":"乙方3"},
    {"name":"其它3"},
    {"name":"Andriod4"},
    {"name":"Andriod2"},
    {"name":"其它4"},
    {"name":"Andriod1"},
    {"name":"乙方4"},
    {"name":"乙方5"},
    {"name":"Andriod5"},
    {"name":"服务频道5"},
    {"name":"其它5"},
    ]

    var links = [
        {"source":"Andriod1","target":"Andriod2","value":"65"},
        {"source":"乙方3","target":"Andriod4","value":"1"},
        {"source":"乙方2","target":"Andriod3","value":"1"},
        {"source":"服务频道3","target":"Andriod4","value":"2"},
        {"source":"Andriod2","target":"Andriod3","value":"48"},
        {"source":"服务频道2","target":"其它3","value":"1"},
        {"source":"乙方2","target":"服务频道3","value":"1"},
        {"source":"Andriod3","target":"Andriod4","value":"35"},
        {"source":"Andriod2","target":"服务频道3","value":"3"},
        {"source":"Andriod4","target":"服务频道5","value":"3"},
        {"source":"Andriod3","target":"乙方4","value":"1"},
        {"source":"Andriod1","target":"服务频道2","value":"6"},
        {"source":"服务频道2","target":"服务频道3","value":"2"},
        {"source":"其它2","target":"Andriod3","value":"1"},
        {"source":"服务频道4","target":"Andriod5","value":"1"},
        {"source":"Andriod2","target":"乙方3","value":"1"},
        {"source":"Andriod1","target":"乙方2","value":"2"},
        {"source":"服务频道2","target":"Andriod3","value":"1"},
        {"source":"Andriod1","target":"其它2","value":"1"},
        {"source":"乙方4","target":"Andriod5","value":"1"},
        {"source":"服务频道3","target":"服务频道4","value":"3"},
        {"source":"Andriod4","target":"Andriod5","value":"26"},
        ];

        var myChart = echarts.init(document.getElementById("main"));
        var option = {
            //标题
            title: {
                        text: 'Sankey Diagram'
                   },
                   //工具提示
                   tooltip: {
                       trigger: 'item',
                       triggerOn: 'mousemove'
            
                   },
                   series: [
                       {
                        //图的类型
                           type: 'sankey',
                           layout:'none',
                           //图中所用数据，就是上面引入的数据，包括节点和关联两部分
                           data: nodes,
                           links:links,
                           itemStyle: {
                               normal: {
                                   borderWidth: 1,
                                   borderColor: '#aaa'
                               }
                           },
                           //线条样式
                           lineStyle: {
                               normal: {
                                   curveness: 0.5
                               }
                           }
                       }
                    ]} 
                    myChart.setOption(option);
            