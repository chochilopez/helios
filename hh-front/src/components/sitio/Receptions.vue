<template>
  <q-card class="q-ma-md font-5">
    <div class="row justify-around q-pa-sm">
      <div class="col-4">
        <q-card class="cp12-c5">
          <q-card-actions align="center" class="cp12-c5 cp12-bc1">
            <span class="text-h6">Recepciones</span>
          </q-card-actions>

          <q-card-actions align="center" class="cp12-c1 cp12-bc4">
            <q-icon name="waving_hand" size="md" />
            <span class="text-h4 q-ml-md">{{ receptionsCount }}</span>
          </q-card-actions>
        </q-card>
      </div>
    </div>
    <div class="row justify-around q-pb-xl">
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afGetByBody" v-model="whatsappsBody" label="Buscar por texto del mensaje" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="sms" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afGetByBody" />
          </template>
        </q-input>
      </div>
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afGetBySid" v-model="whatsappsSid" label="Buscar por identificador SID" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="qr_code" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afGetBySid" />
          </template>
        </q-input>
      </div>
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afGetByTo" v-model="whatsappsTo" label="Buscar por número de celular" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="smartphone" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afGetByTo" />
          </template>
        </q-input>
      </div>

    </div>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        :showing="!loading"
        bordered
        title="Receptions"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        :filter="filter"
        hide-no-data
        :rows="receptions"
        row-key="id"
      >
        <template v-slot:top-right>
          <q-input outlined dense debounce="300" v-model="filter" placeholder="Buscar">
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
          <q-btn
            class="q-ml-md cp12-bc3 cp12-c5"
            icon-right="archive"
            label="Exportar"
            no-caps
            @click="exportTable" />
        </template>
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn @click="fShowError(props)" dense round flat color="red" icon="sms_failed" v-if="props.row.errorCode">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Ver Error
              </q-tooltip>
            </q-btn>
            <q-btn @click="fShowSeeDialog(props)" dense round flat class="cp1-c4" icon="visibility">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Información
              </q-tooltip>
            </q-btn>
          </q-td>
        </template>
        <template v-slot:body-cell-name="props">
          <q-td :props="props">
            {{ props.row.patient.name }}
          </q-td>
        </template>
        <template v-slot:body-cell-to="props">
          <q-td :props="props">
            <span v-if="props.row.to === 'reception:+5493434565568'">
              Sistema
            </span>
            <span v-if="props.row.to !== 'reception:+5493434565568'">
              {{ props.row.to.slice(9, 30) }}
            </span>
          </q-td>
        </template>
        <template v-slot:body-cell-type="props">
          <q-td :props="props">
            <q-chip v-if="props.row.numMedia === '0'" color="deep-purple-6" text-color="white">
              Notificacion
            </q-chip>
            <q-chip v-if="props.row.numMedia === '1'" color="purple-6" text-color="white">
              Estudio
            </q-chip>
          </q-td>
        </template>
        <template v-slot:body-cell-status="props">
          <!-- accepted, scheduled, canceled, queued, sending, sent, failed, delivered, undelivered, receiving, received, read -->
          <q-td :props="props">
            <q-chip square v-if="props.row.status === 'accepted'" color="teal-9" text-color="white">
              Aceptado
            </q-chip>
            <q-chip square v-if="props.row.status === 'queued'" color="cyan-9" text-color="white">
              Encolado
            </q-chip>
            <q-chip square v-if="props.row.status === 'sending'" color="amber-9" text-color="white">
              Enviando
            </q-chip>
            <q-chip square v-if="props.row.status === 'sent'" color="light-green-9" text-color="white">
              Enviado
            </q-chip>
            <q-chip square v-if="props.row.status === 'failed'" color="red-9" text-color="white">
              Fallido
            </q-chip>
            <q-chip square v-if="props.row.status === 'delivered'" color="green-9" text-color="white">
              Entregado
            </q-chip>
            <q-chip square v-if="props.row.status === 'undelivered'" color="yellow-9" text-color="white">
              No Entregado
            </q-chip>
            <q-chip square v-if="props.row.status === 'read'" color="green-7" text-color="white">
              Leido
            </q-chip>
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="loading">
        <q-spinner-puff class="cp12-c3 text-center" size="12em" />
      </q-inner-loading>
    </div>
  </div>

  <q-dialog v-model="errorDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Ver Error</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>
        <div class="row">
          <p class="text-h4 text-black">Error code: {{ reception.errorCode }}</p>
          <p class="text-h5 text-black">Message: {{ reception.errorMessage }}</p>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="seeDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Ver Whatsapp</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>
        <div class="row">

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.id">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.id }}</q-item-label>
              <q-item-label caption>Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.accountSid">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.accountSid }}</q-item-label>
              <q-item-label caption>Account SID</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.apiVersion">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.apiVersion }}</q-item-label>
              <q-item-label caption>API Version</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.dateCreated">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.dateCreated }}</q-item-label>
              <q-item-label caption>Date Created</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.dateSent">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.dateSent }}</q-item-label>
              <q-item-label caption>Date Sent</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.dateUpdated">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.dateUpdated }}</q-item-label>
              <q-item-label caption>Date Updated</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.direction">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.direction }}</q-item-label>
              <q-item-label caption>Direction</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.errorCode">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.errorCode }}</q-item-label>
              <q-item-label caption>Error Code</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.errorMessage">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.errorMessage }}</q-item-label>
              <q-item-label caption>Error Message</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.numSegments">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.numSegments }}</q-item-label>
              <q-item-label caption>Num Segments</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.price">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.price }}</q-item-label>
              <q-item-label caption>Price</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.priceUnit">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.priceUnit }}</q-item-label>
              <q-item-label caption>Price Unit</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.sid">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.sid }}</q-item-label>
              <q-item-label caption>SID</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.status">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.status }}</q-item-label>
              <q-item-label caption>Status</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.subresourceUris">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.subresourceUris }}</q-item-label>
              <q-item-label caption>Subresource Uris</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.to">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.to }}</q-item-label>
              <q-item-label caption>To</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.uri">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.uri }}</q-item-label>
              <q-item-label caption>Uri</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.messagingServiceSid">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.messagingServiceSid }}</q-item-label>
              <q-item-label caption>Messaging Service Sid</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.from">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.from }}</q-item-label>
              <q-item-label caption>From</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.body">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.body }}</q-item-label>
              <q-item-label caption>Body</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.numMedia">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.numMedia }}</q-item-label>
              <q-item-label caption>Num Media</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.created !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.created }}</q-item-label>
              <q-item-label caption>Created</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.creator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.creator.username }}</q-item-label>
              <q-item-label caption>Creator</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.modified !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.modified }}</q-item-label>
              <q-item-label caption>Modified</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.modifier !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.modifier.username }}</q-item-label>
              <q-item-label caption>Modifier</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.removed !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.removed }}</q-item-label>
              <q-item-label caption>Removed</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="reception.remover">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ reception.remover.username }}</q-item-label>
              <q-item-label caption>Remover</q-item-label>
            </q-item-section>
          </q-item>

        </div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notify_service'
import { ReceptionModel } from 'src/models/reception_model'
import { receptionService } from 'src/services/reception_service'

const pagination = {
  rowsPerPage: 10,
  sortBy: 'id',
  descending: true
}

const columns = [
  {
    name: 'idRecepcion',
    label: 'Id Recepción',
    field: 'idRecepcion',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'tipoPac',
    label: 'Tipo Paciente',
    field: 'tipoPac',
    align: 'center'
  },
  {
    name: 'name',
    label: 'Nombre',
    field: '',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'validada',
    label: 'Validada',
    field: '',
    align: 'center'
  },
  {
    name: 'pagada',
    label: 'Pagada',
    field: '',
    align: 'center'
  },
  {
    name: 'status',
    label: 'Estado',
    field: '',
    align: 'center'
  },
  {
    name: 'notification',
    label: 'Notificacion',
    field: '',
    align: 'center'
  },
  {
    name: 'laboratory',
    label: 'Laboratorio',
    field: '',
    align: 'center'
  },
  {
    name: 'actions',
    label: 'Acciones',
    field: '',
    align: 'center'
  }
]

export default {
  setup () {
    const loading = ref(false)
    const errorDialog = ref(false)
    const seeDialog = ref(false)
    const reception = reactive(new ReceptionModel())
    const receptions = ref([])
    const receptionsCount = ref()
    const whatsappsBody = ref()
    const whatsappsSid = ref()
    const whatsappsTo = ref()

    afGetTop100()
    afGetCount()

    function fShowError (props) {
      Object.assign(reception, props.row)
      errorDialog.value = true
    }

    function fShowSeeDialog (props) {
      Object.assign(reception, props.row)
      seeDialog.value = true
    }

    async function afGetByBody () {
      loading.value = true
      try {
        const result = await receptionService.spfFetchAllByBody(whatsappsBody.value)
        if (result.status === 200) {
          receptions.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener receptions por mensaje. ' + err)
      }
      loading.value = false
    }

    async function afGetBySid () {
      loading.value = true
      try {
        const result = await receptionService.spfFetchAllBySid(whatsappsSid.value)
        if (result.status === 200) {
          receptions.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener receptions por SID. ' + err)
      }
      loading.value = false
    }

    async function afGetByTo () {
      loading.value = true
      try {
        const result = await receptionService.spfFetchAllByTo(whatsappsTo.value)
        if (result.status === 200) {
          receptions.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener receptions por celular receptor. ' + err)
      }
      loading.value = false
    }

    async function afGetCount () {
      const result = await receptionService.spfFetchCountAll()
      receptionsCount.value = result.data
    }

    async function afGetTop100 () {
      loading.value = true
      try {
        const result = await receptionService.spfFetchTop100()
        if (result.status === 200) {
          receptions.value = result.data
          console.log(result.data)
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener las las ultimas 50 receptions. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
    }

    return {
      afGetByBody,
      afGetBySid,
      afGetByTo,
      fShowError,
      fShowSeeDialog,

      loading,
      columns,
      errorDialog,
      seeDialog,
      pagination,
      reception,
      receptions,
      whatsappsBody,
      whatsappsSid,
      whatsappsTo,
      receptionsCount,

      filter: ref(''),
      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(receptions.value[0]).join(';'),
          ...receptions.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'receptions.csv')
        link.click()
      }
    }
  }
}
</script>
