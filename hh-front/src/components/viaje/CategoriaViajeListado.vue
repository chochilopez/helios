<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Categorías de viaje"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="categoriasViaje"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Categorías de viaje</p>
              <q-btn
                class="paleta2-fondo2 paleta1-color1 q-mb-lg"
                icon="add_circle"
                label="Nueva categoría de viaje"
                @click="fMostrarNuevaCategoriaViaje"
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
                  @click="fMostrarEditarCategoria(props)"
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
                  @click="fMostrarEliminarCategoria(props)"
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
                  @click="fMostrarReciclarCategoria(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td auto-width class="text-center">
                {{ props.row.categoria }}
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

  <q-dialog v-model="nuevoCategoriaViajeDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nueva categoría viaje</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarCategoriaViaje">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="categoriaViajeCreation.categoria"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Categoría del viaje"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="categoriaViajeCreation.notas"
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
import { CategoriaViajeCreation } from 'src/models/creation/categoria_viaje_creation'
import { categoriaViajeService } from 'src/services/categoria_viaje_service'
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
    name: 'categoria',
    label: 'Categoría del viaje',
    align: 'left',
    field: 'categoria',
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

    const categoriaViajeCreation = reactive(new CategoriaViajeCreation())
    const categoriasViaje = ref([])
    const notas = ref(null)
    const nuevoCategoriaViajeDialog = ref(false)
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
          resultado = await categoriaViajeService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await categoriaViajeService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          categoriasViaje.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          categoriasViaje.value = []
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

    async function afGuardarCategoriaViaje () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await categoriaViajeService.spfGuardar(categoriaViajeCreation)
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

    async function afEliminarCategoriaViaje (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await categoriaViajeService.spfBorrar(id)
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

    async function afReciclarCategoriaViaje (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await categoriaViajeService.spfReciclar(id)
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

    function fGuardarCategoriaViaje () {
      afGuardarCategoriaViaje().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoCategoriaViajeDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      categoriaViajeCreation.categoria = null
      categoriaViajeCreation.notas = null

      categoriaViajeCreation.id = null
      categoriaViajeCreation.creadorId = null
      categoriaViajeCreation.creador = null
      categoriaViajeCreation.creada = null
      categoriaViajeCreation.modificadorId = null
      categoriaViajeCreation.modificador = null
      categoriaViajeCreation.modificada = null
      categoriaViajeCreation.eliminadorId = null
      categoriaViajeCreation.eliminador = null
      categoriaViajeCreation.eliminada = null
    }

    function fMostrarNuevaCategoriaViaje () {
      fLimpiarFormulario()
      fIrPaso1()
      nuevoCategoriaViajeDialog.value = true
    }

    function fMostrarEditarCategoria (props) {
      categoriaViajeCreation.categoria = props.row.categoria
      categoriaViajeCreation.notas = props.row.notas

      categoriaViajeCreation.id = props.row.id
      categoriaViajeCreation.creada = props.row.creada
      categoriaViajeCreation.creadorId = props.row.creadorId
      categoriaViajeCreation.eliminada = props.row.eliminada
      categoriaViajeCreation.eliminadorId = props.row.eliminadorId
      categoriaViajeCreation.modificada = props.row.modificada
      categoriaViajeCreation.modificadorId = props.row.modificadorId

      nuevoCategoriaViajeDialog.value = true
    }

    function fMostrarEliminarCategoria (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarCategoriaViaje(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarCategoria (props) {
      afReciclarCategoriaViaje(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      columnas,
      categoriaViajeCreation,
      categoriasViaje,
      fFormatoFecha,
      fGuardarCategoriaViaje,
      fMostrarNuevaCategoriaViaje,
      fMostrarEditarCategoria,
      fMostrarEliminarCategoria,
      fMostrarReciclarCategoria,
      notas,
      nuevoCategoriaViajeDialog,
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
