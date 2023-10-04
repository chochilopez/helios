package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.BancoCreation;
import gloit.hiperionida.helios.mapper.dto.BancoDTO;
import gloit.hiperionida.helios.model.BancoModel;
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
public class BancoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public BancoModel toEntity(BancoCreation bancoCreation) {
        try {
            BancoModel bancoModel = new BancoModel();

            if (Helper.getLong(bancoCreation.getId()) != null)
                bancoModel.setId(Helper.getLong(bancoCreation.getId()));
            bancoModel.setBanco(bancoCreation.getBanco());
            bancoModel.setNotas(bancoCreation.getNotas());

            if (Helper.getLong(bancoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(bancoCreation.getCreador_id()));
                usuario.ifPresent(bancoModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(bancoCreation.getCreada(), "") != null)
                bancoModel.setCreada(Helper.stringToLocalDateTime(bancoCreation.getCreada(), ""));
            if (Helper.getLong(bancoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(bancoCreation.getModificador_id()));
                usuario.ifPresent(bancoModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(bancoCreation.getModificada(), "") != null)
                bancoModel.setModificada(Helper.stringToLocalDateTime(bancoCreation.getModificada(), ""));
            if (Helper.getLong(bancoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(bancoCreation.getEliminador_id()));
                usuario.ifPresent(bancoModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(bancoCreation.getEliminada(), "") != null)
                bancoModel.setEliminada(Helper.stringToLocalDateTime(bancoCreation.getEliminada(), ""));

            return bancoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public BancoDTO toDto(BancoModel bancoModel) {
        try {
            BancoDTO dto = new BancoDTO();

            dto.setId(bancoModel.getId().toString());
            dto.setBanco(bancoModel.getBanco());
            dto.setNotas(bancoModel.getNotas());

            if (bancoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(bancoModel.getCreador()));
            if (Helper.localDateTimeToString(bancoModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(bancoModel.getCreada(), ""));
            if (bancoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(bancoModel.getModificador()));
            if (Helper.localDateTimeToString(bancoModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(bancoModel.getModificada(), ""));
            if (bancoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(bancoModel.getEliminador()));
            if (Helper.localDateTimeToString(bancoModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(bancoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}