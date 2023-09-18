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
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
