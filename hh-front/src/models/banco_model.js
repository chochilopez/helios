import { UsuarioModel } from './usuario_model'

export class BancoModel {
  constructor () {
    this.id = ''
    this.banco = ''
    this.notas = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
