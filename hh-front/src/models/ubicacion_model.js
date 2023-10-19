import { UsuarioModel } from './usuario_model'

export class UbicacionModel {
  constructor () {
    this.id = ''
    this.ciudad = ''
    this.departamento = ''
    this.pais = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
