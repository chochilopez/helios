import { UsuarioModel } from './usuario_model'
import { CategoriaGastoModel } from './categoria_gasto_model'

export class GastoModel {
  constructor () {
    this.id = ''
    this.monto = ''
    this.notas = ''
    this.categoriaGasto = new CategoriaGastoModel()

    this.creador = new UsuarioModel()
    this.creada = ''
    this.modificador = new UsuarioModel()
    this.modificada = ''
    this.eliminador = new UsuarioModel()
    this.elimianda = ''
  }
}
