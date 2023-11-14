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
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
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

            if (Helper.getLong(creation.getAcopladoId()) != null)
                model.setAcopladoId(Helper.getLong(creation.getAcopladoId()));
            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            if (Helper.getLong(creation.getProveedorId()) != null)
                model.setProveedorId(Helper.getLong(creation.getProveedorId()));

            if (Helper.getLong(creation.getCreadorId()) != null)
                model.setCreadorId(Helper.getLong(creation.getCreadorId()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), ""));
            if (Helper.getLong(creation.getModificadorId()) != null)
                model.setModificadorId(Helper.getLong(creation.getModificadorId()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), ""));
            if (Helper.getLong(creation.getEliminadorId()) != null)
                model.setEliminadorId(Helper.getLong(creation.getEliminadorId()));
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

            if (model.getAcopladoId() != null)
                dto.setAcopladoId(model.getAcopladoId().toString());
            if (model.getCamionId() != null)
                dto.setCamionId(model.getCamionId().toString());
            if (model.getProveedorId() != null) {
                ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(model.getProveedorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el proveedor con id: " + model.getProveedorId() + "."));
                dto.setProveedor(proveedorModel.getNombre());
            }

            if (model.getCreadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreadorId() + "."));
                dto.setCreador(usuarioModel.getNombre());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().toString());
            if (model.getModificadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificadorId() + "."));
                dto.setModificador(usuarioModel.getNombre());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().toString());
            if (model.getEliminadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminadorId() + "."));
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