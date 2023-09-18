import { UsuarioModel } from './usuario_model'
import { CategoriaViajeModel } from './categoria_viaje_model'
import { DireccionModel } from './direccion_model'
import { ClienteModel } from './cliente_model'
import { EventoModel } from './evento_model'

export class PresupuestoModel {
  constructor () {
    this.id = ''
    this.validez = ''
    this.cantidadTransportada = ''
    this.valorKm = ''
    this.kgNeto = ''
    this.kmCargado = ''
    this.kmVacio = ''
    this.notas = ''
    this.categoriaViaje = new CategoriaViajeModel()
    this.origen = new DireccionModel()
    this.carga = new DireccionModel()
    this.destino = new DireccionModel()
    this.vendedor = new ClienteModel()
    this.intermediario = new ClienteModel()
    this.comprador = new ClienteModel()
    this.fecha = new EventoModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
