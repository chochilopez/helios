package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CompraCreation;
import gloit.hiperionida.helios.mapper.dto.CompraDTO;
import gloit.hiperionida.helios.mapper.dto.PagoDTO;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.mapper.dto.RemitoDTO;
import gloit.hiperionida.helios.model.CompraModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.mapper.dto.ArchivoDTO;
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
public class CompraMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public CompraModel toEntity(CompraCreation compraCreation) {
        try {
            CompraModel compraModel = new CompraModel();

            private String id;
            private String fecha;
            private String iva;
            private String numeroComprobante;
            private String subTotal;
            private String tipoComprobante;
            private String notas;
            private String comprobante_id;
            private List<String> pagos_id;
            private String proveedor_id;
            private String remito_id;

            if (Helper.getLong(compraCreation.getId()) != null)
                compraModel.setId(Helper.getLong(compraCreation.getId()));

            if (Helper.getLong(compraCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(compraCreation.getCreador_id()));
                if (user.isPresent())
                    compraModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(compraCreation.getCreada()))
                compraModel.setCreada(Helper.stringToLocalDateTime(compraCreation.getCreada(), ""));
            if (Helper.getLong(compraCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(compraCreation.getModificador_id()));
                if (user.isPresent())
                    compraModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(compraCreation.getModificada()))
                compraModel.setModificada(Helper.stringToLocalDateTime(compraCreation.getModificada(), ""));
            if (Helper.getLong(compraCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(compraCreation.getEliminador_id()));
                if (user.isPresent())
                    compraModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(compraCreation.getEliminada()))
                compraModel.setEliminada(Helper.stringToLocalDateTime(compraCreation.getEliminada(), ""));

            return compraModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public CompraDTO toDto(CompraModel compraModel) {
        try {
            CompraDTO dto = new CompraDTO();

            private String id;
            private String fecha;
            private String iva;
            private String numeroComprobante;
            private String subTotal;
            private String tipoComprobante;
            private String notas;
            private ArchivoDTO comprobante;
            private List<PagoDTO> pagos;
            private ProveedorDTO proveedor;
            private RemitoDTO remito;

            dto.setId(compraModel.getId().toString());


            if (compraModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(compraModel.getCreador()));
            if (compraModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(compraModel.getCreada(), ""));
            if (compraModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(compraModel.getModificador()));
            if (compraModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(compraModel.getModificada(), ""));
            if (compraModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(compraModel.getEliminador()));
            if (compraModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(compraModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}