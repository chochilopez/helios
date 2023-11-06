package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViajeMapper {
    private final AcopladoDAO acopladoDAO;
    private final CamionDAO camionDAO;
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final ClienteDAO clienteDAO;
    private final ConductorDAO conductorDAO;
    private final DireccionDAO direccionDAO;
    private final EventoDAO eventoDAO;
    private final GastoDAO gastoDAO;
    private final UsuarioDAO usuarioDAO;


    public ViajeModel toEntity(ViajeCreation creation) {
        try {
            ViajeModel model = new ViajeModel();

            if (Helper.getDecimal(creation.getCantidadTransportada()) != null)
                model.setCantidadTransportada(Helper.getDecimal(creation.getCantidadTransportada()));
            if (Helper.getDecimal(creation.getValorKm()) != null)
                model.setValorKm(Helper.getDecimal(creation.getValorKm()));
            if (Helper.getDecimal(creation.getNeto()) != null)
                model.setNeto(Helper.getDecimal(creation.getNeto()));
            if (Helper.getDecimal(creation.getKmCargado()) != null)
                model.setKmCargado(Helper.getDecimal(creation.getKmCargado()));
            if (Helper.getDecimal(creation.getKmVacio()) != null)
                model.setKmVacio(Helper.getDecimal(creation.getKmVacio()));
            model.setNotas(creation.getNotas());
            model.setGuia(creation.getGuia());

            if (Helper.getLong(creation.getAcopladoId()) != null)
                model.setAcopladoId(Helper.getLong(creation.getAcopladoId()));
            if (Helper.getLong(creation.getCamionId()) != null)
                model.setCamionId(Helper.getLong(creation.getCamionId()));
            if (Helper.getLong(creation.getCargaId()) != null)
                model.setCargaId(Helper.getLong(creation.getCargaId()));
            if (Helper.getLong(creation.getCategoriaViajeId()) != null)
                model.setCategoriaViajeId(Helper.getLong(creation.getCategoriaViajeId()));
            if (Helper.getLong(creation.getCompradorId()) != null)
                model.setCompradorId(Helper.getLong(creation.getCompradorId()));
            if (Helper.getLong(creation.getConductorId()) != null)
                model.setConductorId(Helper.getLong(creation.getConductorId()));
            if (Helper.getLong(creation.getDestinoId()) != null)
                model.setDestinoId(Helper.getLong(creation.getDestinoId()));
            if (Helper.getLong(creation.getFechaId()) != null)
                model.setFechaId(Helper.getLong(creation.getFechaId()));
            if (Helper.getLong(creation.getOrigenId()) != null)
                model.setOrigenId(Helper.getLong(creation.getOrigenId()));
            if (Helper.getLong(creation.getVendedorId()) != null)
                model.setVendedorId(Helper.getLong(creation.getVendedorId()));

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

    public ViajeDTO toDto(ViajeModel model) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            ViajeDTO dto = new ViajeDTO();

            dto.setId(model.getId().toString());
            dto.setGuia(model.getGuia());
            dto.setNotas(model.getNotas());
            if (model.getNeto() != null)
                dto.setNeto(model.getNeto().toString());
            if (model.getKmCargado() != null)
                dto.setKmCargado(model.getKmCargado().toString());
            if (model.getKmVacio() != null)
                dto.setKmVacio(model.getKmVacio().toString());
            if (model.getCantidadTransportada() != null)
                dto.setCantidadTransportada(format.format(model.getCantidadTransportada()).toString());
            if (model.getValorKm() != null)
                dto.setValorKm(format.format(model.getValorKm()).toString());

            if (model.getAcopladoId() != null) {
                Optional<AcopladoModel> acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(model.getAcopladoId());
                dto.setAcoplado(acopladoModel.get().getMarca() + " - " + acopladoModel.get().getModelo());
            }
            if (model.getCamionId() != null) {
                Optional<CamionModel> camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getCamionId());
                dto.setCamion(camionModel.get().getMarca() + " - " + camionModel.get().getModelo());
            }
            if (model.getCargaId() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getCargaId());
                dto.setCarga(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getCategoriaViajeId() != null)
                dto.setCategoriaViaje(categoriaViajeDAO.findByIdAndEliminadaIsNull(model.getCategoriaViajeId()).get().getCategoria());
            if (model.getCompradorId() != null)
                dto.setComprador(clienteDAO.findByIdAndEliminadaIsNull(model.getCompradorId()).get().getNombre());
            if (model.getConductorId() != null)
                dto.setConductor(conductorDAO.findByIdAndEliminadaIsNull(model.getConductorId()).get().getNombre());
            if (model.getDestinoId() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getDestinoId());
                dto.setDestino(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getFechaId() != null)
                dto.setFecha(eventoDAO.findByIdAndEliminadaIsNull(model.getFechaId()).get().getFecha().toString());
            if (model.getIntermediarioId() != null)
                dto.setIntermediario(clienteDAO.findByIdAndEliminadaIsNull(model.getIntermediarioId()).get().getNombre());
            if (model.getOrigenId() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getOrigenId());
                dto.setOrigen(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getVendedorId() != null)
                dto.setVendedor(clienteDAO.findByIdAndEliminadaIsNull(model.getVendedorId()).get().getNombre());

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