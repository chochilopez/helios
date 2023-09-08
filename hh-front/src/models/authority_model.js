import { UserModel } from './user_model'

export class AuthorityModel {
  constructor () {
    this.id = ''
    this.authority = ''

    this.creator = new UserModel()
    this.created = ''
    this.modifier = new UserModel()
    this.modified = ''
    this.remover = new UserModel()
    this.removed = ''
  }
}
