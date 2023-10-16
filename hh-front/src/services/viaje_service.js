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
  return llaveroService.obtenerDeLocal('hhViajeTodas')
}

function obtenerTodasConEliminadas () {
  return llaveroService.obtenerDeLocal('hhViajeTodasConEliminadas')
}

function obtenerPorId (id) {
  return llaveroService.obtenerDeLocal('hhViajePorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llaveroService.obtenerDeLocal('hhViajePorIdConEliminadas/' + id + '/')
}

function spfBuscarTodasPorCamionId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-camion-id/' + id, {
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

function spfBuscarTodasPorCamionIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-camion-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorChoferId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-chofer-id/' + id, {
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

function spfBuscarTodasPorChoferIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-chofer-id-con-eliminadas/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-todas-por-comprador-id/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-todas-por-comprador-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorDestinoId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-destino-id/' + id, {
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

function spfBuscarTodasPorDestinoIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-destino-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorOrigenId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-origen-id/' + id, {
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

function spfBuscarTodasPorOrigenIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-origen-id-con-eliminadas/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-todas-por-vendedor-id/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-todas-por-vendedor-id-con-eliminadas/' + id, {
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

function spfBuscarUltimoViaje () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-ultimo', {
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

function spfSumarCantidadTransportada () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/sumar-cantidad-transportada', {
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

function spfSumarKmCargado () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/sumar-km-cargado', {
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

function spfSumarKmVacio () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/sumar-km-vacio', {
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

/* FIN métodos especificos */

/* INICIO métodos generales */

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-por-id/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-por-id-con-eliminadas/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-todas', {
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
    axios.get(API_URL + 'viaje/buscar-todas-con-eliminadas', {
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
    axios.post(API_URL + 'viaje/buscar-todas-paginadas', paginadoDTO, {
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
    axios.post(API_URL + 'viaje/buscar-todas-con-eliminadas-paginadas', paginadoDTO, {
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
    axios.get(API_URL + 'viaje/contar-todas', {
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
    axios.get(API_URL + 'viaje/contar-todas-con-eliminadas', {
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
    axios.put(API_URL + 'viaje', anObj, {
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
    axios.delete(API_URL + 'viaje/' + id, {
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
    axios.post(API_URL + 'viaje/reciclar/' + id, {
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
    axios.delete(API_URL + 'viaje/destruir/' + id, {
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

/* FIN métodos generales */

export const viajeService = {
  obtenerTodas,
  obtenerTodasConEliminadas,
  obtenerPorId,
  obtenerPorIdConEliminadas,

  spfBuscarTodasPorCamionId,
  spfBuscarTodasPorCamionIdConEliminadas,
  spfBuscarTodasPorChoferId,
  spfBuscarTodasPorChoferIdConEliminadas,
  spfBuscarTodasPorCompradorId,
  spfBuscarTodasPorCompradorIdConEliminadas,
  spfBuscarTodasPorDestinoId,
  spfBuscarTodasPorDestinoIdConEliminadas,
  spfBuscarTodasPorOrigenId,
  spfBuscarTodasPorOrigenIdConEliminadas,
  spfBuscarTodasPorVendedorId,
  spfBuscarTodasPorVendedorIdConEliminadas,

  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarTodasPaginadas,
  spfBuscarTodasConEliminadasPaginadas,
  spfBuscarUltimoViaje,
  spfContarTodas,
  spfContarTodasConEliminadas,
  spfSumarCantidadTransportada,
  spfSumarKmCargado,
  spfSumarKmVacio,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
