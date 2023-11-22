package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.mapper.dto.AdelantoDTO;
import gloit.hiperionida.helios.mapper.dto.CajaDTO;
import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.model.CajaModel;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.repository.CajaDAO;
import gloit.hiperionida.helios.repository.ConductorDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class AdelantoMapper {
    private final CajaDAO cajaDAO;
    private final ConductorDAO conductorDAO;
    private final UsuarioDAO usuarioDAO;

    public AdelantoModel toEntity(AdelantoCreation creation) {
        try {
            AdelantoModel model = new AdelantoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setDescripcion(creation.getDescripcion());
            if (creation.getFecha() != null && Helper.stringToLocalDateTime(creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime(creation.getFecha(), ""));
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getNDecimal(Helper.getDecimal(creation.getMonto()),2));
            model.setNotas(creation.getNotas());
            model.setRecibo(creation.getRecibo());
            if (Helper.getBoolean(creation.getRendido()) != null)
                model.setRendido(Helper.getBoolean(creation.getRendido()));

            if (Helper.getLong(creation.getCaja_id()) != null)
                model.setCajaId(Helper.getLong(creation.getCaja_id()));
            if (Helper.getLong(creation.getConductorId()) != null)
                model.setConductorId(Helper.getLong(creation.getConductorId()));

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

    public AdelantoDTO toDto(AdelantoModel model) {
        try {
            AdelantoDTO dto = new AdelantoDTO();

            dto.setId(model.getId().toString());
            dto.setDescripcion(model.getDescripcion());
            dto.setFecha(model.getFecha().toString());
            dto.setMonto(model.getMonto().toString());
            dto.setNotas(model.getNotas());
            dto.setRecibo(model.getRecibo());
            dto.setRendido(model.getRendido().toString());

            if (model.getCajaId() != null) {
                CajaModel cajaModel = cajaDAO.findByIdAndEliminadaIsNull(model.getCajaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la caja con id: " + model.getCajaId() + "."));
                dto.setCaja(cajaModel.getCaja());
            }
            if (model.getConductorId() != null) {
                ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(model.getConductorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el conductor con id: " + model.getConductorId() + "."));
                dto.setConductor(conductorModel.getNombre());
            }

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