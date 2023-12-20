<template>
  <div class="row q-col-gutter-sm ">
    <div class="col-12">
      <GraficoSplineArea />
    </div>
    <div class="col-12">
      <GraficoArea />
    </div>
    <div class="col-12">
      <GraficoSimpleDonut />
    </div>
    <div class="col-12">
      <GraficoTreeMap />
    </div>
  </div>
</template>

<script>
import GraficoArea from './grafico/GraficoArea.vue'
import GraficoSimpleDonut from './grafico/GraficoSimpleDonut.vue'
import GraficoSplineArea from './grafico/GraficoSplineArea.vue'
import GraficoTreeMap from './grafico/GraficoTreeMap.vue'

export default {
  data: function () {
    return {
    }
  },
  components: {
    GraficoArea,
    GraficoSimpleDonut,
    GraficoSplineArea,
    GraficoTreeMap
  }
}
</script>
