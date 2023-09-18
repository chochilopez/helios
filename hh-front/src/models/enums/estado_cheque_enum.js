export const tipoArchivoEnum = {
  EMITIDO: 'EMITIDO', // cheque propio, se considera pagado cuando ya está dado al cliente (a través de una orden de pago por ejemplo)
  RECIVIDO: 'RECIVIDO', // cheque de terceros, 1) estado recibido, cuando ingresa a través de un recibo de cta cte
  DEPOSITADO: 'DEPOSITADO', // depositado, cuando lo llevas al banco
  ACREDITADO: 'ACREDITADO', // acreditado cuando el banco te lo paga
  RECHAZADO: 'RECHAZADO', // eventualmente puede tener un cuarto estado que es Rechazado si el banco no lo paga
  TRANSFERIDO: 'TRANSFERIDO' // TRANSFERIDO cuando lo usaste para pagarle a un proveedor
}
