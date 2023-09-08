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
  return llavero.getFromLocalStorage('lRolTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.getFromLocalStorage('lRolTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.getFromLocalStorage('lRolPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.getFromLocalStorage('lRolPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.getFromLocalStorage('lRolCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.getFromLocalStorage('lRolCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'rol/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lRolTodas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'rol/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lRolTodasConEliminadas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'rol/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lRolPorId/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'rol/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lRolPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'rol/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lRolContar', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'rol/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lRolContarConEliminadas', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'rol', anObj, {
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
    axios.delete(API_URL + 'rol/' + id, {
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
    axios.post(API_URL + 'rol/reciclar/' + id, {
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
    axios.delete(API_URL + 'rol/destruir/' + id, {
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

export const rolService = {
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfFetchContarTodas,
  spfFetchContarTodasConEliminadas,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
