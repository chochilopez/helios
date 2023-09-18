package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.RemitoMapper;
import gloit.hiperionida.helios.mapper.creation.RemitoCreation;
import gloit.hiperionida.helios.mapper.dto.RemitoDTO;
import gloit.hiperionida.helios.model.RemitoModel;
import gloit.hiperionida.helios.service.implementation.RemitoServiceImpl;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.controller.AbsBaseController;
import gloit.hiperionida.helios.util.exception.ErrorDTO;
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
@RequestMapping(value = "/api/remito")
@RequiredArgsConstructor
@RestController
@Slf4j
public class RemitoController extends AbsBaseController {
    private final RemitoServiceImpl remitoService;
    private final RemitoMapper remitoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el remito. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<RemitoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        RemitoModel objeto = remitoService.buscarPorId(id);
        return new ResponseEntity<>(remitoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<RemitoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        RemitoModel objeto = remitoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(remitoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<RemitoDTO>> buscarTodas() {
        List<RemitoModel> listado = remitoService.buscarTodas();
        ArrayList<RemitoDTO> remitos = new ArrayList<>();
        for (RemitoModel remito:listado) {
            remitos.add(remitoMapper.toDto(remito));
        }
        return new ResponseEntity<>(remitos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<RemitoDTO>> buscarTodasConEliminadas() {
        List<RemitoModel> listado = remitoService.buscarTodasConEliminadas();
        ArrayList<RemitoDTO> remitos = new ArrayList<>();
        for (RemitoModel remito:listado) {
            remitos.add(remitoMapper.toDto(remito));
        }
        return new ResponseEntity<>(remitos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<RemitoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<RemitoModel> listado = remitoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<RemitoDTO> remitos = new ArrayList<>();
        for (RemitoModel remito:listado) {
            remitos.add(remitoMapper.toDto(remito));
        }
        Slice slice = new SliceImpl(remitos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<RemitoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<RemitoModel> listado = remitoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<RemitoDTO> remitos = new ArrayList<>();
        for (RemitoModel remito:listado) {
            remitos.add(remitoMapper.toDto(remito));
        }
        Slice slice = new SliceImpl(remitos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= remitoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= remitoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<RemitoDTO> guardar(@Valid @RequestBody RemitoCreation remitoCreation) {
        RemitoModel objeto = remitoService.guardar(remitoCreation);
        return new ResponseEntity<>(remitoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<RemitoDTO> borrar(@PathVariable(name = "id") Long id) {
        RemitoModel objeto = remitoService.eliminar(id);
        return new ResponseEntity<>(remitoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<RemitoDTO> reciclar(@PathVariable(name = "id") Long id) {
        RemitoModel objeto = remitoService.reciclar(id);
        return new ResponseEntity<>(remitoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        remitoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

