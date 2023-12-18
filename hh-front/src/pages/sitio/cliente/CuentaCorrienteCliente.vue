<template>
  <div class="q-ma-md">
    <CuentaCorrienteClienteContador />
    <CuentaCorrienteClienteListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { onMounted } from 'vue'
import CuentaCorrienteClienteContador from 'src/components/cliente/CuentaCorrienteClienteContador.vue'
import CuentaCorrienteClienteListado from 'src/components/cliente/CuentaCorrienteClienteListado.vue'

export default {
  components: { CuentaCorrienteClienteContador, CuentaCorrienteClienteListado },
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
