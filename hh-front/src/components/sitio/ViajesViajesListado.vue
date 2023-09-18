<template>
  <q-card class="q-ma-md font-5">
    <div class="row justify-around q-pa-sm">
      <div class="col-4">
        <q-card class="cp12-c5">
          <q-card-actions align="center" class="cp12-c5 cp12-bc1">
            <span class="text-h6">Viajes</span>
          </q-card-actions>

          <q-card-actions align="center" class="cp12-c1 cp12-bc4">
            <q-icon name="waving_hand" size="md" />
            <span class="text-h4 q-ml-md">{{ viajesCuenta }}</span>
          </q-card-actions>
        </q-card>
      </div>
    </div>
    <div class="row justify-around q-pb-xl">
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorDestino" v-model="viajeDestino" label="Buscar por destino" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="sms" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afBuscarPorDestino" />
          </template>
        </q-input>
      </div>
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorChofer" v-model="viajeChofer" label="Buscar por chofer" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="qr_code" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afBuscarPorChofer" />
          </template>
        </q-input>
      </div>
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afBuscarPorCamion" v-model="viajeCamion" label="Buscar por camion" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="smartphone" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afBuscarPorCamion" />
          </template>
        </q-input>
      </div>

    </div>
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        :showing="!cargando"
        bordered
        title="viajes"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        :filter="filter"
        hide-no-data
        :rows="viajes"
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
            <span v-if="props.row.from === 'viaje:+5493434565568'">
              Sistema
            </span>
            <span v-if="props.row.from !== 'viaje:+5493434565568'">
              {{ props.row.from.slice(9, 30) }}
            </span>
          </q-td>
        </template>
        <template v-slot:body-cell-to="props">
          <q-td :props="props">
            <span v-if="props.row.to === 'viaje:+5493434565568'">
              Sistema
            </span>
            <span v-if="props.row.to !== 'viaje:+5493434565568'">
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
      <q-inner-loading :showing="cargando">
        <q-spinner-puff class="cp12-c3 text-center" size="12em" />
      </q-inner-loading>
    </div>
  </div>

  <q-dialog v-model="seeDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Ver viaje</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>

      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, reactive } from 'vue'
import { notificarService } from 'src/helpers/notificar_service'
import { ViajeModel } from 'src/models/viaje_model'
import { viajeService } from 'src/services/viaje_service'

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
  }
]

export default {
  setup () {
    const cargando = ref(false)
    const seeDialog = ref(false)
    const viaje = reactive(new ViajeModel())
    const viajes = ref([])
    const viajesCuenta = ref()
    const viajeDestino = ref()
    const viajeChofer = ref()
    const viajeCamion = ref()

    afObtener100()
    afObtenerCuenta()

    function fShowSeeDialog (props) {
      Object.assign(viaje, props.row)
      seeDialog.value = true
    }

    async function afBuscarPorDestino () {
    //   cargando.value = true
    //   try {
    //     const result = await viajeService.spfFetchAllByBody(viajeDestino.value)
    //     if (result.status === 200) {
    //       viajes.value = [...result.data]
    //       console.info(result.headers.message)
    //       notificarService.notifySuccess(result.headers.message)
    //     } else if (result.status === 202) {
    //       console.warn(result.headers.message)
    //       notificarService.notifyWarning(result.headers.message)
    //     } else if (result.status === 204) {
    //       console.error(result.headers.message)
    //       notificarService.notifyError(result.headers.message)
    //     }
    //   } catch (err) {
    //     notificarService.infoError('Ocurrio un error al intentar obtener viajes por mensaje. ' + err)
    //   }
    //   cargando.value = false
    }

    async function afBuscarPorChofer () {
    //   cargando.value = true
    //   try {
    //     const result = await viajeService.spfFetchAllBySid(viajeChofer.value)
    //     if (result.status === 200) {
    //       viajes.value = [...result.data]
    //       console.info(result.headers.message)
    //       notificarService.notifySuccess(result.headers.message)
    //     } else if (result.status === 202) {
    //       console.warn(result.headers.message)
    //       notificarService.notifyWarning(result.headers.message)
    //     } else if (result.status === 204) {
    //       console.error(result.headers.message)
    //       notificarService.notifyError(result.headers.message)
    //     }
    //   } catch (err) {
    //     notificarService.infoError('Ocurrio un error al intentar obtener viajes por SID. ' + err)
    //   }
    //   cargando.value = false
    }

    async function afBuscarPorCamion () {
      // cargando.value = true
      // try {
      //   const result = await viajeService.spfFetchAllByTo(viajeCamion.value)
      //   if (result.status === 200) {
      //     viajes.value = [...result.data]
      //     console.info(result.headers.message)
      //     notificarService.notifySuccess(result.headers.message)
      //   } else if (result.status === 202) {
      //     console.warn(result.headers.message)
      //     notificarService.notifyWarning(result.headers.message)
      //   } else if (result.status === 204) {
      //     console.error(result.headers.message)
      //     notificarService.notifyError(result.headers.message)
      //   }
      // } catch (err) {
      //   notificarService.infoError('Ocurrio un error al intentar obtener viajes por celular receptor. ' + err)
      // }
      // cargando.value = false
    }

    async function afObtenerCuenta () {
      const result = await viajeService.spfContarTodas()
      viajesCuenta.value = result.data
    }

    async function afObtener100 () {
      cargando.value = true
      try {
        const paginadoDTO = {
          direccion: 'DESC',
          campo: 'creada',
          pagina: '0',
          elementos: '10'
        }
        const result = await viajeService.spfBuscarTodasPaginadas(paginadoDTO)
        if (result.status === 200) {
          viajes.value = result.data.content
        }
      } catch (err) {
        console.clear()
        if (err.response.headers.mensaje) {
          console.error('Error: ' + err.response.headers.mensaje)
        } else {
          console.error('Hubo un error al intentar ingresar.')
        }
        notificarService.notificarError('Hubo un error al comprobar las credenciales.')
      }
      cargando.value = false
    }

    return {
      afBuscarPorDestino,
      afBuscarPorChofer,
      afBuscarPorCamion,
      fShowSeeDialog,

      cargando,
      columns,
      seeDialog,
      pagination,
      viaje,
      viajes,
      viajeDestino,
      viajeChofer,
      viajeCamion,
      viajesCuenta,

      filter: ref(''),
      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(viajes.value[0]).join(';'),
          ...viajes.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'viajes.csv')
        link.click()
      }
    }
  }
}
</script>
