package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ClienteCreation;
import gloit.hiperionida.helios.mapper.dto.ClienteDTO;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.repository.ClienteDAO;
import gloit.hiperionida.helios.repository.DireccionDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteMapper {
    private final DireccionDAO direccionDAO;
    private final UsuarioDAO usuarioDAO;

    public ClienteModel toEntity(ClienteCreation creation) {
        try {
            ClienteModel model = new ClienteModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setDireccion(creation.getDireccion());
            model.setEmail(creation.getEmail());
            model.setIdentificacion(creation.getIdentificacion());
            model.setNombre(creation.getNombre());
            model.setNotas(creation.getNotas());
            model.setTelefono(creation.getTelefono());

            if (Helper.getLong(creation.getCreador_id()) != null)
                model.setCreador_id(Helper.getLong(creation.getCreador_id()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), ""));
            if (Helper.getLong(creation.getModificador_id()) != null)
                model.setModificador_id(Helper.getLong(creation.getModificador_id()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), ""));
            if (Helper.getLong(creation.getEliminador_id()) != null)
                model.setEliminador_id(Helper.getLong(creation.getEliminador_id()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), ""));

            return model;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ClienteDTO toDto(ClienteModel model) {
        try {
            ClienteDTO dto = new ClienteDTO();

            dto.setId(model.getId().toString());
            dto.setDireccion(model.getDireccion());
            dto.setEmail(model.getEmail());
            dto.setIdentificacion(model.getIdentificacion());
            dto.setNombre(model.getNombre());
            dto.setNotas(model.getNotas());
            dto.setTelefono(model.getTelefono());

            if (model.getCreador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreador_id() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificador_id() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminador_id() + "."));
                dto.setEliminador(usuarioModel.getNombre());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}