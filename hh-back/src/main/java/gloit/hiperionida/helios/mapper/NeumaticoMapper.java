package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.NeumaticoCreation;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.model.NeumaticoModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.enums.EstadoNeumaticoEnum;
import gloit.hiperionida.helios.model.enums.UbicacionNeumaticoEnum;
import gloit.hiperionida.helios.repository.AcopladoDAO;
import gloit.hiperionida.helios.repository.CamionDAO;
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
    private final ProveedorDAO proveedorDAO;
    private final UsuarioDAO usuarioDAO;

    public NeumaticoModel toEntity(NeumaticoCreation creation) {
        try {
            NeumaticoModel model = new NeumaticoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (creation.getFechaCompra() != null && Helper.stringToLocalDateTime(creation.getFechaCompra(), "") != null)
                model.setFechaCompra(Helper.stringToLocalDateTime(creation.getFechaCompra(), ""));
            if (Helper.getDecimal(creation.getKmVida()) != null)
                model.setKmVida(Helper.getDecimal(creation.getKmVida()));
            if (Helper.getDecimal(creation.getKmRecapado()) != null)
                model.setKmRecapado(Helper.getDecimal(creation.getKmRecapado()));
            if (Helper.getDecimal(creation.getKmActuales()) != null)
                model.setKmActuales(Helper.getDecimal(creation.getKmActuales()));
            model.setMarca(creation.getMarca());
            if (Helper.getDecimal(creation.getPrecioCompra()) != null)
                model.setPrecioCompra(Helper.getDecimal(creation.getPrecioCompra()));
            if (Helper.getInteger(creation.getRecapadosMaximos()) != null)
                model.setRecapadosMaximos(Helper.getInteger(creation.getRecapadosMaximos()));
            if (creation.getUbicacion() != null)
                model.setUbicacion(UbicacionNeumaticoEnum.valueOf(creation.getUbicacion()));
            if (creation.getEstado() != null)
                model.setEstado(EstadoNeumaticoEnum.valueOf(creation.getEstado()));
            if (Helper.getBoolean(creation.getBaja()) != null)
                model.setBaja(Helper.getBoolean(creation.getBaja()));

            if (Helper.getLong(creation.getAcoplado_id()) != null)
                model.setAcoplado_id(Helper.getLong(creation.getAcoplado_id()));
            if (Helper.getLong(creation.getCamion_id()) != null)
                model.setCamion_id(Helper.getLong(creation.getCamion_id()));
            if (Helper.getLong(creation.getProveedor_id()) != null)
                model.setProveedor_id(Helper.getLong(creation.getProveedor_id()));

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

    public NeumaticoDTO toDto(NeumaticoModel model) {
        try {
            NeumaticoDTO dto = new NeumaticoDTO();

            dto.setId(model.getId().toString());
            dto.setFechaCompra(model.getFechaCompra().toString());
            dto.setKmVida(model.getKmVida().toString());
            dto.setKmActuales(model.getKmActuales().toString());
            dto.setKmRecapado(model.getKmRecapado().toString());
            dto.setMarca(model.getMarca());
            dto.setPrecioCompra(model.getPrecioCompra().toString());
            dto.setRecapadosMaximos(model.getRecapadosMaximos().toString());
            dto.setUbicacion(model.getUbicacion().name());
            dto.setEstado(model.getEstado().name());
            dto.setBaja(model.getBaja().toString());

            if (model.getAcoplado_id() != null)
                dto.setAcoplado_id(model.getAcoplado_id().toString());
            if (model.getCamion_id() != null)
                dto.setCamion_id(model.getCamion_id().toString());
            if (model.getProveedor_id() != null)
                dto.setProveedor(proveedorDAO.findByIdAndEliminadaIsNull(model.getProveedor_id()).get().getNombre());

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