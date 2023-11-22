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

function spfBuscarTodasPorClienteId (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-cliente-id/' + id, {
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

function spfBuscarTodasPorClienteIdConEliminadas (id) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-cliente-id-con-eliminadas/' + id, {
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

function spfBuscarTodasPorRangoCantidadTransportada (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-cantidad-transportada/' + inicio + '/' + fin, {
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

function spfBuscarTodasPorRangoCantidadTransportadaConEliminadas (inicio, fin) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas-por-rango-cantidad-transportada-con-eliminadas/' + inicio + '/' + fin, {
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

function spfBuscarProximo () {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-proximo', {
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

function spfBuscarUltimo () {
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

function spfBuscarTodasConSesion (sesion) {
  return new Promise((resolve, reject) => {
    axios.get(API_URL + 'viaje/buscar-todas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        if (result.status === 200) {
          llaveroService.guardarEnLocalConSesion('hhViajeTodasConSesion', result.data, sesion)
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
    axios.get(API_URL + 'viaje/buscar-todas-con-eliminadas', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((result) => {
        if (result.status === 200) {
          llaveroService.guardarEnLocalConSesion('hhViajeTodasConEliminadasConSesion', result.data, sesion)
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
    axios.get(API_URL + 'viaje/reciclar/' + id, {
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
  spfBuscarTodasPorAcopladoId,
  spfBuscarTodasPorAcopladoIdConEliminadas,
  spfBuscarTodasPorCamionId,
  spfBuscarTodasPorCamionIdConEliminadas,
  spfBuscarTodasPorCategoriaViajeId,
  spfBuscarTodasPorCategoriaViajeIdConEliminadas,
  spfBuscarTodasPorConductorId,
  spfBuscarTodasPorConductorIdConEliminadas,
  spfBuscarTodasPorClienteId,
  spfBuscarTodasPorClienteIdConEliminadas,
  spfBuscarTodasPorDireccionCargaId,
  spfBuscarTodasPorDireccionCargaIdConEliminadas,
  spfBuscarTodasPorDireccionDestinoId,
  spfBuscarTodasPorDireccionDestinoIdConEliminadas,
  spfBuscarTodasPorDireccionOrigenId,
  spfBuscarTodasPorDireccionOrigenIdConEliminadas,
  spfBuscarTodasPorIntermediarioId,
  spfBuscarTodasPorIntermediarioIdConEliminadas,
  spfBuscarTodasPorFechaViajeEntreFechas,
  spfBuscarTodasPorFechaViajeEntreFechasConEliminadas,
  spfBuscarTodasPorNotas,
  spfBuscarTodasPorNotasConEliminadas,
  spfBuscarTodasPorGuia,
  spfBuscarTodasPorGuiaConEliminadas,
  spfBuscarTodasPorRangoCantidadTransportada,
  spfBuscarTodasPorRangoCantidadTransportadaConEliminadas,
  spfBuscarProximo,
  spfBuscarUltimo,

  spfBuscarTodasPorRangoNeto,
  spfBuscarTodasPorRangoNetoConEliminadas,
  spfBuscarTodasPorRangoValorKm,
  spfBuscarTodasPorRangoValorKmConEliminadas,
  spfBuscarTodasPorVendedorId,
  spfBuscarTodasPorVendedorIdConEliminadas,
  spfBuscarTodasPorRangoKmCargado,
  spfBuscarTodasPorRangoKmCargadoConEliminadas,
  spfBuscarTodasPorRangoKmVacio,
  spfBuscarTodasPorRangoKmVacioConEliminadas,
  spfBuscarPorId,
  spfBuscarPorIdConEliminadas,
  spfBuscarTodas,
  spfBuscarTodasConEliminadas,
  spfBuscarTodasConSesion,
  spfBuscarTodasConEliminadasConSesion,
  spfBuscarTodasPaginadas,
  spfBuscarTodasConEliminadasPaginadas,
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
