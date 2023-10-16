import TableroGrafico from 'src/components/sitio/TableroGrafico.vue';
<template>
  <TableroContador />
  <TableroGrafico />
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { onMounted } from 'vue'
import TableroContador from '../../components/sitio/TableroContador.vue'
import TableroGrafico from '../../components/sitio/TableroGrafico.vue'

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
