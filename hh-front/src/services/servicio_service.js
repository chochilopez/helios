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
  return llavero.obtenerDeLocal('lServicioTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.obtenerDeLocal('lServicioTodasConEliminadas')
}

function obtenerPorId (id) {
  return llavero.obtenerDeLocal('lServicioPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.obtenerDeLocal('lServicioPorIdConEliminadas/' + id + '/')
}

function obtenerCuenta () {
  return llavero.obtenerDeLocal('lServicioCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.obtenerDeLocal('lServicioCuentaConEliminadas')
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/buscar-todas-paginadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioTodas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/buscar-todas-paginadas-con-eliminadas', paginadoDTO, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioTodasConEliminadas', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioPorId/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HORA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioContar', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'servicio/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        llavero.guardarEnLocal('lServicioContarConEliminadas', result, ttlEnum.TTL_1_DIA)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'servicio', anObj, {
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
    axios.delete(API_URL + 'servicio/' + id, {
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
    axios.post(API_URL + 'servicio/reciclar/' + id, {
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
    axios.delete(API_URL + 'servicio/destruir/' + id, {
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

export const servicioService = {
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
