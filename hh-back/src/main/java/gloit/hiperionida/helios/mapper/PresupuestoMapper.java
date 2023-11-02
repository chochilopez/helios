package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.CategoriaViajeDAO;
import gloit.hiperionida.helios.repository.ClienteDAO;
import gloit.hiperionida.helios.repository.DireccionDAO;
import gloit.hiperionida.helios.repository.EventoDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class PresupuestoMapper {
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

    public PresupuestoModel toEntity(PresupuestoCreation presupuestoCreation) {
        try {
            PresupuestoModel presupuestoModel = new PresupuestoModel();

            if (Helper.getLong(presupuestoCreation.getId()) != null)
                presupuestoModel.setId(Helper.getLong(presupuestoCreation.getId()));
            if (presupuestoCreation.getValidez() != null && Helper.stringToLocalDateTime(presupuestoCreation.getValidez(), "") != null)
                presupuestoModel.setValidez(Helper.stringToLocalDateTime(presupuestoCreation.getValidez(), ""));

            if (Helper.getDecimal(presupuestoCreation.getCantidadTransportada()) != null)
                presupuestoModel.setCantidadTransportada(Helper.getDecimal(presupuestoCreation.getCantidadTransportada()));
            if (Helper.getDecimal(presupuestoCreation.getValorKm()) != null)
                presupuestoModel.setValorKm(Helper.getDecimal(presupuestoCreation.getValorKm()));
            if (Helper.getDecimal(presupuestoCreation.getNeto()) != null)
                presupuestoModel.setNeto(Helper.getDecimal(presupuestoCreation.getNeto()));
            if (Helper.getDecimal(presupuestoCreation.getKmCargado()) != null)
                presupuestoModel.setKmCargado(Helper.getDecimal(presupuestoCreation.getKmCargado()));
            if (Helper.getDecimal(presupuestoCreation.getKmVacio()) != null)
                presupuestoModel.setKmVacio(Helper.getDecimal(presupuestoCreation.getKmVacio()));
            presupuestoModel.setNotas(presupuestoCreation.getNotas());
            if (Helper.getLong(presupuestoCreation.getCategoriaViaje_id()) != null) {
                Optional<CategoriaViajeModel> categoriaViaje = categoriaViajeDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getCategoriaViaje_id()));
                categoriaViaje.ifPresent(presupuestoModel::setCategoriaViaje);
            }
            if (Helper.getLong(presupuestoCreation.getOrigen_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getOrigen_id()));
                direccion.ifPresent(presupuestoModel::setOrigen);
            }
            if (Helper.getLong(presupuestoCreation.getCarga_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getCarga_id()));
                direccion.ifPresent(presupuestoModel::setCarga);
            }
            if (Helper.getLong(presupuestoCreation.getDestino_id()) != null) {
                Optional<DireccionModel> direccion = direccionDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getDestino_id()));
                direccion.ifPresent(presupuestoModel::setDestino);
            }
            if (Helper.getLong(presupuestoCreation.getVendedor_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getVendedor_id()));
                cliente.ifPresent(presupuestoModel::setVendedor);
            }
            if (Helper.getLong(presupuestoCreation.getIntermediario_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getIntermediario_id()));
                cliente.ifPresent(presupuestoModel::setIntermediario);
            }
            if (Helper.getLong(presupuestoCreation.getComprador_id()) != null) {
                Optional<ClienteModel> cliente = clienteDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getComprador_id()));
                cliente.ifPresent(presupuestoModel::setComprador);
            }
            if (Helper.getLong(presupuestoCreation.getFecha_id()) != null) {
                Optional<EventoModel> evento = eventoDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getFecha_id()));
                evento.ifPresent(presupuestoModel::setFecha);
            }

            if (Helper.getLong(presupuestoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getCreador_id()));
                usuario.ifPresent(presupuestoModel::setCreador);
            }
            if (Helper.stringToLocalDateTime(presupuestoCreation.getCreada(), "") != null)
                presupuestoModel.setCreada(Helper.stringToLocalDateTime(presupuestoCreation.getCreada(), ""));
            if (Helper.getLong(presupuestoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getModificador_id()));
                usuario.ifPresent(presupuestoModel::setModificador);
            }
            if (Helper.stringToLocalDateTime(presupuestoCreation.getModificada(), "") != null)
                presupuestoModel.setModificada(Helper.stringToLocalDateTime(presupuestoCreation.getModificada(), ""));
            if (Helper.getLong(presupuestoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> usuario = usuarioDAO.findByIdAndEliminadaIsNull(Helper.getLong(presupuestoCreation.getEliminador_id()));
                usuario.ifPresent(presupuestoModel::setEliminador);
            }
            if (Helper.stringToLocalDateTime(presupuestoCreation.getEliminada(), "") != null)
                presupuestoModel.setEliminada(Helper.stringToLocalDateTime(presupuestoCreation.getEliminada(), ""));

            return presupuestoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public PresupuestoDTO toDto(PresupuestoModel presupuestoModel) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            PresupuestoDTO dto = new PresupuestoDTO();

            dto.setId(presupuestoModel.getId().toString());
            dto.setValidez(presupuestoModel.getValidez().toString());
            if (presupuestoModel.getCantidadTransportada() != null)
                dto.setCantidadTransportada(format.format(presupuestoModel.getCantidadTransportada()).toString());
            if (presupuestoModel.getValorKm() != null)
                dto.setValorKm(format.format(presupuestoModel.getValorKm()).toString());
            if (presupuestoModel.getNeto() != null)
                dto.setNeto(format.format(presupuestoModel.getNeto()).toString());
            if (presupuestoModel.getKmCargado() != null)
                dto.setKmCargado(format.format(presupuestoModel.getKmCargado()).toString());
            if (presupuestoModel.getKmVacio() != null)
                dto.setKmVacio(format.format(presupuestoModel.getKmVacio()).toString());
            dto.setNotas(presupuestoModel.getNotas());
            if (presupuestoModel.getCategoriaViaje() != null)
                dto.setCategoriaViaje(categoriaViajeMapper.toDto(presupuestoModel.getCategoriaViaje()));
            if (presupuestoModel.getOrigen() != null)
                dto.setOrigen(direccionMapper.toDto(presupuestoModel.getOrigen()));
            if (presupuestoModel.getCarga() != null)
                dto.setCarga(direccionMapper.toDto(presupuestoModel.getCarga()));
            if (presupuestoModel.getDestino() != null)
                dto.setDestino(direccionMapper.toDto(presupuestoModel.getDestino()));
            if (presupuestoModel.getVendedor() != null)
                dto.setVendedor(clienteMapper.toDto(presupuestoModel.getVendedor()));
            if (presupuestoModel.getIntermediario() != null)
                dto.setIntermediario(clienteMapper.toDto(presupuestoModel.getIntermediario()));
            if (presupuestoModel.getComprador() != null)
                dto.setComprador(clienteMapper.toDto(presupuestoModel.getComprador()));
            if (presupuestoModel.getFecha() != null)
                dto.setFecha(eventoMapper.toDto(presupuestoModel.getFecha()));

            if (presupuestoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(presupuestoModel.getCreador()));
            if (Helper.localDateTimeToString(presupuestoModel.getCreada(), "") != null)
                dto.setCreada(Helper.localDateTimeToString(presupuestoModel.getCreada(), ""));
            if (presupuestoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(presupuestoModel.getModificador()));
            if (Helper.localDateTimeToString(presupuestoModel.getModificada(), "") != null)
                dto.setModificada(Helper.localDateTimeToString(presupuestoModel.getModificada(), ""));
            if (presupuestoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(presupuestoModel.getEliminador()));
            if (Helper.localDateTimeToString(presupuestoModel.getEliminada(), "") != null)
                dto.setEliminada(Helper.localDateTimeToString(presupuestoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}