<template>
  <div class="q-ma-md">
    <CalendarioCalendario v-if="autoridad === 'admin' || autoridad === 'usuario' " />
    <TableroGraficos />
    <!-- <TableroGraficos v-if="autoridad === 'admin' || autoridad === 'usuario' "/> -->
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { ref, onMounted } from 'vue'
import { ayuda } from 'src/helpers/ayuda'
import CalendarioCalendario from 'src/components/calendario/CalendarioCalendario.vue'
import TableroGraficos from 'src/components/tablero/TableroGraficos.vue'

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
    TableroGraficos,
    CalendarioCalendario
  }
}
</script>
