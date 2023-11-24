package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.repository.ClienteDAO;
import gloit.hiperionida.helios.repository.FacturaDAO;
import gloit.hiperionida.helios.repository.ReciboDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class CuentaCorrienteMapper {
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final ReciboDAO reciboDAO;
    private final UsuarioDAO usuarioDAO;

    public CuentaCorrienteModel toEntity(CuentaCorrienteCreation creation) {
        try {
            CuentaCorrienteModel model = new CuentaCorrienteModel();
            
            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getNDecimal(Helper.getDecimal(creation.getMonto()), 2));
            model.setNotas(creation.getNotas());
            if (creation.getTipoPago() != null)
                model.setTipoPago(TipoPagoEnum.valueOf(creation.getTipoPago()));
            if (creation.getTipoMovimiento() != null)
                model.setTipoMovimiento(MovimientoEnum.valueOf(creation.getTipoMovimiento()));
            model.setFecha(Helper.getNow(""));
            if (Helper.getLong(creation.getClienteId()) != null)
                model.setClienteId(Helper.getLong(creation.getClienteId()));
            if (Helper.getLong(creation.getFacturaId()) != null)
                model.setFacturaId(Helper.getLong(creation.getFacturaId()));
            if (Helper.getLong(creation.getReciboId()) != null)
                model.setReciboId(Helper.getLong(creation.getReciboId()));

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

    public CuentaCorrienteDTO toDto(CuentaCorrienteModel model) {
        try {
            CuentaCorrienteDTO dto = new CuentaCorrienteDTO();

            dto.setId(model.getId().toString());
            if (model.getMonto() != null)
                dto.setMonto(model.getMonto().toString());
            dto.setNotas(model.getNotas());
            if (model.getTipoPago() != null)
                dto.setTipoPago(model.getTipoPago().toString());
            if (model.getTipoMovimiento() != null)
                dto.setTipoMovimiento(model.getTipoMovimiento().toString());
            if (model.getFecha() != null)
                dto.setFecha(model.getFecha().toString());
            if (model.getClienteId() != null) {
                ClienteModel clienteModel = clienteDAO.findByIdAndEliminadaIsNull(model.getClienteId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el cliente con id: " + model.getClienteId() + "."));
                dto.setCliente(clienteModel.getNombre());
                dto.setClienteId(model.getClienteId().toString());
            }
            if (model.getFacturaId() != null) {
                FacturaModel facturaModel = facturaDAO.findByIdAndEliminadaIsNull(model.getFacturaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la factura con id: " + model.getFacturaId() + "."));
                dto.setFactura(facturaModel.getTipoComprobante().toString() + "-" + facturaModel.getNumeroComprobante());
                dto.setFacturaId(model.getFacturaId().toString());
            }
            if (model.getReciboId() != null) {
                ReciboModel reciboModel = reciboDAO.findByIdAndEliminadaIsNull(model.getReciboId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el recibo con id: " + model.getReciboId() + "."));
                dto.setRecibo(reciboModel.getId().toString());
                dto.setReciboId(model.getReciboId().toString());
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