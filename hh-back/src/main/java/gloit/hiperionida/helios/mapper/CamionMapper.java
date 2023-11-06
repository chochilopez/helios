package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.mapper.dto.ServicioDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.Helper;
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
public class CamionMapper {
    private final EventoDAO eventoDAO;
    private final ProveedorDAO proveedorDAO;
    private final SeguroDAO seguroDAO;
    private final UsuarioDAO usuarioDAO;

    public CamionModel toEntity(CamionCreation creation) {
        try {
            CamionModel model = new CamionModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setNumeroChasis(model.getNumeroChasis());
            model.setNumeroMotor(creation.getNumeroMotor());
            model.setPesoArrastre(creation.getPesoArrastre());
            model.setCantidadNeumaticos(creation.getCantidadNeumaticos());
            model.setMarca(creation.getMarca());
            model.setModelo(creation.getModelo());
            model.setAnio(creation.getAnio());
            model.setPatente(creation.getPatente());
            model.setPeso(creation.getPeso());

            if (Helper.getLong(creation.getSeguro_id()) != null)
                model.setSeguro_id(Helper.getLong(creation.getSeguro_id()));

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
    
    public CamionDTO toDto(CamionModel model) {
        try {
            CamionDTO dto = new CamionDTO();

            dto.setId(model.getId().toString());
            dto.setCantidadNeumaticos(model.getCantidadNeumaticos());
            dto.setMarca(model.getMarca());
            dto.setNumeroChasis(model.getNumeroChasis());
            dto.setNumeroMotor(model.getNumeroMotor());
            dto.setPesoArrastre(model.getPesoArrastre());
            dto.setModelo(model.getModelo());
            dto.setAnio(model.getAnio());
            dto.setPatente(model.getPatente());
            dto.setPeso(model.getPeso());

            if (model.getSeguro_id() != null) {
                Optional<SeguroModel> seguroModel = seguroDAO.findByIdAndEliminadaIsNull(model.getSeguro_id());
                Optional<ProveedorModel> proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(seguroModel.get().getAseguradora_id());
                Optional<EventoModel> eventoModel = eventoDAO.findByIdAndEliminadaIsNull(seguroModel.get().getVencimiento_id());
                dto.setAseguradora(proveedorModel.get().getNombre());
                dto.setVencimiento(eventoModel.get().getFecha().toString());
            }

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