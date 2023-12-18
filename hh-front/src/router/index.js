import { route } from 'quasar/wrappers'
import { createRouter, createMemoryHistory, createWebHistory, createWebHashHistory } from 'vue-router'
import routes from './routes'
import { autenticacionService } from 'src/services/autenticacion_service'
import { notificarService } from 'src/helpers/notificar_service'

export default route(function (/* { store, ssrContext } */) {
  const createHistory = process.env.SERVER
    ? createMemoryHistory
    : (process.env.VUE_ROUTER_MODE === 'history' ? createWebHistory : createWebHashHistory)

  const Router = createRouter({
    scrollBehavior (to, from, savedPosition) {
      if (to.hash) {
        return document.querySelector(to.hash).scrollIntoView({ behavior: 'smooth' })
      } else {
        return { left: 0, top: 0 }
      }
    },
    routes,
    history: createHistory(process.env.MODE === 'ssr' ? void 0 : process.env.VUE_ROUTER_BASE)
  })

  Router.beforeEach(async (to, from, next) => {
    const autenticado = autenticacionService.fEstaLogueado()
    if (!autenticado && to.name !== 'Logueo') {
      next({ name: 'Logueo' })
    } else if (autenticado) {
      const rolNecesario = to.meta.rol
      const roles = autenticacionService.obtenerAutoridades()
      if (!roles.includes(rolNecesario)) {
        notificarService.infoError('No posee autoridades para ingresar al recurso.')
        return false
      }
    }
    next()
  })

  return Router
})
