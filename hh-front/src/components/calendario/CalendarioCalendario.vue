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
        <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg q-mx-sm" ripple icon="add_circle" label="Evento" />
        <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg q-mx-sm" ripple icon="add_circle" label="Vencimiento" />
      </div>
    </div>
  </div>
  <q-card class="font-5 no-shadow no-border">
    <div class="subcontent">
      <div class="row justify-center">
        <div style="display: flex; max-width: 800px; width: 100%;">
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
            @click-day="fMostrarDialogoEventosDia"
          >
            <template #week="{ scope: { week, weekdays } }">
              <template v-for="evento in fObtenerEventos(week, weekdays)" :key="evento" >
                <div :class="fClaseEvento(evento)" :style="badgeStyles(evento, week.length)" @click="fMostrarDialogoEvento()">
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

  <q-dialog v-model="eventoDialogo">
      <q-card class="my-card">
        <q-img src="https://cdn.quasar.dev/img/chicken-salad.jpg" />

        <q-card-section>
          <q-btn
            fab
            color="primary"
            icon="place"
            class="absolute"
            style="top: 0; right: 12px; transform: translateY(-50%);"
          />

          <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis">
              Cafe Basilico
            </div>
            <div class="col-auto text-grey text-caption q-pt-md row no-wrap items-center">
              <q-icon name="place" />
              250 ft
            </div>
          </div>

        </q-card-section>

        <q-card-section class="q-pt-none">
          <div class="text-subtitle1">
            $・Italian, Cafe
          </div>
          <div class="text-caption text-grey">
            Small plates, salads & sandwiches in an intimate setting.
          </div>
        </q-card-section>

        <q-separator />

        <q-card-actions align="right">
          <q-btn v-close-popup flat color="primary" label="Reserve" />
          <q-btn v-close-popup flat color="primary" round icon="event" />
        </q-card-actions>
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
import { useQuasar } from 'quasar'
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
    const eventoDialogo = ref(false)
    const fechaSeleccionada = ref(today())

    const eventoCreation = reactive(new EventoCreation())
    const events = ref([])

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

    function fMostrarDialogoEvento (data) {
      console.log('fMostrarDialogoEvento', data)
      eventoDialogo.value = true
    }

    function fMostrarDialogoEventosDia () {
      eventoDialogo.value = true
    }

    function fObtenerEventos (semana, weekdays) {
      const primerDiaSemana = parsed(semana[0].date + ' 00:00')
      const ultimoDiaSemana = parsed(semana[semana.length - 1].date + ' 23:59')

      const eventosSemana = []
      this.events.forEach((event, id) => {
        const inicio = parsed(event.inicio.slice(0, 10) + ' 00:00')
        const fin = parsed(event.fin.slice(0, 10) + ' 23:59')

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
            event.bgcolor = 'black'
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

    return {
      eventoDialogo,
      events,
      fechaSeleccionada,
      fClaseEvento,
      fMostrarDialogoEvento,
      fMostrarDialogoEventosDia,
      fObtenerEventos,

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

.rounded-border
  border-radius: 2px
</style>
