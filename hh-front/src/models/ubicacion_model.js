import { UsuarioModel } from './usuario_model'

export class UbicacionModel {
  constructor () {
    this.id = ''
    this.ciudad = ''
    this.departamento = ''
    this.pais = ''

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
