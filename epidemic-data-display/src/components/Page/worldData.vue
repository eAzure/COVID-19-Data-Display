<template>
  <div>
    <el-row>
        <el-col :span="4"><pieChart :dealedDate="dealedDate" :todayDate="todayDate" :isProOrCount="isProOrCount" :widthPie="widthPie" :heightPie="heightPie"></pieChart></el-col>
        <el-col :span="15"><worldMap @func="getMsgFromChild" :dateData="dateData" :width="width" :height="height"></worldMap></el-col>
        <el-col :span="5"><bar-chart :todayDate="todayDate" :widthBar="widthBar" :heightBar="heightBar" :isProOrCount="isProOrCount"></bar-chart></el-col>
    </el-row>
    <worldLineChart @func="getDateFromChild" :countryName="countryName" :widthLine="widthLine" :heightLine="heightLine"></worldLineChart>
  </div>
</template>
<script>
import worldMap from "../PageComponents/worldMap"
import worldLineChart from "../PageComponents/worldLineChart"
import barChart from "../PageComponents/barChart.vue"
import pieChart from "../PageComponents/pieChart.vue"
export default {
  name: 'worldData',
  components:{
    worldMap,
    worldLineChart,
    barChart,
    pieChart
  },
  data () {
    return {
      dateData:'null',
      width:"100%",
      height:"450px",
      widthLine:"100%",
      heightLine:"210px",
      countryName:"世界",
      //用于柱状图
      widthBar:"100%",
      heightBar:"450px",
      isProOrCount:"世界",
      todayDate:null,
      //用于饼图
      widthPie:"100%",
      heightPie:"450px",
      dealedDate:null
    }
  },
  beforeMount(){
    this.dateData=this.getCurrentDate()
    this.todayDate=parseInt(this.getCurrentDateForChart())
    this.dealedDate=this.dealDateForPie(this.todayDate)
  },
  methods:{
    //为饼状图所用
    dealDateForPie(data){
      var temp=parseInt(data)
      temp=temp-20200000
      var month=Math.floor(temp/100)
      var day=temp%100
      var date=month+'月'+day+'日'
      return date
    },
    //为柱状图所用
    getCurrentDateForChart(){
      var curDate=new Date();
      var date = new Date(curDate.getTime()-24*60*60*1000);
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = year  + month + strDate;
      console.log("cur:"+currentdate);
      return currentdate;
    },
    //获取前一天的日期
    getCurrentDate(){
      var curDate=new Date();
      var date = new Date(curDate.getTime()-24*60*60*1000);
      var seperator1 = "-";
      var year = date.getFullYear();
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate = year + seperator1 + month + seperator1 + strDate;
      console.log("cur:"+currentdate);
      return currentdate;
    },
    //获取选中的国家的数据，并展示出来
    getMsgFromChild(data){
      this.open(data)
      this.countryName=data
    },//展示从子组件中获取到的信息
    open(data){
      if(data!='世界'){
        var message="已选中"+data
        this.$message(message)
      }else{
        this.$message("取消选中")
      }
    },//获取时间轴选中的时间
    getDateFromChild(data){
      this.dateData=data
      var mes=data-20200000
      var month=Math.floor(mes/100)
      var day=mes%100
      var showMes=month+'月'+day+'日'
      this.$message("已选中"+showMes+"数据")
    }
  }
}
</script>
<style scoped>
  .el-row {
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>