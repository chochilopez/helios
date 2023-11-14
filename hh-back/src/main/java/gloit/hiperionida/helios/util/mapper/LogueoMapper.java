package gloit.hiperionida.helios.util.mapper;

import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.creation.LogueoCreation;
import gloit.hiperionida.helios.util.mapper.dto.LogueoDTO;
import gloit.hiperionida.helios.util.model.LogueoModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class LogueoMapper {
    private final UsuarioMapper usuarioMapper;
    public final UsuarioDAO usuarioDAO;

    public LogueoModel toEntity(LogueoCreation creation) {
        try {
            LogueoModel model = new LogueoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setIp(creation.getIp());
            model.setHostname(creation.getHostname());
            model.setCountry_name(creation.getCountry_name());
            model.setState_prov(creation.getState_prov());
            model.setDistrict(creation.getDistrict());
            model.setCity(creation.getCity());
            model.setZipcode(creation.getZipcode());
            model.setCountry_flag(creation.getCountry_flag());
            model.setIsp(creation.getIsp());
            model.setOrganization(creation.getOrganization());
            model.setAsn(creation.getAsn());
            model.setUsername(creation.getUsername());
            if (Helper.getBoolean(creation.getLogueado()) != null)
                model.setLogueado(Helper.getBoolean(creation.getLogueado()));
            
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

    public LogueoDTO toDto(LogueoModel model) {
        try {
            LogueoDTO dto = new LogueoDTO();

            dto.setId(model.getId().toString());
            dto.setIp(model.getIp());
            dto.setHostname(model.getHostname());
            dto.setCountry_name(model.getCountry_name());
            dto.setState_prov(model.getState_prov());
            dto.setDistrict(model.getDistrict());
            dto.setCity(model.getCity());
            dto.setZipcode(model.getZipcode());
            dto.setCountry_flag(model.getCountry_flag());
            dto.setIsp(model.getIsp());
            dto.setOrganization(model.getOrganization());
            dto.setAsn(model.getAsn());
            dto.setUsername(model.getUsername());
            dto.setLogueado(model.getLogueado().toString());

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