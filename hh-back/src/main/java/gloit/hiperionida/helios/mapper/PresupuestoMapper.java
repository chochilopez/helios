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
    private final CategoriaViajeDAO categoriaViajeDAO;
    private final ClienteDAO clienteDAO;
    private final DireccionDAO direccionDAO;
    private final EventoDAO eventoDAO;
    private final UsuarioDAO usuarioDAO;

    public PresupuestoModel toEntity(PresupuestoCreation creation) {
        try {
            PresupuestoModel model = new PresupuestoModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (creation.getValidez() != null && Helper.stringToLocalDateTime(creation.getValidez(), "") != null)
                model.setValidez(Helper.stringToLocalDateTime(creation.getValidez(), ""));
            if (Helper.getDecimal(creation.getCantidadTransportada()) != null)
                model.setCantidadTransportada(Helper.getDecimal(creation.getCantidadTransportada()));
            if (Helper.getDecimal(creation.getValorKm()) != null)
                model.setValorKm(Helper.getDecimal(creation.getValorKm()));
            model.setNotas(creation.getNotas());

            if (Helper.getLong(creation.getCategoriaViajeId()) != null)
                model.setCategoriaViajeId(Helper.getLong(creation.getCategoriaViajeId()));
            if (Helper.getLong(creation.getCompradorId()) != null)
                model.setCompradorId(Helper.getLong(creation.getCompradorId()));
            if (Helper.getLong(creation.getDestinoId()) != null)
                model.setDestinoId(Helper.getLong(creation.getDestinoId()));
            if (Helper.getLong(creation.getOrigenId()) != null)
                model.setOrigenId(Helper.getLong(creation.getOrigenId()));

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

    public PresupuestoDTO toDto(PresupuestoModel model) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            PresupuestoDTO dto = new PresupuestoDTO();

            dto.setId(model.getId().toString());
            dto.setValidez(model.getValidez().toString());
            if (model.getCantidadTransportada() != null)
                dto.setCantidadTransportada(model.getCantidadTransportada().toString());
            if (model.getValorKm() != null)
                dto.setValorKm(format.format(model.getValorKm()).toString());
            dto.setNotas(model.getNotas());

            if (model.getCategoriaViajeId() != null)
                dto.setCategoriaViaje(categoriaViajeDAO.findByIdAndEliminadaIsNull(model.getCategoriaViajeId()).get().getCategoria());
            if (model.getCompradorId() != null)
                dto.setComprador(clienteDAO.findByIdAndEliminadaIsNull(model.getCompradorId()).get().getNombre());
            if (model.getDestinoId() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getDestinoId());
                dto.setDestino(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }
            if (model.getFechaId() != null)
                dto.setFecha(eventoDAO.findByIdAndEliminadaIsNull(model.getFechaId()).get().getFecha().toString());
            if (model.getOrigenId() != null) {
                Optional<DireccionModel> direccionModel = direccionDAO.findByIdAndEliminadaIsNull(model.getOrigenId());
                dto.setOrigen(direccionModel.get().getCiudad() + " - " + direccionModel.get().getDireccion());
            }


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