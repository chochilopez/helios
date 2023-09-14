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
  if (llaveroService.getFromLocalStorage('hhToken') === null) {
    return false
  } else {
    return true
  }
}

function obtenerAutoridades () {
  if (llaveroService.getFromLocalStorage('hhAutoridades')) { return llaveroService.getFromLocalStorage('hhAutoridades').value } else { return null }
}

function obtenerToken () {
  if (llaveroService.getFromLocalStorage('hhToken')) { return llaveroService.getFromLocalStorage('hhToken').value } else { return null }
}

function obtenerNombreUsuario () {
  if (llaveroService.getFromLocalStorage('hhNombreUsuario')) { return llaveroService.getFromLocalStorage('hhNombreUsuario').value } else { return null }
}

function spfIngresar (user) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'autenticacion/ingresar', user)
      .then((response) => {
        if (response.status === 200) {
          llaveroService.setToLocalStorage('hhToken', response.data.token, ttlEnum.TTL_1_DIA)
          llaveroService.setToLocalStorage('hhAutoridades', response.data.authorities, ttlEnum.TTL_1_DIA)
          llaveroService.setToLocalStorage('hhNombreUsuario', response.data.username, ttlEnum.TTL_1_DIA)
        }
        resolve(response)
      })
      .catch((error) => {
        reject(error)
      })
  })
}

function spfSalir (user) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'autenticacion/salir', user)
      .then((response) => {
        if (response.status === 200) {
          llaveroService.deleteFromLocalStorage('hhToken')
          llaveroService.deleteFromLocalStorage('hhAutoridades')
          llaveroService.deleteFromLocalStorage('hhNombreUsuario')
          localStorage.clear()
        }
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
