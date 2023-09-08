import { UserModel } from './user_model'

export class LocalFileModel {
  constructor () {
    this.id = ''
    this.path = ''
    this.filename = ''
    this.filetype = ''
    this.description = ''
    this.filesize = ''

    this.uploadedFile = ''

    this.creator = new UserModel()
    this.created = ''
    this.modifier = new UserModel()
    this.modified = ''
    this.remover = new UserModel()
    this.removed = ''
  }
}
