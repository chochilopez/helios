package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.LicenciaCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.mapper.dto.LicenciaDTO;
import gloit.hiperionida.helios.model.LicenciaModel;
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
public class LicenciaMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public LicenciaModel toEntity(LicenciaCreation licenciaCreation) {
        try {
            LicenciaModel licenciaModel = new LicenciaModel();


            private String id;
            private String numero;
            private String categoria;
            private String vencimiento_id;


            if (Helper.getLong(licenciaCreation.getId()) != null)
                licenciaModel.setId(Helper.getLong(licenciaCreation.getId()));

            if (Helper.getLong(licenciaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(licenciaCreation.getCreador_id()));
                if (user.isPresent())
                    licenciaModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(licenciaCreation.getCreada()))
                licenciaModel.setCreada(Helper.stringToLocalDateTime(licenciaCreation.getCreada(), ""));
            if (Helper.getLong(licenciaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(licenciaCreation.getModificador_id()));
                if (user.isPresent())
                    licenciaModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(licenciaCreation.getModificada()))
                licenciaModel.setModificada(Helper.stringToLocalDateTime(licenciaCreation.getModificada(), ""));
            if (Helper.getLong(licenciaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(licenciaCreation.getEliminador_id()));
                if (user.isPresent())
                    licenciaModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(licenciaCreation.getEliminada()))
                licenciaModel.setEliminada(Helper.stringToLocalDateTime(licenciaCreation.getEliminada(), ""));

            return licenciaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public LicenciaDTO toDto(LicenciaModel licenciaModel) {
        try {
            LicenciaDTO dto = new LicenciaDTO();

            private String id;
            private String numero;
            private String categoria;
            private EventoDTO vencimiento;

            dto.setId(licenciaModel.getId().toString());


            if (licenciaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(licenciaModel.getCreador()));
            if (licenciaModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(licenciaModel.getCreada(), ""));
            if (licenciaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(licenciaModel.getModificador()));
            if (licenciaModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(licenciaModel.getModificada(), ""));
            if (licenciaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(licenciaModel.getEliminador()));
            if (licenciaModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(licenciaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}