<template>
    <div>
       <div  :style="{width: widthBar, height: heightBar}" ref="myEchart"></div>
    </div>
</template>
<script>
    //柱状图我打算通用，即从父组件里传值进来，然后判断对应着的是中国还是世界
    // 引入基本模板
    import echarts from "echarts";
    //引入柱状图组件
    require('echarts/lib/chart/bar')
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');
    export default {
        name: "barChart",
        props: {
            widthBar:{type:String,default:"325px"},
            heightBar:{type:String,default:"450px"},
            isProOrCount:{type:String,default:"中国"},//判断是中国各省份的数据(中国)还是世界各国家地区的数据(世界)
            todayDate:{type:Number,default:20200521}
        },
        data(){
            return{
                chart:null,
                option:{
                    title:{
                        text:'累计病例详情',
                        top:"30px",
                        left:'center',
                        textStyle:{color:'#fff',fontSize:13}
                    },
                    textStyle:{color:'#fff'},
                    backgroundColor:'#1e1e1e',
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['累计确诊', '现存确诊', '累计死亡'],
                        textStyle:{color:'#fff'}
                    },
                    grid: {
                        left: '3%',
                        //right: '4%',
                        bottom: '8%',
                        containLabel: true
                    },
                    xAxis: [
                        {
                            type: 'value',
                            show:false
                        }
                    ],
                    yAxis: [
                        {
                            type: 'category',
                            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日'],
                            axisLabel:{
                                interval:0
                            }
                        }
                    ],
                    toolbox: {
                        show: true,
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    dataZoom: [{
                        type: 'slider',
                        height:30,
                        bottom:0,
                        start:0,
                        end:10,
                        xAxisIndex:[0],
                        filterMode:'none',
                        textStyle:{color:'#fff'}
                    },{
                        type: 'slider',
                        yAxisIndex:[0],
                        start:0,
                        end:10,
                        width: 30,
                        right:0,
                        textStyle:{color:'#fff'}
                    }],
                    series: [
                        {
                            name: '累计确诊',
                            type: 'bar',
                            stack: '疫情',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name: '现存确诊',
                            type: 'bar',
                            stack: '疫情',
                            data: [220, 182, 191, 234, 290, 330, 310]
                        },
                        {
                            name: '累计死亡',
                            type: 'bar',
                            stack: '疫情',
                            data: [150, 232, 201, 154, 190, 330, 410]
                        }
                    ]
                }
//上述为option
            }
        },
        mounted(){
            //获取数据
            if(this.isProOrCount=='中国'){
                //获取中国各省份的数据
                var _this=this;
                this.axios.get('http://localhost:8080/user/findAllProvinceBarDataGroup?dateId='+this.todayDate)
                .then(function(res){
                    _this.option.series[0].data=res.data.confirmedCountList;
                    _this.option.series[1].data=res.data.curConfirmedCountList;
                    _this.option.series[2].data=res.data.deadCount;
                    _this.option.yAxis[0].data=res.data.shortNameList;
                    _this.initChart()
                })
            }else{
                //获取世界各国家地区的数据
                var _this=this;
                this.axios.get('http://localhost:8080/user/findAllCountryBarDataGroup?dateId='+this.todayDate)
                .then(function(res){
                    _this.option.series[0].data=res.data.confirmedCountList;
                    _this.option.series[1].data=res.data.curConfirmedCountList;
                    _this.option.series[2].data=res.data.deadCount;
                    _this.option.yAxis[0].data=res.data.shortNameList;
                    _this.initChart()
                })
            }
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
            }
        }
    }
</script>