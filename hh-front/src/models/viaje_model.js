import { UsuarioModel } from './usuario_model'
import { CategoriaViajeModel } from './categoria_viaje_model'
import { DireccionModel } from './direccion_model'
import { ClienteModel } from './cliente_model'
import { EventoModel } from './evento_model'
import { ConductorModel } from './conductor_model'
import { CamionModel } from './camion_model'
import { AcopladoModel } from './acoplado_model'

export class ViajeModel {
  constructor () {
    this.id = ''
    this.cantidadTransportada = ''
    this.valorKm = ''
    this.neto = ''
    this.kmCargado = ''
    this.kmVacio = ''
    this.notas = ''
    this.guia = ''
    this.categoriaViaje = new CategoriaViajeModel()
    this.origen = new DireccionModel()
    this.carga = new DireccionModel()
    this.destino = new DireccionModel()
    this.vendedor = new ClienteModel()
    this.intermediario = new ClienteModel()
    this.comprador = new ClienteModel()
    this.fecha = new EventoModel()
    this.conductor = new ConductorModel()
    this.camion = new CamionModel()
    this.acoplado = new AcopladoModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
