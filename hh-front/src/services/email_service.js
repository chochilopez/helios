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
  return llavero.getFromLocalStorage('lEmailTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.getFromLocalStorage('lEmailTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.getFromLocalStorage('lEmailPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.getFromLocalStorage('lEmailPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.getFromLocalStorage('lEmailCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.getFromLocalStorage('lEmailCuentaConEliminadas')
}

function pfEnviarEmailSimple (emailSimple) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'email/simple', emailSimple, {
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

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'email/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lEmailTodas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'email/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lEmailTodasConEliminadas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'email/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lEmailPorId/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'email/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lEmailPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'email/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lEmailContar', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'email/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lEmailContarConEliminadas', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'email', anObj, {
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
    axios.delete(API_URL + 'email/' + id, {
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
    axios.post(API_URL + 'email/reciclar/' + id, {
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
    axios.delete(API_URL + 'email/destruir/' + id, {
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

export const emailService = {
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfContarTodas,
  spfContarTodasConEliminadas,

  pfEnviarEmailSimple,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
