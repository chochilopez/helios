import { CamionModel } from './camion_model'
import { ConductorModel } from './conductor_model'
import { ProveedorModel } from './proveedor_model'
import { UsuarioModel } from './usuario_model'

export class CombustibleModel {
  constructor () {
    this.id = ''
    this.fecha = ''
    this.kilometros = ''
    this.litros = ''
    this.precio = ''
    this.notas = ''
    this.camion = new CamionModel()
    this.conductor = new ConductorModel()
    this.proveedor = new ProveedorModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
