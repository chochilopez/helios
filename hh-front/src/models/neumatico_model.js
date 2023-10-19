import { UsuarioModel } from './usuario_model'
import { ProveedorModel } from './proveedor_model'

export class NeumaticoModel {
  constructor () {
    this.id = ''
    this.fechaCompra = ''
    this.kmVida = ''
    this.kmActuales = ''
    this.kmRecapado = ''
    this.numeroRemito = ''
    this.marca = ''
    this.precioCompra = ''
    this.recapadosMaximos = ''
    this.ubicacion = ''
    this.estado = ''
    this.baja = ''
    this.proveedor = new ProveedorModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
