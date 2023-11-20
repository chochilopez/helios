<template>
  <div class="q-ma-md">
    <PresupuestoContador v-if="autoridad === 'admin' || autoridad === 'usuario' "/>
    <PresupuestoListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import PresupuestoListado from 'src/components/viaje/PresupuestoListado.vue'
import { ref, onMounted } from 'vue'
import PresupuestoContador from 'src/components/viaje/PresupuestoContador.vue'
import { ayuda } from 'src/helpers/ayuda'

export default {
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())

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
    return {
      autoridad
    }
  },
  components: {
    PresupuestoListado,
    PresupuestoContador
  }
}
</script>
