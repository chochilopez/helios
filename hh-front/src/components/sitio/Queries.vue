<template>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        :showing="!loading"
        bordered
        title="Consultas"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        :filter="filter"
        hide-no-data
        :rows="consultas"
        row-key="name"
      >
        <template v-slot:top-right>
          <q-input outlined dense debounce="300" v-model="filter" placeholder="Buscar">
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
          <q-btn
            class="q-ml-md bg-c-2 text-white"
            icon-right="archive"
            label="Exportar"
            no-caps
            @click="exportTable" />
        </template>
        <template v-slot:body-cell-creado="props">
          <q-td :props="props">
            {{ fFormatoFecha(props.row.creado) }}
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="loading">
        <q-spinner-puff class="c-3" size="6em" />
      </q-inner-loading>
    </div>
  </div>

</template>

<script>
import { ref } from 'vue'
import { notifyService } from 'src/helpers/notify_service'
import { helper } from 'app/src/helpers/helper'
import { queryService } from 'src/services/query_service'

const paginacion = {
  rowsPerPage: 10,
  sortBy: 'creado',
  descending: true
}

const columnas = [
  {
    name: 'id',
    label: 'ID',
    field: 'id',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'ip',
    label: 'Direccion Ip',
    field: 'ip',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'ciudad',
    label: 'Ciudad',
    field: 'ciudad',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'tipoPac',
    label: 'Tipo Pac',
    field: 'tipoPac',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'idRecepcion',
    label: 'Id Recepcion',
    field: 'idRecepcion',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'dni',
    label: 'Nº Documento',
    field: 'dni',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'creado',
    label: 'Ingreso',
    field: 'creado',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  }
]

export default {
  setup () {
    const loading = ref(false)
    const consultas = ref([])

    // afGetAllQueries()
    afGetTop50Queries()

    function fFormatoFecha (fecha) {
      return helper.getDateWithFormat(fecha)
    }

    // async function afGetAllQueries () {
    //   loading.value = true
    //   try {
    //     const result = await queryService.spfFetchAllQueries()
    //     if (result.status === 200) {
    //       consultas.value = result.data
    //       console.info(result.headers.message)
    //     } else if (result.status === 202) {
    //       console.warn(result.headers.message)
    //       notifyService.notifyWarning(result.headers.message)
    //     } else if (result.status === 204) {
    //       console.error(result.headers.message)
    //       notifyService.notifyError(result.headers.message)
    //     }
    //   } catch (err) {
    //     const error = 'Hubo un error al obtener las consultas. ' + err
    //     notifyService.notifyError(error)
    //     console.error(error)
    //   }
    //   loading.value = false
    // }

    async function afGetTop50Queries () {
      loading.value = true
      try {
        const result = await queryService.spfFetchTop50Queries()
        if (result.status === 200) {
          consultas.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener 50 Consultas. ' + err)
      }
      loading.value = false
    }

    return {
      fFormatoFecha,

      loading,
      columnas,
      paginacion,
      consultas,

      filter: ref(''),
      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(consultas.value[0]).join(';'),
          ...consultas.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'consultas.csv')
        link.click()
      }
    }
  }
}
</script>
