import { UsuarioModel } from './usuario_model'

export class ReciboModel {
  constructor () {
    this.id = ''
    this.monto = ''
    this.fecha = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
