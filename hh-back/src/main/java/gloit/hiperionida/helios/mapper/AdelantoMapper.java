package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.mapper.dto.AdelantoDTO;
import gloit.hiperionida.helios.mapper.dto.CajaDTO;
import gloit.hiperionida.helios.model.AdelantoModel;
import gloit.hiperionida.helios.model.CajaModel;
import gloit.hiperionida.helios.repository.CajaDAO;
import gloit.hiperionida.helios.repository.ConductorDAO;
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
public class AdelantoMapper {
    private final CajaDAO cajaDAO;
    private final ConductorDAO conductorDAO;
    private final UsuarioDAO usuarioDAO;

    public AdelantoModel toEntity(AdelantoCreation creation) {
        try {
            AdelantoModel model = new AdelantoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setDescripcion(creation.getDescripcion());
            if (creation.getFecha() != null && Helper.stringToLocalDateTime(creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime(creation.getFecha(), ""));
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getDecimal(creation.getMonto()));
            model.setNotas(creation.getNotas());
            model.setRecibo(creation.getRecibo());
            if (Helper.getBoolean(creation.getRendido()) != null)
                model.setRendido(Helper.getBoolean(creation.getRendido()));

            if (Helper.getLong(creation.getCaja_id()) != null)
                model.setCaja_id(Helper.getLong(creation.getCaja_id()));
            if (Helper.getLong(creation.getConductor_id()) != null)
                model.setConductor_id(Helper.getLong(creation.getConductor_id()));

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
    private String caja_id;
    private String conductor_id;
     */

    public AdelantoDTO toDto(AdelantoModel model) {
        try {
            AdelantoDTO dto = new AdelantoDTO();

            dto.setId(model.getId().toString());
            dto.setDescripcion(model.getDescripcion());
            dto.setFecha(model.getFecha().toString());
            dto.setMonto(model.getMonto().toString());
            dto.setNotas(model.getNotas());
            dto.setRecibo(model.getRecibo());
            dto.setRendido(model.getRendido().toString());

            if (model.getCaja_id() != null)
                dto.setCaja(cajaDAO.findByIdAndEliminadaIsNull(model.getCaja_id()).get().getNombre());
            if (model.getConductor_id() != null)
                dto.setConductor(conductorDAO.findByIdAndEliminadaIsNull(model.getConductor_id()).get().getNombre());

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