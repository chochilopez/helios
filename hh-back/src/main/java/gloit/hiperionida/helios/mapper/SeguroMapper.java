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

    public SeguroModel toEntity(SeguroCreation seguroCreation) {
        try {
            SeguroModel seguroModel = new SeguroModel();

            if (Helper.getLong(seguroCreation.getId()) != null)
                seguroModel.setId(Helper.getLong(seguroCreation.getId()));
            if (Helper.getLong(seguroCreation.getAseguradora_id()) != null) {
                Optional<ProveedorModel> aseguradora = proveedorDAO.findById(Helper.getLong(seguroCreation.getAseguradora_id()));
                if (aseguradora.isPresent())
                    seguroModel.setAseguradora(aseguradora.get());
            }
            if (Helper.getLong(seguroCreation.getVencimiento_id()) != null) {
                Optional<EventoModel> vencimiento = eventoDAO.findById(Helper.getLong(seguroCreation.getVencimiento_id()));
                if (vencimiento.isPresent())
                    seguroModel.setVencimiento(vencimiento.get());
            }

            if (Helper.getLong(seguroCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(seguroCreation.getCreador_id()));
                if (user.isPresent())
                    seguroModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(seguroCreation.getCreada()))
                seguroModel.setCreada(Helper.stringToLocalDateTime(seguroCreation.getCreada(), ""));
            if (Helper.getLong(seguroCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(seguroCreation.getModificador_id()));
                if (user.isPresent())
                    seguroModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(seguroCreation.getModificada()))
                seguroModel.setModificada(Helper.stringToLocalDateTime(seguroCreation.getModificada(), ""));
            if (Helper.getLong(seguroCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(seguroCreation.getEliminador_id()));
                if (user.isPresent())
                    seguroModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(seguroCreation.getEliminada()))
                seguroModel.setEliminada(Helper.stringToLocalDateTime(seguroCreation.getEliminada(), ""));

            return seguroModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public SeguroDTO toDto(SeguroModel seguroModel) {
        try {
            SeguroDTO dto = new SeguroDTO();

            dto.setId(seguroModel.getId().toString());
            if (seguroModel.getAseguradora() != null)
                dto.setAseguradora(proveedorMapper.toDto(seguroModel.getAseguradora()));
            if (seguroModel.getVencimiento() != null)
                dto.setVencimiento(eventoMapper.toDto(seguroModel.getVencimiento()));

            if (seguroModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(seguroModel.getCreador()));
            if (seguroModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(seguroModel.getCreada(), ""));
            if (seguroModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(seguroModel.getModificador()));
            if (seguroModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(seguroModel.getModificada(), ""));
            if (seguroModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(seguroModel.getEliminador()));
            if (seguroModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(seguroModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}