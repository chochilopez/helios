package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.GastoCreation;
import gloit.hiperionida.helios.mapper.dto.CategoriaGastoDTO;
import gloit.hiperionida.helios.mapper.dto.GastoDTO;
import gloit.hiperionida.helios.model.CategoriaGastoModel;
import gloit.hiperionida.helios.model.GastoModel;
import gloit.hiperionida.helios.repository.CategoriaGastoDAO;
import gloit.hiperionida.helios.repository.ViajeDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
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
    private final CategoriaGastoDAO categoriaGastoDAO;
    private final UsuarioDAO usuarioDAO;

    public GastoModel toEntity(GastoCreation creation) {
        try {
            GastoModel model = new GastoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getDecimal(creation.getMonto()) != null)
                model.setMonto(Helper.getDecimal(creation.getMonto()));
            model.setNotas(creation.getNotas());

            if (Helper.getLong(creation.getCategoriaGastoId()) != null)
                model.setCategoriaGastoId(Helper.getLong(creation.getCategoriaGastoId()));
            if (Helper.getLong(creation.getViajeId()) != null)
                model.setViajeId(Helper.getLong(creation.getViajeId()));

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
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public GastoDTO toDto(GastoModel model) {
        try {
            GastoDTO dto = new GastoDTO();

            dto.setId(model.getId().toString());
            dto.setMonto(model.getMonto().toString());
            dto.setNotas(model.getNotas());

            if (model.getCategoriaGastoId() != null) {
                CategoriaGastoModel categoriaGastoModel = categoriaGastoDAO.findByIdAndEliminadaIsNull(model.getCategoriaGastoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró la categoría de gasto con id: " + model.getCategoriaGastoId() + "."));
                dto.setCategoriaGasto(categoriaGastoModel.getCategoria());
            }
            model.setViajeId(model.getViajeId());

            if (model.getCreador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreador_id() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificador_id() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminador_id() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminador_id()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminador_id() + "."));
                dto.setEliminador(usuarioModel.getNombre());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().toString());

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}