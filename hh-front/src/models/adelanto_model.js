import { UsuarioModel } from './usuario_model'
import { CajaModel } from './caja_model'

export class AdelantoModel {
  constructor () {
    this.id = ''
    this.descripcion = ''
    this.fecha = ''
    this.monto = ''
    this.notas = ''
    this.recibo = ''
    this.rendido = ''
    this.caja = new CajaModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
