package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.mapper.dto.SeguroDTO;
import gloit.hiperionida.helios.mapper.dto.ServicioDTO;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CamionMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CamionModel toEntity(CamionCreation camionCreation) {
        try {
            CamionModel camionModel = new CamionModel();

            private String id;
            private String numeroChasis;
            private String numeroMotor;
            private String pesoArrastre;
            private String seguro_id;
            private List<String> servicios_id;
            private List<String> neumaticos_id;

            if (Helper.getLong(camionCreation.getId()) != null)
                camionModel.setId(Helper.getLong(camionCreation.getId()));

            if (Helper.getLong(camionCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(camionCreation.getCreador_id()));
                if (user.isPresent())
                    camionModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(camionCreation.getCreada()))
                camionModel.setCreada(Helper.stringToLocalDateTime(camionCreation.getCreada(), ""));
            if (Helper.getLong(camionCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(camionCreation.getModificador_id()));
                if (user.isPresent())
                    camionModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(camionCreation.getModificada()))
                camionModel.setModificada(Helper.stringToLocalDateTime(camionCreation.getModificada(), ""));
            if (Helper.getLong(camionCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(camionCreation.getEliminador_id()));
                if (user.isPresent())
                    camionModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(camionCreation.getEliminada()))
                camionModel.setEliminada(Helper.stringToLocalDateTime(camionCreation.getEliminada(), ""));

            return camionModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CamionDTO toDto(CamionModel camionModel) {
        try {
            CamionDTO dto = new CamionDTO();

            private String id;
            private String numeroChasis;
            private String numeroMotor;
            private String pesoArrastre;
            private SeguroDTO seguro;
            private List<ServicioDTO> servicios;
            private List<NeumaticoDTO> neumaticos;


            dto.setId(camionModel.getId().toString());


            if (camionModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(camionModel.getCreador()));
            if (camionModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(camionModel.getCreada(), ""));
            if (camionModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(camionModel.getModificador()));
            if (camionModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(camionModel.getModificada(), ""));
            if (camionModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(camionModel.getEliminador()));
            if (camionModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(camionModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}