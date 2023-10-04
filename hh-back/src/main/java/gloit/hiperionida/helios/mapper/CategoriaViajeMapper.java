package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CategoriaViajeCreation;
import gloit.hiperionida.helios.mapper.dto.CategoriaViajeDTO;
import gloit.hiperionida.helios.model.CategoriaViajeModel;
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
public class CategoriaViajeMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CategoriaViajeModel toEntity(CategoriaViajeCreation categoriaViajeCreation) {
        try {
            CategoriaViajeModel categoriaViajeModel = new CategoriaViajeModel();

            if (Helper.getLong(categoriaViajeCreation.getId()) != null)
                categoriaViajeModel.setId(Helper.getLong(categoriaViajeCreation.getId()));
            categoriaViajeModel.setCategoria(categoriaViajeCreation.getCategoria());

            if (Helper.getLong(categoriaViajeCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(categoriaViajeCreation.getCreador_id()));
                usuario.ifPresent(categoriaViajeModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(categoriaViajeCreation.getCreada(), "") != null)
                categoriaViajeModel.setCreada(Helper.stringToLocalDateTime(categoriaViajeCreation.getCreada(), ""));
            if (Helper.getLong(categoriaViajeCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(categoriaViajeCreation.getModificador_id()));
                usuario.ifPresent(categoriaViajeModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(categoriaViajeCreation.getModificada(), "") != null)
                categoriaViajeModel.setModificada(Helper.stringToLocalDateTime(categoriaViajeCreation.getModificada(), ""));
            if (Helper.getLong(categoriaViajeCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(categoriaViajeCreation.getEliminador_id()));
                usuario.ifPresent(categoriaViajeModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(categoriaViajeCreation.getEliminada(), "") != null)
                categoriaViajeModel.setEliminada(Helper.stringToLocalDateTime(categoriaViajeCreation.getEliminada(), ""));

            return categoriaViajeModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CategoriaViajeDTO toDto(CategoriaViajeModel categoriaViajeModel) {
        try {
            CategoriaViajeDTO dto = new CategoriaViajeDTO();

            dto.setId(categoriaViajeModel.getId().toString());
            dto.setCategoria(categoriaViajeModel.getCategoria());

            if (categoriaViajeModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(categoriaViajeModel.getCreador()));
            if (Helper.localDateTimeToString(categoriaViajeModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(categoriaViajeModel.getCreada(), ""));
            if (categoriaViajeModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(categoriaViajeModel.getModificador()));
            if (Helper.localDateTimeToString(categoriaViajeModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(categoriaViajeModel.getModificada(), ""));
            if (categoriaViajeModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(categoriaViajeModel.getEliminador()));
            if (Helper.localDateTimeToString(categoriaViajeModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(categoriaViajeModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}