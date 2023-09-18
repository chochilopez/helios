<template>
  <q-card class="q-ma-md fuente5">
    <div class="row justify-around q-mb-sm">
      <div class="col-4">
        <q-card class="my-card text-white">
          <q-card-actions align="center" class="bg-c-1">
            <span class="text-h6">Pacientes</span>
          </q-card-actions>

          <q-card-actions align="center" class="bg-c-1">
            <q-icon name="mdi-account-group" size="md" />
            <span class="text-h4 q-ml-md">{{ contadorPacientes }}</span>
          </q-card-actions>
        </q-card>
      </div>
    </div>
    <div class="row justify-around q-pb-xl">
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afGetPatientsByName" v-if="verInputNombre" v-model="pacienteNombre" label="Buscar por Nombre / Apellido" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="mdi-format-letter-case" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="mano" @click="afGetPatientsByName" />
          </template>
        </q-input>
      </div>
      <!--div class="col-xs-12 col-md-6">
        <q-input outlined v-on:keypress.enter="afGetPatientById" v-if="verInputId" v-model="pacienteId" label="Buscar por Id Paciente" counter maxlength="7" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="mdi-numeric" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="mano" @click="afGetPatientById" />
          </template>
        </q-input>
      </!--div-->
    </div>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        :showing="!cargando"
        bordered
        title="Pacientes"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        :filter="filter"
        hide-no-data
        :rows="pacientes"
        row-key="name"
      >
        <template v-slot:top-right>
          <q-btn
            class="q-ml-md bg-c-2 text-white"
            icon-right="archive"
            label="Exportar"
            no-caps
            @click="exportTable" />
        </template>
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn @click="fSeeChart(props)" dense round flat class="text-c-12" icon="mdi-clipboard-account">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Ver historial
              </q-tooltip>
            </q-btn>
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="cargando">
        <q-spinner-puff class="c-3" size="6em" />
      </q-inner-loading>
    </div>
  </div>
</template>

<script>
import { useRouter } from 'vue-router'
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notificar_service'
import { patientService } from 'src/services/patient_service'

const paginacion = {
  rowsPerPage: 10,
  sortBy: 'idPaciente',
  descending: true
}

const columnas = [
  {
    name: 'idPaciente',
    label: 'Id paciente',
    field: 'idPaciente',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'nomyape',
    label: 'Nombre y Apellido',
    field: 'nomyape',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'domicilio',
    label: 'Domicilio',
    field: 'domicilio',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'nroDoc',
    label: 'Numero doc',
    field: 'nroDoc',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'telefono',
    label: 'Telefono',
    field: 'telefono',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'actions',
    label: 'Historial',
    field: '',
    align: 'center'
  }
]

export default {
  setup () {
    const cargando = ref(false)
    const contadorPacientes = ref(0)
    const filter = ref('')
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
      creado: '',
      creador: { username: '' },
      modificado: '',
      modificador: { username: '' },
      eliminado: '',
      eliminador: { username: '' }
    })
    const pacienteId = ref('')
    const pacientes = ref([])
    const pacienteNombre = ref('')
    const recepciones = ref([])
    const router = useRouter()
    const verBotonTodos = ref(true)
    const verInputId = ref(true)
    const verInputNombre = ref(true)

    afGetPatientsCount()

    function fSeeChart (props) {
      router.push({ name: 'Paciente', params: { id: props.row.idPaciente } })
    }

    async function afGetPatientsByName () {
      cargando.value = true
      try {
        const result = await patientService.spfFetchPatientsByName(pacienteNombre.value)
        if (result.status === 200) {
          pacientes.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener pacientes por nombre. ' + err)
      }
      cargando.value = false
    }

    async function afGetPatientById () {
      cargando.value = true
      try {
        const result = await patientService.spfFetchPatientsById(pacienteId.value)
        if (result.status === 200) {
          pacientes.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener pacientes por id. ' + err)
      }
      cargando.value = false
    }

    async function afGetPatientsCount () {
      const result = await patientService.spfFetchPatientsCount()
      contadorPacientes.value = result.data
    }

    async function afGetAllPatients () {
      cargando.value = true
      try {
        const result = await patientService.spfFetchAllPatients()
        if (result.status === 200) {
          pacientes.value = [...result.data]
          verInputId.value = false
          verInputNombre.value = false
          verBotonTodos.value = false
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
        notifyService.infoError('Ocurrio un problema al mostrar los pacientes. ' + err)
      }
      cargando.value = false
    }

    return {
      afGetAllPatients,
      afGetPatientById,
      afGetPatientsByName,
      fSeeChart,

      cargando,
      columnas,
      contadorPacientes,
      filter,
      paciente,
      pacienteId,
      pacienteNombre,
      pacientes,
      paginacion,
      recepciones,
      verInputId,
      verInputNombre,
      verBotonTodos,

      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(pacientes.value[0]).join(';'),
          ...pacientes.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'pacientes.csv')
        link.click()
      }
    }
  }
}
</script>

<style scoped>
.mano {
  cursor: pointer;
}
</style>
