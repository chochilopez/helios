<template>
  <div class="q-ma-md">
    <FacturacionFacturacionContador />
    <FacturacionFacturacionListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import FacturacionFacturacionContador from '../../components/sitio/FacturacionFacturacionContador.vue'
import FacturacionFacturacionListado from '../../components/sitio/FacturacionFacturacionListado.vue'
import { onMounted } from 'vue'

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
    FacturacionFacturacionContador,
    FacturacionFacturacionListado
  }
}
</script>
