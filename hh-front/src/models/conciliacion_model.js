import { UsuarioModel } from './usuario_model'
import { BancoModel } from './banco_model'

export class ConciliacionModel {
  constructor () {
    this.id = ''
    this.movimiento = ''
    this.fecha = ''
    this.concepto = ''
    this.monto = ''
    this.banco = new BancoModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
