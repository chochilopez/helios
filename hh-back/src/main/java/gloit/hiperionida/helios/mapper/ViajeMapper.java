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
    /*
        private String id;
    private String guia;
    private String conductor_id;
    private String camion_id;
    private String acoplado_id;
     */

    public ViajeModel toEntity(ViajeCreation creation) {
        try {
            ViajeModel model = new ViajeModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            model.setGuia(creation.getGuia());
            if (Helper.getLong(creation.getConductor_id()) != null) {
                Optional<ConductorModel> conductor = conductorDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getConductor_id()));
                conductor.ifPresent(model::setConductor);
            }
            if (Helper.getLong(creation.getCamion_id()) != null) {
                Optional<CamionModel> camion = camionDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCamion_id()));
                camion.ifPresent(model::setCamion);
            }
            if (Helper.getLong(creation.getAcoplado_id()) != null) {
                Optional<AcopladoModel> acoplado = acopladoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getAcoplado_id()));
                acoplado.ifPresent(model::setAcoplado);
            }
            Set<GastoModel> gastos = new HashSet<>();
            for (String gasto_id: creation.getGastos_id()) {
                if (Helper.getLong(gasto_id) != null) {
                    Optional<GastoModel> gasto = gastoDAO.findByIdAndEliminadaIsNull(Helper.getLong(gasto_id));
                    gasto.ifPresent(gastos::add);
                }
            }
            model.setGastos(gastos);
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
            if (Helper.getLong(creation.getCategoriaViaje_id()) != null) {
                Optional<CategoriaViajeModel> categoriaViaje = categoriaViajeDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCategoriaViaje_id()));
                categoriaViaje.ifPresent(model::setCategoriaViaje);
            }
            if (Helper.getLong(creation.getOrigen_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getOrigen_id()));
                direccion.ifPresent(model::setOrigen);
            }
            if (Helper.getLong(creation.getCarga_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getCarga_id()));
                direccion.ifPresent(model::setCarga);
            }
            if (Helper.getLong(creation.getDestino_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getDestino_id()));
                direccion.ifPresent(model::setDestino);
            }
            if (Helper.getLong(creation.getVendedor_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getVendedor_id()));
                cliente.ifPresent(model::setVendedor);
            }
            if (Helper.getLong(creation.getIntermediario_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getIntermediario_id()));
                cliente.ifPresent(model::setIntermediario);
            }
            if (Helper.getLong(creation.getComprador_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getComprador_id()));
                cliente.ifPresent(model::setComprador);
            }
            if (Helper.getLong(creation.getFecha_id()) != null) {
                Optional<EventoModel> evento = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getFecha_id()));
                evento.ifPresent(model::setFecha);
            }

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
    private String guia;
    private String conductor_id;
    private String camion_id;
    private String acoplado_id;
     */

    public ViajeDTO toDto(ViajeModel model) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            ViajeDTO dto = new ViajeDTO();

            dto.setId(model.getId().toString());
            dto.setGuia(model.getGuia());
            if (model.getConductor() != null)
                dto.setConductor(conductorMapper.toDto(model.getConductor()));
            if (model.getCamion() != null)
                dto.setCamion(camionMapper.toDto(model.getCamion()));
            if (model.getAcoplado() != null)
                dto.setAcoplado(acopladoMapper.toDto(model.getAcoplado()));
            if (!model.getGastos().isEmpty()) {
                List<GastoDTO> gastoDTOS = new ArrayList<>();
                for (GastoModel gasto:model.getGastos()) {
                    gastoDTOS.add(gastoMapper.toDto(gasto));
                }
                dto.setGastos(gastoDTOS);
            }
            if (model.getCantidadTransportada() != null)
                dto.setCantidadTransportada(format.format(model.getCantidadTransportada()).toString());
            if (model.getValorKm() != null)
                dto.setValorKm(format.format(model.getValorKm()).toString());
            if (model.getNeto() != null)
                dto.setNeto(format.format(model.getNeto()).toString());
            if (model.getKmCargado() != null)
                dto.setKmCargado(format.format(model.getKmCargado()).toString());
            if (model.getKmVacio() != null)
                dto.setKmVacio(format.format(model.getKmVacio()).toString());
            dto.setNotas(model.getNotas());
            if (model.getCategoriaViaje() != null)
                dto.setCategoriaViaje(categoriaViajeMapper.toDto(model.getCategoriaViaje()));
            if (model.getOrigen() != null)
                dto.setOrigen(direccionMapper.toDto(model.getOrigen()));
            if (model.getCarga() != null)
                dto.setCarga(direccionMapper.toDto(model.getCarga()));
            if (model.getDestino() != null)
                dto.setDestino(direccionMapper.toDto(model.getDestino()));
            if (model.getVendedor() != null)
                dto.setVendedor(clienteMapper.toDto(model.getVendedor()));
            if (model.getIntermediario() != null)
                dto.setIntermediario(clienteMapper.toDto(model.getIntermediario()));
            if (model.getComprador() != null)
                dto.setComprador(clienteMapper.toDto(model.getComprador()));
            if (model.getFecha() != null)
                dto.setFecha(eventoMapper.toDto(model.getFecha()));

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