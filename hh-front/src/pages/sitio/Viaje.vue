<template>
  <ViajesViajesContador />
  <ViajesViajesListado />
</template>

<script>
import ViajesViajesContador from 'src/components/sitio/ViajesViajesContador.vue'
import ViajesViajesListado from 'src/components/sitio/ViajesViajesListado.vue'

export default {
  components: {
    ViajesViajesContador,
    ViajesViajesListado
  },
  setup () {
    return {}
  }
}
</script>
