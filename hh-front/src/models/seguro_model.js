import { UsuarioModel } from './usuario_model'
import { ProveedorModel } from './proveedor_model'
import { EventoModel } from './evento_model'

export class SeguroModel {
  constructor () {
    this.id = ''
    this.aseguradora = new ProveedorModel()
    this.vencimiento = new EventoModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
