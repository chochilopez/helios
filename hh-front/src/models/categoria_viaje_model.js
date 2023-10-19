import { UsuarioModel } from './usuario_model'

export class CategoriaViajeModel {
  constructor () {
    this.id = ''
    this.categoria = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
