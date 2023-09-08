<template>
  <div class="row justify-center">
    <div class="col-12 fuente9">
      <q-card class="q-ma-sm">
        <div class="row q-pa-sm">
          <span class="text-bold">ID PACIENTE: </span><span class="fuente9 q-ml-sm">{{ paciente.idPaciente }}</span>
        </div>
        <div class="row">
          <div class="col q-pa-sm">
            <span class="text-bold">NOMBRE Y APELLIDO: </span><span class="fuente9">{{ paciente.nomyape }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">NUMERO DOCUMENTO: </span><span class="fuente9">{{ paciente.nroDoc }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">FECHA NACIMIENTO: </span><span class="fuente9">{{ formatoFecha(paciente.fechaNac) }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col q-pa-sm">
            <span class="text-bold">DOMICILIO: </span><span class="fuente9">{{ paciente.domicilio }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">CELULAR: </span><span class="fuente9">{{ paciente.celular }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">TELEFONO: </span><span class="fuente9">{{ paciente.telefono }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col q-pa-sm">
            <span class="text-bold">DOMICILIO LABORAL: </span><span class="fuente9">{{ paciente.laboral }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">MAIL: </span><span class="fuente9">{{ paciente.mail }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">OCUPACION: </span><span class="fuente9">{{ paciente.ocupacion }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col q-pa-sm">
            <span class="text-bold">OBRA SOCIAL: </span><span>{{ paciente.obraSocial.nombre }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">NUMERO AFILIADO: </span><span>{{ paciente.ocupacion }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">NOTAS: </span><span>{{ paciente.notas }}</span>
          </div>
        </div>
        <div class="row">
          <div class="col q-pa-sm">
            <span class="text-bold">SEXO: </span><span>{{ paciente.sexo }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">HC EN CLINICA: </span><span>{{ paciente.hCenClinica }}</span>
          </div>
          <div class="col q-pa-sm">
            <span class="text-bold">TIPO IVA: </span><span>{{ paciente.tipoIVA }}</span>
          </div>
        </div>
      </q-card>
    </div>
  </div>
</template>

<script>
import { helper } from 'app/src/helpers/helper'

export default {
  props: {
    paciente: {
      type: Object,
      default: () => ({
        idPaciente: '',
        nomyape: '',
        domicilio: '',
        laboral: '',
        telefono: '',
        fechaNac: '',
        nroDoc: '',
        celular: '',
        mail: '',
        ocupacion: '',
        notas: '',
        nroAfiliado: '',
        sexo: '',
        hCenClinica: '',
        tipoIVA: '',
        idCp: '',
        idObraSocial: '',
        obraSocial: { nombre: '' }
      })
    }
  },
  data () {
    function formatoFecha (fecha) {
      return helper.getDateWithFormat(fecha)
    }

    return {
      formatoFecha

    }
  }
}
</script>
