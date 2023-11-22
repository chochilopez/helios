<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Cajas"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="cajas"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Cajas</p>
              <q-btn
                class="paleta2-fondo2 paleta1-color1 q-mb-lg"
                icon="add_circle"
                label="Nueva Caja"
                @click="fMostrarNuevaCaja"
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
                  @click="fMostrarEditarCaja(props)"
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
                  @click="fMostrarEliminarCaja(props)"
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
                  @click="fMostrarReciclarCaja(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td auto-width class="text-center">
                {{ props.row.caja }}
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

  <q-dialog v-model="nuevoCajaDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nueva Caja</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarCaja">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="cajaCreation.caja"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Nombre de la caja"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="cajaCreation.notas"
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
import { CajaCreation } from 'src/models/creation/caja_creation'
import { cajaService } from 'src/services/caja_service'
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
    name: 'caja',
    label: 'Categoría del viaje',
    align: 'left',
    field: 'caja',
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

    const cajaCreation = reactive(new CajaCreation())
    const cajas = ref([])
    const notas = ref(null)
    const nuevoCajaDialog = ref(false)
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
          resultado = await cajaService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await cajaService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          cajas.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          cajas.value = []
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

    async function afGuardarCaja () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await cajaService.spfGuardar(cajaCreation)
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

    async function afEliminarCaja (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await cajaService.spfBorrar(id)
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

    async function afReciclarCaja (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await cajaService.spfReciclar(id)
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

    function fGuardarCaja () {
      afGuardarCaja().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoCajaDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      cajaCreation.caja = null
      cajaCreation.notas = null

      cajaCreation.id = null
      cajaCreation.creadorId = null
      cajaCreation.creador = null
      cajaCreation.creada = null
      cajaCreation.modificadorId = null
      cajaCreation.modificador = null
      cajaCreation.modificada = null
      cajaCreation.eliminadorId = null
      cajaCreation.eliminador = null
      cajaCreation.eliminada = null
    }

    function fMostrarNuevaCaja () {
      fLimpiarFormulario()
      fIrPaso1()
      nuevoCajaDialog.value = true
    }

    function fMostrarEditarCaja (props) {
      cajaCreation.caja = props.row.caja
      cajaCreation.notas = props.row.notas

      cajaCreation.id = props.row.id
      cajaCreation.creada = props.row.creada
      cajaCreation.creadorId = props.row.creadorId
      cajaCreation.eliminada = props.row.eliminada
      cajaCreation.eliminadorId = props.row.eliminadorId
      cajaCreation.modificada = props.row.modificada
      cajaCreation.modificadorId = props.row.modificadorId

      nuevoCajaDialog.value = true
    }

    function fMostrarEliminarCaja (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarCaja(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarCaja (props) {
      afReciclarCaja(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      columnas,
      cajaCreation,
      cajas,
      fFormatoFecha,
      fGuardarCaja,
      fMostrarNuevaCaja,
      fMostrarEditarCaja,
      fMostrarEliminarCaja,
      fMostrarReciclarCaja,
      notas,
      nuevoCajaDialog,
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
