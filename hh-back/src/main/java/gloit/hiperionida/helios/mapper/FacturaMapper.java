package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

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
    private final UsuarioDAO usuarioDAO;

    public FacturaModel toEntity(FacturaCreation creation) {
        try {
            FacturaModel model = new FacturaModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getDecimal(creation.getDescuento()) != null)
                model.setDescuento(Helper.getDecimal(creation.getDescuento()));
            if (creation.getFecha() != null && Helper.stringToLocalDateTime(creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime(creation.getFecha(), ""));
            if (Helper.getDecimal(creation.getIva()) != null)
                model.setIva(Helper.getDecimal(creation.getIva()));
            model.setNumeroComprobante(creation.getNumeroComprobante());
            if (Helper.getDecimal(creation.getRecarga()) != null)
                model.setRecarga(Helper.getDecimal(creation.getRecarga()));
            if (Helper.getDecimal(creation.getSubTotal()) != null)
                model.setSubTotal(Helper.getDecimal(creation.getSubTotal()));
            if (creation.getTipoComprobante() != null)
                model.setTipoComprobante(TipoComprobanteEnum.valueOf(creation.getTipoComprobante()));
            if (Helper.getBoolean(creation.getPagada()) != null)
                model.setPagada(Helper.getBoolean(creation.getPagada()));

            if (Helper.getLong(creation.getRemitoId()) != null)
                model.setRemitoId(Helper.getLong(creation.getRemitoId()));
            if (Helper.getLong(creation.getViajeId()) != null)
                model.setViajeId(Helper.getLong(creation.getViajeId()));

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

    public FacturaDTO toDto(FacturaModel model) {
        try {
            FacturaDTO dto = new FacturaDTO();

            dto.setId(model.getId().toString());
            dto.setDescuento(model.getDescuento().toString());
            dto.setFecha(model.getFecha().toString());
            dto.setIva(model.getIva().toString());
            dto.setNumeroComprobante(model.getNumeroComprobante());
            dto.setRecarga(model.getRecarga().toString());
            dto.setSubTotal(model.getSubTotal().toString());
            dto.setTipoComprobante(model.getTipoComprobante().name());
            dto.setPagada(model.getPagada().toString());

            if (model.getRemitoId() != null) {
                RemitoModel remitoModel = remitoDAO.findByIdAndEliminadaIsNull(model.getRemitoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el remito con id: " + model.getRemitoId() + "."));
                dto.setRemito(remitoModel.getNumero());
            }
            if (model.getViajeId() != null) {
                ViajeModel viajeModel = viajeDAO.findByIdAndEliminadaIsNull(model.getViajeId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el viaje id: " + model.getViajeId() + "."));
                if (viajeModel.getCamionId() != null) {
                    CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(viajeModel.getCamionId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el camion con id: " + viajeModel.getCamionId() + "."));
                    dto.setCamion(camionModel.getMarcaModelo());
                }
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
                dto.setNumeroGuia(viajeModel.getGuia());
                if (viajeModel.getOrigenId() != null) {
                    DireccionModel origenModel = direccionDAO.findByIdAndEliminadaIsNull(viajeModel.getOrigenId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la dirección origen con id: " + viajeModel.getOrigenId() + "."));
                    dto.setOrigen(origenModel.getCiudad() + " - " + origenModel.getDireccion());
                }
            }

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
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}