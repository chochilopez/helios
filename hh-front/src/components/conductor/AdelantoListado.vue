<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Seguros"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="seguros"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Seguros</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo seguro" @click="fMostrarNuevoSeguro" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar seguros por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarAcoplado">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-trailer" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Acoplado</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarAseguradora">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-car-burst" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Aseguradora</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCamion">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-truck" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Camion</q-item-label>
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
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarVencimiento">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-calendar-days" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Vencimiento</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </div>
              <div class="col">
                <q-select
                  v-if="editAcoplado"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="acoplado"
                  option-value="id"
                  option-label="marcaModelo"
                  label="Buscar por modelo acoplado"
                  use-input
                  hide-selected
                  fill-input
                  :options="acoplados"
                  @filter="fFiltrarAcoplados"
                  @update:model-value="afBuscarPorAcopladoId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <q-select
                  v-if="editAseguradora"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="aseguradora"
                  :options="aseguradoras"
                  option-value="id"
                  option-label="nombre"
                  label="Buscar por aseguradora"
                  use-input
                  input-debounce="0"
                  @filter="fFiltrarAseguradoras"
                  @update:model-value="afBuscarPorAseguradoraId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
                <q-select
                  v-if="editCamion"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="camion"
                  :options="camiones"
                  option-value="id"
                  option-label="marcaModelo"
                  label="Buscar por camión"
                  use-input
                  input-debounce="0"
                  @filter="fFiltrarCamiones"
                  @update:model-value="afBuscarPorCamionId()"
                  hint="Tenés que escribir al menos 3 caracteres para buscar."
                >
                  <template v-slot:no-option>
                    <q-item>
                      <q-item-section class="text-grey"> Sin resultados </q-item-section>
                    </q-item>
                  </template>
                </q-select>
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
                <div class="column" v-if="editVencimiento">
                  <div class="row justify-around">
                      <q-input
                        mask="##-##-####"
                        style="width: 180px"
                        v-model="vencimiento.from"
                        outlined
                        dense
                        clearable
                        label="Vencimiento inicio"
                        hint="20-01-2020"
                      >
                        <template v-slot:before>
                          <q-icon name="event" class="cursor-pointer">
                            <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                              <q-date v-model="vencimiento.from" mask="DD-MM-YYYY">
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
                        v-model="vencimiento.to"
                        outlined
                        dense
                        clearable
                        label="Vencimiento fin"
                        hint="30-01-2020"
                      >
                        <template v-slot:before>
                          <q-icon name="event" class="cursor-pointer">
                            <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                              <q-date v-model="vencimiento.to" mask="DD-MM-YYYY">
                                <div class="row items-center justify-end">
                                  <q-btn v-close-popup label="OK" color="primary" flat />
                                </div>
                              </q-date>
                            </q-popup-proxy>
                          </q-icon>
                        </template>
                      </q-input>
                    <div class="col">
                      <q-icon name="fa-solid fa-magnifying-glass" size="24px" class="cursor-pointer q-pa-sm edits" v-on:click="afBuscarPorVencimiento()" />
                    </div>
                  </div>
                </div>
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
                  @click="fMostrarEditarSeguro(props)"
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
                  @click="fMostrarEliminarSeguro(props)"
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
                  @click="fMostrarReciclarSeguro(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td v-if="props.row.acoplado !== null">
                Acoplado: {{ props.row.acoplado }}
              </q-td>
              <q-td v-if="props.row.camion !== null">
                Camión: {{ props.row.camion }}
              </q-td>
              <q-td>
                {{ props.row.aseguradora }}
              </q-td>
              <q-td>
                {{ fFormatoFecha(props.row.vencimiento) }}
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.acoplado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.acoplado }}</div>
                    <div class="row paleta1-color2">Acoplado</div>
                  </div>
                  <div v-if="props.row.aseguradora != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.aseguradora }}</div>
                    <div class="row paleta1-color2">Aseguradora</div>
                  </div>
                  <div v-if="props.row.camion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.camion }}</div>
                    <div class="row paleta1-color2">Camión</div>
                  </div>
                  <div v-if="props.row.vencimiento != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.vencimiento) }}</div>
                    <div class="row paleta1-color2">Vencimiento</div>
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

  <q-dialog v-model="nuevoSeguroDialog" persistent transition-show="fade" transition-hide="fade">
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
        <q-form v-on:submit.prevent="fGuardarSeguro">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                clearable
                v-model="tipoVehiculo"
                :rules="[reglas.requerido]"
                :options="vehiculos"
                label="Tipo vehículo"
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
                v-if="tipoVehiculo === 'Acoplado'"
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="seguroCreation.acopladoId"
                :options="acoplados"
                option-value="id"
                option-label="marcaModelo"
                label="Acoplado"
                use-input
                input-debounce="0"
                @filter="fFiltrarAcoplados"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            <div class="row justify-around">
            </div>
              <q-select
                v-if="tipoVehiculo === 'Camión'"
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="seguroCreation.camionId"
                :options="camiones"
                option-value="id"
                option-label="marcaModelo"
                label="Camión"
                use-input
                input-debounce="0"
                @filter="fFiltrarCamiones"
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
                v-model="seguroCreation.aseguradoraId"
                :options="aseguradoras"
                option-value="id"
                option-label="nombre"
                label="Aseguradora"
                use-input
                input-debounce="0"
                @filter="fFiltrarAseguradoras"
                hint="Ingresá 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="row justify-around">
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="seguroCreation.vencimiento"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Vencimiento póliza"
                hint="Formato dd-mm-yyyy."
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="seguroCreation.vencimiento" mask="DD-MM-YYYY" :locale="myLocale">
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
                type="textarea"
                v-model="seguroCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-end q-pa-md">
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

import { acopladoService } from 'src/services/acoplado_service'
import { ayuda } from 'app/src/helpers/ayuda'
import { camionService } from 'src/services/camion_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { proveedorService } from 'src/services/proveedor_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { SeguroCreation } from 'src/models/creation/seguro_creation'
import { seguroService } from 'src/services/seguro_service'
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
    name: 'vehiculo',
    label: 'Vehiculo',
    align: 'left',
    field: 'vehiculo',
    sortable: true
  },
  {
    name: 'aseguradora',
    label: 'Aseguradora',
    align: 'left',
    field: 'aseguradora',
    sortable: true
  },
  {
    name: 'vencimiento',
    label: 'Vencimiento',
    align: 'left',
    field: 'vencimiento',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(ayuda.getUid())
    const autoridad = ref(ayuda.getAutoridad())

    const acoplado = ref(null)
    const acoplados = ref([])
    const acopladosList = ref([])
    const aseguradora = ref(null)
    const aseguradoras = ref([])
    const aseguradorasList = ref([])
    const camion = ref(null)
    const camiones = ref([])
    const camionesList = ref([])
    const notas = ref(null)
    const tipoVehiculo = ref(null)
    const vencimiento = ref({ from: null, to: null })
    const vencimientos = ref([])

    const editAseguradora = ref(false)
    const editAcoplado = ref(false)
    const editCamion = ref(false)
    const editNotas = ref(false)
    const editVencimiento = ref(false)

    const nuevaBusqueda = ref(false)
    const nuevoSeguroDialog = ref(false)
    const paso1 = ref(true)
    const seguroCreation = reactive(new SeguroCreation())
    const seguros = ref([])
    const titulo = ref(null)

    afBuscarPaginadas()

    async function afBuscarAcoplados () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConEliminadasConSesion', sesion.value) !== null) {
            acopladosList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConEliminadasConSesion', sesion.value).value
            console.log('AcopladoService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await acopladoService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              acopladosList.value = resultado.data
              console.log('AcopladoService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value) !== null) {
            acopladosList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value).value
            console.log('AcopladoService: Sesion recargada.')
          } else {
            resultado = await acopladoService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              acopladosList.value = resultado.data
              console.log('AcopladoService: ' + resultado.headers.mensaje)
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

    async function afBuscarAseguradoras () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConEliminadasConSesion', sesion.value) !== null) {
            aseguradorasList.value = llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConEliminadasConSesion', sesion.value).value
            console.log('ProveedorService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await proveedorService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              aseguradorasList.value = resultado.data
              console.log('ProveedorService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConSesion', sesion.value) !== null) {
            aseguradorasList.value = llaveroService.obtenerDeLocalConSesion('hhProveedorTodasConSesion', sesion.value).value
            console.log('ProveedorService: Sesion recargada.')
          } else {
            resultado = await proveedorService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              aseguradorasList.value = resultado.data
              console.log('ProveedorService: ' + resultado.headers.mensaje)
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

    async function afBuscarCamiones () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConEliminadasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await camionService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value) !== null) {
            camionesList.value = llaveroService.obtenerDeLocalConSesion('hhCamionTodasConSesion', sesion.value).value
            console.log('CamionService: Sesion recargada.')
          } else {
            resultado = await camionService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              camionesList.value = resultado.data
              console.log('CamionService: ' + resultado.headers.mensaje)
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
          resultado = await seguroService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await seguroService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          seguros.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          seguros.value = []
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

    async function afBuscarPorAcopladoId () {
      if (acoplado.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await seguroService.spfBuscarTodasPorAcopladoIdConEliminadas(acoplado.value)
          } else {
            resultado = await seguroService.spfBuscarTodasPorAcopladoId(acoplado.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            seguros.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            seguros.value = []
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

    async function afBuscarPorAseguradoraId () {
      if (aseguradora.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await seguroService.spfBuscarTodasPorAseguradoraIdConEliminadas(aseguradora.value)
          } else {
            resultado = await seguroService.spfBuscarTodasPorAseguradoraId(aseguradora.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            seguros.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            seguros.value = []
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

    async function afBuscarPorCamionId () {
      if (camion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await seguroService.spfBuscarTodasPorCamionIdConEliminadas(camion.value)
          } else {
            resultado = await seguroService.spfBuscarTodasPorCamionId(camion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            seguros.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            seguros.value = []
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

    async function afBuscarPorNotas () {
      if (notas.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await seguroService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await seguroService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            seguros.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            seguros.value = []
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

    async function afBuscarPorVencimiento () {
      if (vencimiento.value.from == null) {
        notificarService.notificarAlerta('El campo vencimiento desde no puede estar vacio.')
      } else if (vencimiento.value.to == null) {
        notificarService.notificarAlerta('El campo vencimiento hasta no puede estar vacio.')
      } else if (vencimiento.value.from > vencimiento.value.to) {
        notificarService.notificarAlerta('El campo vencimiento desde no puede ser mayor que vencimiento hasta.')
      } else {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await seguroService.spfBuscarTodasPorVencimientoEntreFechasConEliminadas(
              ayuda.fFormatearDeDatePicker(vencimiento.value.from),
              ayuda.fFormatearDeDatePicker(vencimiento.value.to)
            )
          } else {
            resultado = await seguroService.spfBuscarTodasPorVencimientoEntreFechas(
              ayuda.fFormatearDeDatePicker(vencimiento.value.from),
              ayuda.fFormatearDeDatePicker(vencimiento.value.to)
            )
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            seguros.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            seguros.value = []
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

    async function afGuardarSeguro () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await seguroService.spfGuardar(seguroCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el seguro.')
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

    function fGuardarSeguro () {
      afGuardarSeguro().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoSeguroDialog.value = false
          fIrPaso1()
        })
      })
    }

    async function afEliminarSeguro (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await seguroService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el seguro.')
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

    async function afReciclarSeguro (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await seguroService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el seguro.')
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

    function fFiltrarAcoplados (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        acoplados.value = acopladosList.value.filter(
          (v) => v.marcaModelo.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarAseguradoras (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        aseguradoras.value = aseguradorasList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFiltrarCamiones (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        camiones.value = camionesList.value.filter(
          (v) => v.marcaModelo.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      seguroCreation.acopladoId = null
      seguroCreation.aseguradoraId = null
      seguroCreation.camionId = null
      seguroCreation.vencimiento = null
      seguroCreation.notas = null

      seguroCreation.id = null
      seguroCreation.creadorId = null
      seguroCreation.creador = null
      seguroCreation.creada = null
      seguroCreation.modificadorId = null
      seguroCreation.modificador = null
      seguroCreation.modificada = null
      seguroCreation.eliminadorId = null
      seguroCreation.eliminador = null
      seguroCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editAseguradora.value = false
      editAcoplado.value = false
      editCamion.value = false
      editNotas.value = false
      editVencimiento.value = false

      acoplado.value = null
      aseguradora.value = null
      camion.value = null
      notas.value = null
      vencimiento.value.from = null
      vencimiento.value.to = null
    }

    function fMostrarAcoplado () {
      afBuscarAcoplados().then(() => {
        fLimpiarInputs()
        editAcoplado.value = true
      })
    }

    function fMostrarAseguradora () {
      afBuscarAseguradoras().then(() => {
        fLimpiarInputs()
        editAseguradora.value = true
      })
    }

    function fMostrarCamion () {
      afBuscarCamiones().then(() => {
        fLimpiarInputs()
        editCamion.value = true
      })
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarNuevoSeguro () {
      afBuscarAcoplados().then(() => {
        afBuscarAseguradoras().then(() => {
          afBuscarCamiones().then(() => {
            titulo.value = 'Nuevo seguro'
            fLimpiarFormulario()
            tipoVehiculo.value =
            fIrPaso1()
            nuevoSeguroDialog.value = true
          })
        })
      })
    }

    function fMostrarEditarSeguro (props) {
      titulo.value = 'Modificar seguro'
      if (props.row.acopladoId !== null) {
        tipoVehiculo.value = 'Acoplado'
      } else {
        tipoVehiculo.value = 'Camión'
      }
      seguroCreation.id = props.row.id
      seguroCreation.notas = props.row.notas
      seguroCreation.acopladoId = props.row.acopladoId
      seguroCreation.camionId = props.row.camionId
      seguroCreation.aseguradoraId = props.row.aseguradoraId
      seguroCreation.vencimientoId = props.row.vencimientoId
      seguroCreation.vencimiento = ayuda.fFormatearADatePicker(props.row.vencimiento.slice(0, 10))

      seguroCreation.creada = props.row.creada
      seguroCreation.creadorId = props.row.creadorId
      seguroCreation.eliminada = props.row.eliminada
      seguroCreation.eliminadorId = props.row.eliminadorId
      seguroCreation.modificada = props.row.modificada
      seguroCreation.modificadorId = props.row.modificadorId

      nuevoSeguroDialog.value = true
    }

    function fMostrarEliminarSeguro (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarSeguro(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarSeguro (props) {
      afReciclarSeguro(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarVencimiento () {
      fLimpiarInputs()
      editVencimiento.value = true
    }

    return {
      autoridad,

      aseguradora,
      aseguradoras,
      aseguradorasList,
      acoplado,
      acoplados,
      acopladosList,
      camion,
      camiones,
      camionesList,
      notas,
      vencimiento,
      vencimientos,

      columnas,
      nuevaBusqueda,
      nuevoSeguroDialog,
      paginacion,
      paso1,
      reglas,
      tipoVehiculo,
      titulo,
      seguroCreation,
      seguros,
      vehiculos: ['Acoplado', 'Camión'],

      editAseguradora,
      editAcoplado,
      editCamion,
      editNotas,
      editVencimiento,

      fGuardarSeguro,
      fFiltrarAcoplados,
      fFiltrarAseguradoras,
      fFiltrarCamiones,
      fFormatoFecha,
      fMostrarAcoplado,
      fMostrarAseguradora,
      fMostrarCamion,
      fMostrarNotas,
      fMostrarVencimiento,

      fMostrarNuevoSeguro,
      fMostrarEditarSeguro,
      fMostrarEliminarSeguro,
      fMostrarReciclarSeguro,

      afBuscarPorAcopladoId,
      afBuscarPorAseguradoraId,
      afBuscarPorCamionId,
      afBuscarPorVencimiento,
      afBuscarPorNotas,
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
