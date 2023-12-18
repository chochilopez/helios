<template>
  <q-card class="font-5 no-shadow no-border">
    <div class="row q-pa-md">
      <div class="col">
        <q-table
          title="Conceptos de compra"
          :columns="columnas"
          rows-per-page-label="Registros por pagina"
          no-data-label="Sin datos para mostrar"
          :pagination="paginacion"
          hide-no-data
          :rows="conceptosCompra"
          row-key="id"
        >
          <template v-slot:top-left>
            <div class="column">
              <p class="text-h5">Conceptos de compra</p>
              <q-btn
                class="paleta2-fondo2 paleta1-color1 q-mb-lg"
                icon="add_circle"
                label="Nuevo Concepto compra"
                @click="fMostrarNuevaConceptoCompra"
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
                  @click="fMostrarEditarConceptoCompra(props)"
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
                  @click="fMostrarEliminarConceptoCompra(props)"
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
                  @click="fMostrarReciclarConceptoCompra(props)"
                >
                  <q-icon size="2em" class="q-pa-xs" name="recycling" />
                  <q-tooltip>
                    Reciclar
                  </q-tooltip>
                </q-btn>
              </q-td>
              <q-td auto-width class="text-center">
                {{ props.row.concepto }}
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

  <q-dialog v-model="nuevoConceptoCompraDialog" persistent transition-show="fade" transition-hide="fade">
    <q-card style="max-width: 650px">
      <q-card-section class="row items-center">
        <div class="text-h6 text-grey-8">Nuevo concepto compra</div>
        <q-space />
        <q-btn class="text-grey-8" icon="close" flat round dense v-close-popup />
      </q-card-section>
      <div class="row justify-around">
        <q-icon name="img:/icons/numeros/number1.svg" size="3em" class="svg-primary" :class="{ 'svg-accent': paso1 }" />
      </div>
      <q-card-section v-if="paso1">
        <q-form v-on:submit.prevent="fGuardarConceptoCompra">
          <div class="row justify-around">
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                v-model="conceptoCompraCreation.concepto"
                :rules="[reglas.requerido, reglas.min3, reglas.max50]"
                outlined
                dense
                clearable
                label="Nombre del concepto"
              >
              </q-input>
            </div>
            <div class="col-xs-6 q-pa-md">
              <q-input
                class="nuevo-input"
                type="textarea"
                v-model="conceptoCompraCreation.notas"
                autogrow
                outlined
                dense
                clearable
                label="Notas"
              >
              </q-input>
            </div>
          </div>
          <div class="row justify-between q-pa-md">
            <q-btn class="paleta2-color2 bg-white" v-close-popup icon="close" ripple>
              Cerrar
            </q-btn>
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
import { ConceptoCompraCreation } from 'src/models/creation/concepto_compra_creation'
import { conceptoCompraService } from 'src/services/concepto_compra_service'
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
    name: 'onceptoCompra',
    label: 'Concepto compra',
    align: 'left',
    field: 'conceptoCompra',
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

    const conceptoCompraCreation = reactive(new ConceptoCompraCreation())
    const conceptosCompra = ref([])
    const notas = ref(null)
    const nuevoConceptoCompraDialog = ref(false)
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
          resultado = await conceptoCompraService.spfBuscarTodasConEliminadasPaginadas(paginadoDTO)
        } else {
          resultado = await conceptoCompraService.spfBuscarTodasPaginadas(paginadoDTO)
        }
        if (resultado.status === 200) {
          conceptosCompra.value = resultado.data.content
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
        }
      } catch (err) {
        console.clear()
        if (err.response.status === 404) {
          conceptosCompra.value = []
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

    async function afGuardarConceptoCompra () {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await conceptoCompraService.spfGuardar(conceptoCompraCreation)
        if (resultado.status === 201) {
          console.log(resultado.headers.mensaje)
          $q.loading.hide()
          notificarService.notificarExito('Se creó correctamente la conceptoCompra.')
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

    async function afEliminarConceptoCompra (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await conceptoCompraService.spfBorrar(id)
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

    async function afReciclarConceptoCompra (id) {
      $q.loading.show()
      try {
        let resultado = null
        resultado = await conceptoCompraService.spfReciclar(id)
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

    function fGuardarConceptoCompra () {
      afGuardarConceptoCompra().then(() => {
        afBuscarPaginadas().then(() => {
          nuevoConceptoCompraDialog.value = false
          fIrPaso1()
        })
      })
    }

    function fIrPaso1 () {
      paso1.value = true
      fLimpiarFormulario()
    }

    function fLimpiarFormulario () {
      conceptoCompraCreation.concepto = null
      conceptoCompraCreation.notas = null

      conceptoCompraCreation.id = null
      conceptoCompraCreation.creadorId = null
      conceptoCompraCreation.creador = null
      conceptoCompraCreation.creada = null
      conceptoCompraCreation.modificadorId = null
      conceptoCompraCreation.modificador = null
      conceptoCompraCreation.modificada = null
      conceptoCompraCreation.eliminadorId = null
      conceptoCompraCreation.eliminador = null
      conceptoCompraCreation.eliminada = null
    }

    function fMostrarNuevaConceptoCompra () {
      fLimpiarFormulario()
      fIrPaso1()
      nuevoConceptoCompraDialog.value = true
    }

    function fMostrarEditarConceptoCompra (props) {
      conceptoCompraCreation.concepto = props.row.concepto
      conceptoCompraCreation.notas = props.row.notas

      conceptoCompraCreation.id = props.row.id
      conceptoCompraCreation.creada = props.row.creada
      conceptoCompraCreation.creadorId = props.row.creadorId
      conceptoCompraCreation.eliminada = props.row.eliminada
      conceptoCompraCreation.eliminadorId = props.row.eliminadorId
      conceptoCompraCreation.modificada = props.row.modificada
      conceptoCompraCreation.modificadorId = props.row.modificadorId

      nuevoConceptoCompraDialog.value = true
    }

    function fMostrarEliminarConceptoCompra (props) {
      notificarService.infoAlerta('No se puede eliminar el recurso.')
      // afEliminarConceptoCompra(props.row.id).then(() => {
      //   afBuscarPaginadas().then(() => {

      //   })
      // })
    }

    function fMostrarReciclarConceptoCompra (props) {
      afReciclarConceptoCompra(props.row.id).then(() => {
        afBuscarPaginadas().then(() => {

        })
      })
    }

    return {
      autoridad,
      columnas,
      conceptoCompraCreation,
      conceptosCompra,
      fFormatoFecha,
      fGuardarConceptoCompra,
      fMostrarNuevaConceptoCompra,
      fMostrarEditarConceptoCompra,
      fMostrarEliminarConceptoCompra,
      fMostrarReciclarConceptoCompra,
      notas,
      nuevoConceptoCompraDialog,
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
