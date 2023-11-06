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

    public ChequeModel toEntity(ChequeCreation creation) {
        try {
            ChequeModel model = new ChequeModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setCuitEmisor(creation.getCuitEmisor());
            model.setDestinatario(creation.getDestinatario());
            model.setEmisor(creation.getEmisor());
            model.setEntregadoA(creation.getEntregadoA());
            model.setRecibidoDe(creation.getRecibidoDe());
            model.setBanco(creation.getBanco());
            if (creation.getEstado() != null)
                model.setEstado(EstadoChequeEnum.valueOf(creation.getEstado()));
            if (creation.getFechaCobro() != null && Helper.stringToLocalDateTime(creation.getFechaCobro(), "") != null)
                model.setFechaCobro(Helper.stringToLocalDateTime(creation.getFechaCobro(), ""));
            if (creation.getFechaEmision() != null && Helper.stringToLocalDateTime(creation.getFechaEmision(), "") != null)
                model.setFechaEmision(Helper.stringToLocalDateTime(creation.getFechaEmision(), ""));
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getDecimal(creation.getMonto()));
            model.setNumeroCheque(creation.getNumeroCheque());

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
    
    public ChequeDTO toDto(ChequeModel model) {
        try {
            ChequeDTO dto = new ChequeDTO();

            dto.setId(model.getId().toString());
            dto.setCuitEmisor(model.getCuitEmisor());
            dto.setDestinatario(model.getDestinatario());
            dto.setEmisor(model.getEmisor());
            dto.setEstado(model.getEstado().toString());
            dto.setFechaCobro(model.getFechaCobro().toString());
            dto.setFechaEmision(model.getFechaEmision().toString());
            dto.setMonto(model.getMonto().toString());
            dto.setNumeroCheque(model.getNumeroCheque());
            dto.setCuitEmisor(model.getCuitEmisor());
            dto.setBanco(model.getBanco());
            dto.setEntregadoA(model.getEntregadoA());
            dto.setRecibidoDe(model.getRecibidoDe());

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