<template>
  <q-card class="font-5 no-shadow no-border">
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Direcciones"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="direcciones"
        row-key="id"
      >
        <template v-slot:top-left>
          <div class="column">
            <p class="text-h5">Direcciones</p>
            <q-btn
              class="paleta2-fondo2 paleta1-color1 q-mb-lg"
              icon="add_circle"
              label="Nueva direccion"
              @click="fMostrarNuevaDireccion"
            />
          </div>
        </template>
        <template v-slot:top-right>
          <div class="column items-end">
            <div class="q-my-md">
              <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar direcciones por" dropdown-icon="fa-solid fa-magnifying-glass">
                <q-list>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarCiudad">
                    <q-item-section avatar>
                      <q-icon name="location_city" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Ciudad</q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
                <q-list>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccion">
                    <q-item-section avatar>
                      <q-icon name="home" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Dirección</q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
                <q-list>
                  <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNombre">
                    <q-item-section avatar>
                      <q-icon name="contact_mail" />
                    </q-item-section>
                    <q-item-section>
                      <q-item-label>Nombre</q-item-label>
                    </q-item-section>
                  </q-item>
                </q-list>
                <q-list>
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
                v-if="editCiudad"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="ciudad"
                option-value="ciudad"
                option-label="ciudad"
                label="Buscar por ciudad"
                use-input
                hide-selected
                fill-input
                :options="ciudades"
                @filter="fFiltrarCiudades"
                @update:model-value="afBuscarPorCiudad()"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
              <q-input
                v-if="editDireccion"
                outlined
                dense
                clearable
                v-on:keyup.enter="afBuscarPorDireccion()"
                v-model="direccion"
                label="Buscar por direccion"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
                <template v-slot:after>
                  <q-icon name="fa-solid fa-magnifying-glass" class="q-mx-xs" v-on:click="afBuscarPorDireccion()" style="cursor: pointer" />
                </template>
              </q-input>
              <q-input
                v-if="editNombre"
                outlined
                dense
                clearable
                v-on:keyup.enter="afBuscarPorNombre()"
                v-model="nombre"
                label="Buscar por nombre"
                hint="Tenés que escribir al menos 3 caracteres para buscar."
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
                <template v-slot:after>
                  <q-icon name="fa-solid fa-magnifying-glass" class="q-mx-xs" v-on:click="afBuscarPorNombre()" style="cursor: pointer" />
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
                  <q-icon name="fa-solid fa-magnifying-glass" class="q-mx-xs" v-on:click="afBuscarPorNotas()" style="cursor: pointer" />
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
                v-if="props.row.eliminada === null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarEditarDireccion(props)"
              >
                <q-icon size="2em" class="q-pa-xs" name="edit" />
                <q-tooltip>
                  Modificar
                </q-tooltip>
              </q-btn>
              <q-btn
                v-if="props.row.eliminada === null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarEliminarDireccion(props)"
              >
                <q-icon size="2em" class="q-pa-xs" name="delete" />
                <q-tooltip>
                  Eliminar
                </q-tooltip>
              </q-btn>
              <q-btn
                v-if="props.row.eliminada !== null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarReciclarDireccion(props)"
              >
                <q-icon size="2em" class="q-pa-xs" name="recycling" />
                <q-tooltip>
                  Reciclar
                </q-tooltip>
              </q-btn>
            </q-td>
            <q-td>
              {{ props.row.ciudad }}
            </q-td>
            <q-td>
              {{ props.row.direccion}}
            </q-td>
            <q-td>
              {{ props.row.nombre }}
            </q-td>
            <q-td>
              {{ props.row.notas }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
            <q-td colspan="100%">
              <div class="row">
                <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.id }}</div>
                  <div class="row paleta1-color2">Id</div>
                </div>
                <div v-if="props.row.ciudad != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                  <div class="row text-white">{{ props.row.ciudad }}</div>
                  <div class="row paleta1-color2">Ciudad</div>
                </div>
                <div v-if="props.row.direccion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.direccion }}</div>
                  <div class="row paleta1-color2">Direccion</div>
                </div>
                <div v-if="props.row.nombre != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.nombre }}</div>
                  <div class="row paleta1-color2">Nombre</div>
                </div>
                <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.notas }}</div>
                  <div class="row paleta1-color2">Notas</div>
                </div>
                <div v-if="props.row.creador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.creador }}</div>
                  <div class="row paleta1-color2">Creador</div>
                </div>
                <div v-if="props.row.creada != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ fFormatoFecha(props.row.creada) }}</div>
                  <div class="row paleta1-color2">Creado</div>
                </div>
                <div v-if="props.row.modificador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.modificador }}</div>
                  <div class="row paleta1-color2">Modificador</div>
                </div>
                <div v-if="props.row.modificada != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ fFormatoFecha(props.row.modificada) }}</div>
                  <div class="row paleta1-color2">Modificado</div>
                </div>
                <div v-if="props.row.eliminador != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.eliminador }}</div>
                  <div class="row paleta1-color2">Eliminador</div>
                </div>
                <div v-if="props.row.eliminada != null && esAdmin" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
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

  <q-dialog v-model="nuevoDireccionDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nueva direccion</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarDireccion">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="direccionCreation.ciudad"
                :rules="[reglas.requerido]"
                :options="ciudades"
                option-value="ciudad"
                option-label="ciudad"
                label="Ciudad"
                use-input
                input-debounce="0"
                @filter="fFiltrarCiudades"
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
                v-model="direccionCreation.direccion"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Dirección"
                hint="Dirección del direccion"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="direccionCreation.nombre"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Nombre dirección"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="direccionCreation.notas"
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
import { autenticacionService } from 'src/services/autenticacion_service'
import { ayuda } from 'app/src/helpers/ayuda'
import { DireccionCreation } from 'src/models/creation/direccion_creation'
import { direccionService } from 'src/services/direccion_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { rolEnum } from 'src/models/enums/rol_enum'
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
    name: 'ciudad',
    label: 'Ciudad',
    align: 'left',
    field: 'ciudad',
    sortable: true
  },
  {
    name: 'direccion',
    label: 'Direccion',
    align: 'left',
    field: 'direccion',
    sortable: true
  },
  {
    name: 'nombre',
    label: 'Nombre',
    align: 'left',
    field: 'nombre',
    sortable: true
  },
  {
    name: 'notas',
    label: 'Notas',
    align: 'left',
    field: 'notas',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()

    const ciudad = ref(null)
    const ciudades = ref([])
    const ciudadesList = ref([])
    const direccionCreation = reactive(new DireccionCreation())
    const direccion = ref(null)
    const direcciones = ref([])
    const editCiudad = ref(false)
    const editDireccion = ref(false)
    const editNombre = ref(true)
    const editNotas = ref(false)
    const esAdmin = ref(autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN))
    const nombre = ref(null)
    const notas = ref(null)
    const nuevaBusqueda = ref(false)
    const nuevoDireccionDialog = ref(false)
    const paso1 = ref(true)
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(uuidv4())

    afBuscarPaginadas()

    async function afBuscarCiudades () {
      $q.loading.show()
      try {
        let resultado = null
        if (llaveroService.obtenerDeLocalConSesion('hhCiudadesTodasConSesion', sesion.value) !== null) {
          ciudadesList.value = llaveroService.obtenerDeLocalConSesion('hhCiudadesTodasConSesion', sesion.value).value
          console.log('DireccionService: Sesion recargada, con eliminadas.')
        } else {
          resultado = await direccionService.spfBuscarTodasCiudadesConSesion()
          if (resultado.status === 200) {
            ciudadesList.value = resultado.data
            console.log('DireccionService: ' + resultado.headers.mensaje)
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
        if (esAdmin.value) {
          resultado = await direccionService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await direccionService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          direcciones.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          direcciones.value = []
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

    async function afBuscarPorCiudad () {
      if (ciudad.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await direccionService.spfBuscarTodasPorCiudadConEliminadas(ciudad.value)
          } else {
            resultado = await direccionService.spfBuscarTodasPorCiudad(ciudad.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            direcciones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            direcciones.value = []
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

    async function afBuscarPorDireccion () {
      if (direccion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await direccionService.spfBuscarTodasPorDireccionConEliminadas(direccion.value)
          } else {
            resultado = await direccionService.spfBuscarTodasPorDireccion(direccion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            direcciones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            direcciones.value = []
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

    async function afBuscarPorNombre () {
      if (nombre.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await direccionService.spfBuscarTodasPorNombreConEliminadas(nombre.value)
          } else {
            resultado = await direccionService.spfBuscarTodasPorNombre(nombre.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            direcciones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            direcciones.value = []
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
          if (esAdmin.value) {
            resultado = await direccionService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await direccionService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            direcciones.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            direcciones.value = []
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

    async function afGuardarDireccion () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await direccionService.spfGuardar(direccionCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente la direccion.')
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

    async function afEliminarDireccion (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await direccionService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el direccion.')
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

    async function afReciclarDireccion (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await direccionService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el direccion.')
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

    function fFiltrarCiudades (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        ciudades.value = ciudadesList.value.filter(
          (v) => v.ciudad.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fGuardarDireccion () {
      afGuardarDireccion().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoDireccionDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      direccionCreation.ciudad = null
      direccionCreation.direccion = null
      direccionCreation.nombre = null
      direccionCreation.notas = null

      direccionCreation.id = null
      direccionCreation.creadorId = null
      direccionCreation.creador = null
      direccionCreation.creada = null
      direccionCreation.modificadorId = null
      direccionCreation.modificador = null
      direccionCreation.modificada = null
      direccionCreation.eliminadorId = null
      direccionCreation.eliminador = null
      direccionCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      editCiudad.value = null
      ciudad.value = null
      ciudades.value = null
      editDireccion.value = null
      direccion.value = null
      editNombre.value = null
      nombre.value = null
      editNotas.value = null
      notas.value = null
    }

    function fMostrarCiudad () {
      afBuscarCiudades().then(() => {
        fLimpiarInputs()
        editCiudad.value = true
      })
    }

    function fMostrarDireccion () {
      fLimpiarInputs()
      editDireccion.value = true
    }

    function fMostrarNombre () {
      fLimpiarInputs()
      editNombre.value = true
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarNuevaDireccion () {
      afBuscarCiudades().then(() => {
        fLimpiarFormulario()
        fIrPaso1()
        nuevoDireccionDialog.value = true
      })
    }

    function fMostrarEditarDireccion (props) {
      direccionCreation.id = props.row.id
      direccionCreation.ciudad = props.row.ciudad
      direccionCreation.direccion = props.row.direccion
      direccionCreation.nombre = props.row.nombre
      direccionCreation.notas = props.row.notas

      direccionCreation.creada = props.row.creada
      direccionCreation.creadorId = props.row.creadorId
      direccionCreation.eliminada = props.row.eliminada
      direccionCreation.eliminadorId = props.row.eliminadorId
      direccionCreation.modificada = props.row.modificada
      direccionCreation.modificadorId = props.row.modificadorId

      nuevoDireccionDialog.value = true
    }

    function fMostrarEliminarDireccion (props) {
      afEliminarDireccion(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarReciclarDireccion (props) {
      afReciclarDireccion(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      afBuscarPorCiudad,
      afBuscarPorDireccion,
      afBuscarPorNombre,
      afBuscarPorNotas,
      ciudad,
      ciudades,
      columnas,
      direccion,
      direccionCreation,
      direcciones,
      editCiudad,
      editDireccion,
      editNombre,
      editNotas,
      esAdmin,
      fFiltrarCiudades,
      fFormatoFecha,
      fGuardarDireccion,
      fMostrarCiudad,
      fMostrarDireccion,
      fMostrarNombre,
      fMostrarNotas,
      fMostrarNuevaDireccion,

      fMostrarEditarDireccion,
      fMostrarEliminarDireccion,
      fMostrarReciclarDireccion,

      nombre,
      notas,
      nuevaBusqueda,
      nuevoDireccionDialog,
      paginacion,
      paso1,
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
  width: 260px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 250px;
}
</style>
