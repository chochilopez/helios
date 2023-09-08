package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ChequeCreation;
import gloit.hiperionida.helios.mapper.dto.ChequeDTO;
import gloit.hiperionida.helios.model.ChequeModel;
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
public class ChequeMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ChequeModel toEntity(ChequeCreation chequeCreation) {
        try {
            ChequeModel chequeModel = new ChequeModel();

            if (Helper.getLong(chequeCreation.getId()) != null)
                chequeModel.setId(Helper.getLong(chequeCreation.getId()));

            if (Helper.getLong(chequeCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(chequeCreation.getCreador_id()));
                if (user.isPresent())
                    chequeModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(chequeCreation.getCreada()))
                chequeModel.setCreada(Helper.stringToLocalDateTime(chequeCreation.getCreada(), ""));
            if (Helper.getLong(chequeCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(chequeCreation.getModificador_id()));
                if (user.isPresent())
                    chequeModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(chequeCreation.getModificada()))
                chequeModel.setModificada(Helper.stringToLocalDateTime(chequeCreation.getModificada(), ""));
            if (Helper.getLong(chequeCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(chequeCreation.getEliminador_id()));
                if (user.isPresent())
                    chequeModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(chequeCreation.getEliminada()))
                chequeModel.setEliminada(Helper.stringToLocalDateTime(chequeCreation.getEliminada(), ""));

            return chequeModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ChequeDTO toDto(ChequeModel chequeModel) {
        try {
            ChequeDTO dto = new ChequeDTO();

            dto.setId(chequeModel.getId().toString());


            if (chequeModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(chequeModel.getCreador()));
            if (chequeModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(chequeModel.getCreada(), ""));
            if (chequeModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(chequeModel.getModificador()));
            if (chequeModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(chequeModel.getModificada(), ""));
            if (chequeModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(chequeModel.getEliminador()));
            if (chequeModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(chequeModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}