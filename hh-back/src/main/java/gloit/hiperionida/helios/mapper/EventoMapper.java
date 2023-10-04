package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.EventoCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.model.EventoModel;
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
public class EventoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public EventoModel toEntity(EventoCreation eventoCreation) {
        try {
            EventoModel eventoModel = new EventoModel();

            if (Helper.getLong(eventoCreation.getId()) != null)
                eventoModel.setId(Helper.getLong(eventoCreation.getId()));
            if (eventoCreation.getFecha() != null && Helper.stringToLocalDateTime(eventoCreation.getFecha(), "") != null)
                eventoModel.setFecha(Helper.stringToLocalDateTime(eventoCreation.getFecha(), ""));
            eventoModel.setDescripcion(eventoCreation.getDescripcion());
            if (eventoCreation.getHabilitada() != null)
                eventoModel.setHabilitada(Helper.getBoolean(eventoCreation.getHabilitada()));
            if (eventoCreation.getRecordatorio() != null)
                eventoModel.setRecordatorio(Helper.getBoolean(eventoCreation.getRecordatorio()));
            eventoModel.setNombre(eventoCreation.getNombre());

            if (Helper.getLong(eventoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(eventoCreation.getCreador_id()));
                usuario.ifPresent(eventoModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(eventoCreation.getCreada(), "") != null)
                eventoModel.setCreada(Helper.stringToLocalDateTime(eventoCreation.getCreada(), ""));
            if (Helper.getLong(eventoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(eventoCreation.getModificador_id()));
                usuario.ifPresent(eventoModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(eventoCreation.getModificada(), "") != null)
                eventoModel.setModificada(Helper.stringToLocalDateTime(eventoCreation.getModificada(), ""));
            if (Helper.getLong(eventoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(eventoCreation.getEliminador_id()));
                usuario.ifPresent(eventoModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(eventoCreation.getEliminada(), "") != null)
                eventoModel.setEliminada(Helper.stringToLocalDateTime(eventoCreation.getEliminada(), ""));

            return eventoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public EventoDTO toDto(EventoModel eventoModel) {
        try {
            EventoDTO dto = new EventoDTO();

            dto.setId(eventoModel.getId().toString());
            dto.setFecha(eventoModel.getFecha().toString());
            dto.setDescripcion(eventoModel.getDescripcion());
            dto.setHabilitada(eventoModel.getHabilitada().toString());
            dto.setRecordatorio(eventoModel.getRecordatorio().toString());
            dto.setNombre(eventoModel.getNombre());

            if (eventoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(eventoModel.getCreador()));
            if (Helper.localDateTimeToString(eventoModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(eventoModel.getCreada(), ""));
            if (eventoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(eventoModel.getModificador()));
            if (Helper.localDateTimeToString(eventoModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(eventoModel.getModificada(), ""));
            if (eventoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(eventoModel.getEliminador()));
            if (Helper.localDateTimeToString(eventoModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(eventoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}