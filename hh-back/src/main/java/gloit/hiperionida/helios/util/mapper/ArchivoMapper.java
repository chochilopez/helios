package gloit.hiperionida.helios.util.mapper;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.creation.ArchivoCreation;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import gloit.hiperionida.helios.util.model.ArchivoModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.ArchivoDAO;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ArchivoMapper {
    public final ArchivoDAO archivoDAO;
    public final UsuarioDAO usuarioDAO;

    public ArchivoModel toEntity(ArchivoCreation archivoCreation) {
        try {
            ArchivoModel archivoModel = new ArchivoModel();

            if (Helper.getLong(archivoCreation.getId()) != null) {
                archivoModel = archivoDAO.findByIdAndEliminadaIsNull(Helper.getLong(archivoCreation.getId())).get();
            }

            archivoModel.setPath(archivoCreation.getPath());
            archivoModel.setNombre(archivoCreation.getNombre());

            if (Helper.getLong(archivoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(archivoCreation.getCreador_id()));
                if (user.isPresent())
                    archivoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(archivoCreation.getCreada()))
                archivoModel.setCreada(Helper.stringToLocalDateTime(archivoCreation.getCreada(), ""));
            if (Helper.getLong(archivoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(archivoCreation.getModificador_id()));
                if (user.isPresent())
                    archivoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(archivoCreation.getModificada()))
                archivoModel.setModificada(Helper.stringToLocalDateTime(archivoCreation.getModificada(), ""));
            if (Helper.getLong(archivoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(archivoCreation.getEliminador_id()));
                if (user.isPresent())
                    archivoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(archivoCreation.getEliminada()))
                archivoModel.setEliminada(Helper.stringToLocalDateTime(archivoCreation.getEliminada(), ""));

            return archivoModel;
        } catch (Exception e) {
            log.info("Archivo creation to entity error. Exception: " + e);
            return null;
        }
    }

    public ArchivoDTO toDto(ArchivoModel archivoModel) {
        try {
            ArchivoDTO dto = new ArchivoDTO();

            dto.setId(archivoModel.getId().toString());
            dto.setPath(archivoModel.getPath());
            dto.setNombre(archivoModel.getNombre());

            return dto;
        } catch (Exception e) {
            log.info("Archivo entity to dto error. Exception: " + e);
            return null;
        }
    }
}