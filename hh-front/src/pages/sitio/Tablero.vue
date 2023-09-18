import TableroGrafico from 'src/components/sitio/TableroGrafico.vue';
<template>
  <TableroContador />
  <TableroGrafico />
</template>

<script>
import TableroContador from 'src/components/sitio/TableroContador.vue'
import TableroGrafico from 'src/components/sitio/TableroGrafico.vue'

export default {
  setup () {
  },
  components: { TableroContador, TableroGrafico }
}
</script>
