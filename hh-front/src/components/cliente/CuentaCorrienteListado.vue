<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <div class="relieve shadow-2 q-pa-md">
          <div class="row justify-start text-h5">
            {{ tituloTabla }}
          </div>
          <div class="row justify-between q-py-md" style="width: 100%">
            <q-btn :disable="cliente.id !== ''" class="paleta2-fondo2 paleta1-color1 q-mr-lg" icon="add_circle" label="Nuevo pago" @click="fMostrarNuevoPago" />
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
                <td class="text-center">{{ movimiento.comprobante }}</td>
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
        <q-form v-on:submit.prevent="fIrPaso2">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="facturaCreation.razonSocial"
                :rules="[reglas.requerido, reglas.min3]"
                outlined
                dense
                clearable
                label="Nombre/Razón social"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="facturaCreation.domicilioComercial"
                :rules="[reglas.requerido, reglas.min3]"
                outlined
                dense
                clearable
                label="Domicilio/Dirección"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-select
                class="nuevo-input"
                outlined
                dense
                clearable
                v-model="facturaCreation.tipoComprobante"
                :rules="[reglas.requerido]"
                :options="comprobantes"
                label="Tipo comprobante"
                hint="Seleccioná el tipo de comprobante."
                @update:model-value="fObtenerNumeroComprobante()"
              >
                <template v-slot:no-option>
                  <q-item>
                    <q-item-section class="text-grey"> Sin resultados </q-item-section>
                  </q-item>
                </template>
              </q-select>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="facturaCreation.numeroComprobante"
                :rules="[reglas.requerido]"
                mask="###################"
                outlined
                dense
                clearable
                label="Número comprobante"
                hint="Si SC, usar autogenerado."
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="fechaEmision"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Fecha de emisión"
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="fechaEmision" mask="DD-MM-YYYY" :locale="myLocale">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="OK" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                mask="##-##-####"
                v-model="fechaVencimiento"
                :rules="[reglas.requerido]"
                outlined
                dense
                readonly
                clearable
                label="Fecha de vencimiento"
              >
                <template v-slot:prepend>
                  <q-icon name="event" class="cursor-pointer">
                    <q-popup-proxy cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="fechaVencimiento" mask="DD-MM-YYYY" :locale="myLocale">
                        <div class="row items-center justify-end">
                          <q-btn v-close-popup label="OK" color="primary" flat />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-icon>
                </template>
              </q-input>
            </div>
          </div>
          <div class="row justify-end q-pa-md">
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
import { llaveroService } from 'src/helpers/llavero_service'
import { notificarService } from 'src/helpers/notificar_service'
import { onMounted, reactive, ref } from 'vue'
import { useQuasar } from 'quasar'

export default {
  setup () {
    const $q = useQuasar()
    const sesion = ref(ayuda.getUid)
    const autoridad = ref(ayuda.getAutoridad())

    const cliente = reactive(new ClienteModel())
    const clienteSelect = ref(null)
    const clientesSelect = ref([])
    const clientesList = ref([])
    const cuentaCorrienteCreation = reactive(new CuentaCorrienteCreation())
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

    async function afBuscarEstados () {
      if (clienteSelect.value != null) {
        $q.loading.show()
        try {
          let resultado = null
          if (autoridad.value === 'admin') {
            resultado = await clienteService.spfBuscarPorIdConEliminadas(clienteSelect.value)
          } else {
            resultado = await clienteService.spfBuscarPorId(clienteSelect.value)
          }
          if (resultado.status === 200) {
            console.log(resultado.headers.mensaje)
            Object.assign(cliente, resultado.data)
            $q.loading.hide()
            tituloTabla.value = 'Estado de cuenta corriente de: ' + resultado.data.nombre
            afBuscarMovimientosCuentaCorriente(clienteSelect.value)
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

    async function afBuscarMovimientosCuentaCorriente (id) {
      $q.loading.show()
      movimientos.value = []
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await cuentaCorrienteService.spfBuscarTodasPorClienteIdConEliminadas(id)
        } else {
          resultado = await cuentaCorrienteService.spfBuscarTodasPorClienteId(id)
        }
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
    }

    function fMostrarFecha (datos) {
      return ayuda.getDateWithFormat(datos)
    }

    function fMostrarNuevoPago () {
      tituloFormulario.value = 'Nuevo pago'
      fLimpiarFormulario()
      nuevoPagoDialog.value = true
    }

    return {
      afBuscarEstados,
      fFiltrarClientes,
      fMostrarFecha,
      fMostrarNuevoPago,

      cliente,
      clienteSelect,
      clientesSelect,
      movimientos,
      nuevoPagoDialog,
      tituloFormulario,
      tituloTabla
    }
  }
}
</script>
<style scoped>
.q-markup-table {
  width: 100%;
}

.q-select,
.q-range {
  width: 300px;
}
</style>
