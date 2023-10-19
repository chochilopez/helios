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
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
