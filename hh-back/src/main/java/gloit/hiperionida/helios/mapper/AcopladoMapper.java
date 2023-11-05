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
                Optional<SeguroModel> seguro = seguroDAO.findByIdAndEliminadaIsNull(Helper.getLong(acopladoCreation.getSeguro_id()));
                seguro.ifPresent(acopladoModel::setSeguro);
            }
            Set<NeumaticoModel> neumaticos = new HashSet<>();
            if (acopladoCreation.getNeumaticos_id() != null) {
                for (String neumatico_id : acopladoCreation.getNeumaticos_id()) {
                    if (Helper.getLong(neumatico_id) != null) {
                        Optional<NeumaticoModel> neumatico = neumaticoDAO.findByIdAndEliminadaIsNull(Helper.getLong(neumatico_id));
                        neumatico.ifPresent(neumaticos::add);
                    }
                }
            }
            acopladoModel.setNeumaticos(neumaticos);

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
                dto.setNeumaticos(neumaticoDTOS);
            }
            if (acopladoModel.getSeguro() != null)
                dto.setSeguro(seguroMapper.toDto(acopladoModel.getSeguro()));

            if (acopladoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(acopladoModel.getCreador()));
            if (Helper.localDateTimeToString(acopladoModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(acopladoModel.getCreada(), ""));
            if (acopladoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(acopladoModel.getModificador()));
            if (Helper.localDateTimeToString(acopladoModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(acopladoModel.getModificada(), ""));
            if (acopladoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(acopladoModel.getEliminador()));
            if (Helper.localDateTimeToString(acopladoModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(acopladoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}