<template>
  <q-layout view="hHh lpR fFf">

    <q-header bordered class="cp12-bc2 text-white fuente1">
      <q-toolbar>
        <q-btn dense flat round icon="menu" @click="toggleLeftDrawer" />
        <q-toolbar-title>
            <span class="q-ml-sm text-bold">Lebe</span>
        </q-toolbar-title>
        <q-space />
        <q-btn-dropdown
          :label="username"
          class="q-ml-xs cp12-bc3"
          no-caps
          dropdown-icon="change_history">
          <q-item clickable v-close-popup @click="cerrarSesion">
          <q-item-section>
            <q-item-label>Cerrar sesión</q-item-label>
          </q-item-section>
          </q-item>
        </q-btn-dropdown>
      </q-toolbar>
    </q-header>

    <q-drawer show-if-above v-model="leftDrawerOpen" side="left" bordered class="cp12-bc3 text-white font-5 text-bold">
      <q-list bordered padding class="rounded-borders cp12-c5">

        <q-item clickable v-ripple :active="link === 'dashboard'" @click="link = 'dashboard'" active-class="my-menu-link" exact :to="{ name: 'Dashboard' }" >
          <q-item-section avatar>
            <q-icon name="dashboard" />
          </q-item-section>
          <q-item-section class="white-text">Panel Principal</q-item-section>
        </q-item>

        <q-bar style="height: 1px"/>

        <q-item clickable v-ripple :active="link === 'carousel'" @click="link = 'carousel'" active-class="my-menu-link" exact :to="{ name: 'Carousel' }" >
          <q-item-section avatar>
            <q-icon name="mdi-image-move" />
          </q-item-section>
          <q-item-section>Carousel</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'consultas'" @click="link = 'consultas'" active-class="my-menu-link" exact :to="{ name: 'Queries' }" >
          <q-item-section avatar>
            <q-icon name="mdi-database-search" />
          </q-item-section>
          <q-item-section>Consultas</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'audio'" @click="link = 'audio'" active-class="my-menu-link" exact :to="{ name: 'GaleryAudios' }" >
          <q-item-section avatar>
            <q-icon name="music_note" />
          </q-item-section>
          <q-item-section>Galería Audios</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'imagen'" @click="link = 'imagen'" active-class="my-menu-link" exact :to="{ name: 'GaleryImages' }" >
          <q-item-section avatar>
            <q-icon name="image" />
          </q-item-section>
          <q-item-section>Galería Imagenes</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'video'" @click="link = 'video'" active-class="my-menu-link" exact :to="{ name: 'GaleryVideos' }" >
          <q-item-section avatar>
            <q-icon name="videocam" />
          </q-item-section>
          <q-item-section>Galería Videos</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'nueva'" @click="link = 'nueva'" active-class="my-menu-link" exact :to="{ name: 'NewPost' }" >
          <q-item-section avatar>
            <q-icon name="fiber_new" />
          </q-item-section>
          <q-item-section>Nueva Publicacion</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'pacientes'" @click="link = 'pacientes'" active-class="my-menu-link" exact :to="{ name: 'Patients' }" >
          <q-item-section avatar>
            <q-icon name="mdi-account-group" />
          </q-item-section>
          <q-item-section>Pacientes</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'recepcion'" @click="link = 'recepcion'" active-class="my-menu-link" exact :to="{ name: 'Receptions' }" >
          <q-item-section avatar>
            <q-icon name="mdi-test-tube" />
          </q-item-section>
          <q-item-section>Recepciones</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'users'" @click="link = 'users'" active-class="my-menu-link" exact :to="{ name: 'Users' }" >
          <q-item-section avatar>
            <q-icon name="mdi-account-key" />
          </q-item-section>
          <q-item-section>Usuarios</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'visita'" @click="link = 'visita'" active-class="my-menu-link" exact :to="{ name: 'Visits' }" >
          <q-item-section avatar>
            <q-icon name="mdi-account-search" />
          </q-item-section>
          <q-item-section>Visitas</q-item-section>
        </q-item>

        <q-item clickable v-ripple :active="link === 'whatsapp'" @click="link = 'whatsapp'" active-class="my-menu-link" exact :to="{ name: 'Whatsapps' }" >
          <q-item-section avatar>
            <q-icon name="mdi-whatsapp" />
          </q-item-section>
          <q-item-section>Whatsapps</q-item-section>
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
.my-menu-link {
  color: #FEFCFD !important;
  background-color: #3B3355;
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
