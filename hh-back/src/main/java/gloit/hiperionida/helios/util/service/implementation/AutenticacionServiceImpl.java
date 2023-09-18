package gloit.hiperionida.helios.util.service.implementation;

import gloit.hiperionida.helios.util.exception.CustomDataNotFoundException;
import gloit.hiperionida.helios.util.exception.CustomTokenMismatchException;
import gloit.hiperionida.helios.util.exception.CustomUserAlreadyCreatedException;
import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.mapper.dto.AutenticacionRequestDTO;
import gloit.hiperionida.helios.util.mapper.dto.AutenticacionResponseDTO;
import gloit.hiperionida.helios.util.model.RolModel;
import gloit.hiperionida.helios.util.model.TokenModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.TokenDAO;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import gloit.hiperionida.helios.util.security.JwtService;
import gloit.hiperionida.helios.util.service.AutenticacionService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.keygen.BytesKeyGenerator;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class AutenticacionServiceImpl implements AutenticacionService {

    private final UsuarioServiceImpl usuarioService;

    private final AuthenticationManager authenticationManager;
    private final TokenDAO tokenDAO;
    private final JwtService jwtService;
    private final UsuarioDAO usuarioDAO;
    private final TokenServiceImpl tokenService;

    @Value("${neso.app.mail.path}")
    private String path;
    private static final BytesKeyGenerator DEFAULT_TOKEN_GENERATOR = KeyGenerators.secureRandom(15);

    @Override
    public AutenticacionResponseDTO ingresar(AutenticacionRequestDTO autenticacionRequestDTO) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(autenticacionRequestDTO.getUsername(), autenticacionRequestDTO.getPassword()));
        } catch (Exception e) {
            throw new CustomDataNotFoundException("El usuario: " + autenticacionRequestDTO.getUsername() + " no existe");
        }
        UsuarioModel usuario = usuarioService.buscarPorNombreDeUsuario(autenticacionRequestDTO.getUsername());
        if (!usuario.getHabilitada())
            throw new CustomDataNotFoundException("El usuario: " + autenticacionRequestDTO.getUsername() + " no se encuentra habilitado, debe confirmar su email");
        String token = jwtService.generarToken(usuario);
        tokenService.revocarTokensUsuario(usuario);
        tokenService.guardarTokenUsuario(usuario, token);
        log.info("El usuario {} se logueo correctamente.", autenticacionRequestDTO.getUsername());
        List<String> listado = new ArrayList<>();
        for (RolModel rol:usuario.getRoles()) {
            listado.add(rol.getRol().toString());
        }
        return new AutenticacionResponseDTO(token, listado, usuario.getUsername());
    }

    @Override
    public UsuarioModel validarToken(Long id, String token) {
        UsuarioModel usuario = usuarioService.buscarPorId(id);
        if (usuario.getToken().equals(token)) {
            usuario.setHabilitada(true);
            log.info("La cuenta de usuario: {}, se habilitó correctamente.", usuario.getUsername());
            return usuarioDAO.save(usuario);
        } else {
            log.warn("Ocurrió un error al intentar habilitar la cuenta.");
            throw new CustomTokenMismatchException("No se pudo habilitar el usuario " + usuario.getUsername() + " ya que los tokens no coinciden.");
        }
    }

    @Override
    public UsuarioModel registrar(UsuarioCreation usuarioCreation) {
        if (usuarioService.existeUsuarioPorNombreDeUsuario(usuarioCreation.getUsername()))
            throw new CustomUserAlreadyCreatedException("Ya existe un usuario con ese nombre de usuario.");
        UsuarioModel usuario = usuarioService.guardar(usuarioCreation);
        usuario.setHabilitada(false);
        String token = Base64.encodeBase64URLSafeString(DEFAULT_TOKEN_GENERATOR.generateKey());
        usuario.setToken(token);
//        String body = path + usuario.getId() + "/" + token;
//        EmailModel emailModel = emailService.enviarEmailSimple(new EmailCreation(
//                "Confirmá tu dirección de email para continuar con tu reclamo.",
//                this.sender,
//                usuarioCreation.getUsername(),
//                body
//        ));
        return usuarioService.darRol(usuario, "CARGA");
    }

    @Override
    public ResponseEntity<String> logout(HttpServletRequest request) {
        try {
            final String authHeader = request.getHeader("Authorization");
            final String jwt;
            if (authHeader != null) {
                if (authHeader.startsWith("Bearer ")) {
                    jwt = authHeader.substring(7);
                    Optional<TokenModel> storedToken = tokenDAO.findByToken(jwt);
                    if (storedToken.isPresent()) {
                        storedToken.get().setExpirado(true);
                        storedToken.get().setRevocado(true);
                        tokenDAO.save(storedToken.get());
                        SecurityContextHolder.clearContext();
                        String mensaje = "El usuario ha salido correctamente del sistema.";
                        log.info(mensaje);
                        return new ResponseEntity<>(mensaje, HttpStatus.OK);
                    }
                }
            }
            String mensaje = "El token se encuentra mal formado.";
            log.info(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            String mensaje = "Ocurrio un error al intentar salir del sistema.";
            log.error(mensaje);
            return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);
        }
    }
}