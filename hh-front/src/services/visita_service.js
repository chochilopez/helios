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
  return llavero.getFromLocalStorage('lVisitaTodas')
}

function obtenerTodasConEliminadas () {
  return llavero.getFromLocalStorage('lVisitaTodasConEliminadas')
}

function obtenerTodasPorIp (ip) {
  return llavero.getFromLocalStorage('lVisitAllByIp/' + ip + '/')
}

function obtenerTodasPorIpConEliminadas (ip) {
  return llavero.getFromLocalStorage('lVisitAllByIpConEliminadas/' + ip + '/')
}

function obtenerPorId (id) {
  return llavero.getFromLocalStorage('lVisitaPorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llavero.getFromLocalStorage('lVisitaPorIdConEliminadas/' + id + '/')
}

function obtenerTop100 () {
  return llavero.getFromLocalStorage('lTop100Visitas')
}

function obtenerTop100ConEliminadas () {
  return llavero.getFromLocalStorage('lTop100VisitasConEliminadas')
}

function obtenerVisitante () {
  return llavero.getFromLocalStorage('lVisitante')
}

function obtenerCuenta () {
  return llavero.getFromLocalStorage('lVisitaCuenta')
}

function obtenerCuentaConEliminadas () {
  return llavero.getFromLocalStorage('lVisitaCuentaConEliminadas')
}

async function spfBuscarVisitante () {
  if (llavero.getFromLocalStorage('lVisitante')) {
    console.info('Data reloaded: ' + llavero.getFromLocalStorage('lVisitante').value.headers.message)
  } else {
    try {
      const result = await axios.get('https://api.ipgeolocation.io/ipgeo?apiKey=d66d5dac832c4f2f839fb72f7fc308db&lang=es')
      if (result.status === 200) {
        const resultVisitor = await axios.put(API_URL + 'visit', result.data, {
          headers: {
            Authorization: 'Bearer ' + autenticacionService.getToken()
          }
        })
        if (resultVisitor.status === 201) {
          console.log(resultVisitor.headers.message)
          llavero.setToLocalStorage('lVisitVisitor', resultVisitor, ttlEnum.TTL_1_DAY)
          return resultVisitor.data
        } else if (resultVisitor.status === 202) {
          console.warn(resultVisitor.headers.message)
        } else if (resultVisitor.status === 204) {
          console.error(resultVisitor.headers.message)
        }
        return null
      }
      return null
    } catch (err) {
      console.error('Fatal error: ' + err.message)
      return null
    }
  }
}

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'visita/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lVisitaTodas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'visita/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lVisitaTodasConEliminadas', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'visita/buscar-por-id/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lVisitaPorId/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfBuscarPorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'visita/buscar-por-id-con-eliminadas/' + id, {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lVisitaPorIdConEliminadas/' + id + '/', result, ttlEnum.TTL_1_HOUR)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'visita/contar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lVisitaContar', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfFetchContarTodasConEliminadas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'visita/contar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.getToken()
      }
    })
      .then((result) => {
        llavero.setToLocalStorage('lVisitaContarConEliminadas', result, ttlEnum.TTL_1_DAY)
        resolve(result)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfGuardar (anObj) {
  return new Promise((resolve, reject) => {
    axios.put(API_URL + 'visita', anObj, {
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
    axios.delete(API_URL + 'visita/' + id, {
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
    axios.post(API_URL + 'visita/reciclar/' + id, {
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
    axios.delete(API_URL + 'visita/destruir/' + id, {
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

export const visitaService = {

  spfBuscarVisitante,

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
