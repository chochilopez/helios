package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ConciliacionBancoCreation;
import gloit.hiperionida.helios.mapper.dto.ConciliacionBancoDTO;
import gloit.hiperionida.helios.model.BancoModel;
import gloit.hiperionida.helios.model.ConciliacionBancoModel;
import gloit.hiperionida.helios.model.enums.MovimientoEnum;
import gloit.hiperionida.helios.repository.BancoDAO;
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
public class ConciliacionBancoMapper {
    private final BancoDAO bancoDAO;
    private final UsuarioDAO usuarioDAO;

    public ConciliacionBancoModel toEntity(ConciliacionBancoCreation creation) {
        try {
            ConciliacionBancoModel model = new ConciliacionBancoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (creation.getMovimiento() != null)
                model.setMovimiento(MovimientoEnum.valueOf(creation.getMovimiento()));
            if (creation.getFecha() != null && Helper.stringToLocalDateTime(creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime(creation.getFecha(), ""));
            model.setConcepto(creation.getConcepto());
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getDecimal(creation.getMonto()));
            if (Helper.getLong(creation.getBancoId()) != null)
                model.setBancoId(Helper.getLong(creation.getBancoId()));

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

    public ConciliacionBancoDTO toDto(ConciliacionBancoModel model) {
        try {
            ConciliacionBancoDTO dto = new ConciliacionBancoDTO();

            dto.setId(model.getId().toString());
            dto.setMovimiento(model.getMovimiento().name());
            dto.setFecha(model.getFecha().toString());
            dto.setConcepto(model.getConcepto());
            dto.setMonto(model.getMonto().toString());
            if (model.getBancoId() != null) {
                BancoModel bancoModel = bancoDAO.findByIdAndEliminadaIsNull(model.getBancoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el banco con id: " + model.getBancoId() + "."));
                dto.setBanco(bancoModel.getBanco());
            }

            if (model.getCreador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreador_id() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificador_id() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminador_id() + "."));
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