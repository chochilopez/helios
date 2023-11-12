<template>
  <q-layout view="hHh lpR fFf">
    <q-header bordered class="paleta1-fondo5 text-white fuente1">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-toolbar-title>
          <span class="q-ml-sm text-bold">Helios</span>
        </q-toolbar-title>
        <q-space />
        <q-btn-dropdown :label="username" class="q-ml-xs paleta1-fondo4 paleta1-color1" no-caps dropdown-icon="brightness_high">
          <q-item clickable v-close-popup @click="cerrarSesion">
            <q-item-section>
              <q-item-label>Cerrar sesión</q-item-label>
            </q-item-section>
          </q-item>
        </q-btn-dropdown>
      </q-toolbar>
    </q-header>
    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered class="paleta1-fondo5 text-white font-5 text-bold">
      <q-list bordered padding class="rounded-borders paleta1-color1">
        <q-item
          clickable
          v-ripple
          :active="link === 'tablero'"
          @click="link = 'tablero'"
          active-class="seleccion-activa"
          exact
          :to="{ name: 'Tablero' }"
        >
          <q-item-section avatar>
            <q-icon name="dashboard" />
          </q-item-section>
          <q-item-section class="white-text">Panel Principal</q-item-section>
        </q-item>
        <q-expansion-item icon="account_balance" label="Banco">
          <q-item
            clickable
            v-ripple
            :active="link === 'banco'"
            @click="link = 'banco'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Banco' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-building-columns" />
            </q-item-section>
            <q-item-section class="white-text">Banco</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'conciliacion'"
            @click="link = 'conciliacion'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Conciliacion' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="price_change" />
            </q-item-section>
            <q-item-section class="white-text">Movimientos conciliaciones</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="paid" label="Caja">
          <q-item
            clickable
            v-ripple
            :active="link === 'adelantosCaja'"
            @click="link = 'adelantosCaja'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Adelanto' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-comments-dollar" />
            </q-item-section>
            <q-item-section class="white-text">Adelantos</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'cajas'"
            @click="link = 'cajas'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Caja' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="paid" />
            </q-item-section>
            <q-item-section class="white-text">Cajas</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'conceptoAdelantos'"
            @click="link = 'conceptoAdelantos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'ConceptoAdelanto' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="request_quote" />
            </q-item-section>
            <q-item-section class="white-text">Conceptos adelantos</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'movimientoCaja'"
            @click="link = 'movimientoCaja'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'MovimientoCaja' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="currency_exchange" />
            </q-item-section>
            <q-item-section class="white-text">Movimientos cajas</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'planesCuenta'"
            @click="link = 'planesCuenta'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'PlanCuenta' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="local_atm" />
            </q-item-section>
            <q-item-section class="white-text">Planes de cuenta</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="calendar_month" label="Calendario">
          <q-item
            clickable
            v-ripple
            :active="link === 'calendario'"
            @click="link = 'calendario'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Calendario' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-calendar-days" />
            </q-item-section>
            <q-item-section class="white-text">Calendario</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'tareas'"
            @click="link = 'tareas'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Tarea' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="event_available" />
            </q-item-section>
            <q-item-section class="white-text">Tareas</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'vencimientos'"
            @click="link = 'vencimientos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Vencimiento' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="free_cancellation" />
            </q-item-section>
            <q-item-section class="white-text">Vencimientos</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="airline_seat_recline_extra" label="Choferes">
          <q-item
            clickable
            v-ripple
            :active="link === 'adelantos'"
            @click="link = 'adelantos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Adelanto' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-money-check-dollar" />
            </q-item-section>
            <q-item-section class="white-text">Adelantos</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'combustible'"
            @click="link = 'combustible'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Combustible' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="local_gas_station" />
            </q-item-section>
            <q-item-section class="white-text">Combustible</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'choferes'"
            @click="link = 'choferes'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Chofer' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="airline_seat_recline_extra" />
            </q-item-section>
            <q-item-section class="white-text">Choferes</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'resumenChofer'"
            @click="link = 'resumenChofer'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'ResumenChofer' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="follow_the_signs" />
            </q-item-section>
            <q-item-section class="white-text">Resumen por chofer</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="groups" label="Clientes">
          <q-item
            clickable
            v-ripple
            :active="link === 'clientes'"
            @click="link = 'clientes'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Cliente' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="groups" />
            </q-item-section>
            <q-item-section class="white-text">Clientes</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'detallesCtaCte'"
            @click="link = 'detallesCtaCte'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'DetalleCtaCte' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="folder_shared" />
            </q-item-section>
            <q-item-section class="white-text"
              >Detalles cuentas corrientes</q-item-section
            >
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'direccion'"
            @click="link = 'direccion'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Direccion' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-earth-americas" />
            </q-item-section>
            <q-item-section class="white-text"
              >Direcciones</q-item-section
            >
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'moviemientoCtaCte'"
            @click="link = 'moviemientoCtaCte'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'MovimientoCtaCte' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="social_distance" />
            </q-item-section>
            <q-item-section class="white-text"
              >Movimientos cuentas corrientes</q-item-section
            >
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'saldos'"
            @click="link = 'saldos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'SaldoCtaCte' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-money-bill-trend-up" />
            </q-item-section>
            <q-item-section class="white-text">Saldos cuentas corrientes</q-item-section>
          </q-item>
        </q-expansion-item>
        <!-- <q-expansion-item icon="shopping_cart" label="Compras">
          <q-item
            clickable
            v-ripple
            :active="link === 'compras'"
            @click="link = 'compras'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Compra' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="shopping_cart" />
            </q-item-section>
            <q-item-section class="white-text">Compras</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'conceptosCompras'"
            @click="link = 'conceptosCompras'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'ConceptoCompra' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="local_mall" />
            </q-item-section>
            <q-item-section class="white-text">Concepto compras</q-item-section>
          </q-item>
        </q-expansion-item> -->
        <q-expansion-item icon="receipt_long" label="Facturacion">
          <!-- <q-item
            clickable
            v-ripple
            :active="link === 'facturar'"
            @click="link = 'facturar'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Facturar' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-file-invoice-dollar" />
            </q-item-section>
            <q-item-section class="white-text">Facturar</q-item-section>
          </q-item> -->
          <q-item
            clickable
            v-ripple
            :active="link === 'facturacion'"
            @click="link = 'facturacion'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Facturacion' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="receipt_long" />
            </q-item-section>
            <q-item-section class="white-text">Facturacion</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'recibos'"
            @click="link = 'recibos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Recibo' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="receipt" />
            </q-item-section>
            <q-item-section class="white-text">Recibos</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="fa-solid fa-ring" label="Neumaticos">
          <q-item
            clickable
            v-ripple
            :active="link === 'marcas'"
            @click="link = 'marcas'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Marca' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-copyright" />
            </q-item-section>
            <q-item-section class="white-text">Marcas</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'neumaticos'"
            @click="link = 'neumaticos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Neumatico' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-ring" />
            </q-item-section>
            <q-item-section class="white-text">Neumaticos</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'colocados'"
            @click="link = 'colocados'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Colocado' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="tire_repair" />
            </q-item-section>
            <q-item-section class="white-text">Neumaticos colocados</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="storefront" label="Proveedores">
          <q-item
            clickable
            v-ripple
            :active="link === 'movimientosCtaCtePro'"
            @click="link = 'movimientosCtaCtePro'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'MovCtaCteProveedor' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-money-bill-transfer" />
            </q-item-section>
            <q-item-section class="white-text"
              >Movimientos cuentas corrientes proveedores</q-item-section
            >
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'proveedores'"
            @click="link = 'proveedores'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Proveedor' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="storefront" />
            </q-item-section>
            <q-item-section class="white-text">Proveedores</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="local_shipping" label="Vehiculos">
          <q-item
            clickable
            v-ripple
            :active="link === 'acoplados'"
            @click="link = 'acoplados'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Acoplado' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-trailer" />
            </q-item-section>
            <q-item-section class="white-text">Acoplados</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'camiones'"
            @click="link = 'camiones'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Camion' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-truck-fast" />
            </q-item-section>
            <q-item-section class="white-text">Camiones</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'seguros'"
            @click="link = 'seguros'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Seguro' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-car-burst" />
            </q-item-section>
            <q-item-section class="white-text">Seguros</q-item-section>
          </q-item>
        </q-expansion-item>
        <q-expansion-item icon="fa-solid fa-route" label="Viajes">
          <q-item
            clickable
            v-ripple
            :active="link === 'agendaViaje'"
            @click="link = 'agendaViaje'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'AgendaViaje' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="departure_board" />
            </q-item-section>
            <q-item-section class="white-text">Agenda viaje</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'presupuestos'"
            @click="link = 'presupuestos'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Presupuesto' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-road-circle-check" />
            </q-item-section>
            <q-item-section class="white-text">Presupuestos</q-item-section>
          </q-item>
          <q-item
            clickable
            v-ripple
            :active="link === 'viajes'"
            @click="link = 'viajes'"
            active-class="seleccion-activa"
            exact
            :to="{ name: 'Viaje' }"
            class="q-ml-md paleta1-fondo5"
          >
            <q-item-section avatar>
              <q-icon name="fa-solid fa-route" />
            </q-item-section>
            <q-item-section class="white-text">Viajes</q-item-section>
          </q-item>
        </q-expansion-item>
      </q-list>
    </q-drawer>
    <q-page-container>
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>
import { ref } from 'vue'
import { autenticacionService } from 'src/services/autenticacion_service'
import { useRouter } from 'vue-router'
import { notificarService } from 'src/helpers/notificar_service'

export default {
  setup () {
    const layout = ref([])
    const leftDrawerOpen = ref(false)
    const link = ref('dashboard')
    const username = ref(autenticacionService.obtenerNombreUsuario())
    const router = useRouter()

    const cerrarSesion = () => {
      autenticacionService.spfSalir()
      notificarService.infoExito('Has salido correctamente del sistema.')
      router.push({ name: 'Ingreso' })
    }

    return {
      link,
      leftDrawerOpen,
      username,
      cerrarSesion,
      layout,
      toggleLeftDrawer () {
        leftDrawerOpen.value = !leftDrawerOpen.value
      }
    }
  }
}
</script>

<style scoped>
.seleccion-activa {
  color: #fefcfd;
  background-color: #526d82;
}
a {
  color: #fefcfd !important;
  text-decoration: none;
}
a :hover {
  color: #bfcde0 !important;
  text-decoration: none;
}
</style>
