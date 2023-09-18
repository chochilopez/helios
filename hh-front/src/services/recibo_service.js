import axios from 'axios'
const API_URL = process.env.API_URL
import { autenticacionService } from 'src/services/autenticacion_service'
import { llavero } from 'src/helpers/llavero'
import { ttlEnum } from 'src/models/enums/ttl_enum'

/*
s -> servicio
p -> promesa
f -> funcion
l -> local
*/

function obtenerTodas () {
  return llavero.obtenerDeLocal('lReciboTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.obtenerDeLocal('lReciboTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.obtenerDeLocal('lReciboPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.obtenerDeLocal('lReciboPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.obtenerDeLocal('lReciboCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.obtenerDeLocal('lReciboCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/buscar-todas-paginadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/buscar-todas-paginadas-con-eliminadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboPorId/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboContar', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'recibo/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lReciboContarConEliminadas', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'recibo', anObj, {
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
    axios.delete(API_URL + 'recibo/' + id, {
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
    axios.post(API_URL + 'recibo/reciclar/' + id, {
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
    axios.delete(API_URL + 'recibo/destruir/' + id, {
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

export const reciboService = {
  obtenerTodas,
  obtenerTodasConEliminadas,
  obtenerPorId,
  obtenerPorIdConEliminadas,
  obtenerCuenta,
  obtenerCuentaConEliminadas,

  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarTodasPaginadas,
  spfBuscarTodasConEliminadasPaginadas,
  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfContarTodas,
  spfContarTodasConEliminadas,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
