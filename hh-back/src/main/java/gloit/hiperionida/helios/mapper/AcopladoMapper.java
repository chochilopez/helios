package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.mapper.dto.AcopladoDTO;
import gloit.hiperionida.helios.model.AcopladoModel;
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
public class AcopladoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public AcopladoModel toEntity(AcopladoCreation acopladoCreation) {
        try {
            AcopladoModel acopladoModel = new AcopladoModel();

            if (Helper.getLong(acopladoCreation.getId()) != null)
                acopladoModel.setId(Helper.getLong(acopladoCreation.getId()));

            if (Helper.getLong(acopladoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(acopladoCreation.getCreador_id()));
                if (user.isPresent())
                    acopladoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(acopladoCreation.getCreada()))
                acopladoModel.setCreada(Helper.stringToLocalDateTime(acopladoCreation.getCreada(), ""));
            if (Helper.getLong(acopladoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(acopladoCreation.getModificador_id()));
                if (user.isPresent())
                    acopladoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(acopladoCreation.getModificada()))
                acopladoModel.setModificada(Helper.stringToLocalDateTime(acopladoCreation.getModificada(), ""));
            if (Helper.getLong(acopladoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(acopladoCreation.getEliminador_id()));
                if (user.isPresent())
                    acopladoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(acopladoCreation.getEliminada()))
                acopladoModel.setEliminada(Helper.stringToLocalDateTime(acopladoCreation.getEliminada(), ""));

            return acopladoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public AcopladoDTO toDto(AcopladoModel acopladoModel) {
        try {
            AcopladoDTO dto = new AcopladoDTO();

            dto.setId(acopladoModel.getId().toString());


            if (acopladoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(acopladoModel.getCreador()));
            if (acopladoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(acopladoModel.getCreada(), ""));
            if (acopladoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(acopladoModel.getModificador()));
            if (acopladoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(acopladoModel.getModificada(), ""));
            if (acopladoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(acopladoModel.getEliminador()));
            if (acopladoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(acopladoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}