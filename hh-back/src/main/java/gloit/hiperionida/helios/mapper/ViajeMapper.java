package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViajeMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ViajeModel toEntity(ViajeCreation viajeCreation) {
        try {
            ViajeModel viajeModel = new ViajeModel();

            private String id;
            private String guia;
            private String conductor_id;
            private String camion_id;
            private String acoplado_id;
            private List<String> gastos_id;
            private String cantidadTransportada;
            private String valorKm;
            private String kgNeto;
            private String kmCargado;
            private String kmVacio;
            private String notas;
            private String categoriaViaje_id;
            private String origen_id;
            private String carga_id;
            private String destino_id;
            private String vendedor_id;
            private String intermediario_id;
            private String comprador_id;
            private String fecha_id;

            if (Helper.getLong(viajeCreation.getId()) != null)
                viajeModel.setId(Helper.getLong(viajeCreation.getId()));

            if (Helper.getLong(viajeCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(viajeCreation.getCreador_id()));
                if (user.isPresent())
                    viajeModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(viajeCreation.getCreada()))
                viajeModel.setCreada(Helper.stringToLocalDateTime(viajeCreation.getCreada(), ""));
            if (Helper.getLong(viajeCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(viajeCreation.getModificador_id()));
                if (user.isPresent())
                    viajeModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(viajeCreation.getModificada()))
                viajeModel.setModificada(Helper.stringToLocalDateTime(viajeCreation.getModificada(), ""));
            if (Helper.getLong(viajeCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(viajeCreation.getEliminador_id()));
                if (user.isPresent())
                    viajeModel.setEliminador(user.get());
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

            private String id;
            private String guia;
            private ConductorDTO conductor;
            private CamionDTO camion;
            private AcopladoDTO acoplado;
            private List<GastoDTO> gastos;
            private String cantidadTransportada;
            private String valorKm;
            private String kgNeto;
            private String kmCargado;
            private String kmVacio;
            private String notas;
            private CategoriaViajeDTO categoriaViaje;
            private DireccionDTO origen;
            private DireccionDTO carga;
            private DireccionDTO destino;
            private ClienteDTO vendedor;
            private ClienteDTO intermediario;
            private ClienteDTO comprador;
            private EventoDTO fecha;

            dto.setId(viajeModel.getId().toString());

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