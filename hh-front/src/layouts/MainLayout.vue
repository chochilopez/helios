<template>
  <q-layout view="hHh lpR fFf">

    <q-header bordered class="paleta1-fondo5 text-white fuente1">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-toolbar-title>
            <span class="q-ml-sm text-bold">Helios</span>
        </q-toolbar-title>
        <q-space />
        <q-btn-dropdown
          :label="username"
          class="q-ml-xs paleta1-fondo4 paleta1-color1"
          no-caps
          dropdown-icon="brightness_high">
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

        <q-item v-for="(menu, index) in menues" :key="index" clickable v-ripple :active="link === menu.item" @click="link = menu.item" active-class="seleccion-activa" exact :to="{ name: menu.ruta }" >
          <q-item-section avatar>
            <q-icon :name=menu.icono />
          </q-item-section>
          <q-item-section class="white-text">{{ menu.nombre }}</q-item-section>
        </q-item>

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
import menu from 'src/json/menu.json'

export default {
  setup () {
    const layout = ref([])
    const leftDrawerOpen = ref(false)
    const link = ref('dashboard')
    const username = ref(autenticacionService.obtenerNombreUsuario())
    const router = useRouter()
    const menues = menu

    const cerrarSesion = () => {
      autenticacionService.spfSalir()
      notificarService.infoExito('Has salido correctamente del sistema.')
      router.push({ name: 'Ingreso' })
    }

    return {
      link,
      leftDrawerOpen,
      menues,
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
  color: #FEFCFD;
  background-color: #526D82;
}
a {
  color: #FEFCFD !important;
  text-decoration: none;
}
a :hover {
  color: #BFCDE0 !important;
  text-decoration: none;
}
</style>
