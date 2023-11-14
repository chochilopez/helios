package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PagoCreation;
import gloit.hiperionida.helios.mapper.dto.PagoDTO;
import gloit.hiperionida.helios.mapper.dto.ReciboDTO;
import gloit.hiperionida.helios.model.PagoModel;
import gloit.hiperionida.helios.model.ReciboModel;
import gloit.hiperionida.helios.model.RemitoModel;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.repository.ReciboDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PagoMapper {
    private final ReciboDAO reciboDAO;
    private final UsuarioDAO usuarioDAO;

    public PagoModel toEntity(PagoCreation creation) {
        try {
            PagoModel model = new PagoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getDecimal(creation.getMonto()));
            model.setNotas(creation.getNotas());
            if (creation.getTipoPago() != null)
                model.setTipoPago(TipoPagoEnum.valueOf(creation.getTipoPago()));

            if (Helper.getLong(creation.getCompraId()) != null)
                model.setCompraId(Helper.getLong(creation.getCompraId()));
            if (Helper.getLong(creation.getFacturaId()) != null)
                model.setFacturaId(Helper.getLong(creation.getFacturaId()));
            if (Helper.getLong(creation.getReciboId()) != null)
                model.setReciboId(Helper.getLong(creation.getReciboId()));

            if (Helper.getLong(creation.getCreadorId()) != null)
                model.setCreadorId(Helper.getLong(creation.getCreadorId()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), ""));
            if (Helper.getLong(creation.getModificadorId()) != null)
                model.setModificadorId(Helper.getLong(creation.getModificadorId()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), ""));
            if (Helper.getLong(creation.getEliminadorId()) != null)
                model.setEliminadorId(Helper.getLong(creation.getEliminadorId()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), ""));

            return model;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public PagoDTO toDto(PagoModel model) {
        try {
            PagoDTO dto = new PagoDTO();

            dto.setId(model.getId().toString());
            dto.setMonto(model.getMonto().toString());
            dto.setNotas(model.getNotas());
            dto.setTipoPago(model.getTipoPago().name());

            if (model.getCompraId() != null)
                dto.setCompraId(model.getCompraId().toString());
            if (model.getFacturaId() != null)
                dto.setFacturaId(model.getFacturaId().toString());
            if (model.getReciboId() != null) {
                ReciboModel reciboModel = reciboDAO.findByIdAndEliminadaIsNull(model.getReciboId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el remito con id: " + model.getReciboId() + "."));
                dto.setRecibo(reciboModel.getFecha().toString());
            }

            if (model.getCreadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreadorId() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificadorId() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminadorId() + "."));
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