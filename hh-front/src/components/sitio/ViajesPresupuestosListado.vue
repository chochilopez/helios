<template>
  <q-card class="q-ma-md font-5 no-shadow no-border">
    <div class="row justify-around q-pb-md">
      <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorFecha" v-model="fecha" label="Buscar por fecha" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-cash-register" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorFecha" />
          </template>
        </q-input>
      </div>
      <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorOrigen" v-model="origen" label="Buscar por origen" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location-dot" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorOrigen" />
          </template>
        </q-input>
      </div>
      <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorDestino" v-model="destino" label="Buscar por destino" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="fa-solid fa-map-location" class="q-mx-xs" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="cursor-mano" @click="afBuscarPorDestino" />
          </template>
        </q-input>
      </div>
      <div class="col-lg-3 col-md-6 col-sm-12 col-xs-12">
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
        title="Presupuestos"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        hide-no-data
        :rows="presupuestos"
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
        <template v-slot:body-cell-fecha="props">
          <q-td :props="props">
            {{ fFormatoFecha(props.row.fecha.fecha) }}
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
        <template v-slot:body-cell-origen="props">
          <q-td :props="props">
            {{ props.row.origen.direccion + ", " + props.row.origen.ubicacion.ciudad }}
          </q-td>
        </template>
        <template v-slot:body-cell-destino="props">
          <q-td :props="props">
            {{ props.row.destino.direccion + ", " + props.row.destino.ubicacion.ciudad }}
          </q-td>
        </template>
        <template v-slot:body-cell-total="props">
          <q-td :props="props">
            {{ props.row.valorKm * props.row.kmCargado }}
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
        <div>Ver presupuesto {{ presupuesto.id }}</div>
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
                <q-item-label>{{ presupuesto.id }}</q-item-label>
                <q-item-label caption>Id</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.cantidadTransportada }}</q-item-label>
                <q-item-label caption>Cantidad</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.categoriaViaje.categoria }}</q-item-label>
                <q-item-label caption>Transportado</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.valorKm}}</q-item-label>
                <q-item-label caption>Valor km</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.neto}}</q-item-label>
                <q-item-label caption>Peso neto</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.tara}}</q-item-label>
                <q-item-label caption>Peso tara</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.kmCargado}}</q-item-label>
                <q-item-label caption>Km cargado</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.kmVacio}}</q-item-label>
                <q-item-label caption>Km vacio</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.notas}}</q-item-label>
                <q-item-label caption>Notas</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.guia}}</q-item-label>
                <q-item-label caption>Guia</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.origen.direccion + ", " + presupuesto.origen.ubicacion.ciudad + ", " + presupuesto.origen.ubicacion.pais}}</q-item-label>
                <q-item-label caption>Direccion origen</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.carga.direccion + ", " + presupuesto.carga.ubicacion.ciudad + ", " + presupuesto.carga.ubicacion.pais}}</q-item-label>
                <q-item-label caption>Direccion carga</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.destino.direccion + ", " + presupuesto.destino.ubicacion.ciudad + ", " + presupuesto.destino.ubicacion.pais}}</q-item-label>
                <q-item-label caption>Direccion destino</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.vendedor.nombre}}</q-item-label>
                <q-item-label caption>Vendedor</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.intermediario.nombre}}</q-item-label>
                <q-item-label caption>Intermediario</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.comprador.nombre}}</q-item-label>
                <q-item-label caption>Comprador</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{fFormatoFecha(presupuesto.fecha.fecha)}}</q-item-label>
                <q-item-label caption>Fecha</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.conductor.nombre}}</q-item-label>
                <q-item-label caption>Conductor</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.camion.marca + " - " + presupuesto.camion.modelo }}</q-item-label>
                <q-item-label caption>Camion</q-item-label>
              </q-item-section>
            </q-item>
            <q-item clickable v-ripple>
              <q-item-section>
                <q-item-label>{{ presupuesto.acoplado.marca + " - " + presupuesto.acoplado.modelo }}</q-item-label>
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
import { PresupuestoModel } from 'src/models/presupuesto_model'
import { autenticacionService } from 'src/services/autenticacion_service'
import { presupuestoService } from 'src/services/presupuesto_service'
import { rolEnum } from 'src/models/enums/rol_enum'
import { helper } from 'app/src/helpers/ayuda'

const pagination = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columns = [
  {
    name: 'fecha',
    label: 'Fecha',
    align: 'center',
    field: '',
    sortOrder: 'da',
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
    name: 'origen',
    label: 'Origen',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'destino',
    label: 'Destino',
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
    name: 'total',
    label: 'Total presupuestado',
    align: 'center',
    field: ''
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
    const comprador = ref(null)
    const destino = ref(null)
    const fecha = ref(null)
    const origen = ref(null)

    const cargando = ref(false)
    const seeDialog = ref(false)
    const presupuesto = reactive(new PresupuestoModel())
    const presupuestos = ref([])

    afBuscarPaginadas()

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
          resultado = await presupuestoService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await presupuestoService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          presupuestos.value = resultado.data.content
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

    async function afBuscarPorComprador () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await presupuestoService.spfBuscarTodasPorCompradorNombreConEliminadas(comprador.value)
        } else {
          resultado = await presupuestoService.spfBuscarTodasPorCompradorNombre(comprador.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          presupuestos.value = resultado.data
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

    async function afBuscarPorDestino () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await presupuestoService.spfBuscarTodasPorDestinoDireccionConEliminadas(destino.value)
        } else {
          resultado = await presupuestoService.spfBuscarTodasPorDestinoDireccion(destino.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          presupuestos.value = resultado.data
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

    async function afBuscarPorFecha () {
      cargando.value = true
      try {
        let resultado = null
        if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
          resultado = await presupuestoService.spfBuscarTodasPorFechaConEliminadas(fecha.value)
        } else {
          resultado = await presupuestoService.spfBuscarTodasPorFecha(fecha.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          presupuestos.value = resultado.data
        }
        fLimpiarInputs('fecha')
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
          resultado = await presupuestoService.spfBuscarTodasPorOrigenDireccionConEliminadas(origen.value)
        } else {
          resultado = await presupuestoService.spfBuscarTodasPorOrigenDireccion(origen.value)
        }
        if (resultado.status === 200) {
          console.info(resultado.headers.mensaje)
          presupuestos.value = resultado.data
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

    function fLimpiarInputs (actual) {
      switch (actual) {
        case 'fecha':
          comprador.value = null
          origen.value = null
          destino.value = null
          break
        case 'comprador':
          fecha.value = null
          origen.value = null
          destino.value = null
          break
        case 'origen':
          comprador.value = null
          fecha.value = null
          destino.value = null
          break
        case 'destino':
          comprador.value = null
          fecha.value = null
          origen.value = null
          break
        default:
          comprador.value = null
          fecha.value = null
          origen.value = null
          destino.value = null
          break
      }
    }

    function fFormatoFecha (fecha) {
      return helper.getDateWithFormat(fecha)
    }

    function fMostrarDialogo (props) {
      Object.assign(presupuesto, props.row)
      seeDialog.value = true
    }

    return {
      afBuscarPorFecha,
      afBuscarPorOrigen,
      afBuscarPorDestino,
      afBuscarPorComprador,

      comprador,
      destino,
      fecha,
      origen,

      fFormatoFecha,
      fMostrarDialogo,

      cargando,
      columns,
      seeDialog,
      pagination,
      presupuesto,
      presupuestos

    }
  }
}
</script>
