package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PlanCuentaCreation;
import gloit.hiperionida.helios.mapper.dto.PlanCuentaDTO;
import gloit.hiperionida.helios.model.PlanCuentaModel;
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
public class PlanCuentaMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public PlanCuentaModel toEntity(PlanCuentaCreation planCuentaCreation) {
        try {
            PlanCuentaModel planCuentaModel = new PlanCuentaModel();

            if (Helper.getLong(planCuentaCreation.getId()) != null)
                planCuentaModel.setId(Helper.getLong(planCuentaCreation.getId()));
            planCuentaModel.setNombre(planCuentaCreation.getNombre());

            if (Helper.getLong(planCuentaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(planCuentaCreation.getCreador_id()));
                usuario.ifPresent(planCuentaModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(planCuentaCreation.getCreada(), "") != null)
                planCuentaModel.setCreada(Helper.stringToLocalDateTime(planCuentaCreation.getCreada(), ""));
            if (Helper.getLong(planCuentaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(planCuentaCreation.getModificador_id()));
                usuario.ifPresent(planCuentaModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(planCuentaCreation.getModificada(), "") != null)
                planCuentaModel.setModificada(Helper.stringToLocalDateTime(planCuentaCreation.getModificada(), ""));
            if (Helper.getLong(planCuentaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(planCuentaCreation.getEliminador_id()));
                usuario.ifPresent(planCuentaModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(planCuentaCreation.getEliminada(), "") != null)
                planCuentaModel.setEliminada(Helper.stringToLocalDateTime(planCuentaCreation.getEliminada(), ""));

            return planCuentaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public PlanCuentaDTO toDto(PlanCuentaModel planCuentaModel) {
        try {
            PlanCuentaDTO dto = new PlanCuentaDTO();

            dto.setId(planCuentaModel.getId().toString());
            dto.setNombre(planCuentaModel.getNombre());

            if (planCuentaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(planCuentaModel.getCreador()));
            if (Helper.localDateTimeToString(planCuentaModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(planCuentaModel.getCreada(), ""));
            if (planCuentaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(planCuentaModel.getModificador()));
            if (Helper.localDateTimeToString(planCuentaModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(planCuentaModel.getModificada(), ""));
            if (planCuentaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(planCuentaModel.getEliminador()));
            if (Helper.localDateTimeToString(planCuentaModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(planCuentaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}