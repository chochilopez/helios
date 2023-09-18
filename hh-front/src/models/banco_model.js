import { UsuarioModel } from './usuario_model'

export class BancoModel {
  constructor () {
    this.id = ''
    this.banco = ''
    this.notas = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
