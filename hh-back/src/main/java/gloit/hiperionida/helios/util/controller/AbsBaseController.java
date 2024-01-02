package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.*;
import gloit.hiperionida.helios.util.mapper.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public abstract class AbsBaseController {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorDTO> handleIllegalArgumentException(IllegalArgumentException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		String mensaje = "No se cumplieron los requisitos del objeto. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		String clave = "";
		String valor = "";
		String mensaje = "";
		for (ObjectError error:e.getBindingResult().getAllErrors()) {
			clave = ((FieldError) error).getField();
			valor = error.getDefaultMessage();
			if (mensaje.isEmpty())
				mensaje = "Error en la validacion de los siguientes campos: " + clave + ": " + valor;
			else
				mensaje = mensaje + " --- " + clave + ": " + valor;
		}
		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(ReporteMalFormadoException.class)
	public ResponseEntity<ErrorDTO> handleReporteMalFormadoException(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		String mensaje = "El reporte esta mal formado. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(ParametroInvalidoException.class)
	public ResponseEntity<ErrorDTO> handleCustomParameterConstraintExceptions(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		String mensaje = "Los datos ingresados no poseen el formato correcto. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(DatosInexistentesException.class)
	public ResponseEntity<ErrorDTO> handleCustomDataNotFoundExceptions(Exception e) {
		HttpStatus status = HttpStatus.NOT_FOUND; // 404

		return new ResponseEntity<>(new ErrorDTO(status, e.getMessage()), Helper.httpHeaders(e.getMessage()), status);
	}

	@ExceptionHandler(ObjectoNoEliminadoException.class)
	public ResponseEntity<ErrorDTO> handleCustomObjectNotDeletedException(Exception e) {
		HttpStatus status = HttpStatus.CONFLICT; // 409
		String mensaje = "El objeto no se encuentra eliminado. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDTO> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		HttpStatus status = HttpStatus.CONFLICT; // 409
		String mensaje = "Error en la conversion de parametros ingresados. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(ErrorGenericoException.class)
	public ResponseEntity<ErrorDTO> handleCustomErrorExceptions(Exception e) {
		// casting the generic Exception e to CustomErrorException
		ErrorGenericoException errorGenericoException = (ErrorGenericoException) e;

		HttpStatus status = errorGenericoException.getStatus();
		String mensaje = "Ocurrio un error. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, errorGenericoException.getMessage()), Helper.httpHeaders(mensaje), status);
	}

	// fallback method
	@ExceptionHandler(Exception.class) // exception handled
	public ResponseEntity handleExceptions(Exception e ) {
		// ... potential custom logic

		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
		String mensaje = "Ocurrio un error al intentar consumir el recurso. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, e.getMessage()), Helper.httpHeaders(mensaje), status);
	}
}
