package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.mapper.dto.CombustibleDTO;
import gloit.hiperionida.helios.model.CombustibleModel;
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
public class CombustibleMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CombustibleModel toEntity(CombustibleCreation combustibleCreation) {
        try {
            CombustibleModel combustibleModel = new CombustibleModel();

            if (Helper.getLong(combustibleCreation.getId()) != null)
                combustibleModel.setId(Helper.getLong(combustibleCreation.getId()));

            if (Helper.getLong(combustibleCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(combustibleCreation.getCreador_id()));
                if (user.isPresent())
                    combustibleModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(combustibleCreation.getCreada()))
                combustibleModel.setCreada(Helper.stringToLocalDateTime(combustibleCreation.getCreada(), ""));
            if (Helper.getLong(combustibleCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(combustibleCreation.getModificador_id()));
                if (user.isPresent())
                    combustibleModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(combustibleCreation.getModificada()))
                combustibleModel.setModificada(Helper.stringToLocalDateTime(combustibleCreation.getModificada(), ""));
            if (Helper.getLong(combustibleCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(combustibleCreation.getEliminador_id()));
                if (user.isPresent())
                    combustibleModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(combustibleCreation.getEliminada()))
                combustibleModel.setEliminada(Helper.stringToLocalDateTime(combustibleCreation.getEliminada(), ""));

            return combustibleModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CombustibleDTO toDto(CombustibleModel combustibleModel) {
        try {
            CombustibleDTO dto = new CombustibleDTO();

            dto.setId(combustibleModel.getId().toString());


            if (combustibleModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(combustibleModel.getCreador()));
            if (combustibleModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(combustibleModel.getCreada(), ""));
            if (combustibleModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(combustibleModel.getModificador()));
            if (combustibleModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(combustibleModel.getModificada(), ""));
            if (combustibleModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(combustibleModel.getEliminador()));
            if (combustibleModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(combustibleModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}