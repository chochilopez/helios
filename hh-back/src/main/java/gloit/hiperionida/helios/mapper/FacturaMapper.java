package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.repository.PagoDAO;
import gloit.hiperionida.helios.repository.RemitoDAO;
import gloit.hiperionida.helios.repository.ViajeDAO;
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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final PagoDAO pagoDAO;
    private final PagoMapper pagoMapper;
    private final RemitoDAO remitoDAO;
    private final RemitoMapper remitoMapper;
    private final ViajeDAO viajeDAO;
    private final ViajeFacturaMapper viajeFacturaMapper;
    /*
        private String id;
    private String descuento;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;
    private String remito_id;
    private String viaje_id;
     */

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
            Set<PagoModel> pagos = new HashSet<>();
            if (creation.getPagos_id() != null) {
                for (String pago_id : creation.getPagos_id()) {
                    if (Helper.getLong(pago_id) != null) {
                        Optional<PagoModel> pago = pagoDAO.findByIdAndEliminadaIsNull(Helper.getLong(pago_id));
                        pago.ifPresent(pagos::add);
                    }
                }
            }
            model.setPagos(pagos);
            if (Helper.getLong(creation.getRemito_id()) != null) {
                Optional<RemitoModel> remito = remitoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getRemito_id()));
                remito.ifPresent(model::setRemito);
            }
            if (Helper.getLong(creation.getViaje_id()) != null) {
                Optional<ViajeModel> viaje = viajeDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getViaje_id()));
                viaje.ifPresent(model::setViaje);
            }

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
    /*
        private String id;
    private String descuento;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String recarga;
    private String subTotal;
    private String tipoComprobante;
    private String pagada;
    private String remito_id;
    private String viaje_id;
     */

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
            if (!model.getPagos().isEmpty()) {
                List<PagoDTO> pagoDTOS = new ArrayList<>();
                for (PagoModel pago:model.getPagos()) {
                    pagoDTOS.add(pagoMapper.toDto(pago));
                }
                dto.setPagos(pagoDTOS);
            }
            if (model.getRemito() != null)
                dto.setRemito(remitoMapper.toDto(model.getRemito()));
            if (model.getViaje() != null)
                dto.setViaje(viajeFacturaMapper.toDto(model.getViaje()));

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