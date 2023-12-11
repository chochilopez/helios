<template>
  <div class="q-pa-xl">
    <apexchart type="treemap" height="350" :options="chartOptions" :series="series"></apexchart>
  </div>
</template>

<script>
import VueApexCharts from 'vue3-apexcharts'

export default {
  setup () {
    return {
      series: [
        {
          data: [
            {
              x: 'New Delhi',
              y: 218
            },
            {
              x: 'Kolkata',
              y: 149
            },
            {
              x: 'Mumbai',
              y: 184
            },
            {
              x: 'Ahmedabad',
              y: 55
            },
            {
              x: 'Bangaluru',
              y: 84
            },
            {
              x: 'Pune',
              y: 31
            },
            {
              x: 'Chennai',
              y: 70
            },
            {
              x: 'Jaipur',
              y: 30
            },
            {
              x: 'Surat',
              y: 44
            },
            {
              x: 'Hyderabad',
              y: 68
            },
            {
              x: 'Lucknow',
              y: 28
            },
            {
              x: 'Indore',
              y: 19
            },
            {
              x: 'Kanpur',
              y: 29
            }
          ]
        }
      ],
      chartOptions: {
        legend: {
          show: false
        },
        chart: {
          height: 350,
          type: 'treemap'
        },
        title: {
          text: 'Gráfico 4'
        }
      }
    }
  },
  components: {
    apexchart: VueApexCharts
  }

}
</script>
