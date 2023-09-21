package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CajaCreation;
import gloit.hiperionida.helios.mapper.dto.CajaDTO;
import gloit.hiperionida.helios.model.CajaModel;
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
public class CajaMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CajaModel toEntity(CajaCreation cajaCreation) {
        try {
            CajaModel cajaModel = new CajaModel();

            private String id;
            private String nombre;

            if (Helper.getLong(cajaCreation.getId()) != null)
                cajaModel.setId(Helper.getLong(cajaCreation.getId()));

            if (Helper.getLong(cajaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(cajaCreation.getCreador_id()));
                if (user.isPresent())
                    cajaModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(cajaCreation.getCreada()))
                cajaModel.setCreada(Helper.stringToLocalDateTime(cajaCreation.getCreada(), ""));
            if (Helper.getLong(cajaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(cajaCreation.getModificador_id()));
                if (user.isPresent())
                    cajaModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(cajaCreation.getModificada()))
                cajaModel.setModificada(Helper.stringToLocalDateTime(cajaCreation.getModificada(), ""));
            if (Helper.getLong(cajaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(cajaCreation.getEliminador_id()));
                if (user.isPresent())
                    cajaModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(cajaCreation.getEliminada()))
                cajaModel.setEliminada(Helper.stringToLocalDateTime(cajaCreation.getEliminada(), ""));

            return cajaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CajaDTO toDto(CajaModel cajaModel) {
        try {
            CajaDTO dto = new CajaDTO();

            private String id;
            private String nombre;

            dto.setId(cajaModel.getId().toString());


            if (cajaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(cajaModel.getCreador()));
            if (cajaModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(cajaModel.getCreada(), ""));
            if (cajaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(cajaModel.getModificador()));
            if (cajaModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(cajaModel.getModificada(), ""));
            if (cajaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(cajaModel.getEliminador()));
            if (cajaModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(cajaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}