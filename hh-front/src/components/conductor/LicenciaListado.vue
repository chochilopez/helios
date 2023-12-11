<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Licencias"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="licencias"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Licencias</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nueva licencia" @click="fMostrarNuevoLicencia" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar licencias por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarConductor">
                      <q-item-section avatar>
                        <q-icon name="airline_seat_recline_extra" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Conductor</q-item-label>
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
                  v-if="editConductor"
                  outlined
                  dense
                  emit-value
                  map-options
                  clearable
                  v-model="conductor"
                  option-value="id"
                  option-label="nombre"
                  label="Buscar por nombre conductor"
                  use-input
                  hide-selected
                  fill-input
                  :options="conductores"
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
                  @click="fMostrarEditarLicencia(props)"
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
                  @click="fMostrarEliminarLicencia(props)"
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
                  @click="fMostrarReciclarLicencia(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td>
                {{ fFormatoFecha(props.row.vencimiento)  }}
              </q-td>
              <q-td>
                {{ props.row.conductor }}
              </q-td>
              <q-td>
                {{ props.row.categoria }}
              </q-td>
              <q-td>
                {{ props.row.numero }}
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.categoria != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.categoria }}</div>
                    <div class="row paleta1-color2">Categoria</div>
                  </div>
                  <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.conductor }}</div>
                    <div class="row paleta1-color2">Conductor</div>
                  </div>
                  <div v-if="props.row.numero != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.numero }}</div>
                    <div class="row paleta1-color2">Número</div>
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

  <q-dialog v-model="nuevaLicenciaDialog" persistent transition-show="fade" transition-hide="fade">
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
        <q-form v-on:submit.prevent="fGuardarLicencia">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="licenciaCreation.conductorId"
                :rules="[reglas.requerido]"
                :options="conductores"
                option-value="id"
                option-label="nombre"
                label="Conductor"
                use-input
                input-debounce="0"
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
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="licenciaCreation.vencimiento"
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
                      <q-date v-model="licenciaCreation.vencimiento" mask="DD-MM-YYYY" :locale="myLocale">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="OK" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="licenciaCreation.categoria"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Categoría de licencia"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="licenciaCreation.numero"
                :rules="[reglas.requerido, reglas.max50]"
                outlined
                dense
                clearable
                label="Número de licencia"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="licenciaCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              >
              </q-input>
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

import { ayuda } from 'app/src/helpers/ayuda'
import { conductorService } from 'src/services/conductor_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { LicenciaCreation } from 'src/models/creation/licencia_creation'
import { licenciaService } from 'src/services/licencia_service'
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
    name: 'vencimiento',
    label: 'Vencimiento',
    align: 'left',
    field: 'vencimiento',
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
    name: 'categoria',
    label: 'Categoría',
    align: 'left',
    field: 'categoria',
    sortable: true
  },
  {
    name: 'numero',
    label: 'Número',
    align: 'left',
    field: 'numero',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(ayuda.getUid())
    const autoridad = ref(ayuda.getAutoridad())

    const conductor = ref(null)
    const conductores = ref([])
    const conductoresList = ref([])
    const notas = ref(null)
    const vencimiento = ref({ from: null, to: null })
    const vencimientos = ref([])

    const editConductor = ref(false)
    const editNotas = ref(false)
    const editVencimiento = ref(false)

    const nuevaBusqueda = ref(false)
    const nuevaLicenciaDialog = ref(false)
    const paso1 = ref(true)
    const licenciaCreation = reactive(new LicenciaCreation())
    const licencias = ref([])
    const titulo = ref(null)

    afBuscarPaginadas()

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
          if (llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value) !== null) {
            conductoresList.value = llaveroService.obtenerDeLocalConSesion('hhAcopladoTodasConSesion', sesion.value).value
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
          resultado = await licenciaService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await licenciaService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          licencias.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          licencias.value = []
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

    async function afBuscarPorConductorId () {
      if (conductor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await licenciaService.spfBuscarTodasPorConductorIdConEliminadas(conductor.value)
          } else {
            resultado = await licenciaService.spfBuscarTodasPorConductorId(conductor.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            licencias.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            licencias.value = []
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
            resultado = await licenciaService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await licenciaService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            licencias.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            licencias.value = []
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
            resultado = await licenciaService.spfBuscarTodasPorVencimientoEntreFechasConEliminadas(
              ayuda.fFormatearDeDatePicker(vencimiento.value.from),
              ayuda.fFormatearDeDatePicker(vencimiento.value.to)
            )
          } else {
            resultado = await licenciaService.spfBuscarTodasPorVencimientoEntreFechas(
              ayuda.fFormatearDeDatePicker(vencimiento.value.from),
              ayuda.fFormatearDeDatePicker(vencimiento.value.to)
            )
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            licencias.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            licencias.value = []
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

    async function afGuardarLicencia () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await licenciaService.spfGuardar(licenciaCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el licencia.')
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

    function fGuardarLicencia () {
      afGuardarLicencia().then(() => {
        afBuscarPaginadas().then(() => {
          nuevaLicenciaDialog.value = false
          fIrPaso1()
        })
      })
    }

    async function afEliminarLicencia (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await licenciaService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el licencia.')
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

    async function afReciclarLicencia (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await licenciaService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el licencia.')
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
      licenciaCreation.categoria = null
      licenciaCreation.conductorId = null
      licenciaCreation.notas = null
      licenciaCreation.numero = null
      licenciaCreation.vencimiento = null
      licenciaCreation.vencimientoId = null

      licenciaCreation.id = null
      licenciaCreation.creadorId = null
      licenciaCreation.creador = null
      licenciaCreation.creada = null
      licenciaCreation.modificadorId = null
      licenciaCreation.modificador = null
      licenciaCreation.modificada = null
      licenciaCreation.eliminadorId = null
      licenciaCreation.eliminador = null
      licenciaCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editConductor.value = false
      editNotas.value = false
      editVencimiento.value = false

      conductor.value = null
      notas.value = null
      vencimiento.value.from = null
      vencimiento.value.to = null
    }

    function fMostrarConductor () {
      afBuscarConductores().then(() => {
        fLimpiarInputs()
        editConductor.value = true
      })
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarNuevoLicencia () {
      afBuscarConductores().then(() => {
        titulo.value = 'Nueva licencia'
        fLimpiarFormulario()
        fIrPaso1()
        nuevaLicenciaDialog.value = true
      })
    }

    function fMostrarEditarLicencia (props) {
      titulo.value = 'Modificar licencia'
      licenciaCreation.id = props.row.id
      licenciaCreation.categoria = props.row.categoria
      licenciaCreation.conductorId = props.row.conductorId
      licenciaCreation.notas = props.row.notas
      licenciaCreation.numero = props.row.numero
      licenciaCreation.vencimiento = ayuda.fFormatearADatePicker(props.row.vencimiento.slice(0, 10))
      licenciaCreation.vencimientoId = props.row.vencimientoId

      licenciaCreation.creada = props.row.creada
      licenciaCreation.creadorId = props.row.creadorId
      licenciaCreation.eliminada = props.row.eliminada
      licenciaCreation.eliminadorId = props.row.eliminadorId
      licenciaCreation.modificada = props.row.modificada
      licenciaCreation.modificadorId = props.row.modificadorId

      nuevaLicenciaDialog.value = true
    }

    function fMostrarEliminarLicencia (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarLicencia(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarLicencia (props) {
      afReciclarLicencia(props.row.id).then(() => {
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

      conductor,
      conductores,
      conductoresList,
      notas,
      vencimiento,
      vencimientos,

      columnas,
      nuevaBusqueda,
      nuevaLicenciaDialog,
      paginacion,
      paso1,
      reglas,
      titulo,
      licenciaCreation,
      licencias,
      vehiculos: ['Acoplado', 'Camión'],

      editConductor,
      editNotas,
      editVencimiento,

      fGuardarLicencia,
      fFiltrarConductores,
      fFormatoFecha,
      fMostrarConductor,
      fMostrarNotas,
      fMostrarVencimiento,

      fMostrarNuevoLicencia,
      fMostrarEditarLicencia,
      fMostrarEliminarLicencia,
      fMostrarReciclarLicencia,

      afBuscarPorConductorId,
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
