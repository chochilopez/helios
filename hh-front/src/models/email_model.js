import { UserModel } from './user_model'

export class EmailModel {
  constructor () {
    this.id = ''
    this.name = ''
    this.telephone = ''
    this.sender = ''
    this.recepient = ''
    this.carbonCopy = ''
    this.subject = ''
    this.sended = ''
    this.body = ''
    this.error = ''

    this.creator = new UserModel()
    this.created = ''
    this.modifier = new UserModel()
    this.modified = ''
    this.remover = new UserModel()
    this.removed = ''
  }
}
