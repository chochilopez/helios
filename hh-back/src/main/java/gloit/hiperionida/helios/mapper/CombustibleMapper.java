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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final CamionDAO camionDAO;
    private final CamionMapper camionMapper;
    private final ConductorDAO conductorDAO;
    private final ConductorMapper conductorMapper;
    private final ProveedorDAO proveedorDAO;
    private final ProveedorMapper proveedorMapper;

    public CombustibleModel toEntity(CombustibleCreation combustibleCreation) {
        try {
            CombustibleModel combustibleModel = new CombustibleModel();

            if (Helper.getLong(combustibleCreation.getId()) != null)
                combustibleModel.setId(Helper.getLong(combustibleCreation.getId()));
            if (combustibleCreation.getFecha() != null && Helper.stringToLocalDateTime(combustibleCreation.getFecha(), "") != null)
                combustibleModel.setFecha(Helper.stringToLocalDateTime(combustibleCreation.getFecha(), ""));
            if (Helper.getDecimal(combustibleCreation.getKilometros()) != null)
                combustibleModel.setKilometros(Helper.getDecimal(combustibleCreation.getKilometros()));
            if (Helper.getDecimal(combustibleCreation.getLitros()) != null)
                combustibleModel.setLitros(Helper.getDecimal(combustibleCreation.getLitros()));
            if (Helper.getDecimal(combustibleCreation.getPrecio()) != null)
                combustibleModel.setPrecio(Helper.getDecimal(combustibleCreation.getPrecio()));
            combustibleModel.setNotas(combustibleCreation.getNotas());
            if (Helper.getLong(combustibleCreation.getCamion_id()) != null) {
                Optional<CamionModel> camion = camionDAO.findByIdAndEliminadaIsNull(Helper.getLong(combustibleCreation.getCamion_id()));
                camion.ifPresent(combustibleModel::setCamion);
            }
            if (Helper.getLong(combustibleCreation.getConductor_id()) != null) {
                Optional<ConductorModel> conductor = conductorDAO.findByIdAndEliminadaIsNull(Helper.getLong(combustibleCreation.getConductor_id()));
                conductor.ifPresent(combustibleModel::setConductor);
            }
            if (Helper.getLong(combustibleCreation.getProveedor_id()) != null) {
                Optional<ProveedorModel> proveedor = proveedorDAO.findByIdAndEliminadaIsNull(Helper.getLong(combustibleCreation.getProveedor_id()));
                proveedor.ifPresent(combustibleModel::setProveedor);
            }
            
            if (Helper.getLong(combustibleCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(combustibleCreation.getCreador_id()));
                usuario.ifPresent(combustibleModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(combustibleCreation.getCreada(), "") != null)
                combustibleModel.setCreada(Helper.stringToLocalDateTime(combustibleCreation.getCreada(), ""));
            if (Helper.getLong(combustibleCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(combustibleCreation.getModificador_id()));
                usuario.ifPresent(combustibleModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(combustibleCreation.getModificada(), "") != null)
                combustibleModel.setModificada(Helper.stringToLocalDateTime(combustibleCreation.getModificada(), ""));
            if (Helper.getLong(combustibleCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(combustibleCreation.getEliminador_id()));
                usuario.ifPresent(combustibleModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(combustibleCreation.getEliminada(), "") != null)
                combustibleModel.setEliminada(Helper.stringToLocalDateTime(combustibleCreation.getEliminada(), ""));

            return combustibleModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CombustibleDTO toDto(CombustibleModel combustibleModel) {
        try {
            CombustibleDTO dto = new CombustibleDTO();

            dto.setId(combustibleModel.getId().toString());
            dto.setFecha(combustibleModel.getFecha().toString());
            dto.setKilometros(combustibleModel.getKilometros().toString());
            dto.setLitros(combustibleModel.getLitros().toString());
            dto.setPrecio(combustibleModel.getPrecio().toString());
            dto.setNotas(combustibleModel.getNotas());
            dto.setCamion(camionMapper.toDto(combustibleModel.getCamion()));
            dto.setConductor(conductorMapper.toDto(combustibleModel.getConductor()));
            dto.setProveedor(proveedorMapper.toDto(combustibleModel.getProveedor()));

            if (combustibleModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(combustibleModel.getCreador()));
            if (Helper.localDateTimeToString(combustibleModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(combustibleModel.getCreada(), ""));
            if (combustibleModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(combustibleModel.getModificador()));
            if (Helper.localDateTimeToString(combustibleModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(combustibleModel.getModificada(), ""));
            if (combustibleModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(combustibleModel.getEliminador()));
            if (Helper.localDateTimeToString(combustibleModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(combustibleModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}