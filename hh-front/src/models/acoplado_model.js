import { UsuarioModel } from './usuario_model'
import { SeguroModel } from './seguro_model'

export class AcopladoModel {
  constructor () {
    this.id = ''
    this.seguro = new SeguroModel()
    this.cantidadNeumaticos = ''
    this.marca = ''
    this.modelo = ''
    this.anio = ''
    this.patente = ''
    this.peso = ''

    this.neumaticos = new Array([])

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
