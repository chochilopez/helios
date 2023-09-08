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
  return llavero.getFromLocalStorage('lUsuarioTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.getFromLocalStorage('lUsuarioTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.getFromLocalStorage('lUsuarioPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.getFromLocalStorage('lUsuarioPorIdConEliminadas/' + id + '/')
}

function obtenerPorUsername (username) {
  return llavero.getFromLocalStorage('lUsuarioPorNombreUsuario/' + username + '/')
}

function obtenerPorUsernameConBorradas (username) {
  return llavero.getFromLocalStorage('lUsuarioPorNombreUsuarioConEliminadas/' + username + '/')
}

function obtenerCuenta () {
  return llavero.getFromLocalStorage('lUsuarioCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.getFromLocalStorage('lUsuarioCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioTodas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioTodasConEliminadas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioPorId/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorNombreUsuario (username) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-por-nombre-usuario/' + username, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioPorNombreUsuario/' + username + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorNombreUsuarioConEliminadas (username) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-por-nombre-usuario-con-eliminadas/' + username, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioPorNombreUsuarioConEliminadas/' + username + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioContar', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lUsuarioContarConEliminadas', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'usuario', anObj, {
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
    axios.delete(API_URL + 'usuario/' + id, {
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
    axios.post(API_URL + 'usuario/reciclar/' + id, {
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
    axios.delete(API_URL + 'usuario/destruir/' + id, {
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

export const usuarioService = {
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfFetchContarTodas,
  spfFetchContarTodasConEliminadas,

  spfBuscarPorNombreUsuario,
  spfBuscarPorNombreUsuarioConEliminadas,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
