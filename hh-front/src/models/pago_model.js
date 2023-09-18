import { UsuarioModel } from './usuario_model'
import { ReciboModel } from './recibo_model'

export class PagoModel {
  constructor () {
    this.id = ''
    this.monto = ''
    this.notas = ''
    this.tipoPago = ''
    this.recibo = new ReciboModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
