import { llaveroService } from 'src/helpers/llavero_service'
import { ttlEnum } from 'src/models/enums/ttl_enum'
import axios from 'axios'
const API_URL = process.env.API_URL

/*
s -> servicio
p -> promesa
f -> funcion
l -> local
*/

function fEstaLogueado () {
  if (llaveroService.obtenerDeLocal('hhToken') === null) {
    return false
  } else {
    return true
  }
}

function obtenerAutoridades () {
  if (llaveroService.obtenerDeLocal('hhAutoridades')) { return llaveroService.obtenerDeLocal('hhAutoridades').value } else { return null }
}

function obtenerToken () {
  if (llaveroService.obtenerDeLocal('hhToken')) { return llaveroService.obtenerDeLocal('hhToken').value } else { return null }
}

function obtenerNombreUsuario () {
  if (llaveroService.obtenerDeLocal('hhNombreUsuario')) { return llaveroService.obtenerDeLocal('hhNombreUsuario').value } else { return null }
}

function spfIngresar (user) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'autenticacion/ingresar', user)
      .then((response) => {
        if (response.status === 200) {
          llaveroService.guardarEnLocal('hhToken', response.data.token, ttlEnum.TTL_1_DIA)
          llaveroService.guardarEnLocal('hhAutoridades', response.data.roles, ttlEnum.TTL_1_DIA)
          llaveroService.guardarEnLocal('hhNombreUsuario', response.data.usuario, ttlEnum.TTL_1_DIA)
        }
        resolve(response)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfSalir () {
  return new Promise((resolve, reject) => {
    llaveroService.borrarDeLocal('hhToken')
    llaveroService.borrarDeLocal('hhAutoridades')
    llaveroService.borrarDeLocal('hhNombreUsuario')
    localStorage.clear()
    axios.get(API_URL + 'autenticacion/salir', {
      headers: {
        Authorization: 'Bearer ' + autenticacionService.obtenerToken()
      }
    })
      .then((response) => {
        resolve(response)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

export const autenticacionService = {
  fEstaLogueado,
  obtenerAutoridades,
  obtenerToken,
  obtenerNombreUsuario,
  spfIngresar,
  spfSalir
}
