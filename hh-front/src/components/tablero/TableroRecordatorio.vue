<template>
  <div class="row q-mt-lg justify-end">
    <!-- <div v-for="" key=""> -->
      <q-banner rounded class="bg-orange text-white">
        You have lost connection to the internet. This app is offline.

        <template v-slot:action>
          <q-icon class="q-mr-sm" name="visibility" size="20px" />
          <q-icon name="close" size="20px" />
        </template>
      </q-banner>
    <!-- </div> -->
  </div>
</template>

<script>
import { ref, reactive, defineComponent } from 'vue'
import { eventoService } from 'src/services/evento_service'
import { EventoCreation } from 'src/models/creation/evento_creation'
import { useQuasar, date } from 'quasar'
import { ayuda } from 'src/helpers/ayuda'
import { notificarService } from 'src/helpers/notificar_service'

export default defineComponent({
  name: 'Calendario',
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())
    const eventoCreation = reactive(new EventoCreation())
    const events = ref([])
    const mostrarRecordatorios = ref(false)
    const verEventoDialog = ref(false)
    const titulo = ref(null)

    afBuscarEventos()

    async function afBuscarEventos () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await eventoService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await eventoService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          events.value = resultado.data
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
        $q.loading.hide()
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        $q.loading.hide()
      }
    }

    function fFormatearFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fLimpiarFormulario () {
      eventoCreation.descripcion = null
      eventoCreation.fecha = null
      eventoCreation.habilitada = null
      eventoCreation.nombre = null
      eventoCreation.recordatorioDias = null
      eventoCreation.recordatorioFecha = null

      mostrarRecordatorios.value = false

      eventoCreation.id = null
      eventoCreation.creadorId = null
      eventoCreation.creador = null
      eventoCreation.creada = null
      eventoCreation.modificadorId = null
      eventoCreation.modificador = null
      eventoCreation.modificada = null
      eventoCreation.eliminadorId = null
      eventoCreation.eliminador = null
      eventoCreation.eliminada = null
    }

    function fMostrarVerEvento (evento) {
      console.table(evento)
      titulo.value = 'Ver evento ' + evento.nombre
      eventoCreation.bgcolor = evento.bgcolor
      eventoCreation.descripcion = evento.descripcion
      eventoCreation.fecha = evento.fecha
      eventoCreation.habilitada = evento.habilitada
      eventoCreation.nombre = evento.nombre
      eventoCreation.recordatorioDias = evento.recordatorioDias
      eventoCreation.recordatorioFecha = evento.recordatorioFecha

      eventoCreation.id = evento.id
      eventoCreation.creadorId = evento.creadorId
      eventoCreation.creador = evento.creador
      eventoCreation.creada = evento.creada
      eventoCreation.modificadorId = evento.modificadorId
      eventoCreation.modificador = evento.modificador
      eventoCreation.modificada = evento.modificada
      eventoCreation.eliminadorId = evento.eliminadorId
      eventoCreation.eliminador = evento.eliminador
      eventoCreation.eliminada = evento.eliminada
    }

    function fObtenerEventos (fondo) {
      switch (fondo) {
        case 'Presupuesto':
          fondo = 'background: #8338ec'
          break
        case 'Seguro':
          fondo = 'background: #ff006e'
          break
        case 'Licencia':
          fondo = 'background: #ff006e'
          break
        case 'Viaje':
          fondo = 'background: #3a86ff'
          break
        case 'Vencimiento':
          fondo = 'background: #ff006e'
          break
        case 'Evento':
          fondo = 'background: #9e0059'
          break
        default:
          fondo = ''
          break
      }
      return fondo
    }

    return {
    }
  }
})
</script>

<style scoped>
.q-icon {
  cursor: pointer;
}
</style>
