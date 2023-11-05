package gloit.hiperionida.helios.util.mapper;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.creation.EmailCreation;
import gloit.hiperionida.helios.util.mapper.dto.EmailDTO;
import gloit.hiperionida.helios.util.model.EmailModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public EmailModel toEntity(EmailCreation creation) {
        try {
            EmailModel model = new EmailModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setSubject(creation.getSubject());
            model.setSender(creation.getSender());
            model.setReciever(creation.getReciever());
            model.setBody(creation.getBody());

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
            log.info("Email creation to entity error. Exception: " + e);
            return null;
        }
    }

    public EmailDTO toDto(EmailModel model) {
        try {
            EmailDTO dto = new EmailDTO();

            dto.setId(model.getId().toString());
            dto.setSubject(model.getSubject());
            dto.setCc(model.getCc());
            dto.setSender(model.getSender());
            dto.setReciever(model.getReciever());
            dto.setBody(model.getBody());
            dto.setSended(model.getSended().toString());

            if (model.getCreador_id() != null)
                dto.setCreador(usuarioDAO.findByIdAndEliminadaIsNull(model.getCreador_id()).get().getNombre());
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null)
                dto.setModificador(usuarioDAO.findByIdAndEliminadaIsNull(model.getModificador_id()).get().getNombre());
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null)
                dto.setEliminador(usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminador_id()).get().getNombre());
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.info("Email entity to dto error. Exception: " + e);
            return null;
        }
    }
}