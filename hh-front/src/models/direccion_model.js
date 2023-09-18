import { UsuarioModel } from './usuario_model'
import { UbicacionModel } from './ubicacion_model'

export class DireccionModel {
  constructor () {
    this.id = ''
    this.direccion = ''
    this.nombre = ''
    this.ubicacion = new UbicacionModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
