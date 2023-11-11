<template>
  <ClientesDireccionesContador />
  <ClientesDireccionesListado />
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { onMounted } from 'vue'
import ClientesDireccionesContador from 'src/components/sitio/ClientesDireccionesContador.vue'
import ClientesDireccionesListado from 'src/components/sitio/ClientesDireccionesListado.vue'

export default {
  components: { ClientesDireccionesContador, ClientesDireccionesListado },
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
