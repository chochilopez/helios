import { UsuarioModel } from './usuario_model'
import { UbicacionModel } from './ubicacion_model'

export class DireccionModel {
  constructor () {
    this.id = ''
    this.direccion = ''
    this.nombre = ''
    this.ubicacion = new UbicacionModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
