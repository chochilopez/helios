package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.DireccionCreation;
import gloit.hiperionida.helios.mapper.dto.DireccionDTO;
import gloit.hiperionida.helios.mapper.dto.UbicacionDTO;
import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.model.UbicacionModel;
import gloit.hiperionida.helios.repository.UbicacionDAO;
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
    private final UbicacionDAO ubicacionDAO;
    private final UbicacionMapper ubicacionMapper;

    public DireccionModel toEntity(DireccionCreation direccionCreation) {
        try {
            DireccionModel direccionModel = new DireccionModel();

            if (Helper.getLong(direccionCreation.getId()) != null)
                direccionModel.setId(Helper.getLong(direccionCreation.getId()));
            direccionModel.setDireccion(direccionCreation.getDireccion());
            direccionModel.setNombre(direccionCreation.getNombre());
            if (Helper.getLong(direccionCreation.getUbicacion_id()) != null) {
                Optional<UbicacionModel> ubicacion = ubicacionDAO.findById(Helper.getLong(direccionCreation.getUbicacion_id()));
                ubicacion.ifPresent(direccionModel::setUbicacion);
            }

            if (Helper.getLong(direccionCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(direccionCreation.getCreador_id()));
                user.ifPresent(direccionModel::setCreador);
            }
            if (!Helper.isEmptyString(direccionCreation.getCreada()))
                direccionModel.setCreada(Helper.stringToLocalDateTime(direccionCreation.getCreada(), ""));
            if (Helper.getLong(direccionCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(direccionCreation.getModificador_id()));
                user.ifPresent(direccionModel::setModificador);
            }
            if (!Helper.isEmptyString(direccionCreation.getModificada()))
                direccionModel.setModificada(Helper.stringToLocalDateTime(direccionCreation.getModificada(), ""));
            if (Helper.getLong(direccionCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(direccionCreation.getEliminador_id()));
                user.ifPresent(direccionModel::setEliminador);
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
            dto.setDireccion(direccionModel.getDireccion());
            dto.setNombre(direccionModel.getNombre());
            if (direccionModel.getUbicacion() != null)
                dto.setUbicacion(ubicacionMapper.toDto(direccionModel.getUbicacion()));

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