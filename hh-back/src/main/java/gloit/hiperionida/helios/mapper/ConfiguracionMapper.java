package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ConfiguracionCreation;
import gloit.hiperionida.helios.mapper.dto.ConfiguracionDTO;
import gloit.hiperionida.helios.model.ConfiguracionModel;
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
public class ConfiguracionMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ConfiguracionModel toEntity(ConfiguracionCreation configuracionCreation) {
        try {
            ConfiguracionModel configuracionModel = new ConfiguracionModel();

            if (Helper.getLong(configuracionCreation.getId()) != null)
                configuracionModel.setId(Helper.getLong(configuracionCreation.getId()));

            if (Helper.getLong(configuracionCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(configuracionCreation.getCreador_id()));
                if (user.isPresent())
                    configuracionModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(configuracionCreation.getCreada()))
                configuracionModel.setCreada(Helper.stringToLocalDateTime(configuracionCreation.getCreada(), ""));
            if (Helper.getLong(configuracionCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(configuracionCreation.getModificador_id()));
                if (user.isPresent())
                    configuracionModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(configuracionCreation.getModificada()))
                configuracionModel.setModificada(Helper.stringToLocalDateTime(configuracionCreation.getModificada(), ""));
            if (Helper.getLong(configuracionCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(configuracionCreation.getEliminador_id()));
                if (user.isPresent())
                    configuracionModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(configuracionCreation.getEliminada()))
                configuracionModel.setEliminada(Helper.stringToLocalDateTime(configuracionCreation.getEliminada(), ""));

            return configuracionModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ConfiguracionDTO toDto(ConfiguracionModel configuracionModel) {
        try {
            ConfiguracionDTO dto = new ConfiguracionDTO();

            dto.setId(configuracionModel.getId().toString());


            if (configuracionModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(configuracionModel.getCreador()));
            if (configuracionModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(configuracionModel.getCreada(), ""));
            if (configuracionModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(configuracionModel.getModificador()));
            if (configuracionModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(configuracionModel.getModificada(), ""));
            if (configuracionModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(configuracionModel.getEliminador()));
            if (configuracionModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(configuracionModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}