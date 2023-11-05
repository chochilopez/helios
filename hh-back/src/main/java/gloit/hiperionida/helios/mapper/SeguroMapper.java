package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.mapper.dto.EventoDTO;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.mapper.dto.SeguroDTO;
import gloit.hiperionida.helios.model.EventoModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
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
public class SeguroMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final ProveedorMapper proveedorMapper;
    private final EventoMapper eventoMapper;
    private final ProveedorDAO proveedorDAO;
    private final EventoDAO eventoDAO;
    /*
        private String id;
    private String aseguradora_id;
    private String vencimiento_id;
     */

    public SeguroModel toEntity(SeguroCreation creation) {
        try {
            SeguroModel model = new SeguroModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getLong(creation.getAseguradora_id()) != null) {
                Optional<ProveedorModel> aseguradora = proveedorDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getAseguradora_id()));
                if (aseguradora.isPresent())
                    model.setAseguradora(aseguradora.get());
            }
            if (Helper.getLong(creation.getVencimiento_id()) != null) {
                Optional<EventoModel> vencimiento = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getVencimiento_id()));
                if (vencimiento.isPresent())
                    model.setVencimiento(vencimiento.get());
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
    private String aseguradora_id;
    private String vencimiento_id;
     */

    public SeguroDTO toDto(SeguroModel model) {
        try {
            SeguroDTO dto = new SeguroDTO();

            dto.setId(model.getId().toString());
            if (model.getAseguradora() != null)
                dto.setAseguradora(proveedorMapper.toDto(model.getAseguradora()));
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