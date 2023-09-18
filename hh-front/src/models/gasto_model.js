import { UsuarioModel } from './usuario_model'
import { CategoriaGastoModel } from './categoria_gasto_model'

export class GastoModel {
  constructor () {
    this.id = ''
    this.monto = ''
    this.notas = ''
    this.categoriaGasto = new CategoriaGastoModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
