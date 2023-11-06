package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ServicioCreation;
import gloit.hiperionida.helios.mapper.dto.ServicioDTO;
import gloit.hiperionida.helios.model.ServicioModel;
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
public class ServicioMapper {
    private final UsuarioDAO usuarioDAO;

    public ServicioModel toEntity(ServicioCreation creation) {
        try {
            ServicioModel model = new ServicioModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getDecimal(creation.getKmServicio()) != null)
                model.setKmServicio(Helper.getDecimal(creation.getKmServicio()));
            if (Helper.getDecimal(creation.getKmActual()) != null)
                model.setKmActual(Helper.getDecimal(creation.getKmActual()));
            if (Helper.getDecimal(creation.getKmProximo()) != null)
                model.setKmProximo(Helper.getDecimal(creation.getKmProximo()));
            model.setTipo(creation.getTipo());

            if (Helper.getLong(creation.getCamion_id()) != null)
                model.setCamion_id(Helper.getLong(creation.getCamion_id()));

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

    public ServicioDTO toDto(ServicioModel model) {
        try {
            ServicioDTO dto = new ServicioDTO();

            dto.setId(model.getId().toString());
            dto.setKmServicio(model.getKmServicio().toString());
            dto.setKmActual(model.getKmActual().toString());
            dto.setKmProximo(model.getKmProximo().toString());
            dto.setTipo(model.getTipo());

            if (model.getCamion_id() != null)
            dto.setCamion_id(model.getCamion_id().toString());

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
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}