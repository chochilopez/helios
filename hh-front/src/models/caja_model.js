import { UsuarioModel } from './usuario_model'

export class CajaModel {
  constructor () {
    this.id = ''
    this.nombre = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
