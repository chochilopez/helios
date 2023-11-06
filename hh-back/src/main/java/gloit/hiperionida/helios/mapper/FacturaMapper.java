package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

            if (Helper.getLong(creation.getRemito_id()) != null)
                model.setRemito_id(Helper.getLong(creation.getRemito_id()));
            if (Helper.getLong(creation.getViaje_id()) != null)
                model.setViaje_id(Helper.getLong(creation.getViaje_id()));

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

            if (model.getRemito_id() != null)
                dto.setRemito(remitoDAO.findByIdAndEliminadaIsNull(model.getRemito_id()).get().getNumero());
            if (model.getViaje_id() != null) {
                Optional<ViajeModel> viajeModel = viajeDAO.findByIdAndEliminadaIsNull(model.getViaje_id());
                Optional<CamionModel> camionModel = camionDAO.findByIdAndEliminadaIsNull(viajeModel.get().getCamion_id());
                Optional<CategoriaViajeModel> categoriaViajeModel = categoriaViajeDAO.findByIdAndEliminadaIsNull(viajeModel.get().getCategoriaViaje_id());
                Optional<ClienteModel> clienteModel = clienteDAO.findByIdAndEliminadaIsNull(viajeModel.get().getComprador_id());
                Optional<ConductorModel> conductorModel = conductorDAO.findByIdAndEliminadaIsNull(viajeModel.get().getConductor_id());
                Optional<DireccionModel> destinoModel = direccionDAO.findByIdAndEliminadaIsNull(viajeModel.get().getDestino_id());
                Optional<EventoModel> eventoModel = eventoDAO.findByIdAndEliminadaIsNull(viajeModel.get().getFecha_id());
                Optional<DireccionModel> origenModel = direccionDAO.findByIdAndEliminadaIsNull(viajeModel.get().getOrigen_id());
                dto.setCamion(camionModel.get().getMarca() + " - " + camionModel.get().getModelo());
                dto.setCantidadTransportada(viajeModel.get().getCantidadTransportada().toString());
                dto.setCategoriaViaje(categoriaViajeModel.get().getCategoria());
                dto.setComprador(clienteModel.get().getNombre());
                dto.setConductor(conductorModel.get().getNombre());
                dto.setDestino(destinoModel.get().getCiudad() + " - " + destinoModel.get().getDireccion());
                dto.setFechaViaje(eventoModel.get().getFecha().toString());
                dto.setNumeroGuia(viajeModel.get().getGuia());
                dto.setOrigen(origenModel.get().getCiudad() + " - " + origenModel.get().getDireccion());
            }

            if (model.getCreador_id() != null)
                dto.setCreador(usuarioDAO.findByIdAndEliminadaIsNull(model.getCreador_id()).get().getNombre());
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null)
                dto.setModificador(usuarioDAO.findByIdAndEliminadaIsNull(model.getModificador_id()).get().getNombre());
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null)
                dto.setEliminador(usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminador_id()).get().getNombre());
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}