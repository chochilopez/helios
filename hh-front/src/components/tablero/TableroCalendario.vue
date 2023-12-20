<template>
  <div class="row q-mt-md justify-around">
    <div class="col-6">
      <div class="row justify-center">
        <q-btn class="paleta2-fondo2 text-white q-mx-sm" icon="arrow_left" ripple @click="onPrev()" />
        <q-btn class="paleta2-fondo2 text-white q-mx-sm" ripple @click="onToday()" label="Hoy" />
        <q-btn class="paleta2-fondo2 text-white q-mx-sm" icon-right="arrow_right" ripple @click="onNext()" />
      </div>
    </div>
    <div class="col-6 text-center">
      <div class="row justify-center">
        <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg q-mx-sm" ripple icon="add_circle" label="Evento" @click="fMostrarNuevoEvento()" />
      </div>
    </div>
  </div>
  <q-card class="font-5 no-shadow no-border">
    <div class="subcontent">
      <div class="row justify-center">
        <div class="full-width" style="display: flex;">
          <q-calendar-month
            ref="calendar"
            v-model="fechaSeleccionada"
            locale="es"
            :focus-type="['day']"
            month-label-size="lg"
            no-outside-days
            animated
            bordered
            focusable
            hoverable
            date-align="right"
            :weekdays="[1,2,3,4,5,6,0]"
            :day-min-height="60"
            :day-height="0"
            :style="tema"
            @click-day="fMostrarNuevoEventoFecha(fechaSeleccionada)"
            @click-date="fMostrarNuevoEventoFecha(fechaSeleccionada)"
          >
            <template #week="{ scope: { week, weekdays } }">
              <template v-for="evento in fObtenerEventos(week, weekdays)" :key="evento" >
                <div :class="fClaseEvento(evento)" :style="badgeStyles(evento, week.length)" @click="fMostrarVerEvento(evento.event)">
                  <div v-if="evento.event && evento.event.descripcion" class="title q-calendar__ellipsis">
                    {{ evento.event.nombre + (evento.event.time ? ' - ' + evento.event.time : '') }}
                    <q-tooltip style="background: #526D82; font-size: 14px">{{ evento.event.descripcion }}</q-tooltip>
                  </div>
                </div>
              </template>
            </template>
          </q-calendar-month>
        </div>
      </div>
    </div>
  </q-card>

  <div class="row justify-center">
    <div class="col-md-6 tablero_estadistica">
    </div>
    <div class="col-md-6 tablero_recordatorio">
      <div class="row q-mt-lg justify-end" v-for="(recordatorio, index) in recordatorios" :key="index">
        <q-banner rounded class="text-white" :class="fObtenerFondo(recordatorio.nombre)">
          <p>{{ recordatorio.nombre }}</p>
          <p>{{ recordatorio.descripcion }}</p>
          <template v-slot:action>
            <q-btn flat color="white" label="Ver" @click="fVerEvento(recordatorio)" />
            <q-btn flat color="white" label="Modificar" @click="fModificarEvento(recordatorio)" />
          </template>
        </q-banner>
      </div>
    </div>
  </div>

  <q-dialog v-model="verEventoDialog">
    <q-card style="max-width: 650px; min-width: 400px;">
      <q-card-section class="row items-center" :class="eventoCreation.bgcolor">
        <div class="text-h6 text-white">{{ titulo }}</div>
        <q-space />
        <q-btn class="text-white" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <q-card-section>
        <q-form v-on:submit.prevent="fModificarEvento">
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
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" v-close-popup icon="close" ripple>
              Cerrar
            </q-btn>
            <q-btn class="paleta2-fondo2 text-white" type="submit" v-close-popup icon-right="save" ripple>
              Modificar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="nuevoEventoDialog">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">{{ titulo }}</div>
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
                :options="recordatoriosCombo"
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
import {
  QCalendarMonth,
  daysBetween,
  isOverlappingDates,
  parsed,
  today,
  indexOf
} from '@quasar/quasar-ui-qcalendar/src/index.js'
import '@quasar/quasar-ui-qcalendar/src/QCalendarVariables.sass'
import '@quasar/quasar-ui-qcalendar/src/QCalendarTransitions.sass'
import '@quasar/quasar-ui-qcalendar/src/QCalendarMonth.sass'

import { ref, reactive, defineComponent } from 'vue'
import { eventoService } from 'src/services/evento_service'
import { EventoCreation } from 'src/models/creation/evento_creation'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { useQuasar, date } from 'quasar'
import { ayuda } from 'src/helpers/ayuda'
import { notificarService } from 'src/helpers/notificar_service'

export default defineComponent({
  name: 'Calendario',
  components: {
    QCalendarMonth
  },
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())
    const eventoCreation = reactive(new EventoCreation())
    const events = ref([])
    const fechaSeleccionada = ref(today())
    const mostrarRecordatorios = ref(false)
    const nuevoEventoDialog = ref(false)
    const recordatorios = ref([])
    const verEventoDialog = ref(false)
    const reglas = reactive(reglasValidacion.reglas)
    const titulo = ref(null)

    afBuscarEventos()
    afBuscarRecordatorios()

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
          recordatorios.value = resultado.data
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

    function fClaseEvento (evento) {
      if (evento.event !== undefined) {
        return {
          'my-event': true,
          'text-white': true,
          [`${evento.event.bgcolor}`]: true,
          'q-calendar__ellipsis': true
        }
      }
      return {
        'my-void-event': true
      }
    }

    function fFormatearFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarEvento () {
      if (date.getDateDiff(Date.parse(ayuda.fFormatearDeDatePicker(eventoCreation.fecha)), Date.now(), 'days') < 1) {
        notificarService.notificarAlerta('La fecha del evento no puede ser pasada.')
      } else {
        afGuardarEvento().then(() => {
          nuevoEventoDialog.value = false
          afBuscarEventos()
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

    function fModificarEvento () {
      titulo.value = 'Modificar Evento'
      eventoCreation.fecha = ayuda.fFormatearADatePicker(eventoCreation.fecha)
      if (eventoCreation.recordatorioFecha !== null) {
        mostrarRecordatorios.value = true
      }
      nuevoEventoDialog.value = true
    }

    function fMostrarVerEvento (evento) {
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

      verEventoDialog.value = true
    }

    function fMostrarNuevoEvento () {
      fLimpiarFormulario()
      titulo.value = 'Nuevo Evento'
      nuevoEventoDialog.value = true
    }

    function fMostrarNuevoEventoFecha (unaFecha) {
      eventoCreation.fecha = ayuda.fFormatearADatePicker(unaFecha)
      titulo.value = 'Nuevo Evento'
      nuevoEventoDialog.value = true
    }

    function fObtenerEventos (semana, weekdays) {
      const primerDiaSemana = parsed(semana[0].date + ' 00:00')
      const ultimoDiaSemana = parsed(semana[semana.length - 1].date + ' 23:59')

      const eventosSemana = []
      this.events.forEach((event, id) => {
        const inicio = parsed(event.fecha.slice(0, 10) + ' 00:00')
        const fin = inicio

        switch (event.nombre) {
          case 'Presupuesto':
            event.bgcolor = 'presupuesto'
            break
          case 'Seguro':
            event.bgcolor = 'seguro'
            break
          case 'Licencia':
            event.bgcolor = 'licencia'
            break
          case 'Vencimiento':
            event.bgcolor = 'vencimiento'
            break
          case 'Viaje':
            event.bgcolor = 'viaje'
            break
          default:
            event.bgcolor = 'default'
            break
        }

        if (isOverlappingDates(inicio, fin, primerDiaSemana, ultimoDiaSemana)) {
          const left = daysBetween(primerDiaSemana, inicio, true)
          const right = daysBetween(fin, ultimoDiaSemana, true)

          eventosSemana.push({
            id, // index event
            left, // Position initial day [0-6]
            right, // Number days available
            size: semana.length - (left + right), // Size current event (in days)
            event // Info
          })
        }
      })

      const events = []
      if (eventosSemana.length > 0) {
        const informacionSemana = eventosSemana.sort((a, b) => a.left - b.left)
        informacionSemana.forEach((_, i) => {
          this.insertEvent(events, semana.length, informacionSemana, i, 0, 0)
        })
      }

      return events
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
      verEventoDialog,
      nuevoEventoDialog,
      eventoCreation,
      events,
      fechaSeleccionada,
      mostrarRecordatorios,
      titulo,
      recordatorios,
      reglas,
      recordatoriosCombo: ['1', '7', '15', '30', '60', '120'],

      fClaseEvento,
      fFormatearFecha,
      fGuardarEvento,
      fModificarEvento,
      fMostrarVerEvento,
      fMostrarNuevoEvento,
      fMostrarNuevoEventoFecha,
      fObtenerEventos,
      fObtenerFondo,

      myLocale: {
        /* starting with Sunday */
        days: 'Domingo_Lunes_Martes_Miércoles_Jueves_Viernes_Sábado'.split('_'),
        daysShort: 'Dom_Lun_Mar_Mié_Jue_Vie_Sáb'.split('_'),
        months: 'Enero_Febrero_Marzo_Abril_Mayo_Junio_Julio_Agosto_Septiembre_Octubre_Noviembre_Diciembre'.split(
          '_'
        ),
        monthsShort: 'Ene_Feb_Mar_Abr_May_Jun_Jul_Ago_Sep_Oct_Nov_Dic'.split('_'),
        firstDayOfWeek: 1, // 0-6, 0 - Sunday, 1 Monday, ...
        format24h: true,
        pluralDay: 'dias'
      },

      tema: {
        '--calendar-scrollbar-track': '#3D5168',
        '--calendar-scrollbar-thumb': '#3d5168',
        '--calendar-scrollbar-thumb-hover': '#526D82',
        '--calendar-border': '#3D5168 1px solid',
        '--calendar-border-section': '#ff0000 1px dashed',
        '--calendar-border-current': '#3D5168 1px solid',
        '--calendar-mini-range-connector-hover-border': '#027BE3 1px dashed',
        '--calendar-color': '#526D82',
        '--calendar-background': '#ffffff',
        '--calendar-current-color': '#027BE3',
        '--calendar-current-background': '#00000000',
        '--calendar-disabled-date-color': '#ff0000',
        '--calendar-disabled-date-background': '#ff0000',
        '--calendar-active-date-color': '#ffffff',
        '--calendar-active-date-background': '#526D82',
        '--calendar-outside-color': '#3d5168',
        '--calendar-outside-background': '#00000000',
        '--calendar-selected-color': '#027BE3',
        '--calendar-selected-background': '#cce7ff',
        '--calendar-mini-selected-color': 'unset',
        '--calendar-mini-selected-background': 'unset',
        '--calendar-mini-selected-label-color': '#027BE3',
        '--calendar-mini-selected-label-background': '#cce7ff',
        '--calendar-range-color': '#027BE3',
        '--calendar-range-background': '#cce7ff',
        '--calendar-mini-range-color': '#cce7ff',
        '--calendar-mini-range-background': 'unset',
        '--calendar-mini-range-label-color': '#cce7ff',
        '--calendar-mini-range-label-background': '#cce7ff',
        '--calendar-mini-range-connector-color': '#cce7ff',
        '--calendar-mini-range-hover-color': '#027BE3',
        '--calendar-mini-range-firstlast-color': '#cce7ff',
        '--calendar-mini-range-firstlast-background': 'unset',
        '--calendar-mini-range-firstlast-label-color': '#cce7ff',
        '--calendar-mini-range-firstlast-label-background': '#027BE3',
        '--calendar-intervals-width': '56px',
        '--calendar-work-week-width': '30px',
        '--calendar-mini-work-week-width': '30px',
        '--calendar-work-week-font-size': '1.0em',
        '--calendar-head-font-weight': '600'
      }
    }
  },
  methods: {
    insertEvent (events, weekLength, infoWeek, index, availableDays, level) {
      const iEvent = infoWeek[index]
      if (iEvent !== undefined && iEvent.left >= availableDays) {
        // If you have space available, more events are placed
        if (iEvent.left - availableDays) {
          // It is filled with empty events
          events.push({ size: iEvent.left - availableDays })
        }
        // The event is built
        events.push({ size: iEvent.size, event: iEvent.event })

        if (level !== 0) {
          // If it goes into recursion, then the item is deleted
          infoWeek.splice(index, 1)
        }

        const currentAvailableDays = iEvent.left + iEvent.size

        if (currentAvailableDays < weekLength) {
          const indexNextEvent = indexOf(infoWeek, e => e.id !== iEvent.id && e.left >= currentAvailableDays)

          this.insertEvent(
            events,
            weekLength,
            infoWeek,
            indexNextEvent !== -1 ? indexNextEvent : index,
            currentAvailableDays,
            level + 1
          )
        } // else: There are no more days available, end of iteration
      } else {
        events.push({ size: weekLength - availableDays })
        // end of iteration
      }
    },

    badgeStyles (evento, weekLength) {
      const s = {}
      if (evento.size !== undefined) {
        s.width = ((100 / weekLength) * evento.size) + '%'
      }
      return s
    },

    isBetweenDatesWeek (dateStart, dateEnd, weekStart, weekEnd) {
      return ((dateEnd < weekEnd && dateEnd >= weekStart) || dateEnd === weekEnd || (dateEnd > weekEnd && dateStart <= weekEnd))
    },

    onToday () {
      this.$refs.calendar.moveToToday()
    },

    onPrev () {
      this.$refs.calendar.prev()
    },

    onNext () {
      this.$refs.calendar.next()
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
@media screen and (max-width: 1023.99px) {
  .tablero_recordatorio {
    order:1
  }
  .tablero_estadistica {
    order:2
  }
}
</style>

<style lang="sass" scoped>
.my-event
  position: relative
  display: inline-flex
  white-space: nowrap
  font-size: 12px
  height: 16px
  max-height: 16px
  margin: 1px 0 0 0
  justify-content: center
  text-overflow: ellipsis
  overflow: hidden
  cursor: pointer

.title
  position: relative
  display: flex
  justify-content: center
  align-items: center
  height: 100%

.my-void-event
  display: inline-flex
  white-space: nowrap
  height: 1px

.text-white
  color: white

.viaje
  background: #3a86ff

.presupuesto
  background: #8338ec

.seguro
  background: #ff006e

.licencia
  background: #ff006e

.vencimiento
  background: #ff006e

.evento
  background: #9e0059

.default
  background: #9e0059

.rounded-border
  border-radius: 2px
</style>
