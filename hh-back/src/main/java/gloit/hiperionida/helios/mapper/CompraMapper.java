package gloit.hiperionida.helios.mapper;

import gloit.hiperionida.helios.mapper.creation.CompraCreation;
import gloit.hiperionida.helios.mapper.dto.*;
import gloit.hiperionida.helios.model.*;
import gloit.hiperionida.helios.model.enums.TipoComprobanteEnum;
import gloit.hiperionida.helios.repository.PagoDAO;
import gloit.hiperionida.helios.repository.ProveedorDAO;
import gloit.hiperionida.helios.repository.RemitoDAO;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.ArchivoMapper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.model.ArchivoModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.repository.ArchivoDAO;
import gloit.hiperionida.helios.util.repository.UsuarioDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class CompraMapper {
    private final UsuarioDAO usuarioDAO;
    private final UsuarioMapper usuarioMapper;
    private final ArchivoDAO archivoDAO;
    private final ArchivoMapper archivoMapper;
    private final PagoDAO pagoDAO;
    private final PagoMapper pagoMapper;
    private final ProveedorDAO proveedorDAO;
    private final ProveedorMapper proveedorMapper;
    private final RemitoDAO remitoDAO;
    private final RemitoMapper remitoMapper;

    public CompraModel toEntity(CompraCreation compraCreation) {
        try {
            CompraModel compraModel = new CompraModel();

            if (Helper.getLong(compraCreation.getId()) != null)
                compraModel.setId(Helper.getLong(compraCreation.getId()));
            if (compraCreation.getFecha() != null && Helper.stringToLocalDateTime(compraCreation.getFecha(), "") != null)
                compraModel.setFecha(Helper.stringToLocalDateTime(compraCreation.getFecha(), ""));
            if (Helper.getDecimal(compraCreation.getFecha()) != null)
                compraModel.setIva(Helper.getDecimal(compraCreation.getFecha()));
            compraModel.setNumeroComprobante(compraCreation.getNumeroComprobante());
            if (Helper.getDecimal(compraCreation.getSubTotal()) != null)
                compraModel.setSubTotal(Helper.getDecimal(compraCreation.getSubTotal()));
            if (compraCreation.getTipoComprobante() != null)
                compraModel.setTipoComprobante(TipoComprobanteEnum.valueOf(compraCreation.getTipoComprobante()));
            compraModel.setNotas(compraCreation.getNotas());

            if (Helper.getLong(compraCreation.getComprobante_id()) != null) {
                Optional<ArchivoModel> archivo = archivoDAO.findById(Helper.getLong(compraCreation.getComprobante_id()));
                archivo.ifPresent(compraModel::setComprobante);
            }
            if (Helper.getLong(compraCreation.getProveedor_id()) != null) {
                Optional<ProveedorModel> proveedor = proveedorDAO.findById(Helper.getLong(compraCreation.getProveedor_id()));
                proveedor.ifPresent(compraModel::setProveedor);
            }
            if (Helper.getLong(compraCreation.getRemito_id()) != null) {
                Optional<RemitoModel> remito = remitoDAO.findById(Helper.getLong(compraCreation.getRemito_id()));
                remito.ifPresent(compraModel::setRemito);
            }
            Set<PagoModel> pagos = new HashSet<>();
            for (String pago_id: compraCreation.getPagos_id()) {
                if (Helper.getLong(pago_id) != null) {
                    Optional<PagoModel> pago = pagoDAO.findById(Helper.getLong(pago_id));
                    pago.ifPresent(pagos::add);
                }
            }
            compraModel.setPagos(pagos);

            if (Helper.getLong(compraCreation.getCreador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(compraCreation.getCreador_id()));
                user.ifPresent(compraModel::setCreador);
            }
            if (!Helper.isEmptyString(compraCreation.getCreada()))
                compraModel.setCreada(Helper.stringToLocalDateTime(compraCreation.getCreada(), ""));
            if (Helper.getLong(compraCreation.getModificador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(compraCreation.getModificador_id()));
                user.ifPresent(compraModel::setModificador);
            }
            if (!Helper.isEmptyString(compraCreation.getModificada()))
                compraModel.setModificada(Helper.stringToLocalDateTime(compraCreation.getModificada(), ""));
            if (Helper.getLong(compraCreation.getEliminador_id()) != null) {
                Optional<UsuarioModel> user = usuarioDAO.findById(Helper.getLong(compraCreation.getEliminador_id()));
                user.ifPresent(compraModel::setEliminador);
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

            dto.setId(compraModel.getId().toString());
            dto.setFecha(compraModel.getFecha().toString());
            dto.setIva(compraModel.getIva().toString());
            dto.setNumeroComprobante(compraModel.getNumeroComprobante());
            dto.setSubTotal(compraModel.getSubTotal().toString());
            dto.setTipoComprobante(compraModel.getTipoComprobante().name());
            dto.setNotas(compraModel.getNotas());
            if (compraModel.getComprobante() != null)
                dto.setComprobante(archivoMapper.toDto(compraModel.getComprobante()));
            if (compraModel.getProveedor() != null)
                dto.setProveedor(proveedorMapper.toDto(compraModel.getProveedor()));
            if (compraModel.getRemito() != null)
                dto.setRemito(remitoMapper.toDto(compraModel.getRemito()));
            if (!compraModel.getPagos().isEmpty()) {
                List<PagoDTO> pagoDTOS = new ArrayList<>();
                for (PagoModel pago:compraModel.getPagos()) {
                    pagoDTOS.add(pagoMapper.toDto(pago));
                }
                dto.setPagos(pagoDTOS);
            }

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