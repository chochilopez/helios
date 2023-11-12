<template>
  <q-card class="q-ma-xs no-shadow no-border">
    <q-card-section class="q-pa-none">
      <div class="row q-col-gutter-sm justify-center">
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo2">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo1">
              <q-icon name="fa-solid fa-truck-fast" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ cantidadViajes }}</q-item-label>
              <q-item-label>Viajes</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo4">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo3">
              <q-icon name="fa-solid fa-truck" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ kmCargado }}</q-item-label>
              <q-item-label>Km Cargado</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo6">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo5">
              <q-icon name="fa-solid fa-truck-pickup" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ kmVacio }}</q-item-label>
              <q-item-label>Km Vacio</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-lg-3 col-md-6 col-xs-12">
          <q-item class="q-pa-none fondo8">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo7">
              <q-icon name="fa-solid fa-cow" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ cantidadTransportada }}</q-item-label>
              <q-item-label>Cantidad Transportada</q-item-label>
            </q-item-section>
          </q-item>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from 'vue'
import { viajeService } from 'src/services/viaje_service'
import { autenticacionService } from 'src/services/autenticacion_service'
import { rolEnum } from 'src/models/enums/rol_enum'

export default {
  setup () {
    const cantidadViajes = ref()
    const cantidadTransportada = ref()
    const kmCargado = ref()
    const kmVacio = ref()
    const ultimoPrecio = ref()

    afBuscarCantidadViajes()
    afBuscarSumaCantidadTransportada()
    afBuscarSumaKmCaragdo()
    afBuscarSumaKmVacio()
    afBuscarPrecioUltimoViaje()

    async function afBuscarCantidadViajes () {
      let resultado = null
      if (autenticacionService.obtenerAutoridades().includes(rolEnum.ADMIN)) {
        resultado = await viajeService.spfContarTodasConEliminadas()
      } else {
        resultado = await viajeService.spfContarTodas()
      }
      if (resultado.status === 200) {
        cantidadViajes.value = resultado.data
      }
    }

    async function afBuscarSumaCantidadTransportada () {
      const resultado = await viajeService.spfSumarCantidadTransportada()
      if (resultado.status === 200) {
        cantidadTransportada.value = resultado.data
      }
    }

    async function afBuscarSumaKmCaragdo () {
      const resultado = await viajeService.spfSumarKmCargado()
      if (resultado.status === 200) {
        kmCargado.value = resultado.data
      }
    }

    async function afBuscarSumaKmVacio () {
      const resultado = await viajeService.spfSumarKmVacio()
      if (resultado.status === 200) {
        kmVacio.value = resultado.data
      }
    }

    async function afBuscarPrecioUltimoViaje () {
      const resultado = await viajeService.spfBuscarUltimoViaje()
      if (resultado.status === 200) {
        ultimoPrecio.value = resultado.data.valorKm
      }
    }

    return {
      cantidadViajes,
      cantidadTransportada,
      kmCargado,
      kmVacio,
      ultimoPrecio
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
