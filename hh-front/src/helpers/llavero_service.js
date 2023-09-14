/*
s -> servicio
p -> promesa
f -> funcion
l -> local
*/

function deleteFromLocalStorage (key) {
  localStorage.removeItem(key)
}

function getFromLocalStorage (key) {
  const itemStr = localStorage.getItem(key)
  // if the item doesn't exist, return null
  if (!itemStr) {
    return null
  }
  const item = JSON.parse(itemStr)
  const now = new Date()
  // compare the expiry time of the item with the current time
  if (now.getTime() > item.expire) {
    // If the item is expired, delete the item from storage
    // and return null
    localStorage.removeItem(key)
    return null
  }
  return item
}

function setToLocalStorage (key, val, ttl) {
  localStorage.removeItem(key)
  const now = new Date()
  // `item` is an object which contains the original value
  // as well as the time when it's supposed to expire
  const item = {
    value: val,
    expire: now.getTime() + ttl
  }
  localStorage.setItem(key, JSON.stringify(item))
}

export const llaveroService = {
  deleteFromLocalStorage,
  getFromLocalStorage,
  setToLocalStorage
}
