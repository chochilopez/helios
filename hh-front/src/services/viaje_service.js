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

function obtenerTodas () {
  return llaveroService.obtenerDeLocal('hhViajeTodas')
}

function obtenerTodasConEliminadas () {
  return llaveroService.obtenerDeLocal('hhViajeTodasConEliminadas')
}

function obtenerPorId (id) {
  return llaveroService.obtenerDeLocal('hhViajePorId/' + id + '/')
}

function obtenerPorIdConEliminadas (id) {
  return llaveroService.obtenerDeLocal('hhViajePorIdConEliminadas/' + id + '/')
}

function spfBuscarTodasPorAcopladoId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-acoplado-id/' + id, {
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

function spfBuscarTodasPorAcopladoIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-acoplado-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorCamionId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-camion-id/' + id, {
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

function spfBuscarTodasPorCamionIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-camion-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorCamionIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-camion-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorCamionIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-camion-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorCategoriaViajeId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-categoria-viaje-id/' + id, {
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

function spfBuscarTodasPorCategoriaViajeIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-categoria-viaje-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorConductorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-conductor-id/' + id, {
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

function spfBuscarTodasPorConductorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-conductor-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorConductorIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-conductor-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorConductorIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-conductor-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorCompradorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-comprador-id/' + id, {
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

function spfBuscarTodasPorCompradorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-comprador-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorCompradorIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-comprador-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorCompradorIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-comprador-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorDireccionCargaId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-carga-id/' + id, {
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

function spfBuscarTodasPorDireccionCargaIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-carga-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorDireccionCargaIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-carga-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorDireccionCargaIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-carga-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorDireccionDestinoId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-destino-id/' + id, {
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

function spfBuscarTodasPorDireccionDestinoIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-destino-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorDireccionDestinoIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-destino-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorDireccionDestinoIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-destino-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorDireccionOrigenId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-origen-id/' + id, {
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

function spfBuscarTodasPorDireccionOrigenIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-origen-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorDireccionOrigenIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-origen-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorDireccionOrigenIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-origen-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorFechaViajeEntreFechas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-fecha-entre-fechas/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorFechaViajeEntreFechasConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-fecha-entre-fechas-con-eliminadas/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoKmCargado (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-km-cargado/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoKmCargadoConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-km-cargado-con-eliminadas/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoKmVacio (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-km-vacio/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoKmVacioConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-km-vacio-con-eliminadas/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorNotas (notas) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-notas/' + notas, {
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

function spfBuscarTodasPorNotasConEliminadas (notas) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-notas-con-eliminadas/' + notas, {
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

function spfBuscarTodasPorGuia (guia) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-guia/' + guia, {
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

function spfBuscarTodasPorGuiaConEliminadas (guia) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-guia-con-eliminadas/' + guia, {
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

function spfBuscarTodasPorRangoNeto (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-neto/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoNetoConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-neto-con-eliminadas/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoValorKm (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-valor-km/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoValorKmConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-valor-km-con-eliminadas/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorIntermediarioId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-intermediario-id/' + id, {
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

function spfBuscarTodasPorIntermediarioIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-intermediario-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorIntermediarioIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-intermediario-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorIntermediarioIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-intermediario-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorVendedorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-vendedor-id/' + id, {
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

function spfBuscarTodasPorVendedorIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-vendedor-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorVendedorIdEntreFechas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-vendedor-id/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorVendedorIdEntreFechasConEliminadas (id, inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-vendedor-id-con-eliminadas/' + id + '/' + inicio + '/' + fin, {
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

function spfBuscarUltimoViaje () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-ultimo', {
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

function spfSumarCantidadTransportada () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/sumar-cantidad-transportada', {
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

function spfSumarKmCargado () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/sumar-km-cargado', {
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

function spfSumarKmVacio () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/sumar-km-vacio', {
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

/* FIN métodos especificos */

/* INICIO métodos generales */

function spfBuscarPorId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-por-id/' + id, {
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
    axios.get(API_URL + 'viaje/buscar-por-id-con-eliminadas/' + id, {
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

function spfBuscarTodas () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas', {
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
    axios.get(API_URL + 'viaje/buscar-todas-con-eliminadas', {
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

function spfBuscarTodasPaginadas (paginadoDTO) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'viaje/buscar-todas-paginadas', paginadoDTO, {
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
    axios.post(API_URL + 'viaje/buscar-todas-con-eliminadas-paginadas', paginadoDTO, {
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
    axios.get(API_URL + 'viaje/contar-todas', {
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
    axios.get(API_URL + 'viaje/contar-todas-con-eliminadas', {
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
    axios.put(API_URL + 'viaje', anObj, {
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
    axios.delete(API_URL + 'viaje/' + id, {
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
    axios.post(API_URL + 'viaje/reciclar/' + id, {
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
    axios.delete(API_URL + 'viaje/destruir/' + id, {
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

/* FIN métodos generales */

export const viajeService = {
  obtenerTodas,
  obtenerTodasConEliminadas,
  obtenerPorId,
  obtenerPorIdConEliminadas,

  spfBuscarTodasPorAcopladoId,
  spfBuscarTodasPorAcopladoIdConEliminadas,
  spfBuscarTodasPorCamionId,
  spfBuscarTodasPorCamionIdConEliminadas,
  spfBuscarTodasPorCamionIdEntreFechas,
  spfBuscarTodasPorCamionIdEntreFechasConEliminadas,
  spfBuscarTodasPorCategoriaViajeId,
  spfBuscarTodasPorCategoriaViajeIdConEliminadas,
  spfBuscarTodasPorConductorId,
  spfBuscarTodasPorConductorIdConEliminadas,
  spfBuscarTodasPorConductorIdEntreFechas,
  spfBuscarTodasPorConductorIdEntreFechasConEliminadas,
  spfBuscarTodasPorCompradorId,
  spfBuscarTodasPorCompradorIdConEliminadas,
  spfBuscarTodasPorCompradorIdEntreFechas,
  spfBuscarTodasPorCompradorIdEntreFechasConEliminadas,
  spfBuscarTodasPorDireccionCargaId,
  spfBuscarTodasPorDireccionCargaIdConEliminadas,
  spfBuscarTodasPorDireccionCargaIdEntreFechas,
  spfBuscarTodasPorDireccionCargaIdEntreFechasConEliminadas,
  spfBuscarTodasPorDireccionDestinoId,
  spfBuscarTodasPorDireccionDestinoIdConEliminadas,
  spfBuscarTodasPorDireccionDestinoIdEntreFechas,
  spfBuscarTodasPorDireccionDestinoIdEntreFechasConEliminadas,
  spfBuscarTodasPorDireccionOrigenId,
  spfBuscarTodasPorDireccionOrigenIdConEliminadas,
  spfBuscarTodasPorDireccionOrigenIdEntreFechas,
  spfBuscarTodasPorDireccionOrigenIdEntreFechasConEliminadas,
  spfBuscarTodasPorIntermediarioId,
  spfBuscarTodasPorIntermediarioIdConEliminadas,
  spfBuscarTodasPorIntermediarioIdEntreFechas,
  spfBuscarTodasPorIntermediarioIdEntreFechasConEliminadas,
  spfBuscarTodasPorFechaViajeEntreFechas,
  spfBuscarTodasPorFechaViajeEntreFechasConEliminadas,
  spfBuscarTodasPorRangoKmCargado,
  spfBuscarTodasPorRangoKmCargadoConEliminadas,
  spfBuscarTodasPorRangoKmVacio,
  spfBuscarTodasPorRangoKmVacioConEliminadas,
  spfBuscarTodasPorNotas,
  spfBuscarTodasPorNotasConEliminadas,
  spfBuscarTodasPorGuia,
  spfBuscarTodasPorGuiaConEliminadas,
  spfBuscarTodasPorRangoNeto,
  spfBuscarTodasPorRangoNetoConEliminadas,
  spfBuscarTodasPorRangoValorKm,
  spfBuscarTodasPorRangoValorKmConEliminadas,
  spfBuscarTodasPorVendedorId,
  spfBuscarTodasPorVendedorIdConEliminadas,
  spfBuscarTodasPorVendedorIdEntreFechas,
  spfBuscarTodasPorVendedorIdEntreFechasConEliminadas,

  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarTodasPaginadas,
  spfBuscarTodasConEliminadasPaginadas,
  spfBuscarUltimoViaje,
  spfContarTodas,
  spfContarTodasConEliminadas,
  spfSumarCantidadTransportada,
  spfSumarKmCargado,
  spfSumarKmVacio,

  spfGuardar,
  spfBorrar,
  spfReciclar,
  spfDestruir
}
