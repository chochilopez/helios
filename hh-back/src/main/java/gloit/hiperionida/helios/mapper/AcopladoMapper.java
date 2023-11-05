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
    
    /*
        private String id;
    private String seguro_id;
     */

    public AcopladoModel toEntity(AcopladoCreation creation) {
        try {
            AcopladoModel model = new AcopladoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setCantidadNeumaticos(creation.getCantidadNeumaticos());
            model.setMarca(creation.getMarca());
            model.setModelo(creation.getModelo());
            model.setAnio(creation.getAnio());
            model.setPatente(creation.getPatente());
            model.setPeso(creation.getPeso());
            if (Helper.getLong(creation.getSeguro_id()) != null) {
                Optional<SeguroModel> seguro = seguroDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getSeguro_id()));
                seguro.ifPresent(model::setSeguro);
            }
            Set<NeumaticoModel> neumaticos = new HashSet<>();
            if (creation.getNeumaticos_id() != null) {
                for (String neumatico_id : creation.getNeumaticos_id()) {
                    if (Helper.getLong(neumatico_id) != null) {
                        Optional<NeumaticoModel> neumatico = neumaticoDAO.findByIdAndEliminadaIsNull(Helper.getLong(neumatico_id));
                        neumatico.ifPresent(neumaticos::add);
                    }
                }
            }
            model.setNeumaticos(neumaticos);

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
    private String seguro_id;
     */

    public AcopladoDTO toDto(AcopladoModel model) {
        try {
            AcopladoDTO dto = new AcopladoDTO();

            dto.setId(model.getId().toString());
            dto.setCantidadNeumaticos(model.getCantidadNeumaticos());
            dto.setMarca(model.getMarca());
            dto.setModelo(model.getModelo());
            dto.setAnio(model.getAnio());
            dto.setPatente(model.getPatente());
            dto.setPeso(model.getPeso());
            if (!model.getNeumaticos().isEmpty()) {
                List<NeumaticoDTO> neumaticoDTOS = new ArrayList<>();
                for (NeumaticoModel neumatico:model.getNeumaticos()) {
                    neumaticoDTOS.add(neumaticoMapper.toDto(neumatico));
                }
                dto.setNeumaticos(neumaticoDTOS);
            }
            if (model.getSeguro() != null)
                dto.setSeguro(seguroMapper.toDto(model.getSeguro()));

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