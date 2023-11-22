package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ChequeCreation;
import gloit.hiperionida.helios.mapper.dto.ChequeDTO;
import gloit.hiperionida.helios.model.ChequeModel;
import gloit.hiperionida.helios.model.enums.EstadoChequeEnum;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
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
                model.setMonto(Helper.getNDecimal(Helper.getDecimal(creation.getMonto()), 2));
            model.setNumeroCheque(creation.getNumeroCheque());

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