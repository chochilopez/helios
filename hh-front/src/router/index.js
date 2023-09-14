import { route } from 'quasar/wrappers'
import { createRouter, createMemoryHistory, createWebHistory, createWebHashHistory } from 'vue-router'
import routes from './routes'
import { autenticacionService } from 'src/services/autenticacion_service'

/*
 * If not building with SSR mode, you can
 * directly export the Router instantiation;
 *
 * The function below can be async too; either use
 * async/await or return a Promise which resolves
 * with the Router instance.
 */

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
    console.log('Ruta ' + to.name)
    if (to.name !== 'Ingreso' && !autenticacionService.fEstaLogueado()) {
      next({ name: 'Ingreso' })
    } else {
      next()
    }
    // const authorize = to.meta.authority
    // const authority = autenticacionService.obtenerAutoridades()
    // if (!authority.includes(authorize)) {
    //   next({ name: 'Login' })
    // } else {
    //   next()
    // }
  })

  return Router
})
