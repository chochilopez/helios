import { rolEnum } from 'src/models/enums/rol_enum'

const routes = [
  {
    path: '',
    component: () => import('layouts/MainLayout.vue'),
    meta: {
      rol: rolEnum.CARGA
    },
    children: [
      {
        path: '',
        name: 'Tablero',
        component: () => import('pages/sitio/Tablero.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/acoplado',
        name: 'Acoplado',
        component: () => import('pages/sitio/vehiculo/Acoplado.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/adelanto',
        name: 'Adelanto',
        component: () => import('pages/sitio/conductor/Adelanto.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/banco',
        name: 'Banco',
        component: () => import('pages/sitio/flujo/Banco.vue'),
        meta: {
          rol: rolEnum.ADMIN
        }
      },
      {
        path: '/caja',
        name: 'Caja',
        component: () => import('pages/sitio/flujo/Caja.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/calendario',
        name: 'Calendario',
        component: () => import('pages/sitio/calendario/Calendario.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/camion',
        name: 'Camion',
        component: () => import('pages/sitio/vehiculo/Camion.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/categoria-viaje',
        name: 'CategoriaViaje',
        component: () => import('pages/sitio/viaje/CategoriaViaje.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/conductor',
        name: 'Conductor',
        component: () => import('pages/sitio/conductor/Conductor.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/cliente',
        name: 'Cliente',
        component: () => import('src/pages/sitio/cliente/Cliente.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/combustible',
        name: 'Combustible',
        component: () => import('src/pages/sitio/conductor/Combustible.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/compra',
        name: 'Compra',
        component: () => import('pages/sitio/proveedor/Compra.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/conciliacion',
        name: 'Conciliacion',
        component: () => import('pages/sitio/flujo/Conciliacion.vue'),
        meta: {
          rol: rolEnum.ADMIN
        }
      },
      {
        path: '/cuenta-corriente',
        name: 'CuentaCorriente',
        component: () => import('pages/sitio/cliente/CuentaCorriente.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/detalle-cuenta-corriente',
        name: 'DetalleCtaCte',
        component: () => import('pages/sitio/cliente/DetalleCtaCte.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/direccion',
        name: 'Direccion',
        component: () => import('pages/sitio/cliente/Direccion.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/facturacion',
        name: 'Facturacion',
        component: () => import('pages/sitio/facturacion/Facturacion.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/licencia',
        name: 'Licencia',
        component: () => import('pages/sitio/conductor/Licencia.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/movimiento-cuenta-corriente-proveedor',
        name: 'MovCtaCteProveedor',
        component: () => import('pages/sitio/proveedor/MovCtaCteProveedor.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/movimiento-caja',
        name: 'MovimientoCaja',
        component: () => import('pages/sitio/flujo/MovimientoCaja.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/movimiento-cuenta-corriente',
        name: 'MovimientoCtaCte',
        component: () => import('pages/sitio/cliente/MovimientoCtaCte.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/neumatico',
        name: 'Neumatico',
        component: () => import('pages/sitio/vehiculo/Neumatico.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/plan-cuenta',
        name: 'PlanCuenta',
        component: () => import('pages/sitio/flujo/PlanCuenta.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/presupuesto',
        name: 'Presupuesto',
        component: () => import('pages/sitio/viaje/Presupuesto.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/proveedor',
        name: 'Proveedor',
        component: () => import('pages/sitio/proveedor/Proveedor.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/recibo',
        name: 'Recibo',
        component: () => import('pages/sitio/facturacion/Recibo.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/remito',
        name: 'Remito',
        component: () => import('pages/sitio/facturacion/Remito.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/resumen-conductor',
        name: 'ResumenConductor',
        component: () => import('pages/sitio/conductor/ResumenConductor.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/seguro',
        name: 'Seguro',
        component: () => import('pages/sitio/vehiculo/Seguro.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/tarea',
        name: 'Tarea',
        component: () => import('pages/sitio/calendario/Tarea.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/vencimiento',
        name: 'Vencimiento',
        component: () => import('pages/sitio/calendario/Vencimiento.vue'),
        meta: {
          rol: rolEnum.USUARIO
        }
      },
      {
        path: '/viaje',
        name: 'Viaje',
        component: () => import('pages/sitio/viaje/Viaje.vue'),
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
