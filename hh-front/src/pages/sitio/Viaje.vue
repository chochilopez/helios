<template>
  <ViajesViajesContador />
  <ViajesViajesListado />
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import ViajesViajesContador from '../../components/sitio/ViajesViajesContador.vue'
import ViajesViajesListado from '../../components/sitio/ViajesViajesListado.vue'
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
    ViajesViajesContador,
    ViajesViajesListado
  }
}
</script>
