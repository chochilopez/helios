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
  return llavero.obtenerDeLocal('hhUsuarioTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.obtenerDeLocal('hhUsuarioTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.obtenerDeLocal('hhUsuarioPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.obtenerDeLocal('hhUsuarioPorIdConEliminadas/' + id + '/')
}

function obtenerPorUsername (username) {
  return llavero.obtenerDeLocal('hhUsuarioPorNombreUsuario/' + username + '/')
}

function obtenerPorUsernameConBorradas (username) {
  return llavero.obtenerDeLocal('hhUsuarioPorNombreUsuarioConEliminadas/' + username + '/')
}

function obtenerCuenta () {
  return llavero.obtenerDeLocal('hhUsuarioCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.obtenerDeLocal('hhUsuarioCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'usuario/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioTodas', result, ttlEnum.TTL_1_HOUR)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioTodasConEliminadas', result, ttlEnum.TTL_1_HOUR)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioPorId/' + id + '/', result, ttlEnum.TTL_1_HOUR)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HOUR)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioPorNombreUsuario/' + username + '/', result, ttlEnum.TTL_1_HOUR)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioPorNombreUsuarioConEliminadas/' + username + '/', result, ttlEnum.TTL_1_HOUR)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioContar', result, ttlEnum.TTL_1_DAY)
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
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('hhUsuarioContarConEliminadas', result, ttlEnum.TTL_1_DAY)
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
    axios.delete(API_URL + 'usuario/' + id, {
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
    axios.post(API_URL + 'usuario/reciclar/' + id, {
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
    axios.delete(API_URL + 'usuario/destruir/' + id, {
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
