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
        path: '',
        component: () => import('pages/IndexPage.vue'),
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
    component: () => import('pages/Error401.vue'),
    meta: {
      rol: null
    }
  },

  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/ErrorNotFound.vue'),
    meta: {
      rol: null
    }
  }
]

export default routes
