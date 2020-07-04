<template>
    <el-container style="height: 100%; border: 1px solid #eee">
  <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
    <el-menu default-active="1-1"
    @select="handleSelect"
    active-text-color="#ffd04b">
      <el-submenu index="1">
        <template slot="title"><i class="el-icon-document"></i>全球疫情数据</template>
        <el-menu-item-group>
          <template slot="title">数据下载</template>
          <el-menu-item index="1-1">疫情数据</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title"><i class="el-icon-menu"></i>中国疫情数据</template>
        <el-menu-item-group>
          <template slot="title">数据下载</template>
          <el-menu-item index="2-1">疫情数据</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title"><i class="el-icon-position"></i>患者同程数据</template>
        <el-menu-item-group>
          <template slot="title">数据下载</template>
          <el-menu-item index="3-1">患者同程数据</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </el-aside>
  
  <el-container>
    <el-header style="text-align: right; font-size: 12px">
      <el-button type="primary" @click="exportFile">导出<i class="el-icon-download el-icon--right"></i></el-button>
    </el-header>
    
    <el-main>
      <el-table :data="tableData.slice((currentPage-1)*pageSize,currentPage*pageSize)" v-if="show" ref="tableDataRef" stripe border height="500px" style="width:100%">
        <el-table-column prop="dateId" label="日期" fixed="left"  width="150">
        </el-table-column>
        <el-table-column prop="countryName"  label="国家/地区名" fixed="left" width="150">
        </el-table-column>
        <el-table-column prop="continent" label="所在洲" width="150">
        </el-table-column>
        <el-table-column prop="confirmedCount" label="累计确诊" width="150">
        </el-table-column>
        <el-table-column prop="confirmedIncr" label="新增确诊人数" width="150">
        </el-table-column>
        <el-table-column prop="curedCount" label="累计治愈" width="150">
        </el-table-column>
        <el-table-column prop="curedIncr" label="新增治愈人数" width="150">
        </el-table-column>
        <el-table-column prop="currentConfirmedCount" label="现存确诊人数" width="150">
        </el-table-column>
        <el-table-column prop="currentConfirmedIncr" label="新增现存确诊人数"  width="150">
        </el-table-column>
        <el-table-column prop="deadCount" label="累计死亡"  width="150">
        </el-table-column>
        <el-table-column prop="deadIncr" label="新增死亡人数" width="150">
        </el-table-column>
        <el-table-column prop="suspectedCount" label="疑似人数"  width="150">
        </el-table-column>
        <el-table-column prop="suspectedCountIncr" label="新增疑似人数"  width="150">
        </el-table-column>
      </el-table>
      <el-table :data="tableData_ch.slice((currentPage-1)*pageSize,currentPage*pageSize)" v-if="showInver" ref="tableDataRef1" stripe border height="500px" style="width:100%">
        <el-table-column prop="dateId" label="日期" fixed="left" width="150">
        </el-table-column>
        <el-table-column prop="provinceName" label="省份" fixed="left" width="150">
        </el-table-column>
        <el-table-column prop="confirmedCount" label="累计确诊" width="150">
        </el-table-column>
        <el-table-column prop="confirmedIncr" label="新增确诊人数" width="150">
        </el-table-column>
        <el-table-column prop="curedCount" label="累计治愈" width="150">
        </el-table-column>
        <el-table-column prop="curedIncr" label="新增治愈人数" width="150">
        </el-table-column>
        <el-table-column prop="currentConfirmedCount" label="现存确诊人数" width="150">
        </el-table-column>
        <el-table-column prop="currentConfirmedIncr" label="新增现存确诊人数" width="150">
        </el-table-column>
        <el-table-column prop="deadCount" label="累计死亡" width="150">
        </el-table-column>
        <el-table-column prop="deadIncr" label="新增死亡人数" width="150">
        </el-table-column>
        <el-table-column prop="suspectedCount" label="疑似人数"  width="150">
        </el-table-column>
        <el-table-column prop="suspectedCountIncr" label="新增疑似人数"  width="150">
        </el-table-column>
      </el-table>
      <el-table :data="tableData_trip.slice((currentPage-1)*pageSize,currentPage*pageSize)" v-if="showInver1" ref="tableDataRef2" stripe border height="500px" style="width:100%">
        <el-table-column prop="t_date" label="日期" fixed="left" width="150">
        </el-table-column>
        <el-table-column prop="t_start" label="乘车时间（大约）" width="150">
        </el-table-column>
        <el-table-column prop="t_end" label="下车时间（大约）" width="150">
        </el-table-column>
        <el-table-column prop="t_type" label="出行类型" width="150">
        </el-table-column>
        <el-table-column prop="t_no" label="车次/车牌/航班号/场所" width="150">
        </el-table-column>
        <el-table-column prop="t_no_sub" label="车厢号" width="150">
        </el-table-column>
        <el-table-column prop="t_pos_start" label="起点" width="150">
        </el-table-column>
        <el-table-column prop="t_pos_end" label="终点" width="150">
        </el-table-column>
        <el-table-column prop="source" label="数据来源链接" width="150">
        </el-table-column>
        <el-table-column prop="who" label="数据来源名称" width="150">
        </el-table-column>
      </el-table>
      <div style="text-align: center;margin-top: 10px;">
        <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          :current-page="currentPage"
          :page-size="pageSize"
          @current-change="current_change">
        </el-pagination>
      </div>
    </el-main>
  </el-container>
</el-container>
</template>

<script>
export default {
    name:'data_download',
    components:{
    },
    data() {
      item:null
      return {
        item:{
          date: '2016-05-02',
          name: '王小虎',
          address: '上海市普陀区金沙江路 1518 弄',
          address1: '上海市普陀区金沙江路 1518 弄',
          address2: '上海市普陀区金沙江路 1518 弄',
          address3: '上海市普陀区金沙江路 1518 弄',
          address4: '上海市普陀区金沙江路 1518 弄',
          address5: '上海市普陀区金沙江路 1518 弄',
          address6: '上海市普陀区金沙江路 1518 弄'
        },
        tableData: [],//存放世界的数据
        tableData_ch:[],//存放中国的数据
        tableData_trip:[],//存放同程的数据
        total:0,
        pageSize:20,
        currentPage:1,
        key_Path:'1-1',
        show:true,//记录世界表格的关闭
        showInver:false,//记录中国表格的关闭
        showInver1:false//记录同程表格的关闭
      }
    },
    mounted(){
      //目前未对同程数据进行处理
      var _this=this;
      //获取世界数据
      this.axios.get('http://localhost:8080/user/findAllWorldData')
      .then(function(res){
        _this.tableData=res.data
        _this.total=Object.keys(res.data).length
      })
      //获取中国数据
      this.axios.get('http://localhost:8080/user/findAllChinaData')
      .then(function(res){
        _this.tableData_ch=res.data
      })
      //获取同程数据
      this.axios.get('http://localhost:8080/user/findAllTripData')
      .then(function(res){
        _this.tableData_trip=res.data
      })
      // this.tableData=Array(45).fill(this.item)
      // this.total=45
    },
    methods:{
      handleSelect(key, keyPath) {
        console.log(keyPath[1]);
        this.key_Path=keyPath[1];
        if(keyPath[1]=='1-1'){
          this.show=true
          this.showInver=false
          this.showInver1=false
          this.$nextTick(() => {
            this.$refs.tableDataRef.doLayout();
          });
          this.total=Object.keys(this.tableData).length
          this.currentPage=1
        }
        else if(keyPath[1]=='2-1'){
          this.show=false
          this.showInver=true
          this.showInver1=false
          this.$nextTick(() => {
            this.$refs.tableDataRef1.doLayout();
          });
          this.total=Object.keys(this.tableData_ch).length
          this.currentPage=1
        }else if(keyPath[1]=='3-1'){
          this.show=false
          this.showInver=false
          this.showInver1=true
          this.$nextTick(() => {
            this.$refs.tableDataRef2.doLayout();
          });
          this.total=Object.keys(this.tableData_trip).length
          this.currentPage=1
        }
      },
      current_change:function(currentPage){
        this.currentPage=currentPage
      },
      //文件下载
      exportFile:function(){
        if(this.key_Path=='1-1'){
          this.exportDataStream('http://localhost:8080/user/findAllWorldData','WorldData.json')
        }else if(this.key_Path=='2-1'){
          this.exportDataStream('http://localhost:8080/user/findAllChinaData','ChinaData.json')
        }else if(this.key_Path=='3-1'){
          this.exportDataStream('http://localhost:8080/user/findAllTripData','TripData.json')
        }
      },
      exportDataStream:function(url,filePath){
        var _this=this
        this.axios.post(url,{responseType:'blob'})
        .then(function(res){
          //Json这里非常重要
          console.log(res.data)
          const content=JSON.stringify(res.data)
          const blob=new Blob([content])
          const fileName=filePath
          if('download' in document.createElement('a')){
            const link=document.createElement('a')
            link.download=fileName
            link.style.display='none'
            link.href=URL.createObjectURL(blob)
            document.body.appendChild(link)
            link.click()
            URL.revokeObjectURL(link.href)
            document.body.removeChild(link)
          }else{
            navigator.msSaveBlob(blob,fileName)
          }
        })
      }
    }
}
</script>
<style>
.el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
.el-aside {
    color: #333;
}
</style>