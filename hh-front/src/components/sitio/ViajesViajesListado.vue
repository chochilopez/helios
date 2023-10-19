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
          v-model="comprador"
          :options="compradores"
          option-value="id"
          option-label="nombre"
          label="Buscar por comprador"
          use-input
          input-debounce="0"
          @filter="filtrarCompradores"
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
          v-model="vendedor"
          :options="vendedores"
          option-value="id"
          option-label="nombre"
          label="Buscar por vendedor"
          use-input
          input-debounce="0"
          @filter="filtrarVendedores"
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
          v-model="chofer"
          :options="choferes"
          option-value="id"
          option-label="nombre"
          label="Buscar por chofer"
          use-input
          input-debounce="0"
          @filter="filtrarChoferes"
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
          v-model="origen"
          :options="origenes"
          option-value="id"
          option-label="direccion"
          label="Buscar por origen"
          use-input
          input-debounce="0"
          @filter="filtrarOrigenes"
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
          v-model="destino"
          :options="destinos"
          option-value="id"
          option-label="direccion"
          label="Buscar por destino"
          use-input
          input-debounce="0"
          @filter="filtrarDestinos"
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
          v-model="camion"
          option-value="id"
          option-label="modelo"
          label="Buscar por modelo camion"
          use-input
          hide-selected
          fill-input
          :options="camiones"
          @filter="filtrarCamiones"
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
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12 q-pa-md">
        <q-input outlined dense label="Buscar por fecha" readonly :model-value="rangoFechas.to + ' - ' + rangoFechas.from">
          <template v-slot:after>
            <q-icon name="event" class="cursor-pointer">
              <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                <q-date v-model="rangoFechas" range mask="DD-MM-YYYY">
                  <div class="row items-center justify-end">
                    <q-btn v-close-popup label="Buscar" color="primary" flat v-on:click="afBuscarEntreFechas()" />
                  </div>
                </q-date>
              </q-popup-proxy>
            </q-icon>
          </template>
        </q-input>
      </div>
    </div>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Viajes"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        hide-no-data
        :rows="viajes"
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
              {{ props.row.camion.marca + " " + props.row.camion.modelo }}
            </q-td>
            <q-td>
              {{ props.row.vendedor.nombre }}
            </q-td>
            <q-td>
              {{ props.row.comprador.nombre }}
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
                <div v-if="props.row.origen != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.origen.ubicacion.ciudad + "-" + props.row.origen.direccion}}</div>
                  <div class="row paleta1-color2">Origen viaje</div>
                </div>
                <div v-if="props.row.carga != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.carga.ubicacion.ciudad + "-" + props.row.carga.direccion}}</div>
                  <div class="row paleta1-color2">Direccion de carga</div>
                </div>
                <div v-if="props.row.destino != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.destino.ubicacion.ciudad + "-" + props.row.destino.direccion}}</div>
                  <div class="row paleta1-color2">Direccion de destino</div>
                </div>
                <div v-if="props.row.vendedor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.vendedor.nombre}}</div>
                  <div class="row paleta1-color2">Nombre vendedor</div>
                </div>
                <div v-if="props.row.intermediario != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.intermediario.nombre}}</div>
                  <div class="row paleta1-color2">Nombre intermediario</div>
                </div>
                <div v-if="props.row.comprador != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.comprador.nombre}}</div>
                  <div class="row paleta1-color2">Nombre comprador</div>
                </div>
                <div v-if="props.row.fecha != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.fecha.fecha}}</div>
                  <div class="row paleta1-color2">Fecha del viaje</div>
                </div>
                <div v-if="props.row.conductor != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.conductor.nombre}}</div>
                  <div class="row paleta1-color2">Nombre conductor</div>
                </div>
                <div v-if="props.row.camion != null" class="col-lg-3 col-md-4 col-sm-6 col-xs-12 item-lista">
                  <div class="row text-white">{{props.row.camion.marca}}</div>
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
import { ViajeModel } from 'src/models/viaje_model'
import { conductorService } from 'src/services/conductor_service'
import { camionService } from 'src/services/camion_service'
import { viajeService } from 'src/services/viaje_service'
import { autenticacionService } from 'src/services/autenticacion_service'
import { rolEnum } from 'src/models/enums/rol_enum'
import { ayuda } from 'app/src/helpers/ayuda'
import { useQuasar } from 'quasar'
import { clienteService } from 'src/services/cliente_service'
import { direccionService } from 'src/services/direccion_service'

const pagination = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columns = [
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
    name: 'conductor',
    label: 'Chofer',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'camion',
    label: 'Camion',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'vendedor',
    label: 'Vendedor',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'comprador',
    label: 'Comprador',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'categoria',
    label: 'Categoria',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'cantidadTransportada',
    label: 'Cantidad',
    align: 'center',
    field: 'cantidadTransportada'
  },
  {
    name: 'kmVacio',
    label: 'Km Vacio',
    align: 'center',
    field: 'kmVacio'
  },
  {
    name: 'kmCargado',
    label: 'Km Cargado',
    align: 'center',
    field: 'kmCargado'
  },
  {
    name: 'valorKm',
    label: 'Valor Km',
    align: 'center',
    field: 'valorKm'
  }
]

export default {
  setup () {
    const $q = useQuasar()

    const rangoFechas = ref({ from: null, to: null })

    const chofer = ref(null)
    const camion = ref(null)
    const vendedor = ref(null)
    const origen = ref(null)
    const destino = ref(null)
    const comprador = ref(null)

    const viajes = ref([])
    const choferes = ref([])
    const choferesList = ref([])
    const camiones = ref([])
    const camionesList = ref([])
    const vendedores = ref([])
    const compradores = ref([])
    const origenes = ref([])
    const destinos = ref([])

    const clientes = ref([])
    const direcciones = ref([])

    const viaje = reactive(new ViajeModel())

    const autoridad = ref(autenticacionService.obtenerAutoridades())

    afBuscarCamiones()
    afBuscarChoferes()
    afBuscarPaginadas()
    afBuscarClientes()
    afBuscarDirecciones()

    async function afBuscarCamiones () {
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await camionService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await camionService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          camionesList.value = resultado.data
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

    async function afBuscarChoferes () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await conductorService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await conductorService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          choferesList.value = resultado.data
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

    async function afBuscarEntreFechas () {
      $q.loading.show()
      try {
        if (vendedor.value != null || camion.value != null || chofer.value != null || comprador.value != null || origen.value != null || destino.value != null) {
          console.log('Filtrar viaje por busqueda.')
          viaje.value = viaje.value.filter((viaje) => {
            console.log(viaje.creada.slice(0, 10))
            return ayuda.fFormatearStringALocalDate(rangoFechas.value.to) <= ayuda.fFormatearStringALocalDate(viaje.creada) &&
            ayuda.fFormatearStringALocalDate(rangoFechas.value.to) >= ayuda.fFormatearStringALocalDate(viaje.creada)
          })
        } else {
          console.log(
            ayuda.fFormatearStringALocalDate(rangoFechas.value.to))
          // let resultado = null
          // if (autoridad.value.includes(rolEnum.ADMIN)) {
          //   resultado = await viajeService.spfBuscarTodasPorCreadaEntreFechasConEliminadas(rangoFechas.value.to, rangoFechas.value.from)
          // } else {
          //   resultado = await viajeService.spfBuscarTodasPorCreadaEntreFechas(rangoFechas.value.to, rangoFechas.value.from)
          // }
          // if (resultado.status === 200) {
          //   viaje.value = resultado.data
          // }
        }
        $q.loading.hide()
      } catch (err) {
        // console.clear()
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
          viajes.value = resultado.data.content
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

    async function afBuscarPorCamionId () {
      if (camion.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorCamionIdConEliminadas(camion.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCamionId(camion.value)
          }
          if (resultado.status === 200) {
            console.info(resultado.headers.mensaje)
            viajes.value = resultado.data
          }
          $q.loading.hide()
          fLimpiarInputs('camion')
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
    }

    async function afBuscarPorChoferId () {
      if (chofer.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorChoferIdConEliminadas(chofer.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorChoferId(chofer.value)
          }
          if (resultado.status === 200) {
            console.info(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
          fLimpiarInputs('chofer')
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
    }

    async function afBuscarPorCompradorId () {
      if (comprador.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorCompradorIdConEliminadas(comprador.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorCompradorId(comprador.value)
          }
          if (resultado.status === 200) {
            console.info(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
          fLimpiarInputs('comprador')
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
    }

    async function afBuscarPorDestinoId () {
      if (destino.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorDestinoIdConEliminadas(destino.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorDestinoId(destino.value)
          }
          if (resultado.status === 200) {
            console.info(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
          fLimpiarInputs('destino')
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
    }

    async function afBuscarPorOrigenId () {
      if (origen.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            resultado = await viajeService.spfBuscarTodasPorOrigenIdConEliminadas(origen.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorOrigenId(origen.value)
          }
          if (resultado.status === 200) {
            console.info(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
          fLimpiarInputs('origen')
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
    }

    async function afBuscarPorVendedorId () {
      if (vendedor.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value.includes(rolEnum.ADMIN)) {
            console.log('aca')
            resultado = await viajeService.spfBuscarTodasPorVendedorIdConEliminadas(vendedor.value)
          } else {
            resultado = await viajeService.spfBuscarTodasPorVendedorId(vendedor.value)
          }
          if (resultado.status === 200) {
            console.info(resultado.headers.mensaje)
            viajes.value = resultado.data
            $q.loading.hide()
          }
          fLimpiarInputs('vendedor')
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
    }

    function fLimpiarInputs (actual) {
      rangoFechas.value.inicio = null
      rangoFechas.value.fin = null
      switch (actual) {
        case 'camion':
          chofer.value = null
          comprador.value = null
          vendedor.value = null
          origen.value = null
          destino.value = null
          break
        case 'chofer':
          camion.value = null
          comprador.value = null
          vendedor.value = null
          origen.value = null
          destino.value = null
          break
        case 'comprador':
          camion.value = null
          chofer.value = null
          vendedor.value = null
          origen.value = null
          destino.value = null
          break
        case 'vendedor':
          camion.value = null
          chofer.value = null
          comprador.value = null
          origen.value = null
          destino.value = null
          break
        case 'origen':
          camion.value = null
          chofer.value = null
          comprador.value = null
          vendedor.value = null
          destino.value = null
          break
        case 'destino':
          camion.value = null
          chofer.value = null
          comprador.value = null
          vendedor.value = null
          origen.value = null
          break
        default:
          camion.value = null
          chofer.value = null
          comprador.value = null
          vendedor.value = null
          origen.value = null
          destino.value = null
          break
      }
    }

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
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
      camion,
      camiones,
      chofer,
      choferes,
      vendedor,
      vendedores,
      origen,
      origenes,
      destino,
      destinos,
      comprador,
      compradores,

      rangoFechas,

      fFormatoFecha,

      columns,
      pagination,
      viaje,
      viajes,

      model: ref(null),

      filtrarCamiones (val, update, abort) {
        if (val.length < 3) {
          abort()
          return
        }
        update(() => {
          const needle = val.toLowerCase()
          camiones.value = camionesList.value.filter(v => v.modelo.toLowerCase().indexOf(needle) > -1)
        })
      },
      filtrarChoferes (val, update, abort) {
        if (val.length < 3) {
          abort()
          return
        }
        update(() => {
          const needle = val.toLowerCase()
          choferes.value = choferesList.value.filter(v => v.nombre.toLowerCase().indexOf(needle) > -1)
        })
      },
      filtrarCompradores (val, update, abort) {
        if (val.length < 3) {
          abort()
          return
        }
        update(() => {
          const needle = val.toLowerCase()
          compradores.value = clientes.value.filter(v => v.nombre.toLowerCase().indexOf(needle) > -1)
        })
      },
      filtrarVendedores (val, update, abort) {
        if (val.length < 3) {
          abort()
          return
        }
        update(() => {
          const needle = val.toLowerCase()
          vendedores.value = clientes.value.filter(v => v.nombre.toLowerCase().indexOf(needle) > -1)
        })
      },
      filtrarOrigenes (val, update, abort) {
        if (val.length < 3) {
          abort()
          return
        }
        update(() => {
          const needle = val.toLowerCase()
          origenes.value = direcciones.value.filter(v => v.direccion.toLowerCase().indexOf(needle) > -1)
        })
      },
      filtrarDestinos (val, update, abort) {
        if (val.length < 3) {
          abort()
          return
        }
        update(() => {
          const needle = val.toLowerCase()
          destinos.value = direcciones.value.filter(v => v.direccion.toLowerCase().indexOf(needle) > -1)
        })
      }
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
