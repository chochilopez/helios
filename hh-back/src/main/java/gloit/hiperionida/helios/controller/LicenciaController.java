package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.LicenciaMapper;
import gloit.hiperionida.helios.mapper.creation.LicenciaCreation;
import gloit.hiperionida.helios.mapper.dto.LicenciaDTO;
import gloit.hiperionida.helios.model.LicenciaModel;
import gloit.hiperionida.helios.service.implementation.LicenciaServiceImpl;
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
@RequestMapping(value = "/api/licencia")
@RequiredArgsConstructor
@RestController
@Slf4j
public class LicenciaController extends AbsBaseController {
    private final LicenciaServiceImpl licenciaService;
    private final LicenciaMapper licenciaMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el licencia. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<LicenciaDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        LicenciaModel objeto = licenciaService.buscarPorId(id);
        return new ResponseEntity<>(licenciaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LicenciaDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        LicenciaModel objeto = licenciaService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(licenciaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<LicenciaDTO>> buscarTodas() {
        List<LicenciaModel> listado = licenciaService.buscarTodas();
        ArrayList<LicenciaDTO> licencias = new ArrayList<>();
        for (LicenciaModel licencia:listado) {
            licencias.add(licenciaMapper.toDto(licencia));
        }
        return new ResponseEntity<>(licencias, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<LicenciaDTO>> buscarTodasConEliminadas() {
        List<LicenciaModel> listado = licenciaService.buscarTodasConEliminadas();
        ArrayList<LicenciaDTO> licencias = new ArrayList<>();
        for (LicenciaModel licencia:listado) {
            licencias.add(licenciaMapper.toDto(licencia));
        }
        return new ResponseEntity<>(licencias, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<LicenciaDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<LicenciaModel> listado = licenciaService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<LicenciaDTO> licencias = new ArrayList<>();
        for (LicenciaModel licencia:listado) {
            licencias.add(licenciaMapper.toDto(licencia));
        }
        Slice slice = new SliceImpl(licencias, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<LicenciaDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<LicenciaModel> listado = licenciaService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<LicenciaDTO> licencias = new ArrayList<>();
        for (LicenciaModel licencia:listado) {
            licencias.add(licenciaMapper.toDto(licencia));
        }
        Slice slice = new SliceImpl(licencias, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= licenciaService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= licenciaService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LicenciaDTO> guardar(@Valid @RequestBody LicenciaCreation licenciaCreation) {
        LicenciaModel objeto = licenciaService.guardar(licenciaCreation);
        return new ResponseEntity<>(licenciaMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LicenciaDTO> borrar(@PathVariable(name = "id") Long id) {
        LicenciaModel objeto = licenciaService.eliminar(id);
        return new ResponseEntity<>(licenciaMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<LicenciaDTO> reciclar(@PathVariable(name = "id") Long id) {
        LicenciaModel objeto = licenciaService.reciclar(id);
        return new ResponseEntity<>(licenciaMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        licenciaService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

