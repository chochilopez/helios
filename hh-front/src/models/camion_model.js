import { UsuarioModel } from './usuario_model'
import { SeguroModel } from './seguro_model'

export class CamionModel {
  constructor () {
    this.cantidadNeumaticos = ''
    this.marca = ''
    this.modelo = ''
    this.anio = ''
    this.patente = ''
    this.peso = ''
    this.numeroChasis = ''
    this.numeroMotor = ''
    this.pesoArrastre = ''
    this.seguro = new SeguroModel()
    this.servicios = new Array([])
    this.neumaticos = new Array([])

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
