<template>
    <div>
       <div  :style="{width: widthLine, height: heightLine}" ref="myEchart"></div>
    </div>
</template>
<script>
    // 引入基本模板
    import echarts from "echarts";
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');
    export default {
        name: "chinaLine",
        props: {
            widthLine:{type:String,default:"100%"},
            heightLine:{type:String,default:"590px"},
            dateData:String,
            provinceName:String
        },
        data(){
            //这里是为了在自定义的toolbox按钮里使用methods里的方法
            let self=this
            return{
                timeStoper:null,//设置定时器对象
                chart:null,
                selectDate:'null',
                isPlay:false,//是否播放控制变量
                option:{
                    textStyle:{color:'#fff'},
                    backgroundColor:'#1e1e1e',
                    color:['rgb(224,32,32)','rgb(255,242,104)','rgb(84,151,211)','rgb(245,98,98)','rgb(255,193,0)','#fff'],
                    title: {
                        text: '全国疫情数据统计',
                        left:'100',
                        textStyle:{color:'#fff'}
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['新增确诊','新增疑似','新增死亡','累计确诊','累计疑似','累计死亡'],
                        selectedMode:'single',
                        textStyle:{color:'#fff'}
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            //自定义播放组件
                            myPlay:{
                                show:true,
                                title:'开始播放',
                                icon:'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                                onclick:function(){
                                    self.play()
                                }
                            },
                            myPlayStop:{
                                show:true,
                                title:'暂停播放',
                                icon:'path://M432.45,595.444c0,2.177-4.661,6.82-11.305,6.82c-6.475,0-11.306-4.567-11.306-6.82s4.852-6.812,11.306-6.812C427.841,588.632,432.452,593.191,432.45,595.444L432.45,595.444z M421.155,589.876c-3.009,0-5.448,2.495-5.448,5.572s2.439,5.572,5.448,5.572c3.01,0,5.449-2.495,5.449-5.572C426.604,592.371,424.165,589.876,421.155,589.876L421.155,589.876z M421.146,591.891c-1.916,0-3.47,1.589-3.47,3.549c0,1.959,1.554,3.548,3.47,3.548s3.469-1.589,3.469-3.548C424.614,593.479,423.062,591.891,421.146,591.891L421.146,591.891zM421.146,591.891',
                                onclick:function(){
                                    self.playStop()
                                }
                            },
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    dataZoom:[{
                        startValue:'20190416'
                    },{
                        type:'inside'
                    }],
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
                        data: ['20190225','20190304','20190318','20190326','20190416','20190426','20190504'],
                        axisLabel:{
                            interval:10
                        }
                    },
                    yAxis: {
                        splitLine:{
                            show:false
                        }
                    },
                    series: [
                        {
                            name:'新增确诊',
                            type:'line',
                            data:[11, 11, 15, 13, 12, 13, 10],
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:3000
                                },{
                                    yAxis:6000
                                },{
                                    yAxis:9000
                                },{
                                    yAxis:12000
                                },{
                                    yAxis:15000
                                },{
                                    yAxis:18000
                                }
                                ]
                            }
                        },
                        {
                            name:'新增疑似',
                            type:'line',
                            data:[1, -2, 2, 5, 3, 2, 0],
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:20
                                },{
                                    yAxis:40
                                },{
                                    yAxis:60
                                },{
                                    yAxis:80
                                },{
                                    yAxis:100
                                }
                                ]
                            }
                        },
                        {
                            name:'新增死亡',
                            type:'line',
                            data:[1, -2, 2, 5, 3, 2, 0],
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:200
                                },{
                                    yAxis:400
                                },{
                                    yAxis:600
                                },{
                                    yAxis:800
                                },{
                                    yAxis:1000
                                },{
                                    yAxis:1200
                                },{
                                    yAxis:1500
                                }
                                ]
                            }
                        },
                        {
                            name:'累计确诊',
                            type:'line',
                            data:[1, -2, 2, 5, 3, 2, 0],
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:20000
                                },{
                                    yAxis:40000
                                },{
                                    yAxis:60000
                                },{
                                    yAxis:80000
                                },{
                                    yAxis:100000
                                }
                                ]
                            }
                        },
                        {
                            name:'累计疑似',
                            type:'line',
                            data:[1, -2, 2, 5, 3, 2, 0],
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:300
                                },{
                                    yAxis:600
                                },{
                                    yAxis:900
                                },{
                                    yAxis:1200
                                },{
                                    yAxis:1500
                                },{
                                    yAxis:1800
                                }
                                ]
                            }
                        },
                        {
                            name:'累计死亡',
                            type:'line',
                            data:[1, -2, 2, 5, 3, 2, 0],
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:1000
                                },{
                                    yAxis:2000
                                },{
                                    yAxis:3000
                                },{
                                    yAxis:4000
                                },{
                                    yAxis:5000
                                }
                                ]
                            }
                        }
                    ]
                }
//上述为option
            }
        },
        watch:{
            provinceName:{
                handler:function(newVal,oldVal){
                    if(newVal){
                        //获取到的省份名变化，需要重新渲染图表，更新值，方法如获取全国的数据相同
                        if(newVal=='中国'){
                            var _this=this;
                            this.option.title.text=newVal+"疫情数据统计"
                            this.axios.get('http://localhost:8080/user/findAllChinaDataGroup')
                            .then(function(res){
                                _this.option.xAxis.data=res.data.dateId;
                                _this.option.series[0].data=res.data.confirmedIncr;
                                _this.option.series[1].data=res.data.suspectedCountIncr;
                                _this.option.series[2].data=res.data.deadIncr;
                                _this.option.series[3].data=res.data.confirmedCount;
                                _this.option.series[4].data=res.data.suspectedCount;
                                _this.option.series[5].data=res.data.deadCount;
                                _this.initChart();
                            })
                        }else{
                            //切换到选中省份的数据
                            var _this=this;
                            this.option.title.text=newVal+"疫情数据统计"
                            this.axios.get('http://localhost:8080/user/findAllProvinceDataGroup?ProvinceName='+newVal)
                            .then(function(res){
                                _this.option.xAxis.data=res.data.dateId;
                                _this.option.series[0].data=res.data.confirmedIncr;
                                _this.option.series[1].data=res.data.suspectedCountIncr;
                                _this.option.series[2].data=res.data.deadIncr;
                                _this.option.series[3].data=res.data.confirmedCount;
                                _this.option.series[4].data=res.data.suspectedCount;
                                _this.option.series[5].data=res.data.deadCount;
                                _this.initChart();
                            })
                        }
                    }
                }
            },
            //播放相关（完成，只不过在地图上还会提示是选中的哪一天的数据，这个可以想想如何改一下说辞）
            isPlay:{
                handler:function(newVal,oldVal){
                    if(newVal){
                        //设置定时器
                        this.playMessage(newVal)
                        var _this=this
                        var iniVal=0
                        var len=Object.keys(this.option.xAxis.data).length
                        var arrData=this.option.xAxis.data
                        this.timeStoper=setInterval(function(){
                            //真正的play事件被放在定时器里
                            if(iniVal<len){
                                _this.selectDate=arrData[iniVal]+""
                                _this.$emit('func',_this.selectDate)//只能传被定义的，如果直接传params.name传不过去
                                iniVal++
                            }
                        },3000)
                    }else{
                        //关闭定时器
                        this.playMessage(newVal)
                        clearInterval(this.timeStoper)
                    }
                }
            }
        },
        mounted(){
            console.log("proName:"+this.provinceName)
            var _this=this;
            this.option.title.text=this.provinceName+"疫情数据统计"
            this.axios.get('http://localhost:8080/user/findAllChinaDataGroup')
            .then(function(res){
                console.log(res.data.dateId)
                _this.option.xAxis.data=res.data.dateId;
                _this.option.series[0].data=res.data.confirmedIncr;
                _this.option.series[1].data=res.data.suspectedCountIncr;
                _this.option.series[2].data=res.data.deadIncr;
                _this.option.series[3].data=res.data.confirmedCount;
                _this.option.series[4].data=res.data.suspectedCount;
                _this.option.series[5].data=res.data.deadCount;
                _this.initChart();
            })

        },
        beforeDestroy() {
            if (!this.chart) {
                return;
            }
            this.chart.dispose();
            this.chart = null;
        },
        methods:{
            initChart(){
                // 初始化echarts实例
                this.chart =echarts.init(this.$refs.myEchart);
                window.onresize=echarts.init(this.$refs.myEchart).resize;
                // 绘制图表
                this.chart.setOption(this.option);
                //增加折线图选中事件
                if(this.chart._$handlers.click){
                    this.chart._$handlers.click.length = 0;
                }
                var _this=this;
                this.chart.on('click',function(params){
                    console.log("params:"+params.name)//输出的是X轴信息，即日期数据，即可回传日期数据给地图然后更新地图的数据了！！！
                    _this.selectDate=params.name
                    _this.$emit('func',_this.selectDate)//只能传被定义的，如果直接传params.name传不过去
                })
            },
            play(){
                this.isPlay=true
            },
            playStop(){
                this.isPlay=false
            },
            //展示正在播放还是播放完毕
            playMessage(val){
                if(val){
                    this.$message("疫情回顾开始")
                }else{
                    this.$message("疫情回顾结束")
                }
            }
        }
    }
</script>