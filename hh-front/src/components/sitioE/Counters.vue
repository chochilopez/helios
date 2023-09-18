<template>
  <q-card class="q-ma-md font-5 cp12-bc3">
    <div class="row">
      <div class="col">
        <div class="row justify-around">

          <div class="col-6 col-sm-3 col-md-2 q-pa-sm">
            <q-card class="cp12-bc4">
              <q-card-actions align="center" class="cp12-c5 cp12-bc1">
                <span class="text-h6">Pacientes</span>
              </q-card-actions>

              <q-card-actions align="center" class="cp12-c1">
                <q-icon name="mdi-account-group" size="md" />
                <span class="text-h4 q-ml-md">{{ patientsCount }}</span>
              </q-card-actions>
            </q-card>
          </div>

          <div class="col-6 col-sm-3 col-md-2 q-pa-sm">
            <q-card class="cp12-bc4">
              <q-card-actions align="center" class="cp12-c5 cp12-bc1">
                <span class="text-h6">Recepciones</span>
              </q-card-actions>

              <q-card-actions align="center" class="cp12-c1">
                <q-icon name="biotech" size="md" />
                <span class="text-h4 q-ml-md">{{ receptionsCOunt }}</span>
              </q-card-actions>
            </q-card>
          </div>

          <div class="col-6 col-sm-3 col-md-2 q-pa-sm">
            <q-card class="cp12-bc4">
              <q-card-actions align="center" class="cp12-c5 cp12-bc1">
                <span class="text-h6">Consultas</span>
              </q-card-actions>

              <q-card-actions align="center" class="cp12-c1">
                <q-icon name="mdi-database-search" size="md" />
                <span class="text-h4 q-ml-md">{{ queriesCount }}</span>
              </q-card-actions>
            </q-card>
          </div>

          <div class="col-6 col-sm-3 col-md-2 q-pa-sm">
            <q-card class="cp12-bc4">
              <q-card-actions align="center" class="cp12-c5 cp12-bc1">
                <span class="text-h6">Visitas</span>
              </q-card-actions>

              <q-card-actions align="center" class="cp12-c1">
                <q-icon name="waving_hand" size="md" />
                <span class="text-h4 q-ml-md">{{ visitsCounts }}</span>
              </q-card-actions>
            </q-card>
          </div>

        </div>
      </div>
    </div>
  </q-card>
</template>

<script>
import { ref } from 'vue'
import { visitService } from 'src/services/visit_service'
import { queryService } from 'src/services/query_service'
import { receptionService } from 'src/services/reception_service'
import { patientService } from 'src/services/patient_service'
import { notifyService } from 'src/helpers/notificar_service'

export default {
  setup () {
    const queriesCount = ref(0)
    const visitsCounts = ref(0)
    const receptionsCOunt = ref(0)
    const patientsCount = ref(0)

    afGetPatientsCount()
    afGetQueriesCount()
    afGetReceptionsCount()
    afGetVisitsCount()

    async function afGetPatientsCount () {
      try {
        const result = await patientService.spfFetchCountAll()
        if (result.status === 200) {
          patientsCount.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la cantidad de pacientes. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
    }

    async function afGetQueriesCount () {
      try {
        const result = await queryService.spfFetchCountAll()
        if (result.status === 200) {
          queriesCount.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la cantidad de consultas. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
    }

    async function afGetReceptionsCount () {
      try {
        const result = await receptionService.spfFetchCountAll()
        if (result.status === 200) {
          receptionsCOunt.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la cantidad de recepciones. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
    }

    async function afGetVisitsCount () {
      try {
        const result = await visitService.spfFetchCountAll()
        if (result.status === 200) {
          visitsCounts.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la cantidad de visitas. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
    }

    return {
      patientsCount,
      receptionsCOunt,
      queriesCount,
      visitsCounts
    }
  }
}
</script>
