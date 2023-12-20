<template>
  <div class="row q-mt-lg justify-end" v-for="(evento, index) in events" :key="index">
    <q-banner rounded class="text-white" :class="fObtenerFondo(evento.nombre)">
      <p>{{ evento.nombre }}</p>
      <p>{{ evento.descripcion }}</p>
      <template v-slot:action>
        <q-btn flat color="white" label="Ver" @click="fVerEvento(evento)" />
        <q-btn flat color="white" label="Modificar" @click="fModificarEvento(evento)" />
      </template>
    </q-banner>
  </div>

  <q-dialog v-model="verEventoDialog">
    <q-card style="max-width: 650px; min-width: 400px;">
      <q-card-section class="row items-center" :class="fObtenerFondo(eventoCreation.nombre)">
        <div class="text-h6 text-white">Ver evento {{ eventoCreation.nombre }}</div>
        <q-space />
        <q-btn class="text-white" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <q-card-section>
          <div class="row justify-around items-center bg-grey-2 q-pa-sm" style="height: 50px;">
            <div class="col">Tipo evento</div>
            <div class="col text-center">{{ eventoCreation.nombre }}</div>
          </div>
          <div class="row justify-around items-center  q-pa-sm" style="height: 50px;">
            <div class="col">Fecha del evento</div>
            <div class="col text-center">{{ fFormatearFecha(eventoCreation.fecha) }}</div>
          </div>
          <div class="row justify-around items-center bg-grey-2 q-pa-sm" style="height: 50px;">
            <div class="col">Descripción del evento:</div>
            <div class="col text-center">{{ eventoCreation.descripcion }}</div>
          </div>
          <div class="row justify-around items-center q-pa-sm" style="height: 50px;">
            <div class="col">¿Evento habilitado?</div>
            <div class="col text-center">
              <q-chip v-if="eventoCreation.habilitada" color="teal" text-color="white" icon="done">
                Habilitado
              </q-chip>
              <q-chip v-if="!eventoCreation.habilitada" color="red" text-color="white" icon="close">
                Deshabilitado
              </q-chip>
            </div>
          </div>
          <div class="row justify-around items-center bg-grey-2 q-pa-sm" v-if="eventoCreation.recordatorioDias !== null" style="height: 50px;">
            <div class="col">Dias previos</div>
            <div class="col text-center">
              <span>{{ eventoCreation.recordatorioDias }}</span>
            </div>
          </div>
          <div class="row justify-around items-center q-pa-sm" v-if="eventoCreation.recordatorioFecha !== null" style="height: 50px;">
            <div class="col">Recordatorio</div>
            <div class="col text-center">
              <span>{{ fFormatearFecha(eventoCreation.recordatorioFecha) }}</span>
            </div>
          </div>
          <div class="row justify-around items-center bg-grey-2 q-pa-sm" v-if="eventoCreation.creador !== null" style="height: 50px;">
            <div class="col">Creador</div>
            <div class="col text-center">
              <span>{{ eventoCreation.creador }}</span>
            </div>
          </div>
          <div class="row justify-around items-center q-pa-sm" v-if="eventoCreation.creada !== null" style="height: 50px;">
            <div class="col">Creado</div>
            <div class="col text-center">
              <span>{{ fFormatearFecha(eventoCreation.creada) }}</span>
            </div>
          </div>
          <div class="row justify-around items-center bg-grey-2 q-pa-sm" v-if="eventoCreation.modificador !== null" style="height: 50px;">
            <div class="col">Modificador</div>
            <div class="col text-center">
              <span>{{ eventoCreation.modificador }}</span>
            </div>
          </div>
          <div class="row justify-around items-center q-pa-sm" v-if="eventoCreation.modificada !== null" style="height: 50px;">
            <div class="col">Modificado</div>
            <div class="col text-center">
              <span>{{ fFormatearFecha(eventoCreation.modificada) }}</span>
            </div>
          </div>
          <div class="row justify-around items-center bg-grey-2 q-pa-sm" v-if="eventoCreation.eliminador !== null" style="height: 50px;">
            <div class="col">Eliminador</div>
            <div class="col text-center">
              <span>{{ eventoCreation.eliminador }}</span>
            </div>
          </div>
          <div class="row justify-around items-center q-pa-sm" v-if="eventoCreation.eliminada !== null" style="height: 50px;">
            <div class="col">Eliminado</div>
            <div class="col text-center">
              <span>{{ fFormatearFecha(eventoCreation.eliminada) }}</span>
            </div>
          </div>
          <div class="row justify-end q-pa-md">
            <q-btn class="paleta2-color2 bg-white" v-close-popup icon="close" ripple>
              Cerrar
            </q-btn>
          </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="modificarEventoDialog">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Modificar evento {{ eventoCreation.nombre }}</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-accent" />
      </div>
      <q-card-section>
        <q-form v-on:submit.prevent="fGuardarEvento">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="eventoCreation.fecha"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Día del evento"
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="eventoCreation.fecha" mask="DD-MM-YYYY" :locale="myLocale">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="OK" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="eventoCreation.nombre"
                :rules="[reglas.requerido, reglas.min3, reglas.max16]"
                outlined
                dense
                clearable
                label="Nombre evento"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md text-center">
              <q-toggle
                v-model="mostrarRecordatorios"
                icon="alarm"
              />
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                :disable="!mostrarRecordatorios"
                class="nuevo-input"
                outlined
                dense
                clearable
                v-model="eventoCreation.recordatorioDias"
                :rules="[reglas.requerido]"
                :options="recordatorios"
                label="Recordar días antes"
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="eventoCreation.descripcion"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              />
            </div>
          </div>
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" v-close-popup icon="close" ripple>
              Cerrar
            </q-btn>
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple>
              Finalizar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, reactive, defineComponent } from 'vue'
import { eventoService } from 'src/services/evento_service'
import { EventoCreation } from 'src/models/creation/evento_creation'
import { useQuasar, date } from 'quasar'
import { ayuda } from 'src/helpers/ayuda'
import { notificarService } from 'src/helpers/notificar_service'
import { reglasValidacion } from 'src/helpers/reglas_validacion'

export default defineComponent({
  name: 'Calendario',
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())
    const eventoCreation = reactive(new EventoCreation())
    const events = ref([])
    const modificarEventoDialog = ref(false)
    const mostrarRecordatorios = ref(false)
    const reglas = reactive(reglasValidacion.reglas)
    const verEventoDialog = ref(false)

    afBuscarRecordatorios()

    async function afBuscarRecordatorios () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await eventoService.spfBuscarTodasPorRecordatorioActivoConEliminadas()
        } else {
          resultado = await eventoService.spfBuscarTodasPorRecordatorioActivo()
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

    async function afGuardarEvento () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await eventoService.spfGuardar(eventoCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el evento.')
        }
      } catch (err) {
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
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

    function fCopiarEvento (evento) {
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

    function fFormatearFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarEvento () {
      if (date.getDateDiff(Date.parse(ayuda.fFormatearDeDatePicker(eventoCreation.fecha)), Date.now(), 'days') < 1) {
        notificarService.notificarAlerta('La fecha del evento no puede ser pasada.')
      } else {
        afGuardarEvento().then(() => {
          modificarEventoDialog.value = false
          afBuscarRecordatorios()
        })
      }
    }

    function fLimpiarFormulario () {
      eventoCreation.descripcion = null
      eventoCreation.fecha = null
      eventoCreation.habilitada = null
      eventoCreation.nombre = null
      eventoCreation.recordatorioDias = null
      eventoCreation.recordatorioFecha = null

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

    function fModificarEvento (evento) {
      fLimpiarFormulario()
      fCopiarEvento(evento)
      if (eventoCreation.recordatorioFecha !== null) {
        mostrarRecordatorios.value = true
      }
      modificarEventoDialog.value = true
    }

    function fVerEvento (evento) {
      fLimpiarFormulario()
      fCopiarEvento(evento)
      verEventoDialog.value = true
    }

    function fObtenerFondo (fondo) {
      switch (fondo) {
        case 'Presupuesto':
          fondo = 'presupuesto'
          break
        case 'Seguro':
          fondo = 'seguro'
          break
        case 'Licencia':
          fondo = 'licencia'
          break
        case 'Viaje':
          fondo = 'viaje'
          break
        case 'Vencimiento':
          fondo = 'vencimiento'
          break
        case 'Evento':
          fondo = 'evento'
          break
        default:
          fondo = 'default'
          break
      }
      return fondo
    }

    return {
      eventoCreation,
      events,
      modificarEventoDialog,
      mostrarRecordatorios,
      recordatorios: ['1', '7', '15', '30', '60', '120'],
      reglas,
      verEventoDialog,
      fFormatearFecha,
      fGuardarEvento,
      fModificarEvento,
      fVerEvento,
      fObtenerFondo
    }
  }
})
</script>

<style scoped>
.q-banner {
  width: 100%;
}
.q-banner p:nth-child(1) {
  margin: 0 0 5px 0;
  font-weight: 600;
}
.q-banner p:nth-child(2) {
  margin: 0;
}
.viaje {
  background: #3a86ff
}
.presupuesto {
  background: #8338ec
}
.seguro {
  background: #ff006e
}
.licencia {
  background: #ff006e
}
.vencimiento {
  background: #ff006e
}
.evento {
  background: #9e0059
}
.default {
  background: #9e0059
}
</style>
