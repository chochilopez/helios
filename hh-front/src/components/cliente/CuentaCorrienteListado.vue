<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md" >
      <div class="row justify-around items-baseline q-py-md" style="width: 100%">
        <p class="text-h5" v-if="cliente.id === ''">Cuentas corrientes</p>
        <p class="text-h5" v-if="cliente.id !== ''">Estado de cuenta corriente de: <b>{{ cliente.nombre }}</b></p>
        <div class="row">
          <q-btn v-if="cliente.id !== ''" class="paleta2-fondo2 paleta1-color1 q-mr-lg" icon="add_circle" label="Nuevo pago" @click="fMostrarNuevoPago" />
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
      <p class="text-center text-h6 q-pa-md" v-if="cliente.id !== '' && movimientos.length === 0">{{ cliente.nombre }} no posee movimientos.</p>
    </div>
  </q-card>
</template>

<script>
import { ayuda } from 'app/src/helpers/ayuda'
import { ClienteModel } from 'src/models/cliente_model'
import { clienteService } from 'src/services/cliente_service'
import { cuentaCorrienteService } from 'src/services/cuenta_corriente_service'
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
    const movimientos = ref([])

    onMounted(() => {
      if (llaveroService.obtenerDeLocal('hhVerEstadoCuentaCorriente') !== null) {
        const resultado = llaveroService.obtenerDeLocal('hhVerEstadoCuentaCorriente')
      }
    })

    afBuscarClientes()

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

    async function afBuscarMovimientosCuentaCorriente (id) {
      $q.loading.show()
      movimientos.value = []
      try {
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await cuentaCorrienteService.spfBuscarTodasPorClienteIdConEliminadas(clienteSelect.value)
        } else {
          resultado = await cuentaCorrienteService.spfBuscarTodasPorClienteId(clienteSelect.value)
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

    function fMostrarFecha (datos) {
      return ayuda.getDateWithFormat(datos)
    }

    function fMostrarNuevoPago () {}

    return {
      afBuscarEstados,
      fFiltrarClientes,
      fMostrarFecha,
      fMostrarNuevoPago,

      cliente,
      clienteSelect,
      clientesSelect,
      movimientos
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
