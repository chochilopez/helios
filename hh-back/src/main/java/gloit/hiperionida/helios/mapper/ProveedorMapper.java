package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ProveedorCreation;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.model.ProveedorModel;
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
public class ProveedorMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ProveedorModel toEntity(ProveedorCreation proveedorCreation) {
        try {
            ProveedorModel proveedorModel = new ProveedorModel();

            if (Helper.getLong(proveedorCreation.getId()) != null)
                proveedorModel.setId(Helper.getLong(proveedorCreation.getId()));
            proveedorModel.setDireccion(proveedorCreation.getDireccion());
            proveedorModel.setEmail(proveedorCreation.getEmail());
            proveedorModel.setIdentificacion(proveedorCreation.getIdentificacion());
            proveedorModel.setNombre(proveedorCreation.getNombre());
            proveedorModel.setNotas(proveedorCreation.getNotas());
            proveedorModel.setTelefono(proveedorCreation.getTelefono());

            if (Helper.getLong(proveedorCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(proveedorCreation.getCreador_id()));
                usuario.ifPresent(proveedorModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(proveedorCreation.getCreada(), "") != null)
                proveedorModel.setCreada(Helper.stringToLocalDateTime(proveedorCreation.getCreada(), ""));
            if (Helper.getLong(proveedorCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(proveedorCreation.getModificador_id()));
                usuario.ifPresent(proveedorModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(proveedorCreation.getModificada(), "") != null)
                proveedorModel.setModificada(Helper.stringToLocalDateTime(proveedorCreation.getModificada(), ""));
            if (Helper.getLong(proveedorCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(proveedorCreation.getEliminador_id()));
                usuario.ifPresent(proveedorModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(proveedorCreation.getEliminada(), "") != null)
                proveedorModel.setEliminada(Helper.stringToLocalDateTime(proveedorCreation.getEliminada(), ""));

            return proveedorModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ProveedorDTO toDto(ProveedorModel proveedorModel) {
        try {
            ProveedorDTO dto = new ProveedorDTO();

            dto.setId(proveedorModel.getId().toString());

            dto.setDireccion(proveedorModel.getDireccion());
            dto.setEmail(proveedorModel.getEmail());
            dto.setIdentificacion(proveedorModel.getIdentificacion());
            dto.setNombre(proveedorModel.getNombre());
            dto.setNotas(proveedorModel.getNotas());
            dto.setTelefono(proveedorModel.getTelefono());

            if (proveedorModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(proveedorModel.getCreador()));
            if (Helper.localDateTimeToString(proveedorModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(proveedorModel.getCreada(), ""));
            if (proveedorModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(proveedorModel.getModificador()));
            if (Helper.localDateTimeToString(proveedorModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(proveedorModel.getModificada(), ""));
            if (proveedorModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(proveedorModel.getEliminador()));
            if (Helper.localDateTimeToString(proveedorModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(proveedorModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}