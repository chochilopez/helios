package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ReciboCreation;
import gloit.hiperionida.helios.mapper.dto.ReciboDTO;
import gloit.hiperionida.helios.model.ReciboModel;
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
public class ReciboMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ReciboModel toEntity(ReciboCreation reciboCreation) {
        try {
            ReciboModel reciboModel = new ReciboModel();

            if (Helper.getLong(reciboCreation.getId()) != null)
                reciboModel.setId(Helper.getLong(reciboCreation.getId()));
            if (reciboCreation.getFecha() != null && Helper.stringToLocalDateTime(reciboCreation.getFecha(), "") != null)
                reciboModel.setFecha(Helper.stringToLocalDateTime(reciboCreation.getFecha(), ""));
            if (Helper.getDecimal(reciboCreation.getMonto()) != null)
                reciboModel.setMonto(Helper.getDecimal(reciboCreation.getMonto()));

            if (Helper.getLong(reciboCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(reciboCreation.getCreador_id()));
                user.ifPresent(reciboModel::setCreador);
            }
            if (!Helper.isEmptyString(reciboCreation.getCreada()))
                reciboModel.setCreada(Helper.stringToLocalDateTime(reciboCreation.getCreada(), ""));
            if (Helper.getLong(reciboCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(reciboCreation.getModificador_id()));
                user.ifPresent(reciboModel::setModificador);
            }
            if (!Helper.isEmptyString(reciboCreation.getModificada()))
                reciboModel.setModificada(Helper.stringToLocalDateTime(reciboCreation.getModificada(), ""));
            if (Helper.getLong(reciboCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(reciboCreation.getEliminador_id()));
                user.ifPresent(reciboModel::setEliminador);
            }
            if (!Helper.isEmptyString(reciboCreation.getEliminada()))
                reciboModel.setEliminada(Helper.stringToLocalDateTime(reciboCreation.getEliminada(), ""));

            return reciboModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ReciboDTO toDto(ReciboModel reciboModel) {
        try {
            ReciboDTO dto = new ReciboDTO();

            dto.setId(reciboModel.getId().toString());
            dto.setFecha(reciboModel.getFecha().toString());
            dto.setMonto(reciboModel.getMonto().toString());

            if (reciboModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(reciboModel.getCreador()));
            if (reciboModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(reciboModel.getCreada(), ""));
            if (reciboModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(reciboModel.getModificador()));
            if (reciboModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(reciboModel.getModificada(), ""));
            if (reciboModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(reciboModel.getEliminador()));
            if (reciboModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(reciboModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}