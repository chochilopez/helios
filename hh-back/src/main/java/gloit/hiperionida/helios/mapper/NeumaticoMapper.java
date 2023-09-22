package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.NeumaticoCreation;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.model.NeumaticoModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.model.enums.UbicacionNeumaticoEnum;
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
public class NeumaticoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final ProveedorDAO proveedorDAO;
    private final ProveedorMapper proveedorMapper;

    public NeumaticoModel toEntity(NeumaticoCreation neumaticoCreation) {
        try {
            NeumaticoModel neumaticoModel = new NeumaticoModel();

            if (Helper.getLong(neumaticoCreation.getId()) != null)
                neumaticoModel.setId(Helper.getLong(neumaticoCreation.getId()));
            if (neumaticoCreation.getFechaCompra() != null && Helper.stringToLocalDateTime(neumaticoCreation.getFechaCompra(), "") != null)
                neumaticoModel.setFechaCompra(Helper.stringToLocalDateTime(neumaticoCreation.getFechaCompra(), ""));
            if (Helper.getDecimal(neumaticoCreation.getKmVida()) != null)
                neumaticoModel.setKmVida(Helper.getDecimal(neumaticoCreation.getKmVida()));
            if (Helper.getDecimal(neumaticoCreation.getKmRecapado()) != null)
                neumaticoModel.setKmRecapado(Helper.getDecimal(neumaticoCreation.getKmRecapado()));
            if (Helper.getDecimal(neumaticoCreation.getKmActuales()) != null)
                neumaticoModel.setKmActuales(Helper.getDecimal(neumaticoCreation.getKmActuales()));
            if (neumaticoCreation.getUbicacion() != null)
                neumaticoModel.setUbicacion(UbicacionNeumaticoEnum.valueOf(neumaticoCreation.getUbicacion()));
            if (neumaticoCreation.getEstado() != null)
                neumaticoModel.setEstado(EstadoNeumaticoEnum.valueOf(neumaticoCreation.getEstado()));
            if (Helper.getBoolean(neumaticoCreation.getBaja()) != null)
                neumaticoModel.setBaja(Helper.getBoolean(neumaticoCreation.getBaja()));
            if (Helper.getLong(neumaticoCreation.getProveedor_id()) != null) {
                Optional<ProveedorModel> proveedor = proveedorDAO.findById(Helper.getLong(neumaticoCreation.getProveedor_id()));
                proveedor.ifPresent(neumaticoModel::setProveedor);
            }

            if (Helper.getLong(neumaticoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(neumaticoCreation.getCreador_id()));
                user.ifPresent(neumaticoModel::setCreador);
            }
            if (!Helper.isEmptyString(neumaticoCreation.getCreada()))
                neumaticoModel.setCreada(Helper.stringToLocalDateTime(neumaticoCreation.getCreada(), ""));
            if (Helper.getLong(neumaticoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(neumaticoCreation.getModificador_id()));
                user.ifPresent(neumaticoModel::setModificador);
            }
            if (!Helper.isEmptyString(neumaticoCreation.getModificada()))
                neumaticoModel.setModificada(Helper.stringToLocalDateTime(neumaticoCreation.getModificada(), ""));
            if (Helper.getLong(neumaticoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(neumaticoCreation.getEliminador_id()));
                user.ifPresent(neumaticoModel::setEliminador);
            }
            if (!Helper.isEmptyString(neumaticoCreation.getEliminada()))
                neumaticoModel.setEliminada(Helper.stringToLocalDateTime(neumaticoCreation.getEliminada(), ""));

            return neumaticoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public NeumaticoDTO toDto(NeumaticoModel neumaticoModel) {
        try {
            NeumaticoDTO dto = new NeumaticoDTO();

            dto.setId(neumaticoModel.getId().toString());
            dto.setFechaCompra(neumaticoModel.getFechaCompra().toString());
            dto.setKmVida(neumaticoModel.getKmVida().toString());
            dto.setKmActuales(neumaticoModel.getKmActuales().toString());
            dto.setKmRecapado(neumaticoModel.getKmRecapado().toString());
            dto.setNumeroRemito(neumaticoModel.getNumeroRemito());
            dto.setMarca(neumaticoModel.getMarca());
            dto.setPrecioCompra(neumaticoModel.getPrecioCompra().toString());
            dto.setRecapadosMaximos(neumaticoModel.getRecapadosMaximos().toString());
            dto.setUbicacion(neumaticoModel.getUbicacion().name());
            dto.setEstado(neumaticoModel.getEstado().name());
            dto.setBaja(neumaticoModel.getBaja().toString());
            if (neumaticoModel.getProveedor() != null)
                dto.setProveedor(proveedorMapper.toDto(neumaticoModel.getProveedor()));

            if (neumaticoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(neumaticoModel.getCreador()));
            if (neumaticoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(neumaticoModel.getCreada(), ""));
            if (neumaticoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(neumaticoModel.getModificador()));
            if (neumaticoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(neumaticoModel.getModificada(), ""));
            if (neumaticoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(neumaticoModel.getEliminador()));
            if (neumaticoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(neumaticoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}