<template>
  <TableroContador class="q-ma-lg" />
  <TableroGrafico class="q-ma-lg" />
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { onMounted } from 'vue'
import TableroContador from 'src/components/TableroContador.vue'
import TableroGrafico from 'src/components/TableroGrafico.vue'

export default {
  setup () {
    const $q = useQuasar()

    onMounted(() => {
      $q.loading.hide()
    })

    onBeforeRouteLeave((to, from, next) => {
      $q.loading.show({
        spinner: QSpinnerCube,
        message: 'Cargando...',
        boxClass: 'paleta1-color4 paleta1-fondo1',
        spinnerColor: 'paleta1-color5'
      })
      next()
    })
    return {}
  },
  components: {
    TableroContador,
    TableroGrafico
  }
}
</script>
