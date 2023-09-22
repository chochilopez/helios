package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.mapper.dto.ServicioDTO;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.model.NeumaticoModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.model.ServicioModel;
import gloit.hiperionida.helios.repository.NeumaticoDAO;
import gloit.hiperionida.helios.repository.SeguroDAO;
import gloit.hiperionida.helios.repository.ServicioDAO;
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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final SeguroDAO seguroDAO;
    private final SeguroMapper seguroMapper;
    private final NeumaticoDAO neumaticoDAO;
    private final NeumaticoMapper neumaticoMapper;
    private final ServicioDAO servicioDAO;
    private final ServicioMapper servicioMapper;

    public CamionModel toEntity(CamionCreation camionCreation) {
        try {
            CamionModel camionModel = new CamionModel();

            if (Helper.getLong(camionCreation.getId()) != null)
                camionModel.setId(Helper.getLong(camionCreation.getId()));
            camionModel.setNumeroChasis(camionModel.getNumeroChasis());
            camionModel.setNumeroMotor(camionCreation.getNumeroMotor());
            camionModel.setPesoArrastre(camionCreation.getPesoArrastre());
            camionModel.setCantidadNeumaticos(camionCreation.getCantidadNeumaticos());
            camionModel.setMarca(camionCreation.getMarca());
            camionModel.setModelo(camionCreation.getModelo());
            camionModel.setAnio(camionCreation.getAnio());
            camionModel.setPatente(camionCreation.getPatente());
            camionModel.setPeso(camionCreation.getPeso());
            if (Helper.getLong(camionCreation.getSeguro_id()) != null) {
                Optional<SeguroModel> seguro = seguroDAO.findById(Helper.getLong(camionCreation.getSeguro_id()));
                if (seguro.isPresent())
                    camionModel.setSeguro(seguro.get());
            }
            Set<NeumaticoModel> neumaticos = new HashSet<>();
            for (String neumatico_id: camionCreation.getNeumaticos_id()) {
                if (Helper.getLong(neumatico_id) != null) {
                    Optional<NeumaticoModel> neumatico = neumaticoDAO.findById(Helper.getLong(neumatico_id));
                    if (neumatico.isPresent())
                        neumaticos.add(neumatico.get());
                }
            }
            camionModel.setNeumaticos(neumaticos);
            Set<ServicioModel> servicios = new HashSet<>();
            for (String servicio_id: camionCreation.getServicios_id()) {
                if (Helper.getLong(servicio_id) != null) {
                    Optional<ServicioModel> servicio = servicioDAO.findById(Helper.getLong(servicio_id));
                    if (servicio.isPresent())
                        servicios.add(servicio.get());
                }
            }
            camionModel.setNeumaticos(neumaticos);

            if (Helper.getLong(camionCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(camionCreation.getCreador_id()));
                if (user.isPresent())
                    camionModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(camionCreation.getCreada()))
                camionModel.setCreada(Helper.stringToLocalDateTime(camionCreation.getCreada(), ""));
            if (Helper.getLong(camionCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(camionCreation.getModificador_id()));
                if (user.isPresent())
                    camionModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(camionCreation.getModificada()))
                camionModel.setModificada(Helper.stringToLocalDateTime(camionCreation.getModificada(), ""));
            if (Helper.getLong(camionCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(camionCreation.getEliminador_id()));
                if (user.isPresent())
                    camionModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(camionCreation.getEliminada()))
                camionModel.setEliminada(Helper.stringToLocalDateTime(camionCreation.getEliminada(), ""));

            return camionModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CamionDTO toDto(CamionModel camionModel) {
        try {
            CamionDTO dto = new CamionDTO();

            dto.setId(camionModel.getId().toString());
            dto.setCantidadNeumaticos(camionModel.getCantidadNeumaticos());
            dto.setMarca(camionModel.getMarca());
            dto.setNumeroChasis(camionModel.getNumeroChasis());
            dto.setNumeroMotor(camionModel.getNumeroMotor());
            dto.setPesoArrastre(camionModel.getPesoArrastre());
            dto.setModelo(camionModel.getModelo());
            dto.setAnio(camionModel.getAnio());
            dto.setPatente(camionModel.getPatente());
            dto.setPeso(camionModel.getPeso());
            if (!camionModel.getNeumaticos().isEmpty()) {
                List<NeumaticoDTO> neumaticoDTOS = new ArrayList<>();
                for (NeumaticoModel neumatico:camionModel.getNeumaticos()) {
                    neumaticoDTOS.add(neumaticoMapper.toDto(neumatico));
                }
            }
            if (!camionModel.getServicios().isEmpty()) {
                List<ServicioDTO> servicioDTOS = new ArrayList<>();
                for (ServicioModel servicio:camionModel.getServicios()) {
                    servicioDTOS.add(servicioMapper.toDto(servicio));
                }
            }
            if (camionModel.getSeguro() != null)
                dto.setSeguro(seguroMapper.toDto(camionModel.getSeguro()));

            if (camionModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(camionModel.getCreador()));
            if (camionModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(camionModel.getCreada(), ""));
            if (camionModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(camionModel.getModificador()));
            if (camionModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(camionModel.getModificada(), ""));
            if (camionModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(camionModel.getEliminador()));
            if (camionModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(camionModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}