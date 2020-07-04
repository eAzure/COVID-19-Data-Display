<template>
  <div>
    <el-row>
        <el-col :span="4"><pieChart :dealedDate="dealedDate" :todayDate="todayDate" :isProOrCount="isProOrCount" :widthPie="widthPie" :heightPie="heightPie"></pieChart></el-col>
        <el-col :span="15"><chinaMap @func="getMsgFromChild" :dateData="dateData" :width="width" :height="height"></chinaMap></el-col>
        <el-col :span="5"><bar-chart :todayDate="todayDate" :widthBar="widthBar" :heightBar="heightBar" :isProOrCount="isProOrCount"></bar-chart></el-col>
    </el-row>
    <chinaLineChart @func="getDateFromChild" :provinceName="provinceName" :widthLine="widthLine" :heightLine="heightLine"></chinaLineChart>
  </div>
</template>
<script>
import chinaMap from "../PageComponents/chinaMap"
import chinaLineChart from "../PageComponents/chinaLineChart"
import barChart from "../PageComponents/barChart.vue"
import pieChart from "../PageComponents/pieChart.vue"
export default {
  name: 'chinaData',
  components:{
      chinaMap,
      chinaLineChart,
      barChart,
      pieChart
    },
  data () {
    return {
      msg: 'Welcome to Your ChinaData',
      dateData:'null',
      width:"100%",
      widthLine:"100%",
      height:"450px",
      heightLine:"210px",
      provinceName:"中国",
      //用于柱状图
      widthBar:"100%",
      heightBar:"450px",
      isProOrCount:"中国",
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
    //从子组件地图中接受选过来的值
    getMsgFromChild(data){
        console.log(data)
        this.open(data)
        this.provinceName=data
        //从该值便可以切换下面的折线图，以让其显示为具体某个省份的数据，先将后台结构化
    },//显示提醒框，提醒选中还是取消
    open(data) {
      if(data!='中国'){
        var message="已选中"+data
        this.$message(message);
      }else{
        this.$message("取消选中")
      }
    },
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