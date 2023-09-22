package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.ServicioCreation;
import gloit.hiperionida.helios.mapper.dto.ServicioDTO;
import gloit.hiperionida.helios.model.ServicioModel;
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
public class ServicioMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public ServicioModel toEntity(ServicioCreation servicioCreation) {
        try {
            ServicioModel servicioModel = new ServicioModel();

            private String id;
            private String kmServicio;
            private String kmActual;
            private String kmProximo;
            private String tipo;
            private	String cantidadNeumaticos;
            private	String marca;
            private String modelo;
            private String anio;
            private String patente;
            private String peso;

            if (Helper.getLong(servicioCreation.getId()) != null)
                servicioModel.setId(Helper.getLong(servicioCreation.getId()));

            if (Helper.getLong(servicioCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(servicioCreation.getCreador_id()));
                if (user.isPresent())
                    servicioModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(servicioCreation.getCreada()))
                servicioModel.setCreada(Helper.stringToLocalDateTime(servicioCreation.getCreada(), ""));
            if (Helper.getLong(servicioCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(servicioCreation.getModificador_id()));
                if (user.isPresent())
                    servicioModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(servicioCreation.getModificada()))
                servicioModel.setModificada(Helper.stringToLocalDateTime(servicioCreation.getModificada(), ""));
            if (Helper.getLong(servicioCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(servicioCreation.getEliminador_id()));
                if (user.isPresent())
                    servicioModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(servicioCreation.getEliminada()))
                servicioModel.setEliminada(Helper.stringToLocalDateTime(servicioCreation.getEliminada(), ""));

            return servicioModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public ServicioDTO toDto(ServicioModel servicioModel) {
        try {
            ServicioDTO dto = new ServicioDTO();

            private String id;
            private String kmServicio;
            private String kmActual;
            private String kmProximo;
            private String tipo;    private	String cantidadNeumaticos;
            private	String marca;
            private String modelo;
            private String anio;
            private String patente;
            private String peso;

            dto.setId(servicioModel.getId().toString());


            if (servicioModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(servicioModel.getCreador()));
            if (servicioModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(servicioModel.getCreada(), ""));
            if (servicioModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(servicioModel.getModificador()));
            if (servicioModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(servicioModel.getModificada(), ""));
            if (servicioModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(servicioModel.getEliminador()));
            if (servicioModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(servicioModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}