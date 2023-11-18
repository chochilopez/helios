<template>
  <div class="q-ma-md">
    <ViajeContador />
    <ViajeListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import ViajeContador from 'src/components/viaje/ViajeContador.vue'
import ViajeListado from 'src/components/viaje/ViajeListado.vue'
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
    ViajeContador,
    ViajeListado
  }
}
</script>
