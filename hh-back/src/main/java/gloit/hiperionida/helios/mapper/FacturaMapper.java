package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.FacturaModel;
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
public class FacturaMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;

    public FacturaModel toEntity(FacturaCreation facturaCreation) {
        try {
            FacturaModel facturaModel = new FacturaModel();

            private String id;
            private String descuento;
            private String fecha;
            private String iva;
            private String numeroComprobante;
            private String recarga;
            private String subTotal;
            private String tipoComprobante;
            private String pagada;
            private String movimiento_id;
            private List<String> pagos_id;
            private String remito_id;
            private String viaje_id;

            if (Helper.getLong(facturaCreation.getId()) != null)
                facturaModel.setId(Helper.getLong(facturaCreation.getId()));

            if (Helper.getLong(facturaCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(facturaCreation.getCreador_id()));
                if (user.isPresent())
                    facturaModel.setCreador(user.get());
            }
            if (!Helper.isEmptyString(facturaCreation.getCreada()))
                facturaModel.setCreada(Helper.stringToLocalDateTime(facturaCreation.getCreada(), ""));
            if (Helper.getLong(facturaCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(facturaCreation.getModificador_id()));
                if (user.isPresent())
                    facturaModel.setModificador(user.get());
            }
            if (!Helper.isEmptyString(facturaCreation.getModificada()))
                facturaModel.setModificada(Helper.stringToLocalDateTime(facturaCreation.getModificada(), ""));
            if (Helper.getLong(facturaCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(facturaCreation.getEliminador_id()));
                if (user.isPresent())
                    facturaModel.setEliminador(user.get());
            }
            if (!Helper.isEmptyString(facturaCreation.getEliminada()))
                facturaModel.setEliminada(Helper.stringToLocalDateTime(facturaCreation.getEliminada(), ""));

            return facturaModel;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir Creation a entidad. Excepcion: " + e);
            return null;
        }
    }

    public FacturaDTO toDto(FacturaModel facturaModel) {
        try {
            FacturaDTO dto = new FacturaDTO();

            private String id;
            private String descuento;
            private String fecha;
            private String iva;
            private String numeroComprobante;
            private String recarga;
            private String subTotal;
            private String tipoComprobante;
            private String pagada;
            private MovimientoDTO movimiento;
            private List<PagoDTO> pagos;
            private RemitoDTO remito;
            private ViajeDTO viaje;

            dto.setId(facturaModel.getId().toString());

            if (facturaModel.getCreador() != null)
                dto.setCreador(usuarioMapper.toDto(facturaModel.getCreador()));
            if (facturaModel.getCreada() != null)
                dto.setCreada(Helper.localDateTimeToString(facturaModel.getCreada(), ""));
            if (facturaModel.getModificador() != null)
                dto.setModificador(usuarioMapper.toDto(facturaModel.getModificador()));
            if (facturaModel.getModificada() != null)
                dto.setModificada(Helper.localDateTimeToString(facturaModel.getModificada(), ""));
            if (facturaModel.getEliminador() != null)
                dto.setEliminador(usuarioMapper.toDto(facturaModel.getEliminador()));
            if (facturaModel.getEliminada() != null)
                dto.setEliminada(Helper.localDateTimeToString(facturaModel.getEliminada(), ""));

            return dto;
        } catch (Exception e) {
            log.error("Ocurrio un error al convertir de entidad a DTO. Excepcion: " + e);
            return null;
        }
    }
}