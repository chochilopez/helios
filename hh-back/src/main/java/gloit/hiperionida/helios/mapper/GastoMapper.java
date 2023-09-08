package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.GastoCreation;
import gloit.hiperionida.helios.mapper.dto.GastoDTO;
import gloit.hiperionida.helios.model.GastoModel;
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
public class GastoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public GastoModel toEntity(GastoCreation gastoCreation) {
        try {
            GastoModel gastoModel = new GastoModel();

            if (Helper.getLong(gastoCreation.getId()) != null)
                gastoModel.setId(Helper.getLong(gastoCreation.getId()));

            if (Helper.getLong(gastoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(gastoCreation.getCreador_id()));
                if (user.isPresent())
                    gastoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(gastoCreation.getCreada()))
                gastoModel.setCreada(Helper.stringToLocalDateTime(gastoCreation.getCreada(), ""));
            if (Helper.getLong(gastoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(gastoCreation.getModificador_id()));
                if (user.isPresent())
                    gastoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(gastoCreation.getModificada()))
                gastoModel.setModificada(Helper.stringToLocalDateTime(gastoCreation.getModificada(), ""));
            if (Helper.getLong(gastoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(gastoCreation.getEliminador_id()));
                if (user.isPresent())
                    gastoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(gastoCreation.getEliminada()))
                gastoModel.setEliminada(Helper.stringToLocalDateTime(gastoCreation.getEliminada(), ""));

            return gastoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public GastoDTO toDto(GastoModel gastoModel) {
        try {
            GastoDTO dto = new GastoDTO();

            dto.setId(gastoModel.getId().toString());


            if (gastoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(gastoModel.getCreador()));
            if (gastoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(gastoModel.getCreada(), ""));
            if (gastoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(gastoModel.getModificador()));
            if (gastoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(gastoModel.getModificada(), ""));
            if (gastoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(gastoModel.getEliminador()));
            if (gastoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(gastoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}