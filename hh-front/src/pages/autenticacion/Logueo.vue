<template>
  <div class="paleta1-fondo3 fuente1" style="min-height: 100vh" >
    <div class="row">
      <div class="col-md-4 offset-md-4 col-12">
        <q-card class="q-mt-xl">
          <q-card-section class="paleta1-color5">
            <q-icon name="input"  size="md" ></q-icon>
            <span class="q-pa-md text-h5 text-weight-regular">Ingresar</span>
          </q-card-section>
          <q-card-section>
            <q-form v-on:submit.prevent="afLoguearUsuario">
              <q-input v-model="username" outlined label="Nombre de usuario" :reglas="[reglas.requerido, reglas.min8, reglas.email]" :disable="cargando" />
              <q-input type="password" outlined label="Contraseña" v-model="password" :disable="cargando" />
              <div class="row q-mt-xl justify-around">
                <q-btn :loading="cargando" class="paleta1-fondo4 text-white" type="submit" style="width: 150px">
                  <q-icon name="input" class="q-mr-sm"/>
                  Ingresar
                  <template v-slot:loading>
                    <q-spinner-hourglass class="on-left" />
                      Ingresando
                  </template>
                </q-btn>
              </div>
            </q-form>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script>
import { autenticacionService } from 'src/services/autenticacion_service'
import { logueoService } from 'src/services/logueo_service'
import { LogueoCreation } from 'src/models/creation/logueo_creation'
import { QSpinnerCube, useQuasar } from 'quasar'
import { notificarService } from 'src/helpers/notificar_service'
import { onBeforeRouteLeave, useRouter } from 'vue-router'
import { onMounted, reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'

const USERNAME = process.env.APP_USERNAME_ADMIN
const PASSWORD = process.env.APP_PASSWORD_ADMIN

export default {
  setup () {
    const $q = useQuasar()
    const password = ref(PASSWORD)
    const username = ref(USERNAME)
    const cargando = ref(false)
    const logueoCreation = new LogueoCreation()
    const reglas = reactive(reglasValidacion.reglas)
    const router = useRouter()

    onMounted(() => {
      $q.loading.hide()
      afDatosLogueo()
    })

    onBeforeRouteLeave((to, from, next) => {
      $q.loading.show({
        spinner: QSpinnerCube,
        message: 'Cargando...',
        boxClass: 'paleta1-color4 paleta1-fondo1',
        spinnerColor: 'paleta1-color5'
      })
      next()
    })

    async function afLoguearUsuario () {
      cargando.value = true
      try {
        const user = {
          username: username.value,
          password: password.value
        }
        logueoCreation.username = username.value
        const result = await autenticacionService.spfIngresar(user)
        console.log('Mensaje: ' + result.headers.mensaje)
        if (result.status === 200) {
          console.info(result.headers.mensaje)
          logueoCreation.logueado = true
          logueoService.spfGuardar(logueoCreation)
          router.push({ name: 'Tablero' })
        }
      } catch (err) {
        console.clear()
        logueoCreation.logueado = false
        logueoService.spfGuardar(logueoCreation)
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar ingresar.')
        }
        notificarService.notificarError('Hubo un error al comprobar las credenciales.')
      }
      cargando.value = false
    }

    async function afDatosLogueo () {
      try {
        const result = await logueoService.spfObtenerDatosLogueo()
        if (result.status === 200) {
          logueoCreation.ip = result.data.ip
          logueoCreation.hostname = result.data.hostname
          logueoCreation.country_name = result.data.country_name
          logueoCreation.state_prov = result.data.state_prov
          logueoCreation.district = result.data.district
          logueoCreation.city = result.data.city
          logueoCreation.zipcode = result.data.zipcode
          logueoCreation.country_flag = result.data.country_flag
          logueoCreation.isp = result.data.isp
          logueoCreation.organization = result.data.organization
          logueoCreation.asn = result.data.asn
        }
      } catch (err) {
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener los datos de logueo.')
        }
      }
    }

    return {
      afLoguearUsuario,

      cargando,
      password,
      username,
      reglas
    }
  }
}
</script>
