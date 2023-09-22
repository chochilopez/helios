package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.RemitoCreation;
import gloit.hiperionida.helios.mapper.dto.RemitoDTO;
import gloit.hiperionida.helios.model.RemitoModel;
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
public class RemitoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public RemitoModel toEntity(RemitoCreation remitoCreation) {
        try {
            RemitoModel remitoModel = new RemitoModel();

            if (Helper.getLong(remitoCreation.getId()) != null)
                remitoModel.setId(Helper.getLong(remitoCreation.getId()));
            remitoModel.setDescripcion(remitoCreation.getDescripcion());
            if (remitoCreation.getFecha() != null && Helper.stringToLocalDateTime(remitoCreation.getFecha(), "") != null)
                remitoModel.setFecha(Helper.stringToLocalDateTime(remitoCreation.getFecha(), ""));
            remitoModel.setNumero(remitoCreation.getNumero());

            if (Helper.getLong(remitoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(remitoCreation.getCreador_id()));
                if (user.isPresent())
                    remitoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(remitoCreation.getCreada()))
                remitoModel.setCreada(Helper.stringToLocalDateTime(remitoCreation.getCreada(), ""));
            if (Helper.getLong(remitoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(remitoCreation.getModificador_id()));
                if (user.isPresent())
                    remitoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(remitoCreation.getModificada()))
                remitoModel.setModificada(Helper.stringToLocalDateTime(remitoCreation.getModificada(), ""));
            if (Helper.getLong(remitoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(remitoCreation.getEliminador_id()));
                if (user.isPresent())
                    remitoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(remitoCreation.getEliminada()))
                remitoModel.setEliminada(Helper.stringToLocalDateTime(remitoCreation.getEliminada(), ""));

            return remitoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public RemitoDTO toDto(RemitoModel remitoModel) {
        try {
            RemitoDTO dto = new RemitoDTO();

            dto.setId(remitoModel.getId().toString());
            dto.setDescripcion(remitoModel.getDescripcion());
            dto.setFecha(remitoModel.getFecha().toString());
            dto.setNumero(remitoModel.getNumero());

            if (remitoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(remitoModel.getCreador()));
            if (remitoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(remitoModel.getCreada(), ""));
            if (remitoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(remitoModel.getModificador()));
            if (remitoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(remitoModel.getModificada(), ""));
            if (remitoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(remitoModel.getEliminador()));
            if (remitoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(remitoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}