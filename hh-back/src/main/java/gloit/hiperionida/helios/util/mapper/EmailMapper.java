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

    public EmailModel toEntity(EmailCreation emailCreation) {
        try {
            EmailModel emailModel = new EmailModel();

            if (Helper.getLong(emailCreation.getId()) != null)
                emailModel.setId(Helper.getLong(emailCreation.getId()));
            emailModel.setSubject(emailCreation.getSubject());
            emailModel.setSender(emailCreation.getSender());
            emailModel.setReciever(emailCreation.getReciever());
            emailModel.setBody(emailCreation.getBody());

            if (Helper.getLong(emailCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(emailCreation.getCreador_id()));
                if (usuario.isPresent())
                    emailModel.setCreador(usuario.get());
            }
            if (!Helper.isEmptyString(emailCreation.getCreada()))
                emailModel.setCreada(Helper.stringToLocalDateTime(emailCreation.getCreada(), ""));
            if (Helper.getLong(emailCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(emailCreation.getModificador_id()));
                if (usuario.isPresent())
                    emailModel.setModificador(usuario.get());
            }
            if (!Helper.isEmptyString(emailCreation.getModificada()))
                emailModel.setModificada(Helper.stringToLocalDateTime(emailCreation.getModificada(), ""));
            if (Helper.getLong(emailCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(emailCreation.getEliminador_id()));
                if (usuario.isPresent())
                    emailModel.setEliminador(usuario.get());
            }
            if (!Helper.isEmptyString(emailCreation.getEliminada()))
                emailModel.setEliminada(Helper.stringToLocalDateTime(emailCreation.getEliminada(), ""));

            log.info("Se convirtio correctamente de EmailCreation a entidad.");
            
            return emailModel;
        } catch (Exception e) {
            log.info("Email creation to entity error. Exception: " + e);
            return null;
        }
    }

    public EmailDTO toDto(EmailModel emailModel) {
        try {
            EmailDTO dto = new EmailDTO();

            dto.setId(emailModel.getId().toString());
            dto.setSubject(emailModel.getSubject());
            dto.setCc(emailModel.getCc());
            dto.setSender(emailModel.getSender());
            dto.setReciever(emailModel.getReciever());
            dto.setBody(emailModel.getBody());
            dto.setSended(emailModel.getSended().toString());

            if (emailModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(emailModel.getCreador()));
            if (emailModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(emailModel.getCreada(), ""));
            if (emailModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(emailModel.getModificador()));
            if (emailModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(emailModel.getModificada(), ""));
            if (emailModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(emailModel.getEliminador()));
            if (emailModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(emailModel.getEliminada(), ""));

            log.info("Se convirtio correctamente de entidad a DTO.");

            return dto;
        } catch (Exception e) {
            log.info("Email entity to dto error. Exception: " + e);
            return null;
        }
    }
}