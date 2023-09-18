package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CategoriaViajeMapper;
import gloit.hiperionida.helios.mapper.creation.CategoriaViajeCreation;
import gloit.hiperionida.helios.mapper.dto.CategoriaViajeDTO;
import gloit.hiperionida.helios.model.CategoriaViajeModel;
import gloit.hiperionida.helios.service.implementation.CategoriaViajeServiceImpl;
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
@RequestMapping(value = "/api/categoria-viaje")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CategoriaViajeController extends AbsBaseController {
    private final CategoriaViajeServiceImpl categoriaViajeService;
    private final CategoriaViajeMapper categoriaViajeMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el categoriaViaje. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<CategoriaViajeDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CategoriaViajeModel objeto = categoriaViajeService.buscarPorId(id);
        return new ResponseEntity<>(categoriaViajeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaViajeDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CategoriaViajeModel objeto = categoriaViajeService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(categoriaViajeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CategoriaViajeDTO>> buscarTodas() {
        List<CategoriaViajeModel> listado = categoriaViajeService.buscarTodas();
        ArrayList<CategoriaViajeDTO> categoriaViajes = new ArrayList<>();
        for (CategoriaViajeModel categoriaViaje:listado) {
            categoriaViajes.add(categoriaViajeMapper.toDto(categoriaViaje));
        }
        return new ResponseEntity<>(categoriaViajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CategoriaViajeDTO>> buscarTodasConEliminadas() {
        List<CategoriaViajeModel> listado = categoriaViajeService.buscarTodasConEliminadas();
        ArrayList<CategoriaViajeDTO> categoriaViajes = new ArrayList<>();
        for (CategoriaViajeModel categoriaViaje:listado) {
            categoriaViajes.add(categoriaViajeMapper.toDto(categoriaViaje));
        }
        return new ResponseEntity<>(categoriaViajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CategoriaViajeDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CategoriaViajeModel> listado = categoriaViajeService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CategoriaViajeDTO> categoriaViajes = new ArrayList<>();
        for (CategoriaViajeModel categoriaViaje:listado) {
            categoriaViajes.add(categoriaViajeMapper.toDto(categoriaViaje));
        }
        Slice slice = new SliceImpl(categoriaViajes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CategoriaViajeDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CategoriaViajeModel> listado = categoriaViajeService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CategoriaViajeDTO> categoriaViajes = new ArrayList<>();
        for (CategoriaViajeModel categoriaViaje:listado) {
            categoriaViajes.add(categoriaViajeMapper.toDto(categoriaViaje));
        }
        Slice slice = new SliceImpl(categoriaViajes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= categoriaViajeService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= categoriaViajeService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaViajeDTO> guardar(@Valid @RequestBody CategoriaViajeCreation categoriaViajeCreation) {
        CategoriaViajeModel objeto = categoriaViajeService.guardar(categoriaViajeCreation);
        return new ResponseEntity<>(categoriaViajeMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaViajeDTO> borrar(@PathVariable(name = "id") Long id) {
        CategoriaViajeModel objeto = categoriaViajeService.eliminar(id);
        return new ResponseEntity<>(categoriaViajeMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CategoriaViajeDTO> reciclar(@PathVariable(name = "id") Long id) {
        CategoriaViajeModel objeto = categoriaViajeService.reciclar(id);
        return new ResponseEntity<>(categoriaViajeMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        categoriaViajeService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

