package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.CondicionPagoEnum;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class FacturaMapper {
    private final CamionDAO camionDAO;
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final ClienteDAO clienteDAO;
    private final ConductorDAO conductorDAO;
    private final DireccionDAO direccionDAO;
    private final EventoDAO eventoDAO;
    private final RemitoDAO remitoDAO;
    private final ViajeDAO viajeDAO;
    private final UsuarioServiceImpl usuarioService;

    public FacturaModel toEntity(FacturaCreation creation) {
        try {
            FacturaModel model = new FacturaModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getDecimal(creation.getBonificacion()) != null)
                model.setBonificacion(Helper.getDecimal(creation.getBonificacion()));
            if (Helper.getInteger(creation.getCantidad()) != null)
                model.setCantidad(Helper.getInteger(creation.getCantidad()));
            model.setCodigo(creation.getCodigo());
            model.setConcepto(creation.getConcepto());
            if (creation.getCondicionPago() != null)
                model.setCondicionPago(CondicionPagoEnum.valueOf(creation.getCondicionPago()));
            model.setDomicilioComercial(creation.getDomicilioComercial());
            if (!Helper.isEmptyString(creation.getFechaEmision()))
                model.setFechaEmision(Helper.stringToLocalDateTime(creation.getFechaEmision(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getFechaVencimientoId()) != null) {
                model.setFechaVencimientoId(Helper.getLong(creation.getFechaVencimientoId()));
            } else {
                EventoModel evento = eventoDAO.save(new EventoModel(
                        Helper.stringToLocalDateTime("00:00:00 " + creation.getFechaVencimiento(), ""),
                        "Vencimiento de " + creation.getTipoComprobante() + " - " + creation.getNumeroComprobante(),
                        null,
                        null,
                        "Vencimiento",
                        Helper.getNow(""),
                        usuarioService.obtenerUsuario().getId()
                ));
                model.setFechaVencimientoId(evento.getId());
            }
            if (Helper.getDecimal(creation.getIva()) != null)
                model.setIva(Helper.getDecimal(creation.getIva()));
            model.setNotas(creation.getNotas());
            model.setNumeroComprobante(creation.getNumeroComprobante());
            if (Helper.getDecimal(creation.getOtrosImpuestos()) != null)
                model.setOtrosImpuestos(Helper.getDecimal(creation.getOtrosImpuestos()));
            if (Helper.getBoolean(creation.getPagada()) != null)
                model.setPagada(Helper.getBoolean(creation.getPagada()));
            if (Helper.getDecimal(creation.getPrecioUnitario()) != null)
                model.setPrecioUnitario(Helper.getDecimal(creation.getPrecioUnitario()));
            model.setRazonSocial(creation.getRazonSocial());
            if (creation.getTipoComprobante() != null)
                model.setTipoComprobante(TipoComprobanteEnum.valueOf(creation.getTipoComprobante()));
            if (Helper.getLong(creation.getRemitoId()) != null)
                model.setRemitoId(Helper.getLong(creation.getRemitoId()));
            if (Helper.getLong(creation.getViajeId()) != null)
                model.setViajeId(Helper.getLong(creation.getViajeId()));

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

    public FacturaDTO toDto(FacturaModel model) {
        try {
            FacturaDTO dto = new FacturaDTO();

            dto.setId(model.getId().toString());
            if (model.getBonificacion() != null)
                dto.setBonificacion(model.getBonificacion().toString());
            if (model.getCantidad() != null)
                dto.setCantidad(model.getCantidad().toString());
            dto.setCodigo(model.getCodigo());
            dto.setConcepto(model.getConcepto());
            if (model.getCondicionPago() != null)
                dto.setCondicionPago(model.getCondicionPago().toString());
            dto.setDomicilioComercial(model.getDomicilioComercial());
            if (model.getFechaEmision() != null)
                dto.setFechaEmision(model.getFechaEmision().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getFechaVencimientoId() != null) {
                EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(model.getFechaVencimientoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la fecha de vencimiento con id: " + model.getFechaVencimientoId() + "."));
                dto.setFechaVencimiento(eventoModel.getFecha().toString());
                dto.setFechaVencimientoId(model.getFechaVencimientoId().toString());
            }
            if (model.getIva() != null)
                dto.setIva(model.getIva().toString());
            dto.setNotas(model.getNotas());
            dto.setNumeroComprobante(model.getNumeroComprobante());
            if (model.getOtrosImpuestos() != null)
                dto.setOtrosImpuestos(model.getOtrosImpuestos().toString());
            if (model.getPagada() != null)
                dto.setPagada(model.getPagada().toString());
            if (model.getPrecioUnitario() != null)
                dto.setPrecioUnitario(model.getPrecioUnitario().toString());
            dto.setRazonSocial(model.getRazonSocial());
            if (model.getTipoComprobante() != null)
                dto.setTipoComprobante(model.getTipoComprobante().toString());
            if (model.getRemitoId() != null) {
                RemitoModel remitoModel = remitoDAO.findByIdAndEliminadaIsNull(model.getRemitoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el remito con id: " + model.getRemitoId() + "."));
                dto.setNumeroRemito(remitoModel.getNumero());
            }
            if (model.getViajeId() != null) {
                ViajeModel viajeModel = viajeDAO.findByIdAndEliminadaIsNull(model.getViajeId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el viaje id: " + model.getViajeId() + "."));
                if (viajeModel.getCamionId() != null) {
                    CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(viajeModel.getCamionId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el camion con id: " + viajeModel.getCamionId() + "."));
                    dto.setCamion(camionModel.getMarcaModelo());
                }
                if (viajeModel.getCantidadTransportada() != null)
                    dto.setCantidadTransportada(viajeModel.getCantidadTransportada().toString());
                if (viajeModel.getCategoriaViajeId() != null) {
                    CategoriaViajeModel categoriaViajeModel = categoriaViajeDAO.findByIdAndEliminadaIsNull(viajeModel.getCategoriaViajeId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la categoria id: " + viajeModel.getCategoriaViajeId() + "."));
                    dto.setCategoriaViaje(categoriaViajeModel.getCategoria());
                }
                if (viajeModel.getCompradorId() != null) {
                    ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(viajeModel.getCompradorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el comprador con id: " + viajeModel.getCompradorId() + "."));
                    dto.setComprador(clienteModel.getNombre());
                }
                if (viajeModel.getConductorId() != null) {
                    ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(viajeModel.getConductorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el conductor con id: " + viajeModel.getConductorId() + "."));
                    dto.setConductor(conductorModel.getNombre());
                }
                if (viajeModel.getDestinoId() != null) {
                    DireccionModel destinoModel = direccionDAO.findByIdAndEliminadaIsNull(viajeModel.getDestinoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la dirección destino con id: " + viajeModel.getDestinoId() + "."));
                    dto.setDestino(destinoModel.getCiudad() + " - " + destinoModel.getDireccion());
                }
                if (viajeModel.getFechaId() != null) {
                    EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(viajeModel.getFechaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la fecha de viaje con id: " + viajeModel.getFechaId() + "."));
                    dto.setFechaViaje(eventoModel.getFecha().toString());
                }
                if (viajeModel.getKmCargado() != null)
                    dto.setKmCargado(viajeModel.getKmCargado().toString());
                dto.setNumeroGuia(viajeModel.getGuia());
                if (viajeModel.getOrigenId() != null) {
                    DireccionModel origenModel = direccionDAO.findByIdAndEliminadaIsNull(viajeModel.getOrigenId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la dirección origen con id: " + viajeModel.getOrigenId() + "."));
                    dto.setOrigen(origenModel.getCiudad() + " - " + origenModel.getDireccion());
                }
                if (viajeModel.getValorKm() != null)
                    dto.setValorKm(viajeModel.getValorKm().toString());
            }

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