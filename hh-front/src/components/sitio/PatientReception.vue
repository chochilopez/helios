<template>
  <!-- <div class="row">
    <q-btn icon="download" label="Descargar" @click="fPrintInform" class="q-ma-md bg-c-10 text-white" />
  </div> -->
  <div ref="modelo" style="width: 793px; height: 1122px">
    <q-card class="text-black q-pt-md" flat>
      <q-card-section>
        <div class="row justify-center q-mx-xl">
          <div class="col-md-4 text-center q-mt-md">
            <q-img src="~/assets/images/banner.png"/>
          </div>
          <div class="col-md-8 text-center">
            <span class="text-bold fuente9" style="font-size: 16px">Laboratorio de Estudios Bioquícos Especializados</span><br>
            <span class="text-bold fuente4" style="font-size: 8px">BANCO DE SANGRE</span><br>
            <span class="fuente1" style="font-size: 8px">25 de MAYO 895 (ESQUINA SARMIENTO) - C.P. 3116 - CRESPO - ENTRE RIOS</span><br>
            <span class="fuente1" style="font-size: 8px">TEL: 0343-4954533 - WHATSAPP: 343 4165169 - EMAIL: consultas@lebe.com.ar - www.lebe.com.ar</span>
          </div>
          <div class="col-12">
            <q-bar class="bg-black rounded-borders q-mt-sm" style="height: 2px"/>
          </div>
          <div class="col-md-12 q-pa-sm q-mt-xs" style="border: 1px solid black">
            <div class="row" v-if="!cargandoRecepcion">
              <div class="col-md-2">
                <p class="fuente11 text-bold" style="font-size: 10px">Paciente:</p>
                <p class="fuente11 text-bold" style="font-size: 10px">Médico solicitante:</p>
              </div>
              <div class="col-md-4 q-pr-md">
                <p class="fuente11 text-uppercase" style="font-size: 10px">{{ recepcion.paciente.nomyape }}</p>
                <p class="fuente11 text-uppercase" style="font-size: 10px">{{ recepcion.medico.nomyape }}</p>
              </div>
              <div class="col-md-2 q-pl-md">
                <p class="fuente11 text-bold" style="font-size: 10px">Fecha:</p>
                <p class="fuente11 text-bold" style="font-size: 10px">Nº de orden:</p>
              </div>
              <div class="col-md-4">
                <p class="fuente11 text-uppercase" style="font-size: 10px">{{ recepcion.fecha }}</p>
                <p class="fuente11 text-uppercase" style="font-size: 10px">{{ recepcion.tipoPac }} {{ recepcion.idRecepcion }}</p>
              </div>
            </div>
            <div class="row" v-if="cargandoRecepcion">
                <q-spinner-puff size="50px" color="primary" />
            </div>
          </div>
          <div class="col-md-12 q-mt-md" v-if="cargandoResultado">
            <div class="row align-center">
              <q-spinner-puff size="50px" color="primary" />
            </div>
          </div>
          <div class="col-md-12 q-mt-md" v-if="!cargandoResultado">
            <div class="row">
              <div class="col-md-6 text-left">
                <span class="fuente11 text-bold" style="font-size: 8px">Pruebas</span>
              </div>
              <div class="col-md-2 text-right q-pr-sm">
                <span class="fuente11 text-bold" style="font-size: 8px">Resultado</span>
              </div>
              <div class="col-md-2 text-left q-pl-sm">
                <span class="fuente11 text-bold" style="font-size: 8px">Unidades</span>
              </div>
              <div class="col-md-2 text-left">
                <span class="fuente11 text-bold" style="font-size: 8px">Valor de referencia</span>
              </div>
            </div>
            <q-bar class="bg-black rounded-borders" style="height: 1px"/>

            <div v-for="inf in informe" :key="inf" class="row">

              <div class="col-md-6 text-left">
                <div v-if="inf.tipo === 'tituloSerie'">
                  <br>
                  <span class="fuente11" :class="{ 'text-bold': inf.negrita }" style="font-size: 9px">{{ inf.nombre }}</span>
                </div>

                <div v-if="inf.tipo === 'subTituloSerie' && inf.valor !== ''" class="q-ml-sm">
                  <div class="row">
                    <div class="col-md-3"><span class="fuente6" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">{{ inf.nombre }}</span></div>
                    <div class="col-md-9"><span class="fuente6" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">{{ inf.valorNombre }}</span></div>
                  </div>
                </div>

                <div v-if="inf.tipo === 'estudio'"  class="q-ml-sm">
                  <span class="fuente8" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">{{ inf.nombre }}</span>
                </div>

                <div v-if="inf.observaciones !== ''" class="q-ml-sm">
                  <div class="row">
                    <div class="col-md-3"><span class="fuente8" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">Observaciones:</span></div>
                    <div class="col-md-9"><span class="fuente8" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">{{ inf.observaciones }}</span></div>
                  </div>
                </div>
                <br v-if="inf.nombre === ''">

              </div>
              <div class="col-md-2 text-right q-pr-sm" :class="{ 'text-bold': inf.valorAnormal === 'SI' }">
                <br v-if="inf.tipo === 'tituloSerie'">
                <span class="fuente11" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">{{ inf.valorHallado }}</span>
              </div>
              <div class="col-md-2 text-left q-pl-sm" :class="{ 'text-bold': inf.valorAnormal === 'SI' }">
                <br v-if="inf.tipo === 'tituloSerie'">
                <span class="fuente11" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px">{{ inf.unidad }}</span>
                <span class="fuente11 q-pl-sm" :class="{ 'text-bold': inf.negrita }" style="font-size: 8px" v-if="inf.valorNumerico !== ''">({{ inf.valorNumerico }})</span>
              </div>
              <div class="col-md-2 text-left">
                <br v-if="inf.tipo === 'tituloSerie'">
                <span class="fuente11" :class="{ 'text-bold': inf.negrita }" style="font-size: 7px">{{ inf.valorNormal }}</span>
              </div>
            </div>
          </div>
        </div>
      </q-card-section>
    </q-card>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { notifyService } from 'src/helpers/notify_service'
import { jsPDF } from 'jspdf'
import html2canvas from 'html2canvas'
import { receptionService } from 'src/services/reception_service'
import { analysisService } from 'src/services/analysis_service'
import { determinationService } from 'src/services/determination_service'
import { studyLineService } from 'src/services/study_line_service'

export default {
  props: {
    idRecepcion: {
      type: String,
      required: true
    },
    tipoPaciente: {
      type: String,
      required: true
    }
  },
  setup (props) {
    const cargandoRecepcion = ref(false)
    const cargandoResultado = ref(false)
    const determinacion = reactive({
      idDet: '',
      analisis: {
        idEst: '',
        codMetroLab: '',
        idMetroLab: '',
        nombre: '',
        idCober: '',
        metodo: '',
        muestra: '',
        valorNormal: '',
        unidades: '',
        precio: '',
        costo: '',
        inos: '',
        derivacion: '',
        unidadBioquimica: '',
        derivar: '',
        subanalisis: '',
        nTipo: '',
        vMinimo: '',
        vMaximo: '',
        calcular: '',
        lInferior: '',
        lSuperior: '',
        limite: '',
        notas: '',
        demora: '',
        textoPre: '',
        idLaboratorio: '',
        tipoAnalisisCp: '',
        idAnalisisCp: '',
        predefinidos: '',
        itemIdBs: '',
        itemIdBsDec: '',
        itemType: '',
        abreviatura: '',
        abreviaturaEtiquetas: '',
        agrupacionEtiquetas: '',
        abreviaturaEtiquetas2: '',
        agrupacionEtiquetas2: '',
        multiplicador: '',
        tipoCultivo: '',
        aparato: '',
        itemIdBs_Old: '',
        itemIdBsDec_Old: '',
        itemType_Old: '',
        aplicaDerivacion: '',
        aplicaVeterinaria: '',
        derivacionSerologia: '',
        negrita: '',
        path: '',
        idProducto: '',
        cantidadProducto: '',
        idMuestraDerivacion: '',
        volumenDerivacion: '',
        pregunta: ''
      },
      nombre: '',
      unidades: '',
      metodo: '',
      valorNormal: '',
      titulo: '',
      vMinimo: '',
      vMaximo: '',
      calcular: '',
      lInferior: '',
      lSuperior: '',
      limite: '',
      notas: '',
      demora: '',
      textoPre: '',
      tipoAnalisisCp: '',
      idAnalisisCp: '',
      idAnalisisCp1: '',
      idAnalisisCp2: '',
      ordenImp: '',
      ceros: '',
      esperaResultado: '',
      abreviatura: '',
      abreviaturaEtiquetas: '',
      agrupacionEtiquetas: '',
      multiplicador: '',
      itemIdBs: '',
      itemIdBsDec: '',
      itemType: '',
      subTitulo: ''
    })
    const determinaciones = ref([])
    const estRecep = reactive({
      idRecepcion: '',
      tipoPac: '',
      codPac: '',
      idEst: '',
      idDet: '',
      valorHallado: '',
      valorAnormal: '',
      formaPago: '',
      notas: '',
      precio: '',
      estado: '',
      muestra: '',
      orden: '',
      nroOrden: '',
      impreso: '',
      yaImpreso: '',
      yaExportado: '',
      exportar: '',
      cultivo: '',
      idGermen: '',
      estadoATB: ''
    })
    const estReceps = ref([])
    const informe = ref([])
    const modelo = ref(null)
    const recepcion = reactive({
      idRecepcion: '',
      dni: '',
      fecha: '',
      tipoPac: '',
      paciente: { nomyape: '' },
      retirar: '',
      medico: { nomyape: '' },
      registrada: '',
      grupo: '',
      idReceptor: '',
      idObraSocial: '',
      idInstitucion: '',
      nroAfiliado: '',
      importe: '',
      pagada: '',
      factor: '',
      voluntario: '',
      impreso: '',
      internacion: '',
      coseguro: '',
      entrega: '',
      urgencia: '',
      valorOrden: '',
      coSegPagado: '',
      valTransfusion: '',
      valPreparacion: '',
      devolucion: '',
      notas: '',
      validada: '',
      horas: '',
      minutos: '',
      habilitadoDonar: '',
      autorizada: '',
      idPerfil: ''
    })

    afGetEstRecepByIdRecepcionAndTipoPac(props.idRecepcion, props.tipoPaciente)
    afGetReceptionByIdReceptionAndTipoPac(props.idRecepcion, props.tipoPaciente)

    function fPrintInform () {
      // eslint-disable-next-line new-cap
      const informe = new jsPDF({
        orientation: 'p',
        unit: 'px',
        format: 'a4',
        hotfixes: ['px_scaling']
      })
      const ancho = informe.internal.pageSize.getWidth()
      const alto = informe.internal.pageSize.getHeight()
      html2canvas(modelo.value).then(canvas => {
        const img = canvas.toDataURL('image/png', 1)
        document.body.appendChild(canvas)
        informe.addImage(img, 'PNG', 0, 0, ancho, alto)
        informe.save('sample.pdf')
      })
    }

    async function afGetReceptionByIdReceptionAndTipoPac (numero, letra) {
      cargandoRecepcion.value = true
      try {
        const result = await receptionService.spfFetchReceptionByIdReceptionAndTipoPac(numero, letra)
        if (result.status === 200) {
          console.info(result.headers.message)
          Object.assign(recepcion, result.data)
        } else if (result.status === 202) {
          notifyService.notifyWarning(result.headers.message)
          console.warn(result.headers.message)
        } else if (result.status === 204) {
          notifyService.notifyError(result.headers.message)
          console.error(result.headers.message)
        }
      } catch (err) {
        cargandoRecepcion.value = false
        const mensaje = 'Hubo un error al intentar obtener las recepciones. ' + err
        console.error(mensaje)
        notifyService.notifyError(mensaje)
      }
      cargandoRecepcion.value = false
    }

    async function afGetEstRecepByIdRecepcionAndTipoPac (numero, letra) {
      cargandoResultado.value = true
      try {
        const result = await studyLineService.spfFetchEstRecepByIdRecepcionAndTipoPac(numero, letra)
        if (result.status === 200) {
          console.info(result.headers.message)
          estReceps.value = result.data
          afFormInform()
        } else if (result.status === 202) {
          notifyService.notifyAlert(result.headers.message)
          console.warn(result.headers.message)
        } else if (result.status === 204) {
          notifyService.notifyError(result.headers.message)
          console.error(result.headers.message)
        }
      } catch (err) {
        const mensaje = 'Hubo un error al intentar obtener los resultados. ' + err
        console.error(mensaje)
        notifyService.notifyError(mensaje)
      }
      cargandoResultado.value = false
    }

    function ordenarDescNroOrden (x, y) {
      if (x.ordenEstRecep < y.ordenEstRecep) { return -1 }
      if (x.ordenEstRecep > y.ordenEstRecep) { return 1 }
      return 0
    }

    function ordenarDescOrdenImp (x, y) {
      if (x.ordenDeterminacion < y.ordenDeterminacion) { return -1 }
      if (x.ordenDeterminacion > y.ordenDeterminacion) { return 1 }
      return 0
    }

    function ordenarTitulo (x, y) {
      if (x.tipo === 'subTituloSerie' && y.tipo === 'tituloSerie') { return 1 }
      if (x.tipo === 'tituloSerie' && y.tipo === 'subTituloSerie') { return -1 }
      if (x.tipo === 'estudio' && y.tipo === 'tituloSerie') { return 1 }
      if (x.tipo === 'tituloSerie' && y.tipo === 'estudio') { return -1 }
      return 0
    }

    async function afFormInform () {
      informe.value = []
      for (let a = 0; a < estReceps.value.length; a++) {
        if (estReceps.value[a].idDet === 0) {
          analysisService.spfFetchAnalysisById(estReceps.value[a].idEst).then((valor) => {
            if (valor.status === 200) {
              informe.value.push({
                tipo: 'tituloSerie',
                ordenEstRecep: isNaN(parseInt(estReceps.value[a].nroOrden, 10)) ? '' : parseInt(estReceps.value[a].nroOrden, 10),
                ordenDeterminacion: isNaN(parseInt(valor.data.ordenImp, 10)) ? '' : parseInt(valor.data.ordenImp, 10),
                valorAnormal: estReceps.value[a].valorAnormal === 'SI',
                valorHallado: estReceps.value[a].valorHallado,
                nombre: valor.data.nombre,
                valorNombre: '',
                unidad: valor.data.unidades,
                negrita: valor.data.negrita === 'S',
                valorNormal: valor.data.valorNormal,
                observaciones: estReceps.value[a].notas,
                valorNumerico: estReceps.value[a].formaPago === '0' || estReceps.value[a].formaPago === '1' ? '' : estReceps.value[a].formaPago
              })
              if (valor.data.metodo !== '') {
                informe.value.push({
                  tipo: 'subTituloSerie',
                  ordenEstRecep: isNaN(parseInt(estReceps.value[a].nroOrden, 10)) ? '' : parseInt(estReceps.value[a].nroOrden, 10),
                  ordenDeterminacion: isNaN(parseInt(valor.data.ordenImp, 10)) ? '' : parseInt(valor.data.ordenImp, 10),
                  valorAnormal: '',
                  valorHallado: '',
                  nombre: 'Método',
                  valorNombre: valor.data.metodo,
                  unidad: '',
                  negrita: false,
                  valorNormal: '',
                  observaciones: estReceps.value[a].notas,
                  valorNumerico: estReceps.value[a].formaPago === '0' || estReceps.value[a].formaPago === '1' ? '' : estReceps.value[a].formaPago
                })
              }
              if (valor.data.muestra !== '') {
                informe.value.push({
                  tipo: 'subTituloSerie',
                  ordenEstRecep: isNaN(parseInt(estReceps.value[a].nroOrden, 10)) ? '' : parseInt(estReceps.value[a].nroOrden, 10),
                  ordenDeterminacion: isNaN(parseInt(valor.data.ordenImp, 10)) ? '' : parseInt(valor.data.ordenImp, 10),
                  valorAnormal: '',
                  valorHallado: '',
                  nombre: 'Muestra',
                  valorNombre: valor.data.muestra,
                  unidad: '',
                  negrita: false,
                  valorNormal: '',
                  observaciones: estReceps.value[a].notas,
                  valorNumerico: estReceps.value[a].formaPago === '0' || estReceps.value[a].formaPago === '1' ? '' : estReceps.value[a].formaPago
                })
              }
              informe.value = informe.value.sort(ordenarTitulo)
              informe.value = informe.value.sort(ordenarDescOrdenImp)
              informe.value = informe.value.sort(ordenarDescNroOrden)
            }
          })
        } else if (estReceps.value[a].idDet > 0) {
          determinationService.spfFetchDeterminationByIdDetAndIdEst(estReceps.value[a].idDet, estReceps.value[a].idEst).then((valor) => {
            if (valor.status === 200) {
              if (estReceps.value[a].valorHallado !== '' || valor.data.titulo !== 'N' || valor.data.subTitulo !== 'N' || valor.data.nombre === '-') {
                informe.value.push({
                  tipo: 'estudio',
                  ordenEstRecep: isNaN(parseInt(estReceps.value[a].nroOrden, 10)) ? '' : parseInt(estReceps.value[a].nroOrden, 10),
                  ordenDeterminacion: isNaN(parseInt(valor.data.ordenImp, 10)) ? '' : parseInt(valor.data.ordenImp, 10),
                  valorAnormal: estReceps.value[a].valorAnormal,
                  valorHallado: estReceps.value[a].valorHallado,
                  nombre: valor.data.nombre === '-' ? '' : valor.data.nombre,
                  valorNombre: '',
                  negrita: valor.data.negrita === 'S',
                  unidad: valor.data.unidades,
                  valorNormal: valor.data.valorNormal,
                  observaciones: estReceps.value[a].notas,
                  valorNumerico: estReceps.value[a].formaPago === '0' || estReceps.value[a].formaPago === '1' ? '' : estReceps.value[a].formaPago
                })
                informe.value = informe.value.sort(ordenarTitulo)
                informe.value = informe.value.sort(ordenarDescOrdenImp)
                informe.value = informe.value.sort(ordenarDescNroOrden)
              }
            }
          })
        }
      }
    }

    return {
      cargandoRecepcion,
      cargandoResultado,

      afFormInform,
      modelo,
      fPrintInform,

      recepcion,

      informe,
      estRecep,
      determinacion,
      estReceps,
      determinaciones
    }
  }

}
</script>
