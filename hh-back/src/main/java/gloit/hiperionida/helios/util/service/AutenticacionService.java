package gloit.hiperionida.helios.util.service;

import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.mapper.dto.AutenticacionRequestDTO;
import gloit.hiperionida.helios.util.mapper.dto.AutenticacionResponseDTO;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

public interface AutenticacionService {
    UsuarioModel validarToken (Long id, String token);
    UsuarioModel registrar (UsuarioCreation usuarioCreation);
    AutenticacionResponseDTO ingresar (AutenticacionRequestDTO autenticacionRequestDTO);
    ResponseEntity<String> logout(HttpServletRequest request);
}
