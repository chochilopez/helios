package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CajaCreation;
import gloit.hiperionida.helios.mapper.dto.CajaDTO;
import gloit.hiperionida.helios.model.CajaModel;
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
public class CajaMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CajaModel toEntity(CajaCreation cajaCreation) {
        try {
            CajaModel cajaModel = new CajaModel();

            if (Helper.getLong(cajaCreation.getId()) != null)
                cajaModel.setId(Helper.getLong(cajaCreation.getId()));
            cajaModel.setNombre(cajaCreation.getNombre());

            if (Helper.getLong(cajaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(cajaCreation.getCreador_id()));
                usuario.ifPresent(cajaModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(cajaCreation.getCreada(), "") != null)
                cajaModel.setCreada(Helper.stringToLocalDateTime(cajaCreation.getCreada(), ""));
            if (Helper.getLong(cajaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(cajaCreation.getModificador_id()));
                usuario.ifPresent(cajaModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(cajaCreation.getModificada(), "") != null)
                cajaModel.setModificada(Helper.stringToLocalDateTime(cajaCreation.getModificada(), ""));
            if (Helper.getLong(cajaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(cajaCreation.getEliminador_id()));
                usuario.ifPresent(cajaModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(cajaCreation.getEliminada(), "") != null)
                cajaModel.setEliminada(Helper.stringToLocalDateTime(cajaCreation.getEliminada(), ""));

            return cajaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CajaDTO toDto(CajaModel cajaModel) {
        try {
            CajaDTO dto = new CajaDTO();

            dto.setId(cajaModel.getId().toString());
            dto.setNombre(cajaModel.getNombre());

            if (cajaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(cajaModel.getCreador()));
            if (Helper.localDateTimeToString(cajaModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(cajaModel.getCreada(), ""));
            if (cajaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(cajaModel.getModificador()));
            if (Helper.localDateTimeToString(cajaModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(cajaModel.getModificada(), ""));
            if (cajaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(cajaModel.getEliminador()));
            if (Helper.localDateTimeToString(cajaModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(cajaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}