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

    public FacturaModel toEntity(FacturaCreation facturaCreation) {
        try {
            FacturaModel facturaModel = new FacturaModel();

            if (Helper.getLong(facturaCreation.getId()) != null)
                facturaModel.setId(Helper.getLong(facturaCreation.getId()));
            if (Helper.getDecimal(facturaCreation.getDescuento()) != null)
                facturaModel.setDescuento(Helper.getDecimal(facturaCreation.getDescuento()));
            if (facturaCreation.getFecha() != null && Helper.stringToLocalDateTime(facturaCreation.getFecha(), "") != null)
                facturaModel.setFecha(Helper.stringToLocalDateTime(facturaCreation.getFecha(), ""));
            if (Helper.getDecimal(facturaCreation.getIva()) != null)
                facturaModel.setIva(Helper.getDecimal(facturaCreation.getIva()));
            facturaModel.setNumeroComprobante(facturaCreation.getNumeroComprobante());
            if (Helper.getDecimal(facturaCreation.getRecarga()) != null)
                facturaModel.setRecarga(Helper.getDecimal(facturaCreation.getRecarga()));
            if (Helper.getDecimal(facturaCreation.getSubTotal()) != null)
                facturaModel.setSubTotal(Helper.getDecimal(facturaCreation.getSubTotal()));
            if (facturaCreation.getTipoComprobante() != null)
                facturaModel.setTipoComprobante(TipoComprobanteEnum.valueOf(facturaCreation.getTipoComprobante()));
            if (Helper.getBoolean(facturaCreation.getPagada()) != null)
                facturaModel.setPagada(Helper.getBoolean(facturaCreation.getPagada()));
            Set<PagoModel> pagos = new HashSet<>();
            if (facturaCreation.getPagos_id() != null) {
                for (String pago_id : facturaCreation.getPagos_id()) {
                    if (Helper.getLong(pago_id) != null) {
                        Optional<PagoModel> pago = pagoDAO.findByIdAndEliminadaIsNull(Helper.getLong(pago_id));
                        pago.ifPresent(pagos::add);
                    }
                }
            }
            facturaModel.setPagos(pagos);
            if (Helper.getLong(facturaCreation.getRemito_id()) != null) {
                Optional<RemitoModel> remito = remitoDAO.findByIdAndEliminadaIsNull(Helper.getLong(facturaCreation.getRemito_id()));
                remito.ifPresent(facturaModel::setRemito);
            }
            if (Helper.getLong(facturaCreation.getViaje_id()) != null) {
                Optional<ViajeModel> viaje = viajeDAO.findByIdAndEliminadaIsNull(Helper.getLong(facturaCreation.getViaje_id()));
                viaje.ifPresent(facturaModel::setViaje);
            }

            if (Helper.getLong(facturaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(facturaCreation.getCreador_id()));
                usuario.ifPresent(facturaModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(facturaCreation.getCreada(), "") != null)
                facturaModel.setCreada(Helper.stringToLocalDateTime(facturaCreation.getCreada(), ""));
            if (Helper.getLong(facturaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(facturaCreation.getModificador_id()));
                usuario.ifPresent(facturaModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(facturaCreation.getModificada(), "") != null)
                facturaModel.setModificada(Helper.stringToLocalDateTime(facturaCreation.getModificada(), ""));
            if (Helper.getLong(facturaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(facturaCreation.getEliminador_id()));
                usuario.ifPresent(facturaModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(facturaCreation.getEliminada(), "") != null)
                facturaModel.setEliminada(Helper.stringToLocalDateTime(facturaCreation.getEliminada(), ""));

            return facturaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public FacturaDTO toDto(FacturaModel facturaModel) {
        try {
            FacturaDTO dto = new FacturaDTO();

            dto.setId(facturaModel.getId().toString());
            dto.setDescuento(facturaModel.getDescuento().toString());
            dto.setFecha(facturaModel.getFecha().toString());
            dto.setIva(facturaModel.getIva().toString());
            dto.setNumeroComprobante(facturaModel.getNumeroComprobante());
            dto.setRecarga(facturaModel.getRecarga().toString());
            dto.setSubTotal(facturaModel.getSubTotal().toString());
            dto.setTipoComprobante(facturaModel.getTipoComprobante().name());
            dto.setPagada(facturaModel.getPagada().toString());
            if (!facturaModel.getPagos().isEmpty()) {
                List<PagoDTO> pagoDTOS = new ArrayList<>();
                for (PagoModel pago:facturaModel.getPagos()) {
                    pagoDTOS.add(pagoMapper.toDto(pago));
                }
                dto.setPagos(pagoDTOS);
            }
            if (facturaModel.getRemito() != null)
                dto.setRemito(remitoMapper.toDto(facturaModel.getRemito()));
            if (facturaModel.getViaje() != null)
                dto.setViaje(viajeFacturaMapper.toDto(facturaModel.getViaje()));

            if (facturaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(facturaModel.getCreador()));
            if (Helper.localDateTimeToString(facturaModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(facturaModel.getCreada(), ""));
            if (facturaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(facturaModel.getModificador()));
            if (Helper.localDateTimeToString(facturaModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(facturaModel.getModificada(), ""));
            if (facturaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(facturaModel.getEliminador()));
            if (Helper.localDateTimeToString(facturaModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(facturaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}