package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.TokensIncorrectosException;
import gloit.hiperionida.helios.util.exception.UsuarioExistenteException;
import gloit.hiperionida.helios.util.mapper.dto.ErrorDTO;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.mapper.dto.AutenticacionRequestDTO;
import gloit.hiperionida.helios.util.mapper.dto.AutenticacionResponseDTO;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.service.implementation.AutenticacionServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/autenticacion")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AutenticacionController extends AbsBaseController {
	private final AutenticacionServiceImpl autenticacionService;
	private final UsuarioMapper usuarioMapper;
	@Value("${helios.app.mail.path}")
	private String path;

	@ExceptionHandler(TokensIncorrectosException.class)
	public ResponseEntity<ErrorDTO> handleTokenMismatchException(Exception e) {
		HttpStatus status = HttpStatus.CONFLICT; // 409
		String mensaje = "Error al comparar los tokens. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@ExceptionHandler(UsuarioExistenteException.class)
	public ResponseEntity<ErrorDTO> handleUserAlreadyCreatedException(Exception e) {
		HttpStatus status = HttpStatus.CONFLICT; // 409
		String mensaje = "El usuario ingresado ya existe. " + e.getMessage();

		return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
	}

	@PostMapping("/ingresar")
	public ResponseEntity<?> ingresar(@Valid @RequestBody AutenticacionRequestDTO autenticacionRequestDTO) {
		AutenticacionResponseDTO objeto = autenticacionService.ingresar(autenticacionRequestDTO);
		return new ResponseEntity<>(objeto, Helper.httpHeaders("Usuario ingresado correctamente."), HttpStatus.OK);
	}

	@PostMapping("/registrarse")
	public ResponseEntity<?> registrarse(@Valid @RequestBody UsuarioCreation usuarioCreation) {
		UsuarioModel objeto = autenticacionService.registrar(usuarioCreation);
		return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders(path + objeto.getId() + "/" + objeto.getToken()), HttpStatus.OK);
	}

	@GetMapping(value="/salir")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		return autenticacionService.logout(request);
	}

	@GetMapping(value="/confirmar-email/{id}/{token}")
	public ResponseEntity<?> confirmarEmail(@PathVariable(name = "id") Long id, @PathVariable(name = "token")  String token) {
		UsuarioModel objeto = autenticacionService.validarToken(id, token);
		return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Todas las credenciales emitidas para el usuario han sido borradas"), HttpStatus.OK);
	}
}
