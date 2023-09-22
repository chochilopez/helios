package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.mapper.dto.AcopladoDTO;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.model.NeumaticoModel;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.repository.NeumaticoDAO;
import gloit.hiperionida.helios.repository.SeguroDAO;
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
public class AcopladoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final SeguroDAO seguroDAO;
    private final NeumaticoDAO neumaticoDAO;
    private final SeguroMapper seguroMapper;
    private final NeumaticoMapper neumaticoMapper;

    public AcopladoModel toEntity(AcopladoCreation acopladoCreation) {
        try {
            AcopladoModel acopladoModel = new AcopladoModel();

            if (Helper.getLong(acopladoCreation.getId()) != null)
                acopladoModel.setId(Helper.getLong(acopladoCreation.getId()));
            acopladoModel.setCantidadNeumaticos(acopladoCreation.getCantidadNeumaticos());
            acopladoModel.setMarca(acopladoCreation.getMarca());
            acopladoModel.setModelo(acopladoCreation.getModelo());
            acopladoModel.setAnio(acopladoCreation.getAnio());
            acopladoModel.setPatente(acopladoCreation.getPatente());
            acopladoModel.setPeso(acopladoCreation.getPeso());
            if (Helper.getLong(acopladoCreation.getSeguro_id()) != null) {
                Optional<SeguroModel> seguro = seguroDAO.findById(Helper.getLong(acopladoCreation.getSeguro_id()));
                if (seguro.isPresent())
                    acopladoModel.setSeguro(seguro.get());
            }
            Set<NeumaticoModel> neumaticos = new HashSet<>();
            for (String neumatico_id: acopladoCreation.getNeumaticos_id()) {
                if (Helper.getLong(neumatico_id) != null) {
                    Optional<NeumaticoModel> neumatico = neumaticoDAO.findById(Helper.getLong(acopladoCreation.getSeguro_id()));
                    if (neumatico.isPresent())
                        neumaticos.add(neumatico.get());
                }
            }
            acopladoModel.setNeumaticos(neumaticos);

            if (Helper.getLong(acopladoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(acopladoCreation.getCreador_id()));
                if (user.isPresent())
                    acopladoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(acopladoCreation.getCreada()))
                acopladoModel.setCreada(Helper.stringToLocalDateTime(acopladoCreation.getCreada(), ""));
            if (Helper.getLong(acopladoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(acopladoCreation.getModificador_id()));
                if (user.isPresent())
                    acopladoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(acopladoCreation.getModificada()))
                acopladoModel.setModificada(Helper.stringToLocalDateTime(acopladoCreation.getModificada(), ""));
            if (Helper.getLong(acopladoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(acopladoCreation.getEliminador_id()));
                if (user.isPresent())
                    acopladoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(acopladoCreation.getEliminada()))
                acopladoModel.setEliminada(Helper.stringToLocalDateTime(acopladoCreation.getEliminada(), ""));

            return acopladoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public AcopladoDTO toDto(AcopladoModel acopladoModel) {
        try {
            AcopladoDTO dto = new AcopladoDTO();

            dto.setId(acopladoModel.getId().toString());
            dto.setCantidadNeumaticos(acopladoModel.getCantidadNeumaticos());
            dto.setMarca(acopladoModel.getMarca());
            dto.setModelo(acopladoModel.getModelo());
            dto.setAnio(acopladoModel.getAnio());
            dto.setPatente(acopladoModel.getPatente());
            dto.setPeso(acopladoModel.getPeso());
            if (!acopladoModel.getNeumaticos().isEmpty()) {
                List<NeumaticoDTO> neumaticoDTOS = new ArrayList<>();
                for (NeumaticoModel neumatico:acopladoModel.getNeumaticos()) {
                    neumaticoDTOS.add(neumaticoMapper.toDto(neumatico));
                }
            }
            if (acopladoModel.getSeguro() != null)
                dto.setSeguro(seguroMapper.toDto(acopladoModel.getSeguro()));

            if (acopladoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(acopladoModel.getCreador()));
            if (acopladoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(acopladoModel.getCreada(), ""));
            if (acopladoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(acopladoModel.getModificador()));
            if (acopladoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(acopladoModel.getModificada(), ""));
            if (acopladoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(acopladoModel.getEliminador()));
            if (acopladoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(acopladoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}