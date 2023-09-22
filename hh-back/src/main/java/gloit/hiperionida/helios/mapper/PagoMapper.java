package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PagoCreation;
import gloit.hiperionida.helios.mapper.dto.PagoDTO;
import gloit.hiperionida.helios.mapper.dto.ReciboDTO;
import gloit.hiperionida.helios.model.PagoModel;
import gloit.hiperionida.helios.model.ReciboModel;
import gloit.hiperionida.helios.model.enums.TipoPagoEnum;
import gloit.hiperionida.helios.repository.ReciboDAO;
import gloit.hiperionida.helios.util.Helper;
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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final ReciboDAO reciboDAO;
    private final ReciboMapper reciboMapper;


    public PagoModel toEntity(PagoCreation pagoCreation) {
        try {
            PagoModel pagoModel = new PagoModel();

            if (Helper.getLong(pagoCreation.getId()) != null)
                pagoModel.setId(Helper.getLong(pagoCreation.getId()));
            if (Helper.getDecimal(pagoCreation.getMonto()) != null)
                pagoModel.setMonto(Helper.getDecimal(pagoCreation.getMonto()));
            pagoModel.setNotas(pagoCreation.getNotas());
            if (pagoCreation.getTipoPago() != null)
                pagoModel.setTipoPago(TipoPagoEnum.valueOf(pagoCreation.getTipoPago()));
            if (Helper.getLong(pagoCreation.getRecibo_id()) != null) {
                Optional<ReciboModel> recibo = reciboDAO.findById(Helper.getLong(pagoCreation.getRecibo_id()));
                recibo.ifPresent(pagoModel::setRecibo);
            }

            if (Helper.getLong(pagoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(pagoCreation.getCreador_id()));
                user.ifPresent(pagoModel::setCreador);
            }
            if (!Helper.isEmptyString(pagoCreation.getCreada()))
                pagoModel.setCreada(Helper.stringToLocalDateTime(pagoCreation.getCreada(), ""));
            if (Helper.getLong(pagoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(pagoCreation.getModificador_id()));
                user.ifPresent(pagoModel::setModificador);
            }
            if (!Helper.isEmptyString(pagoCreation.getModificada()))
                pagoModel.setModificada(Helper.stringToLocalDateTime(pagoCreation.getModificada(), ""));
            if (Helper.getLong(pagoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(pagoCreation.getEliminador_id()));
                user.ifPresent(pagoModel::setEliminador);
            }
            if (!Helper.isEmptyString(pagoCreation.getEliminada()))
                pagoModel.setEliminada(Helper.stringToLocalDateTime(pagoCreation.getEliminada(), ""));

            return pagoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public PagoDTO toDto(PagoModel pagoModel) {
        try {
            PagoDTO dto = new PagoDTO();

            dto.setId(pagoModel.getId().toString());
            dto.setMonto(pagoModel.getMonto().toString());
            dto.setNotas(pagoModel.getNotas());
            dto.setTipoPago(pagoModel.getTipoPago().name());
            if (pagoModel.getRecibo() != null)
                dto.setRecibo(reciboMapper.toDto(pagoModel.getRecibo()));

            if (pagoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(pagoModel.getCreador()));
            if (pagoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(pagoModel.getCreada(), ""));
            if (pagoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(pagoModel.getModificador()));
            if (pagoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(pagoModel.getModificada(), ""));
            if (pagoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(pagoModel.getEliminador()));
            if (pagoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(pagoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}