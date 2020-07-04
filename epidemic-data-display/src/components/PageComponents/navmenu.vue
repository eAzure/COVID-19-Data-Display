<template>
<el-menu
  :default-active="$route.path"
  class="el-menu-demo"
  mode="horizontal"
  @select="handleSelect"
  background-color="#1e1e1e"
  text-color="#fff"
  active-text-color="#ffd04b"
  :router='true'>
  <el-menu-item index="/">首页</el-menu-item>
  <el-submenu index="2">
    <template slot="title">分类数据</template>
    <el-menu-item index="/chinaData">中国疫情数据</el-menu-item>
    <el-menu-item index="/worldData">世界疫情数据</el-menu-item>
    <el-menu-item index="/tripData">感染者出行数据</el-menu-item>
  </el-submenu>
  <el-menu-item index="/data_download">数据下载</el-menu-item>
</el-menu>
</template>

<script>
  export default {
    data() {
      return {
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
</script>
