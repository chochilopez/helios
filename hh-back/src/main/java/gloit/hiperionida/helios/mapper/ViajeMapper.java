package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.EventoCreation;
import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
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
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViajeMapper {
    private final AcopladoDAO acopladoDAO;
    private final CamionDAO camionDAO;
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final ClienteDAO clienteDAO;
    private final ConductorDAO conductorDAO;
    private final DireccionDAO direccionDAO;
    private final EventoDAO eventoDAO;
    private final UsuarioServiceImpl usuarioService;


    public ViajeModel toEntity(ViajeCreation creation) {
        try {
            ViajeModel model = new ViajeModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getLong(creation.getAcopladoId()) != null)
                model.setAcopladoId(Helper.getLong(creation.getAcopladoId()));
            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            if (Helper.getInteger(creation.getCantidadTransportada()) != null)
                model.setCantidadTransportada(Helper.getInteger(creation.getCantidadTransportada()));
            if (Helper.getLong(creation.getCargaId()) != null)
                model.setCargaId(Helper.getLong(creation.getCargaId()));
            if (Helper.getLong(creation.getCategoriaViajeId()) != null)
                model.setCategoriaViajeId(Helper.getLong(creation.getCategoriaViajeId()));
            if (Helper.getLong(creation.getCompradorId()) != null)
                model.setCompradorId(Helper.getLong(creation.getCompradorId()));
            if (Helper.getLong(creation.getConductorId()) != null)
                model.setConductorId(Helper.getLong(creation.getConductorId()));
            if (Helper.getLong(creation.getDestinoId()) != null)
                model.setDestinoId(Helper.getLong(creation.getDestinoId()));
            if (creation.getFecha() != null) {
                Optional<ClienteModel> clienteModel = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCompradorId()));
                EventoModel evento = eventoDAO.save(new EventoModel(
                        Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), ""),
                        "Viaje para " + clienteModel.get().getNombre(),
                        null,
                        null,
                        "Viaje",
                        Helper.getNow(""),
                        usuarioService.obtenerUsuario().getId()
                ));
                model.setFechaId(evento.getId());
            }
            model.setGuia(creation.getGuia());
            if (Helper.getLong(creation.getIntermediarioId()) != null)
                model.setIntermediarioId(Helper.getLong(creation.getIntermediarioId()));
            if (Helper.getDecimal(creation.getKmCargado()) != null)
                model.setKmCargado(Helper.getDecimal(creation.getKmCargado()));
            if (Helper.getDecimal(creation.getKmVacio()) != null)
                model.setKmVacio(Helper.getDecimal(creation.getKmVacio()));
            if (Helper.getDecimal(creation.getNeto()) != null)
                model.setNeto(Helper.getDecimal(creation.getNeto()));
            model.setNotas(creation.getNotas());
            if (Helper.getLong(creation.getOrigenId()) != null)
                model.setOrigenId(Helper.getLong(creation.getOrigenId()));
            if (Helper.getDecimal(creation.getValorKm()) != null)
                model.setValorKm(Helper.getDecimal(creation.getValorKm()));
            if (Helper.getLong(creation.getVendedorId()) != null)
                model.setVendedorId(Helper.getLong(creation.getVendedorId()));

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
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ViajeDTO toDto(ViajeModel model) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            ViajeDTO dto = new ViajeDTO();

            dto.setId(model.getId().toString());


            if (model.getAcopladoId() != null) {
                AcopladoModel acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(model.getAcopladoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el acoplado con id: " + model.getAcopladoId() + "."));
                dto.setAcoplado(acopladoModel.getMarcaModelo());
            }
            if (model.getCamionId() != null) {
                CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getCamionId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el camion con id: " + model.getCamionId() + "."));
                dto.setCamion(camionModel.getMarcaModelo());
            }
            if (model.getCantidadTransportada() != null)
                dto.setCantidadTransportada(model.getCantidadTransportada().toString());
            if (model.getCargaId() != null) {
                DireccionModel direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getCargaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la dirección de carga con id: " + model.getCargaId() + "."));
                dto.setCarga(direccionModel.getCiudad() + " - " + direccionModel.getDireccion());
            }
            if (model.getCategoriaViajeId() != null) {
                CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findByIdAndEliminadaIsNull(model.getCategoriaViajeId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la categoria id: " + model.getCategoriaViajeId() + "."));
                dto.setCategoriaViaje(categoriaViajeModel.getCategoria());
            }
            if (model.getCompradorId() != null) {
                ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(model.getCompradorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el comprador con id: " + model.getCompradorId() + "."));
                dto.setComprador(clienteModel.getNombre());
            }
            if (model.getConductorId() != null) {
                ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(model.getConductorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el conductor con id: " + model.getConductorId() + "."));
                dto.setConductor(conductorModel.getNombre());
            }
            if (model.getDestinoId() != null) {
                DireccionModel destinoModel = direccionDAO.findByIdAndEliminadaIsNull(model.getDestinoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la dirección destino con id: " + model.getDestinoId() + "."));
                dto.setDestino(destinoModel.getCiudad() + " - " + destinoModel.getDireccion());
            }
            if (model.getFechaId() != null) {
                EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(model.getFechaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la fecha de viaje con id: " + model.getFechaId() + "."));
                dto.setFecha(eventoModel.getFecha().toString());
            }
            dto.setGuia(model.getGuia());
            if (model.getIntermediarioId() != null) {
                ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(model.getIntermediarioId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el intermediario con id: " + model.getIntermediarioId() + "."));
                dto.setIntermediario(clienteModel.getNombre());
            }
            if (model.getKmCargado() != null)
                dto.setKmCargado(model.getKmCargado().toString());
            if (model.getKmVacio() != null)
                dto.setKmVacio(model.getKmVacio().toString());
            if (model.getNeto() != null)
                dto.setNeto(model.getNeto().toString());
            dto.setNotas(model.getNotas());
            if (model.getOrigenId() != null) {
                DireccionModel origenModel = direccionDAO.findByIdAndEliminadaIsNull(model.getOrigenId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la dirección origen con id: " + model.getOrigenId() + "."));
                dto.setOrigen(origenModel.getCiudad() + " - " + origenModel.getDireccion());
            }
            if (model.getValorKm() != null)
                dto.setValorKm(model.getValorKm().toString());
            if (model.getVendedorId() != null) {
                ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(model.getVendedorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el vendedor con id: " + model.getVendedorId() + "."));
                dto.setVendedor(clienteModel.getNombre());
            }

            if (model.getCreador_id() != null)
                dto.setCreador(usuarioService.buscarPorId(model.getCreador_id()).getNombre());
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null)
                dto.setModificador(usuarioService.buscarPorId(model.getModificador_id()).getNombre());
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null)
                dto.setEliminador(usuarioService.buscarPorId(model.getEliminador_id()).getNombre());
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}