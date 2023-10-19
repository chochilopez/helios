import { UsuarioModel } from './usuario_model'

export class ReciboModel {
  constructor () {
    this.id = ''
    this.monto = ''
    this.fecha = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
