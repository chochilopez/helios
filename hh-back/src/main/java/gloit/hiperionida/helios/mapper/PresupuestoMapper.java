package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PresupuestoMapper {
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final ClienteDAO clienteDAO;
    private final DireccionDAO direccionDAO;
    private final EventoDAO eventoDAO;
    private final UsuarioServiceImpl usuarioService;

    public PresupuestoModel toEntity(PresupuestoCreation creation) {
        try {
            PresupuestoModel model = new PresupuestoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getInteger(creation.getCantidadTransportada()) != null)
                model.setCantidadTransportada(Helper.getInteger(creation.getCantidadTransportada()));
            if (Helper.getLong(creation.getCategoriaViajeId()) != null)
                model.setCategoriaViajeId(Helper.getLong(creation.getCategoriaViajeId()));
            if (Helper.getLong(creation.getCompradorId()) != null)
                model.setCompradorId(Helper.getLong(creation.getCompradorId()));
            if (Helper.getLong(creation.getDestinoId()) != null)
                model.setDestinoId(Helper.getLong(creation.getDestinoId()));
            if (creation.getFecha() != null) {
                if (Helper.getLong(creation.getFechaId()) != null) {
                    EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getFechaId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el evento."));
                    eventoModel.setFecha(Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), ""));
                    eventoDAO.save(eventoModel);
                    model.setFechaId(Helper.getLong(creation.getFechaId()));
                } else {
                    ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCompradorId())).orElseThrow(() -> new DatosInexistentesException("No se encontró el cliente."));
                    EventoModel evento = eventoDAO.save(new EventoModel(
                            Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), ""),
                            "Presupuesto para " + clienteModel.getNombre(),
                            true,
                            true,
                            "Presupuesto",
                            Helper.getNow(""),
                            usuarioService.obtenerUsuario().getId()
                    ));
                    model.setFechaId(evento.getId());
                }
            }
            if (Helper.getDecimal(creation.getKmCargado()) != null)
                model.setKmCargado(Helper.getDecimal(creation.getKmCargado()));
            model.setNotas(creation.getNotas());
            if (Helper.getLong(creation.getOrigenId()) != null)
                model.setOrigenId(Helper.getLong(creation.getOrigenId()));
            if (Helper.getInteger(creation.getValidez()) != null)
                model.setValidez(Helper.getInteger(creation.getValidez()));
            if (Helper.getDecimal(creation.getValorKm()) != null)
                model.setValorKm(Helper.getDecimal(creation.getValorKm()));

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

    public PresupuestoDTO toDto(PresupuestoModel model) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            PresupuestoDTO dto = new PresupuestoDTO();

            dto.setId(model.getId().toString());
            if (model.getCantidadTransportada() != null)
                dto.setCantidadTransportada(model.getCantidadTransportada().toString());

            if (model.getCategoriaViajeId() != null) {
                CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findByIdAndEliminadaIsNull(model.getCategoriaViajeId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la categoria id: " + model.getCategoriaViajeId() + "."));
                dto.setCategoriaViaje(categoriaViajeModel.getCategoria());
                dto.setCategoriaViajeId(model.getCategoriaViajeId().toString());
            }
            if (model.getCompradorId() != null) {
                ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(model.getCompradorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el comprador con id: " + model.getCompradorId() + "."));
                dto.setComprador(clienteModel.getNombre());
                dto.setCompradorId(model.getCompradorId().toString());
            }
            if (model.getDestinoId() != null) {
                DireccionModel direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getDestinoId()).orElseThrow(() -> new DatosInexistentesException("No se encontro la dirección de destino con id: " + model.getDestinoId() + "."));
                dto.setDestino(direccionModel.getCiudad() + " - " + direccionModel.getDireccion());
                dto.setDestinoId(model.getDestinoId().toString());
            }
            if (model.getFechaId() != null) {
                EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(model.getFechaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la fecha de viaje con id: " + model.getFechaId() + "."));
                dto.setFecha(eventoModel.getFecha().toString());
                dto.setFechaId(model.getFechaId().toString());
            }
            if (model.getCreada() != null)
                dto.setFechaEmision(model.getCreada().toString());
            if (model.getKmCargado() != null)
                dto.setKmCargado(model.getKmCargado().toString());
            dto.setNotas(model.getNotas());
            if (model.getOrigenId() != null) {
                DireccionModel direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getOrigenId()).orElseThrow(() -> new DatosInexistentesException("No se encontro la dirección de destino con id: " + model.getOrigenId() + "."));
                dto.setOrigen(direccionModel.getCiudad() + " - " + direccionModel.getDireccion());
                dto.setOrigenId(model.getOrigenId().toString());
            }
            dto.setValidez(model.getValidez().toString());
            if (model.getValorKm() != null)
                dto.setValorKm(model.getValorKm().toString());

            if (model.getCreadorId() != null) {
                dto.setCreador(usuarioService.buscarPorId(model.getCreadorId()).getNombre());
                dto.setCreadorId(model.getCreadorId().toString());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getModificadorId() != null) {
                dto.setModificador(usuarioService.buscarPorId(model.getModificadorId()).getNombre());
                dto.setModificadorId(model.getModificadorId().toString());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getEliminadorId() != null) {
                dto.setEliminador(usuarioService.buscarPorId(model.getEliminadorId()).getNombre());
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