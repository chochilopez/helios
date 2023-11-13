package gloit.hiperionida.helios.util.mapper;

import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.creation.ArchivoCreation;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
import gloit.hiperionida.helios.util.model.ArchivoModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.model.enums.TipoArchivoEnum;
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

    public ArchivoModel toEntity(ArchivoCreation creation) {
        try {
            ArchivoModel model = new ArchivoModel();

            if (Helper.getLong(creation.getId()) != null) {
                model = archivoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getId())).get();
            }
            model.setDescripcion(creation.getDescripcion());
            model.setNombre(creation.getNombre());
            model.setPath(creation.getPath());
            model.setTamanio(creation.getTamanio());
            if (creation.getTipo() != null)
                model.setTipo(TipoArchivoEnum.valueOf(creation.getTipo()));

            if (Helper.getLong(creation.getCreador_id()) != null)
                model.setCreador_id(Helper.getLong(creation.getCreador_id()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), ""));
            if (Helper.getLong(creation.getModificador_id()) != null)
                model.setModificador_id(Helper.getLong(creation.getModificador_id()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), ""));
            if (Helper.getLong(creation.getEliminador_id()) != null)
                model.setEliminador_id(Helper.getLong(creation.getEliminador_id()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), ""));

            return model;
        } catch (Exception e) {
            log.info("Archivo creation to entity error. Exception: " + e);
            return null;
        }
    }

    public ArchivoDTO toDto(ArchivoModel model) {
        try {
            ArchivoDTO dto = new ArchivoDTO();

            dto.setId(model.getId().toString());
            dto.setDescripcion(model.getDescripcion());
            dto.setNombre(model.getNombre());
            dto.setPath(model.getPath());
            dto.setTamanio(model.getTamanio());
            dto.setTipo(model.getTipo().toString());

            if (model.getCreador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreador_id() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificador_id() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminador_id() + "."));
                dto.setEliminador(usuarioModel.getNombre());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.info("Archivo entity to dto error. Exception: " + e);
            return null;
        }
    }
}