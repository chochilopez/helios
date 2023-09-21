package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ConciliacionBancoCreation;
import gloit.hiperionida.helios.mapper.dto.BancoDTO;
import gloit.hiperionida.helios.mapper.dto.ConciliacionBancoDTO;
import gloit.hiperionida.helios.model.ConciliacionBancoModel;
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
public class ConciliacionBancoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ConciliacionBancoModel toEntity(ConciliacionBancoCreation conciliacionBancoCreation) {
        try {
            ConciliacionBancoModel conciliacionBancoModel = new ConciliacionBancoModel();


            private String id;
            private String movimiento;
            private String fecha;
            private String concepto;
            private String monto;
            private String banco_id;

            if (Helper.getLong(conciliacionBancoCreation.getId()) != null)
                conciliacionBancoModel.setId(Helper.getLong(conciliacionBancoCreation.getId()));

            if (Helper.getLong(conciliacionBancoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(conciliacionBancoCreation.getCreador_id()));
                if (user.isPresent())
                    conciliacionBancoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(conciliacionBancoCreation.getCreada()))
                conciliacionBancoModel.setCreada(Helper.stringToLocalDateTime(conciliacionBancoCreation.getCreada(), ""));
            if (Helper.getLong(conciliacionBancoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(conciliacionBancoCreation.getModificador_id()));
                if (user.isPresent())
                    conciliacionBancoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(conciliacionBancoCreation.getModificada()))
                conciliacionBancoModel.setModificada(Helper.stringToLocalDateTime(conciliacionBancoCreation.getModificada(), ""));
            if (Helper.getLong(conciliacionBancoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(conciliacionBancoCreation.getEliminador_id()));
                if (user.isPresent())
                    conciliacionBancoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(conciliacionBancoCreation.getEliminada()))
                conciliacionBancoModel.setEliminada(Helper.stringToLocalDateTime(conciliacionBancoCreation.getEliminada(), ""));

            return conciliacionBancoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ConciliacionBancoDTO toDto(ConciliacionBancoModel conciliacionBancoModel) {
        try {
            ConciliacionBancoDTO dto = new ConciliacionBancoDTO();

            private String id;
            private String movimiento;
            private String fecha;
            private String concepto;
            private String monto;
            private BancoDTO banco;

            dto.setId(conciliacionBancoModel.getId().toString());


            if (conciliacionBancoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(conciliacionBancoModel.getCreador()));
            if (conciliacionBancoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(conciliacionBancoModel.getCreada(), ""));
            if (conciliacionBancoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(conciliacionBancoModel.getModificador()));
            if (conciliacionBancoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(conciliacionBancoModel.getModificada(), ""));
            if (conciliacionBancoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(conciliacionBancoModel.getEliminador()));
            if (conciliacionBancoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(conciliacionBancoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}