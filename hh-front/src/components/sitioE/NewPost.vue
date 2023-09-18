<template>
  <q-form @submit="afPersistArticle()">
    <div class="row q-pa-md">
      <div class="col-12 col-md-6 q-px-sm">
        <q-input
          v-model="publicacion.titulo"
          label="Titulo de la publicacion"
          outlined
          counter
          maxlength="100"
          clearable
          :rules="[rules.required, rules.min8, rules.max100]"
          class="q-pb-xl"
        >
          <template v-slot:before>
            <q-icon name="title" />
          </template>
        </q-input>
        <q-input
          v-model="publicacion.copete"
          type="textarea"
          label="Copete"
          hint="Subtítulo y descripcion de la publicacion"
          outlined
          counter
          maxlength="500"
          clearable
          :rules="[rules.required, rules.min8, rules.maxText]"
          class="q-pb-xl"
        >
          <template v-slot:before>
            <q-icon name="local_offer" />
          </template>
        </q-input>
        <q-input
          v-model="publicacion.autor"
          label="Autor de la publicacion"
          outlined
          counter
          maxlength="100"
          clearable
          :rules="[rules.required, rules.min8, rules.max100]"
          class="q-pb-xl"
        >
          <template v-slot:before>
            <q-icon name="person_add_alt" />
          </template>
        </q-input>
        <q-input
          v-model="publicacion.categoria"
          label="Categoria de la publicacion"
          outlined
          counter
          maxlength="100"
          clearable
          :rules="[rules.required, rules.min8, rules.max100]"
          class="q-pb-xl"
        >
          <template v-slot:before>
            <q-icon name="campaign" />
          </template>
        </q-input>
      </div>
      <div class="col-12 col-md-6 q-m">
        <q-btn @click="dialogoImagen = true" class="bg-c-3 text-white q-ma-sm">
          Seleccionar imagén portada
        </q-btn>
        <q-img
          :src="fGetAsset(publicacion.imagenPortada.path)"
          fit="contain"
          height="300px"
          class="q-pa-sm"
        />
        <q-card class="q-pa-md">
          <div class="row">
            <div class="col">
              <q-btn @click="dialogoAudios = true" class="bg-c-3 text-white">Seleccionar audios</q-btn>
            </div>
          </div>
          <div class="row" v-if="publicacion.audios.length > 0">
            {{ publicacion.audios.length }} audios seleccionados
          </div>
        </q-card>
        <q-card class="q-pa-md">
          <div class="row">
            <div class="col">
              <q-btn @click="dialogoImagenes = true" class="bg-c-3 text-white">Selecionar imagenes</q-btn>
            </div>
          </div>
          <div class="row" v-if="publicacion.imagenes.length > 0">
            {{ publicacion.imagenes.length }} imagenes seleccionadas
          </div>
        </q-card>
        <q-card class="q-pa-md">
          <div class="row">
            <div class="col">
              <q-btn @click="dialogoVideos = true" class="bg-c-3 text-white">Selecionar videos</q-btn>
            </div>
          </div>
          <div class="row" v-if="publicacion.videos.length > 0">
            {{ publicacion.videos.length }} videos seleccionados
          </div>
        </q-card>
      </div>
    </div>
    <div class="row">
      <div class="col q-pa-xl">
        <q-editor
          :rules="[rules.required, rules.min100]"
          toolbar-text-color="white"
          toolbar-toggle-color="yellow-8"
          toolbar-bg="primary"
          v-model="publicacion.cuerpo"
          :dense="$q.screen.lt.md"
          :toolbar="[
            [
              {
                label: $q.lang.editor.align,
                icon: $q.iconSet.editor.align,
                fixedLabel: true,
                list: 'only-icons',
                options: ['left', 'center', 'right', 'justify']
              },
            ],
            ['bold', 'italic', 'strike', 'underline', 'subscript', 'superscript'],
            ['token', 'hr', 'link', 'custom_btn'],
            ['print', 'fullscreen'],
            [
              {
                label: $q.lang.editor.formatting,
                icon: $q.iconSet.editor.formatting,
                list: 'no-icons',
                options: [
                  'p',
                  'h1',
                  'h2',
                  'h3',
                  'h4',
                  'h5',
                  'h6',
                  'code'
                ]
              },
              {
                label: $q.lang.editor.fontSize,
                icon: $q.iconSet.editor.fontSize,
                fixedLabel: true,
                fixedIcon: true,
                list: 'no-icons',
                options: [
                  'size-1',
                  'size-2',
                  'size-3',
                  'size-4',
                  'size-5',
                  'size-6',
                  'size-7'
                ]
              },
              {
                label: $q.lang.editor.defaultFont,
                icon: $q.iconSet.editor.font,
                fixedIcon: true,
                list: 'no-icons',
                options: [
                  'default_font',
                  'arial',
                  'arial_black',
                  'comic_sans',
                  'courier_new',
                  'impact',
                  'lucida_grande',
                  'times_new_roman',
                  'verdana'
                ]
              },
              'removeFormat'
            ],
            ['quote', 'unordered', 'ordered', 'outdent', 'indent'],

            ['undo', 'redo'],
            ['viewsource']
          ]"
          :fonts="{
            arial: 'Arial',
            arial_black: 'Arial Black',
            comic_sans: 'Comic Sans MS',
            courier_new: 'Courier New',
            impact: 'Impact',
            lucida_grande: 'Lucida Grande',
            times_new_roman: 'Times New Roman',
            verdana: 'Verdana'
          }"
        />
      </div>
    </div>
    <div class="row justify-around q-pa-xl">
      <q-btn @click="fCancelDraft()" stack class="text-white bg-c-1" icon="cancel">Descartar borrador</q-btn>
      <q-btn @click="dialogoVistaPrevia = true" stack class="text-white bg-c-6" icon="preview">Vista previa</q-btn>
      <q-btn type="submit" stack class="text-white bg-c-8" icon="save">Guardar publicacion</q-btn>
    </div>
  </q-form>

  <q-dialog v-model="dialogoGuardarPublicar" persistent>
    <q-card>
      <q-card-section class="row items-center">
        <q-avatar icon="mdi-newspaper-plus" color="primary" text-color="white" />
        <span class="q-ml-sm">¿Deseas sólo guardar la publicacion o quieres publicarla?</span>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn flat label="Solo guardar" color="primary" v-close-popup @click="fGoToDashboard()" />
        <q-btn flat label="PUBLICAR" color="primary" v-close-popup @click="afPublishArticle()" />
      </q-card-actions>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoImagen" persistent transition-show="scale" transition-hide="scale">
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Seleccionar imagen de portada</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <div class="row justify-center q-ma-md">
          <q-table
            :showing="!cargando"
            bordered
            title="Galería imagenes"
            :columns="columns"
            rows-per-page-label="Registros por pagina"
            no-data-label="Sin datos para mostrar"
            :pagination="paginacion"
            :filter="filter"
            hide-no-data
            :rows="imagenes"
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
                <q-btn @click="afSetPrimaryImage(props)" dense round flat class="text-c-8" icon="mdi-clipboard-plus"></q-btn>
              </q-td>
            </template>
            <template v-slot:body-cell-imagen="props">
              <q-td :props="props">
                <q-img
                  :src="fGetAsset(props.row.path)"
                  :alt="props.row.descripcion"
                  fit="cover"
                  @click="fSeeImage(props.row)"
                />
              </q-td>
            </template>
          </q-table>
          <q-inner-loading :showing="cargando">
            <q-spinner-puff class="text-c-8" size="6em" />
          </q-inner-loading>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoAudios" persistent transition-show="scale" transition-hide="scale">
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Seleccionar audios</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <div class="row justify-center q-ma-md">
          <q-table
            :showing="!cargando"
            bordered
            title="Galería audios"
            :columns="columns"
            rows-per-page-label="Registros por pagina"
            no-data-label="Sin datos para mostrar"
            :pagination="paginacion"
            :filter="filter"
            hide-no-data
            :rows="audios"
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
                <q-btn @click="afSelectAudios(props)" dense round flat class="text-c-8" icon="mdi-clipboard-plus"></q-btn>
              </q-td>
            </template>
            <template v-slot:body-cell-imagen="props">
              <q-td :props="props">
                <q-media-player
                  type="audio"
                  content-class="bg-c-18"
                  :source="fGetAsset(props.row.path)"
                />
              </q-td>
            </template>
          </q-table>
          <q-inner-loading :showing="cargando">
            <q-spinner-puff class="text-c-8" size="6em" />
          </q-inner-loading>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoImagenes" persistent transition-show="scale" transition-hide="scale">
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Seleccionar imagenes</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <div class="row justify-center q-ma-md">
          <q-table
            :showing="!cargando"
            bordered
            title="Galería de imagenes"
            :columns="columns"
            rows-per-page-label="Registros por pagina"
            no-data-label="Sin datos para mostrar"
            :pagination="paginacion"
            :filter="filter"
            hide-no-data
            :rows="imagenes"
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
                <q-btn @click="afSelectImages(props)" dense round flat class="text-c-8" icon="mdi-clipboard-plus"></q-btn>
              </q-td>
            </template>
            <template v-slot:body-cell-imagen="props">
              <q-td :props="props">
                <q-img
                  :src="fGetAsset(props.row.path)"
                  :alt="props.row.descripcion"
                  fit="cover"
                  @click="fSeeImage(props.row)"
                />
              </q-td>
            </template>
          </q-table>
          <q-inner-loading :showing="cargando">
            <q-spinner-puff class="text-c-8" size="6em" />
          </q-inner-loading>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoVideos" persistent transition-show="scale" transition-hide="scale">
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <div>Seleccionar videos</div>
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pt-none">
        <div class="row justify-center q-ma-md">
          <q-table
            :showing="!cargando"
            bordered
            title="Galería de videos"
            :columns="columns"
            rows-per-page-label="Registros por pagina"
            no-data-label="Sin datos para mostrar"
            :pagination="paginacion"
            :filter="filter"
            hide-no-data
            :rows="videos"
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
                <q-btn @click="afSelectVideos(props)" dense round flat class="text-c-8" icon="mdi-clipboard-plus"></q-btn>
              </q-td>
            </template>
            <template v-slot:body-cell-imagen="props">
              <q-td :props="props">
                <q-media-player
                  type="video"
                  content-class="bg-c-18"
                  :source="fGetAsset(props.row.path)"
                />
              </q-td>
            </template>
          </q-table>
          <q-inner-loading :showing="cargando">
            <q-spinner-puff class="text-c-8" size="6em" />
          </q-inner-loading>
        </div>
      </q-card-section>
    </q-card>
  </q-dialog>

  <q-dialog v-model="dialogoVerImagen" transition-show="scale" transition-hide="scale" full-width>
    <q-card class="text-white">
      <q-bar class="bg-c-3">
        <q-space />
        <q-btn dense flat icon="close" v-close-popup>
          <q-tooltip class="bg-c-2 text-white">Cerrar</q-tooltip>
        </q-btn>
      </q-bar>
      <q-card-section class="q-pa-md">
        <q-img :src="fGetAsset(imagen.path)" />
      </q-card-section>
    </q-card>
  </q-dialog>

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
            <div class="col-3">
              <q-chip square color="cinco-cv" class="text-white">
                <strong>
                  {{ publicacion.categoria }}
                </strong>
              </q-chip>
            </div>
            <q-space />
            <div class="col-3 text-right" >
              <q-chip square  color="uno-cv" class="text-white">
                Fecha de publicación
              </q-chip>
            </div>
          </div>
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
                :src="fGetAsset(publicacion.imagenPortada.path)"
                :alt="publicacion.imagenPortada.descripcion"
                fit="contain"
                height="580px"
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
                    :source="fGetAsset(audio.path)"
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
                    :src="fGetAsset(imagen.path)"
                    :alt="publicacion.titulo"
                    fit="contain"
                    height="580px"
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
                    :source="fGetAsset(video.path)"
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

</template>

<script>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { notifyService } from 'src/helpers/notificar_service'
import { helper } from 'src/helpers/helper'
import { postService } from 'src/services/post_service'
import { localFileService } from 'src/services/local_file_service'
import { validateRules } from 'src/helpers/validate_rules'
import { keyRing } from 'src/helpers/key_ring'

const columns = [
  { name: 'id', label: 'Id imagen', align: 'left', field: 'id', sortOrder: 'da', sortable: true },
  { name: 'nombreArchivo', label: 'Nombre archivo', align: 'left', field: 'nombreArchivo', sortable: true },
  { name: 'imagen', label: 'Imagen', field: '', align: 'center' },
  { name: 'actions', label: 'Agregar', field: '', align: 'center' }
]

const paginacion = {
  rowsPerPage: 10,
  sortBy: 'id',
  descending: true
}

export default {
  components: {
  },
  setup () {
    const audios = ref([])
    const audiosPrevia = ref([])
    const cargando = ref(false)
    const dialogoImagen = ref(false)
    const dialogoAudios = ref(false)
    const dialogoGuardarPublicar = ref(false)
    const dialogoImagenes = ref(false)
    const dialogoVerImagen = ref(false)
    const dialogoVideos = ref(false)
    const dialogoVistaPrevia = ref(false)
    const filter = ''
    const imagen = reactive({
      id: null,
      path: '',
      descripcion: ''
    })
    const imagenes = ref([])
    const imagenesPrevia = ref([])
    const publicacion = reactive({
      id: null,
      titulo: '',
      copete: '',
      meta: '',
      categoria: '',
      autor: '',
      publicado: '',
      comentarios: true,
      portada: true,
      publicable: false,
      cuerpo: '',
      imagenPortada: {
        id: null,
        path: 'vacio.png',
        descripcion: ''
      },
      audios: [],
      imagenes: [],
      videos: []
    })
    const router = useRouter()
    const rules = reactive(validateRules.rules)
    const slidea = ref(0)
    const slidei = ref(0)
    const slidev = ref(0)
    const videos = ref([])
    const videosPrevia = ref([])

    if (keyRing.obtenerDeLocal('lebeEditPost') !== null) {
      console.log('Editar articulo ' + postService.sfGetEditPost())
      afGetArticleById()
    }

    afGetAllAudios()
    afGetAllImages()
    afGetAllVideos()

    async function afGetAllAudios () {
      cargando.value = true
      try {
        const result = await localFileService.spfFetchAllAudios()
        if (result.status === 200) {
          audios.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la galería de audios. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
    }

    async function afGetAllImages () {
      cargando.value = true
      try {
        const result = await localFileService.spfFetchAllImages()
        if (result.status === 200) {
          imagenes.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la galería de imagenes. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
    }

    async function afGetAllVideos () {
      cargando.value = true
      try {
        const result = await localFileService.spfFetchAllVideos()
        if (result.status === 200) {
          videos.value = result.data
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Hubo un error al obtener la galería de imagenes. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      cargando.value = false
    }

    async function afGetArticleById () {
      cargando.value = true
      try {
        const result = await postService.spfFetchArticleById(postService.sfGetEditPost())
        if (result.status === 200) {
          Object.assign(publicacion, result.data)
          console.info(result.headers.message)
        } else if (result.status === 202) {
          console.warn(result.headers.message)
        } else if (result.status === 204) {
          console.error(result.headers.message)
          notifyService.notifyError(result.headers.message)
        }
      } catch (err) {
        const error = 'Ocurrio un error al intentar obtener el articulo que desea modificar. ' + err
        notifyService.notifyError(error)
        console.error(error)
      }
      postService.sfRemoveEditPost()
      cargando.value = false
    }

    async function afPersistArticle () {
      cargando.value = true
      try {
        if (publicacion.cuerpo.length > 100 && publicacion.imagenPortada.id !== null) {
          const nuevaPublicacion = {
            id: publicacion.id,
            titulo: publicacion.titulo,
            copete: publicacion.copete,
            cuerpo: publicacion.cuerpo,
            meta: publicacion.copete,
            importancia: 0,
            comentarios: publicacion.comentarios,
            portada: publicacion.portada,
            categoria: publicacion.categoria,
            autor: publicacion.autor,
            idImagenPortada: publicacion.imagenPortada.id,
            idsAudios: publicacion.audios,
            idsImagenes: publicacion.imagenes,
            idsVideos: publicacion.videos,
            publicado: null
          }
          const result = await postService.spfPersistArticle(nuevaPublicacion)
          if (result.status === 201) {
            publicacion.id = result.data.id
            dialogoGuardarPublicar.value = true
            console.info(result.headers.message)
            notifyService.notifySuccess(result.headers.message)
          } else if (result.status === 202) {
            console.warn(result.headers.message)
            notifyService.notifyWarning(result.headers.message)
          } else if (result.status === 204) {
            console.error(result.headers.message)
            notifyService.notifyError(result.headers.message)
          }
        } else {
          notifyService.infoError('Debés completar todos los campos.')
        }
      } catch (err) {
        notifyService.infoError('Ocurrió un error al intentar guardar la publicación. ' + err)
      }
      cargando.value = false
    }

    async function afPublishArticle () {
      cargando.value = true
      try {
        const result = await postService.spfPublishArticle(publicacion.id)
        if (result.status === 200) {
          fGoToDashboard()
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
        notifyService.notifyError('Error al intentar publicar el artículo. ' + err)
      }
      cargando.value = false
    }

    async function afSelectAudios (props) {
      dialogoAudios.value = false
      if (publicacion.audios.indexOf(props.row.id) < 0) {
        publicacion.audios.push(props.row.id)
        audiosPrevia.value.push(props.row)
      } else {
        notifyService.notifyWarning('El audio seleccionado ya fue agregado')
      }
    }

    async function afSelectImages (props) {
      dialogoImagenes.value = false
      if (publicacion.imagenes.indexOf(props.row.id) < 0) {
        publicacion.imagenes.push(props.row.id)
        imagenesPrevia.value.push(props.row)
      } else {
        notifyService.notifyWarning('La imagen seleccionada ya fue agregada')
      }
    }

    async function afSelectVideos (props) {
      dialogoVideos.value = false
      if (publicacion.videos.indexOf(props.row.id) < 0) {
        publicacion.videos.push(props.row.id)
        videosPrevia.value.push(props.row)
      } else {
        notifyService.notifyWarning('El video seleccionado ya fue agregado')
      }
    }

    async function afSetPrimaryImage (props) {
      dialogoImagen.value = false
      publicacion.imagenPortada.id = props.row.id
      publicacion.imagenPortada.path = props.row.path
    }

    function fCancelDraft () {
      publicacion.id = null
      publicacion.titulo = ''
      publicacion.copete = ''
      publicacion.meta = ''
      publicacion.categoria = ''
      publicacion.autor = ''
      publicacion.comentarios = true
      publicacion.portada = true
      publicacion.cuerpo = ''
      publicacion.imagenPortada.id = null
      publicacion.imagenPortada.path = 'vacio.png'
      publicacion.audios = []
      publicacion.imagenes = []
      publicacion.videos = []
      imagen.id = null
      imagen.path = ''
      audiosPrevia.value = []
      imagenesPrevia.value = []
      videosPrevia.value = []
    }

    function fGetAsset (pathArchivo) {
      return helper.getAsset(pathArchivo)
    }

    function fGoToDashboard () {
      postService.sfRemoveAllArticlesFromLocalStorage()
      router.push({ name: 'Dashboard' })
    }

    function fSeeImage (archivo) {
      Object.assign(imagen, archivo)
      dialogoVerImagen.value = true
    }

    return {
      fGetAsset,
      fGoToDashboard,
      fSeeImage,

      afSetPrimaryImage,
      afSelectImages,
      afSelectAudios,
      afSelectVideos,
      fCancelDraft,
      afPersistArticle,
      afPublishArticle,

      audios,
      audiosPrevia,
      cargando,
      columns,
      dialogoAudios,
      dialogoGuardarPublicar,
      dialogoImagen,
      dialogoImagenes,
      dialogoVerImagen,
      dialogoVideos,
      dialogoVistaPrevia,
      filter,
      imagen,
      imagenes,
      imagenesPrevia,
      paginacion,
      publicacion,
      rules,
      slidea,
      slidei,
      slidev,
      videos
    }
  }
}
</script>

<style scoped>
.q-img {cursor: pointer;}
</style>
