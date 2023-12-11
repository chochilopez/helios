<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <div class="relieve shadow-2 q-pa-md">
          <div class="row justify-start text-h5">
            {{ tituloTabla }}
          </div>
          <div class="row justify-between q-py-md" style="width: 100%">
            <q-btn :disable="cliente.id === null" class="paleta2-fondo2 paleta1-color1 q-mr-lg" icon="add_circle" label="Nuevo pago" @click="fMostrarNuevoPago" />
            <q-select
              outlined
              dense
              emit-value
              map-options
              clearable
              v-model="clienteSelect"
              :options="clientesSelect"
              option-value="id"
              option-label="nombre"
              label="Buscar por cliente"
              use-input
              input-debounce="0"
              @filter="fFiltrarClientes"
              @update:model-value="afBuscarEstados()"
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
          </div>
          <q-markup-table >
            <thead>
              <tr>
                <th class="text-center">Número de movimiento</th>
                <th class="text-center">Fecha</th>
                <th class="text-center">Comprobante</th>
                <th class="text-left">Debe</th>
                <th class="text-left">Haber</th>
                <th class="text-left">Saldo</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(movimiento, index) in movimientos" :key="index">
                <td class="text-center">{{ index + 1 }}</td>
                <td class="text-center">{{ fMostrarFecha(movimiento.fecha) }}</td>
                <td class="text-center">{{ movimiento.factura }}</td>
                <td class="text-left" v-if="movimiento.tipoMovimiento === 'DEBITO'">{{ movimiento.monto }}</td>
                <td class="text-left" v-if="movimiento.tipoMovimiento === 'CREDITO'"></td>
                <td class="text-left" v-if="movimiento.tipoMovimiento === 'CREDITO'">{{ movimiento.monto }}</td>
                <td class="text-left" v-if="movimiento.tipoMovimiento === 'DEBITO'"></td>
                <td class="text-left">{{ movimiento.saldo }}</td>
              </tr>
            </tbody>
          </q-markup-table>
          <p class="text-center text-h6 q-pa-md" v-if="cliente.id !== null && movimientos.length === 0">{{ cliente.nombre }} no posee movimientos.</p>
        </div>
        </div>
    </div>
  </q-card>

  <q-dialog v-model="nuevoPagoDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">{{ tituloFormulario }}</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-accent"/>
      </div>
      <q-card-section>
        <q-form v-on:submit.prevent="fGuardarPago()">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="cliente.nombre"
                disable
                outlined
                dense
                clearable
                label="Cliente"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                emit-value
                map-options
                clearable
                v-model="cuentaCorrienteCreation.facturaId"
                :rules="[reglas.requerido]"
                :options="facturasList"
                option-value="id"
                option-label="comprobante"
                label="Facturas no pagadas"
                @update:model-value="fObtenerDeuda()"
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                disable
                v-model="montoAdeudado"
                outlined
                dense
                clearable
                label="Monto adeudado"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                clearable
                v-model="cuentaCorrienteCreation.tipoPago"
                :rules="[reglas.requerido]"
                :options="tiposPagos"
                label="Tipo de pago"
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="cuentaCorrienteCreation.monto"
                :rules="[reglas.requerido]"
                mask="#.##"
                fill-mask="0"
                reverse-fill-mask
                outlined
                dense
                clearable
                label="Monto"
                hint="Ingresá un número."
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="cuentaCorrienteCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" v-close-popup icon="close" ripple>
              Cerrar
            </q-btn>
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple>
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
import { ClienteModel } from 'src/models/cliente_model'
import { clienteService } from 'src/services/cliente_service'
import { cuentaCorrienteService } from 'src/services/cuenta_corriente_service'
import { CuentaCorrienteCreation } from 'src/models/creation/cuenta_corriente_creation'
import { facturaService } from 'src/services/factura_service'
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { onMounted, reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { useQuasar } from 'quasar'

export default {
  setup () {
    const $q = useQuasar()
    const reglas = reactive(reglasValidacion.reglas)
    const sesion = ref(ayuda.getUid)
    const autoridad = ref(ayuda.getAutoridad())

    const cliente = reactive(new ClienteModel())
    const clienteSelect = ref(null)
    const clientesSelect = ref([])
    const clientesList = ref([])
    const cuentaCorrienteCreation = reactive(new CuentaCorrienteCreation())
    const facturasList = ref([])
    const montoAdeudado = ref(null)
    const montoAdeudadoList = ref([])
    const movimientos = ref([])
    const nuevoPagoDialog = ref(false)
    const tituloFormulario = ref('Nuevo pago')
    const tituloTabla = ref('Cuentas corrientes')

    afBuscarClientes()

    onMounted(() => {
      if (llaveroService.obtenerDeLocal('hhIngresarPagoCtaCte') !== null) {
        const resultado = llaveroService.obtenerDeLocal('hhIngresarPagoCtaCte')
        afBuscarEstados().then(() => {
          clienteSelect.value = resultado.value.clienteId
          cuentaCorrienteCreation.cliente = null
          cuentaCorrienteCreation.clienteId = null
          cuentaCorrienteCreation.comprobante = null
          cuentaCorrienteCreation.factura = null
          cuentaCorrienteCreation.facturaId = null
          cuentaCorrienteCreation.fecha = null
          cuentaCorrienteCreation.monto = null
          cuentaCorrienteCreation.notas = null
          cuentaCorrienteCreation.recibo = null
          cuentaCorrienteCreation.reciboId = null
          cuentaCorrienteCreation.saldo = null
          cuentaCorrienteCreation.tipoMovimiento = null
          cuentaCorrienteCreation.tipoPago = null

          cuentaCorrienteCreation.id = null
          cuentaCorrienteCreation.creada = null
          cuentaCorrienteCreation.creador = null
          cuentaCorrienteCreation.modificada = null
          cuentaCorrienteCreation.modificador = null
          cuentaCorrienteCreation.eliminada = null
          cuentaCorrienteCreation.eliminador = null

          llaveroService.borrarDeLocal('hhIngresarPagoCtaCte')
          nuevoPagoDialog.value = true
        })
      }

      if (llaveroService.obtenerDeLocal('hhMostrarPagoCtaCte') !== null) {
        const resultado = llaveroService.obtenerDeLocal('hhMostrarPagoCtaCte')
        afBuscarEstados(resultado.value.clienteId).then(() => {
          cuentaCorrienteCreation.cliente = null
          cuentaCorrienteCreation.clienteId = null
          cuentaCorrienteCreation.comprobante = null
          cuentaCorrienteCreation.factura = null
          cuentaCorrienteCreation.facturaId = null
          cuentaCorrienteCreation.fecha = null
          cuentaCorrienteCreation.monto = null
          cuentaCorrienteCreation.notas = null
          cuentaCorrienteCreation.recibo = null
          cuentaCorrienteCreation.reciboId = null
          cuentaCorrienteCreation.saldo = null
          cuentaCorrienteCreation.tipoMovimiento = null
          cuentaCorrienteCreation.tipoPago = null

          cuentaCorrienteCreation.id = null
          cuentaCorrienteCreation.creada = null
          cuentaCorrienteCreation.creador = null
          cuentaCorrienteCreation.modificada = null
          cuentaCorrienteCreation.modificador = null
          cuentaCorrienteCreation.eliminada = null
          cuentaCorrienteCreation.eliminador = null

          llaveroService.borrarDeLocal('hhMostrarPagoCtaCte')
        })
      }
    })

    async function afBuscarClientes () {
      $q.loading.show()
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
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

    async function afBuscarPagosPorFactura () {
      if (cuentaCorrienteCreation.facturaId != null) {
        $q.loading.show()
        try {
          let resultado = null
          resultado = await cuentaCorrienteService.spfBuscarTodasPorFacturaId(cuentaCorrienteCreation.facturaId)
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            montoAdeudadoList.value = resultado.data
            $q.loading.hide()
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
    }

    async function afBuscarEstados () {
      if (clienteSelect.value != null) {
        $q.loading.show()
        cliente.id = clienteSelect.value
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await clienteService.spfBuscarPorIdConEliminadas(cliente.id)
          } else {
            resultado = await clienteService.spfBuscarPorId(cliente.id)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            Object.assign(cliente, resultado.data)
            $q.loading.hide()
            tituloTabla.value = 'Estado de cuenta corriente de: ' + resultado.data.nombre
            afBuscarMovimientosCuentaCorriente(cliente.id)
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
    }

    async function afBuscarFacturasNoPagdasPorClienteId () {
      if (cliente.id !== null) {
        $q.loading.show()
        try {
          let resultado = null
          resultado = await facturaService.spfBuscarTodasPorNoPagadasClienteId(cliente.id)
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            facturasList.value = resultado.data
            $q.loading.hide()
          }
        } catch (err) {
          console.clear()
          if (err.response.status === 404) {
            facturasList.value = []
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

    async function afBuscarMovimientosCuentaCorriente (id) {
      $q.loading.show()
      movimientos.value = []
      try {
        let resultado = null
        resultado = await cuentaCorrienteService.spfBuscarTodasPorClienteId(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          movimientos.value = resultado.data
          $q.loading.hide()
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

    async function afMarcarFacturaComoPagada (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await facturaService.spfMarcarComoPagada(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
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

    async function afGuardarPago () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await cuentaCorrienteService.spfGuardar(cuentaCorrienteCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente el pago.')
          if (cuentaCorrienteCreation.monto >= montoAdeudado.value) {
            afMarcarFacturaComoPagada(cuentaCorrienteCreation.facturaId).then(() => {
              notificarService.notificarExito('La factura fue saldada.')
            })
          }
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

    function fFiltrarClientes (val, update, abort) {
      if (val.length < 3) {
        abort()
        return
      }
      update(() => {
        clientesSelect.value = clientesList.value.filter(
          (v) => v.nombre.toLowerCase().indexOf(val.toLowerCase()) > -1
        )
      })
    }

    function fLimpiarFormulario () {
      cuentaCorrienteCreation.id = null
      cuentaCorrienteCreation.cliente = null
      cuentaCorrienteCreation.clienteId = null
      cuentaCorrienteCreation.comprobante = null
      cuentaCorrienteCreation.factura = null
      cuentaCorrienteCreation.facturaId = null
      cuentaCorrienteCreation.fecha = null
      cuentaCorrienteCreation.monto = null
      cuentaCorrienteCreation.notas = null
      cuentaCorrienteCreation.recibo = null
      cuentaCorrienteCreation.reciboId = null
      cuentaCorrienteCreation.saldo = null
      cuentaCorrienteCreation.tipoMovimiento = null
      cuentaCorrienteCreation.tipoPago = null
      cuentaCorrienteCreation.creada = null
      cuentaCorrienteCreation.creador = null
      cuentaCorrienteCreation.modificada = null
      cuentaCorrienteCreation.modificador = null
      cuentaCorrienteCreation.eliminada = null
      cuentaCorrienteCreation.eliminador = null
      montoAdeudado.value = null
    }

    function fMostrarFecha (datos) {
      return ayuda.getDateWithFormat(datos)
    }

    function fMostrarNuevoPago () {
      afBuscarFacturasNoPagdasPorClienteId().then(() => {
        tituloFormulario.value = 'Nuevo pago'
        fLimpiarFormulario()
        nuevoPagoDialog.value = true
      })
    }

    function fGuardarPago () {
      cuentaCorrienteCreation.clienteId = cliente.id
      cuentaCorrienteCreation.saldo = montoAdeudado.value - cuentaCorrienteCreation.monto
      cuentaCorrienteCreation.tipoMovimiento = 'CREDITO'
      afGuardarPago().then(() => {
        afBuscarEstados().then(() => {
          nuevoPagoDialog.value = false
        })
      })
    }

    function fObtenerDeuda () {
      const resultado = facturasList.value.find((factura) => factura.id === cuentaCorrienteCreation.facturaId)
      montoAdeudado.value = resultado.total
      afBuscarPagosPorFactura().then(() => {
        for (let a = 0; a < montoAdeudadoList.value.length; a++) {
          if (montoAdeudadoList.value[a].tipoMovimiento === 'CREDITO') {
            montoAdeudado.value = (Number(montoAdeudado.value) - (montoAdeudadoList.value[a].monto)).toFixed(2)
            cuentaCorrienteCreation.monto = Number(montoAdeudado.value).toFixed(2)
          }
        }
      })
    }

    return {
      afBuscarEstados,
      fFiltrarClientes,
      fGuardarPago,
      fMostrarFecha,
      fMostrarNuevoPago,
      fObtenerDeuda,

      cliente,
      clienteSelect,
      clientesSelect,
      cuentaCorrienteCreation,
      facturasList,
      montoAdeudado,
      movimientos,
      nuevoPagoDialog,
      reglas,
      tiposPagos: ['EFECTIVO', 'TRANSFERENCIA', 'CHEQUE'],
      tituloFormulario,
      tituloTabla,

      myLocale: {
        /* starting with Sunday */
        days: 'Domingo_Lunes_Martes_Miércoles_Jueves_Viernes_Sábado'.split('_'),
        daysShort: 'Dom_Lun_Mar_Mié_Jue_Vie_Sáb'.split('_'),
        months: 'Enero_Febrero_Marzo_Abril_Mayo_Junio_Julio_Agosto_Septiembre_Octubre_Noviembre_Diciembre'.split(
          '_'
        ),
        monthsShort: 'Ene_Feb_Mar_Abr_May_Jun_Jul_Ago_Sep_Oct_Nov_Dic'.split('_'),
        firstDayOfWeek: 1, // 0-6, 0 - Sunday, 1 Monday, ...
        format24h: true,
        pluralDay: 'dias'
      }
    }
  }
}
</script>
<style scoped>
.q-markup-table {
  width: 100%;
}

</style>
