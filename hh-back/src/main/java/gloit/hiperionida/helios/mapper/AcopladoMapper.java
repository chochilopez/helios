package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.mapper.dto.AcopladoDTO;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.repository.NeumaticoDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
import gloit.hiperionida.helios.repository.SeguroDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class AcopladoMapper {
    private final EventoDAO eventoDAO;
    private final ProveedorDAO proveedorDAO;
    private final SeguroDAO seguroDAO;
    private final UsuarioDAO usuarioDAO;

    public AcopladoModel toEntity(AcopladoCreation creation) {
        try {
            AcopladoModel model = new AcopladoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getInteger(creation.getCantidadNeumaticos()) != null)
                model.setCantidadNeumaticos(Helper.getInteger(creation.getCantidadNeumaticos()));
            model.setMarcaModelo(creation.getMarcaModelo());
            model.setNotas(creation.getNotas());
            if (Helper.getInteger(creation.getAnio()) != null)
                model.setAnio(Helper.getInteger(creation.getAnio()));
            model.setPatente(creation.getPatente());
            model.setPeso(creation.getPeso());

            if (Helper.getLong(creation.getCreadorId()) != null)
                model.setCreadorId(Helper.getLong(creation.getCreadorId()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getModificadorId()) != null)
                model.setModificadorId(Helper.getLong(creation.getModificadorId()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getEliminadorId()) != null)
                model.setEliminadorId(Helper.getLong(creation.getEliminadorId()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), "yyyy-MM-dd HH:mm:ss"));

            return model;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }
    
    public AcopladoDTO toDto(AcopladoModel model) {
        try {
            AcopladoDTO dto = new AcopladoDTO();

            dto.setId(model.getId().toString());
            dto.setAnio(model.getAnio().toString());
            dto.setCantidadNeumaticos(model.getCantidadNeumaticos().toString());
            dto.setMarcaModelo(model.getMarcaModelo());
            dto.setNotas(model.getNotas());
            dto.setPatente(model.getPatente());
            dto.setPeso(model.getPeso());

            if (model.getCreadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreadorId() + "."));
                dto.setCreador(usuarioModel.getNombre());
                dto.setCreadorId(model.getCreadorId().toString());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getModificadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificadorId() + "."));
                dto.setModificador(usuarioModel.getNombre());
                dto.setModificadorId(model.getModificadorId().toString());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getEliminadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminadorId() + "."));
                dto.setEliminador(usuarioModel.getNombre());
                dto.setEliminadorId(model.getEliminadorId().toString());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}