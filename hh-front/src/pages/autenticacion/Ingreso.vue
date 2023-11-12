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
import { ingresoService } from 'src/services/ingreso_service'
import { IngresoCreation } from 'src/models/creation/ingreso_creation'
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
    const ingresoCreation = new IngresoCreation()
    const reglas = reactive(reglasValidacion.reglas)
    const router = useRouter()

    onMounted(() => {
      $q.loading.hide()
      afDatosIngreso()
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
        ingresoCreation.username = username.value
        const result = await autenticacionService.spfIngresar(user)
        console.log('Mensaje: ' + result.headers.mensaje)
        if (result.status === 200) {
          console.info(result.headers.mensaje)
          ingresoCreation.logueado = true
          ingresoService.spfGuardar(ingresoCreation)
          router.push({ name: 'Tablero' })
        }
      } catch (err) {
        console.clear()
        ingresoCreation.logueado = false
        ingresoService.spfGuardar(ingresoCreation)
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar ingresar.')
        }
        notificarService.notificarError('Hubo un error al comprobar las credenciales.')
      }
      cargando.value = false
    }

    async function afDatosIngreso () {
      try {
        const result = await ingresoService.spfObtenerDatosIngreso()
        if (result.status === 200) {
          ingresoCreation.ip = result.data.ip
          ingresoCreation.hostname = result.data.hostname
          ingresoCreation.country_name = result.data.country_name
          ingresoCreation.state_prov = result.data.state_prov
          ingresoCreation.district = result.data.district
          ingresoCreation.city = result.data.city
          ingresoCreation.zipcode = result.data.zipcode
          ingresoCreation.country_flag = result.data.country_flag
          ingresoCreation.isp = result.data.isp
          ingresoCreation.organization = result.data.organization
          ingresoCreation.asn = result.data.asn
        }
      } catch (err) {
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar obtener los datos de ingreso.')
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
