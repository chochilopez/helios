package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.FacturaMapper;
import gloit.hiperionida.helios.mapper.creation.FacturaCreation;
import gloit.hiperionida.helios.mapper.dto.FacturaDTO;
import gloit.hiperionida.helios.mapper.dto.ViajeDTO;
import gloit.hiperionida.helios.model.FacturaModel;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.service.implementation.FacturaServiceImpl;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.controller.AbsBaseController;
import gloit.hiperionida.helios.util.mapper.dto.ErrorDTO;
import gloit.hiperionida.helios.util.mapper.dto.PaginadoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/factura")
@RequiredArgsConstructor
@RestController
@Slf4j
public class FacturaController extends AbsBaseController {
    private final FacturaServiceImpl facturaService;
    private final FacturaMapper facturaMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el factura. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-viaje-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<FacturaDTO> buscarPorViajeId(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.buscarPorViajeId(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con viaje id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-viaje-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FacturaDTO> buscarPorViajeIdConEliminadas(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.buscarPorViajeIdConEliminadas(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con viaje id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-remito-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<FacturaDTO> buscarPorRemitoId(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.buscarPorRemitoId(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con remito id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-remito-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FacturaDTO> buscarPorRemitoIdConEliminadas(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.buscarPorRemitoIdConEliminadas(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con remito id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-codigo/{codigo}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorCodigo(@PathVariable(name = "codigo") String codigo) {
        List<FacturaModel> listado = facturaService.buscarTodasPorCodigo(codigo);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-codigo-con-eliminadas/{codigo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorCodigoConEliminadas(@PathVariable(name = "codigo") String codigo) {
        List<FacturaModel> listado = facturaService.buscarTodasPorCodigoConEliminadas(codigo);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-concepto/{concepto}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorConcepto(@PathVariable(name = "concepto") String concepto) {
        List<FacturaModel> listado = facturaService.buscarTodasPorConcepto(concepto);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-concepto-con-eliminadas/{concepto}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorConceptoConEliminadas(@PathVariable(name = "concepto") String concepto) {
        List<FacturaModel> listado = facturaService.buscarTodasPorConceptoConEliminadas(concepto);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-emision-entre-fechas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorFechaEmisionBetween(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<FacturaModel> listado = facturaService.buscarTodasPorFechaEmisionBetween(inicio, fin);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-emision-entre-fechas-con-eliminadas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorFechaEmisionBetweenConEliminadas(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<FacturaModel> listado = facturaService.buscarTodasPorFechaEmisionBetweenConEliminadas(inicio, fin);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-vencimiento-entre-fechas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorFechaVencimientoBetween(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<FacturaModel> listado = facturaService.buscarTodasPorFechaVencimientoBetween(inicio, fin);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-vencimiento-entre-fechas-con-eliminadas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorFechaVencimientoBetweenConEliminadas(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<FacturaModel> listado = facturaService.buscarTodasPorFechaVencimientoBetweenConEliminadas(inicio, fin);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<FacturaModel> listado = facturaService.buscarTodasPorNotas(notas);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<FacturaModel> listado = facturaService.buscarTodasPorNotas(notas);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-numero-comprobante/{numeroComprobante}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorNumeroComprobante(@PathVariable(name = "numeroComprobante") String numeroComprobante) {
        List<FacturaModel> listado = facturaService.buscarTodasPorNumeroComprobante(numeroComprobante);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-numero-comprobante-con-eliminadas/{numeroComprobante}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorNumeroComprobanteConEliminadas(@PathVariable(name = "numeroComprobante") String numeroComprobante) {
        List<FacturaModel> listado = facturaService.buscarTodasPorNumeroComprobanteConEliminadas(numeroComprobante);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-pagada/{pagada}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorPagada(@PathVariable(name = "pagada") String pagada) {
        List<FacturaModel> listado = facturaService.buscarTodasPorPagada(Boolean.valueOf(pagada));
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-pagada-con-eliminadas/{pagada}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorPagadaConEliminadas(@PathVariable(name = "pagada") String pagada) {
        List<FacturaModel> listado = facturaService.buscarTodasPorPagadaConEliminadas(Boolean.valueOf(pagada));
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-razon-social/{razonSocial}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorRazonSocial(@PathVariable(name = "razonSocial") String razonSocial) {
        List<FacturaModel> listado = facturaService.buscarTodasPorRazonSocial(razonSocial);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-razon-social-con-eliminadas/{razonSocial}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorRazonSocialConEliminadas(@PathVariable(name = "razonSocial") String razonSocial) {
        List<FacturaModel> listado = facturaService.buscarTodasPorRazonSocialConEliminadas(razonSocial);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-tipo-comprobante/{tipoComprobante}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorTipoComprobante(@PathVariable(name = "tipoComprobante") String tipoComprobante) {
        List<FacturaModel> listado = facturaService.buscarTodasPorTipoComprobante(tipoComprobante);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-tipo-comprobante-con-eliminadas/{tipoComprobante}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasPorTipoComprobanteConEliminadas(@PathVariable(name = "tipoComprobante") String tipoComprobante) {
        List<FacturaModel> listado = facturaService.buscarTodasPorTipoComprobanteConEliminadas(tipoComprobante);
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<FacturaDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.buscarPorId(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FacturaDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<FacturaDTO>> buscarTodas() {
        List<FacturaModel> listado = facturaService.buscarTodas();
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<FacturaDTO>> buscarTodasConEliminadas() {
        List<FacturaModel> listado = facturaService.buscarTodasConEliminadas();
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        return new ResponseEntity<>(facturas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<FacturaDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<FacturaModel> listado = facturaService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        Slice slice = new SliceImpl(facturas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<FacturaDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<FacturaModel> listado = facturaService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<FacturaDTO> facturas = new ArrayList<>();
        for (FacturaModel factura:listado) {
            facturas.add(facturaMapper.toDto(factura));
        }
        Slice slice = new SliceImpl(facturas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= facturaService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= facturaService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<FacturaDTO> guardar(@Valid @RequestBody FacturaCreation facturaCreation) {
        FacturaModel objeto = facturaService.guardar(facturaCreation);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<FacturaDTO> borrar(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.eliminar(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FacturaDTO> reciclar(@PathVariable(name = "id") Long id) {
        FacturaModel objeto = facturaService.reciclar(id);
        return new ResponseEntity<>(facturaMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        facturaService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

