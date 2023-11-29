package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CombustibleMapper;
import gloit.hiperionida.helios.mapper.creation.CombustibleCreation;
import gloit.hiperionida.helios.mapper.dto.CombustibleDTO;
import gloit.hiperionida.helios.mapper.dto.CombustibleDTO;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.model.CombustibleModel;
import gloit.hiperionida.helios.service.implementation.CombustibleServiceImpl;
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
@RequestMapping(value = "/api/combustible")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CombustibleController extends AbsBaseController {
    private final CombustibleServiceImpl combustibleService;
    private final CombustibleMapper combustibleMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el combustible. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorCamionId(@PathVariable(name = "id") Long id) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorCamionId(id);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorCamionIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorCamionIdConEliminadas(id);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-conductor-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorConductorId(@PathVariable(name = "id") Long id) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorConductorId(id);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-conductor-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorConductorIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorConductorIdConEliminadas(id);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-entre-fechas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorFecha(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorFecha(inicio, fin);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-entre-fechas-con-eliminadas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorFechaConEliminadas(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorFechaConEliminadas(inicio, fin);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorNotasContainingIgnoreCase(@PathVariable(name = "notas") String notas) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorNotasContainingIgnoreCase(notas);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorNotasContainingIgnoreCaseConEliminadas(@PathVariable(name = "notas") String notas) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorNotasContainingIgnoreCaseConEliminadas(notas);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-proveedor-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorProveedorId(@PathVariable(name = "id") Long id) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorProveedorId(id);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-proveedor-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasPorProveedorIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<CombustibleModel> listado = combustibleService.buscarTodasPorProveedorIdConEliminadas(id);
        ArrayList<CombustibleDTO> viajes = new ArrayList<>();
        for (CombustibleModel viaje:listado) {
            viajes.add(combustibleMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CombustibleDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CombustibleModel objeto = combustibleService.buscarPorId(id);
        return new ResponseEntity<>(combustibleMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CombustibleDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CombustibleModel objeto = combustibleService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(combustibleMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodas() {
        List<CombustibleModel> listado = combustibleService.buscarTodas();
        ArrayList<CombustibleDTO> combustibles = new ArrayList<>();
        for (CombustibleModel combustible:listado) {
            combustibles.add(combustibleMapper.toDto(combustible));
        }
        return new ResponseEntity<>(combustibles, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CombustibleDTO>> buscarTodasConEliminadas() {
        List<CombustibleModel> listado = combustibleService.buscarTodasConEliminadas();
        ArrayList<CombustibleDTO> combustibles = new ArrayList<>();
        for (CombustibleModel combustible:listado) {
            combustibles.add(combustibleMapper.toDto(combustible));
        }
        return new ResponseEntity<>(combustibles, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CombustibleDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CombustibleModel> listado = combustibleService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CombustibleDTO> combustibles = new ArrayList<>();
        for (CombustibleModel combustible:listado) {
            combustibles.add(combustibleMapper.toDto(combustible));
        }
        Slice slice = new SliceImpl(combustibles, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<CombustibleDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CombustibleModel> listado = combustibleService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CombustibleDTO> combustibles = new ArrayList<>();
        for (CombustibleModel combustible:listado) {
            combustibles.add(combustibleMapper.toDto(combustible));
        }
        Slice slice = new SliceImpl(combustibles, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= combustibleService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= combustibleService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CombustibleDTO> guardar(@Valid @RequestBody CombustibleCreation combustibleCreation) {
        CombustibleModel objeto = combustibleService.guardar(combustibleCreation);
        return new ResponseEntity<>(combustibleMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CombustibleDTO> borrar(@PathVariable(name = "id") Long id) {
        CombustibleModel objeto = combustibleService.eliminar(id);
        return new ResponseEntity<>(combustibleMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CombustibleDTO> reciclar(@PathVariable(name = "id") Long id) {
        CombustibleModel objeto = combustibleService.reciclar(id);
        return new ResponseEntity<>(combustibleMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        combustibleService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

