package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PagoCreation;
import gloit.hiperionida.helios.mapper.dto.PagoDTO;
import gloit.hiperionida.helios.model.PagoModel;
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
public class PagoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public PagoModel toEntity(PagoCreation pagoCreation) {
        try {
            PagoModel pagoModel = new PagoModel();

            if (Helper.getLong(pagoCreation.getId()) != null)
                pagoModel.setId(Helper.getLong(pagoCreation.getId()));

            if (Helper.getLong(pagoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(pagoCreation.getCreador_id()));
                if (user.isPresent())
                    pagoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(pagoCreation.getCreada()))
                pagoModel.setCreada(Helper.stringToLocalDateTime(pagoCreation.getCreada(), ""));
            if (Helper.getLong(pagoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(pagoCreation.getModificador_id()));
                if (user.isPresent())
                    pagoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(pagoCreation.getModificada()))
                pagoModel.setModificada(Helper.stringToLocalDateTime(pagoCreation.getModificada(), ""));
            if (Helper.getLong(pagoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(pagoCreation.getEliminador_id()));
                if (user.isPresent())
                    pagoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(pagoCreation.getEliminada()))
                pagoModel.setEliminada(Helper.stringToLocalDateTime(pagoCreation.getEliminada(), ""));

            return pagoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public PagoDTO toDto(PagoModel pagoModel) {
        try {
            PagoDTO dto = new PagoDTO();

            dto.setId(pagoModel.getId().toString());


            if (pagoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(pagoModel.getCreador()));
            if (pagoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(pagoModel.getCreada(), ""));
            if (pagoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(pagoModel.getModificador()));
            if (pagoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(pagoModel.getModificada(), ""));
            if (pagoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(pagoModel.getEliminador()));
            if (pagoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(pagoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}