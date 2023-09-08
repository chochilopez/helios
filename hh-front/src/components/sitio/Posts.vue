<template>
  <div class="row q-pa-md">
    <div class="col-12">
      <q-table
        :showing="!cargando"
        bordered
        title="Publicaciones"
        :columns="columnas"
        rows-per-page-label="Registros por pagina"
        no-data-label="Sin datos para mostrar"
        :pagination="paginacion"
        :filter="filter"
        hide-no-data
        :rows="publicaciones"
        row-key="name"
      >
        <template v-slot:top-right>
          <q-input outlined dense debounce="300" v-model="filter" placeholder="Buscar">
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
          <q-btn
            class="q-ml-md bg-c-1"
            icon-right="add_box"
            label="Nueva publicacion"
            no-caps
            :to="{ name: 'NewPost' }" />
          <q-btn
            class="q-ml-md bg-c-2 text-white"
            icon-right="archive"
            label="Exportar"
            no-caps
            @click="exportTable" />
        </template>
        <template v-slot:body-cell-actions="props">
          <q-td :props="props">
            <q-btn @click="afPublishArticle(props)" dense round flat class="text-c-14" icon="mdi-arrow-up-circle" v-if="props.row.publicado == null">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Publicar
              </q-tooltip>
            </q-btn>
            <q-btn @click="afUnpublishArticle(props)" dense round flat class="text-c-13" icon="mdi-arrow-down-circle" v-if="props.row.publicado != null">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Despublicar
              </q-tooltip>
            </q-btn>
            <q-btn @click="fSeeArticlePreview(props)" dense round flat class="text-c-12" icon="visibility">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Ver
              </q-tooltip>
            </q-btn>
            <q-btn @click="fShowDialogEdit(props)" dense round flat class="text-c-11" icon="edit">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Editar
              </q-tooltip>
            </q-btn>
            <q-btn @click="fRemoveArticle(props)" dense round flat class="text-c-10" icon="delete">
              <q-tooltip anchor="top middle" self="bottom middle" :offset="[3, 3]">
                Eliminar
              </q-tooltip>
            </q-btn>
          </q-td>
        </template>
        <template v-slot:body-cell-categoria="props">
          <q-td :props="props">
            {{ props.row.categoria }}
          </q-td>
        </template>
        <template v-slot:body-cell-autor="props">
          <q-td :props="props">
            {{ props.row.autor }}
          </q-td>
        </template>
        <template v-slot:body-cell-estado="props">
          <q-td :props="props">
            <q-chip square class="bg-positive text-white" v-if="props.row.publicado != null && props.row.eliminado == null">
              Publicada
            </q-chip>
            <q-chip square class="bg-warning text-white" v-else-if="props.row.publicado == null && props.row.eliminado == null">
              Borrador
            </q-chip>
            <q-chip square class="bg-negative text-white" v-else-if="props.row.eliminado != null">
              Eliminada
            </q-chip>
          </q-td>
        </template>
      </q-table>
      <q-inner-loading :showing="cargando">
        <q-spinner-puff class="text-c-3" size="6em" />
      </q-inner-loading>
    </div>
  </div>

  <q-dialog v-model="dialogoVistaPrevia" transition-show="scale" transition-hide="scale" full-width>
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Vista previa de la publicacion</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none text-black">
        <div class="q-pa-md">
          <div class="row">
            <div class="col">
              <p class="text-h3">{{ publicacion.titulo }}</p>
              <p class="q-mt-sm">Por {{ publicacion.autor }}</p>
              <p>{{ publicacion.copete }}</p>
            </div>
          </div>
          <div class="row">
            <div class="col">
              <q-img
                :src="fGetFile(publicacion.imagenPortada.path)"
                :alt="publicacion.imagenPortada.descripcion"
                fit="contain"
                height="580px"
                @click="fSeeImage(publicacion.imagenPortada)"
              />
            </div>
          </div>
          <div class="row">
            <div class="col">
              <p style="font-family: Roboto; font-size: 16px" class="q-pa-xl" v-html="publicacion.cuerpo"></p>
            </div>
          </div>
          <div class="row" v-if="publicacion.audios.length > 0">
            <div class="col">
              <q-bar class="bg-c-1 q-mt-xl" style="height: 5px;"/>
              <p class="text-h6">Galeria de audios</p>
              <q-carousel v-model="slidea" animated swipeable arrows height="100px" navigation control-color="blue-12" control-type="regular">
                <q-carousel-slide :name="index" v-for="(audio, index) in publicacion.audios" :key="index">
                  <q-media-player
                    type="audio"
                    content-class="bg-cinco-cv"
                    :source="fGetFile(audio.path)"
                  />
                </q-carousel-slide>
              </q-carousel>
              <q-bar class="bg-c-1" style="height: 5px;"/>
            </div>
          </div>
          <div class="row" v-if="publicacion.imagenes.length > 0">
            <div class="col">
              <q-bar class="bg-c-2 q-mt-xl" style="height: 5px;"/>
              <p class="text-h6">Galeria de imágenes</p>
              <q-carousel v-model="slidei" animated swipeable arrows height="600px" navigation control-color="blue-12" control-type="regular">
                <q-carousel-slide :name="index" v-for="(imagen, index) in publicacion.imagenes" :key="index">
                  <q-img
                    :src="fGetFile(imagen.path)"
                    :alt="publicacion.titulo"
                    fit="contain"
                    height="580px"
                    @click="fSeeImage(imagen)"
                  />
                </q-carousel-slide>
              </q-carousel>
              <q-bar class="bg-c-2" style="height: 5px;"/>
            </div>
          </div>
          <div class="row" v-if="publicacion.videos.length > 0">
            <div class="col">
              <q-bar class="bg-c-3 q-mt-xl" style="height: 5px;"/>
              <p class="text-h6">Galeria de videos</p>
              <q-carousel v-model="slidev" animated swipeable arrows height="600px" navigation control-color="blue-12" control-type="regular">
                <q-carousel-slide :name="index" v-for="(video, index) in publicacion.videos" :key="index">
                  <q-media-player
                    type="video"
                    content-class="bg-cinco-cv"
                    :source="fGetFile(video.path)"
                  />
                </q-carousel-slide>
              </q-carousel>
              <q-bar class="bg-c-3" style="height: 5px;"/>
            </div>
          </div>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoEliminar" persistent transition-show="scale" transition-hide="scale">
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Eliminar publicacion</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none text-black">
        <q-list dense bordered padding class="rounded-borders">

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.id }}</q-item-label>
              <q-item-label caption>Id</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.titulo === null ? 'Sin datos' : publicacion.titulo }}</q-item-label>
              <q-item-label caption>Titulo</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.copete === null ? 'Sin datos' : publicacion.copete }}</q-item-label>
              <q-item-label caption>Copete</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.cuerpo === null ? 'Sin datos' : publicacion.cuerpo }}</q-item-label>
              <q-item-label caption>Cuerpo</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.comentarios === null ? 'Sin datos' : publicacion.comentarios }}</q-item-label>
              <q-item-label caption>Comentarios</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.vistas === null ? 'Sin datos' : publicacion.vistas }}</q-item-label>
              <q-item-label caption>Vistas</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.categoria !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.categoria === null ? 'Sin datos' : publicacion.categoria }}</q-item-label>
              <q-item-label caption>Categoria</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.autor !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.autor === null ? 'Sin datos' : publicacion.autor }}</q-item-label>
              <q-item-label caption>Categoria</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.publicado !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.publicado === null ? 'Sin datos' : publicacion.publicado }}</q-item-label>
              <q-item-label caption>Publicado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.publicador !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.publicador === null ? 'Sin datos' : publicacion.publicador.username }}</q-item-label>
              <q-item-label caption>Publicado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.creado !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.creado === null ? 'Sin datos' : publicacion.creado }}</q-item-label>
              <q-item-label caption>Creado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.creador !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.creador === null ? 'Sin datos' : publicacion.creador.username }}</q-item-label>
              <q-item-label caption>Creado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.modificado !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.modificado === null ? 'Sin datos' : publicacion.modificado }}</q-item-label>
              <q-item-label caption>Modificado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.modificador !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.modificador === null ? 'Sin datos' : publicacion.modificador.username }}</q-item-label>
              <q-item-label caption>Modificado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.eliminado !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.eliminado === null ? 'Sin datos' : publicacion.eliminado }}</q-item-label>
              <q-item-label caption>Eliminado</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.eliminador !== null">
            <q-item-section>
              <q-item-label class="text-black">{{ publicacion.eliminador === null ? 'Sin datos' : publicacion.eliminador.username }}</q-item-label>
              <q-item-label caption>Eliminado por</q-item-label>
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple class="q-ma-md" v-if="publicacion.imagenPortada !== null">
            <q-img
              :src="fGetFile(publicacion.imagenPortada.path)"
            />
          </q-item>

        </q-list>
        <div class="row justify-around">
          <q-btn v-close-popup class="text-white bg-c-8 q-ma-md">
            <q-icon name="cancel" class="q-mr-sm"/>
            Cancelar
          </q-btn>
          <q-btn v-close-popup class="text-white bg-c-1 q-ma-md" @click="afDeleteArticle" :loading="cargando">
            <template v-slot:loading>
              <q-spinner-facebook />
            </template>
            <q-icon name="mdi-delete" class="q-mr-sm" />
            BORRAR
          </q-btn>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoEditar" persistent transition-show="scale" transition-hide="scale">
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Editar publicacion id {{ archivo.id }}</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none text-black">
        <div class="row justify-around">
          <p class="text-subtitle1 text-center">¿Desea editar la publicación con título: {{ archivo.titulo }}.</p>
          <p class="text-subtitle2 text-center">Al editar una publicacion esta se despublicara instantaneamente. ¿Desea continuar?</p>
        </div>
        <div class="row justify-around">
          <q-btn v-close-popup class="text-white bg-c-8 q-ma-md">
            <q-icon name="cancel" class="q-mr-sm"/>
            Cancelar
          </q-btn>
          <q-btn v-close-popup class="text-white bg-c-1 q-ma-md" @click="afEditArticle(archivo.id)" :loading="cargando">
            <template v-slot:loading>
              <q-spinner-facebook />
            </template>
            <q-icon name="mdi-pencil" class="q-mr-sm" />
            EDITAR
          </q-btn>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="verImagen" transition-show="scale" transition-hide="scale" full-width>
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pa-md">
        <q-img
          :src="fGetFile(archivo.path)"
        />
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { useRouter } from 'vue-router'
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notify_service'
import { helper } from 'app/src/helpers/helper'
import { PostModel } from 'src/models/post_model'
import { LocalFileModel } from 'src/models/local_file_model'
import { postService } from 'src/services/post_service'

const paginacion = {
  rowsPerPage: 10,
  sortBy: 'id',
  descending: true
}

const columnas = [
  {
    name: 'id',
    label: 'Id',
    field: 'id',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'titulo',
    label: 'Título',
    field: 'titulo',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'categoria',
    label: 'Categoría',
    field: '',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'autor',
    label: 'Autor',
    field: '',
    sortable: true,
    sortOrder: 'ad',
    align: 'left'
  },
  {
    name: 'vistas',
    label: 'Vistas',
    field: 'vistas',
    sortable: true,
    sortOrder: 'ad',
    align: 'center'
  },
  {
    name: 'estado',
    label: 'Estado',
    field: '',
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
  components: {
  },
  setup () {
    const router = useRouter()
    const cargando = ref(false)
    const publicaciones = ref([])
    const filter = ref('')
    const dialogoVistaPrevia = ref(false)
    const dialogoEliminar = ref(false)
    const dialogoEditar = ref(false)
    const slidea = ref(0)
    const slidei = ref(0)
    const verImagen = ref(false)
    const slidev = ref(0)
    const publicacion = reactive(new PostModel())
    const archivo = reactive(new LocalFileModel())

    afGetAllArticles()

    async function afDeleteArticle () {
      cargando.value = true
      try {
        const result = await postService.spfRemoveArticle(publicacion.id)
        if (result.status === 200) {
          postService.sfRemoveAllArticlesFromLocalStorage()
          postService.spfFetchAllArticles().then(result => {
            publicaciones.value = [...result.data]
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
        const error = 'Hubo un error al eliminar el artículo. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
    }

    async function afEditArticle (props) {
      postService.sfRemoveEditPost()
      postService.sfSetEditPost(props)
      await postService.spfUnpublishArticle(props)
      postService.sfRemoveAllArticlesFromLocalStorage()
      router.push({ name: 'NuevaPublicacion' })
    }

    async function afGetAllArticles () {
      cargando.value = true
      try {
        const result = await postService.spfFetchAllArticles()
        if (result.status === 200) {
          publicaciones.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
          notifyService.notifyWarning(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Ocurrio un problema al mostrar los artículos. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
    }

    function fChangeTab () {
      filter.value = ''
    }

    async function afPublishArticle (props) {
      cargando.value = true
      try {
        const result = await postService.spfPublishArticle(props.row.id)
        if (result.status === 200) {
          postService.sfRemoveAllArticlesFromLocalStorage()
          postService.spfFetchAllArticles().then(result => {
            publicaciones.value = [...result.data]
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
        const error = 'Ocurrio un problema al públicar el artículo. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
      fHidePreview()
    }

    async function afUnpublishArticle (props) {
      cargando.value = true
      try {
        const result = await postService.spfUnpublishArticle(props.row.id)
        if (result.status === 200) {
          postService.sfRemoveAllArticlesFromLocalStorage()
          postService.spfFetchAllArticles().then(result => {
            publicaciones.value = [...result.data]
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
        const error = 'Ocurrio un problema al despúblicar el artículo. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
      fHidePreview()
    }

    function fCancelPreview () {
      fHidePreview()
      fResetFields()
    }

    function fGetFile (asset) {
      return helper.getAsset(asset)
    }

    function fHidePreview () {
      dialogoVistaPrevia.value = false
      dialogoEliminar.value = false
    }

    function fRemoveArticle (props) {
      Object.assign(publicacion, props.row)
      if (publicacion.creado !== null) {
        publicacion.creado = helper.getDateWithFormat(publicacion.creado)
      }
      if (publicacion.modificado !== null) {
        publicacion.modificado = helper.getDateWithFormat(publicacion.modificado)
      }
      if (publicacion.eliminado !== null) {
        publicacion.eliminado = helper.getDateWithFormat(publicacion.eliminado)
      }
      if (publicacion.publicado !== null) {
        publicacion.publicado = helper.getDateWithFormat(publicacion.publicado)
      }
      dialogoEliminar.value = true
    }

    function fResetFields () {
      publicacion.id = null
      publicacion.titulo = ''
      publicacion.copete = ''
      publicacion.cuerpo = ''
      publicacion.comentarios = ''
      publicacion.vistas = ''
      publicacion.publicado = ''
      publicacion.publicador = ''
      publicacion.imagenPortada = null
      publicacion.categoria = ''
      publicacion.autor = ''
      publicacion.creado = ''
      publicacion.creador = ''
      publicacion.modificado = ''
      publicacion.modificador = ''
      publicacion.eliminado = ''
      publicacion.eliminador = ''
    }

    function fSeeArticlePreview (props) {
      Object.assign(publicacion, props.row)
      dialogoVistaPrevia.value = true
    }

    function fSeeImage (props) {
      Object.assign(archivo, props)
      verImagen.value = true
    }

    function fShowDialogEdit (props) {
      Object.assign(archivo, props.row)
      dialogoEditar.value = true
    }

    return {
      afDeleteArticle,
      afEditArticle,
      afPublishArticle,
      afUnpublishArticle,
      fGetFile,
      fCancelPreview,
      fChangeTab,
      fRemoveArticle,
      fSeeArticlePreview,
      fSeeImage,
      fShowDialogEdit,

      archivo,
      cargando,
      columnas,
      dialogoEditar,
      dialogoEliminar,
      dialogoVistaPrevia,
      filter: ref(''),
      paginacion,
      publicacion,
      publicaciones,
      slidea,
      slidei,
      slidev,
      tab: ref('publicadas'),
      verImagen,

      exportTable () {
        let csvContent = 'data:text/csv;charset=utf-8,'
        csvContent += [
          Object.keys(publicaciones.value[0]).join(';'),
          ...publicaciones.value.map(item => Object.values(item).join(';'))
        ]
          .join('\n')
          .replace(/(^\[)|(\]$)/gm, '')

        const data = encodeURI(csvContent)
        const link = document.createElement('a')
        link.setAttribute('href', data)
        link.setAttribute('download', 'publicaciones.csv')
        link.click()
      }
    }
  }
}
</script>

<style scoped>
.enlace {
  text-decoration: none;
  color: #4B4E6D !important;
  background-color: transparent !important;
}
.q-img {cursor: pointer;}
</style>
