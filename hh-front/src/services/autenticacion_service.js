import { llavero } from 'src/helpers/llavero'
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
  if (llavero.getFromLocalStorage('lToken') === null) {
    return false
  } else {
    return true
  }
}

function obtenerAutoridades () {
  return llavero.getFromLocalStorage('lAutoridades').value
}

function obtenerToken () {
  return llavero.getFromLocalStorage('lToken').value
}

function obtenerNombreUsuario () {
  return llavero.getFromLocalStorage('lNombreUsuario').value
}

function spfLoguearse (user) {
  return new Promise((resolve, reject) => {
    axios.post(API_URL + 'autenticacion/ingresar', user)
      .then((response) => {
        if (response.status === 200) {
          llavero.setToLocalStorage('lToken', response.data.token, ttlEnum.TTL_1_DAY)
          llavero.setToLocalStorage('lAutoridades', response.data.authorities, ttlEnum.TTL_1_DAY)
          llavero.setToLocalStorage('lNombreUsuario', response.data.username, ttlEnum.TTL_1_DAY)
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
          llavero.deleteFromLocalStorage('lToken')
          llavero.deleteFromLocalStorage('lAutoridades')
          llavero.deleteFromLocalStorage('lNombreUsuario')
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
  spfLoguearse,
  spfSalir
}
