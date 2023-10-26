<template>
  <q-card class="q-ma-md font-5 no-shadow no-border">
    <div class="row justify-around q-pb-md">
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-select
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="telefono"
          :options="nombres"
          option-value="id"
          option-label="nombre"
          label="Buscar por telefono"
          use-input
          input-debounce="0"
          @filter="fFiltrarCompradores"
          @update:model-value="afBuscarPorCompradorId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="monetization_on" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-select
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="identificacion"
          :options="identificaciones"
          option-value="id"
          option-label="nombre"
          label="Buscar por identificacion"
          use-input
          input-debounce="0"
          @filter="fFiltrarVendedores"
          @update:model-value="afBuscarPorVendedorId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-select
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="direccion"
          :options="direcciones"
          option-value="id"
          option-label="nombre"
          label="Buscar por direccion"
          use-input
          input-debounce="0"
          @filter="fFiltrarChoferes"
          @update:model-value="afBuscarPorChoferId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="airline_seat_recline_extra" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-select
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="nombre"
          :options="telefonos"
          option-value="id"
          option-label="direccion"
          label="Buscar por nombre"
          use-input
          input-debounce="0"
          @filter="fFiltrarOrigenes"
          @update:model-value="afBuscarPorOrigenId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location-dot" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-select
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="notas"
          :options="notases"
          option-value="id"
          option-label="direccion"
          label="Buscar por notas"
          use-input
          input-debounce="0"
          @filter="fFiltrarDestinos"
          @update:model-value="afBuscarPorDestinoId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>

      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-select
          outlined
          dense
          emit-value
          map-options
          clearable
          v-model="email"
          option-value="id"
          option-label="modelo"
          label="Buscar por modelo email"
          use-input
          hide-selected
          fill-input
          :options="emails"
          @filter="fFiltrarCamiones"
          @update:model-value="afBuscarPorCamionId()"
          hint="Tenés que escribir al menos 3 caracteres para buscar."
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-truck-moving" class="q-mx-xs" />
          </template>
          <template v-slot:no-option>
            <q-item>
              <q-item-section class="text-grey">
                Sin resultados
              </q-item-section>
            </q-item>
          </template>
        </q-select>
      </div>
    </div>
    <hr />
    <div class="row justify-start q-pb-md">
      <div class="col-lg-4 col-sm-5 col-xs-12 q-pa-md">
        <q-input
          mask="##-##-####"
          v-model="rangoFechas.from"
          outlined
          dense
          clearable
          label="Busqueda entre fechas - Inicio"
          hint="Ingresá la fecha con el formato dd-mm-yyyy. Ej: 20-01-2020"
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-greater-than" class="q-mx-xs" />
          </template>
          <template v-slot:prepend>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                <q-date v-model="rangoFechas.from" mask="DD-MM-YYYY">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="OK" color="primary" flat />
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
      <div class="col-lg-4 col-sm-5 col-xs-12 q-pa-md">
        <q-input
          mask="##-##-####"
          v-model="rangoFechas.to"
          outlined
          dense
          clearable
          label="Busqueda entre fechas - Fin"
          hint="Ingresá la fecha con el formato dd-mm-yyyy. Ej: 30-01-2020"
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-less-than" class="q-mx-xs" />
          </template>
          <template v-slot:prepend>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                <q-date v-model="rangoFechas.to" mask="DD-MM-YYYY">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="OK" color="primary" flat />
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
      <div class="col-lg-4 col-sm-2 col-xs-12 q-pa-md text-center">
        <q-btn v-if="!nuevaBusqueda" class="paleta5-fondo2 paleta1-color1" icon-right="fa-solid fa-magnifying-glass-arrow-right" label="Buscar" v-on:click="afBuscarEntreFechas" />
        <q-btn v-if="nuevaBusqueda" class="paleta9-fondo2 paleta1-color1" icon-right="fa-solid fa-arrow-rotate-left" label="Nueva" v-on:click="fLimpiarInputs" />
      </div>
    </div>
    <hr />
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Viajes"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="clientes"
        row-key="id"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width class="text-center">
              <q-btn size="sm" class="text-white" :class="props.expand ? 'paleta5-fondo2' : 'paleta5-fondo3'" round dense @click="props.expand = !props.expand" :icon="props.expand ? 'remove' : 'add'" />
            </q-td>
            <q-td class="text-center">
              {{ props.row.id }}
            </q-td>
            <q-td>
              {{ props.row.conductor.nombre }}
            </q-td>
            <q-td>
              {{ props.row.email.marca + " " + props.row.email.modelo }}
            </q-td>
            <q-td>
              {{ props.row.identificacion.nombre }}
            </q-td>
            <q-td>
              {{ props.row.telefono.nombre }}
            </q-td>
            <q-td>
              {{ props.row.categoriaViaje.categoria }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.cantidadTransportada }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.kmVacio }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.kmCargado }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.valorKm }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props" class="paleta1-fondo4">
            <q-td colspan="100%">
              <div class="row">
                <div v-if="props.row.id != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.id}}</div>
                  <div class="row paleta1-color2">Id</div>
                </div>
                <div v-if="props.row.cantidadTransportada != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.cantidadTransportada}}</div>
                  <div class="row paleta1-color2">Cantidad transportada</div>
                </div>
                <div v-if="props.row.valorKm != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.valorKm}}</div>
                  <div class="row paleta1-color2">Valor del kilometro</div>
                </div>
                <div v-if="props.row.neto != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.neto}}</div>
                  <div class="row paleta1-color2">Peso neto</div>
                </div>
                <div v-if="props.row.tara != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.tara}}</div>
                  <div class="row paleta1-color2">Tara</div>
                </div>
                <div v-if="props.row.kmCargado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.kmCargado}}</div>
                  <div class="row paleta1-color2">Kilometros cargado</div>
                </div>
                <div v-if="props.row.kmVacio != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.kmVacio}}</div>
                  <div class="row paleta1-color2">Kilometros vacio</div>
                </div>
                <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.notas}}</div>
                  <div class="row paleta1-color2">Notas</div>
                </div>
                <div v-if="props.row.guia != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.guia}}</div>
                  <div class="row paleta1-color2">N° guia</div>
                </div>
                <div v-if="props.row.categoriaViaje != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.categoriaViaje.categoria}}</div>
                  <div class="row paleta1-color2">Categoria viaje</div>
                </div>
                <div v-if="props.row.nombre != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.nombre.ubicacion.ciudad + "-" + props.row.nombre.direccion}}</div>
                  <div class="row paleta1-color2">Origen viaje</div>
                </div>
                <div v-if="props.row.carga != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.carga.ubicacion.ciudad + "-" + props.row.carga.direccion}}</div>
                  <div class="row paleta1-color2">Direccion de carga</div>
                </div>
                <div v-if="props.row.notas != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.notas.ubicacion.ciudad + "-" + props.row.notas.direccion}}</div>
                  <div class="row paleta1-color2">Direccion de notas</div>
                </div>
                <div v-if="props.row.identificacion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.identificacion.nombre}}</div>
                  <div class="row paleta1-color2">Nombre identificacion</div>
                </div>
                <div v-if="props.row.intermediario != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.intermediario.nombre}}</div>
                  <div class="row paleta1-color2">Nombre intermediario</div>
                </div>
                <div v-if="props.row.telefono != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.telefono.nombre}}</div>
                  <div class="row paleta1-color2">Nombre telefono</div>
                </div>
                <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.fecha.fecha}}</div>
                  <div class="row paleta1-color2">Fecha del viaje</div>
                </div>
                <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.conductor.nombre}}</div>
                  <div class="row paleta1-color2">Nombre conductor</div>
                </div>
                <div v-if="props.row.email != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.email.marca}}</div>
                  <div class="row paleta1-color2">Camion</div>
                </div>
                <div v-if="props.row.acoplado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.acoplado.marca}}</div>
                  <div class="row paleta1-color2">Acoplado</div>
                </div>
                <div v-if="props.row.creador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.creador.username}}</div>
                  <div class="row paleta1-color2">Creador</div>
                </div>
                <div v-if="props.row.creado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.creado}}</div>
                  <div class="row paleta1-color2">Creado</div>
                </div>
                <div v-if="props.row.modificador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.modificador.username}}</div>
                  <div class="row paleta1-color2">Modificador</div>
                </div>
                <div v-if="props.row.modificado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.modificado}}</div>
                  <div class="row paleta1-color2">Modificado</div>
                </div>
                <div v-if="props.row.eliminador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.eliminador.username}}</div>
                  <div class="row paleta1-color2">Eliminador</div>
                </div>
                <div v-if="props.row.eliminado != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.elimiando}}</div>
                  <div class="row paleta1-color2">Elimiando</div>
                </div>
              </div>
            </q-td>
          </q-tr>
        </template>
      </q-table>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { notificarService } from 'src/helpers/notificar_service'
import { CLienteModel } from 'src/models/viaje_model'
import { emailService } from 'src/services/conductor_service'
import { direccionService } from 'src/services/camion_service'
import { viajeService } from 'src/services/viaje_service'
import { autenticacionService } from 'src/services/autenticacion_service'
import { rolEnum } from 'src/models/enums/rol_enum'
import { ayuda } from 'app/src/helpers/ayuda'
import { useQuasar } from 'quasar'
import { clienteService } from 'src/services/cliente_service'
import { direccionService } from 'src/services/direccion_service'

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
    name: 'id',
    label: 'Id',
    align: 'center',
    field: 'id',
    sortOrder: 'da',
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
    label: 'Telefono',
    align: 'left',
    field: 'telefono',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const autoridad = ref(autenticacionService.obtenerAutoridades())
    const cliente = reactive(new CLienteModel())
    const clientes = ref([])
    const direccion = ref(null)
    const direcciones = ref([])
    const direccionesList = ref([])
    const email = ref(null)
    const emails = ref([])
    const emailsList = ref([])
    const identificacion = ref(null)
    const identificaciones = ref([])
    const identificacionesList = ref([])
    const nombre = ref(null)
    const nombres = ref([])
    const nombresList = ref([])
    const notas = ref(null)
    const notases = ref([])
    const notasesList = ref([])
    const telefono = ref(null)
    const telefonos = ref([])
    const telefonosList = ref([])

    afBuscarDirecciones()
    afBuscarEmails()
    afBuscarPaginadas()
    afBuscarClientes()
    afBuscarDirecciones()

    async function afBuscarDirecciones () {
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await direccionService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await direccionService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          direccionesList.value = resultado.data
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
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

    async function afBuscarEmails () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await emailService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await emailService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          direccionesList.value = resultado.data
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
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

    async function afBuscarClientes () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await clienteService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await clienteService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          clientes.value = resultado.data
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
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

    async function afBuscarDirecciones () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await direccionService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await direccionService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          direcciones.value = resultado.data
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
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
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await viajeService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          clientes.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          clientes.value = []
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

    async function afBuscarPorCamionId () {
      if (email.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('email')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorCamionIdConEliminadas(email.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCamionId(email.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            clientes.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            clientes.value = []
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

    async function afBuscarPorChoferId () {
      if (direccion.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('direccion')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorChoferIdConEliminadas(direccion.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorChoferId(direccion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            clientes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            clientes.value = []
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

    async function afBuscarPorCompradorId () {
      if (telefono.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('telefono')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorCompradorIdConEliminadas(telefono.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCompradorId(telefono.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            clientes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            clientes.value = []
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

    async function afBuscarPorDestinoId () {
      if (notas.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('notas')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorDireccionDestinoIdConEliminadas(notas.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionDestinoId(notas.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            clientes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            clientes.value = []
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

    async function afBuscarPorOrigenId () {
      if (nombre.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('nombre')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorDireccionOrigenIdConEliminadas(nombre.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDireccionOrigenId(nombre.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            clientes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            clientes.value = []
          }
          if (err.response.status === 404) {
            clientes.value = []
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

    async function afBuscarPorVendedorId () {
      if (identificacion.value != null) {
        $q.loading.show()
        try {
          fLimpiarInputs('identificacion')
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            console.log('aca')
            resultado = await viajeService.spfBuscarTodasPorVendedorIdConEliminadas(identificacion.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorVendedorId(identificacion.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            clientes.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            clientes.value = []
          }
          if (err.response.status === 404) {
            clientes.value = []
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

    function fFiltrarCamiones (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        emails.value = emailsList.value.filter(v => v.modelo.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarChoferes (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        direcciones.value = direccionesList.value.filter(v => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarCompradores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        nombres.value = clientes.value.filter(v => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarDestinos (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        notases.value = direcciones.value.filter(v => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarOrigenes (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        telefonos.value = direcciones.value.filter(v => v.direccion.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFiltrarVendedores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        identificaciones.value = clientes.value.filter(v => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1)
      })
    }

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fLimpiarInputs (actual) {
      rangoFechas.value.from = null
      rangoFechas.value.to = null
      switch (actual) {
        case 'email':
          direccion.value = null
          telefono.value = null
          identificacion.value = null
          nombre.value = null
          notas.value = null
          break
        case 'direccion':
          email.value = null
          telefono.value = null
          identificacion.value = null
          nombre.value = null
          notas.value = null
          break
        case 'telefono':
          email.value = null
          direccion.value = null
          identificacion.value = null
          nombre.value = null
          notas.value = null
          break
        case 'identificacion':
          email.value = null
          direccion.value = null
          telefono.value = null
          nombre.value = null
          notas.value = null
          break
        case 'nombre':
          email.value = null
          direccion.value = null
          telefono.value = null
          identificacion.value = null
          notas.value = null
          break
        case 'notas':
          email.value = null
          direccion.value = null
          telefono.value = null
          identificacion.value = null
          nombre.value = null
          break
        default:
          email.value = null
          direccion.value = null
          telefono.value = null
          identificacion.value = null
          nombre.value = null
          notas.value = null
          nuevaBusqueda.value = false
          clientes.value = []
          break
      }
    }

    return {
      afBuscarEntreFechas,
      afBuscarPorCamionId,
      afBuscarPorChoferId,
      afBuscarPorCompradorId,
      afBuscarPorDestinoId,
      afBuscarPorOrigenId,
      afBuscarPorVendedorId,

      autoridad,
      email,
      emails,
      direccion,
      direcciones,
      columnas,
      telefono,
      nombres,
      notas,
      notases,
      nuevaBusqueda,
      nombre,
      telefonos,
      paginacion,
      rangoFechas,
      identificacion,
      identificaciones,
      viaje,
      clientes,

      fFiltrarCamiones,
      fFiltrarChoferes,
      fFiltrarCompradores,
      fFiltrarDestinos,
      fFiltrarOrigenes,
      fFiltrarVendedores,
      fFormatoFecha,
      fLimpiarInputs
    }
  }
}
</script>
<style scoped>
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
</style>
