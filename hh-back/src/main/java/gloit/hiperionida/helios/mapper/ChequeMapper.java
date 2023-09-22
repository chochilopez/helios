package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ChequeCreation;
import gloit.hiperionida.helios.mapper.dto.ChequeDTO;
import gloit.hiperionida.helios.model.ChequeModel;
import gloit.hiperionida.helios.model.enums.EstadoChequeEnum;
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
public class ChequeMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ChequeModel toEntity(ChequeCreation chequeCreation) {
        try {
            ChequeModel chequeModel = new ChequeModel();

            if (Helper.getLong(chequeCreation.getId()) != null)
                chequeModel.setId(Helper.getLong(chequeCreation.getId()));
            chequeModel.setCuitEmisor(chequeCreation.getCuitEmisor());
            chequeModel.setDestinatario(chequeCreation.getDestinatario());
            chequeModel.setEmisor(chequeCreation.getEmisor());
            chequeModel.setEntregadoA(chequeCreation.getEntregadoA());
            chequeModel.setRecibidoDe(chequeCreation.getRecibidoDe());
            chequeModel.setBanco(chequeCreation.getBanco());
            if (chequeCreation.getEstado() != null)
                chequeModel.setEstado(EstadoChequeEnum.valueOf(chequeCreation.getEstado()));
            if (chequeCreation.getFechaCobro() != null && Helper.stringToLocalDateTime(chequeCreation.getFechaCobro(), "") != null)
                chequeModel.setFechaCobro(Helper.stringToLocalDateTime(chequeCreation.getFechaCobro(), ""));
            if (chequeCreation.getFechaEmision() != null && Helper.stringToLocalDateTime(chequeCreation.getFechaEmision(), "") != null)
                chequeModel.setFechaEmision(Helper.stringToLocalDateTime(chequeCreation.getFechaEmision(), ""));
            if (Helper.getDecimal(chequeCreation.getMonto()) != null)
                chequeModel.setMonto(Helper.getDecimal(chequeCreation.getMonto()));
            chequeModel.setNumeroCheque(chequeCreation.getNumeroCheque());

            if (Helper.getLong(chequeCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(chequeCreation.getCreador_id()));
                user.ifPresent(chequeModel::setCreador);
            }
            if (!Helper.isEmptyString(chequeCreation.getCreada()))
                chequeModel.setCreada(Helper.stringToLocalDateTime(chequeCreation.getCreada(), ""));
            if (Helper.getLong(chequeCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(chequeCreation.getModificador_id()));
                user.ifPresent(chequeModel::setModificador);
            }
            if (!Helper.isEmptyString(chequeCreation.getModificada()))
                chequeModel.setModificada(Helper.stringToLocalDateTime(chequeCreation.getModificada(), ""));
            if (Helper.getLong(chequeCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(chequeCreation.getEliminador_id()));
                user.ifPresent(chequeModel::setEliminador);
            }
            if (!Helper.isEmptyString(chequeCreation.getEliminada()))
                chequeModel.setEliminada(Helper.stringToLocalDateTime(chequeCreation.getEliminada(), ""));

            return chequeModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ChequeDTO toDto(ChequeModel chequeModel) {
        try {
            ChequeDTO dto = new ChequeDTO();

            dto.setId(chequeModel.getId().toString());
            dto.setCuitEmisor(chequeModel.getCuitEmisor());
            dto.setDestinatario(chequeModel.getDestinatario());
            dto.setEmisor(chequeModel.getEmisor());
            dto.setEstado(chequeModel.getEstado().toString());
            dto.setFechaCobro(chequeModel.getFechaCobro().toString());
            dto.setFechaEmision(chequeModel.getFechaEmision().toString());
            dto.setMonto(chequeModel.getMonto().toString());
            dto.setNumeroCheque(chequeModel.getNumeroCheque());
            dto.setCuitEmisor(chequeModel.getCuitEmisor());
            dto.setBanco(chequeModel.getBanco());
            dto.setEntregadoA(chequeModel.getEntregadoA());
            dto.setRecibidoDe(chequeModel.getRecibidoDe());

            if (chequeModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(chequeModel.getCreador()));
            if (chequeModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(chequeModel.getCreada(), ""));
            if (chequeModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(chequeModel.getModificador()));
            if (chequeModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(chequeModel.getModificada(), ""));
            if (chequeModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(chequeModel.getEliminador()));
            if (chequeModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(chequeModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}