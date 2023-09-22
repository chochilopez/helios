package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.UbicacionCreation;
import gloit.hiperionida.helios.mapper.dto.UbicacionDTO;
import gloit.hiperionida.helios.model.UbicacionModel;
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
public class UbicacionMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public UbicacionModel toEntity(UbicacionCreation ubicacionCreation) {
        try {
            UbicacionModel ubicacionModel = new UbicacionModel();

            private String id;
            private String ciudad;
            private String departamento;
            private String pais;

            if (Helper.getLong(ubicacionCreation.getId()) != null)
                ubicacionModel.setId(Helper.getLong(ubicacionCreation.getId()));

            if (Helper.getLong(ubicacionCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(ubicacionCreation.getCreador_id()));
                if (user.isPresent())
                    ubicacionModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(ubicacionCreation.getCreada()))
                ubicacionModel.setCreada(Helper.stringToLocalDateTime(ubicacionCreation.getCreada(), ""));
            if (Helper.getLong(ubicacionCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(ubicacionCreation.getModificador_id()));
                if (user.isPresent())
                    ubicacionModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(ubicacionCreation.getModificada()))
                ubicacionModel.setModificada(Helper.stringToLocalDateTime(ubicacionCreation.getModificada(), ""));
            if (Helper.getLong(ubicacionCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(ubicacionCreation.getEliminador_id()));
                if (user.isPresent())
                    ubicacionModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(ubicacionCreation.getEliminada()))
                ubicacionModel.setEliminada(Helper.stringToLocalDateTime(ubicacionCreation.getEliminada(), ""));

            return ubicacionModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public UbicacionDTO toDto(UbicacionModel ubicacionModel) {
        try {
            UbicacionDTO dto = new UbicacionDTO();

            private String id;
            private String ciudad;
            private String departamento;
            private String pais;

            dto.setId(ubicacionModel.getId().toString());

            if (ubicacionModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(ubicacionModel.getCreador()));
            if (ubicacionModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(ubicacionModel.getCreada(), ""));
            if (ubicacionModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(ubicacionModel.getModificador()));
            if (ubicacionModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(ubicacionModel.getModificada(), ""));
            if (ubicacionModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(ubicacionModel.getEliminador()));
            if (ubicacionModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(ubicacionModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}