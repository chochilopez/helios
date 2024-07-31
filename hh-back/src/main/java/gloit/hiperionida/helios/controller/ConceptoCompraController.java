package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ConceptoCompraMapper;
import gloit.hiperionida.helios.mapper.creation.ConceptoCompraCreation;
import gloit.hiperionida.helios.mapper.dto.ConceptoCompraDTO;
import gloit.hiperionida.helios.model.ConceptoCompraModel;
import gloit.hiperionida.helios.service.implementation.ConceptoCompraServiceImpl;
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
@RequestMapping(value = "/api/concepto-compra")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ConceptoCompraController extends AbsBaseController {
    private final ConceptoCompraServiceImpl conceptoCompraService;
    private final ConceptoCompraMapper conceptoCompraMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el conceptoCompra. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConceptoCompraDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ConceptoCompraModel objeto = conceptoCompraService.buscarPorId(id);
        return new ResponseEntity<>(conceptoCompraMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConceptoCompraDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ConceptoCompraModel objeto = conceptoCompraService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(conceptoCompraMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ConceptoCompraDTO>> buscarTodas() {
        List<ConceptoCompraModel> listado = conceptoCompraService.buscarTodas();
        ArrayList<ConceptoCompraDTO> conceptoCompras = new ArrayList<>();
        for (ConceptoCompraModel conceptoCompra:listado) {
            conceptoCompras.add(conceptoCompraMapper.toDto(conceptoCompra));
        }
        return new ResponseEntity<>(conceptoCompras, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ConceptoCompraDTO>> buscarTodasConEliminadas() {
        List<ConceptoCompraModel> listado = conceptoCompraService.buscarTodasConEliminadas();
        ArrayList<ConceptoCompraDTO> conceptoCompras = new ArrayList<>();
        for (ConceptoCompraModel conceptoCompra:listado) {
            conceptoCompras.add(conceptoCompraMapper.toDto(conceptoCompra));
        }
        return new ResponseEntity<>(conceptoCompras, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ConceptoCompraDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ConceptoCompraModel> listado = conceptoCompraService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ConceptoCompraDTO> conceptoCompras = new ArrayList<>();
        for (ConceptoCompraModel conceptoCompra:listado) {
            conceptoCompras.add(conceptoCompraMapper.toDto(conceptoCompra));
        }
        Slice slice = new SliceImpl(conceptoCompras, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<ConceptoCompraDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ConceptoCompraModel> listado = conceptoCompraService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ConceptoCompraDTO> conceptoCompras = new ArrayList<>();
        for (ConceptoCompraModel conceptoCompra:listado) {
            conceptoCompras.add(conceptoCompraMapper.toDto(conceptoCompra));
        }
        Slice slice = new SliceImpl(conceptoCompras, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= conceptoCompraService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= conceptoCompraService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConceptoCompraDTO> guardar(@Valid @RequestBody ConceptoCompraCreation conceptoCompraCreation) {
        ConceptoCompraModel objeto = conceptoCompraService.guardar(conceptoCompraCreation);
        return new ResponseEntity<>(conceptoCompraMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConceptoCompraDTO> borrar(@PathVariable(name = "id") Long id) {
        ConceptoCompraModel objeto = conceptoCompraService.eliminar(id);
        return new ResponseEntity<>(conceptoCompraMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConceptoCompraDTO> reciclar(@PathVariable(name = "id") Long id) {
        ConceptoCompraModel objeto = conceptoCompraService.reciclar(id);
        return new ResponseEntity<>(conceptoCompraMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        conceptoCompraService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}
