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
            if (Helper.getInteger(creation.getAnio()) != null)
                model.setAnio(Helper.getInteger(creation.getAnio()));
            model.setPatente(creation.getPatente());
            model.setPeso(creation.getPeso());
            if (Helper.getLong(creation.getSeguroId()) != null)
                model.setSeguroId(Helper.getLong(creation.getSeguroId()));

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
    
    public AcopladoDTO toDto(AcopladoModel model) {
        try {
            AcopladoDTO dto = new AcopladoDTO();

            dto.setId(model.getId().toString());
            dto.setCantidadNeumaticos(model.getCantidadNeumaticos().toString());
            dto.setMarcaModelo(model.getMarcaModelo());
            dto.setAnio(model.getAnio().toString());
            dto.setPatente(model.getPatente());
            dto.setPeso(model.getPeso());
            if (model.getSeguroId() != null) {
                SeguroModel seguroModel = seguroDAO.findByIdAndEliminadaIsNull(model.getSeguroId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el seguro con id: " + model.getSeguroId()));
                ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(seguroModel.getAseguradoraId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el asegurador con id: " + seguroModel.getAseguradoraId()));
                EventoModel eventoModel = eventoDAO.findByIdAndEliminadaIsNull(seguroModel.getVencimientoId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el vencimiento con id: " + seguroModel.getVencimientoId()));
                dto.setAseguradora(proveedorModel.getNombre());
                dto.setVencimiento(eventoModel.getFecha().toString());
            }

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