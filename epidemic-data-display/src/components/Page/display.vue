<!--首页-->
<template>
  <div>
    <el-row type="flex"  class="row-bg" justify="space-center" :gutter="5">
      <el-col :span="10"><chinaMap :dateData="dateData" :width="width" :height="height"></chinaMap></el-col>
      <el-col :span="10"><worldMap :dateData="dateData" :width="width" :height="height"></worldMap></el-col>
      <el-col :span="4">
        <el-date-picker
        @change="timeChange"
        v-model="time"
        type="date"
        placeholder="选择日期"
        value-format="yyyy-MM-dd"
        :picker-options="expireTimeOption">
        </el-date-picker>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import echartsTest from "../PageComponents/echartsTest"
import worldMap from "../PageComponents/worldMap"
import chinaMap from "../PageComponents/chinaMap"
export default {
  name: 'display',
  components:{
    echartsTest,
    worldMap,
    chinaMap
  },
  data () {
    return {
      msg: 'Data of COVID-19',
      time:'null',
      dateData:'null',
      //定义时间区间
      expireTimeOption: {
		    disabledDate(date) {
          //禁用当天及以后的日期
				  return date.getTime() > Date.now()-24*3600*1000;
		    }
      },
      width:"100%",
      height:"620px",
      chinaData:null
    }
  },
  beforeMount(){
    var currentdate=this.getCurrentDate();
    this.dateData=currentdate;
    this.time=currentdate;
  },
  mounted(){

  },
  created(){
    
  },
  methods:{
    timeChange(){
      //console.log(this.time)
      this.dateData=this.time
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
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.el-row {
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-clow{
  height: 100%;
}
.row-bg {
  padding: 20px 0;
  background-color: #1e1e1e;
}
</style>
