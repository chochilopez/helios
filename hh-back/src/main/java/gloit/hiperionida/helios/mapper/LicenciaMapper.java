package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.LicenciaCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.mapper.dto.LicenciaDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.ConductorDAO;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class LicenciaMapper {
    private final ConductorDAO conductorDAO;
    private final EventoDAO eventoDAO;
    private final UsuarioServiceImpl usuarioService;

    public LicenciaModel toEntity(LicenciaCreation creation) {
        try {
            LicenciaModel model = new LicenciaModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setCategoria(creation.getCategoria());
            if (Helper.getLong(creation.getConductorId()) != null)
                model.setConductorId(Helper.getLong(creation.getConductorId()));
            model.setNotas(creation.getNotas());
            model.setNumero(creation.getNumero());

            if (creation.getVencimiento() != null) {
                if (Helper.getLong(creation.getVencimientoId()) != null) {
                    EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getVencimientoId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el evento."));
                    eventoModel.setInicio(Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""));
                    eventoDAO.save(eventoModel);
                    model.setVencimientoId(Helper.getLong(creation.getVencimientoId()));
                } else {
                    ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getConductorId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el conductor."));
                    EventoModel evento = eventoDAO.save(new EventoModel(
                            Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""),
                            Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""),
                            "Licencia",
                            "Licencia " + model.getCategoria() + "-" + model.getNumero() + " de " + conductorModel.getNombre(),
                            true,
                            Helper.getNow(""),
                            usuarioService.obtenerUsuario().getId()
                    ));
                    model.setVencimientoId(evento.getId());
                }
            }

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

    public LicenciaDTO toDto(LicenciaModel model) {
        try {
            LicenciaDTO dto = new LicenciaDTO();

            dto.setId(model.getId().toString());
            dto.setCategoria(model.getCategoria());
            if (model.getConductorId() != null) {
                ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(model.getConductorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el conductor."));
                dto.setConductor(conductorModel.getNombre());
                dto.setConductorId(model.getConductorId().toString());
            }
            dto.setNotas(model.getNotas());
            dto.setNumero(model.getNumero());
            if (model.getVencimientoId() != null) {
                EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(model.getVencimientoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el vencimiento con id: " + model.getVencimientoId() + "."));
                dto.setVencimiento(eventoModel.getInicio().toString());
                dto.setVencimientoId(model.getVencimientoId().toString());
            }

            if (model.getCreadorId() != null) {
                dto.setCreador(usuarioService.buscarPorId(model.getCreadorId()).getNombre());
                dto.setCreadorId(model.getCreadorId().toString());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getModificadorId() != null) {
                dto.setModificador(usuarioService.buscarPorId(model.getModificadorId()).getNombre());
                dto.setModificadorId(model.getCreadorId().toString());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getEliminadorId() != null) {
                dto.setEliminador(usuarioService.buscarPorId(model.getEliminadorId()).getNombre());

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