package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ClienteCreation;
import gloit.hiperionida.helios.mapper.dto.ClienteDTO;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.util.Helper;
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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ClienteModel toEntity(ClienteCreation clienteCreation) {
        try {
            ClienteModel clienteModel = new ClienteModel();

            if (Helper.getLong(clienteCreation.getId()) != null)
                clienteModel.setId(Helper.getLong(clienteCreation.getId()));
            clienteModel.setEmail(clienteCreation.getEmail());
            clienteModel.setIdentificacion(clienteCreation.getIdentificacion());
            clienteModel.setNombre(clienteCreation.getNombre());
            clienteModel.setNotas(clienteCreation.getNotas());
            clienteModel.setTelefono(clienteCreation.getTelefono());

            if (Helper.getLong(clienteCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(clienteCreation.getCreador_id()));
                usuario.ifPresent(clienteModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(clienteCreation.getCreada(), "") != null)
                clienteModel.setCreada(Helper.stringToLocalDateTime(clienteCreation.getCreada(), ""));
            if (Helper.getLong(clienteCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(clienteCreation.getModificador_id()));
                usuario.ifPresent(clienteModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(clienteCreation.getModificada(), "") != null)
                clienteModel.setModificada(Helper.stringToLocalDateTime(clienteCreation.getModificada(), ""));
            if (Helper.getLong(clienteCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(clienteCreation.getEliminador_id()));
                usuario.ifPresent(clienteModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(clienteCreation.getEliminada(), "") != null)
                clienteModel.setEliminada(Helper.stringToLocalDateTime(clienteCreation.getEliminada(), ""));

            return clienteModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ClienteDTO toDto(ClienteModel clienteModel) {
        try {
            ClienteDTO dto = new ClienteDTO();

            dto.setId(clienteModel.getId().toString());
            dto.setEmail(clienteModel.getEmail());
            dto.setIdentificacion(clienteModel.getIdentificacion());
            dto.setNombre(clienteModel.getNombre());
            dto.setNotas(clienteModel.getNotas());
            dto.setTelefono(clienteModel.getTelefono());

            if (clienteModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(clienteModel.getCreador()));
            if (Helper.localDateTimeToString(clienteModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(clienteModel.getCreada(), ""));
            if (clienteModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(clienteModel.getModificador()));
            if (Helper.localDateTimeToString(clienteModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(clienteModel.getModificada(), ""));
            if (clienteModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(clienteModel.getEliminador()));
            if (Helper.localDateTimeToString(clienteModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(clienteModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}