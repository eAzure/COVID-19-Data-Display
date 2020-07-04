<template>
    <div>
       <div  :style="{width: widthPie, height: heightPie}" ref="myEchart"></div>
    </div>
</template>
<script>
    //柱状图我打算通用，即从父组件里传值进来，然后判断对应着的是中国还是世界
    // 引入基本模板
    import echarts from "echarts";
    //引入柱状图组件
    require('echarts/lib/chart/pie')
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');
    export default {
        name: "pieChart",
        props: {
            widthPie:{type:String,default:"100%"},
            heightPie:{type:String,default:"450px"},
            isProOrCount:{type:String,default:"中国"},//判断是中国各省份的数据(中国)还是世界各国家地区的数据(世界)
            todayDate:{type:Number,default:20200521},
            dealedDate:{type:String,default:"5月21日"}
        },
        data(){
            let self=this
            return{
                chart:null,
                option:{
                    title:{
                        text:'新增病例详情',
                        subtext:self.dealedDate+' 0-24时',
                        top:"85px",
                        left:'center',
                        textStyle:{color:'#fff',fontSize:13}
                    },
                    backgroundColor:'#1e1e1e',
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 10,
                        data: ['新增确诊', '新增疑似', '新增死亡'],
                        textStyle:{
                            color:'#fff'
                        }
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    series: [
                        {
                            name: '新增数据',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: [
                                {value: 0, name: '新增确诊'},
                                {value: 0, name: '新增疑似'},
                                {value: 0, name: '新增死亡'}
                            ]
                        }
                    ]
                }
//上述为option
            }
        },
        mounted(){
            //判断是要显示中国的数据还是要显示世界的数据
            if(this.isProOrCount=="中国"){
                //获取中国的新增数据
                var _this=this
                this.axios.get('http://localhost:8080/user/findAllIncrChinaData?dateId='+this.todayDate)
                .then(function(res){
                    _this.option.series[0].data=res.data;
                    _this.initChart()
                })
            }else{
                //获取世界的新增数据
                var _this=this
                this.axios.get('http://localhost:8080/user/findAllIncrWorldData?dateId='+this.todayDate)
                .then(function(res){
                    _this.option.series[0].data=res.data;
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