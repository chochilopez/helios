<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Camiones"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="camiones"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Camiones</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo camion" @click="fMostrarNuevoCamion" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar camiones por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarAnio">
                      <q-item-section avatar>
                        <q-icon name="update" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Año</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarMarcaModelo">
                      <q-item-section avatar>
                        <q-icon name="stream" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Marca y modelo</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable  v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNotas">
                      <q-item-section avatar>
                        <q-icon name="fa-solid fa-pen-to-square" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Notas</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarPatente">
                      <q-item-section avatar>
                        <q-icon name="local_parking" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Patente</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </div>
              <div class="col">
                <q-input
                  v-if="editMarcaModelo"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorMarcaModelo()"
                  v-model="marcaModelo"
                  label="Buscar por marca/modelo"
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
                      v-on:click="afBuscarPorMarcaModelo()"
                      style="cursor: pointer"
                    />
                  </template>
                </q-input>
                <q-input
                  v-if="editAnio"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorAnio()"
                  v-model.number="anio"
                  label="Buscar por año"
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
                      v-on:click="afBuscarPorAnio()"
                      style="cursor: pointer"
                    />
                  </template>
                </q-input>
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
                <q-input
                  v-if="editPatente"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorPatente()"
                  v-model="patente"
                  label="Buscar por patente"
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
                      v-on:click="afBuscarPorPatente()"
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
                  @click="fMostrarEditarCamion(props)"
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
                  @click="fMostrarEliminarCamion(props)"
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
                  @click="fMostrarReciclarCamion(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td>
                {{ props.row.marcaModelo}}
              </q-td>
              <q-td class="text-center">
                {{ props.row.anio }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.cantidadNeumaticos }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.patente }}
              </q-td>
              <q-td class="text-center">
                {{ props.row.peso }}
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.anio != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.anio }}</div>
                    <div class="row paleta1-color2">Año modelo</div>
                  </div>
                  <div v-if="props.row.cantidadNeumaticos != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.cantidadNeumaticos }}</div>
                    <div class="row paleta1-color2">Cantidad neumaticos</div>
                  </div>
                  <div v-if="props.row.marcaModelo != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.marcaModelo }}</div>
                    <div class="row paleta1-color2">Marca/modelo</div>
                  </div>
                  <div v-if="props.row.numeroChasis != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.numeroChasis }}</div>
                    <div class="row paleta1-color2">Número chasis</div>
                  </div>
                  <div v-if="props.row.numeroMotor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.numeroMotor }}</div>
                    <div class="row paleta1-color2">Número motor</div>
                  </div>
                  <div v-if="props.row.patente != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.patente }}</div>
                    <div class="row paleta1-color2">Patente</div>
                  </div>
                  <div v-if="props.row.peso != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.peso }}</div>
                    <div class="row paleta1-color2">Peso</div>
                  </div>
                  <div v-if="props.row.aseguradora != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.aseguradora }}</div>
                    <div class="row paleta1-color2">Aseguradora</div>
                  </div>
                  <div v-if="props.row.vencimiento != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">props.row.vencimiento</div>
                    <div class="row paleta1-color2">Vencimiento</div>
                  </div>
                  <div v-if="props.row.creador != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.creador }}</div>
                    <div class="row paleta1-color2">Creador</div>
                  </div>
                  <div v-if="props.row.creada != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.creada) }}</div>
                    <div class="row paleta1-color2">Creado</div>
                  </div>
                  <div v-if="props.row.modificador != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.modificador }}</div>
                    <div class="row paleta1-color2">Modificador</div>
                  </div>
                  <div v-if="props.row.modificada != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ fFormatoFecha(props.row.modificada) }}</div>
                    <div class="row paleta1-color2">Modificado</div>
                  </div>
                  <div v-if="props.row.eliminador != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.eliminador }}</div>
                    <div class="row paleta1-color2">Eliminador</div>
                  </div>
                  <div v-if="props.row.eliminada != null && autoridad.value === 'admin'" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
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

  <q-dialog v-model="nuevoCamionDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuevo camion</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarCamion">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model.number="camionCreation.anio"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Año modelo"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model.number="camionCreation.cantidadNeumaticos"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Cantidad neumaticos"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="camionCreation.marcaModelo"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Marca y modelo"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="camionCreation.patente"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Número de patente"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="camionCreation.numeroChasis"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Número del chasis"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="camionCreation.numeroMotor"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Número del motor"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model.number="camionCreation.peso"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Peso del equipo"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="camionCreation.notas"
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
import { ayuda } from 'app/src/helpers/ayuda'
import { CamionCreation } from 'src/models/creation/camion_creation'
import { camionService } from 'src/services/camion_service'
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
    name: 'marcaModelo',
    label: 'Marca/modelo',
    align: 'left',
    field: 'marcaModelo',
    sortable: true
  },
  {
    name: 'anio',
    label: 'Año modelo',
    align: 'center',
    field: 'anio',
    sortable: true
  },
  {
    name: 'cantidadNeumaticos',
    label: 'Neumaticos',
    align: 'center',
    field: 'cantidadNeumaticos',
    sortable: true
  },
  {
    name: 'patente',
    label: 'Patente',
    align: 'center',
    field: 'patente',
    sortable: true
  },
  {
    name: 'peso',
    label: 'Peso',
    align: 'center',
    field: 'peso'
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())

    const anio = ref(null)
    const camionCreation = reactive(new CamionCreation())
    const camiones = ref([])
    const editAnio = ref(false)
    const editMarcaModelo = ref(false)
    const editNotas = ref(false)
    const editPatente = ref(false)
    const marcaModelo = ref(null)
    const notas = ref(null)
    const nuevoCamionDialog = ref(false)
    const nuevaBusqueda = ref(false)
    const paso1 = ref(true)
    const patente = ref(null)
    const reglas = reactive(reglasValidacion.reglas)

    afBuscarPaginadas()

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
          resultado = await camionService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await camionService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          camiones.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          camiones.value = []
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

    async function afBuscarPorAnio () {
      if (anio.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await camionService.spfBuscarTodasPorAnioConEliminadas(anio.value)
          } else {
            resultado = await camionService.spfBuscarTodasPorAnio(anio.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            camiones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            camiones.value = []
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

    async function afBuscarPorMarcaModelo () {
      if (marcaModelo.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await camionService.spfBuscarTodasPorMarcaModeloConEliminadas(marcaModelo.value)
          } else {
            resultado = await camionService.spfBuscarTodasPorMarcaModelo(marcaModelo.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            camiones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            camiones.value = []
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
            resultado = await camionService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await camionService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            camiones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            camiones.value = []
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

    async function afBuscarPorPatente () {
      if (patente.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await camionService.spfBuscarTodasPorPatenteConEliminadas(patente.value)
          } else {
            resultado = await camionService.spfBuscarTodasPorPatente(patente.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            camiones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            camiones.value = []
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

    async function afGuardarCamion () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await camionService.spfGuardar(camionCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el camion.')
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

    async function afEliminarCamion (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await camionService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el camion.')
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

    async function afReciclarCamion (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await camionService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el camion.')
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

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarCamion () {
      afGuardarCamion().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoCamionDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      camionCreation.anio = null
      camionCreation.cantidadNeumaticos = null
      camionCreation.marcaModelo = null
      camionCreation.notas = null
      camionCreation.patente = null
      camionCreation.peso = null
      camionCreation.numeroChasis = null
      camionCreation.numeroMotor = null

      camionCreation.id = null
      camionCreation.creadorId = null
      camionCreation.creador = null
      camionCreation.creada = null
      camionCreation.modificadorId = null
      camionCreation.modificador = null
      camionCreation.modificada = null
      camionCreation.eliminadorId = null
      camionCreation.eliminador = null
      camionCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      anio.value = null
      editAnio.value = null
      editMarcaModelo.value = null
      editNotas.value = null
      editPatente.value = null
      marcaModelo.value = null
      notas.value = null
      patente.value = null
    }

    function fMostrarAnio () {
      fLimpiarInputs()
      editAnio.value = true
    }

    function fMostrarMarcaModelo () {
      fLimpiarInputs()
      editMarcaModelo.value = true
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarPatente () {
      fLimpiarInputs()
      editPatente.value = true
    }

    function fMostrarNuevoCamion () {
      fLimpiarFormulario()
      fIrPaso1()
      nuevoCamionDialog.value = true
    }

    function fMostrarEditarCamion (props) {
      camionCreation.id = props.row.id
      camionCreation.anio = props.row.anio
      camionCreation.cantidadNeumaticos = props.row.cantidadNeumaticos
      camionCreation.marcaModelo = props.row.marcaModelo
      camionCreation.patente = props.row.patente
      camionCreation.peso = props.row.peso
      camionCreation.notas = props.row.notas
      camionCreation.numeroChasis = props.row.numeroChasis
      camionCreation.numeroMotor = props.row.numeroMotor

      camionCreation.creada = props.row.creada
      camionCreation.creadorId = props.row.creadorId
      camionCreation.eliminada = props.row.eliminada
      camionCreation.eliminadorId = props.row.eliminadorId
      camionCreation.modificada = props.row.modificada
      camionCreation.modificadorId = props.row.modificadorId

      nuevoCamionDialog.value = true
    }

    function fMostrarEliminarCamion (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarCamion(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarCamion (props) {
      afReciclarCamion(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      camionCreation,
      camiones,
      afBuscarPorAnio,
      afBuscarPorMarcaModelo,
      afBuscarPorNotas,
      afBuscarPorPatente,
      anio,
      columnas,
      marcaModelo,
      editAnio,
      editMarcaModelo,
      editNotas,
      editPatente,
      fFormatoFecha,
      fGuardarCamion,
      fMostrarAnio,
      fMostrarMarcaModelo,
      fMostrarPatente,
      fMostrarNotas,
      fMostrarNuevoCamion,
      fMostrarEditarCamion,
      fMostrarEliminarCamion,
      fMostrarReciclarCamion,
      notas,
      nuevaBusqueda,
      nuevoCamionDialog,
      paginacion,
      paso1,
      patente,
      reglas
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
  width: 250px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 250px;
}
</style>
