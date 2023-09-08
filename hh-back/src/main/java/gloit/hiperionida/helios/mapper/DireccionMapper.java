package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.DireccionCreation;
import gloit.hiperionida.helios.mapper.dto.DireccionDTO;
import gloit.hiperionida.helios.model.DireccionModel;
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
public class DireccionMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public DireccionModel toEntity(DireccionCreation direccionCreation) {
        try {
            DireccionModel direccionModel = new DireccionModel();

            if (Helper.getLong(direccionCreation.getId()) != null)
                direccionModel.setId(Helper.getLong(direccionCreation.getId()));

            if (Helper.getLong(direccionCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(direccionCreation.getCreador_id()));
                if (user.isPresent())
                    direccionModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(direccionCreation.getCreada()))
                direccionModel.setCreada(Helper.stringToLocalDateTime(direccionCreation.getCreada(), ""));
            if (Helper.getLong(direccionCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(direccionCreation.getModificador_id()));
                if (user.isPresent())
                    direccionModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(direccionCreation.getModificada()))
                direccionModel.setModificada(Helper.stringToLocalDateTime(direccionCreation.getModificada(), ""));
            if (Helper.getLong(direccionCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(direccionCreation.getEliminador_id()));
                if (user.isPresent())
                    direccionModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(direccionCreation.getEliminada()))
                direccionModel.setEliminada(Helper.stringToLocalDateTime(direccionCreation.getEliminada(), ""));

            return direccionModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public DireccionDTO toDto(DireccionModel direccionModel) {
        try {
            DireccionDTO dto = new DireccionDTO();

            dto.setId(direccionModel.getId().toString());


            if (direccionModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(direccionModel.getCreador()));
            if (direccionModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(direccionModel.getCreada(), ""));
            if (direccionModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(direccionModel.getModificador()));
            if (direccionModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(direccionModel.getModificada(), ""));
            if (direccionModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(direccionModel.getEliminador()));
            if (direccionModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(direccionModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}