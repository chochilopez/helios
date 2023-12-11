<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Adelantos"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="adelantos"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Adelantos</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo adelanto" @click="fMostrarNuevoAdelanto" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar adelantos por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCaja">
                      <q-item-section avatar>
                        <q-icon name="paid" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Caja</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarConductor">
                      <q-item-section avatar>
                        <q-icon name="airline_seat_recline_extra" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Conductor</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFecha">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-calendar-days" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Fecha</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarMonto">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-money-bill-1-wave" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Monto</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNotas">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-pen-to-square" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Notas</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </div>
              <div class="col">
                <q-select
                  v-if="editCaja"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="caja"
                  option-value="id"
                  option-label="caja"
                  label="Buscar por caja"
                  use-input
                  hide-selected
                  fill-input
                  :options="cajas"
                  @filter="fFiltrarCajas"
                  @update:model-value="afBuscarPorCajaId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <q-select
                  v-if="editConductor"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="conductor"
                  :options="conductores"
                  option-value="id"
                  option-label="nombre"
                  label="Buscar por conductor"
                  use-input
                  input-debounce="0"
                  @filter="fFiltrarConductores"
                  @update:model-value="afBuscarPorConductorId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <div class="column" v-if="editFecha">
                  <div class="row justify-around">
                    <q-input
                      mask="##-##-####"
                      style="width: 180px"
                      v-model="fecha.from"
                      outlined
                      dense
                      clearable
                      label="Fecha fin"
                      hint="20-01-2020"
                    >
                      <template v-slot:before>
                        <q-icon name="event" class="cursor-pointer">
                          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                            <q-date v-model="fecha.from" mask="DD-MM-YYYY" :locale="myLocale">
                              <div class="row items-center justify-end">
                                <q-btn v-close-popup label="OK" color="primary" flat />
                              </div>
                            </q-date>
                          </q-popup-proxy>
                        </q-icon>
                      </template>
                    </q-input>
                    <q-input
                      class="q-ml-md"
                      mask="##-##-####"
                      style="width: 180px"
                      v-model="fecha.to"
                      outlined
                      dense
                      clearable
                      label="Fecha inicio"
                      hint="30-01-2020"
                    >
                      <template v-slot:before>
                        <q-icon name="event" class="cursor-pointer">
                          <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                            <q-date v-model="fecha.to" mask="DD-MM-YYYY" :locale="myLocale">
                              <div class="row items-center justify-end">
                                <q-btn v-close-popup label="OK" color="primary" flat />
                              </div>
                            </q-date>
                          </q-popup-proxy>
                        </q-icon>
                      </template>
                    </q-input>
                    <div class="col">
                      <q-icon
                        name="fa-solid fa-magnifying-glass"
                        size="24px"
                        class="cursor-pointer q-pa-sm edits"
                        v-on:click="afBuscarPorFecha()"
                      />
                    </div>
                  </div>
                </div>
                <div class="column" v-if="editMonto">
                  <div class="row justify-between">
                    <div class="col">
                      <q-chip
                        v-model:selected="montoChip.izq"
                        :class="{ 'paleta2-fondo2': montoChip.izq, 'edits-fondo': !montoChip.izq }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-minus"
                        @update:selected="afBuscarPorMonto()"
                      >
                        Mínimo
                      </q-chip>
                    </div>
                    <div class="edits col text-center">
                      <span>Buscar por monto</span><br />
                      <q-icon name="fa-solid fa-plus-minus" />
                    </div>
                    <div class="col text-right">
                      <q-chip
                        v-model:selected="montoChip.der"
                        :class="{ 'paleta2-fondo2': montoChip.der, 'edits-fondo': !montoChip.der }"
                        text-color="white"
                        size="12px"
                        icon="fa-solid fa-plus"
                        @update:selected="afBuscarPorMonto()"
                      >
                        Máximo
                      </q-chip>
                    </div>
                  </div>
                  <div class="row">
                    <q-range
                      label-always
                      switch-label-side
                      color="grey-6"
                      v-model="monto"
                      :min="1"
                      :max="1000"
                      label
                      @change="(montoChip.izq = false), (montoChip.der = false)"
                    />
                  </div>
                </div>
                <q-input
                  v-if="editNotas"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorNotas()"
                  v-model="notas"
                  label="Buscar por notas"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                  <template v-slot:after>
                    <q-icon
                      name="fa-solid fa-magnifying-glass"
                      class="q-mx-xs"
                      v-on:click="afBuscarPorNotas()"
                      style="cursor: pointer"
                    />
                  </template>
                </q-input>
              </div>
            </div>
          </template>
          <template v-slot:body="props">
            <q-tr :props="props" :class="(props.row.eliminada === null) ? '':'bg-red-2'">
              <q-td auto-width class="text-center">
                <q-btn
                  size="sm"
                  class="text-white q-mr-xs"
                  :class="props.expand ? 'paleta5-fondo3' : 'paleta5-fondo2'"
                  round
                  dense
                  @click="props.expand = !props.expand"
                >
                  <q-icon size="2em" class="q-pa-xs" :name="props.expand ? 'zoom_out' : 'zoom_in'" />
                  <q-tooltip>
                    Expandir
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada === null && (autoridad === 'admin' || autoridad === 'usuario')"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEditarAdelanto(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="edit" />
                  <q-tooltip>
                    Modificar
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada === null && autoridad === 'admin'"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEliminarAdelanto(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="delete" />
                  <q-tooltip>
                    Eliminar
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada !== null && autoridad === 'admin'"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarReciclarAdelanto(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td class="text-center">
                {{ fFormatoFecha(props.row.fecha) }}
              </q-td>
              <q-td>
                {{ props.row.conductor }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.monto }}
              </q-td>
              <q-td>
                {{ props.row.caja }}
              </q-td>
              <q-td class="text-center">
                <q-chip v-if="props.row.rendido === 'true'" color="teal" text-color="white" icon="done">
                  Rendido
                </q-chip>
                <q-chip v-if="props.row.rendido === 'false'" color="red" text-color="white" icon="close">
                  No rendido
                </q-chip>
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.caja != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.caja }}</div>
                    <div class="row paleta1-color2">Caja</div>
                  </div>
                  <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.conductor }}</div>
                    <div class="row paleta1-color2">Conductor</div>
                  </div>
                  <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ fFormatoFecha(props.row.fecha) }}</div>
                    <div class="row paleta1-color2">Fecha</div>
                  </div>
                  <div v-if="props.row.monto != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.monto }}</div>
                    <div class="row paleta1-color2">Precio por litro</div>
                  </div>
                  <div v-if="props.row.rendido != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">
                      <span v-if="props.row.rendido === 'true'">Si</span>
                      <span v-if="props.row.rendido === 'false'">No</span>
                    </div>
                    <div class="row paleta1-color2">Rendido</div>
                  </div>
                  <div v-if="props.row.creador != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.creador }}</div>
                    <div class="row paleta1-color2">Creador</div>
                  </div>
                  <div v-if="props.row.creada != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.creada) }}</div>
                    <div class="row paleta1-color2">Creado</div>
                  </div>
                  <div v-if="props.row.modificador != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.modificador }}</div>
                    <div class="row paleta1-color2">Modificador</div>
                  </div>
                  <div v-if="props.row.modificada != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.modificada) }}</div>
                    <div class="row paleta1-color2">Modificado</div>
                  </div>
                  <div v-if="props.row.eliminador != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.eliminador }}</div>
                    <div class="row paleta1-color2">Eliminador</div>
                  </div>
                  <div v-if="props.row.eliminada != null && autoridad === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.eliminada) }}</div>
                    <div class="row paleta1-color2">Eliminada</div>
                  </div>
                  <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.notas }}</div>
                    <div class="row paleta1-color2">Notas</div>
                  </div>
                </div>
              </q-td>
            </q-tr>
          </template>
        </q-table>
      </div>
    </div>
  </q-card>

  <q-dialog v-model="nuevoAdelantoDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">{{ titulo }}</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarAdelanto">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="adelantoCreation.fecha"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Fecha"
                hint="Formato dd-mm-yyyy."
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="adelantoCreation.fecha" mask="DD-MM-YYYY" :locale="myLocale">
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
                v-model="adelantoCreation.monto"
                :rules="[reglas.requerido]"
                mask="#.##"
                fill-mask="0"
                reverse-fill-mask
                outlined
                dense
                clearable
                label="Precio por litro"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="adelantoCreation.cajaId"
                :rules="[reglas.requerido]"
                :options="cajas"
                option-value="id"
                option-label="caja"
                label="Caja"
                use-input
                input-debounce="0"
                @filter="fFiltrarCajas"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="adelantoCreation.conductorId"
                :rules="[reglas.requerido]"
                option-value="id"
                option-label="nombre"
                label="Conductor"
                use-input
                hide-selected
                fill-input
                :options="conductores"
                @filter="fFiltrarConductores"
                hint="Ingresá 3 caracteres para buscar."
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
            <div class="col-xs-6 q-pa-md text-center">
              <q-toggle
                v-model="adelantoCreation.rendido"
                icon="open"
                label="Rendido"
              />
            </div>
            <div class="col-xs-6 q-pa-md text-center">
              <q-toggle
                v-model="adelantoCreation.recibo"
                icon="open"
                label="¿Imprimir recibo?"
              />
            </div>
          </div>
          {{ HAY QUE TERMINAR ACA }}
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="adelantoCreation.notas"
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
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple >
              Finalizar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>

import { AdelantoCreation } from 'src/models/creation/adelanto_creation'
import { adelantoService } from 'src/services/adelanto_service'
import { ayuda } from 'app/src/helpers/ayuda'
import { cajaService } from 'src/services/caja_service'
import { conductorService } from 'src/services/conductor_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { useQuasar } from 'quasar'

const paginacion = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    label: 'Acciones',
    align: 'center'
  },
  {
    name: 'fecha',
    label: 'Fecha',
    align: 'center',
    field: 'fecha',
    sortable: true
  },
  {
    name: 'conductor',
    label: 'Conductor',
    align: 'left',
    field: 'conductor',
    sortable: true
  },
  {
    name: 'monto',
    label: 'Monto',
    align: 'center',
    field: 'monto',
    sortable: true
  },
  {
    name: 'caja',
    label: 'Caja',
    align: 'left',
    field: 'caja',
    sortable: true
  },
  {
    name: 'rendido',
    label: 'Rendido',
    align: 'center',
    field: 'rendido',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()

    const adelantoCreation = reactive(new AdelantoCreation())
    const adelantos = ref([])
    const autoridad = ref(ayuda.getAutoridad())
    const caja = ref(null)
    const cajas = ref([])
    const cajasList = ref([])
    const conductor = ref(null)
    const conductores = ref([])
    const conductoresList = ref([])
    const descripcion = ref(null)
    const editCaja = ref(false)
    const editConductor = ref(false)
    const editDescripcion = ref(false)
    const editFecha = ref(false)
    const editMonto = ref(false)
    const editNotas = ref(false)
    const fecha = ref({ from: null, to: null })
    const monto = ref({ min: 1, max: 1000 })
    const montoChip = ref({ izq: false, der: false })
    const notas = ref(null)
    const nuevoAdelantoDialog = ref(false)
    const paso1 = ref(true)
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(ayuda.getUid())
    const titulo = ref(null)

    afBuscarPaginadas()

    async function afBuscarCajas () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhCajaTodasConEliminadasConSesion', sesion.value) !== null) {
            cajasList.value = llaveroService.obtenerDeLocalConSesion('hhCajaTodasConEliminadasConSesion', sesion.value).value
            console.log('CajaService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await cajaService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              cajasList.value = resultado.data
              console.log('CajaService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCajaTodasConSesion', sesion.value) !== null) {
            cajasList.value = llaveroService.obtenerDeLocalConSesion('hhCajaTodasConSesion', sesion.value).value
            console.log('CajaService: Sesion recargada.')
          } else {
            resultado = await cajaService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              cajasList.value = resultado.data
              console.log('CajaService: ' + resultado.headers.mensaje)
            }
          }
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

    async function afBuscarConductores () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConEliminadasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await conductorService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhConductorTodasConSesion', sesion.value).value
            console.log('ConductorService: Sesion recargada.')
          } else {
            resultado = await conductorService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              conductoresList.value = resultado.data
              console.log('ConductorService: ' + resultado.headers.mensaje)
            }
          }
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

    async function afBuscarPaginadas () {
      $q.loading.show()
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '50'
        }
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await adelantoService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await adelantoService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          adelantos.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          adelantos.value = []
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

    async function afBuscarPorCajaId () {
      if (caja.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await adelantoService.spfBuscarTodasPorCajaIdConEliminadas(caja.value)
          } else {
            resultado = await adelantoService.spfBuscarTodasPorCajaId(caja.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            adelantos.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            adelantos.value = []
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
    }

    async function afBuscarPorConductorId () {
      if (conductor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await adelantoService.spfBuscarTodasPorConductorIdConEliminadas(conductor.value)
          } else {
            resultado = await adelantoService.spfBuscarTodasPorConductorId(conductor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            adelantos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            adelantos.value = []
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
    }

    async function afBuscarPorDescripcion () {
      if (descripcion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await adelantoService.spfBuscarTodasPorDescripcionConEliminadas(descripcion.value)
          } else {
            resultado = await adelantoService.spfBuscarTodasPorDescripcion(descripcion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            adelantos.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            adelantos.value = []
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
    }

    async function afBuscarPorFecha () {
      if (fecha.value.from == null) {
        notificarService.notificarAlerta('El campo fecha desde no puede estar vacio.')
      } else if (fecha.value.to == null) {
        notificarService.notificarAlerta('El campo fecha hasta no puede estar vacio.')
      } else if (fecha.value.from > fecha.value.to) {
        notificarService.notificarAlerta('El campo fecha desde no puede ser mayor que fecha hasta.')
      } else {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await adelantoService.spfBuscarTodasPorFechaEntreConEliminadas(ayuda.fFormatearDeDatePicker(fecha.value.from), ayuda.fFormatearDeDatePicker(fecha.value.to))
          } else {
            resultado = await adelantoService.spfBuscarTodasPorFechaEntre(ayuda.fFormatearDeDatePicker(fecha.value.from), ayuda.fFormatearDeDatePicker(fecha.value.to))
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            adelantos.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            adelantos.value = []
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
    }

    async function afBuscarPorMonto () {
      if (montoChip.value.izq === true && montoChip.value.der === true) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await adelantoService.spfBuscarTodasPorMontoEntreConEliminadas(monto.value.min, monto.value.max)
          } else {
            resultado = await adelantoService.spfBuscarTodasPorMontoEntre(monto.value.min, monto.value.max)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            adelantos.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            adelantos.value = []
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
        montoChip.value.izq = false
        montoChip.value.der = false
      }
    }

    async function afBuscarPorNotas () {
      if (notas.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await adelantoService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await adelantoService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            adelantos.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            adelantos.value = []
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
    }

    async function afEliminarAdelanto (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await adelantoService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el adelanto.')
        }
      } catch (err) {
        console.clear()
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

    async function afGuardarAdelanto () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await adelantoService.spfGuardar(adelantoCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el adelanto.')
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

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarAdelanto () {
      afGuardarAdelanto().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoAdelantoDialog.value = false
          fIrPaso1()
        })
      })
    }

    async function afReciclarAdelanto (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await adelantoService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el adelanto.')
        }
      } catch (err) {
        console.clear()
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

    function fFiltrarCajas (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        cajas.value = cajasList.value.filter(
          (v) => v.caja.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarConductores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        conductores.value = conductoresList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      adelantoCreation.descripcion = null
      adelantoCreation.fecha = null
      adelantoCreation.monto = null
      adelantoCreation.notas = null
      adelantoCreation.rendido = null
      adelantoCreation.cajaId = null
      adelantoCreation.conductorId = null
      adelantoCreation.reciboId = null

      adelantoCreation.id = null
      adelantoCreation.creadorId = null
      adelantoCreation.creador = null
      adelantoCreation.creada = null
      adelantoCreation.modificadorId = null
      adelantoCreation.modificador = null
      adelantoCreation.modificada = null
      adelantoCreation.eliminadorId = null
      adelantoCreation.eliminador = null
      adelantoCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editCaja.value = false
      editConductor.value = false
      editDescripcion.value = false
      editFecha.value = false
      editMonto.value = false
      editNotas.value = false

      caja.value = null
      conductor.value = null
      descripcion.value = null
      fecha.value.from = null
      fecha.value.to = null
      monto.value.min = 0
      monto.value.max = 1000
      montoChip.value.izq = false
      montoChip.value.der = false
      notas.value = null
    }

    function fMostrarCaja () {
      afBuscarCajas().then(() => {
        fLimpiarInputs()
        editCaja.value = true
      })
    }

    function fMostrarConductor () {
      afBuscarConductores().then(() => {
        fLimpiarInputs()
        editConductor.value = true
      })
    }

    function fMostrarDescripcion () {
      fLimpiarInputs()
      editDescripcion.value = true
    }

    function fMostrarFecha () {
      fLimpiarInputs()
      editFecha.value = true
    }

    function fMostrarMonto () {
      fLimpiarInputs()
      editMonto.value = true
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarNuevoAdelanto () {
      afBuscarCajas().then(() => {
        afBuscarConductores().then(() => {
          titulo.value = 'Nuevo adelanto'
          fLimpiarFormulario()
          fIrPaso1()
          nuevoAdelantoDialog.value = true
        })
      })
    }

    function fMostrarEditarAdelanto (props) {
      titulo.value = 'Modificar adelanto'
      adelantoCreation.descripcion = props.row.descripcion
      adelantoCreation.fecha = ayuda.fFormatearADatePicker(props.row.fecha.slice(0, 10))
      adelantoCreation.monto = props.row.monto
      adelantoCreation.notas = props.row.notas
      adelantoCreation.rendido = props.row.rendido
      adelantoCreation.cajaId = props.row.cajaId
      adelantoCreation.conductorId = props.row.conductorId
      adelantoCreation.reciboId = props.row.reciboId

      adelantoCreation.id = props.row.id
      adelantoCreation.creada = props.row.creada
      adelantoCreation.creadorId = props.row.creadorId
      adelantoCreation.eliminada = props.row.eliminada
      adelantoCreation.eliminadorId = props.row.eliminadorId
      adelantoCreation.modificada = props.row.modificada
      adelantoCreation.modificadorId = props.row.modificadorId

      nuevoAdelantoDialog.value = true
    }

    function fMostrarEliminarAdelanto (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarAdelanto(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarAdelanto (props) {
      afReciclarAdelanto(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      adelantoCreation,
      adelantos,
      autoridad,
      caja,
      cajas,
      cajasList,
      conductor,
      conductores,
      conductoresList,
      columnas,
      editCaja,
      editConductor,
      editDescripcion,
      editFecha,
      editMonto,
      editNotas,
      fecha,
      monto,
      montoChip,
      notas,
      nuevoAdelantoDialog,
      paginacion,
      paso1,
      reglas,
      titulo,

      afBuscarPorCajaId,
      afBuscarPorConductorId,
      afBuscarPorDescripcion,
      afBuscarPorFecha,
      afBuscarPorMonto,
      afBuscarPorNotas,
      fGuardarAdelanto,
      fFiltrarCajas,
      fFiltrarConductores,
      fFormatoFecha,
      fMostrarCaja,
      fMostrarConductor,
      fMostrarDescripcion,
      fMostrarEditarAdelanto,
      fMostrarEliminarAdelanto,
      fMostrarFecha,
      fMostrarMonto,
      fMostrarNotas,
      fMostrarNuevoAdelanto,
      fMostrarReciclarAdelanto,

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
      }
    }
  }
}

</script>
<style scoped>
.edits-fondo {
  background: #9e9e9e;
}
.edits {
  color: #9e9e9e;
}
.q-btn-dropdown {
  width: 240px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 240px;
}
</style>
