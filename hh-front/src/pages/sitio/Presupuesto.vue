<template>
  <div class="q-ma-md">
    <ViajesPresupuestosContador />
    <ViajesPresupuestosListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import ViajesPresupuestosListado from 'src/components/sitio/ViajesPresupuestosListado.vue'
import { onMounted } from 'vue'
import ViajesPresupuestosContador from 'src/components/sitio/ViajesPresupuestosContador.vue'

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
    ViajesPresupuestosListado,
    ViajesPresupuestosContador
  }
}
</script>
