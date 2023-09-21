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

            private String id;
            private String categoria;

            if (Helper.getLong(categoriaViajeCreation.getId()) != null)
                categoriaViajeModel.setId(Helper.getLong(categoriaViajeCreation.getId()));

            if (Helper.getLong(categoriaViajeCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(categoriaViajeCreation.getCreador_id()));
                if (user.isPresent())
                    categoriaViajeModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(categoriaViajeCreation.getCreada()))
                categoriaViajeModel.setCreada(Helper.stringToLocalDateTime(categoriaViajeCreation.getCreada(), ""));
            if (Helper.getLong(categoriaViajeCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(categoriaViajeCreation.getModificador_id()));
                if (user.isPresent())
                    categoriaViajeModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(categoriaViajeCreation.getModificada()))
                categoriaViajeModel.setModificada(Helper.stringToLocalDateTime(categoriaViajeCreation.getModificada(), ""));
            if (Helper.getLong(categoriaViajeCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(categoriaViajeCreation.getEliminador_id()));
                if (user.isPresent())
                    categoriaViajeModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(categoriaViajeCreation.getEliminada()))
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

            private String id;
            private String categoria;

            dto.setId(categoriaViajeModel.getId().toString());


            if (categoriaViajeModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(categoriaViajeModel.getCreador()));
            if (categoriaViajeModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(categoriaViajeModel.getCreada(), ""));
            if (categoriaViajeModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(categoriaViajeModel.getModificador()));
            if (categoriaViajeModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(categoriaViajeModel.getModificada(), ""));
            if (categoriaViajeModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(categoriaViajeModel.getEliminador()));
            if (categoriaViajeModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(categoriaViajeModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}