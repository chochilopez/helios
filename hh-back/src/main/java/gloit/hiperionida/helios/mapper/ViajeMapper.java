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

            if (Helper.getLong(creation.getAcoplado_id()) != null)
                model.setAcoplado_id(Helper.getLong(creation.getAcoplado_id()));
            if (Helper.getLong(creation.getCamion_id()) != null)
                model.setCamion_id(Helper.getLong(creation.getCamion_id()));
            if (Helper.getLong(creation.getCarga_id()) != null)
                model.setCarga_id(Helper.getLong(creation.getCarga_id()));
            if (Helper.getLong(creation.getCategoriaViaje_id()) != null)
                model.setCategoriaViaje_id(Helper.getLong(creation.getCategoriaViaje_id()));
            if (Helper.getLong(creation.getComprador_id()) != null)
                model.setComprador_id(Helper.getLong(creation.getComprador_id()));
            if (Helper.getLong(creation.getConductor_id()) != null)
                model.setConductor_id(Helper.getLong(creation.getConductor_id()));
            if (Helper.getLong(creation.getDestino_id()) != null)
                model.setDestino_id(Helper.getLong(creation.getDestino_id()));
            if (Helper.getLong(creation.getFecha_id()) != null)
                model.setFecha_id(Helper.getLong(creation.getFecha_id()));
            if (Helper.getLong(creation.getOrigen_id()) != null)
                model.setOrigen_id(Helper.getLong(creation.getOrigen_id()));
            if (Helper.getLong(creation.getVendedor_id()) != null)
                model.setVendedor_id(Helper.getLong(creation.getVendedor_id()));

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

            if (model.getAcoplado_id() != null) {
                Optional<AcopladoModel> acopladoModel = acopladoDAO.findByIdAndEliminadaIsNull(model.getAcoplado_id());
                dto.setAcoplado(acopladoModel.get().getMarca() + " - " + acopladoModel.get().getModelo());
            }
            if (model.getCamion_id() != null) {
                Optional<CamionModel> camionModel = camionDAO.findByIdAndEliminadaIsNull(model.getCamion_id());
                dto.setCamion(camionModel.get().getMarca() + " - " + camionModel.get().getModelo());
            }
            if (model.getCarga_id() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getCarga_id());
                dto.setCarga(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getCategoriaViaje_id() != null)
                dto.setCategoriaViaje(categoriaViajeDAO.findByIdAndEliminadaIsNull(model.getCategoriaViaje_id()).get().getCategoria());
            if (model.getComprador_id() != null)
                dto.setComprador(clienteDAO.findByIdAndEliminadaIsNull(model.getComprador_id()).get().getNombre());
            if (model.getConductor_id() != null)
                dto.setConductor(conductorDAO.findByIdAndEliminadaIsNull(model.getConductor_id()).get().getNombre());
            if (model.getDestino_id() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getDestino_id());
                dto.setDestino(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getFecha_id() != null)
                dto.setFecha(eventoDAO.findByIdAndEliminadaIsNull(model.getFecha_id()).get().getFecha().toString());
            if (model.getIntermediario_id() != null)
                dto.setIntermediario(clienteDAO.findByIdAndEliminadaIsNull(model.getIntermediario_id()).get().getNombre());
            if (model.getOrigen_id() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getOrigen_id());
                dto.setOrigen(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getVendedor_id() != null)
                dto.setVendedor(clienteDAO.findByIdAndEliminadaIsNull(model.getVendedor_id()).get().getNombre());

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