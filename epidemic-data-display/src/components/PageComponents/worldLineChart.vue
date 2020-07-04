<template>
    <div :style="{width:widthLine,height:heightLine}" ref="myEchart"></div>
</template>
<script>
// 引入基本模板
    import echarts from "echarts";
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');
    export default {
        name:"worldLine",
        props:{
            //定义高度和宽度
            widthLine:{type:String,default:"100%"},
            heightLine:{type:String,default:"590px"},
            //获得所选的国家名
            countryName:String
        },
        data(){
            let self=this;
            return{
                timeStoper:null,//设置定时器对象
                isPlay:false,//是否播放控制变量
                chart:null,//定义echarts对象
                selectDate:'null',//定义被选中的时间
                option:{
                    textStyle:{color:'#fff'},
                    backgroundColor:'#1e1e1e',
                    color:['rgb(224,32,32)','rgb(255,242,104)','rgb(84,151,211)','rgb(245,98,98)'],
                    title: {
                        text: '世界疫情数据统计',
                        left:'100',
                        textStyle:{color:'#fff'}
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data:['累计确诊','新增确诊','累计死亡','新增死亡'],
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
                        startValue:'20200119'
                    },{
                        type:'inside'
                    }],
                    xAxis:  {
                        type: 'category',
                        boundaryGap: false,
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
                            name:'累计确诊',
                            type:'line',
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:1000000
                                },{
                                    yAxis:2000000
                                },{
                                    yAxis:3000000
                                },{
                                    yAxis:4000000
                                },{
                                    yAxis:5000000
                                },{
                                    yAxis:6000000
                                },{
                                    yAxis:7000000
                                }
                                ]
                            }
                        },
                        {
                            name:'新增确诊',
                            type:'line',
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
                                },{
                                    yAxis:120000
                                },{
                                    yAxis:150000
                                }
                                ]
                            }
                        },
                        {
                            name:'累计死亡',
                            type:'line',
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:50000
                                },{
                                    yAxis:100000
                                },{
                                    yAxis:150000
                                },{
                                    yAxis:200000
                                },{
                                    yAxis:250000
                                },{
                                    yAxis:300000
                                },{
                                    yAxis:350000
                                },{
                                    yAxis:400000
                                }
                                ]
                            }
                        },
                        {
                            name:'新增死亡',
                            type:'line',
                            markLine: {
                                silent:true,
                                data: [{
                                    yAxis:2000
                                },{
                                    yAxis:4000
                                },{
                                    yAxis:6000
                                },{
                                    yAxis:8000
                                },{
                                    yAxis:10000
                                },{
                                    yAxis:12000
                                }
                                ]
                            }
                        }
                    ]
                }
            }
        },
        watch:{
            countryName:{
                handler:function(newVal,oldVal){
                    if(newVal){
                        if(newVal=='世界'){
                            //获取到世界的数据
                            console.log("获取到世界的数据")
                            var _this=this;
                            this.option.title.text=newVal+"疫情数据统计"
                            this.axios.get('http://localhost:8080/user/findAllWorldDataGroup')
                            .then(function(res){
                                console.log(res.data.dateId)
                                _this.option.xAxis.data=res.data.dateId;
                                _this.option.series[0].data=res.data.confirmedCount;
                                _this.option.series[1].data=res.data.confirmedIncr;
                                _this.option.series[2].data=res.data.deadCount;
                                _this.option.series[3].data=res.data.deadIncr;
                                _this.initChart();
                            })
                        }else{
                            //获取到各选中国家的数据
                            var _this=this;
                            this.option.title.text=newVal+"疫情数据统计"
                            this.axios.get('http://localhost:8080/user/findAllCountryDataGroup?CountryFullName='+newVal)
                            .then(function(res){
                                _this.option.xAxis.data=res.data.dateId;
                                _this.option.series[0].data=res.data.confirmedCount;
                                _this.option.series[1].data=res.data.confirmedIncr;
                                _this.option.series[2].data=res.data.deadCount;
                                _this.option.series[3].data=res.data.deadIncr;
                                _this.initChart();
                            })
                        }
                    }
                }
            },
            isPlay:{
                handler:function(newVal,oldVal){
                    if(newVal){
                        //设置定时器
                        this.$message('疫情回顾开始')
                        var _this=this
                        var iniVal=0;
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
                        this.$message('疫情回顾结束')
                        clearInterval(this.timeStoper)
                    }
                }
            }
        },
        mounted(){
            //这里要进行数据获取，获取时间数据和各类型数据
            var _this=this;
            this.axios.get('http://localhost:8080/user/findAllWorldDataGroup')
            .then(function(res){
                console.log(res.data.dateId)
                _this.option.xAxis.data=res.data.dateId;
                _this.option.series[0].data=res.data.confirmedCount;
                _this.option.series[1].data=res.data.confirmedIncr;
                _this.option.series[2].data=res.data.deadCount;
                _this.option.series[3].data=res.data.deadIncr;
                _this.initChart();
            })
        },
        beforeDestroy(){
            if(!this.chart){
                return;
            }
            this.chart.dispose();
            this.chart=null;
        },
        methods:{
            //初始化echarts实例
            initChart(){
                this.chart=echarts.init(this.$refs.myEchart);
                window.onresize=echarts.init(this.$refs.myEchart).resize;
                //设置图表选项
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
            //toolbox里的播放按钮对应的函数
            play(){
                this.isPlay=true
            },
            //暂停播放按钮对应的函数
            playStop(){
                this.isPlay=false
            }
        }
    }
</script>