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
          router.push('/tablero')
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar ingresar.')
        }
        notificarService.notificarError('Hubo un error al comprobar las credenciales.')
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
