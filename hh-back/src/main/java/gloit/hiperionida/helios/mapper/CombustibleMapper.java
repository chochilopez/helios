package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.mapper.dto.CombustibleDTO;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.CajaDAO;
import gloit.hiperionida.helios.repository.CamionDAO;
import gloit.hiperionida.helios.repository.ConductorDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
@Slf4j
public class CombustibleMapper {
    private final CajaDAO cajaDAO;
    private final CamionDAO camionDAO;
    private final ConductorDAO conductorDAO;
    private final ProveedorDAO proveedorDAO;
    private final UsuarioDAO usuarioDAO;

    public CombustibleModel toEntity(CombustibleCreation creation) {
        try {
            CombustibleModel model = new CombustibleModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (Helper.getLong(creation.getCajaId()) != null)
                model.setCajaId(Helper.getLong(creation.getCajaId()));
            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            if (Helper.getLong(creation.getConductorId()) != null)
                model.setConductorId(Helper.getLong(creation.getConductorId()));
            if (creation.getFecha() != null && Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime("00:00:00 " + creation.getFecha(), ""));
            if (Helper.getDecimal(creation.getKilometros()) != null)
                model.setKilometros(Helper.getDecimal(creation.getKilometros()));
            if (Helper.getDecimal(creation.getLitros()) != null)
                model.setLitros(Helper.getDecimal(creation.getLitros()));
            model.setNotas(creation.getNotas());
            if (Helper.getDecimal(creation.getPrecio()) != null)
                model.setPrecio(Helper.getNDecimal(Helper.getDecimal(creation.getPrecio()), 2));
            if (Helper.getLong(creation.getProveedorId()) != null)
                model.setProveedorId(Helper.getLong(creation.getProveedorId()));

            if (Helper.getLong(creation.getCreadorId()) != null)
                model.setCreadorId(Helper.getLong(creation.getCreadorId()));
            if (!Helper.isEmptyString(creation.getCreada()))
                model.setCreada(Helper.stringToLocalDateTime(creation.getCreada(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getModificadorId()) != null)
                model.setModificadorId(Helper.getLong(creation.getModificadorId()));
            if (!Helper.isEmptyString(creation.getModificada()))
                model.setModificada(Helper.stringToLocalDateTime(creation.getModificada(), "yyyy-MM-dd HH:mm:ss"));
            if (Helper.getLong(creation.getEliminadorId()) != null)
                model.setEliminadorId(Helper.getLong(creation.getEliminadorId()));
            if (!Helper.isEmptyString(creation.getEliminada()))
                model.setEliminada(Helper.stringToLocalDateTime(creation.getEliminada(), "yyyy-MM-dd HH:mm:ss"));

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
            if (model.getCajaId() != null) {
                CajaModel cajaModel = cajaDAO.findByIdAndEliminadaIsNull(model.getCajaId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el Caja con id: " + model.getCajaId() + "."));
                dto.setCamion(cajaModel.getCaja());
                dto.setCajaId(model.getCajaId().toString());
            }
            if (model.getCamionId() != null) {
                CamionModel camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getCamionId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el camión con id: " + model.getCamionId() + "."));
                dto.setCamion(camionModel.getMarcaModelo());
                dto.setCamionId(model.getCamionId().toString());
            }
            if (model.getConductorId() != null) {
                ConductorModel conductorModel = conductorDAO.findByIdAndEliminadaIsNull(model.getConductorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el conductor con id: " + model.getConductorId() + "."));
                dto.setConductor(conductorModel.getNombre());
                dto.setConductor(model.getConductorId().toString());
            }
            if (model.getFecha() != null)
                dto.setFecha(model.getFecha().toString());
            if (model.getKilometros() != null)
                dto.setKilometros(model.getKilometros().toString());
            if (model.getLitros() != null)
                dto.setLitros(model.getLitros().toString());
            if (model.getPrecio() != null)
                dto.setPrecio(model.getPrecio().toString());
            dto.setNotas(model.getNotas());
            if (model.getProveedorId() != null) {
                ProveedorModel proveedorModel = proveedorDAO.findByIdAndEliminadaIsNull(model.getProveedorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el proveedor con id: " + model.getProveedorId() + "."));
                dto.setProveedor(proveedorModel.getNombre());
                dto.setProveedorId(model.getProveedorId().toString());
            }

            if (model.getCreadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getCreadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el creador con id: " + model.getCreadorId() + "."));
                dto.setCreador(usuarioModel.getNombre());
                dto.setCreadorId(model.getCreadorId().toString());
            }
            if (model.getCreada() != null)
                dto.setCreada(model.getCreada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getModificadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getModificadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el modificador con id: " + model.getModificadorId() + "."));
                dto.setModificador(usuarioModel.getNombre());
                dto.setModificadorId(model.getModificadorId().toString());
            }
            if (model.getModificada() != null)
                dto.setModificada(model.getModificada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            if (model.getEliminadorId() != null) {
                UsuarioModel usuarioModel = usuarioDAO.findByIdAndEliminadaIsNull(model.getEliminadorId()).orElseThrow(() -> new DatosInexistentesException("No se encontró el eliminador con id: " + model.getEliminadorId() + "."));
                dto.setEliminador(usuarioModel.getNombre());
                dto.setEliminadorId(model.getEliminadorId().toString());
            }
            if (model.getEliminada() != null)
                dto.setEliminada(model.getEliminada().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}