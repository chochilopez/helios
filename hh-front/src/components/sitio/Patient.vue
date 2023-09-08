<template>
  <PatientPatientData :paciente="paciente" />
  <div class="row justify-center">
    <div class="col-12">
      <q-table
        class="q-mx-sm"
        :showing="!cargando"
        bordered
        title="Estudios"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        hide-no-data
        :rows="recepciones"
        row-key="name"
      >
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn @click="fSeeStudy(props)" dense round flat class="text-c-12" icon="mdi-clipboard-text-search">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Ver Estudio
              </q-tooltip>
            </q-btn>
          </q-td>
        </template>
        <template v-slot:body-cell-estado="props">
          <q-td :props="props">
            <q-chip icon="fas fa-check-circle" square class="bg-positive text-white" v-if="props.row.pagada === 'S'">Pagada</q-chip>
            <q-chip icon="fas fa-exclamation-circle" square class="bg-negative text-white" v-if="props.row.pagada === 'N'">Debe</q-chip>
          </q-td>
        </template>
        <template v-slot:body-cell-medico="props">
          <q-td :props="props">
            {{ props.row.medico.nomyape }}
          </q-td>
        </template>
        <template v-slot:body-cell-obraSocial="props">
          <q-td :props="props">
            {{ props.row.obraSocial.nombre }}
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="cargando">
        <q-spinner-puff class="text-c-3" size="6em" />
      </q-inner-loading>
    </div>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notify_service'
import { helper } from 'app/src/helpers/helper'
import PatientPatientData from './PatientPatientData.vue'
import { useRouter } from 'vue-router'
import { patientService } from 'src/services/patient_service'
import { receptionService } from 'src/services/reception_service'

const columns = [
  { name: 'idRecepcion', label: 'Id Estudio', align: 'center', field: 'idRecepcion', sortOrder: 'da', sortable: true },
  { name: 'tipoPac', label: 'Tipo paciente', align: 'center', field: 'tipoPac', sortable: true },
  { name: 'fecha', label: 'Fecha AAAA-MM-DD', align: 'left', field: 'fecha', sortable: true },
  { name: 'medico', label: 'Medico solicitante', align: 'left', field: '', sortable: true },
  { name: 'obraSocial', label: 'Obra social', align: 'left', field: '', sortable: true },
  { name: 'estado', label: 'Estado', align: 'center', field: '' },
  { name: 'actions', label: 'Ver estudio', field: '', align: 'center' }
]

const paginacion = {
  rowsPerPage: 20,
  sortBy: 'fecha',
  descending: true
}

export default {
  props: {
    id: {
      type: String,
      required: true
    }
  },
  components: {
    PatientPatientData
  },
  setup (props) {
    const cargando = ref(false)
    const paciente = reactive({
      idPaciente: '',
      nomyape: '',
      domicilio: '',
      laboral: '',
      telefono: '',
      fechaNac: '',
      nroDoc: '',
      celular: '',
      mail: '',
      ocupacion: '',
      notas: '',
      idCp: '',
      idObraSocial: '',
      nroAfiliado: '',
      sexo: '',
      hCenClinica: '',
      tipoIVA: '',
      obraSocial: {
        nombre: ''
      }
    })
    const recepcion = reactive({
      idRecepcion: '',
      dni: '',
      fecha: '',
      tipoPac: '',
      idPaciente: '',
      retirar: '',
      idMedico: '',
      registrada: '',
      grupo: '',
      idReceptor: '',
      idObraSocial: '',
      idInstitucion: '',
      nroAfiliado: '',
      importe: '',
      pagada: '',
      factor: '',
      voluntario: '',
      impreso: '',
      internacion: '',
      coseguro: '',
      entrega: '',
      urgencia: '',
      valorOrden: '',
      coSegPagado: '',
      valTransfusion: '',
      valPreparacion: '',
      devolucion: '',
      notas: '',
      validada: '',
      horas: '',
      minutos: '',
      habilitadoDonar: '',
      autorizada: '',
      idPerfil: ''
    })
    const recepciones = ref([])
    const router = useRouter()
    const verEstudio = ref(false)

    afGetReceptionsByIdPatient(props.id)
    afGetPatientById(props.id)

    async function afGetPatientById (idPaciente) {
      cargando.value = true
      try {
        const result = await patientService.spfFetchPatientById(idPaciente)
        if (result.status === 200) {
          Object.assign(paciente, result.data)
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Ocurrio un problema al obtener los datos del paciente. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
    }

    function fFormatoFecha (fecha) {
      return helper.getDateWithFormat(fecha)
    }

    function fSeeStudy (props) {
      router.push({ name: 'PacienteEstudio', params: { idRecepcion: props.row.idRecepcion, tipoPaciente: props.row.tipoPac } })
    }

    async function afGetReceptionsByIdPatient (idPaciente) {
      cargando.value = true
      try {
        const result = await receptionService.spfFetchReceptionsByPatientId(idPaciente)
        if (result.status === 200) {
          recepciones.value = result.data
          console.info(result.headers.message)
          notifyService.notifySuccess(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        notifyService.infoError('Ocurrio un problema al obtener los datos de recepción. ' + err)
      }
      cargando.value = false
    }

    return {
      fFormatoFecha,
      fSeeStudy,

      cargando,
      columns,
      paciente,
      paginacion,
      recepcion,
      recepciones,
      verEstudio
    }
  }
}
</script>

<style scoped>
.claseCSS {
  font-weight: bold;
}
</style>
