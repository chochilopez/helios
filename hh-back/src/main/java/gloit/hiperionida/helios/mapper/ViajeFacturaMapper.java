package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.repository.*;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViajeFacturaMapper {
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

    public ViajeFacturaDTO toDto(ViajeModel viajeModel) {
        try {
            DecimalFormat format = new DecimalFormat("0.#");
            ViajeFacturaDTO dto = new ViajeFacturaDTO();

            dto.setId(viajeModel.getId().toString());
            if (viajeModel.getCantidadTransportada() != null)
                dto.setCantidadTransportada(format.format(viajeModel.getCantidadTransportada()).toString());
            if (viajeModel.getValorKm() != null)
                dto.setValorKm(format.format(viajeModel.getValorKm()).toString());
            if (viajeModel.getConductor() != null)
                dto.setConductor(viajeModel.getConductor().getNombre());
            if (viajeModel.getCamion() != null)
                dto.setCamion(viajeModel.getCamion().getMarca() + " - " + viajeModel.getCamion().getModelo());
            if (viajeModel.getCategoriaViaje() != null)
                dto.setCategoriaViaje(viajeModel.getCategoriaViaje().getCategoria());
            if (viajeModel.getDestino() != null)
                dto.setDestino(viajeModel.getDestino().getUbicacion().getCiudad() + " " + viajeModel.getDestino().getDireccion());
            if (viajeModel.getOrigen() != null)
                dto.setOrigen(viajeModel.getOrigen().getUbicacion().getCiudad() + " " + viajeModel.getOrigen().getDireccion());

            if (viajeModel.getComprador() != null)
                dto.setComprador(viajeModel.getComprador().getNombre());
            if (viajeModel.getFecha() != null)
                dto.setFecha(viajeModel.getFecha().getFecha().toString());

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}