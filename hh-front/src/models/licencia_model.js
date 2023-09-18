import { UsuarioModel } from './usuario_model'
import { EventoModel } from './evento_model'

export class LicenciaModel {
  constructor () {
    this.id = ''
    this.numero = ''
    this.categoria = ''
    this.vencimiento = new EventoModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
