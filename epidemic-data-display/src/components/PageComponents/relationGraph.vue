<template>
    <div>
       <div  :style="{width: widthRelationGraph, height: heightRelationGraph}" ref="myEchart"></div>
    </div>
</template>
<script>
    //柱状图我打算通用，即从父组件里传值进来，然后判断对应着的是中国还是世界
    // 引入基本模板
    import echarts from "echarts";
    //引入柱状图组件
    require('echarts/lib/chart/graph')
    // 引入提示框和title组件
    require('echarts/lib/component/tooltip');
    require('echarts/lib/component/title');
    export default {
        name: "relationGraph",
        props: {
            widthRelationGraph:{type:String,default:"100%"},
            heightRelationGraph:{type:String,default:"660px"}
        },
        data(){
            return{
                chart:null,
                option:{
                    backgroundColor:'#1e1e1e',
                    title: {
                        text: '确诊患者城市间流动情况',
                        top: 0,
                        left: 'right',
                        textStyle:{
                            color:'#fff'
                        }
                    },
                    tooltip: {
                        trigger:'item'
                    },
                    legend: {
                        type:'scroll',
                        orient: 'vertical',
                        left:10,
                        data:['北京','天津','上海','重庆','石家庄','太原','西安',
                        '济南','郑州','沈阳','长春','哈尔滨','南京','杭州','合肥',
                        '南昌','福州','武汉','长沙','成都','贵阳','昆明','广州','海口',
                        '兰州','西宁','台北','呼和浩特','乌鲁木齐','拉萨','南宁','银川',
                        '香港','澳门'],
                        textStyle:{color:'#fff'},
                        pageTextStyle:{color:'#fff'}
                        //itemWidth:10
                    },
                    animationDurationUpdate: 1500,
                    animationEasingUpdate: 'quinticInOut',
                    series: [
                        {
                            name: '感染者外出',
                            type: 'graph',
                            layout: 'circular',
                            circular: {
                                rotateLabel: true
                            },
                            categories:[{name:'北京'},{name:'天津'},{name:'上海'},{name:'重庆'},{name:'石家庄'},{name:'太原'},{name:'西安'},
                            {name:'济南'},{name:'郑州'},{name:'沈阳'},{name:'长春'},{name:'哈尔滨'},{name:'南京'},{name:'杭州'},{name:'合肥'},
                            {name:'南昌'},{name:'福州'},{name:'武汉'},{name:'长沙'},{name:'成都'},{name:'贵阳'},{name:'昆明'},{name:'广州'},{name:'海口'},
                            {name:'兰州'},{name:'西宁'},{name:'台北'},{name:'呼和浩特'},{name:'乌鲁木齐'},{name:'拉萨'},{name:'南宁'},{name:'银川'},
                            {name:'香港'},{name:'澳门'}],
                            data: [{
                                name:'武汉',
                                category:'武汉',
                                value: 10,
                                symbolSize:10
                            }, {
                                name:'济南',
                                category:'济南',
                                value: 20,
                                symbolSize: 20
                                
                            }],
                            links: [{
                                source: '武汉',
                                target: '济南'
                            }],
                            label:{
                                normal:{
                                    show:true
                                }
                            },
                            roam: true,
                            lineStyle: {
                                color: 'source',
                                curveness: 0.3
                            }
                        }
                    ]
                }
//上述为option
            }
        },
        mounted(){
            //获取数据
            var _this=this
            this.axios.get('http://localhost:8080/user/findAllRelInPro')
            .then(function(res){
                _this.option.series[0].data=res.data;
                var _this1=_this;
                _this.axios.get('http://localhost:8080/user/findAllNodeLink')
                .then(function(res){
                    _this1.option.series[0].links=res.data
                    _this1.initChart()
                })
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
            }
        }
    }
</script>