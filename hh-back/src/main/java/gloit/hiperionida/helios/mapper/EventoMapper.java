package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.EventoCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventoMapper {
    private final UsuarioDAO usuarioDAO;

    public EventoModel toEntity(EventoCreation creation) {
        System.out.println(Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), ""));
        System.out.println(creation.getFecha() != null);
        try {
            EventoModel model = new EventoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setDescripcion(creation.getDescripcion());
            if (creation.getFecha() != null && Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), "") != null) {
                model.setFecha(Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), ""));
                if (Helper.getInteger(creation.getRecordatorioDias()) != null) {
                    model.setRecordatorioDias(Helper.getInteger(creation.getRecordatorioDias()));
                    model.setRecordatorioFecha(model.getFecha().minusDays(model.getRecordatorioDias()));
                }
            }
            model.setNombre(creation.getNombre());
            if (Helper.getBoolean(creation.getHabilitada()) != null)
                model.setHabilitada(Helper.getBoolean(creation.getHabilitada()));
            else
                model.setHabilitada(true);

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

    public EventoDTO toDto(EventoModel model) {
        try {
            EventoDTO dto = new EventoDTO();

            dto.setId(model.getId().toString());
            dto.setDescripcion(model.getDescripcion());
            dto.setNombre(model.getNombre());
            if (model.getFecha() != null)
                dto.setFecha(model.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getRecordatorioFecha() != null)
                dto.setRecordatorioFecha(model.getRecordatorioFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getRecordatorioDias() != null)
                dto.setRecordatorioDias(model.getRecordatorioDias().toString());
            if (model.getHabilitada() != null)
                dto.setHabilitada(model.getHabilitada().toString());

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