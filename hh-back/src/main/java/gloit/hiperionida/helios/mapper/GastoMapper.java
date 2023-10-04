package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.GastoCreation;
import gloit.hiperionida.helios.mapper.dto.CategoriaGastoDTO;
import gloit.hiperionida.helios.mapper.dto.GastoDTO;
import gloit.hiperionida.helios.model.CategoriaGastoModel;
import gloit.hiperionida.helios.model.GastoModel;
import gloit.hiperionida.helios.repository.CategoriaGastoDAO;
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
public class GastoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final CategoriaGastoDAO categoriaGastoDAO;
    private final CategoriaGastoMapper categoriaGastoMapper;

    public GastoModel toEntity(GastoCreation gastoCreation) {
        try {
            GastoModel gastoModel = new GastoModel();

            if (Helper.getLong(gastoCreation.getId()) != null)
                gastoModel.setId(Helper.getLong(gastoCreation.getId()));
            if (Helper.getDecimal(gastoCreation.getMonto()) != null)
                gastoModel.setMonto(Helper.getDecimal(gastoCreation.getMonto()));
            gastoModel.setNotas(gastoCreation.getNotas());
            if (Helper.getLong(gastoCreation.getCategoriaGasto_id()) != null) {
                Optional<CategoriaGastoModel> categoriaGasto = categoriaGastoDAO.findByIdAndEliminadaIsNull(Helper.getLong(gastoCreation.getCategoriaGasto_id()));
                categoriaGasto.ifPresent(gastoModel::setCategoriaGasto);
            }

            if (Helper.getLong(gastoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(gastoCreation.getCreador_id()));
                usuario.ifPresent(gastoModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(gastoCreation.getCreada(), "") != null)
                gastoModel.setCreada(Helper.stringToLocalDateTime(gastoCreation.getCreada(), ""));
            if (Helper.getLong(gastoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(gastoCreation.getModificador_id()));
                usuario.ifPresent(gastoModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(gastoCreation.getModificada(), "") != null)
                gastoModel.setModificada(Helper.stringToLocalDateTime(gastoCreation.getModificada(), ""));
            if (Helper.getLong(gastoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(gastoCreation.getEliminador_id()));
                usuario.ifPresent(gastoModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(gastoCreation.getEliminada(), "") != null)
                gastoModel.setEliminada(Helper.stringToLocalDateTime(gastoCreation.getEliminada(), ""));

            return gastoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public GastoDTO toDto(GastoModel gastoModel) {
        try {
            GastoDTO dto = new GastoDTO();

            dto.setId(gastoModel.getId().toString());
            dto.setMonto(gastoModel.getMonto().toString());
            dto.setNotas(gastoModel.getNotas());
            if (gastoModel.getCategoriaGasto() != null)
                dto.setCategoriaGasto(categoriaGastoMapper.toDto(gastoModel.getCategoriaGasto()));

            if (gastoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(gastoModel.getCreador()));
            if (Helper.localDateTimeToString(gastoModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(gastoModel.getCreada(), ""));
            if (gastoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(gastoModel.getModificador()));
            if (Helper.localDateTimeToString(gastoModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(gastoModel.getModificada(), ""));
            if (gastoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(gastoModel.getEliminador()));
            if (Helper.localDateTimeToString(gastoModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(gastoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}