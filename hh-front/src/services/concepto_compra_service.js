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

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'concepto-compra/buscar-todas', {
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
    axios.get(API_URL + 'concepto-compra/buscar-todas-con-eliminadas', {
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

function spfBuscarTodasConSesion (sesion) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'concepto-compra/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        if (result.status === 200) {
          llaveroService.guardarEnLocalConSesion('hhCajaTodasConSesion', result.data, sesion)
        }
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadasConSesion (sesion) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'concepto-compra/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        if (result.status === 200) {
          llaveroService.guardarEnLocalConSesion('hhCajaTodasConEliminadasConSesion', result.data, sesion)
        }
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'concepto-compra/buscar-todas-paginadas', paginadoDTO, {
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
    axios.post(API_URL + 'concepto-compra/buscar-todas-con-eliminadas-paginadas', paginadoDTO, {
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

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'concepto-compra/buscar-por-id/' + id, {
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
    axios.get(API_URL + 'concepto-compra/buscar-por-id-con-eliminadas/' + id, {
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
    axios.get(API_URL + 'concepto-compra/contar-todas', {
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
    axios.get(API_URL + 'concepto-compra/contar-todas-con-eliminadas', {
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
    axios.put(API_URL + 'concepto-compra', anObj, {
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
    axios.delete(API_URL + 'concepto-compra/' + id, {
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
    axios.get(API_URL + 'concepto-compra/reciclar/' + id, {
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
    axios.delete(API_URL + 'concepto-compra/destruir/' + id, {
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

export const conceptoCompraService = {
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarTodasConSesion,
  spfBuscarTodasConEliminadasConSesion,
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