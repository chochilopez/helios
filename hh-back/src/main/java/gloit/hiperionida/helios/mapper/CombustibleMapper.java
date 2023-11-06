package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;import gloit.hiperionida.helios.mapper.dto.CombustibleDTO;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.repository.ConductorDAO;
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
public class CombustibleMapper {
    private final CamionDAO camionDAO;
    private final ConductorDAO conductorDAO;
    private final ProveedorDAO proveedorDAO;
    private final UsuarioDAO usuarioDAO;

    public CombustibleModel toEntity(CombustibleCreation creation) {
        try {
            CombustibleModel model = new CombustibleModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (creation.getFecha() != null && Helper.stringToLocalDateTime(creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime(creation.getFecha(), ""));
            if (Helper.getDecimal(creation.getKilometros()) != null)
                model.setKilometros(Helper.getDecimal(creation.getKilometros()));
            if (Helper.getDecimal(creation.getLitros()) != null)
                model.setLitros(Helper.getDecimal(creation.getLitros()));
            if (Helper.getDecimal(creation.getPrecio()) != null)
                model.setPrecio(Helper.getDecimal(creation.getPrecio()));
            model.setNotas(creation.getNotas());

            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            if (Helper.getLong(creation.getConductorId()) != null)
                model.setConductorId(Helper.getLong(creation.getConductorId()));
            if (Helper.getLong(creation.getProveedorId()) != null)
                model.setProveedorId(Helper.getLong(creation.getProveedorId()));

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

    public CombustibleDTO toDto(CombustibleModel model) {
        try {
            CombustibleDTO dto = new CombustibleDTO();

            dto.setId(model.getId().toString());
            dto.setFecha(model.getFecha().toString());
            dto.setKilometros(model.getKilometros().toString());
            dto.setLitros(model.getLitros().toString());
            dto.setPrecio(model.getPrecio().toString());
            dto.setNotas(model.getNotas());

            if (model.getCamionId() != null) {
                Optional<CamionModel> camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getId());
                dto.setCamion(camionModel.get().getMarca() + " - " + camionModel.get().getModelo());
            }
            if (model.getConductorId() != null)
                dto.setConductor(conductorDAO.findByIdAndEliminadaIsNull(model.getConductorId()).get().getNombre());
            if (model.getProveedorId() != null)
                dto.setProveedor(proveedorDAO.findByIdAndEliminadaIsNull(model.getProveedorId()).get().getNombre());

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