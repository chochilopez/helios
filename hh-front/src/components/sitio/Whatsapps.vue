<template>
  <q-card class="q-ma-md font-5">
    <div class="row justify-around q-pa-sm">
      <div class="col-4">
        <q-card class="cp12-c5">
          <q-card-actions align="center" class="cp12-c5 cp12-bc1">
            <span class="text-h6">Whatsapps</span>
          </q-card-actions>

          <q-card-actions align="center" class="cp12-c1 cp12-bc4">
            <q-icon name="waving_hand" size="md" />
            <span class="text-h4 q-ml-md">{{ whatsappsCount }}</span>
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
    <div class="col text-center">
      <q-img src="../../assets/images/statuses.webp" width="50vw" />
    </div>
  </div>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        :showing="!loading"
        bordered
        title="Whatsapps"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        :filter="filter"
        hide-no-data
        :rows="whatsapps"
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
        <template v-slot:body-cell-from="props">
          <q-td :props="props">
            <span v-if="props.row.from === 'whatsapp:+5493434565568'">
              Sistema
            </span>
            <span v-if="props.row.from !== 'whatsapp:+5493434565568'">
              {{ props.row.from.slice(9, 30) }}
            </span>
          </q-td>
        </template>
        <template v-slot:body-cell-to="props">
          <q-td :props="props">
            <span v-if="props.row.to === 'whatsapp:+5493434565568'">
              Sistema
            </span>
            <span v-if="props.row.to !== 'whatsapp:+5493434565568'">
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
          <p class="text-h4 text-black">Error code: {{ whatsapp.errorCode }}</p>
          <p class="text-h5 text-black">Message: {{ whatsapp.errorMessage }}</p>
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

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.id">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.id }}</q-item-label>
              <q-item-label caption>Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.accountSid">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.accountSid }}</q-item-label>
              <q-item-label caption>Account SID</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.apiVersion">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.apiVersion }}</q-item-label>
              <q-item-label caption>API Version</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.dateCreated">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.dateCreated }}</q-item-label>
              <q-item-label caption>Date Created</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.dateSent">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.dateSent }}</q-item-label>
              <q-item-label caption>Date Sent</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.dateUpdated">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.dateUpdated }}</q-item-label>
              <q-item-label caption>Date Updated</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.direction">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.direction }}</q-item-label>
              <q-item-label caption>Direction</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.errorCode">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.errorCode }}</q-item-label>
              <q-item-label caption>Error Code</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.errorMessage">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.errorMessage }}</q-item-label>
              <q-item-label caption>Error Message</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.numSegments">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.numSegments }}</q-item-label>
              <q-item-label caption>Num Segments</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.price">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.price }}</q-item-label>
              <q-item-label caption>Price</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.priceUnit">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.priceUnit }}</q-item-label>
              <q-item-label caption>Price Unit</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.sid">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.sid }}</q-item-label>
              <q-item-label caption>SID</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.status">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.status }}</q-item-label>
              <q-item-label caption>Status</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.subresourceUris">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.subresourceUris }}</q-item-label>
              <q-item-label caption>Subresource Uris</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.to">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.to }}</q-item-label>
              <q-item-label caption>To</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.uri">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.uri }}</q-item-label>
              <q-item-label caption>Uri</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.messagingServiceSid">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.messagingServiceSid }}</q-item-label>
              <q-item-label caption>Messaging Service Sid</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.from">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.from }}</q-item-label>
              <q-item-label caption>From</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.body">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.body }}</q-item-label>
              <q-item-label caption>Body</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.numMedia">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.numMedia }}</q-item-label>
              <q-item-label caption>Num Media</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.created !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.created }}</q-item-label>
              <q-item-label caption>Created</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.creator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.creator.username }}</q-item-label>
              <q-item-label caption>Creator</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.modified !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.modified }}</q-item-label>
              <q-item-label caption>Modified</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.modifier !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.modifier.username }}</q-item-label>
              <q-item-label caption>Modifier</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.removed !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.removed }}</q-item-label>
              <q-item-label caption>Removed</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="whatsapp.remover">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ whatsapp.remover.username }}</q-item-label>
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
import { notifyService } from 'src/helpers/notificar_service'
import { WhatsappModel } from 'src/models/whatsapp_model'
import { whatsappService } from 'src/services/whatsapp_service'

const pagination = {
  rowsPerPage: 10,
  sortBy: 'id',
  descending: true
}

const columns = [
  {
    name: 'id',
    label: 'Id',
    field: 'id',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'from',
    label: 'Origen',
    field: '',
    align: 'center'
  },
  {
    name: 'to',
    label: 'Destino',
    field: '',
    align: 'center'
  },
  {
    name: 'type',
    label: 'Tipo',
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
    name: 'sent',
    label: 'Enviado',
    field: 'dateSent',
    sortable: true,
    sortOrder: 'ad',
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
    const whatsapp = reactive(new WhatsappModel())
    const whatsapps = ref([])
    const whatsappsCount = ref()
    const whatsappsBody = ref()
    const whatsappsSid = ref()
    const whatsappsTo = ref()

    afGetTop100()
    afGetCount()

    function fShowError (props) {
      Object.assign(whatsapp, props.row)
      errorDialog.value = true
    }

    function fShowSeeDialog (props) {
      Object.assign(whatsapp, props.row)
      seeDialog.value = true
    }

    async function afGetByBody () {
      loading.value = true
      try {
        const result = await whatsappService.spfFetchAllByBody(whatsappsBody.value)
        if (result.status === 200) {
          whatsapps.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener whatsapps por mensaje. ' + err)
      }
      loading.value = false
    }

    async function afGetBySid () {
      loading.value = true
      try {
        const result = await whatsappService.spfFetchAllBySid(whatsappsSid.value)
        if (result.status === 200) {
          whatsapps.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener whatsapps por SID. ' + err)
      }
      loading.value = false
    }

    async function afGetByTo () {
      loading.value = true
      try {
        const result = await whatsappService.spfFetchAllByTo(whatsappsTo.value)
        if (result.status === 200) {
          whatsapps.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener whatsapps por celular receptor. ' + err)
      }
      loading.value = false
    }

    async function afGetCount () {
      const result = await whatsappService.spfFetchCountAll()
      whatsappsCount.value = result.data
    }

    async function afGetTop100 () {
      loading.value = true
      try {
        const result = await whatsappService.spfFetchTop100()
        if (result.status === 200) {
          whatsapps.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener las las ultimas 50 whatsapps. ' + err
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
      whatsapp,
      whatsapps,
      whatsappsBody,
      whatsappsSid,
      whatsappsTo,
      whatsappsCount,

      filter: ref(''),
      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(whatsapps.value[0]).join(';'),
          ...whatsapps.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'whatsapps.csv')
        link.click()
      }
    }
  }
}
</script>
