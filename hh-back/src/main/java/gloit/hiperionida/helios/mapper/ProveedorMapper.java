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


            if (Helper.getLong(proveedorCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(proveedorCreation.getCreador_id()));
                if (user.isPresent())
                    proveedorModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(proveedorCreation.getCreada()))
                proveedorModel.setCreada(Helper.stringToLocalDateTime(proveedorCreation.getCreada(), ""));
            if (Helper.getLong(proveedorCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(proveedorCreation.getModificador_id()));
                if (user.isPresent())
                    proveedorModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(proveedorCreation.getModificada()))
                proveedorModel.setModificada(Helper.stringToLocalDateTime(proveedorCreation.getModificada(), ""));
            if (Helper.getLong(proveedorCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(proveedorCreation.getEliminador_id()));
                if (user.isPresent())
                    proveedorModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(proveedorCreation.getEliminada()))
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


            if (proveedorModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(proveedorModel.getCreador()));
            if (proveedorModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(proveedorModel.getCreada(), ""));
            if (proveedorModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(proveedorModel.getModificador()));
            if (proveedorModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(proveedorModel.getModificada(), ""));
            if (proveedorModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(proveedorModel.getEliminador()));
            if (proveedorModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(proveedorModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}