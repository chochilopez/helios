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
    /*
        private String id;
    private String numero;
    private String categoria;
    private String vencimiento_id;
     */

    public LicenciaModel toEntity(LicenciaCreation creation) {
        try {
            LicenciaModel model = new LicenciaModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setNumero(creation.getNumero());
            model.setCategoria(creation.getCategoria());
            if (Helper.getLong(creation.getVencimiento_id()) != null) {
                Optional<EventoModel> vencimiento = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getVencimiento_id()));
                vencimiento.ifPresent(model::setVencimiento);
            }

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
    /*
        private String id;
    private String numero;
    private String categoria;
    private String vencimiento_id;
     */

    public LicenciaDTO toDto(LicenciaModel model) {
        try {
            LicenciaDTO dto = new LicenciaDTO();

            dto.setId(model.getId().toString());
            dto.setNumero(model.getNumero());
            dto.setCategoria(model.getCategoria());
            if (model.getVencimiento() != null)
                dto.setVencimiento(eventoMapper.toDto(model.getVencimiento()));

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