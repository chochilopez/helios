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
            <q-btn @click="fMostrarDialogo(props)" dense round flat class="paleta19-color4" icon="visibility">
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
        <q-spinner-puff class="paleta19-color4 text-center" size="12em" />
      </q-inner-loading>
    </div>
  </div>

  <q-dialog v-model="seeDialog" transition-show="scale" transition-hide="scale">
    <q-card class="bg-white">
      <q-bar class="paleta19-fondo4 text-white">
        <div>Ver viaje {{ viaje.id }}</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="paleta19-fondo4 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>
        <div class="q-pa-md" style="max-width: 350px">
          <q-list bordered separator>

            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.id }}</q-item-label>
                <q-item-label caption>Id</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.cantidadTransportada }}</q-item-label>
                <q-item-label caption>Cantidad</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.categoriaViaje.categoria }}</q-item-label>
                <q-item-label caption>Transportado</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.valorKm}}</q-item-label>
                <q-item-label caption>Valor km</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.neto}}</q-item-label>
                <q-item-label caption>Peso neto</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.tara}}</q-item-label>
                <q-item-label caption>Peso tara</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.kmCargado}}</q-item-label>
                <q-item-label caption>Km cargado</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.kmVacio}}</q-item-label>
                <q-item-label caption>Km vacio</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.notas}}</q-item-label>
                <q-item-label caption>Notas</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.guia}}</q-item-label>
                <q-item-label caption>Guia</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.origen.direccion + ", " + viaje.origen.ubicacion.ciudad + ", " + viaje.origen.ubicacion.pais}}</q-item-label>
                <q-item-label caption>Direccion origen</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.carga.direccion + ", " + viaje.carga.ubicacion.ciudad + ", " + viaje.carga.ubicacion.pais}}</q-item-label>
                <q-item-label caption>Direccion carga</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.destino.direccion + ", " + viaje.destino.ubicacion.ciudad + ", " + viaje.destino.ubicacion.pais}}</q-item-label>
                <q-item-label caption>Direccion destino</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.vendedor.nombre}}</q-item-label>
                <q-item-label caption>Vendedor</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.intermediario.nombre}}</q-item-label>
                <q-item-label caption>Intermediario</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.comprador.nombre}}</q-item-label>
                <q-item-label caption>Comprador</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{fFormatoFecha(viaje.fecha.fecha)}}</q-item-label>
                <q-item-label caption>Fecha</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.conductor.nombre}}</q-item-label>
                <q-item-label caption>Conductor</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.camion.marca + " - " + viaje.camion.modelo }}</q-item-label>
                <q-item-label caption>Camion</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ viaje.acoplado.marca + " - " + viaje.acoplado.modelo }}</q-item-label>
                <q-item-label caption>Acoplado</q-item-label>
              </q-item-section>
            </q-item>

          </q-list>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
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
    const camion = ref(null)
    const chofer = ref(null)
    const vendedor = ref(null)
    const origen = ref(null)
    const destino = ref(null)
    const comprador = ref(null)

    const cargando = ref(false)
    const seeDialog = ref(false)
    const viaje = reactive(new ViajeModel())
    const viajes = ref([])
    const choferes = ref([])
    const camiones = ref([])

    afBuscarPaginadas()
    afBuscarCamiones()
    afBuscarChoferes()

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
          viajes.value = resultado.data
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
        cargando.value = false
      }
      cargando.value = false
    }

    async function afBuscarPorChofer () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorChoferIdConEliminadas(chofer.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorChoferId(chofer.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data
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
        cargando.value = false
      }
      cargando.value = false
    }

    async function afBuscarPorComprador () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorCompradorNombreConEliminadas(comprador.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorCompradorNombre(comprador.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data
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
        cargando.value = false
      }
      cargando.value = false
    }

    async function afBuscarPorVendedor () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorVendedorNombreConEliminadas(vendedor.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorVendedorNombre(vendedor.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data
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
        cargando.value = false
      }
      cargando.value = false
    }

    async function afBuscarPorOrigen () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccionConEliminadas(origen.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorOrigenDireccion(origen.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data
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
        cargando.value = false
      }
      cargando.value = false
    }

    async function afBuscarPorDestino () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await viajeService.spfBuscarTodasPorDestinoDireccionConEliminadas(destino.value)
        } else {
          resultado = await viajeService.spfBuscarTodasPorDestinoDireccion(destino.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          viajes.value = resultado.data
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
        cargando.value = false
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
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        cargando.value = false
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
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        cargando.value = false
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
          console.warn('Advertencia: ' + err.response.headers.mensaje)
          notificarService.notificarAlerta('Advertencia: ' + err.response.headers.mensaje)
        } else {
          const mensaje = 'Hubo un error al intentar obtener el listado.'
          notificarService.notificarError(mensaje)
          console.error(mensaje)
        }
        cargando.value = false
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

      fMostrarDialogo,
      fFormatoFecha,

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
