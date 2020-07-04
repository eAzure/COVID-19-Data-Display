<template>
  <div>
    <navmenu/>
    <router-view/>
  </div>
  
</template>

<script>
import navmenu from './components/PageComponents/navmenu.vue'
export default {
  name: 'App',
  components:{
    navmenu
  }
}
</script>

<style>
body{
  margin: 0;
  padding: 0;
  overflow: hidden;
  zoom: 1;
}
</style>