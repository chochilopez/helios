<template>
  <q-card class="q-ma-md font-5">
    <div class="row justify-around q-pa-sm">
      <div class="col-4">
        <q-card class="cp12-c5">
          <q-card-actions align="center" class="cp12-c5 cp12-bc1">
            <span class="text-h6">Visitas</span>
          </q-card-actions>

          <q-card-actions align="center" class="cp12-c1 cp12-bc4">
            <q-icon name="waving_hand" size="md" />
            <span class="text-h4 q-ml-md">{{ visitsCount }}</span>
          </q-card-actions>
        </q-card>
      </div>
    </div>
    <div class="row justify-around q-pb-xl">
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afGetByIp" v-model="visitIp" label="Buscar por dirección IP" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="lan" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afGetByIp" />
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
        title="Visitas"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        :filter="filter"
        hide-no-data
        :rows="visits"
        row-key="ip"
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
            <q-btn @click="fShowSeeDialog(props)" dense round flat class="cp1-c4" icon="visibility">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Información
              </q-tooltip>
            </q-btn>
          </q-td>
        </template>
        <template v-slot:body-cell-countryFlag="props">
          <q-td :props="props">
            <q-img
              :src="props.row.country_flag"
              fit="cover"
              width="50px"
            />
          </q-td>
        </template>
        <template v-slot:body-cell-created="props">
          <q-td :props="props">
            {{ props.row.created }}
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="loading">
        <q-spinner-puff class="cp12-c3 text-center" size="12em" />
      </q-inner-loading>
    </div>
  </div>

  <q-dialog v-model="seeDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Ver visitante</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>
        <div class="row">

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.id }}</q-item-label>
              <q-item-label caption>Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.ip }}</q-item-label>
              <q-item-label caption>Direccion Ip</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.hostname === null || visit.hostname === '' ? 'SIN DATOS' : visit.hostname }}</q-item-label>
              <q-item-label caption>Hostname</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.continent_code === null || visit.continent_code === '' ? 'SIN DATOS' : visit.continent_code }}</q-item-label>
              <q-item-label caption>Continent Code</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.continent_name === null || visit.continent_name === '' ? 'SIN DATOS' : visit.continent_name }}</q-item-label>
              <q-item-label caption>Continent Name</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.countryCode3 === null || visit.countryCode3 === '' ? 'SIN DATOS' : visit.countryCode3 }}</q-item-label>
              <q-item-label caption>Provincia</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.country_code2 === null || visit.country_code2 === '' ? 'SIN DATOS' : visit.country_code2 }}</q-item-label>
              <q-item-label caption>Country Code2</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.country_code3 === null || visit.country_code3 === '' ? 'SIN DATOS' : visit.country_code3 }}</q-item-label>
              <q-item-label caption>Country Code3</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.country_name === null || visit.country_name === '' ? 'SIN DATOS' : visit.country_name }}</q-item-label>
              <q-item-label caption>Country Name</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.country_capital === null || visit.country_capital === '' ? 'SIN DATOS' : visit.country_capital }}</q-item-label>
              <q-item-label caption>Country Capital</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.state_prov === null || visit.state_prov === '' ? 'SIN DATOS' : visit.state_prov }}</q-item-label>
              <q-item-label caption>State Prov</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.district === null || visit.district === '' ? 'SIN DATOS' : visit.district }}</q-item-label>
              <q-item-label caption>District</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.city === null || visit.city === '' ? 'SIN DATOS' : visit.city }}</q-item-label>
              <q-item-label caption>City</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.zipcode === null || visit.zipcode === '' ? 'SIN DATOS' : visit.zipcode }}</q-item-label>
              <q-item-label caption>Zipcode</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.latitude === null || visit.latitude === '' ? 'SIN DATOS' : visit.latitude }}</q-item-label>
              <q-item-label caption>Latitude</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.longitude === null || visit.longitude === '' ? 'SIN DATOS' : visit.longitude }}</q-item-label>
              <q-item-label caption>Latitude</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.is_eu === null || visit.is_eu === '' ? 'SIN DATOS' : visit.is_eu }}</q-item-label>
              <q-item-label caption>Is Eu</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.calling_code === null || visit.calling_code === '' ? 'SIN DATOS' : visit.calling_code }}</q-item-label>
              <q-item-label caption>Calling Code</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.country_tld === null || visit.country_tld === '' ? 'SIN DATOS' : visit.country_tld }}</q-item-label>
              <q-item-label caption>Country Tld</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.languages === null || visit.languages === '' ? 'SIN DATOS' : visit.languages }}</q-item-label>
              <q-item-label caption>Languages</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.country_flag === null || visit.country_flag === '' ? 'SIN DATOS' : visit.country_flag }}</q-item-label>
              <q-item-label caption>Country Flag</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.isp === null || visit.isp === '' ? 'SIN DATOS' : visit.isp }}</q-item-label>
              <q-item-label caption>ISP</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.connection_type === null || visit.connection_type === '' ? 'SIN DATOS' : visit.connection_type }}</q-item-label>
              <q-item-label caption>Connection Type</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.organization === null || visit.organization === '' ? 'SIN DATOS' : visit.organization }}</q-item-label>
              <q-item-label caption>Organization</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.asn === null || visit.asn === '' ? 'SIN DATOS' : visit.asn }}</q-item-label>
              <q-item-label caption>ASN</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.geoname_id === null || visit.geoname_id === '' ? 'SIN DATOS' : visit.geoname_id }}</q-item-label>
              <q-item-label caption>Geoname Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="visit.created !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ visit.created === null ? 'SIN DATOS' : visit.created }}</q-item-label>
              <q-item-label caption>Creado</q-item-label>
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
import { VisitModel } from 'src/models/visit_model'
import { visitService } from 'src/services/visit_service'

const pagination = {
  rowsPerPage: 10,
  sortBy: 'created',
  descending: true
}

const columns = [
  {
    name: 'ip',
    label: 'Direccion Ip',
    field: 'ip',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'city',
    label: 'Ciudad',
    field: 'city',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'isp',
    label: 'Proveedor',
    field: 'isp',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'created',
    label: 'Ingreso',
    field: 'created',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'countryFlag',
    label: 'País',
    field: '',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'actions',
    label: 'Ver visita',
    field: '',
    align: 'center'
  }
]

export default {
  setup () {
    const loading = ref(false)
    const seeDialog = ref(false)
    const visit = reactive(new VisitModel())
    const visits = ref([])
    const visitsCount = ref()
    const visitIp = ref()

    afGetTop100()
    afGetCount()

    function fShowSeeDialog (props) {
      Object.assign(visit, props.row)
      seeDialog.value = true
    }

    async function afGetTop100 () {
      loading.value = true
      try {
        const result = await visitService.spfFetchTop100()
        if (result.status === 200) {
          visits.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener las las ultimas 50 visitas. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
    }

    async function afGetByIp () {
      loading.value = true
      try {
        const result = await visitService.spfFetchAllByIp(visitIp.value)
        if (result.status === 200) {
          visits.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener visitas por ip. ' + err)
      }
      loading.value = false
    }

    async function afGetCount () {
      const result = await visitService.spfFetchCountAll()
      visitsCount.value = result.data
    }

    return {
      afGetByIp,
      fShowSeeDialog,

      loading,
      columns,
      seeDialog,
      pagination,
      visit,
      visitIp,
      visits,
      visitsCount,

      filter: ref(''),
      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(visits.value[0]).join(';'),
          ...visits.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'visitas.csv')
        link.click()
      }
    }
  }
}
</script>
