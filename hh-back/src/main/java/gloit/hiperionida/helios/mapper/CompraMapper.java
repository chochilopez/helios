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
    /*
        private String id;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private String comprobante_id;
    private String proveedor_id;
    private String remito_id;
     */

    public CompraModel toEntity(CompraCreation creation) {
        try {
            CompraModel model = new CompraModel();

            if (Helper.getLong(creation.getId()) != null)
                model.setId(Helper.getLong(creation.getId()));
            if (creation.getFecha() != null && Helper.stringToLocalDateTime(creation.getFecha(), "") != null)
                model.setFecha(Helper.stringToLocalDateTime(creation.getFecha(), ""));
            if (Helper.getDecimal(creation.getFecha()) != null)
                model.setIva(Helper.getDecimal(creation.getFecha()));
            model.setNumeroComprobante(creation.getNumeroComprobante());
            if (Helper.getDecimal(creation.getSubTotal()) != null)
                model.setSubTotal(Helper.getDecimal(creation.getSubTotal()));
            if (creation.getTipoComprobante() != null)
                model.setTipoComprobante(TipoComprobanteEnum.valueOf(creation.getTipoComprobante()));
            model.setNotas(creation.getNotas());

            if (Helper.getLong(creation.getComprobante_id()) != null) {
                Optional<ArchivoModel> archivo = archivoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getComprobante_id()));
                archivo.ifPresent(model::setComprobante);
            }
            if (Helper.getLong(creation.getProveedor_id()) != null) {
                Optional<ProveedorModel> proveedor = proveedorDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getProveedor_id()));
                proveedor.ifPresent(model::setProveedor);
            }
            if (Helper.getLong(creation.getRemito_id()) != null) {
                Optional<RemitoModel> remito = remitoDAO.findByIdAndEliminadaIsNull(Helper.getLong(creation.getRemito_id()));
                remito.ifPresent(model::setRemito);
            }
            Set<PagoModel> pagos = new HashSet<>();
            if (creation.getPagos_id() != null) {
                for (String pago_id : creation.getPagos_id()) {
                    if (Helper.getLong(pago_id) != null) {
                        Optional<PagoModel> pago = pagoDAO.findByIdAndEliminadaIsNull(Helper.getLong(pago_id));
                        pago.ifPresent(pagos::add);
                    }
                }
            }
            model.setPagos(pagos);

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
    /*
        private String id;
    private String fecha;
    private String iva;
    private String numeroComprobante;
    private String subTotal;
    private String tipoComprobante;
    private String notas;
    private String comprobante_id;
    private String proveedor_id;
    private String remito_id;
     */

    public CompraDTO toDto(CompraModel model) {
        try {
            CompraDTO dto = new CompraDTO();

            dto.setId(model.getId().toString());
            dto.setFecha(model.getFecha().toString());
            dto.setIva(model.getIva().toString());
            dto.setNumeroComprobante(model.getNumeroComprobante());
            dto.setSubTotal(model.getSubTotal().toString());
            dto.setTipoComprobante(model.getTipoComprobante().name());
            dto.setNotas(model.getNotas());
            if (model.getComprobante() != null)
                dto.setComprobante(archivoMapper.toDto(model.getComprobante()));
            if (model.getProveedor() != null)
                dto.setProveedor(proveedorMapper.toDto(model.getProveedor()));
            if (model.getRemito() != null)
                dto.setRemito(remitoMapper.toDto(model.getRemito()));
            if (!model.getPagos().isEmpty()) {
                List<PagoDTO> pagoDTOS = new ArrayList<>();
                for (PagoModel pago:model.getPagos()) {
                    pagoDTOS.add(pagoMapper.toDto(pago));
                }
                dto.setPagos(pagoDTOS);
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