package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.VisitaCreation;
import gloit.hiperionida.helios.mapper.dto.VisitaDTO;
import gloit.hiperionida.helios.model.VisitaModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.mapper.dto.UsuarioDTO;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class VisitaMapper {
    private final UsuarioMapper usuarioMapper;
    public final UsuarioDAO usuarioDAO;

    public VisitaModel toEntity(VisitaCreation visitaCreation) {
        try {
            VisitaModel visitaModel = new VisitaModel();

            private String id;
            private String ip;
            private String hostname;
            private String country_name;
            private String state_prov;
            private String district;
            private String city;
            private String zipcode;
            private String country_flag;
            private String isp;
            private String organization;
            private String asn;
            private String visitante_id;

            if (Helper.getLong(visitaCreation.getId()) != null)
                visitaModel.setId(Helper.getLong(visitaCreation.getId()));
            visitaModel.setIp(visitaCreation.getIp());
            visitaModel.setHostname(visitaCreation.getHostname());
            visitaModel.setCountry_name(visitaCreation.getCountry_name());
            visitaModel.setState_prov(visitaCreation.getState_prov());
            visitaModel.setDistrict(visitaCreation.getDistrict());


            if (Helper.getLong(visitaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(visitaCreation.getCreador_id()));
                if (user.isPresent())
                    visitaModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(visitaCreation.getCreada()))
                visitaModel.setCreada(Helper.stringToLocalDateTime(visitaCreation.getCreada(), ""));
            if (Helper.getLong(visitaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(visitaCreation.getModificador_id()));
                if (user.isPresent())
                    visitaModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(visitaCreation.getModificada()))
                visitaModel.setModificada(Helper.stringToLocalDateTime(visitaCreation.getModificada(), ""));
            if (Helper.getLong(visitaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(visitaCreation.getEliminador_id()));
                if (user.isPresent())
                    visitaModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(visitaCreation.getEliminada()))
                visitaModel.setEliminada(Helper.stringToLocalDateTime(visitaCreation.getEliminada(), ""));

            return visitaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public VisitaDTO toDto(VisitaModel visitaModel) {
        try {
            VisitaDTO dto = new VisitaDTO();

            private String id;
            private String ip;
            private String hostname;
            private String country_name;
            private String state_prov;
            private String district;
            private String city;
            private String zipcode;
            private String country_flag;
            private String isp;
            private String organization;
            private String asn; //
            private UsuarioDTO visitante;

            dto.setId(visitaModel.getId().toString());

            if (visitaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(visitaModel.getCreador()));
            if (visitaModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(visitaModel.getCreada(), ""));
            if (visitaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(visitaModel.getModificador()));
            if (visitaModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(visitaModel.getModificada(), ""));
            if (visitaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(visitaModel.getEliminador()));
            if (visitaModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(visitaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}