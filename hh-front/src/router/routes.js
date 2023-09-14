import { rolEnum } from 'src/models/enums/rol_enum'

const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    meta: {
      rol: rolEnum.CARGA
    },
    children: [
      {
        path: 'tablero',
        name: 'Tablero',
        component: () => import('pages/sitio/Tablero.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      }
    ]
  },
  {
    path: '/autenticacion/ingresar',
    name: 'Ingreso',
    component: () => import('pages/autenticacion/Ingreso.vue'),
    meta: {
      rol: null
    }
  },
  {
    path: '/401',
    name: 'Error401',
    component: () => import('src/pages/error/Error401.vue'),
    meta: {
      rol: null
    }
  },
  {
    path: '/404',
    name: 'Error404',
    component: () => import('src/pages/error/Error404.vue'),
    meta: {
      rol: null
    }
  },

  {
    path: '/:catchAll(.*)*',
    component: () => import('src/pages/error/ErrorNotFound.vue'),
    meta: {
      rol: null
    }
  }
]

export default routes
