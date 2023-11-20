<template>
  <q-card class="no-shadow no-border">
    <q-card-section class="q-pa-none">
      <div class="row q-col-gutter-sm justify-center">
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo2">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo1">
              <q-icon name="fa-solid fa-comments-dollar" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ cantidadViajes }}</q-item-label>
              <q-item-label>Presupuestos</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo4">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo3">
              <q-icon name="fa-solid fa-calendar-check" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ fFormatearFecha(proximo.fecha) }}</q-item-label>
              <q-item-label>Proximo viaje presupuestado</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo6">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo5">
              <q-icon name="fa-solid fa-road" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ ultimo.valorKm }}</q-item-label>
              <q-item-label>Ultimo valor km</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo8">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo7">
              <q-icon name="fa-solid fa-clipboard-question" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ ultimo.categoriaViaje }}</q-item-label>
              <q-item-label>Ultima categoria presupuestada</q-item-label>
            </q-item-section>
          </q-item>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { reactive, ref } from 'vue'
import { ayuda } from 'src/helpers/ayuda'
import { presupuestoService } from 'src/services/presupuesto_service'
import { PresupuestoModel } from 'src/models/presupuesto_model'

export default {
  setup () {
    const autoridad = ref(ayuda.getAutoridad())
    const cantidadViajes = ref()
    const cantidadTransportada = ref()
    const kmCargado = ref()
    const kmVacio = ref()
    const proximo = reactive(new PresupuestoModel())
    const ultimo = reactive(new PresupuestoModel())

    afBuscarCantidadViajes()
    afBuscarProximo()
    afBuscarUltimo()

    async function afBuscarCantidadViajes () {
      let resultado = null
      if (autoridad.value === 'admin') {
        resultado = await presupuestoService.spfContarTodasConEliminadas()
      } else {
        resultado = await presupuestoService.spfContarTodas()
      }
      if (resultado.status === 200) {
        cantidadViajes.value = resultado.data
      }
    }

    async function afBuscarProximo () {
      const resultado = await presupuestoService.spfBuscarProximo()
      if (resultado.status === 200) {
        Object.assign(proximo, resultado.data)
      }
    }

    async function afBuscarUltimo () {
      const resultado = await presupuestoService.spfBuscarUltimo()
      if (resultado.status === 200) {
        Object.assign(ultimo, resultado.data)
      }
    }

    function fFormatearFecha (fecha) {
      return ayuda.getDateWithFormat(fecha)
    }

    return {
      cantidadViajes,
      cantidadTransportada,
      kmCargado,
      kmVacio,
      proximo,
      ultimo,

      fFormatearFecha
    }
  }
}
</script>
 <style scoped>
  .fondo1 {
    background-color: #ebac00;
  }
  .fondo2 {
    background-color: #ffbe0b;
  }
  .fondo3 {
    background-color: #fb5607;
  }
  .fondo4 {
    background-color: #ff9c6e;
  }
  .fondo5 {
    background-color: #ff006e;
  }
  .fondo6 {
    background-color: #fd5ea3;
  }
  .fondo7 {
    background-color: #8338ec;
  }
  .fondo8 {
    background-color: #a97ce7;
  }
  .fondo9 {
    background-color: #3a86ff;
  }
  .fondo10 {
    background-color: #97bfff;
  }
 </style>
