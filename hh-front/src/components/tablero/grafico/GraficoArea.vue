<template>
  <div class="q-pa-xl">
    <apexchart width="100%" type="bar" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from 'vue3-apexcharts'

export default {
  setup () {
    return {
      chartOptions: {
        chart: {
          id: 'vuechart-example'
        },
        xaxis: {
          categories: [2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028]
        },
        title: {
          text: 'Gráfico 2'
        }
      },
      series: [
        {
          name: 'series-1',
          data: [30, 40, 35, 50, 49, 60, 70, 91]
        }
      ]
    }
  },
  components: {
    apexchart: VueApexCharts
  }

}
</script>
