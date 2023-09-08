<template>
  <div class="fullscreen bg-blue text-white text-center q-pa-md flex flex-center fuente10">
    <div>
      <div class="text-h1 q-pb-xl">
        En reparación
      </div>

      <div class="text-h2" style="opacity:.4">
        Lo sentimos, estamos experimentando problemas técnicos
      </div>

      <q-btn
        class="q-mt-xl"
        color="white"
        text-color="blue"
        unelevated
        to="/"
        label="Ir a Inicio"
        no-caps
      />
    </div>
  </div>
</template>

<script>
import { useMeta } from 'quasar'
export default ({
  setup () {
    useMeta(() => {
      return {
        title: 'Error',

        meta: {
          // Meta genericos
          googlebot: { name: 'googlebot', content: 'noindex, nofollow' }
        }
      }
    })
  }
})
</script>
