package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.LicenciaCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.mapper.dto.LicenciaDTO;
import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.model.LicenciaModel;
import gloit.hiperionida.helios.repository.EventoDAO;
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
public class LicenciaMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final EventoDAO eventoDAO;
    private final EventoMapper eventoMapper;

    public LicenciaModel toEntity(LicenciaCreation licenciaCreation) {
        try {
            LicenciaModel licenciaModel = new LicenciaModel();

            if (Helper.getLong(licenciaCreation.getId()) != null)
                licenciaModel.setId(Helper.getLong(licenciaCreation.getId()));
            licenciaModel.setNumero(licenciaCreation.getNumero());
            licenciaModel.setCategoria(licenciaCreation.getCategoria());
            if (Helper.getLong(licenciaCreation.getVencimiento_id()) != null) {
                Optional<EventoModel> vencimiento = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(licenciaCreation.getVencimiento_id()));
                vencimiento.ifPresent(licenciaModel::setVencimiento);
            }

            if (Helper.getLong(licenciaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(licenciaCreation.getCreador_id()));
                usuario.ifPresent(licenciaModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(licenciaCreation.getCreada(), "") != null)
                licenciaModel.setCreada(Helper.stringToLocalDateTime(licenciaCreation.getCreada(), ""));
            if (Helper.getLong(licenciaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(licenciaCreation.getModificador_id()));
                usuario.ifPresent(licenciaModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(licenciaCreation.getModificada(), "") != null)
                licenciaModel.setModificada(Helper.stringToLocalDateTime(licenciaCreation.getModificada(), ""));
            if (Helper.getLong(licenciaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(licenciaCreation.getEliminador_id()));
                usuario.ifPresent(licenciaModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(licenciaCreation.getEliminada(), "") != null)
                licenciaModel.setEliminada(Helper.stringToLocalDateTime(licenciaCreation.getEliminada(), ""));

            return licenciaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public LicenciaDTO toDto(LicenciaModel licenciaModel) {
        try {
            LicenciaDTO dto = new LicenciaDTO();

            dto.setId(licenciaModel.getId().toString());
            dto.setNumero(licenciaModel.getNumero());
            dto.setCategoria(licenciaModel.getCategoria());
            if (licenciaModel.getVencimiento() != null)
                dto.setVencimiento(eventoMapper.toDto(licenciaModel.getVencimiento()));

            if (licenciaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(licenciaModel.getCreador()));
            if (Helper.localDateTimeToString(licenciaModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(licenciaModel.getCreada(), ""));
            if (licenciaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(licenciaModel.getModificador()));
            if (Helper.localDateTimeToString(licenciaModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(licenciaModel.getModificada(), ""));
            if (licenciaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(licenciaModel.getEliminador()));
            if (Helper.localDateTimeToString(licenciaModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(licenciaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}