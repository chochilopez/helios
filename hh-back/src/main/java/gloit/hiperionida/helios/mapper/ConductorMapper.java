package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ConductorCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.*;
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
public class ConductorMapper {
    private final DireccionDAO direccionDAO;
    private final EventoDAO eventoDAO;
    private final LicenciaDAO licenciaDAO;
    private final UsuarioDAO usuarioDAO;

    public ConductorModel toEntity(ConductorCreation creation) {
        try {
            ConductorModel model = new ConductorModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setDireccion(creation.getDireccion());
            model.setEmail(creation.getEmail());
            model.setIdentificacion(creation.getIdentificacion());
            model.setNombre(creation.getNombre());
            model.setNotas(creation.getNotas());
            model.setTelefono(creation.getTelefono());
            if (Helper.getLong(creation.getLicenciaId()) != null)
                model.setLicenciaId(Helper.getLong(creation.getLicenciaId()));

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

    public ConductorDTO toDto(ConductorModel model) {
        try {
            ConductorDTO dto = new ConductorDTO();

            dto.setId(model.getId().toString());
            dto.setEmail(model.getEmail());
            dto.setDireccion(model.getDireccion());
            dto.setIdentificacion(model.getIdentificacion());
            dto.setNombre(model.getNombre());
            dto.setNotas(model.getNotas());
            dto.setTelefono(model.getTelefono());
            if (model.getLicenciaId() != null) {
                Optional<LicenciaModel> licenciaModel = licenciaDAO.findByIdAndEliminadaIsNull(model.getLicenciaId());
                Optional<EventoModel> eventoModel = eventoDAO.findByIdAndEliminadaIsNull(licenciaModel.get().getVencimientoId());
                dto.setCategotiaLicencia(licenciaModel.get().getCategoria());
                dto.setVencimiento(eventoModel.get().getFecha().toString());
            }

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