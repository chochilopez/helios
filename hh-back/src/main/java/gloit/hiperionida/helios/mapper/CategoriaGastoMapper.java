package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CategoriaGastoCreation;
import gloit.hiperionida.helios.mapper.dto.CategoriaGastoDTO;
import gloit.hiperionida.helios.model.CategoriaGastoModel;
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
public class CategoriaGastoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CategoriaGastoModel toEntity(CategoriaGastoCreation categoriaGastoCreation) {
        try {
            CategoriaGastoModel categoriaGastoModel = new CategoriaGastoModel();

            if (Helper.getLong(categoriaGastoCreation.getId()) != null)
                categoriaGastoModel.setId(Helper.getLong(categoriaGastoCreation.getId()));
            categoriaGastoModel.setCategoria(categoriaGastoCreation.getCategoria());

            if (Helper.getLong(categoriaGastoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(categoriaGastoCreation.getCreador_id()));
                user.ifPresent(categoriaGastoModel::setCreador);
            }
            if (!Helper.isEmptyString(categoriaGastoCreation.getCreada()))
                categoriaGastoModel.setCreada(Helper.stringToLocalDateTime(categoriaGastoCreation.getCreada(), ""));
            if (Helper.getLong(categoriaGastoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(categoriaGastoCreation.getModificador_id()));
                user.ifPresent(categoriaGastoModel::setModificador);
            }
            if (!Helper.isEmptyString(categoriaGastoCreation.getModificada()))
                categoriaGastoModel.setModificada(Helper.stringToLocalDateTime(categoriaGastoCreation.getModificada(), ""));
            if (Helper.getLong(categoriaGastoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(categoriaGastoCreation.getEliminador_id()));
                user.ifPresent(categoriaGastoModel::setEliminador);
            }
            if (!Helper.isEmptyString(categoriaGastoCreation.getEliminada()))
                categoriaGastoModel.setEliminada(Helper.stringToLocalDateTime(categoriaGastoCreation.getEliminada(), ""));

            return categoriaGastoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CategoriaGastoDTO toDto(CategoriaGastoModel categoriaGastoModel) {
        try {
            CategoriaGastoDTO dto = new CategoriaGastoDTO();

            dto.setId(categoriaGastoModel.getId().toString());
            dto.setCategoria(categoriaGastoModel.getCategoria());

            if (categoriaGastoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(categoriaGastoModel.getCreador()));
            if (categoriaGastoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(categoriaGastoModel.getCreada(), ""));
            if (categoriaGastoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(categoriaGastoModel.getModificador()));
            if (categoriaGastoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(categoriaGastoModel.getModificada(), ""));
            if (categoriaGastoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(categoriaGastoModel.getEliminador()));
            if (categoriaGastoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(categoriaGastoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}