import { UsuarioModel } from './usuario_model'

export class CategoriaGastoModel {
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
