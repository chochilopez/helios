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
  return llavero.obtenerDeLocal('lSeguroTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.obtenerDeLocal('lSeguroTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.obtenerDeLocal('lSeguroPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.obtenerDeLocal('lSeguroPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.obtenerDeLocal('lSeguroCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.obtenerDeLocal('lSeguroCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/buscar-todas-paginadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/buscar-todas-paginadas-con-eliminadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroPorId/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroContar', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'seguro/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lSeguroContarConEliminadas', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'seguro', anObj, {
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
    axios.delete(API_URL + 'seguro/' + id, {
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
    axios.post(API_URL + 'seguro/reciclar/' + id, {
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
    axios.delete(API_URL + 'seguro/destruir/' + id, {
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

export const seguroService = {
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
