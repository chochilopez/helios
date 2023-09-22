package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.util.Helper;
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
public class PresupuestoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public PresupuestoModel toEntity(PresupuestoCreation presupuestoCreation) {
        try {
            PresupuestoModel presupuestoModel = new PresupuestoModel();

            private String id;
            private String validez;
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

            if (Helper.getLong(presupuestoCreation.getId()) != null)
                presupuestoModel.setId(Helper.getLong(presupuestoCreation.getId()));

            if (Helper.getLong(presupuestoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(presupuestoCreation.getCreador_id()));
                if (user.isPresent())
                    presupuestoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(presupuestoCreation.getCreada()))
                presupuestoModel.setCreada(Helper.stringToLocalDateTime(presupuestoCreation.getCreada(), ""));
            if (Helper.getLong(presupuestoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(presupuestoCreation.getModificador_id()));
                if (user.isPresent())
                    presupuestoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(presupuestoCreation.getModificada()))
                presupuestoModel.setModificada(Helper.stringToLocalDateTime(presupuestoCreation.getModificada(), ""));
            if (Helper.getLong(presupuestoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(presupuestoCreation.getEliminador_id()));
                if (user.isPresent())
                    presupuestoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(presupuestoCreation.getEliminada()))
                presupuestoModel.setEliminada(Helper.stringToLocalDateTime(presupuestoCreation.getEliminada(), ""));

            return presupuestoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public PresupuestoDTO toDto(PresupuestoModel presupuestoModel) {
        try {
            PresupuestoDTO dto = new PresupuestoDTO();

            private String id;
            private String validez;
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

            dto.setId(presupuestoModel.getId().toString());


            if (presupuestoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(presupuestoModel.getCreador()));
            if (presupuestoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(presupuestoModel.getCreada(), ""));
            if (presupuestoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(presupuestoModel.getModificador()));
            if (presupuestoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(presupuestoModel.getModificada(), ""));
            if (presupuestoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(presupuestoModel.getEliminador()));
            if (presupuestoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(presupuestoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}