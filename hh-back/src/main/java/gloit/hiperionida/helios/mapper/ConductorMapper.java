package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ConductorCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.AdelantoDAO;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.repository.LicenciaDAO;
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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final AdelantoDAO adelantoDAO;
    private final AdelantoMapper adelantoMapper;
    private final CamionDAO camionDAO;
    private final CamionMapper camionMapper;
    private final LicenciaDAO licenciaDAO;
    private final LicenciaMapper licenciaMapper;

    public ConductorModel toEntity(ConductorCreation conductorCreation) {
        try {
            ConductorModel conductorModel = new ConductorModel();

            if (Helper.getLong(conductorCreation.getId()) != null)
                conductorModel.setId(Helper.getLong(conductorCreation.getId()));
            conductorModel.setEmail(conductorCreation.getEmail());
            conductorModel.setIdentificacion(conductorCreation.getIdentificacion());
            conductorModel.setNombre(conductorCreation.getNombre());
            conductorModel.setNotas(conductorCreation.getNotas());
            conductorModel.setTelefono(conductorCreation.getTelefono());
            Set<AdelantoModel> adelantos = new HashSet<>();
            if (conductorCreation.getAdelantos_id() != null) {
                for (String adelanto_id : conductorCreation.getAdelantos_id()) {
                    if (Helper.getLong(adelanto_id) != null) {
                        Optional<AdelantoModel> adelanto = adelantoDAO.findByIdAndEliminadaIsNull(Helper.getLong(adelanto_id));
                        adelanto.ifPresent(adelantos::add);
                    }
                }
            }
            conductorModel.setAdelantos(adelantos);
            if (Helper.getLong(conductorCreation.getCamion_id()) != null) {
                Optional<CamionModel> camion = camionDAO.findByIdAndEliminadaIsNull(Helper.getLong(conductorCreation.getCamion_id()));
                camion.ifPresent(conductorModel::setCamion);
            }
            if (Helper.getLong(conductorCreation.getLicencia_id()) != null) {
                Optional<LicenciaModel> licencia = licenciaDAO.findByIdAndEliminadaIsNull(Helper.getLong(conductorCreation.getLicencia_id()));
                licencia.ifPresent(conductorModel::setLicencia);
            }

            if (Helper.getLong(conductorCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(conductorCreation.getCreador_id()));
                usuario.ifPresent(conductorModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(conductorCreation.getCreada(), "") != null)
                conductorModel.setCreada(Helper.stringToLocalDateTime(conductorCreation.getCreada(), ""));
            if (Helper.getLong(conductorCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(conductorCreation.getModificador_id()));
                usuario.ifPresent(conductorModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(conductorCreation.getModificada(), "") != null)
                conductorModel.setModificada(Helper.stringToLocalDateTime(conductorCreation.getModificada(), ""));
            if (Helper.getLong(conductorCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(conductorCreation.getEliminador_id()));
                usuario.ifPresent(conductorModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(conductorCreation.getEliminada(), "") != null)
                conductorModel.setEliminada(Helper.stringToLocalDateTime(conductorCreation.getEliminada(), ""));

            return conductorModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ConductorDTO toDto(ConductorModel conductorModel) {
        try {
            ConductorDTO dto = new ConductorDTO();

            dto.setId(conductorModel.getId().toString());
            dto.setEmail(conductorModel.getEmail());
            dto.setIdentificacion(conductorModel.getIdentificacion());
            dto.setNombre(conductorModel.getNombre());
            dto.setNotas(conductorModel.getNotas());
            dto.setTelefono(conductorModel.getTelefono());
            if (!conductorModel.getAdelantos().isEmpty()) {
                List<AdelantoDTO> adelantoDTOS = new ArrayList<>();
                for (AdelantoModel adelanto:conductorModel.getAdelantos()) {
                    adelantoDTOS.add(adelantoMapper.toDto(adelanto));
                }
                dto.setAdelantos(adelantoDTOS);
            }
            if (conductorModel.getCamion() != null)
                dto.setCamion(camionMapper.toDto(conductorModel.getCamion()));
            if (conductorModel.getLicencia() != null)
                dto.setLicencia(licenciaMapper.toDto(conductorModel.getLicencia()));

            if (conductorModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(conductorModel.getCreador()));
            if (Helper.localDateTimeToString(conductorModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(conductorModel.getCreada(), ""));
            if (conductorModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(conductorModel.getModificador()));
            if (Helper.localDateTimeToString(conductorModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(conductorModel.getModificada(), ""));
            if (conductorModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(conductorModel.getEliminador()));
            if (Helper.localDateTimeToString(conductorModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(conductorModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}