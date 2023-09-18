import { UsuarioModel } from './usuario_model'

export class VisitaModel {
  constructor () {
    this.id = ''
    this.ip = ''
    this.hostname = ''
    this.country_name = ''
    this.state_prov = ''
    this.district = ''
    this.city = ''
    this.zipcode = ''
    this.country_flag = ''
    this.isp = ''
    this.organization = ''
    this.asn = ''
    this.visitante = new UsuarioModel()

    this.creador = new UsuarioModel()
    this.creado = ''
    this.modificador = new UsuarioModel()
    this.modificado = ''
    this.eliminador = new UsuarioModel()
    this.elimiando = ''
  }
}
