package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.AdelantoCreation;
import gloit.hiperionida.helios.mapper.dto.AdelantoDTO;
import gloit.hiperionida.helios.model.AdelantoModel;
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
public class AdelantoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public AdelantoModel toEntity(AdelantoCreation adelantoCreation) {
        try {
            AdelantoModel adelantoModel = new AdelantoModel();

            if (Helper.getLong(adelantoCreation.getId()) != null)
                adelantoModel.setId(Helper.getLong(adelantoCreation.getId()));

            if (Helper.getLong(adelantoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(adelantoCreation.getCreador_id()));
                if (user.isPresent())
                    adelantoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(adelantoCreation.getCreada()))
                adelantoModel.setCreada(Helper.stringToLocalDateTime(adelantoCreation.getCreada(), ""));
            if (Helper.getLong(adelantoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(adelantoCreation.getModificador_id()));
                if (user.isPresent())
                    adelantoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(adelantoCreation.getModificada()))
                adelantoModel.setModificada(Helper.stringToLocalDateTime(adelantoCreation.getModificada(), ""));
            if (Helper.getLong(adelantoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(adelantoCreation.getEliminador_id()));
                if (user.isPresent())
                    adelantoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(adelantoCreation.getEliminada()))
                adelantoModel.setEliminada(Helper.stringToLocalDateTime(adelantoCreation.getEliminada(), ""));

            return adelantoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public AdelantoDTO toDto(AdelantoModel adelantoModel) {
        try {
            AdelantoDTO dto = new AdelantoDTO();

            dto.setId(adelantoModel.getId().toString());


            if (adelantoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(adelantoModel.getCreador()));
            if (adelantoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(adelantoModel.getCreada(), ""));
            if (adelantoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(adelantoModel.getModificador()));
            if (adelantoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(adelantoModel.getModificada(), ""));
            if (adelantoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(adelantoModel.getEliminador()));
            if (adelantoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(adelantoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}