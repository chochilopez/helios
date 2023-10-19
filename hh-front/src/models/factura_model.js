import { UsuarioModel } from './usuario_model'
import { MovimientoModel } from './movimiento_model'
import { RemitoModel } from './remito_model'
import { ViajeModel } from './viaje_model'

export class FacturaModel {
  constructor () {
    this.id = ''
    this.descuento = ''
    this.fecha = ''
    this.iva = ''
    this.numeroComprobante = ''
    this.recarga = ''
    this.subTotal = ''
    this.tipoComprobante = ''
    this.pagada = ''
    this.pagos = new Array([])
    this.movimiento = new MovimientoModel()
    this.remito = new RemitoModel()
    this.viaje = new ViajeModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
