<template>
  <div class="echarts">
    <div :style="{height:height,width:width}" ref="myEchart"></div>
  </div>
</template>
<script>
  import echarts from "echarts"; //引入组件
  import '../../../node_modules/echarts/map/js/china.js' // 引入中国地图数据
  export default {
    name: "echartsTest",
    props: {
      width:{type:String,default:"100%"},
      height:{type:String,default:"500px"}
    },
    data() {
      return {
        chart: null,
        data: [{
                "name": "北京",
                "value": 599
              }, {
                "name": "上海",
                "value": 142
              }, {
                "name": "黑龙江",
                "value": 44
              }, {
                "name": "深圳",
                "value": 92
              }, {
                "name": "湖北",
                "value": 810
              }, {
                "name": "四川",
                "value": 453
              }]
      };
    },
    mounted() {
      this.initChart();
    },
    methods: {
      initChart() {
        this.chart= echarts.init(this.$refs.myEchart); //这里是为了获得容器所在位置    
        window.onresize = echarts.init(this.$refs.myEchart).resize;
        //配置和数据放在这里
        this.chart.setOption({ // 进行相关配置
          backgroundColor: "#1e1e1e",
          tooltip: {  //提示框组件
            trigger: 'item',
            formatter: '{b}<br/>确诊人数：{c} '
          }, // 鼠标移到图里面的浮动提示框
          //图表标题
          title:{
            text:'中国疫情数据',
            subtext:'the epidemic data of China',
            top:"30px",
            left:'center',
            textStyle:{color:'#fff'}
          },
          //图列显示柱
          dataRange: {
            type:'continuous',
            show: true,
            min: 0,
            left:30,
            max: 1000,
            text: ['High', 'Low'],
            textStyle:{
              color:'#fff'
            },
            realtime: false,
            calculable: true,
            color: ['orangered', 'yellow', 'lightskyblue']
          },
          toolbox: {  //工具栏
              show: true,
              orient: 'vertical',
              left: 'right',
              top:50,
              itemGap:20,
              left:30,
              feature: {
                  dataView: {readOnly: false},
                  restore: {},
                  saveAsImage: {}
              }
          },
          series: [
            {
              name: '确诊人数', // 浮动框的标题
              type: 'map',
              mapType:'china',
              roam:true,
              data: this.data,//绑定数据
              symbolSize:12,
              label: {
                normal: {
                  show: false
                },
                emphasis:{
                  show:false
                }
              },
              itemStyle: {
                normal: {
                  borderColor: 'rgba(0, 0, 0, 0.2)'
                },
                emphasis: {
                  areaColor: '#FF6347'
                }
              }
            }
          ]
        })
      }
    }
  }
</script>