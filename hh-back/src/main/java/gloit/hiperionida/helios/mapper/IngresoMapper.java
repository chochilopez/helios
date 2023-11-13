package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.IngresoCreation;
import gloit.hiperionida.helios.mapper.dto.IngresoDTO;
import gloit.hiperionida.helios.model.IngresoModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class IngresoMapper {
    private final UsuarioDAO usuarioDAO;

    public IngresoModel toEntity(IngresoCreation creation) {
        try {
            IngresoModel model = new IngresoModel();

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

    public IngresoDTO toDto(IngresoModel model) {
        try {
            IngresoDTO dto = new IngresoDTO();

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
            dto.setLogueado(model.getAsn());

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