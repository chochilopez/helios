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
  return llavero.obtenerDeLocal('lDirecccionTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.obtenerDeLocal('lDirecccionTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.obtenerDeLocal('lDirecccionPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.obtenerDeLocal('lDirecccionPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.obtenerDeLocal('lDirecccionCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.obtenerDeLocal('lDirecccionCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/buscar-todas-paginadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/buscar-todas-paginadas-con-eliminadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionPorId/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionContar', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'direcccion/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lDirecccionContarConEliminadas', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'direcccion', anObj, {
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
    axios.delete(API_URL + 'direcccion/' + id, {
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
    axios.post(API_URL + 'direcccion/reciclar/' + id, {
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
    axios.delete(API_URL + 'direcccion/destruir/' + id, {
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

export const direcccionService = {
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
