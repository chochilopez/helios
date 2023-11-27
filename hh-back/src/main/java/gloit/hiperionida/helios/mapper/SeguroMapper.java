package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.mapper.dto.SeguroDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.AcopladoDAO;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
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
public class SeguroMapper {
    private final AcopladoDAO acopladoDAO;
    private final CamionDAO camionDAO;
    private final EventoDAO eventoDAO;
    private final ProveedorDAO proveedorDAO;
    private final UsuarioServiceImpl usuarioService;

    public SeguroModel toEntity(SeguroCreation creation) {
        try {
            SeguroModel model = new SeguroModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getLong(creation.getAcopladoId()) != null)
                model.setAcopladoId(Helper.getLong(creation.getAcopladoId()));
            if (Helper.getLong(creation.getAseguradoraId()) != null)
                model.setAseguradoraId(Helper.getLong(creation.getAseguradoraId()));
            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            model.setNotas(creation.getNotas());
            if (creation.getVencimiento() != null) {
                if (Helper.getLong(creation.getVencimientoId()) != null) {
                    EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getVencimientoId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el evento."));
                    eventoModel.setInicio(Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""));
                    eventoDAO.save(eventoModel);
                    model.setVencimientoId(Helper.getLong(creation.getVencimientoId()));
                } else {
                    String marcaModelo = "";
                    if (Helper.getLong(creation.getCamionId()) != null) {
                        CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCamionId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el camión."));
                        marcaModelo = marcaModelo + camionModel.getMarcaModelo();
                    } else {
                        AcopladoModel acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getAcopladoId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el acoplado."));
                        marcaModelo = marcaModelo + acopladoModel.getMarcaModelo();
                    }
                    EventoModel evento = eventoDAO.save(new EventoModel(
                            Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""),
                            Helper.stringToLocalDateTime("00:00:00 " + creation.getVencimiento(), ""),
                            "Seguro",
                            "Seguro para " + marcaModelo,
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

    public SeguroDTO toDto(SeguroModel model) {
        try {
            SeguroDTO dto = new SeguroDTO();

            dto.setId(model.getId().toString());
            dto.setNotas(model.getNotas());
            if (model.getAcopladoId() != null) {
                AcopladoModel acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(model.getAcopladoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el acoplado con id: " + model.getAcopladoId() + "."));
                dto.setAcoplado(acopladoModel.getMarcaModelo());
                dto.setAcopladoId(model.getAcopladoId().toString());
            }
            if (model.getAseguradoraId() != null) {
                ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(model.getAseguradoraId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la asegurador con id: " + model.getAseguradoraId() + "."));
                dto.setAseguradora(proveedorModel.getNombre());
                dto.setAseguradoraId(model.getAseguradoraId().toString());
            }
            if (model.getCamionId() != null) {
                CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getCamionId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el camión con id: " + model.getCamionId() + "."));
                dto.setCamion(camionModel.getMarcaModelo());
                dto.setCamionId(model.getCamionId().toString());
            }
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