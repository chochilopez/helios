import { UsuarioModel } from './usuario_model'

export class ServicioModel {
  constructor () {
    this.id = ''
    this.kmServicio = ''
    this.kmActual = ''
    this.kmProximo = ''
    this.tipo = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
