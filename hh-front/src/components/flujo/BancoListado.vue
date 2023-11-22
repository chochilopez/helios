<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Bancos"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="bancos"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Bancos</p>
              <q-btn
                class="paleta2-fondo2 paleta1-color1 q-mb-lg"
                icon="add_circle"
                label="Nuevo Banco"
                @click="fMostrarNuevaBanco"
              />
            </div>
          </template>
          <template v-slot:body="props">
            <q-tr :props="props">
              <q-td auto-width class="text-center">
                <q-btn
                  v-if="props.row.eliminada === null && (autoridad === 'admin' || autoridad === 'usuario')"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEditarBanco(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="edit" />
                  <q-tooltip>
                    Modificar
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada === null && autoridad === 'admin'"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarEliminarBanco(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="delete" />
                  <q-tooltip>
                    Eliminar
                  </q-tooltip>
                </q-btn>
                <q-btn
                  v-if="props.row.eliminada !== null && autoridad === 'admin'"
                  size="sm"
                  class="text-white paleta5-fondo2 q-mr-xs"
                  round
                  dense
                  @click="fMostrarReciclarBanco(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td auto-width class="text-center">
                {{ props.row.banco }}
              </q-td>
              <q-td>
                {{ props.row.notas }}
              </q-td>
              <q-td>
                {{ props.row.creador }}
              </q-td>
              <q-td>
                {{ fFormatoFecha(props.row.creada) }}
              </q-td>
            </q-tr>
          </template>
        </q-table>
      </div>
    </div>
  </q-card>

  <q-dialog v-model="nuevoBancoDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuev Banco</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarBanco">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="bancoCreation.banco"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Nombre del banco"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="bancoCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-end q-pa-md">
            <q-btn class="paleta2-fondo2 text-white" type="submit" icon-right="save" ripple >
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
import { BancoCreation } from 'src/models/creation/banco_creation'
import { bancoService } from 'src/services/banco_service'
import { notificarService } from 'src/helpers/notificar_service'
import { reactive, ref } from 'vue'
import { reglasValidacion } from 'src/helpers/reglas_validacion'
import { useQuasar } from 'quasar'

const paginacion = {
  rowsPerPage: 50,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    label: 'Acciones',
    align: 'center'
  },
  {
    name: 'banco',
    label: 'Categoría del viaje',
    align: 'left',
    field: 'banco',
    sortable: true
  },
  {
    name: 'notas',
    label: 'Notas',
    align: 'left',
    field: 'notas',
    sortable: true
  },
  {
    name: 'creador',
    label: 'Creador',
    align: 'left',
    field: 'creador',
    sortable: true
  },
  {
    name: 'creada',
    label: 'Creada',
    align: 'left',
    field: 'creada',
    sortable: true
  }
]

export default {
  setup () {
    const $q = useQuasar()
    const autoridad = ref(ayuda.getAutoridad())

    const bancoCreation = reactive(new BancoCreation())
    const bancos = ref([])
    const notas = ref(null)
    const nuevoBancoDialog = ref(false)
    const paso1 = ref(true)
    const reglas = reactive(reglasValidacion.reglas)

    afBuscarPaginadas()

    async function afBuscarPaginadas () {
      $q.loading.show()
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '50'
        }
        let resultado = null
        if (autoridad.value === 'admin') {
          resultado = await bancoService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await bancoService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          bancos.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          bancos.value = []
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

    async function afGuardarBanco () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await bancoService.spfGuardar(bancoCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente la categoría viaje.')
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

    async function afEliminarBanco (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await bancoService.spfBorrar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se borró correctamente el acoplado.')
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

    async function afReciclarBanco (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await bancoService.spfReciclar(id)
        if (resultado.status === 200) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se recicló correctamente el acoplado.')
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

    function fFormatoFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    function fGuardarBanco () {
      afGuardarBanco().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoBancoDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      bancoCreation.banco = null
      bancoCreation.notas = null

      bancoCreation.id = null
      bancoCreation.creadorId = null
      bancoCreation.creador = null
      bancoCreation.creada = null
      bancoCreation.modificadorId = null
      bancoCreation.modificador = null
      bancoCreation.modificada = null
      bancoCreation.eliminadorId = null
      bancoCreation.eliminador = null
      bancoCreation.eliminada = null
    }

    function fMostrarNuevaBanco () {
      fLimpiarFormulario()
      fIrPaso1()
      nuevoBancoDialog.value = true
    }

    function fMostrarEditarBanco (props) {
      bancoCreation.banco = props.row.banco
      bancoCreation.notas = props.row.notas

      bancoCreation.id = props.row.id
      bancoCreation.creada = props.row.creada
      bancoCreation.creadorId = props.row.creadorId
      bancoCreation.eliminada = props.row.eliminada
      bancoCreation.eliminadorId = props.row.eliminadorId
      bancoCreation.modificada = props.row.modificada
      bancoCreation.modificadorId = props.row.modificadorId

      nuevoBancoDialog.value = true
    }

    function fMostrarEliminarBanco (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarBanco(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarBanco (props) {
      afReciclarBanco(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      columnas,
      bancoCreation,
      bancos,
      fFormatoFecha,
      fGuardarBanco,
      fMostrarNuevaBanco,
      fMostrarEditarBanco,
      fMostrarEliminarBanco,
      fMostrarReciclarBanco,
      notas,
      nuevoBancoDialog,
      paginacion,
      paso1,
      reglas
    }
  }
}

</script>
<style scoped>
.edits-fondo {
  background: #9e9e9e;
}
.edits {
  color: #9e9e9e;
}
.q-btn-dropdown {
  width: 260px;
}
.item-lista {
  border-bottom: 2px solid white;
  padding: 5px;
}
.nuevo-input {
  width: 250px;
}
</style>
