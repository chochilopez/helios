package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CategoriaGastoMapper;
import gloit.hiperionida.helios.mapper.creation.CategoriaGastoCreation;
import gloit.hiperionida.helios.mapper.dto.CategoriaGastoDTO;
import gloit.hiperionida.helios.model.CategoriaGastoModel;
import gloit.hiperionida.helios.service.implementation.CategoriaGastoServiceImpl;
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
@RequestMapping(value = "/api/categoria-gasto")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CategoriaGastoController extends AbsBaseController {
    private final CategoriaGastoServiceImpl categoriaGastoService;
    private final CategoriaGastoMapper categoriaGastoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el categoriaGasto. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<CategoriaGastoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CategoriaGastoModel objeto = categoriaGastoService.buscarPorId(id);
        return new ResponseEntity<>(categoriaGastoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaGastoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CategoriaGastoModel objeto = categoriaGastoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(categoriaGastoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CategoriaGastoDTO>> buscarTodas() {
        List<CategoriaGastoModel> listado = categoriaGastoService.buscarTodas();
        ArrayList<CategoriaGastoDTO> categoriaGastos = new ArrayList<>();
        for (CategoriaGastoModel categoriaGasto:listado) {
            categoriaGastos.add(categoriaGastoMapper.toDto(categoriaGasto));
        }
        return new ResponseEntity<>(categoriaGastos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CategoriaGastoDTO>> buscarTodasConEliminadas() {
        List<CategoriaGastoModel> listado = categoriaGastoService.buscarTodasConEliminadas();
        ArrayList<CategoriaGastoDTO> categoriaGastos = new ArrayList<>();
        for (CategoriaGastoModel categoriaGasto:listado) {
            categoriaGastos.add(categoriaGastoMapper.toDto(categoriaGasto));
        }
        return new ResponseEntity<>(categoriaGastos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CategoriaGastoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CategoriaGastoModel> listado = categoriaGastoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CategoriaGastoDTO> categoriaGastos = new ArrayList<>();
        for (CategoriaGastoModel categoriaGasto:listado) {
            categoriaGastos.add(categoriaGastoMapper.toDto(categoriaGasto));
        }
        Slice slice = new SliceImpl(categoriaGastos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CategoriaGastoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CategoriaGastoModel> listado = categoriaGastoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CategoriaGastoDTO> categoriaGastos = new ArrayList<>();
        for (CategoriaGastoModel categoriaGasto:listado) {
            categoriaGastos.add(categoriaGastoMapper.toDto(categoriaGasto));
        }
        Slice slice = new SliceImpl(categoriaGastos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= categoriaGastoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= categoriaGastoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaGastoDTO> guardar(@Valid @RequestBody CategoriaGastoCreation categoriaGastoCreation) {
        CategoriaGastoModel objeto = categoriaGastoService.guardar(categoriaGastoCreation);
        return new ResponseEntity<>(categoriaGastoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaGastoDTO> borrar(@PathVariable(name = "id") Long id) {
        CategoriaGastoModel objeto = categoriaGastoService.eliminar(id);
        return new ResponseEntity<>(categoriaGastoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaGastoDTO> reciclar(@PathVariable(name = "id") Long id) {
        CategoriaGastoModel objeto = categoriaGastoService.reciclar(id);
        return new ResponseEntity<>(categoriaGastoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        categoriaGastoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

