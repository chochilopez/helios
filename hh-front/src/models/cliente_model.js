import { UsuarioModel } from './usuario_model'

export class ClienteModel {
  constructor () {
    this.id = ''
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
