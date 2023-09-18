import { UsuarioModel } from './usuario_model'

export class CategoriaViajeModel {
  constructor () {
    this.id = ''
    this.categoria = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
