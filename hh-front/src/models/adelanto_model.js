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
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
