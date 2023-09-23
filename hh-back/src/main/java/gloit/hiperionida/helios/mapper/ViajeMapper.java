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
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final EventoDAO eventoDAO;
    private final DireccionDAO direccionDAO;
    private final DireccionMapper direccionMapper;
    private final ClienteDAO clienteDAO;
    private final CategoriaViajeMapper categoriaViajeMapper;
    private final EventoMapper eventoMapper;
    private final ClienteMapper clienteMapper;
    private final ConductorDAO conductorDAO;
    private final ConductorMapper conductorMapper;
    private final CamionDAO camionDAO;
    private final CamionMapper camionMapper;
    private final AcopladoDAO acopladoDAO;
    private final AcopladoMapper acopladoMapper;
    private final GastoDAO gastoDAO;
    private final GastoMapper gastoMapper;

    public ViajeModel toEntity(ViajeCreation viajeCreation) {
        try {
            ViajeModel viajeModel = new ViajeModel();

            if (Helper.getLong(viajeCreation.getId()) != null)
                viajeModel.setId(Helper.getLong(viajeCreation.getId()));
            viajeModel.setGuia(viajeCreation.getGuia());
            if (Helper.getLong(viajeCreation.getConductor_id()) != null) {
                Optional<ConductorModel> conductor = conductorDAO.findById(Helper.getLong(viajeCreation.getConductor_id()));
                conductor.ifPresent(viajeModel::setConductor);
            }
            if (Helper.getLong(viajeCreation.getCamion_id()) != null) {
                Optional<CamionModel> camion = camionDAO.findById(Helper.getLong(viajeCreation.getCamion_id()));
                camion.ifPresent(viajeModel::setCamion);
            }
            if (Helper.getLong(viajeCreation.getAcoplado_id()) != null) {
                Optional<AcopladoModel> acoplado = acopladoDAO.findById(Helper.getLong(viajeCreation.getAcoplado_id()));
                acoplado.ifPresent(viajeModel::setAcoplado);
            }
            Set<GastoModel> gastos = new HashSet<>();
            for (String gasto_id: viajeCreation.getGastos_id()) {
                if (Helper.getLong(gasto_id) != null) {
                    Optional<GastoModel> gasto = gastoDAO.findById(Helper.getLong(gasto_id));
                    gasto.ifPresent(gastos::add);
                }
            }
            viajeModel.setGastos(gastos);
            if (Helper.getDecimal(viajeCreation.getCantidadTransportada()) != null)
                viajeModel.setCantidadTransportada(Helper.getDecimal(viajeCreation.getCantidadTransportada()));
            if (Helper.getDecimal(viajeCreation.getValorKm()) != null)
                viajeModel.setValorKm(Helper.getDecimal(viajeCreation.getValorKm()));
            if (Helper.getDecimal(viajeCreation.getKgNeto()) != null)
                viajeModel.setKgNeto(Helper.getDecimal(viajeCreation.getKgNeto()));
            if (Helper.getDecimal(viajeCreation.getKmCargado()) != null)
                viajeModel.setKmCargado(Helper.getDecimal(viajeCreation.getKmCargado()));
            if (Helper.getDecimal(viajeCreation.getKmVacio()) != null)
                viajeModel.setKmVacio(Helper.getDecimal(viajeCreation.getKmVacio()));
            viajeModel.setNotas(viajeCreation.getNotas());
            if (Helper.getLong(viajeCreation.getCategoriaViaje_id()) != null) {
                Optional<CategoriaViajeModel> categoriaViaje = categoriaViajeDAO.findById(Helper.getLong(viajeCreation.getCategoriaViaje_id()));
                categoriaViaje.ifPresent(viajeModel::setCategoriaViaje);
            }
            if (Helper.getLong(viajeCreation.getOrigen_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findById(Helper.getLong(viajeCreation.getOrigen_id()));
                direccion.ifPresent(viajeModel::setOrigen);
            }
            if (Helper.getLong(viajeCreation.getCarga_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findById(Helper.getLong(viajeCreation.getCarga_id()));
                direccion.ifPresent(viajeModel::setCarga);
            }
            if (Helper.getLong(viajeCreation.getDestino_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findById(Helper.getLong(viajeCreation.getDestino_id()));
                direccion.ifPresent(viajeModel::setDestino);
            }
            if (Helper.getLong(viajeCreation.getVendedor_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findById(Helper.getLong(viajeCreation.getVendedor_id()));
                cliente.ifPresent(viajeModel::setVendedor);
            }
            if (Helper.getLong(viajeCreation.getIntermediario_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findById(Helper.getLong(viajeCreation.getIntermediario_id()));
                cliente.ifPresent(viajeModel::setIntermediario);
            }
            if (Helper.getLong(viajeCreation.getComprador_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findById(Helper.getLong(viajeCreation.getComprador_id()));
                cliente.ifPresent(viajeModel::setComprador);
            }
            if (Helper.getLong(viajeCreation.getFecha_id()) != null) {
                Optional<EventoModel> evento = eventoDAO.findById(Helper.getLong(viajeCreation.getFecha_id()));
                evento.ifPresent(viajeModel::setFecha);
            }

            if (Helper.getLong(viajeCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(viajeCreation.getCreador_id()));
                user.ifPresent(viajeModel::setCreador);
            }
            if (!Helper.isEmptyString(viajeCreation.getCreada()))
                viajeModel.setCreada(Helper.stringToLocalDateTime(viajeCreation.getCreada(), ""));
            if (Helper.getLong(viajeCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(viajeCreation.getModificador_id()));
                user.ifPresent(viajeModel::setModificador);
            }
            if (!Helper.isEmptyString(viajeCreation.getModificada()))
                viajeModel.setModificada(Helper.stringToLocalDateTime(viajeCreation.getModificada(), ""));
            if (Helper.getLong(viajeCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(viajeCreation.getEliminador_id()));
                user.ifPresent(viajeModel::setEliminador);
            }
            if (!Helper.isEmptyString(viajeCreation.getEliminada()))
                viajeModel.setEliminada(Helper.stringToLocalDateTime(viajeCreation.getEliminada(), ""));

            return viajeModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ViajeDTO toDto(ViajeModel viajeModel) {
        try {
            ViajeDTO dto = new ViajeDTO();

            dto.setId(viajeModel.getId().toString());
            dto.setGuia(viajeModel.getGuia());
            if (viajeModel.getConductor() != null)
                dto.setConductor(conductorMapper.toDto(viajeModel.getConductor()));
            if (viajeModel.getCamion() != null)
                dto.setCamion(camionMapper.toDto(viajeModel.getCamion()));
            if (viajeModel.getAcoplado() != null)
                dto.setAcoplado(acopladoMapper.toDto(viajeModel.getAcoplado()));
            if (!viajeModel.getGastos().isEmpty()) {
                List<GastoDTO> gastoDTOS = new ArrayList<>();
                for (GastoModel gasto:viajeModel.getGastos()) {
                    gastoDTOS.add(gastoMapper.toDto(gasto));
                }
                dto.setGastos(gastoDTOS);
            }
            DecimalFormat format = new DecimalFormat("0.#");
            dto.setCantidadTransportada(format.format(viajeModel.getCantidadTransportada()).toString());
            dto.setValorKm(viajeModel.getValorKm().toString());
            dto.setKgNeto(viajeModel.getKgNeto().toString());
            dto.setKmCargado(viajeModel.getKmCargado().toString());
            dto.setKmVacio(viajeModel.getKmVacio().toString());
            dto.setNotas(viajeModel.getNotas());
            if (viajeModel.getCategoriaViaje() != null)
                dto.setCategoriaViaje(categoriaViajeMapper.toDto(viajeModel.getCategoriaViaje()));
            if (viajeModel.getOrigen() != null)
                dto.setOrigen(direccionMapper.toDto(viajeModel.getOrigen()));
            if (viajeModel.getCarga() != null)
                dto.setCarga(direccionMapper.toDto(viajeModel.getCarga()));
            if (viajeModel.getDestino() != null)
                dto.setDestino(direccionMapper.toDto(viajeModel.getDestino()));
            if (viajeModel.getVendedor() != null)
                dto.setVendedor(clienteMapper.toDto(viajeModel.getVendedor()));
            if (viajeModel.getIntermediario() != null)
                dto.setIntermediario(clienteMapper.toDto(viajeModel.getIntermediario()));
            if (viajeModel.getComprador() != null)
                dto.setComprador(clienteMapper.toDto(viajeModel.getComprador()));
            if (viajeModel.getFecha() != null)
                dto.setFecha(eventoMapper.toDto(viajeModel.getFecha()));

            if (viajeModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(viajeModel.getCreador()));
            if (viajeModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(viajeModel.getCreada(), ""));
            if (viajeModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(viajeModel.getModificador()));
            if (viajeModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(viajeModel.getModificada(), ""));
            if (viajeModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(viajeModel.getEliminador()));
            if (viajeModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(viajeModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}