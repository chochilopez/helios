package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.DireccionCreation;
import gloit.hiperionida.helios.mapper.dto.DireccionDTO;
import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.WordUtils;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class DireccionMapper {
    private final UsuarioDAO usuarioDAO;

    public DireccionModel toEntity(DireccionCreation creation) {
        try {
            DireccionModel model = new DireccionModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setCiudad(WordUtils.capitalizeFully(creation.getCiudad()));
            model.setDireccion(creation.getDireccion());
            model.setNombre(creation.getNombre());
            model.setNotas(creation.getNotas());

            if (Helper.getLong(creation.getCreadorId()) != null)
                model.setCreadorId(Helper.getLong(creation.getCreadorId()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getModificadorId()) != null)
                model.setModificadorId(Helper.getLong(creation.getModificadorId()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getEliminadorId()) != null)
                model.setEliminadorId(Helper.getLong(creation.getEliminadorId()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), "yyyy-MM-dd HH:mm:ss"));

            return model;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public DireccionDTO toDto(DireccionModel model) {
        try {
            DireccionDTO dto = new DireccionDTO();

            dto.setId(model.getId().toString());
            dto.setCiudad(model.getCiudad());
            dto.setDireccion(model.getDireccion());
            dto.setNombre(model.getNombre());
            dto.setNotas(model.getNotas());

            if (model.getCreadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreadorId() + "."));
                dto.setCreador(usuarioModel.getNombre());
                dto.setCreadorId(model.getCreadorId().toString());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getModificadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificadorId() + "."));
                dto.setModificador(usuarioModel.getNombre());
                dto.setModificadorId(model.getModificadorId().toString());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getEliminadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminadorId() + "."));
                dto.setEliminador(usuarioModel.getNombre());
                dto.setEliminadorId(model.getEliminadorId().toString());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}