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
        component: () => import('pages/sitio/Acoplado.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/adelanto',
        name: 'Adelanto',
        component: () => import('pages/sitio/Adelanto.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/agenda-viaje',
        name: 'AgendaViaje',
        component: () => import('pages/sitio/AgendaViaje.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/banco',
        name: 'Banco',
        component: () => import('pages/sitio/Banco.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/caja',
        name: 'Caja',
        component: () => import('pages/sitio/Caja.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/calendario',
        name: 'Calendario',
        component: () => import('pages/sitio/Calendario.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/camion',
        name: 'Camion',
        component: () => import('pages/sitio/Camion.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/categoria-viaje',
        name: 'CategoriaViaje',
        component: () => import('pages/sitio/CategoriaViaje.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/chofer',
        name: 'Chofer',
        component: () => import('pages/sitio/Chofer.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/cliente',
        name: 'Cliente',
        component: () => import('src/pages/sitio/Cliente.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/colocado',
        name: 'Colocado',
        component: () => import('pages/sitio/Colocado.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/combustible',
        name: 'Combustible',
        component: () => import('src/pages/sitio/Combustible.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      // {
      //   path: '/compra',
      //   name: 'Compra',
      //   component: () => import('pages/sitio/Compra.vue'),
      //   meta: {
      //     rol: rolEnum.CARGA
      //   }
      // },
      {
        path: '/concepto-adelanto',
        name: 'ConceptoAdelanto',
        component: () => import('pages/sitio/ConceptoAdelanto.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      // {
      //   path: '/concepto-compra',
      //   name: 'ConceptoCompra',
      //   component: () => import('pages/sitio/ConceptoCompra.vue'),
      //   meta: {
      //     rol: rolEnum.CARGA
      //   }
      // },
      {
        path: '/conciliacion',
        name: 'Conciliacion',
        component: () => import('pages/sitio/Conciliacion.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/detalle-cuenta-corriente',
        name: 'DetalleCtaCte',
        component: () => import('pages/sitio/DetalleCtaCte.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/direccion',
        name: 'Direccion',
        component: () => import('pages/sitio/Direccion.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/facturacion',
        name: 'Facturacion',
        component: () => import('pages/sitio/Facturacion.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      // {
      //   path: '/facturar',
      //   name: 'Facturar',
      //   component: () => import('pages/sitio/Facturar.vue'),
      //   meta: {
      //     rol: rolEnum.CARGA
      //   }
      // },
      {
        path: '/marca',
        name: 'Marca',
        component: () => import('pages/sitio/Marca.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      // {
      //   path: '/movimiento-cuenta-corriente-proveedor',
      //   name: 'MovCtaCteProveedor',
      //   component: () => import('pages/sitio/MovCtaCteProveedor.vue'),
      //   meta: {
      //     rol: rolEnum.CARGA
      //   }
      // },
      {
        path: '/movimiento-caja',
        name: 'MovimientoCaja',
        component: () => import('pages/sitio/MovimientoCaja.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/movimiento-cuenta-corriente',
        name: 'MovimientoCtaCte',
        component: () => import('pages/sitio/MovimientoCtaCte.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/neumatico',
        name: 'Neumatico',
        component: () => import('pages/sitio/Neumatico.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/plan-cuenta',
        name: 'PlanCuenta',
        component: () => import('pages/sitio/PlanCuenta.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/presupuesto',
        name: 'Presupuesto',
        component: () => import('pages/sitio/Presupuesto.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/proveedor',
        name: 'Proveedor',
        component: () => import('pages/sitio/Proveedor.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/recibo',
        name: 'Recibo',
        component: () => import('pages/sitio/Recibo.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/resumen-chofer',
        name: 'ResumenChofer',
        component: () => import('pages/sitio/ResumenChofer.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/saldo-cuenta-corriente',
        name: 'SaldoCtaCte',
        component: () => import('pages/sitio/SaldoCtaCte.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/seguro',
        name: 'Seguro',
        component: () => import('pages/sitio/Seguro.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/tarea',
        name: 'Tarea',
        component: () => import('pages/sitio/Tarea.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/vehiculo',
        name: 'Vehiculo',
        component: () => import('pages/sitio/Vehiculo.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/vencimiento',
        name: 'Vencimiento',
        component: () => import('pages/sitio/Vencimiento.vue'),
        meta: {
          rol: rolEnum.CARGA
        }
      },
      {
        path: '/viaje',
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
