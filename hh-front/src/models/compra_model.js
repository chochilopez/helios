import { UsuarioModel } from './usuario_model'
import { ArchivoModel } from './archivo_model'
import { ProveedorModel } from './proveedor_model'
import { RemitoModel } from './remito_model'

export class CompraModel {
  constructor () {
    this.id = ''
    this.fecha = ''
    this.iva = ''
    this.numeroComprobante = ''
    this.subTotal = ''
    this.tipoComprobante = ''
    this.notas = ''
    this.comprobante = new ArchivoModel()
    this.pagos = new Array([])
    this.proveedor = new ProveedorModel()
    this.remito = new RemitoModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
