package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ConductorCreation;
import gloit.hiperionida.helios.mapper.dto.AdelantoDTO;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;
import gloit.hiperionida.helios.mapper.dto.ConductorDTO;
import gloit.hiperionida.helios.mapper.dto.LicenciaDTO;
import gloit.hiperionida.helios.model.ConductorModel;
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
public class ConductorMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ConductorModel toEntity(ConductorCreation conductorCreation) {
        try {
            ConductorModel conductorModel = new ConductorModel();

            private String id;
            private List<String> adelantos_id;
            private String camion_id;
            private String licencia_id;
            private String direccion;
            private String email;
            private String identificacion;
            private String nombre;
            private String notas;
            private String telefono;

            if (Helper.getLong(conductorCreation.getId()) != null)
                conductorModel.setId(Helper.getLong(conductorCreation.getId()));

            if (Helper.getLong(conductorCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(conductorCreation.getCreador_id()));
                if (user.isPresent())
                    conductorModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(conductorCreation.getCreada()))
                conductorModel.setCreada(Helper.stringToLocalDateTime(conductorCreation.getCreada(), ""));
            if (Helper.getLong(conductorCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(conductorCreation.getModificador_id()));
                if (user.isPresent())
                    conductorModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(conductorCreation.getModificada()))
                conductorModel.setModificada(Helper.stringToLocalDateTime(conductorCreation.getModificada(), ""));
            if (Helper.getLong(conductorCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(conductorCreation.getEliminador_id()));
                if (user.isPresent())
                    conductorModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(conductorCreation.getEliminada()))
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

            private String id;
            private List<AdelantoDTO> adelantos;
            private CamionDTO camion;
            private LicenciaDTO licencia;
            private String direccion;
            private String email;
            private String identificacion;
            private String nombre;
            private String notas;
            private String telefono;


            if (conductorModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(conductorModel.getCreador()));
            if (conductorModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(conductorModel.getCreada(), ""));
            if (conductorModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(conductorModel.getModificador()));
            if (conductorModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(conductorModel.getModificada(), ""));
            if (conductorModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(conductorModel.getEliminador()));
            if (conductorModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(conductorModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}