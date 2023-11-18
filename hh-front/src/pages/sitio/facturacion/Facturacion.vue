<template>
  <div class="q-ma-md">
    <FacturacionContador />
    <FacturacionListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import FacturacionContador from 'src/components/facturacion/FacturacionContador.vue'
import FacturacionListado from 'src/components/facturacion/FacturacionListado.vue'
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
    FacturacionContador,
    FacturacionListado
  }
}
</script>
