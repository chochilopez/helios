<template>
  <div class="q-ma-md">
    <TableroCalendario v-if="autoridad === 'admin' || autoridad === 'usuario' " />
    <div class="row justify-center">
      <div class="col-md-6 tablero_estadistica"><TableroEstadistica /></div>
      <div class="col-md-6 tablero_recordatorio"><TableroRecordatorio /></div>
    </div>
  </div>
</template>

<script>
import { onBeforeRouteLeave } from 'vue-router'
import { useQuasar, QSpinnerCube } from 'quasar'
import { ref, onMounted } from 'vue'
import { ayuda } from 'src/helpers/ayuda'
import TableroCalendario from 'src/components/tablero/TableroCalendario.vue'
import TableroEstadistica from 'src/components/tablero/TableroEstadistica.vue'
import TableroRecordatorio from 'src/components/tablero/TableroRecordatorio.vue'

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
    TableroEstadistica,
    TableroCalendario,
    TableroRecordatorio
  }
}
</script>

<style scoped>
@media screen and (max-width: 1023.99px) {
  .tablero_recordatorio {
    order:1
  }
  .tablero_estadistica {
    order:2
  }
}
</style>
