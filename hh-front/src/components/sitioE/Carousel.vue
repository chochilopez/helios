<template>
  <div class="q-pa-md">
    <q-table
      :showing="!loading"
      bordered
      title="Carousel de imagenes"
      :columns="columnsCarousels"
      rows-per-page-label="Registros por pagina"
      no-data-label="Sin datos para mostrar"
      :pagination="pagination"
      :filter="filter"
      hide-no-data
      :rows="carousels"
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
          label="Seleccionar imagen"
          no-caps
          @click="afAddCarousel"
        />
      </template>
      <template v-slot:body-cell-actions="props">
        <q-td :props="props">
          <q-btn @click="fDelete(props)" dense round flat class="cp10-c5" icon="delete">
            <q-tooltip class="cp12-bc2" anchor="top middle" self="bottom middle" :offset="[3, 3]">
              Eliminar
            </q-tooltip>
          </q-btn>
        </q-td>
      </template>
      <template v-slot:body-cell-imagen="props">
        <q-td :props="props">
          <q-img
            :src="fGetAsset(props.row.localFile.path)"
            :alt="props.row.localFile.description"
            fit="cover"
            @click="fSeeImage(props.row.localFile.path)"
          />
        </q-td>
      </template>
    </q-table>
    <q-inner-loading :showing="loading">
      <q-spinner-puff class="cp12-c3" size="6em" />
    </q-inner-loading>
  </div>
  <!-- <InicioCarousel tamanio="300px" :carousels="carousels" /> -->

  <q-dialog v-model="selectCarouselDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Seleccionar imagen para agregar a Carousel</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc1 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <div class="row justify-center q-ma-md">
          <q-table
            :showing="!loading"
            bordered
            title="Galería imagenes"
            :columns="columnsImages"
            rows-per-page-label="Registros por pagina"
            no-data-label="Sin datos para mostrar"
            :pagination="pagination"
            :filter="filter"
            hide-no-data
            :rows="images"
            row-key="name"
          >
            <template v-slot:top-right>
              <q-input outlined dense debounce="300" v-model="filter" placeholder="Buscar">
                <template v-slot:append>
                  <q-icon name="search" />
                </template>
              </q-input>
            </template>
            <template v-slot:body-cell-actions="props">
              <q-td :props="props">
                <q-btn @click="afSave(props)" dense round flat class="cp12-c3" icon="mdi-clipboard-plus"></q-btn>
              </q-td>
            </template>
            <template v-slot:body-cell-imagen="props">
              <q-td :props="props">
                <q-img
                  :src="fGetAsset(props.row.path)"
                  :alt="props.row.descripcion"
                  fit="cover"
                  @click="fSeeImage(props.row.path)"
                />
              </q-td>
            </template>
          </q-table>
          <q-inner-loading :showing="loading">
            <q-spinner-puff class="cp12-c3 text-center" size="12em" />
          </q-inner-loading>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="removeCarouselDialog" transition-show="scale" transition-hide="scale">
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <div>Quitar imagen del Carousel</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc1 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <p class="cp12-c1 text-h6">¿Desea quitar la imagén del Carousel?</p>
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

  <q-dialog v-model="seeImageDialog" transition-show="scale" transition-hide="scale" full-width>
    <q-card class="cp12-c5">
      <q-bar class="cp12-bc3">
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="cp12-bc1 cp12-c5">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pa-md">
        <q-img
          :src="fGetAsset(seeImage.path)"
        />
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notificar_service'
import { CarouselModel } from 'src/models/carousel_model'
import { helper } from 'src/helpers/helper'
import { carouselService } from 'src/services/carousel_service'
import { localFileService } from 'src/services/local_file_service'
import { fileTypeEnum } from 'src/enums/file_type_enum'

const columnsCarousels = [
  { name: 'id', label: 'Id', align: 'left', field: 'id', sortOrder: 'da', sortable: true },
  { name: 'imagen', label: 'Imagen', field: '', align: 'center' },
  { name: 'actions', label: 'Quitar', field: '', align: 'center' }
]

const columnsImages = [
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
    name: 'filesize',
    label: 'Tamaño',
    align: 'left',
    field: 'filesize',
    sortable: true
  },
  {
    name: 'imagen',
    label: 'Imagen',
    field: '',
    align: 'center'
  },
  {
    name: 'actions',
    label: 'Agregar',
    field: '',
    align: 'center'
  }
]

export default {
  // components: { InicioCarousel },
  setup () {
    const loading = ref(true)
    const seeImageDialog = ref(false)
    const removeCarouselDialog = ref(false)
    const selectCarouselDialog = ref(false)
    const carousels = ref([])
    const images = ref([])
    const pagination = {
      rowsPerPage: 10,
      sortBy: 'id',
      descending: true
    }
    const carousel = reactive(new CarouselModel())
    const seeImage = reactive({
      path: ''
    })

    afGetAll()

    async function afAddCarousel () {
      try {
        await afGetAllImages()
        selectCarouselDialog.value = true
      } catch (err) {}
    }

    async function afDelete () {
      loading.value = true
      try {
        const result = await carouselService.spfDelete(carousel.id)
        if (result.status === 200) {
          carouselService.spfFetchAll().then(result => {
            carousels.value = [...result.data]
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
        const error = 'Hubo un error al eliminar el carousel. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      loading.value = false
      fHide()
    }

    async function afGetAll () {
      loading.value = true
      try {
        const result = await carouselService.spfFetchAll()
        if (result.status === 200) {
          carousels.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener los carousels. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      fHide()
      loading.value = false
    }

    async function afGetAllImages () {
      loading.value = true
      try {
        const result = await localFileService.spfFetchAllByFileType(fileTypeEnum.FILE_TYPE_IMAGE)
        if (result.status === 200) {
          images.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener las imagenes. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      fHide()
      loading.value = false
    }

    async function afSave (props) {
      loading.value = true
      try {
        selectCarouselDialog.value = false
        const objeto = { multimediaId: props.row.id }
        const result = await carouselService.spfInsert(objeto)
        if (result.status === 201) {
          carouselService.spfFetchAllCarousels().then(result => {
            carousels.value = [...result.data]
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
        const error = 'Hubo un error al obtener las imagenes. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      fHide()
      loading.value = false
    }

    function fGetAsset (pathArchivo) {
      return helper.getAsset(pathArchivo)
    }

    function fSeeImage (path) {
      seeImage.path = path
      seeImageDialog.value = true
    }

    function fDelete (props) {
      Object.assign(carousel, props.row)
      removeCarouselDialog.value = true
    }

    function fHide () {
      removeCarouselDialog.value = false
      seeImageDialog.value = false
    }

    return {
      afAddCarousel,
      afDelete,
      afSave,
      fDelete,
      fGetAsset,
      fSeeImage,

      carousel,
      carousels,
      images,
      seeImage,

      removeCarouselDialog,
      selectCarouselDialog,
      seeImageDialog,

      loading,
      columnsCarousels,
      columnsImages,
      pagination,

      filter: ref('')
    }
  }
}
</script>

<style scoped>
.q-img {cursor: pointer;}
</style>
