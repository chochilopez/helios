package gloit.hiperionida.helios.util.mapper;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.mapper.dto.UsuarioDTO;
import gloit.hiperionida.helios.util.model.RolModel;
import gloit.hiperionida.helios.util.model.TokenModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.RolDAO;
import gloit.hiperionida.helios.util.repository.TokenDAO;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsuarioMapper {
    private final PasswordEncoder bcryptEncoder;
    private final UsuarioDAO usuarioDAO;
    private final TokenDAO tokenDAO;
    private final RolDAO rolDAO;

    public UsuarioModel toEntity(UsuarioCreation creation) {
        UsuarioModel model = new UsuarioModel();

        if (Helper.getLong(creation.getId()) != null)
            model = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getId())).get();
        model.setNombre(creation.getNombre());
        model.setDni(creation.getDni());
        model.setDireccion(creation.getDireccion());
        model.setTelefono(creation.getTelefono());
        if (Helper.getBoolean(creation.getHabilitada()) != null)
            model.setHabilitada(Helper.getBoolean(creation.getHabilitada()));
        else
            model.setHabilitada(false);
        model.setUsername(creation.getUsername());
        model.setPassword(bcryptEncoder.encode(creation.getPassword()));
        if (creation.getToken() != null)
            model.setToken(creation.getToken());


        if (creation.getTokens_id() != null) {
            Set<TokenModel> tokens = new HashSet<>();
            for (String tokenId:creation.getTokens_id()) {
                if (Helper.getLong(tokenId) != null) {
                    Optional<TokenModel> token = tokenDAO.findById(Helper.getLong(tokenId));
                    if (token.isPresent()) {
                        tokens.add(token.get());
                    }
                }
            }
            model.setTokens(tokens);
        }

        if (creation.getRoles_id() != null) {
            Set<RolModel> roles = new HashSet<>();
            for (String rolId:creation.getRoles_id()) {
                if (Helper.getLong(rolId) != null) {
                    Optional<RolModel> rol = rolDAO.findById(Helper.getLong(rolId));
                    if (rol.isPresent()) {
                        roles.add(rol.get());
                    }
                }
            }
            model.setRoles(roles);
        }

        if (Helper.getLong(creation.getCreadorId()) != null)
            model.setCreadorId(Helper.getLong(creation.getCreadorId()));
        if (!Helper.isEmptyString(creation.getCreada()))
            model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), ""));
        if (Helper.getLong(creation.getModificadorId()) != null)
            model.setModificadorId(Helper.getLong(creation.getModificadorId()));
        if (!Helper.isEmptyString(creation.getModificada()))
            model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), ""));
        if (Helper.getLong(creation.getEliminadorId()) != null)
            model.setEliminadorId(Helper.getLong(creation.getEliminadorId()));
        if (!Helper.isEmptyString(creation.getEliminada()))
            model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), ""));

        return model;
    }

    public UsuarioDTO toDto(UsuarioModel model) {
        try {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(model.getId().toString());
            dto.setNombre(model.getNombre());
            dto.setDni(model.getDni());
            dto.setDireccion(model.getDireccion());
            dto.setTelefono(model.getTelefono());
            dto.setUsername(model.getUsername());
            List<String> roles = new ArrayList<>();
            for (RolModel rol:model.getRoles()) {
                roles.add(rol.getRol().toString());
            }
            dto.setRoles(roles);

            if (model.getCreadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreadorId() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificadorId() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminadorId() + "."));
                dto.setEliminador(usuarioModel.getNombre());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.info("Usuario entity to dto error. Exception: " + e);
            return null;
        }
    }
}