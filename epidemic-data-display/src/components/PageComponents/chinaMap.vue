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
      height:{type:String,default:"590px"},
      dateData:String
    },
    data() {
      return {
        chart: null,
        data: null,
        theSelectedData:'中国',
        option:{ // 进行相关配置
          backgroundColor: "#1e1e1e",
          //图表标题
          title:{
            text:'中国疫情数据',
            subtext:'the epidemic data of China',
            top:"30px",
            left:'center',
            textStyle:{color:'#fff'}
          },
          tooltip: {  //提示框组件
            trigger: 'item',
            formatter: '{b}<br/>累计确诊：{c} ',
            transitionDuration:0//防止页面因tooltip抖动
          }, // 鼠标移到图里面的浮动提示框
          //图列显示柱
          visualMap: {
            type:'piecewise',
            show: true,
            min: 0,
            left:30,
            max: 3000,
            //text: ['High', 'Low'],
            textStyle:{
              color:'#fff'
            },
            realtime: true,
            calculable: true,
            //color: ['red', 'yellow', 'lightskyblue'],
            //注意这里的区间设置，一定要将所有值都包含进来
            pieces: [
              { gte: 10000, color: '#b80909' },
              { gte: 1000, lte: 9999, color: '#e64546' },
              { gte: 100, lte: 999, color: '#f57567' },
              { gte: 10, lte: 99, color: '#ff9985' },
              { gte: 1, lte: 9, color: '#ffe5db' },
              { value: 0, color: '#fff' },
            ],
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
              name: '累计确诊', // 浮动框的标题
              type: 'map',
              mapType:'china',
              roam:true,
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
                  areaColor: '#FF6347'//选中时的颜色
                }
              }
            }
          ]
        }
      };
    },
    watch:{
      dateData:{
        handler:function(newValue,oldValue){
          if(newValue){
            console.log(newValue)
            var date=newValue.replace('-','')
            date=date.replace('-','')
            console.log(date)
            this.option.title.text='中国疫情数据 '+date
            //在这里获取数据
            var _this=this;
            this.axios.get('http://localhost:8080/user/findAllConDateData?dateId='+date)
            .then(function(res){
              console.log(res.data)
              _this.option.series[0].data=res.data
              _this.initChart();
            })
            // this.option.series[0].data=[{
            //     "name": "北京",
            //     "value": 599
            //   }]
          }
        }
      },
      option:{
        handler:function(newValue,oldValue){
          if(this.chart){
            if(newValue){
              this.chart.setOption(newValue,true)
            }else{
              this.chart.setOption(oldValue,true)
            }
          }else{
            this.initChart()
          }
        },
        deep:true
      }
    },
    mounted() {
      var date=this.dateData.replace('-','')
      date=date.replace('-','')
      console.log(date)
      this.option.title.text='中国疫情数据 '+date
      //在这里获取数据
      var _this=this;
      this.axios.get('http://localhost:8080/user/findAllConDateData?dateId='+date)
      .then(function(res){
        console.log(res.data)
        _this.option.series[0].data=res.data
        _this.initChart();
      })
      //this.initChart();
    },
    beforeDestroy() {
      if (!this.chart) {
        return;
      }
      this.chart.dispose();
      this.chart = null;
    },
    methods: {
      initChart() {
        this.chart= echarts.init(this.$refs.myEchart); //这里是为了获得容器所在位置    
        window.onresize = echarts.init(this.$refs.myEchart).resize;
        //this.option.series[0].data=this.data
        this.chart.setOption(this.option)
        //增加地图选中事件
        if(this.chart._$handlers.click){
          this.chart._$handlers.click.length = 0;
        }
        var _this=this;
        this.chart.on('click',function(params){
          console.log("params:"+params.componentType)
          if(_this.theSelectedData=='中国'){
            _this.theSelectedData=params.name
            //给父组件传值
            console.log('点击选中')
            _this.$emit('func',_this.theSelectedData)
          }else{
            _this.theSelectedData='中国'
            //给父组件传值
            console.log('取消选中')
            _this.$emit('func',_this.theSelectedData)
          }
        })
      }
    }
  }
</script>