import { UserModel } from './user_model'

export class VisitModel {
  constructor () {
    this.id = ''
    this.ip = ''
    this.hostname = ''
    this.continent_code = ''
    this.continent_name = ''
    this.country_code2 = ''
    this.country_code3 = ''
    this.country_name = ''
    this.country_capital = ''
    this.state_prov = ''
    this.district = ''
    this.city = ''
    this.zipcode = ''
    this.latitude = ''
    this.longitude = ''
    this.is_eu = ''
    this.calling_code = ''
    this.country_tld = ''
    this.languages = ''
    this.country_flag = ''
    this.isp = ''
    this.connection_type = ''
    this.organization = ''
    this.asn = ''
    this.geoname_id = ''

    this.creator = new UserModel()
    this.created = ''
    this.modifier = new UserModel()
    this.modified = ''
    this.remover = new UserModel()
    this.removed = ''
  }
}
