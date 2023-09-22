package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ServicioCreation;
import gloit.hiperionida.helios.mapper.dto.ServicioDTO;
import gloit.hiperionida.helios.model.ServicioModel;
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
public class ServicioMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ServicioModel toEntity(ServicioCreation servicioCreation) {
        try {
            ServicioModel servicioModel = new ServicioModel();

            if (Helper.getLong(servicioCreation.getId()) != null)
                servicioModel.setId(Helper.getLong(servicioCreation.getId()));
            if (Helper.getDecimal(servicioCreation.getKmServicio()) != null)
                servicioModel.setKmServicio(Helper.getDecimal(servicioCreation.getKmServicio()));
            if (Helper.getDecimal(servicioCreation.getKmActual()) != null)
                servicioModel.setKmActual(Helper.getDecimal(servicioCreation.getKmActual()));
            if (Helper.getDecimal(servicioCreation.getKmProximo()) != null)
                servicioModel.setKmProximo(Helper.getDecimal(servicioCreation.getKmProximo()));
            servicioModel.setTipo(servicioCreation.getTipo());

            if (Helper.getLong(servicioCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(servicioCreation.getCreador_id()));
                user.ifPresent(servicioModel::setCreador);
            }
            if (!Helper.isEmptyString(servicioCreation.getCreada()))
                servicioModel.setCreada(Helper.stringToLocalDateTime(servicioCreation.getCreada(), ""));
            if (Helper.getLong(servicioCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(servicioCreation.getModificador_id()));
                user.ifPresent(servicioModel::setModificador);
            }
            if (!Helper.isEmptyString(servicioCreation.getModificada()))
                servicioModel.setModificada(Helper.stringToLocalDateTime(servicioCreation.getModificada(), ""));
            if (Helper.getLong(servicioCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(servicioCreation.getEliminador_id()));
                user.ifPresent(servicioModel::setEliminador);
            }
            if (!Helper.isEmptyString(servicioCreation.getEliminada()))
                servicioModel.setEliminada(Helper.stringToLocalDateTime(servicioCreation.getEliminada(), ""));

            return servicioModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ServicioDTO toDto(ServicioModel servicioModel) {
        try {
            ServicioDTO dto = new ServicioDTO();

            dto.setId(servicioModel.getId().toString());
            dto.setKmServicio(servicioModel.getKmServicio().toString());
            dto.setKmActual(servicioModel.getKmActual().toString());
            dto.setKmProximo(servicioModel.getKmProximo().toString());
            dto.setTipo(servicioModel.getTipo());

            if (servicioModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(servicioModel.getCreador()));
            if (servicioModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(servicioModel.getCreada(), ""));
            if (servicioModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(servicioModel.getModificador()));
            if (servicioModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(servicioModel.getModificada(), ""));
            if (servicioModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(servicioModel.getEliminador()));
            if (servicioModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(servicioModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}