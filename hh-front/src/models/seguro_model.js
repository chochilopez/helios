import { UsuarioModel } from './usuario_model'
import { ProveedorModel } from './proveedor_model'
import { EventoModel } from './evento_model'

export class SeguroModel {
  constructor () {
    this.id = ''
    this.aseguradora = new ProveedorModel()
    this.vencimiento = new EventoModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
