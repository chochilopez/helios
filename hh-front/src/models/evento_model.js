import { UsuarioModel } from './usuario_model'

export class EventoModel {
  constructor () {
    this.id = ''
    this.fecha = ''
    this.descripcion = ''
    this.habilitada = ''
    this.recordatorio = ''
    this.nombre = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
