<template>
  <div class="q-ma-md">
    <ClientesSaldosCtasCtesContador />
    <ClientesSaldosCtasCtesListado />
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { onMounted } from 'vue'
import ClientesSaldosCtasCtesContador from 'src/components/sitio/ClientesSaldosCtasCtesContador.vue'
import ClientesSaldosCtasCtesListado from 'src/components/sitio/ClientesSaldosCtasCtesListado.vue'

export default {
  components: { ClientesSaldosCtasCtesContador, ClientesSaldosCtasCtesListado },
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
