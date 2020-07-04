<template>
  <div class="echarts">
    <div :style="{height:height,width:width}" ref="myEchart"></div>
  </div>
</template>
<script>
  import echarts from "echarts" //引入组件
  import '../../../node_modules/echarts/map/js/world.js' // 引入世界地图数据

  export default {
    name: "worldMap",
    props: {
      width: {type: String,default: "100%" },
      height: {type: String,default: "590px"},
      dateData:String
    },
    data() {
      return {
        chart: null,
        data:null,
        theSelectedData:'世界',
        option:{
          backgroundColor: "#1e1e1e",
          title: {    //地图显示标题
            text: '全球国家地区疫情数据',
            subtext: 'the epidemic data of the World',
            top:"30px",
            left: 'center',
            textStyle: {color: '#fff'}
          },
          visualMap: {   //图列显示柱
              //type: 'continuous',
              type:'piecewise',
              show:true,
              min: 0,
              left:30,
              max: 2000,
              //text:['High','Low'],
              textStyle:{
                color:'#fff'
              },
              realtime: true,
              calculable : true,
              //color: ['red','yellow','lightskyblue']
              pieces: [
                { gte: 100000, color: '#b80909' },
                { gte: 10000, lte: 99999, color: '#e64546' },
                { gte: 1000, lte: 9999, color: '#f57567' },
                { gte: 100, lte: 999, color: '#ff9985' },
                { gte: 1, lte: 99, color: '#ffe5db' },
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
          tooltip: {  //提示框组件
            trigger: 'item',
            formatter: '{b}<br/>累计确诊：{c} ',
            transitionDuration:0//防止页面因tooltip抖动
          },
          series: [{
            name:"确诊人数",
            type: 'map',
            mapType: 'world',
            roam: true,
            symbolSize: 12,
            label: {
              normal: {show: false},
              emphasis: {show: false}
            },
            itemStyle: {
              normal: {
                borderColor: 'rgba(0, 0, 0, 0.2)'
              },
              emphasis: {
                areaColor: '#FF6347'
              }
            }
          }]
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
            this.option.title.text='全球国家地区疫情数据 '+date
            //在这里获取数据
            var _this=this;
            this.axios.get('http://localhost:8080/user/findAllConDateWorldData?dateId='+date)
            .then(function(res){
              console.log(res.data)
              _this.option.series[0].data=res.data
              _this.initChart();
            })
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
      console.log("date:"+date)
      this.option.title.text='全球国家地区疫情数据 '+date
      //在这里获取数据
      var _this=this;
      this.axios.get('http://localhost:8080/user/findAllConDateWorldData?dateId='+date)
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
        this.chart = echarts.init(this.$refs.myEchart);
        window.onresize = echarts.init(this.$refs.myEchart).resize;
        // 把配置和数据放这里
        //this.option.series[0].data=this.data
        this.chart.setOption(this.option);
        //增加地图选中事件
        if(this.chart._$handlers.click){
          this.chart._$handlers.click.length = 0;
        }
        var _this=this;
        this.chart.on('click',function(params){
          console.log("params:"+params.componentType)
          if(_this.theSelectedData=='世界'){
            _this.theSelectedData=params.name
            //给父组件传值
            console.log('点击选中')
            _this.$emit('func',_this.theSelectedData)
          }else{
            _this.theSelectedData='世界'
            //给父组件传值
            console.log('取消选中')
            _this.$emit('func',_this.theSelectedData)
          }
        })
      }
    }
  }
</script>
