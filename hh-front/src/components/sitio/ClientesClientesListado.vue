<template>
  <q-card class="q-ma-md font-5 no-shadow no-border">
    <div class="row justify-around q-pb-md">
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <q-select
          outlined
          v-model="camion"
          :options="camiones"
          option-label="modelo"
          option-value="id"
          label="Buscar por camion"
          dense
          emit-value
          map-options
          class="q-pa-md"
          @update:model-value="afBuscarPorCamion()"
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-truck-moving" class="q-mx-xs" />
          </template>
        </q-select>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <q-select
          outlined
          v-model="chofer"
          :options="choferes"
          option-label="nombre"
          option-value="id"
          label="Buscar por chofer"
          dense
          emit-value
          map-options
          class="q-pa-md"
          @update:model-value="afBuscarPorChofer()"
        >
          <template v-slot:before>
            <q-icon name="airline_seat_recline_extra" class="q-mx-xs" />
          </template>
        </q-select>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorVendedor" v-model="vendedor" label="Buscar por vendedor" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorVendedor" />
          </template>
        </q-input>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorOrigen" v-model="origen" label="Buscar por origen" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location-dot" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorOrigen" />
          </template>
        </q-input>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorDestino" v-model="destino" label="Buscar por destino" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorDestino" />
          </template>
        </q-input>
      </div>
      <div class="col-lg-4 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorComprador" v-model="comprador" label="Buscar por comprador" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="monetization_on" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorComprador" />
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
import { helper } from 'app/src/helpers/ayuda'
import { useQuasar } from 'quasar'

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

    const camion = ref(null)
    const chofer = ref(null)
    const vendedor = ref(null)
    const origen = ref(null)
    const destino = ref(null)
    const comprador = ref(null)

    const seeDialog = ref(false)
    const viaje = reactive(new ViajeModel())
    const viajes = ref([])
    const choferes = ref([])
    const camiones = ref([])

    const autoridad = ref([autenticacionService.obtenerAutoridades()])

    afBuscarCamiones()
    afBuscarChoferes()
    afBuscarPaginadas()

    async function afBuscarCamiones () {
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await camionService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await camionService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          camiones.value = resultado.data
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
          choferes.value = resultado.data
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

    async function afBuscarPorCamion () {
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
          $q.loading.hide()
        }
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

    async function afBuscarPorChofer () {
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

    async function afBuscarPorComprador () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorCompradorNombreConEliminadas(comprador.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorCompradorNombre(comprador.value)
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

    async function afBuscarPorDestino () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorDestinoDireccionConEliminadas(destino.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorDestinoDireccion(destino.value)
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

    async function afBuscarPorOrigen () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccionConEliminadas(origen.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccion(origen.value)
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

    async function afBuscarPorVendedor () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value.includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorVendedorNombreConEliminadas(vendedor.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorVendedorNombre(vendedor.value)
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

    function fLimpiarInputs (actual) {
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
      return helper.getDateWithFormat(fecha)
    }

    function fMostrarDialogo (props) {
      Object.assign(viaje, props.row)
      seeDialog.value = true
    }

    return {
      afBuscarPorCamion,
      afBuscarPorChofer,
      afBuscarPorComprador,
      afBuscarPorDestino,
      afBuscarPorOrigen,
      afBuscarPorVendedor,

      autoridad,
      camion,
      camiones,
      chofer,
      choferes,
      vendedor,
      origen,
      destino,
      comprador,

      fMostrarDialogo,
      fFormatoFecha,

      columns,
      seeDialog,
      pagination,
      viaje,
      viajes

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
