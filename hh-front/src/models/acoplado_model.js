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
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
