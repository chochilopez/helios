<template>
  <q-card class="q-ma-md font-5">
    <div class="row justify-around q-pa-sm">
      <div class="col-4">
        <q-card class="cp12-c5">
          <q-card-actions align="center" class="cp12-c5 cp12-bc1">
            <span class="text-h6">Usuarios</span>
          </q-card-actions>

          <q-card-actions align="center" class="cp12-c1 cp12-bc4">
            <q-icon name="mdi-account-key" size="md" />
            <span class="text-h4 q-ml-md">{{ usersCount }}</span>
          </q-card-actions>
        </q-card>
      </div>
    </div>
    <!-- <div class="row justify-around q-pb-xl">
      <div class="col-12">
        <q-input outlined v-on:keypress.enter="afGetByUsername" v-model="userName" label="Buscar por Nombre / Apellido" counter maxlength="24" dense class="q-pa-md">
          <template v-slot:before>
            <q-icon name="mdi-format-letter-case" />
          </template>
          <template v-slot:append>
            <q-icon name="search" class="hand" @click="afGetByUsername" />
          </template>
        </q-input>
      </div>

    </div> -->
  </q-card>
  <div class="row q-pa-md">
    <div class="col">
      <q-table
        :showing="!loading"
        bordered
        title="Usuarios"
        :columns="columns"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="pagination"
        :filter="filter"
        hide-no-data
        :rows="users"
        row-key="name"
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
        <template v-slot:body-cell-authorities="props">
          <q-td :props="props">
            <span v-for="authority in props.row.authorities" :key="authority.id">
              <q-chip v-if="authority == 'AUTHORITY_GUEST'" color="blue" text-color="white">
                Invitado
              </q-chip>
              <q-chip v-if="authority == 'AUTHORITY_EDITOR'" color="teal" text-color="white">
                Editor
              </q-chip>
              <q-chip v-if="authority == 'AUTHORITY_ADMIN'" color="orange" text-color="white">
                Administrador
              </q-chip>
            </span>
          </q-td>
        </template>
        <template v-slot:body-cell-enabled="props">
          <q-td :props="props">
            <q-chip v-if="props.row.enabled == 'true'" color="green" text-color="white">
              Habilitado
            </q-chip>
            <q-chip v-if="props.row.enabled == 'false'" color="red" text-color="white">
              Deshabilitado
            </q-chip>
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
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notificar_service'
import { userService } from 'src/services/user_service'
import { UserModel } from 'src/models/user_model'

const pagination = {
  rowsPerPage: 10,
  sortBy: 'id',
  descending: true
}

const columns = [
  {
    name: 'id',
    label: 'Id Usuario',
    field: 'id',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'username',
    label: 'Username',
    field: 'username',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'authorities',
    label: 'Autoridades',
    field: '',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'enabled',
    label: 'Habilitado',
    field: '',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  }
]

export default {
  setup () {
    const loading = ref(false)
    const usersCount = ref(0)
    const filter = ref('')
    const user = reactive(new UserModel())
    const users = ref([])
    const userName = ref('')

    afCount()
    afGetAll()

    async function afGetByUsername () {
      loading.value = true
      try {
        const result = await userService.spfFetchByUsername(userName.value)
        if (result.status === 200) {
          users.value = [...result.data]
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
        notifyService.infoError('Ocurrio un error al intentar obtener usuarios por nombre. ' + err)
      }
      loading.value = false
    }

    async function afCount () {
      const result = await userService.spfFetchCountAll()
      usersCount.value = result.data
    }

    async function afGetAll () {
      loading.value = true
      try {
        const result = await userService.spfFetchAll()
        if (result.status === 200) {
          users.value = [...result.data]
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        notifyService.infoError('Ocurrio un problema al mostrar los clientes. ' + err)
      }
      loading.value = false
    }

    return {
      afGetAll,
      afGetByUsername,

      loading,
      columns,
      usersCount,
      filter,
      user,
      userName,
      users,
      pagination,

      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(users.value[0]).join(';'),
          ...users.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'users.csv')
        link.click()
      }
    }
  }
}
</script>

<style scoped>
.hand {
  cursor: pointer;
}
</style>
