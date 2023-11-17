<template>
  <q-card class="font-5 no-shadow no-border">
    <q-card-section>
      <div class="row justify-center items-center">
        <div class="col-md-4 q-mb-md">
          <q-btn class="paleta2-fondo2 paleta1-color1" icon-right="add_circle" label="Facturar"></q-btn>
        </div>
        <div class="col-md-4 q-mb-md q-mx-md">
          <q-btn-dropdown class="paleta2-fondo2 paleta1-color1" label="Buscar facturas por" dropdown-icon="fa-solid fa-magnifying-glass">
            <q-list>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarComprador">
                <q-item-section avatar>
                  <q-icon name="monetization_on" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Comprador</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFechaFacturacion">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-calendar-days" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Fecha facturación</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarFechaViaje">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-truck-arrow-right" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Fecha viaje</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroComprobante">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-file-invoice" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Número comprobante</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroGuia">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-shuffle" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Numero guía</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarNumeroRemito">
                <q-item-section avatar>
                  <q-icon name="pin" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Número remito</q-item-label>
                </q-item-section>
              </q-item>
              <q-item clickable v-close-popup class="desplegable paleta2-fondo2 paleta1-color1" @click="fMostrarTipoComprobante">
                <q-item-section avatar>
                  <q-icon name="fa-solid fa-file-lines" />
                </q-item-section>
                <q-item-section>
                  <q-item-label>Tipo comprobante</q-item-label>
                </q-item-section>
              </q-item>
            </q-list>
          </q-btn-dropdown>
        </div>
        <div class="col-md-4">
          <q-select
            v-if="editComprador"
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
            @filter="fFiltrarCompradores"
            @update:model-value="afBuscarPorCompradorId()"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="monetization_on" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
          </q-select>

          <div class="column" v-if="editFechaFacturacion">
            <div class="row justify-around">
                <q-input
                  mask="##-##-####"
                  style="width: 180px"
                  v-model="fechaFacturacion.from"
                  outlined
                  dense
                  clearable
                  label="Facturacion fin"
                  hint="20-01-2020"
                >
                  <template v-slot:before>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        <q-date v-model="fechaFacturacion.from" mask="DD-MM-YYYY">
                          <div class="row items-center justify-end">
                            <q-btn v-close-popup label="OK" color="primary" flat />
                          </div>
                        </q-date>
                      </q-popup-proxy>
                    </q-icon>
                  </template>
                </q-input>
                <q-input
                  class="q-ml-md"
                  mask="##-##-####"
                  style="width: 180px"
                  v-model="fechaFacturacion.to"
                  outlined
                  dense
                  clearable
                  label="Facturacion inicio"
                  hint="30-01-2020"
                >
                  <template v-slot:before>
                    <q-icon name="event" class="cursor-pointer">
                      <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                        <q-date v-model="fechaFacturacion.to" mask="DD-MM-YYYY">
                          <div class="row items-center justify-end">
                            <q-btn v-close-popup label="OK" color="primary" flat />
                          </div>
                        </q-date>
                      </q-popup-proxy>
                    </q-icon>
                  </template>
                </q-input>
              <div class="col">
                <q-icon name="fa-solid fa-magnifying-glass" size="24px" class="cursor-pointer q-pa-sm edits" v-on:click="afBuscarPorFechaViaje()" />
              </div>
            </div>
          </div>
          <q-input
            v-if="editNumeroGuia"
            outlined
            dense
            clearable
            v-on:keyup.enter="afBuscarPorTipoComprobante()"
            v-model="numeroGuia"
            label="Buscar por número guía"
            hint="Tenés que escribir al menos 3 caracteres para buscar."
          >
            <template v-slot:before>
              <q-icon name="fa-solid fa-shuffle" class="q-mx-xs" />
            </template>
            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> Sin resultados </q-item-section>
              </q-item>
            </template>
            <template v-slot:after>
              <q-icon name="fa-solid fa-magnifying-glass" class="q-mx-xs" v-on:click="afBuscarPorTipoComprobante()" style="cursor: pointer" />
            </template>
          </q-input>
        </div>
      </div>
    </q-card-section>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        title="Facturas"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="facturas"
        row-key="id"
      >
        <template v-slot:body="props">
          <q-tr :props="props">
            <q-td auto-width class="text-center">
              <q-btn
                size="sm"
                class="text-white"
                :class="props.expand ? 'paleta5-fondo3' : 'paleta5-fondo2'"
                round
                dense
                @click="props.expand = !props.expand"
                :icon="props.expand ? 'remove' : 'add'"
              />
            </q-td>
            <q-td class="text-center">
              {{ props.row.id }}
            </q-td>
            <q-td>
              {{ props.row.fecha.fecha }}
            </q-td>
            <q-td>
              {{ props.row.tipoComprobante }}
            </q-td>
            <q-td>
              {{ props.row.numeroComprobante }}
            </q-td>
            <q-td>
              {{ props.row.viaje.origen }}
            </q-td>
            <q-td>
              {{ props.row.viaje.destino }}
            </q-td>
            <q-td>
              {{ props.row.viaje.conductor }}
            </q-td>
            <q-td>
              {{ props.row.viaje.camion }}
            </q-td>
            <q-td>
              {{ props.row.viaje.categoriaViaje }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.viaje.cantidadTransportada }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.viaje.valorKm + props.row.recarga - props.row.descuento + props.row.iva }}
            </q-td>
            <q-td class="text-center">
              {{ props.row.pagada }}
            </q-td>
          </q-tr>
          <q-tr v-show="props.expand" :props="props" class="paleta5-fondo2">
            <q-td colspan="100%">
              <div class="row">
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
import { rolEnum } from 'src/models/enums/rol_enum'
import { useQuasar } from 'quasar'


import { ayuda } from 'app/src/helpers/ayuda'
import { llaveroService } from 'src/helpers/llavero_service'

import { autenticacionService } from 'src/services/autenticacion_service'
import { clienteService } from 'src/services/cliente_service'
import { notificarService } from 'src/helpers/notificar_service'
import { facturaService } from 'src/services/factura_service'
import { FacturaModel } from 'src/models/factura_model'

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
    name: 'id',
    label: 'Id',
    align: 'center',
    field: 'id',
    sortOrder: 'da',
    sortable: true
  },
  {
    name: 'fechaFacturacion',
    label: 'Fecha facturación',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'tipoComprobante',
    label: 'Tipo comprobante',
    align: 'left',
    field: '',
    sortable: true
  },
  {
    name: 'numeroComprobante',
    label: 'Número comprobante',
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
    name: 'conductor',
    label: 'Conductor',
    align: 'center',
    field: ''
  },
  {
    name: 'camion',
    label: 'Camión',
    align: 'center',
    field: ''
  },
  {
    name: 'categoriaViaje',
    label: 'Categoría factura',
    align: 'center',
    field: ''
  },
  {
    name: 'cantidadTransportada',
    label: 'Cantidad',
    align: 'center',
    field: ''
  },
  {
    name: 'total',
    label: 'Total',
    align: 'center',
    field: ''
  },
  {
    name: 'pagada',
    label: 'Pagada',
    align: 'center',
    field: ''
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const sesion = ref(uuidv4())

    const editComprador = ref(true)
    const editFechaFacturacion = ref(false)
    const editFechaViaje = ref(false)
    const editNumeroComprobante = ref(false)
    const editNumeroGuia = ref(false)
    const editNumeroRemito = ref(false)
    const editTipoComprobante = ref(false)

    const esAdmin = ref(autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN))
    const comprador = ref(null)
    const compradores = ref([])
    const fechaFacturacion = ref({ from: null, to: null })
    const fechasFacturacion = ref([])
    const fechaViaje = ref({ from: null, to: null })
    const fechasViaje = ref([])
    const numeroComprobante = ref(null)
    const numerosComprobante = ref([])
    const numeroGuia = ref(null)
    const numerosGuia = ref([])
    const numeroRemito = ref(null)
    const numerosRemito = ref([])
    const tipoComprobante = ref(null)
    const tiposComprobante = ref(['SC', 'A', 'B', 'C'])

    const clientesList = ref([])
    const nuevaBusqueda = ref(false)
    const factura = reactive(new FacturaModel())
    const facturas = ref([])

    afBuscarPaginadas()

    async function afBuscarCompradores () {
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
          resultado = await facturaService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await facturaService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          facturas.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          facturas.value = []
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

    async function afBuscarPorCompradorId () {
      if (comprador.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorCompradorIdConEliminadas(comprador.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorCompradorId(comprador.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    async function afBuscarPorFechaFacturacion () {
      if (fechaFacturacion.value.from == null) {
        notificarService.notificarAlerta('El campo fecha facturacion desde no puede estar vacio.')
      } else if (fechaFacturacion.value.to == null) {
        notificarService.notificarAlerta('El campo fecha facturacion hasta no puede estar vacio.')
      } else if (fechaFacturacion.value.from > fechaFacturacion.value.to) {
        notificarService.notificarAlerta('El campo fecha facturacion desde no puede ser mayor que fecha facturacion hasta.')
      } else {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorFechaFacturacionEntreFechasConEliminadas(
              ayuda.fFormatearDeDatePicker(fechaFacturacion.value.from),
              ayuda.fFormatearDeDatePicker(fechaFacturacion.value.to)
            )
          } else {
            resultado = await facturaService.spfBuscarTodasPorFechaFacturacionEntreFechas(
              ayuda.fFormatearDeDatePicker(fechaFacturacion.value.from),
              ayuda.fFormatearDeDatePicker(fechaFacturacion.value.to)
            )
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    async function afBuscarPorFechaViaje () {
      if (fechaViaje.value.from == null) {
        notificarService.notificarAlerta('El campo fecha viaje desde no puede estar vacio.')
      } else if (fechaViaje.value.to == null) {
        notificarService.notificarAlerta('El campo fecha viaje hasta no puede estar vacio.')
      } else if (fechaViaje.value.from > fechaViaje.value.to) {
        notificarService.notificarAlerta('El campo fecha viaje desde no puede ser mayor que fecha viaje hasta.')
      } else {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorFechaViajeEntreFechasConEliminadas(
              ayuda.fFormatearDeDatePicker(fechaViaje.value.from),
              ayuda.fFormatearDeDatePicker(fechaViaje.value.to)
            )
          } else {
            resultado = await facturaService.spfBuscarTodasPorFechaViajeEntreFechas(
              ayuda.fFormatearDeDatePicker(fechaViaje.value.from),
              ayuda.fFormatearDeDatePicker(fechaViaje.value.to)
            )
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
          }
          $q.loading.hide()
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    async function afBuscarPorNumeroComprobante () {
      if (numeroComprobante.value !== null && numeroComprobante.value.length > 3) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorNumeroComprobanteConEliminadas(numeroComprobante.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorNumeroComprobante(numeroComprobante.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    async function afBuscarPorNumeroGuia () {
      if (numeroGuia.value !== null && numeroGuia.value.length > 3) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorNumeroGuiaConEliminadas(numeroGuia.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorNumeroGuia(numeroGuia.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    async function afBuscarPorNumeroRemito () {
      if (numeroRemito.value !== null && numeroRemito.value.length > 3) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorNumeroRemitoConEliminadas(numeroRemito.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorNumeroRemito(numeroRemito.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    async function afBuscarPorTipoComprobante () {
      if (numeroGuia.value !== null && numeroGuia.value.length > 2) {
        $q.loading.show()
        try {
          let resultado = null
          if (esAdmin.value) {
            resultado = await facturaService.spfBuscarTodasPorTipoComprobanteConEliminadas(tipoComprobante.value)
          } else {
            resultado = await facturaService.spfBuscarTodasPorTipoComprobante(tipoComprobante.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturas.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturas.value = []
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

    function fFiltrarCompradores (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        compradores.value = clientesList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fFormatoFecha (fechaFacturacion) {
      return ayuda.getDateWithFormat(fechaFacturacion)
    }

    function fLimpiarInputs (actual) {
      comprador.value = null
      editComprador.value = false
      fechaFacturacion.value.from = null
      fechaFacturacion.value.to = null
      editFechaFacturacion.value = false
      fechaViaje.value.from = null
      fechaViaje.value.to = null
      editFechaViaje.value = false
      numeroComprobante.value = false
      editNumeroComprobante.value = false
      numeroGuia.value = false
      editNumeroGuia.value = false
      numeroRemito.value = false
      editNumeroRemito.value = false
      tipoComprobante.value = false
      editTipoComprobante.value = false
    }

    function fMostrarComprador () {
      afBuscarCompradores().then(() => {
        fLimpiarInputs()
        editComprador.value = true
      })
    }

    function fMostrarFechaFacturacion () {
      fLimpiarInputs()
      editFechaFacturacion.value = true
    }

    function fMostrarFechaViaje () {
      fLimpiarInputs()
      editFechaViaje.value = true
    }

    function fMostrarNumeroComprobante () {
      fLimpiarInputs()
      editNumeroComprobante.value = true
    }

    function fMostrarNumeroGuia () {
      fLimpiarInputs()
      editNumeroGuia.value = true
    }

    function fMostrarNumeroRemito () {
      fLimpiarInputs()
      editNumeroRemito.value = true
    }

    function fMostrarTipoComprobante () {
      fLimpiarInputs()
      editTipoComprobante.value = true
    }

    return {
      afBuscarPorCompradorId,
      afBuscarPorFechaFacturacion,
      afBuscarPorFechaViaje,
      afBuscarPorNumeroComprobante,
      afBuscarPorNumeroGuia,
      afBuscarPorNumeroRemito,
      afBuscarPorTipoComprobante,

      editComprador,
      comprador,
      compradores,
      fechaFacturacion,
      fechasFacturacion,
      editFechaFacturacion,
      editFechaViaje,
      fechaViaje,
      fechasViaje,
      editNumeroComprobante,
      numeroComprobante,
      numerosComprobante,
      editNumeroGuia,
      numeroGuia,
      numerosGuia,
      editNumeroRemito,
      numeroRemito,
      numerosRemito,
      editTipoComprobante,
      tipoComprobante,
      tiposComprobante,

      columnas,
      esAdmin,
      nuevaBusqueda,
      paginacion,
      factura,
      facturas,

      fMostrarComprador,
      fMostrarFechaFacturacion,
      fMostrarFechaViaje,
      fMostrarNumeroComprobante,
      fMostrarNumeroGuia,
      fMostrarNumeroRemito,
      fMostrarTipoComprobante,

      fFiltrarCompradores,
      fFormatoFecha
    }
  }
}
</script>
<style scoped>
.edits-fondo {
  background: #9E9E9E;
}
.edits {
  color: #9E9E9E;
}
.q-btn-dropdown {
  width: 250px;
}
.q-select, .q-range {
  width: 300px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
</style>
