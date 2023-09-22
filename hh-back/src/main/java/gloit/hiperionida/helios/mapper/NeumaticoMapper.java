package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.NeumaticoCreation;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.model.NeumaticoModel;
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
public class NeumaticoMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public NeumaticoModel toEntity(NeumaticoCreation neumaticoCreation) {
        try {
            NeumaticoModel neumaticoModel = new NeumaticoModel();

            private String id;
            private String fechaCompra;
            private String kmVida;
            private String kmActuales;
            private String kmRecapado;
            private String numeroRemito;
            private String marca ;
            private String precioCompra;
            private String recapadosMaximos;
            private String ubicacion;
            private String estado;
            private String baja;
            private String proveedor_id;

            if (Helper.getLong(neumaticoCreation.getId()) != null)
                neumaticoModel.setId(Helper.getLong(neumaticoCreation.getId()));

            if (Helper.getLong(neumaticoCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(neumaticoCreation.getCreador_id()));
                if (user.isPresent())
                    neumaticoModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(neumaticoCreation.getCreada()))
                neumaticoModel.setCreada(Helper.stringToLocalDateTime(neumaticoCreation.getCreada(), ""));
            if (Helper.getLong(neumaticoCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(neumaticoCreation.getModificador_id()));
                if (user.isPresent())
                    neumaticoModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(neumaticoCreation.getModificada()))
                neumaticoModel.setModificada(Helper.stringToLocalDateTime(neumaticoCreation.getModificada(), ""));
            if (Helper.getLong(neumaticoCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(neumaticoCreation.getEliminador_id()));
                if (user.isPresent())
                    neumaticoModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(neumaticoCreation.getEliminada()))
                neumaticoModel.setEliminada(Helper.stringToLocalDateTime(neumaticoCreation.getEliminada(), ""));

            return neumaticoModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public NeumaticoDTO toDto(NeumaticoModel neumaticoModel) {
        try {
            NeumaticoDTO dto = new NeumaticoDTO();

            private String id;
            private String fechaCompra;
            private String kmVida;
            private String kmActuales;
            private String kmRecapado;
            private String numeroRemito;
            private String marca ;
            private String precioCompra;
            private String recapadosMaximos;
            private String ubicacion;
            private String estado;
            private String baja;
            private ProveedorDTO proveedor;

            dto.setId(neumaticoModel.getId().toString());


            if (neumaticoModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(neumaticoModel.getCreador()));
            if (neumaticoModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(neumaticoModel.getCreada(), ""));
            if (neumaticoModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(neumaticoModel.getModificador()));
            if (neumaticoModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(neumaticoModel.getModificada(), ""));
            if (neumaticoModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(neumaticoModel.getEliminador()));
            if (neumaticoModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(neumaticoModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}