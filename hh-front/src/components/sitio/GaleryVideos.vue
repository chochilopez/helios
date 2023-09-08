<template>
  <div class="q-pa-md cp12-c1 text-center">
    <q-table
      :showing="!loading"
      bordered
      title="Audios"
      :columns="columns"
      rows-per-page-label="Registros por pagina"
      no-data-label="Sin datos para mostrar"
      :pagination="pagination"
      :filter="filter"
      hide-no-data
      :rows="localFiles"
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
          icon-right="add_box"
          label="Nuevo audio"
          no-caps
          @click="fShowDialogCrear"
        />
      </template>
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn @click="fShowSeeDialog(props)" dense round flat class="cp1-c4" icon="visibility">
            <q-tooltip class="cp12-bc2" anchor="top middle" self="bottom middle" :offset="[3, 3]">
              Información
            </q-tooltip>
          </q-btn>
          <q-btn @click="fShowEditDialog(props)" dense round flat class="cp6-c5" icon="edit">
            <q-tooltip class="cp12-bc2" anchor="top middle" self="bottom middle" :offset="[3, 3]">
              Editar
            </q-tooltip>
          </q-btn>
          <q-btn @click="fShowDeleteDialog(props)" dense round flat class="cp10-c5" icon="delete">
            <q-tooltip class="cp12-bc2" anchor="top middle" self="bottom middle" :offset="[3, 3]">
              Eliminar
            </q-tooltip>
          </q-btn>
        </q-td>
      </template>
      <template v-slot:body-cell-audio="props">
        <q-td :props="props">
          <q-media-player
            type="audio"
            content-class="bg-c-3"
            :source="fGetAsset(props.row.path)"
          />
        </q-td>
      </template>
    </q-table>
    <q-inner-loading :showing="loading" class="align-center">
      <q-spinner-puff class="cp12-c3 text-center" size="12em" />
    </q-inner-loading>
  </div>

  <q-dialog v-model="createDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5" style="width: 400px">
      <q-bar class="cp12-bc3">
        <div>Nuevo audio</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>
        <q-form  @submit="afUploadFile()">
          <div class="row q-pa-md">
            <div class="col-12">
              <q-input
                v-model="localFile.description"
                label="Descripción del audio"
                outlined
                counter
                maxlength="100"
                clearable
                :rules="[rules.required, rules.min8, rules.max50]"
              >
                <template v-slot:prepend>
                  <q-icon name="mdi-message-text" />
                </template>
              </q-input>
            </div>
          </div>
          <div class="row q-pa-md">
            <div class="col-12">
              <q-localFile
                outlined
                bottom-slots
                v-model="localFile.localFile"
                label="Buscá el audio en tu pc"
                accept="audio/*"
              >
                <template v-slot:prepend>
                  <q-icon name="attachment" />
                </template>

                <template v-slot:append>
                  <q-icon v-if="localFile.localFile !== null" name="close" @click.stop="localFile.localFile = null" class="cursor-pointer" />
                  <q-icon name="search" @click.stop />
                </template>
              </q-localFile>
            </div>
          </div>
          <div class="row justify-around">
            <q-btn type="submit" class="cp12-c5 cp12-bc2" icon="cancel" v-close-popup>
              <span class="q-pl-sm">
                Cancelar
              </span>
            </q-btn>
            <q-btn type="submit" class="cp12-c5 cp12-bc3" icon="save" :loading="loading" v-if="localFile.localFile">
              <template v-slot:loading>
                <q-spinner-facebook />
              </template>
              <span class="q-pl-sm">
                Subir audio
              </span>
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="editDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Editar audio</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <q-form  @submit="afSave">
          <div class="row q-pa-md">
            <div class="col-12 q-px-sm">
              <q-input
                v-model="localFile.description"
                label="Descripción del audio"
                outlined
                counter
                maxlength="100"
                clearable
                :rules="[rules.required, rules.min8, rules.max50]"
                class="q-pb-xl"
              >
                <template v-slot:before>
                  <q-icon name="mdi-message-text" />
                </template>
              </q-input>
            </div>
          </div>
          <div class="row justify-around">
            <q-btn v-close-popup class="cp12-c5 cp12-bc2">
              <q-icon name="cancel" class="q-mr-sm" />
              Descartar
            </q-btn>
            <q-btn type="submit" class="cp12-c5 cp12-bc3" :loading="loading">
              <template v-slot:loading>
                <q-spinner-facebook />
              </template>
              <q-icon name="save" class="q-mr-sm" />
              Guardar
            </q-btn>
          </div>
        </q-form>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="seeDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Ver audio</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section>
        <q-list dense bordered padding class="rounded-borders">

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.id }}</q-item-label>
              <q-item-label caption>Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.fileName === null || localFile.fileName === '' ? 'SIN DATOS' : localFile.fileName }}</q-item-label>
              <q-item-label caption>Nombre archivo</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.description === null || localFile.description === '' ? 'SIN DATOS' : localFile.description }}</q-item-label>
              <q-item-label caption>Descripción</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.filesize === null || localFile.filesize === '' ? 'SIN DATOS' : localFile.filesize }}</q-item-label>
              <q-item-label caption>Tamaño</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.created !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.created === null ? 'SIN DATOS' : localFile.created }}</q-item-label>
              <q-item-label caption>Creado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.creator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.creator === null ? 'SIN DATOS' : localFile.creator.username }}</q-item-label>
              <q-item-label caption>Creado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.modified !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.modified === null ? 'SIN DATOS' : localFile.modified }}</q-item-label>
              <q-item-label caption>Modificado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.modificator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.modificator === null ? 'SIN DATOS' : localFile.modificator.username }}</q-item-label>
              <q-item-label caption>Modificado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.elminated !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.elminated === null ? 'SIN DATOS' : localFile.elminated }}</q-item-label>
              <q-item-label caption>Eliminado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.eliminator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.eliminator === null ? 'SIN DATOS' : localFile.eliminator.username }}</q-item-label>
              <q-item-label caption>Eliminado por</q-item-label>
            </q-item-section>
          </q-item>

        </q-list>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="deleteDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Eliminar audio</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <q-list dense bordered padding class="rounded-borders">

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.id }}</q-item-label>
              <q-item-label caption>Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.fileName === null || localFile.fileName === '' ? 'SIN DATOS' : localFile.fileName }}</q-item-label>
              <q-item-label caption>Nombre archivo</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.description === null || localFile.description === '' ? 'SIN DATOS' : localFile.description }}</q-item-label>
              <q-item-label caption>Descripción</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.filesize === null || localFile.filesize === '' ? 'SIN DATOS' : localFile.filesize }}</q-item-label>
              <q-item-label caption>Tamaño</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.created !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.created === null ? 'SIN DATOS' : localFile.created }}</q-item-label>
              <q-item-label caption>Creado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.creator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.creator === null ? 'SIN DATOS' : localFile.creator.username }}</q-item-label>
              <q-item-label caption>Creado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.modified !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.modified === null ? 'SIN DATOS' : localFile.modified }}</q-item-label>
              <q-item-label caption>Modificado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.modificator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.modificator === null ? 'SIN DATOS' : localFile.modificator.username }}</q-item-label>
              <q-item-label caption>Modificado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.elminated !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.elminated === null ? 'SIN DATOS' : localFile.elminated }}</q-item-label>
              <q-item-label caption>Eliminado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="localFile.eliminator !== null">
            <q-item-section>
              <q-item-label class="cp12-c1">{{ localFile.eliminator === null ? 'SIN DATOS' : localFile.eliminator.username }}</q-item-label>
              <q-item-label caption>Eliminado por</q-item-label>
            </q-item-section>
          </q-item>

        </q-list>
        <div class="row justify-around">
          <q-btn v-close-popup class="cp12-c5 cp12-bc2 q-ma-md">
            <q-icon name="cancel" class="q-mr-sm"/>
            Cancelar
          </q-btn>
          <q-btn v-close-popup class="cp12-c5 cp12-bc3 q-ma-md" @click="afDelete" :loading="loading">
            <template v-slot:loading>
              <q-spinner-facebook />
            </template>
            <q-icon name="delete" class="q-mr-sm"/>
            BORRAR
          </q-btn>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="seeAudioDialog" transition-show="scale" transition-hide="scale" full-width>
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc2 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pa-md">
        <q-img
          :src="fGetAsset(localFile.location)"
        />
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notify_service'
import { helper } from 'app/src/helpers/helper'
import { validateRules } from 'src/helpers/validate_rules'
import { localFileService } from 'src/services/local_file_service'
import { LocalFileModel } from 'src/models/local_file_model'
import { fileTypeEnum } from 'src/enums/file_type_enum'

const columns = [
  {
    name: 'id',
    label: 'Id',
    align: 'center',
    field: 'id',
    sortOrder: 'da',
    sortable: true
  },
  {
    name: 'filename',
    label: 'Nombre Archivo',
    align: 'left',
    field: 'filename',
    sortable: true
  },
  {
    name: 'description',
    label: 'Descripción',
    align: 'left',
    field: 'description'
  },
  {
    name: 'filesize',
    label: 'Tamaño',
    align: 'left',
    field: 'filesize',
    sortable: true
  },
  {
    name: 'audio',
    label: 'Audio',
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
    const localFile = reactive(new LocalFileModel())
    const localFiles = ref([])
    const loading = ref(false)
    const createDialog = ref(false)
    const editDialog = ref(false)
    const deleteDialog = ref(false)
    const seeDialog = ref(false)
    const seeImageDialog = ref(false)
    const filter = ref('')
    const pagination = {
      rowsPerPage: 10,
      sortBy: 'id',
      descending: true
    }
    const rules = reactive(validateRules.rules)

    afGetAll()

    async function afDelete () {
      loading.value = true
      try {
        const result = await localFileService.spfDelete(localFile.id)
        if (result.status === 200) {
          localFileService.spfFetchAllByFileType(fileTypeEnum.FILE_TYPE_AUDIO).then(result => {
            localFiles.value = [...result.data]
          })
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
        const error = 'Hubo un error al eliminar el archivo multimedia. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
      fCleanAndHide()
    }

    async function afGetAll () {
      loading.value = true
      try {
        const result = await localFileService.spfFetchAllByFileType(fileTypeEnum.FILE_TYPE_AUDIO)
        if (result.status === 200) {
          localFiles.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener los archivos multimedia. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
      fCleanAndHide()
    }

    async function afSave () {
      loading.value = true
      try {
        const result = await localFileService.spfSave(localFile)
        if (result.status === 200) {
          localFileService.spfFetchAllByFileType(fileTypeEnum.FILE_TYPE_AUDIO).then(result => {
            localFiles.value = [...result.data]
          })
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
        const error = 'Hubo un error al persistir el archivo multimedia. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
      fCleanAndHide()
    }

    async function afUploadFile () {
      loading.value = true
      try {
        if (localFile.localFile !== null) {
          const formData = new FormData()
          formData.append('localFile', localFile.localFile)
          localFile.filesize = helper.getSize(localFile.localFile.size)
          formData.append('description', localFile.description)
          formData.append('size', localFile.filesize)
          const result = await localFileService.spfPersistImage(formData)
          if (result.status === 201) {
            localFileService.spfFetchAllByFileType(fileTypeEnum.FILE_TYPE_AUDIO).then(result => {
              localFiles.value = [...result.data]
            })
            console.info(result.headers.message)
            notifyService.notifySuccess(result.headers.message)
          } else if (result.status === 202) {
            console.warn(result.headers.message)
            notifyService.notifyWarning(result.headers.message)
          } else if (result.status === 204) {
            console.error(result.headers.message)
            notifyService.notifyError(result.headers.message)
          }
        }
      } catch (err) {
        const error = 'Hubo un error al intentar persistir el localFile multimedia. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
      fCleanAndHide()
    }

    function fCleanAndHide () {
      fHideDialogs()
      fCleanFields()
    }

    function fCleanFields () {
      localFile.id = null
      localFile.localFile = null
      localFile.location = null
      localFile.filename = null
      localFile.description = null
      localFile.filesize = null
      localFile.created = null
      localFile.creator = null
      localFile.modified = null
      localFile.modificator = null
      localFile.elminated = null
      localFile.eliminator = null
    }

    function fGetAsset (pathFile) {
      return helper.getAsset(pathFile)
    }

    function fHideDialogs () {
      seeDialog.value = false
      createDialog.value = false
      editDialog.value = false
      deleteDialog.value = false
    }

    function fShowDialogCrear () {
      fCleanAndHide()
      createDialog.value = true
    }

    function fShowEditDialog (props) {
      Object.assign(localFile, props.row)
      editDialog.value = true
    }

    function fShowDeleteDialog (props) {
      Object.assign(localFile, props.row)
      deleteDialog.value = true
    }

    function fShowSeeDialog (props) {
      Object.assign(localFile, props.row)
      seeDialog.value = true
    }

    function fShowDialogVerAudio (props) {
      Object.assign(localFile, props.row)
      seeImageDialog.value = true
    }

    return {
      afDelete,
      afSave,
      afUploadFile,
      fCleanAndHide,
      fGetAsset,
      fShowDialogCrear,
      fShowEditDialog,
      fShowDeleteDialog,
      fShowSeeDialog,
      fShowDialogVerAudio,

      localFile,
      localFiles,
      loading,
      columns,
      createDialog,
      editDialog,
      deleteDialog,
      seeDialog,
      seeImageDialog,
      filter,
      pagination,
      rules
    }
  }
}
</script>

<style scoped>
.q-img {cursor: pointer;}
</style>
