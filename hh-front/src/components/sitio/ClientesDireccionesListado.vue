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
              <q-btn-dropdown
                class="paleta2-fondo2 paleta1-color1"
                label="Buscar direcciones por"
                dropdown-icon="fa-solid fa-magnifying-glass"
              >
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
            </div>
          </div>
        </template>
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width class="text-center">
              <q-btn
                size="sm"
                class="text-white"
                :class="props.expand ? 'paleta5-fondo2' : 'paleta5-fondo3'"
                round
                dense
                @click="props.expand = !props.expand"
                :icon="props.expand ? 'remove' : 'add'"
              />
            </q-td>
            <q-td>
              {{ props.row.direccion }}
            </q-td>
            <q-td>
              {{ props.row.ciudad}}
            </q-td>
            <q-td>
              {{ props.row.nombre }}
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
                <div v-if="props.row.ciudad != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.ciudad }}</div>
                  <div class="row paleta1-color2">Ciudad</div>
                </div>
                <div v-if="props.row.nombre != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{ props.row.nombre }}</div>
                  <div class="row paleta1-color2">Nombre</div>
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
                  <div class="row text-white">{{ fFormatoFecha(props.row.elimiando) }}</div>
                  <div class="row paleta1-color2">Elimiando</div>
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

  <q-dialog
    v-model="nuevoClienteDialog"
    persistent
    transition-show="fade"
    transition-hide="fade"
  >
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
        <q-form v-on:submit.prevent="fGuardarCliente">
          <div class="row justify-around">
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                v-model="clienteCreation.nombre"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Nombre"
                hint="Apellido y nombre"
              >
              </q-input>
            </div>
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                v-model="clienteCreation.direccion"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Dirección"
                hint="Dirección del direccion"
              >
              </q-input>
            </div>
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                v-model="clienteCreation.email"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Email"
                hint="Dirección de email"
              >
              </q-input>
            </div>
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                v-model="clienteCreation.telefono"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Teléfono"
                hint="Número de teléfono"
              >
              </q-input>
            </div>
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                v-model="clienteCreation.identificacion"
                :rules="[reglas.requerido]"
                outlined
                dense
                clearable
                label="Identificacion"
                hint="Documento/Pasaporte"
              >
              </q-input>
            </div>
            <div class="col-xs-5 q-mx-xs q-my-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="clienteCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-end q-mr-xl q-my-md">
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
import { ClienteCreation } from 'src/models/creation/cliente_creation'
import { clienteService } from 'src/services/cliente_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { rolEnum } from 'src/models/enums/rol_enum'
import { useQuasar } from 'quasar'
import { v4 as uuidv4 } from 'uuid'

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

    const clienteCreation = reactive(new ClienteCreation())
    const direcciones = ref([])
    const clientesList = ref([])
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
    const nuevoClienteDialog = ref(false)
    const paso1 = ref(true)
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(uuidv4())
    const telefono = ref(null)

    afBuscarPaginadas()

    async function afBuscarClientes () {
      $q.loading.show()
      try {
        let resultado = null
        if (esAdmin.value) {
          if (llaveroService.obtenerDeLocalConSesion('hhClienteTodasConEliminadasConSesion', sesion.value) !== null) {
            clientesList.value = llaveroService.obtenerDeLocalConSesion('hhClienteTodasConEliminadasConSesion', sesion.value).value
            console.log('ClienteService: Sesion recargada, con eliminadas.')
          } else {
            resultado = await clienteService.spfBuscarTodasConEliminadasConSesion(sesion.value)
            if (resultado.status === 200) {
              clientesList.value = resultado.data
              console.log('ClienteService: ' + resultado.headers.mensaje)
            }
          }
        } else {
          if (llaveroService.obtenerDeLocalConSesion('hhClienteTodasConSesion', sesion.value) !== null) {
            clientesList.value = llaveroService.obtenerDeLocalConSesion('hhClienteTodasConSesion', sesion.value).value
            console.log('ClienteService: Sesion recargada.')
          } else {
            resultado = await clienteService.spfBuscarTodasConSesion(sesion.value)
            if (resultado.status === 200) {
              clientesList.value = resultado.data
              console.log('ClienteService: ' + resultado.headers.mensaje)
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
        if (esAdmin.value) {
          resultado = await clienteService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await clienteService.spfBuscarTodasPaginadas(paginadoDTO)
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

    async function afBuscarPorDireccion () {
      if (direccion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await clienteService.spfBuscarTodasPorDireccionConEliminadas(
              direccion.value
            )
          } else {
            resultado = await clienteService.spfBuscarTodasPorDireccion(direccion.value)
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
            notificarService.notificarAlerta(
              'Advertencia: ' + err.response.headers.mensaje
            )
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
            resultado = await clienteService.spfBuscarTodasPorEmailConEliminadas(
              email.value
            )
          } else {
            resultado = await clienteService.spfBuscarTodasPorEmail(email.value)
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
            notificarService.notificarAlerta(
              'Advertencia: ' + err.response.headers.mensaje
            )
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
            resultado = await clienteService.spfBuscarTodasPorIdentificacionConEliminadas(
              identificacion.value
            )
          } else {
            resultado = await clienteService.spfBuscarTodasPorIdentificacion(identificacion.value)
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
            notificarService.notificarAlerta(
              'Advertencia: ' + err.response.headers.mensaje
            )
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
            resultado = await clienteService.spfBuscarTodasPorNombreConEliminadas(
              nombre.value
            )
          } else {
            resultado = await clienteService.spfBuscarTodasPorNombre(nombre.value)
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
            notificarService.notificarAlerta(
              'Advertencia: ' + err.response.headers.mensaje
            )
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
            resultado = await clienteService.spfBuscarTodasPorNotasConEliminadas(
              notas.value
            )
          } else {
            resultado = await clienteService.spfBuscarTodasPorNotas(notas.value)
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
            notificarService.notificarAlerta(
              'Advertencia: ' + err.response.headers.mensaje
            )
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
            resultado = await clienteService.spfBuscarTodasPorTelefonoConEliminadas(
              telefono.value
            )
          } else {
            resultado = await clienteService.spfBuscarTodasPorTelefono(telefono.value)
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
            notificarService.notificarAlerta(
              'Advertencia: ' + err.response.headers.mensaje
            )
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
        resultado = await clienteService.spfGuardar(clienteCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el viaje.')
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          console.info(err.response.headers.mensaje)
          notificarService.infoAlerta(err.response.headers.mensaje)
        } else if (err.response.headers.mensaje) {
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta(
            'Advertencia: ' + err.response.headers.mensaje
          )
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
          nuevoClienteDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      clienteCreation.direccion = null
      clienteCreation.email = null
      clienteCreation.identificacion = null
      clienteCreation.nombre = null
      clienteCreation.notas = null
      clienteCreation.telefono = null
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

    function fMostrarNuevaDireccion () {
      fIrPaso1()
      nuevoClienteDialog.value = true
    }

    return {
      afBuscarPorDireccion,
      afBuscarPorEmail,
      afBuscarPorIdentificacion,
      afBuscarPorNombre,
      afBuscarPorNotas,
      afBuscarPorTelefono,
      clienteCreation,
      direcciones,
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
      fMostrarNuevaDireccion,
      fMostrarTelefono,
      identificacion,
      nombre,
      notas,
      nuevaBusqueda,
      nuevoClienteDialog,
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
