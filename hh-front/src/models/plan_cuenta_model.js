import { UsuarioModel } from './usuario_model'

export class PlanCuentaModel {
  constructor () {
    this.id = ''
    this.nombre = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
