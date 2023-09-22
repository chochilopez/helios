package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.MovimientoCreation;
import gloit.hiperionida.helios.mapper.dto.MovimientoDTO;
import gloit.hiperionida.helios.model.MovimientoModel;
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
public class MovimientoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public MovimientoModel toEntity(MovimientoCreation movimientoCreation) {
        try {
            MovimientoModel movimientoModel = new MovimientoModel();

            if (Helper.getLong(movimientoCreation.getId()) != null)
                movimientoModel.setId(Helper.getLong(movimientoCreation.getId()));

            if (Helper.getLong(movimientoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(movimientoCreation.getCreador_id()));
                user.ifPresent(movimientoModel::setCreador);
            }
            if (!Helper.isEmptyString(movimientoCreation.getCreada()))
                movimientoModel.setCreada(Helper.stringToLocalDateTime(movimientoCreation.getCreada(), ""));
            if (Helper.getLong(movimientoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(movimientoCreation.getModificador_id()));
                user.ifPresent(movimientoModel::setModificador);
            }
            if (!Helper.isEmptyString(movimientoCreation.getModificada()))
                movimientoModel.setModificada(Helper.stringToLocalDateTime(movimientoCreation.getModificada(), ""));
            if (Helper.getLong(movimientoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(movimientoCreation.getEliminador_id()));
                user.ifPresent(movimientoModel::setEliminador);
            }
            if (!Helper.isEmptyString(movimientoCreation.getEliminada()))
                movimientoModel.setEliminada(Helper.stringToLocalDateTime(movimientoCreation.getEliminada(), ""));

            return movimientoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public MovimientoDTO toDto(MovimientoModel movimientoModel) {
        try {
            MovimientoDTO dto = new MovimientoDTO();

            dto.setId(movimientoModel.getId().toString());

            if (movimientoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(movimientoModel.getCreador()));
            if (movimientoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(movimientoModel.getCreada(), ""));
            if (movimientoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(movimientoModel.getModificador()));
            if (movimientoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(movimientoModel.getModificada(), ""));
            if (movimientoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(movimientoModel.getEliminador()));
            if (movimientoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(movimientoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}