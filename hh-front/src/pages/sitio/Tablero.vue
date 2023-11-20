<template>
  <TableroContador class="q-ma-lg" v-if="autoridad === 'admin' || autoridad === 'usuario' "/>
  <TableroGrafico class="q-ma-lg" v-if="autoridad === 'admin' || autoridad === 'usuario' "/>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { ref, onMounted } from 'vue'
import TableroContador from 'src/components/TableroContador.vue'
import TableroGrafico from 'src/components/TableroGrafico.vue'
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
    TableroContador,
    TableroGrafico
  }
}
</script>
