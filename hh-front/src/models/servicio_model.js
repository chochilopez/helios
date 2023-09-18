import { UsuarioModel } from './usuario_model'

export class ServicioModel {
  constructor () {
    this.id = ''
    this.kmServicio = ''
    this.kmActual = ''
    this.kmProximo = ''
    this.tipo = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
