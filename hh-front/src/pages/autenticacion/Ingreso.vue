<template>
  <div class="bg-c-10 fuente1" style="min-height: 100vh" >
    <div class="row">
      <div class="col-md-4 offset-md-4 col-12">
        <q-card class="q-mt-xl">
          <q-card-section>
            <q-icon name="input" class="text-c-1" size="md"></q-icon>
            <span class="q-ml-xs text-h5 text-c-1 text-weight-regular">Ingresar</span>
          </q-card-section>
          <q-card-section>
            <q-form v-on:submit.prevent="afLoguearUsuario">
              <q-input v-model="username" outlined label="Nombre de usuario" :reglas="[reglas.requerido, reglas.min8, reglas.email]" :disable="cargando" />
              <q-input type="password" outlined label="Contraseña" v-model="password" :disable="cargando" />
              <div class="row q-mt-xl justify-around">
                <q-btn :to="{ name: 'Home' }" class="bg-c-1 text-white" style="width: 150px">
                  <q-icon name="mdi-arrow-left-circle" class="q-mr-sm"/>
                  Volver
                </q-btn>
                <q-btn :loading="cargando" class="bg-c-8 text-white" type="submit" style="width: 150px">
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
        <q-card class="q-mt-xl">
          <q-card-section class="text-center">
            <!-- <q-img src="~/assets/images/lebe1.png" fit="contain" class="rounded-borders" /> -->
          </q-card-section>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script>
import { notificarService } from 'src/helpers/notificar_service'
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { autenticacionService } from 'src/services/autenticacion_service'
import { reglasValidacion } from 'src/helpers/reglas_validacion'

const USERNAME = process.env.APP_USERNAME_ADMIN
const PASSWORD = process.env.APP_PASSWORD_ADMIN

export default {
  setup () {
    const router = useRouter()
    const cargando = ref(false)
    const password = ref(PASSWORD)
    const username = ref(USERNAME)
    const reglas = reactive(reglasValidacion.reglas)

    async function afLoguearUsuario () {
      cargando.value = true
      try {
        const user = {
          username: username.value,
          password: password.value
        }
        const result = await autenticacionService.spfIngresar(user)
        console.log('Mensaje: ' + result.headers.mensaje)
        if (result.status === 200) {
          console.info(result.headers.mensaje)
          notificarService.notifySuccess(result.headers.mensaje)
          router.push('/tablero')
        } else if (result.status === 202) {
          console.warn(result.headers.mensaje)
          notificarService.notifyWarning(result.headers.mensaje)
        } else if (result.status === 204) {
          notificarService.notifyError(result.headers.mensaje)
          console.error(result.headers.mensaje)
        }
        console.error(result.headers.mensaje)
      } catch (err) {
        notificarService.notifyError('Ha ocurrido un error al intentar ingresar. ' + err)
        console.error('Error: ' + err)
      }
      cargando.value = false
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
