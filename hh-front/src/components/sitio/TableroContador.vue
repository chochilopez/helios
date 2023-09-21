<template>
  <q-card class="q-ma-xs no-shadow no-border">
    <q-card-section class="q-pa-none">
      <div class="row q-col-gutter-sm ">
        <div class="col-md-3 col-sm-12 col-xs-12">
          <q-item class="q-pa-none fondo2">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo1">
              <q-icon name="person" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ contador1 }}</q-item-label>
              <q-item-label>My Account</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <q-item class="q-pa-none fondo4">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo3">
              <q-icon name="person" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ contador2 }}</q-item-label>
              <q-item-label>My Account</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <q-item class="q-pa-none fondo6">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo5">
              <q-icon name="person" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ contador3 }}</q-item-label>
              <q-item-label>My Account</q-item-label>
            </q-item-section>
          </q-item>
        </div>
        <div class="col-md-3 col-sm-12 col-xs-12">
          <q-item class="q-pa-none fondo8">
            <q-item-section side class=" q-pa-lg q-mr-none text-white fondo7">
              <q-icon name="person" color="white" size="24px"></q-icon>
            </q-item-section>
            <q-item-section class=" q-pa-md q-ml-none  text-white">
              <q-item-label class="text-white text-h6 text-weight-bolder">{{ contador4 }}</q-item-label>
              <q-item-label>My Account</q-item-label>
            </q-item-section>
          </q-item>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from 'vue'

export default {
  setup () {
    const contador1 = ref()
    const contador2 = ref()
    const contador3 = ref()
    const contador4 = ref()

    return {
      contador1,
      contador2,
      contador3,
      contador4
    }
  }
}
</script>
 <style scoped>
  .fondo1 {
    background-color: #f72585;
  }
  .fondo2 {
    background-color: #ff85bc;
  }
  .fondo3 {
    background-color: #7209b7;
  }
  .fondo4 {
    background-color: #8c53b3;
  }
  .fondo5 {
    background-color: #3a0ca3;
  }
  .fondo6 {
    background-color: #6f57a7;
  }
  .fondo7 {
    background-color: #4361ee;
  }
  .fondo8 {
    background-color: #778ce7;
  }
 </style>
