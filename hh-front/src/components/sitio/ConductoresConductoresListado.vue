<template>
  <q-card class="font-5 no-shadow no-border">
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Conductores"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="conductores"
        row-key="id"
      >
        <template v-slot:top-left>
          <div class="column">
            <p class="text-h5">Conductores</p>
            <q-btn class="paleta2-fondo2 paleta1-color1 q-mb-lg" icon="add_circle" label="Nuevo conductor" @click="fMostrarNuevoCliente" />
          </div>
        </template>
        <template v-slot:top-right>
          <div class="column items-end">
            <div class="q-my-md">
              <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar conductores por" dropdown-icon="fa-solid fa-magnifying-glass">
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
                v-if="props.row.eliminada === null"
                size="sm"
                class="text-white paleta5-fondo2 q-mr-xs"
                round
                dense
                @click="fMostrarEditarConductor(props)"
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
                @click="fMostrarEliminarConductor(props)"
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
                @click="fMostrarReciclarConductor(props)"
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
                  <div class="row text-white">{{ props.row.telefono }}</div>
                  <div class="row paleta1-color2">Telefono</div>
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

  <q-dialog v-model="nuevoConductorDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuevo conductor</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarCliente">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="conductorCreation.nombre"
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
                v-model="conductorCreation.direccion"
                :rules="[reglas.requerido, reglas.min8, reglas.max50]"
                outlined
                dense
                clearable
                label="Dirección"
                hint="Dirección del conductor"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="conductorCreation.email"
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
                v-model="conductorCreation.telefono"
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
                v-model="conductorCreation.identificacion"
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
                v-model="conductorCreation.notas"
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
import { ConductorCreation } from 'src/models/creation/conductor_creation'
import { conductorService } from 'src/services/conductor_service'
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
    label: 'Expandir',
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

    const conductorCreation = reactive(new ConductorCreation())
    const conductores = ref([])
    const direccion = ref(null)
    const editDireccion = ref(false)
    const editEmail = ref(false)
    const editIdentifiacion = ref(false)
    const editNombre = ref(true)
    const editNotas = ref(false)
    const editTelefono = ref(false)
    const email = ref(null)
    const esAdmin = ref(autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN))
    const identificacion = ref(null)
    const nombre = ref(null)
    const notas = ref(null)
    const nuevaBusqueda = ref(false)
    const nuevoConductorDialog = ref(false)
    const paso1 = ref(true)
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
        if (esAdmin.value) {
          resultado = await conductorService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await conductorService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          conductores.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          conductores.value = []
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
          if (esAdmin.value) {
            resultado = await conductorService.spfBuscarTodasPorDireccionConEliminadas(direccion.value)
          } else {
            resultado = await conductorService.spfBuscarTodasPorDireccion(direccion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            conductores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            conductores.value = []
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
          if (esAdmin.value) {
            resultado = await conductorService.spfBuscarTodasPorEmailConEliminadas(email.value)
          } else {
            resultado = await conductorService.spfBuscarTodasPorEmail(email.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            conductores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            conductores.value = []
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
          if (esAdmin.value) {
            resultado = await conductorService.spfBuscarTodasPorIdentificacionConEliminadas(identificacion.value)
          } else {
            resultado = await conductorService.spfBuscarTodasPorIdentificacion(identificacion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            conductores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            conductores.value = []
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
            resultado = await conductorService.spfBuscarTodasPorNombreConEliminadas(nombre.value)
          } else {
            resultado = await conductorService.spfBuscarTodasPorNombre(nombre.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            conductores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            conductores.value = []
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
            resultado = await conductorService.spfBuscarTodasPorNotasConEliminadas(notas.value)
          } else {
            resultado = await conductorService.spfBuscarTodasPorNotas(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            conductores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            conductores.value = []
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
          if (esAdmin.value) {
            resultado = await conductorService.spfBuscarTodasPorTelefonoConEliminadas(telefono.value)
          } else {
            resultado = await conductorService.spfBuscarTodasPorTelefono(telefono.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            conductores.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            conductores.value = []
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

    async function afGuardarCliente () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await conductorService.spfGuardar(conductorCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el conductor.')
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

    function fGuardarCliente () {
      afGuardarCliente().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoConductorDialog.value = false
          fIrPaso1()
        })
      })
    }

    async function afEliminarCliente (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await conductorService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el conductor.')
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

    async function afReciclarCliente (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await conductorService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el conductor.')
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

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      conductorCreation.direccion = null
      conductorCreation.email = null
      conductorCreation.identificacion = null
      conductorCreation.nombre = null
      conductorCreation.notas = null
      conductorCreation.telefono = null

      conductorCreation.id = null
      conductorCreation.creadorId = null
      conductorCreation.creador = null
      conductorCreation.creada = null
      conductorCreation.modificadorId = null
      conductorCreation.modificador = null
      conductorCreation.modificada = null
      conductorCreation.eliminadorId = null
      conductorCreation.eliminador = null
      conductorCreation.eliminada = null
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

    function fMostrarNuevoCliente () {
      fIrPaso1()
      nuevoConductorDialog.value = true
    }

    function fMostrarEditarConductor (props) {
      conductorCreation.id = props.row.id
      conductorCreation.nombre = props.row.nombre
      conductorCreation.direccion = props.row.direccion
      conductorCreation.email = props.row.email
      conductorCreation.telefono = props.row.telefono
      conductorCreation.identificacion = props.row.identificacion
      conductorCreation.notas = props.row.notas

      conductorCreation.creada = props.row.creada
      conductorCreation.creadorId = props.row.creadorId
      conductorCreation.eliminada = props.row.eliminada
      conductorCreation.eliminadorId = props.row.eliminadorId
      conductorCreation.modificada = props.row.modificada
      conductorCreation.modificadorId = props.row.modificadorId

      nuevoConductorDialog.value = true
    }

    function fMostrarEliminarConductor (props) {
      afEliminarCliente(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    function fMostrarReciclarConductor (props) {
      afReciclarCliente(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      afBuscarPorDireccion,
      afBuscarPorEmail,
      afBuscarPorIdentificacion,
      afBuscarPorNombre,
      afBuscarPorNotas,
      afBuscarPorTelefono,
      conductorCreation,
      conductores,
      columnas,
      direccion,
      editDireccion,
      editEmail,
      editIdentifiacion,
      editNombre,
      editNotas,
      editTelefono,
      email,
      esAdmin,
      fFormatoFecha,
      fGuardarCliente,
      fMostrarDireccion,
      fMostrarEmail,
      fMostrarIdentificacion,
      fMostrarNombre,
      fMostrarNotas,
      fMostrarNuevoCliente,
      fMostrarTelefono,

      fMostrarEditarConductor,
      fMostrarEliminarConductor,
      fMostrarReciclarConductor,

      identificacion,
      nombre,
      notas,
      nuevaBusqueda,
      nuevoConductorDialog,
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
  width: 280px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 250px;
}
</style>
