import { UsuarioModel } from './usuario_model'
import { EventoModel } from './evento_model'

export class LicenciaModel {
  constructor () {
    this.id = ''
    this.numero = ''
    this.categoria = ''
    this.vencimiento = new EventoModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
