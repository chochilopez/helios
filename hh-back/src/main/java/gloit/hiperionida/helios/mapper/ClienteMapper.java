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

            if (Helper.getLong(clienteCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(clienteCreation.getCreador_id()));
                if (user.isPresent())
                    clienteModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(clienteCreation.getCreada()))
                clienteModel.setCreada(Helper.stringToLocalDateTime(clienteCreation.getCreada(), ""));
            if (Helper.getLong(clienteCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(clienteCreation.getModificador_id()));
                if (user.isPresent())
                    clienteModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(clienteCreation.getModificada()))
                clienteModel.setModificada(Helper.stringToLocalDateTime(clienteCreation.getModificada(), ""));
            if (Helper.getLong(clienteCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(clienteCreation.getEliminador_id()));
                if (user.isPresent())
                    clienteModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(clienteCreation.getEliminada()))
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


            if (clienteModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(clienteModel.getCreador()));
            if (clienteModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(clienteModel.getCreada(), ""));
            if (clienteModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(clienteModel.getModificador()));
            if (clienteModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(clienteModel.getModificada(), ""));
            if (clienteModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(clienteModel.getEliminador()));
            if (clienteModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(clienteModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}