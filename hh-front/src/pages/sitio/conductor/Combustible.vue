<template>
  <div class="q-ma-md">
    <CombustibleListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { onMounted } from 'vue'
import CombustibleListado from 'src/components/conductor/CombustibleListado.vue'

export default {
  components: { CombustibleListado },
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
  }
}
</script>
