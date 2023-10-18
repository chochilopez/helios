package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ConciliacionBancoMapper;
import gloit.hiperionida.helios.mapper.creation.ConciliacionBancoCreation;
import gloit.hiperionida.helios.mapper.dto.ConciliacionBancoDTO;
import gloit.hiperionida.helios.model.ConciliacionBancoModel;
import gloit.hiperionida.helios.service.implementation.ConciliacionBancoServiceImpl;
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
@RequestMapping(value = "/api/conciliacion-banco")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ConciliacionBancoController extends AbsBaseController {
    private final ConciliacionBancoServiceImpl conciliacionBancoService;
    private final ConciliacionBancoMapper conciliacionBancoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el conciliacionBanco. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConciliacionBancoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ConciliacionBancoModel objeto = conciliacionBancoService.buscarPorId(id);
        return new ResponseEntity<>(conciliacionBancoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConciliacionBancoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ConciliacionBancoModel objeto = conciliacionBancoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(conciliacionBancoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ConciliacionBancoDTO>> buscarTodas() {
        List<ConciliacionBancoModel> listado = conciliacionBancoService.buscarTodas();
        ArrayList<ConciliacionBancoDTO> conciliacionBancos = new ArrayList<>();
        for (ConciliacionBancoModel conciliacionBanco:listado) {
            conciliacionBancos.add(conciliacionBancoMapper.toDto(conciliacionBanco));
        }
        return new ResponseEntity<>(conciliacionBancos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ConciliacionBancoDTO>> buscarTodasConEliminadas() {
        List<ConciliacionBancoModel> listado = conciliacionBancoService.buscarTodasConEliminadas();
        ArrayList<ConciliacionBancoDTO> conciliacionBancos = new ArrayList<>();
        for (ConciliacionBancoModel conciliacionBanco:listado) {
            conciliacionBancos.add(conciliacionBancoMapper.toDto(conciliacionBanco));
        }
        return new ResponseEntity<>(conciliacionBancos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ConciliacionBancoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ConciliacionBancoModel> listado = conciliacionBancoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ConciliacionBancoDTO> conciliacionBancos = new ArrayList<>();
        for (ConciliacionBancoModel conciliacionBanco:listado) {
            conciliacionBancos.add(conciliacionBancoMapper.toDto(conciliacionBanco));
        }
        Slice slice = new SliceImpl(conciliacionBancos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<ConciliacionBancoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ConciliacionBancoModel> listado = conciliacionBancoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ConciliacionBancoDTO> conciliacionBancos = new ArrayList<>();
        for (ConciliacionBancoModel conciliacionBanco:listado) {
            conciliacionBancos.add(conciliacionBancoMapper.toDto(conciliacionBanco));
        }
        Slice slice = new SliceImpl(conciliacionBancos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= conciliacionBancoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= conciliacionBancoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConciliacionBancoDTO> guardar(@Valid @RequestBody ConciliacionBancoCreation conciliacionBancoCreation) {
        ConciliacionBancoModel objeto = conciliacionBancoService.guardar(conciliacionBancoCreation);
        return new ResponseEntity<>(conciliacionBancoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ConciliacionBancoDTO> borrar(@PathVariable(name = "id") Long id) {
        ConciliacionBancoModel objeto = conciliacionBancoService.eliminar(id);
        return new ResponseEntity<>(conciliacionBancoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ConciliacionBancoDTO> reciclar(@PathVariable(name = "id") Long id) {
        ConciliacionBancoModel objeto = conciliacionBancoService.reciclar(id);
        return new ResponseEntity<>(conciliacionBancoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        conciliacionBancoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

