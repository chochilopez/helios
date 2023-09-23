<template>
  <q-card class="q-ma-md font-5 no-shadow no-border">
    <div class="row justify-around q-pb-md">
      <div class="col-md-4 col-sm-12">
        <q-select
          outlined
          v-model="camion"
          :options="camiones"
          option-label="modelo"
          option-value="id"
          label="Buscar por camion"
          dense
          emit-value
          class="q-pa-md"
          @update:model-value="afBuscarPorCamion()"
        >
          <template v-slot:before>
            <q-icon name="fa-solid fa-truck-moving" class="q-mx-xs" />
          </template>
        </q-select>
      </div>
      <div class="col-md-4 col-sm-12">
        <q-select
          outlined
          v-model="chofer"
          :options="choferes"
          option-label="nombre"
          option-value="id"
          label="Buscar por chofer"
          dense
          emit-value
          class="q-pa-md"
          @update:model-value="afBuscarPorChofer()"
        >
          <template v-slot:before>
            <q-icon name="airline_seat_recline_extra" class="q-mx-xs" />
          </template>
        </q-select>
      </div>
      <div class="col-md-4 col-sm-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorVendedor" v-model="vendedor" label="Buscar por vendedor" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorVendedor" />
          </template>
        </q-input>
      </div>
    </div>
    <div class="row justify-around q-pb-md">
      <div class="col-md-4 col-sm-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorOrigen" v-model="origen" label="Buscar por origen" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location-dot" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorOrigen" />
          </template>
        </q-input>
      </div>
      <div class="col-md-4 col-sm-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorDestino" v-model="destino" label="Buscar por destino" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorDestino" />
          </template>
        </q-input>
      </div>
      <div class="col-md-4 col-sm-12">
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
        :showing="!cargando"
        title="Viajes"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        hide-no-data
        :rows="viajes"
        row-key="id"
      >
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn @click="fShowError(props)" dense round flat color="red" icon="sms_failed" v-if="props.row.errorCode">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Ver Error
              </q-tooltip>
            </q-btn>
            <q-btn @click="fShowSeeDialog(props)" dense round flat class="cp1-c4" icon="visibility">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Información
              </q-tooltip>
            </q-btn>
          </q-td>
        </template>
        <template v-slot:body-cell-conductor="props">
          <q-td :props="props">
            {{ props.row.conductor.nombre }}
          </q-td>
        </template>
        <template v-slot:body-cell-camion="props">
          <q-td :props="props">
            {{ props.row.camion.modelo }}
          </q-td>
        </template>
        <template v-slot:body-cell-vendedor="props">
          <q-td :props="props">
            {{ props.row.vendedor.nombre }}
          </q-td>
        </template>
        <template v-slot:body-cell-comprador="props">
          <q-td :props="props">
            {{ props.row.comprador.nombre }}
          </q-td>
        </template>
        <template v-slot:body-cell-categoria="props">
          <q-td :props="props">
            {{ props.row.categoriaViaje.categoria }}
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="cargando">
        <q-spinner-puff class="cp12-c3 text-center" size="12em" />
      </q-inner-loading>
    </div>
  </div>

  <q-dialog v-model="seeDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Ver viaje</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>

      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, reactive } from 'vue'
import { notificarService } from 'src/helpers/notificar_service'
import { ViajeModel } from 'src/models/viaje_model'
import { viajeService } from 'src/services/viaje_service'
import { conductorService } from 'src/services/conductor_service'
import { camionService } from 'src/services/camion_service'
import { autenticacionService } from 'src/services/autenticacion_service'
import { rolEnum } from 'src/models/enums/rol_enum'

const pagination = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columns = [
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
  },
  {
    name: 'actions',
    label: 'Acciones',
    field: '',
    align: 'center'
  }
]

export default {
  setup () {
    const camion = ref()
    const chofer = ref()
    const vendedor = ref()
    const origen = ref()
    const destino = ref()
    const comprador = ref()

    const cargando = ref(false)
    const seeDialog = ref(false)
    const viaje = reactive(new ViajeModel())
    const viajes = ref([])
    const choferes = ref([])
    const camiones = ref([])

    afBuscarPaginadas()
    afBuscarCamiones()
    afBuscarChoferes()

    function fShowSeeDialog (props) {
      Object.assign(viaje, props.row)
      seeDialog.value = true
    }

    async function afBuscarPorCamion () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorCamionIdConEliminadas(camion.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorCamionId(camion.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarPorChofer () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorChoferIdConEliminadas()
        } else {
          resultado = await viajeService.spfBuscarTodasPorChoferId()
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarPorComprador (comprador) {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorCompradorNombreConEliminadas(comprador)
        } else {
          resultado = await viajeService.spfBuscarTodasPorCompradorNombre(comprador)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarPorVendedor (vendedor) {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorVendedorNombreConEliminadas(vendedor)
        } else {
          resultado = await viajeService.spfBuscarTodasPorVendedorNombre(vendedor)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarPorOrigen (origen) {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccionConEliminadas(origen)
        } else {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccion(origen)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarPorDestino (destino) {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccionConEliminadas(destino)
        } else {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccion(destino)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarPaginadas () {
      cargando.value = true
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '50'
        }
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await viajeService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          viajes.value = resultado.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener el listado.')
        }
        notificarService.notificarError('Hubo un error al realizar la busqueda.')
      }
      cargando.value = false
    }

    async function afBuscarChoferes () {
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await conductorService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await conductorService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          choferes.value = resultado.data
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar ingresar.')
        }
        notificarService.notificarError('Hubo un error al comprobar las credenciales.')
      }
      cargando.value = false
    }

    async function afBuscarCamiones () {
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await camionService.spfBuscarTodasConEliminadas()
        } else {
          resultado = await camionService.spfBuscarTodas()
        }
        if (resultado.status === 200) {
          camiones.value = resultado.data
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar ingresar.')
        }
        notificarService.notificarError('Hubo un error al comprobar las credenciales.')
      }
    }

    return {
      afBuscarPorCamion,
      afBuscarPorChofer,
      afBuscarPorVendedor,
      afBuscarPorOrigen,
      afBuscarPorDestino,
      afBuscarPorComprador,

      camion,
      camiones,
      chofer,
      choferes,
      vendedor,
      origen,
      destino,
      comprador,

      fShowSeeDialog,

      cargando,
      columns,
      seeDialog,
      pagination,
      viaje,
      viajes

    }
  }
}
</script>
