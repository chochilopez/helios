package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteProveedorCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteProveedorDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteProveedorModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.repository.FacturaDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
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
public class CuentaCorrienteProveedorMapper {
    private final ProveedorDAO proveedorDAO;
    private final UsuarioDAO usuarioDAO;

    public CuentaCorrienteProveedorModel toEntity(CuentaCorrienteProveedorCreation creation) {
        try {
            CuentaCorrienteProveedorModel model = new CuentaCorrienteProveedorModel();
            
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
            if (Helper.getLong(creation.getProveedorId()) != null)
                model.setProveedorId(Helper.getLong(creation.getProveedorId()));

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

    public CuentaCorrienteProveedorDTO toDto(CuentaCorrienteProveedorModel model) {
        try {
            CuentaCorrienteProveedorDTO dto = new CuentaCorrienteProveedorDTO();

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
            if (model.getProveedorId() != null) {
                ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(model.getProveedorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el Proveedor con id: " + model.getProveedorId() + "."));
                dto.setProveedor(proveedorModel.getNombre());
                dto.setProveedorId(model.getProveedorId().toString());
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