<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Proveedores"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="proveedores"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Proveedores</p>
              <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo proveedor" @click="fMostrarNuevoProveedor" />
            </div>
          </template>
          <template v-slot:top-right>
            <div class="column items-end">
              <div class="q-my-md">
                <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar proveedores por" dropdown-icon="fa-solid fa-magnifying-glass">
                  <q-list>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarDireccion">
                      <q-item-section avatar>
                        <q-icon name="home" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Dirección</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarEmail">
                      <q-item-section avatar>
                        <q-icon name="alternate_email" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Email</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarIdentificacion">
                      <q-item-section avatar>
                        <q-icon name="badge" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Identificacion</q-item-label>
                      </q-item-section>
                    </q-item>
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNombre">
                      <q-item-section avatar>
                        <q-icon name="account_circle" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Nombre</q-item-label>
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
                    <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarTelefono">
                      <q-item-section avatar>
                        <q-icon name="phone_iphone" />
                      </q-item-section>
                      <q-item-section>
                        <q-item-label>Teléfono</q-item-label>
                      </q-item-section>
                    </q-item>
                  </q-list>
                </q-btn-dropdown>
              </div>
              <div class="col">
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
                    <q-icon
                      name="fa-solid fa-magnifying-glass"
                      class="q-mx-xs"
                      v-on:click="afBuscarPorDireccion()"
                      style="cursor: pointer"
                    />
                  </template>
                </q-input>
                <q-input
                  v-if="editEmail"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorEmail()"
                  v-model="email"
                  label="Buscar por email"
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
                      v-on:click="afBuscarPorEmail()"
                      style="cursor: pointer"
                    />
                  </template>
                </q-input>
                <q-input
                  v-if="editIdentifiacion"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorIdentificacion()"
                  v-model="identificacion"
                  label="Buscar por identificacion"
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
                      v-on:click="afBuscarPorIdentificacion()"
                      style="cursor: pointer"
                    />
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
                    <q-icon
                      name="fa-solid fa-magnifying-glass"
                      class="q-mx-xs"
                      v-on:click="afBuscarPorNombre()"
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
                  v-if="editTelefono"
                  outlined
                  dense
                  clearable
                  v-on:keyup.enter="afBuscarPorTelefono()"
                  v-model="telefono"
                  label="Buscar por telefono"
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
                      v-on:click="afBuscarPorTelefono()"
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
                  @click="fMostrarEditarProveedor(props)"
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
                  @click="fMostrarEliminarProveedor(props)"
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
                  @click="fMostrarReciclarProveedor(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td>
                {{ props.row.nombre }}
              </q-td>
              <q-td>
                {{ props.row.direccion}}
              </q-td>
              <q-td>
                {{ props.row.email }}
              </q-td>
              <q-td>
                {{ props.row.identificacion }}
              </q-td>
              <q-td>
                {{ props.row.telefono }}
              </q-td>
            </q-tr>
            <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
              <q-td colspan="100%">
                <div class="row">
                  <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.id }}</div>
                    <div class="row paleta1-color2">Id</div>
                  </div>
                  <div v-if="props.row.direccion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{ props.row.direccion }}</div>
                    <div class="row paleta1-color2">Direccion</div>
                  </div>
                  <div v-if="props.row.email != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.email }}</div>
                    <div class="row paleta1-color2">Email</div>
                  </div>
                  <div v-if="props.row.identificacion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.identificacion }}</div>
                    <div class="row paleta1-color2">Identificacion</div>
                  </div>
                  <div v-if="props.row.nombre != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                    <div class="row text-white">{{ props.row.nombre }}</div>
                    <div class="row paleta1-color2">Nombre</div>
                  </div>
                  <div v-if="props.row.telefono != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista" >
                    <div class="row text-white">{{props.row.telefono}}</div>
                    <div class="row paleta1-color2">Telefono</div>
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

  <q-dialog v-model="nuevoProveedorDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuevo proveedor</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarProveedor">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="proveedorCreation.nombre"
                :rules="[reglas.requerido, reglas.min8, reglas.max50]"
                outlined
                dense
                clearable
                label="Nombre"
                hint="Apellido y nombre"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="proveedorCreation.direccion"
                :rules="[reglas.requerido, reglas.min8, reglas.max50]"
                outlined
                dense
                clearable
                label="Dirección"
                hint="Dirección del proveedor"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="proveedorCreation.email"
                :rules="[reglas.requerido, reglas.min8, reglas.max50, reglas.email]"
                outlined
                dense
                clearable
                label="Email"
                hint="Dirección de email"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="proveedorCreation.telefono"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Teléfono"
                hint="Número de teléfono"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="proveedorCreation.identificacion"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Identificacion"
                hint="Documento/Pasaporte"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="proveedorCreation.notas"
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
import { ProveedorCreation } from 'src/models/creation/proveedor_creation'
import { proveedorService } from 'src/services/proveedor_service'
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
    name: 'nombre',
    label: 'Nombre',
    align: 'left',
    field: 'nombre',
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
    name: 'email',
    label: 'Email',
    align: 'left',
    field: 'email',
    sortable: true
  },
  {
    name: 'identificacion',
    label: 'Identificacion',
    align: 'left',
    field: 'identificacion',
    sortable: true
  },
  {
    name: 'telefono',
    label: 'telefono',
    align: 'left',
    field: 'telefono'
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())

    const direccion = ref(null)
    const editDireccion = ref(false)
    const editEmail = ref(false)
    const editIdentifiacion = ref(false)
    const editNombre = ref(true)
    const editNotas = ref(false)
    const editTelefono = ref(false)
    const email = ref(null)
    const identificacion = ref(null)
    const nombre = ref(null)
    const notas = ref(null)
    const nuevaBusqueda = ref(false)
    const nuevoProveedorDialog = ref(false)
    const paso1 = ref(true)
    const proveedorCreation = reactive(new ProveedorCreation())
    const proveedores = ref([])
    const reglas = reactive(reglasValidacion.reglas)
    const telefono = ref(null)

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
          resultado = await proveedorService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await proveedorService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          proveedores.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          proveedores.value = []
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

    async function afBuscarPorDireccion () {
      if (direccion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await proveedorService.spfBuscarTodasPorDireccionConEliminadas(direccion.value)
          } else {
            resultado = await proveedorService.spfBuscarTodasPorDireccion(direccion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            proveedores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            proveedores.value = []
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

    async function afBuscarPorEmail () {
      if (email.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await proveedorService.spfBuscarTodasPorEmailConEliminadas(email.value)
          } else {
            resultado = await proveedorService.spfBuscarTodasPorEmail(email.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            proveedores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            proveedores.value = []
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

    async function afBuscarPorIdentificacion () {
      if (identificacion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await proveedorService.spfBuscarTodasPorIdentificacionConEliminadas(identificacion.value)
          } else {
            resultado = await proveedorService.spfBuscarTodasPorIdentificacion(identificacion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            proveedores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            proveedores.value = []
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
          if (autoridad.value === 'admin') {
            resultado = await proveedorService.spfBuscarTodasPorNombreConEliminadas(nombre.value)
          } else {
            resultado = await proveedorService.spfBuscarTodasPorNombre(nombre.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            proveedores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            proveedores.value = []
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
            resultado = await proveedorService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await proveedorService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            proveedores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            proveedores.value = []
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

    async function afBuscarPorTelefono () {
      if (telefono.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await proveedorService.spfBuscarTodasPorTelefonoConEliminadas(telefono.value)
          } else {
            resultado = await proveedorService.spfBuscarTodasPorTelefono(telefono.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            proveedores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            proveedores.value = []
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

    async function afGuardarProveedor () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await proveedorService.spfGuardar(proveedorCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el proveedor.')
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

    async function afEliminarProveedor (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await proveedorService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el proveedor.')
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

    async function afReciclarProveedor (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await proveedorService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el proveedor.')
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

    function fGuardarProveedor () {
      afGuardarProveedor().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoProveedorDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      proveedorCreation.direccion = null
      proveedorCreation.email = null
      proveedorCreation.identificacion = null
      proveedorCreation.nombre = null
      proveedorCreation.notas = null
      proveedorCreation.telefono = null

      proveedorCreation.id = null
      proveedorCreation.creadorId = null
      proveedorCreation.creador = null
      proveedorCreation.creada = null
      proveedorCreation.modificadorId = null
      proveedorCreation.modificador = null
      proveedorCreation.modificada = null
      proveedorCreation.eliminadorId = null
      proveedorCreation.eliminador = null
      proveedorCreation.eliminada = null
    }

    function fLimpiarInputs (actual) {
      direccion.value = null
      editDireccion.value = null
      editEmail.value = null
      editIdentifiacion.value = null
      editNombre.value = null
      editNotas.value = null
      editTelefono.value = null
      email.value = null
      identificacion.value = null
      nombre.value = null
      notas.value = null
      telefono.value = null
    }

    function fMostrarDireccion () {
      fLimpiarInputs()
      editDireccion.value = true
    }

    function fMostrarEmail () {
      fLimpiarInputs()
      editEmail.value = true
    }

    function fMostrarIdentificacion () {
      fLimpiarInputs()
      editIdentifiacion.value = true
    }

    function fMostrarNombre () {
      fLimpiarInputs()
      editNombre.value = true
    }

    function fMostrarNotas () {
      fLimpiarInputs()
      editNotas.value = true
    }

    function fMostrarTelefono () {
      fLimpiarInputs()
      editTelefono.value = true
    }

    function fMostrarNuevoProveedor () {
      fIrPaso1()
      nuevoProveedorDialog.value = true
    }

    function fMostrarEditarProveedor (props) {
      proveedorCreation.id = props.row.id
      proveedorCreation.direccion = props.row.direccion
      proveedorCreation.email = props.row.email
      proveedorCreation.identificacion = props.row.identificacion
      proveedorCreation.nombre = props.row.nombre
      proveedorCreation.telefono = props.row.telefono
      proveedorCreation.notas = props.row.notas

      proveedorCreation.creada = props.row.creada
      proveedorCreation.creadorId = props.row.creadorId
      proveedorCreation.eliminada = props.row.eliminada
      proveedorCreation.eliminadorId = props.row.eliminadorId
      proveedorCreation.modificada = props.row.modificada
      proveedorCreation.modificadorId = props.row.modificadorId

      nuevoProveedorDialog.value = true
    }

    function fMostrarEliminarProveedor (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarProveedor(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarProveedor (props) {
      afReciclarProveedor(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      afBuscarPorDireccion,
      afBuscarPorEmail,
      afBuscarPorIdentificacion,
      afBuscarPorNombre,
      afBuscarPorNotas,
      afBuscarPorTelefono,
      proveedorCreation,
      proveedores,
      columnas,
      direccion,
      editDireccion,
      editEmail,
      editIdentifiacion,
      editNombre,
      editNotas,
      editTelefono,
      email,
      fFormatoFecha,
      fGuardarProveedor,
      fMostrarDireccion,
      fMostrarEmail,
      fMostrarIdentificacion,
      fMostrarNombre,
      fMostrarNotas,
      fMostrarNuevoProveedor,
      fMostrarTelefono,

      fMostrarEditarProveedor,
      fMostrarEliminarProveedor,
      fMostrarReciclarProveedor,

      identificacion,
      nombre,
      notas,
      nuevaBusqueda,
      nuevoProveedorDialog,
      paginacion,
      paso1,
      telefono,
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
  width: 290px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 250px;
}
</style>
