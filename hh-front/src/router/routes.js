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
      },
      {
        path: 'banco',
        name: 'Banco',
        component: () => import('pages/sitio/Banco.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'caja',
        name: 'Caja',
        component: () => import('pages/sitio/Caja.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'calendario',
        name: 'Calendario',
        component: () => import('pages/sitio/Calendario.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'chofer',
        name: 'Chofer',
        component: () => import('pages/sitio/Chofer.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'cliente',
        name: 'Cliente',
        component: () => import('pages/sitio/Cliente.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'compra',
        name: 'Compra',
        component: () => import('pages/sitio/Compra.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'proveedor',
        name: 'Proveedor',
        component: () => import('pages/sitio/Proveedor.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },

      {
        path: 'tablero',
        name: 'Tablero',
        component: () => import('pages/sitio/Tablero.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'vehiculo',
        name: 'Vehiculo',
        component: () => import('pages/sitio/Vehiculo.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: 'viaje',
        name: 'Viaje',
        component: () => import('pages/sitio/Viaje.vue'),
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
