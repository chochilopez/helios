import axios from 'axios'
const API_URL = process.env.API_URL
import { autenticacionService } from 'src/services/autenticacion_service'
import { llaveroService } from 'src/helpers/llavero_service'

/*
s -> servicio
p -> promesa
f -> funcion
l -> local
*/

function obtenerTodas () {
  return llaveroService.obtenerDeLocal('hhPresupuestoTodas')
}

function obtenerTodasConEliminadas () {
  return llaveroService.obtenerDeLocal('hhPresupuestoTodasConEliminadas')
}

function obtenerPorId (id) {
  return llaveroService.obtenerDeLocal('hhPresupuestoPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llaveroService.obtenerDeLocal('hhPresupuestoPorIdConEliminadas/' + id + '/')
}

function spfBuscarTodasPorCategoriaViajeId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-categoria-viaje-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorCategoriaViajeIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-categoria-viaje-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorCompradorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-comprador-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorCompradorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-comprador-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorDireccionCargaId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-carga-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorDireccionCargaIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-carga-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorDireccionDestinoId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-destino-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorDireccionDestinoIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-destino-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorDireccionOrigenId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-origen-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorDireccionOrigenIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-origen-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorFechaViajeEntreFechas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-fecha-entre-fechas/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorFechaViajeEntreFechasConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-fecha-entre-fechas-con-eliminadas/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoCantidadTransportada (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-cantidad-transportada/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoCantidadTransportadaConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-cantidad-transportada-con-eliminadas/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoKmCargado (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-km-cargado/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoKmCargadoConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-km-cargado-con-eliminadas/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoKmVacio (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-km-vacio/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoKmVacioConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-km-vacio-con-eliminadas/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorNotas (notas) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-notas/' + notas, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorNotasConEliminadas (notas) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-notas-con-eliminadas/' + notas, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoValorKm (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-valor-km/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorRangoValorKmConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-rango-valor-km-con-eliminadas/' + inicio + '/' + fin, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorVendedorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-vendedor-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPorVendedorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-por-vendedor-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'presupuesto/buscar-todas-paginadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'presupuesto/buscar-todas-con-eliminadas-paginadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'presupuesto/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'presupuesto', anObj, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBorrar (id) {
  return new Promise((resolve, reject) => {
    axios.delete(API_URL + 'presupuesto/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfReciclar (id) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'presupuesto/reciclar/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfDestruir (id) {
  return new Promise((resolve, reject) => {
    axios.delete(API_URL + 'presupuesto/destruir/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

export const presupuestoService = {
  obtenerTodas,
  obtenerTodasConEliminadas,
  obtenerPorId,
  obtenerPorIdConEliminadas,

  spfBuscarTodasPorCategoriaViajeId,
  spfBuscarTodasPorCategoriaViajeIdConEliminadas,
  spfBuscarTodasPorCompradorId,
  spfBuscarTodasPorCompradorIdConEliminadas,
  spfBuscarTodasPorDireccionCargaId,
  spfBuscarTodasPorDireccionCargaIdConEliminadas,
  spfBuscarTodasPorDireccionDestinoId,
  spfBuscarTodasPorDireccionDestinoIdConEliminadas,
  spfBuscarTodasPorDireccionOrigenId,
  spfBuscarTodasPorDireccionOrigenIdConEliminadas,
  spfBuscarTodasPorFechaViajeEntreFechas,
  spfBuscarTodasPorFechaViajeEntreFechasConEliminadas,
  spfBuscarTodasPorNotas,
  spfBuscarTodasPorNotasConEliminadas,
  spfBuscarTodasPorRangoCantidadTransportada,
  spfBuscarTodasPorRangoCantidadTransportadaConEliminadas,
  spfBuscarTodasPorRangoKmCargado,
  spfBuscarTodasPorRangoKmCargadoConEliminadas,
  spfBuscarTodasPorRangoKmVacio,
  spfBuscarTodasPorRangoKmVacioConEliminadas,
  spfBuscarTodasPorRangoValorKm,
  spfBuscarTodasPorRangoValorKmConEliminadas,
  spfBuscarTodasPorVendedorId,
  spfBuscarTodasPorVendedorIdConEliminadas,

  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarTodasPaginadas,
  spfBuscarTodasConEliminadasPaginadas,
  spfContarTodas,
  spfContarTodasConEliminadas,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
