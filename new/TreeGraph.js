function treeMenu(a) {
    //列表map形式  
    this.tree = a || [];
    this.groups = {};
    //存放id与对应的name映射  
    this.nameMap = {}
    //得到每个点对应的层次,为了后期进行布局  
    this.levelMap = {}
    //样式设计  
    this.style = { "symbolSize": [60, 55, 50, 45, 40], "value": [2, 2, 2, 2, 2] }
};
treeMenu.prototype = {
    init: function (pid) {
        this.group();
        this.MapNamebyId();
        this.setIdLevel(pid);
        return this.rescusive(pid);
    },
    group: function () {
        for (var i = 0; i < this.tree.length; i++) {
            //存在该groups则直接添加  
            if (this.groups[this.tree[i].pId]) {
                if (this.groups[this.tree[i].id]) { }
                else {
                    this.groups[this.tree[i].pId].push(this.tree[i]);
                }
            } else {
                if (this.groups[this.tree[i].id]) { }
                else {
                    this.groups[this.tree[i].pId] = [];
                    this.groups[this.tree[i].pId].push(this.tree[i]);
                }
            }
        }
    },
    //得到每个点的层次  
    setIdLevel: function (pid) {
        var level = 1;
        this.levelMap[pid] = level;
        var gs = this.groups[pid];
        //str=JSON.stringify(gs)  
        //alert("json:"+str)  
        var temp = []
        while (gs) {
            level++;
            if (gs == null || gs == undefined || gs.length == 0)
                break;
            temp = []
            for (var i = 0; i < gs.length; i++) {
                var myid = gs[i]["id"];
                this.levelMap[myid] = level;
                subgs = this.groups[myid];
                if (subgs instanceof Array && subgs != null) {
                    for (var j = 0; j < subgs.length; j++) {
                        temp.push(subgs[j]);
                    }
                }
            }
            gs = temp;
        }

    },
    //样式大小  
    getStyleById: function (id) {
        var level = this.levelMap[id]
        if (level >= 5)
            level = 5;
        var symbolize = 0
        var value = 0
        symbolize = this.style['symbolSize'][level - 1]
        value = this.style['value'][level - 1]
        var styleValue = {}
        styleValue['symbolSize'] = symbolize
        styleValue['value'] = value
        return styleValue
    },
    MapNamebyId: function () {
        for (var i = 0; i < this.tree.length; i++) {
            map = this.tree[i]
            this.nameMap[map["id"]] = map["name"]
        }
    },
    //设置节点属性  
    setNode: function (node, name, symbolize, value, children) {
        node['name'] = name;
        node['symbolSize'] = symbolize;
        node['value'] = value
        node['children'] = children
        return node;
    },
    rescusive: function (number) {
        var data = []
        var node = {}
        var styleValue = {}
        //某个节点下的子节点  
        var a = this.groups[number];
        var nodeName = this.nameMap[number];
        if (a == null || a == undefined) {
            styleValue = this.getStyleById(number)
            //设置节点  
            this.setNode(node, nodeName, styleValue['symbolSize'], styleValue['value'], [])
            return node;
        }
        for (var i = 0; i < a.length; i++) {
            children = this.rescusive(a[i].id);
            data.push(children);
        }
        styleValue = this.getStyleById(number)
        this.setNode(node, nodeName, styleValue['symbolSize'], styleValue['value'], data)
        return node;
    },
    //判断数组 
    isInArray: function (arr, value) {
        for (var i = 0; i < arr.length; i++) {
            if (value === arr[i]) {
                return true;
            }
        }
        return false;
    },

    //创建组织结构图  
    createTreeVisual: function (myChart, title, data) {
        var option = {
            title: {
                text: title
            },
            tooltip: {
                trigger: 'item',
                formatter: "{b}"
            },
            toolbox: {
                show: true,
                feature: {
                    saveAsImage: { show: true }
                }
            },
            calculable: false,
            series: [
                {
                    name: '树图',
                    type: 'tree',
                    orient: 'horizontal',  // vertical horizontal  
                    rootLocation: { x: 100, y: '60%' }, // 根节点位置  {x: 'center',y: 10}  
                    nodePadding: 20,
                    symbol: 'circle',
                    //图的一些样式设置 
                    itemStyle: {
                        //正常情况显示
                        normal: {
                            label: {
                                show: true,
                                position: 'inside',
                                textStyle: {
                                    //字体颜色、大小、加粗
                                    color: '#000000',
                                    fontSize: 15,
                                    fontWeight: 'bolder'
                                }
                            },
                            color: '#fff',
                            lineStyle: {
                                color: '#000',
                                width: 1,

                            }
                        },
                        //鼠标移上去样式
                        emphasis: {
                            label: {
                                show: false,
                                textStyle: {
                                    align: 'center',
                                    verticalAlign: 'middle'
                                }
                            },
                            color: '#fff',
                            borderWidth: 1
                        }
                    },
                    data: data
                }]//series  
        }
        myChart.setOption(option);
    }
}

//得到数据  
function getData(zNodes) {
    var mytree = new treeMenu(zNodes)
    treeData = mytree.init(0)
    data = []
    data.push(treeData)
    return data;
}

function createTreeV(mychart, title, znodes, value) {

    var mytree = new treeMenu(znodes)
    //$("#pathflow").empty();
    treeData = mytree.init(Cmd())
    data = []
    data.push()
    data.push(treeData)
    mytree.createTreeVisual(myChart, title, data)
}
//传ID
function Cmd() {
    queryId = document.getElementById("txt").value;
    return queryId
}

//传ZNodes
function getJson(){
    data =  $.get('pathtest.json')
    return data;
 }
      