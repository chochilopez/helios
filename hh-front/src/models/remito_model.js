import { UsuarioModel } from './usuario_model'

export class RemitoModel {
  constructor () {
    this.id = ''
    this.descripcion = ''
    this.fecha = ''
    this.numero = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
