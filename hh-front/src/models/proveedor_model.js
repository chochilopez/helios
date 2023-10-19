import { UsuarioModel } from './usuario_model'

export class ProveedorModel {
  constructor () {
    this.id = ''
    this.direccion = ''
    this.email = ''
    this.identificacion = ''
    this.nombre = ''
    this.notas = ''
    this.telefono = ''

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
