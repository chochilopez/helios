import { UsuarioModel } from './usuario_model'
import { CamionModel } from './camion_model'
import { LicenciaModel } from './licencia_model'

export class ConductorModel {
  constructor () {
    this.id = ''
    this.adelantos = new Array([])
    this.camion = new CamionModel()
    this.licencia = new LicenciaModel()
    this.direccion = ''
    this.email = ''
    this.identificacion = ''
    this.nombre = ''
    this.notas = ''
    this.telefono = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
