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
  return llavero.getFromLocalStorage('lArchivoTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.getFromLocalStorage('lArchivoTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.getFromLocalStorage('lArchivoPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.getFromLocalStorage('lArchivoPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.getFromLocalStorage('lArchivoCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.getFromLocalStorage('lArchivoCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'archivo/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lArchivoTodas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'archivo/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lArchivoTodasConEliminadas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'archivo/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lArchivoPorId/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'archivo/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lArchivoPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'archivo/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lArchivoContar', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'archivo/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lArchivoContarConEliminadas', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardarArchivo (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'archivo/guardar', anObj.file, anObj.tipo, anObj.tamanio, anObj.descripcion, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
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
    axios.put(API_URL + 'archivo', anObj, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
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
    axios.delete(API_URL + 'archivo/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
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
    axios.post(API_URL + 'archivo/reciclar/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
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
    axios.delete(API_URL + 'archivo/destruir/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
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

export const archivoService = {
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfFetchContarTodas,
  spfFetchContarTodasConEliminadas,

  spfGuardarArchivo,
  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
