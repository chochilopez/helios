<template>
  <q-card class="bg-transparent no-shadow no-border" bordered>
    <q-card-section class="q-pa-none">
      <div class="row q-col-gutter-sm ">
        <div class="col-md-4 col-sm-12 col-xs-12">
          <q-item class="q-pa-none">
            <apexchart width="500" type="bar" :options="chartOptions" :series="series"></apexchart>
          </q-item>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12">
          <q-item class="q-pa-none">
          </q-item>
        </div>
        <div class="col-md-4 col-sm-12 col-xs-12">
          <q-item class="q-pa-none">
          </q-item>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import VueApexCharts from 'vue3-apexcharts'

export default {
  data: function () {
    return {
      chartOptions: {
        chart: {
          id: 'vuechart-example'
        },
        xaxis: {
          categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
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
