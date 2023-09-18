import { UsuarioModel } from './usuario_model'

export class RemitoModel {
  constructor () {
    this.id = ''
    this.descripcion = ''
    this.fecha = ''
    this.numero = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
