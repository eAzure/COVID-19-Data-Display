<template>
  <div>
    <relationGraph :widthRelationGraph="widthRelationGraph" :heightRelationGraph="heightRelationGraph"></relationGraph>
  </div>
</template>

<script>
import relationGraph from "../PageComponents/relationGraph.vue"
export default {
  name: 'tripData',
  components:{
    relationGraph
  },
  data () {
    return {
      widthRelationGraph:"100%",
      heightRelationGraph:"660px"
    }
  },
  methods:{
      
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
