package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.PresupuestoMapper;
import gloit.hiperionida.helios.mapper.creation.PresupuestoCreation;
import gloit.hiperionida.helios.mapper.dto.PresupuestoDTO;
import gloit.hiperionida.helios.model.PresupuestoModel;
import gloit.hiperionida.helios.service.implementation.PresupuestoServiceImpl;
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
@RequestMapping(value = "/api/presupuesto")
@RequiredArgsConstructor
@RestController
@Slf4j
public class PresupuestoController extends AbsBaseController {
    private final PresupuestoServiceImpl presupuestoService;
    private final PresupuestoMapper presupuestoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el presupuesto. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-ultimo")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PresupuestoDTO> buscarUltimoPresupuesto() {
        return new ResponseEntity<>(presupuestoMapper.toDto(presupuestoService.buscarUltimoPresupuesto()), Helper.httpHeaders("Se devolvio el ultimo Presupuesto."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha/{fecha}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorFechaFecha(@PathVariable(name = "fecha") String fecha) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorFechaFecha(fecha);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-con-eliminadas/{fecha}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorFechaFechaConEliminadas(@PathVariable(name = "fecha") String fecha) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorFechaFechaConEliminadas(fecha);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-nombre/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorCompradorNombre(@PathVariable(name = "nombre") String nombre) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorCompradorNombre(nombre);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorCompradorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorCompradorNombreConEliminadas(nombre);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-direccion/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorOrigenDireccion(@PathVariable(name = "direccion") String direccion) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorOrigenDireccion(direccion);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorOrigenDireccionConEliminadas(@PathVariable(name = "direccion") String direccion) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorOrigenDireccionConEliminadas(direccion);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-direccion/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorDestinoDireccion(@PathVariable(name = "direccion") String direccion) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorDestinoDireccion(direccion);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasPorDestinoDireccionConEliminadas(@PathVariable(name = "direccion") String direccion) {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasPorDestinoDireccionConEliminadas(direccion);
        ArrayList<PresupuestoDTO> fechas = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            fechas.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(fechas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PresupuestoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        PresupuestoModel objeto = presupuestoService.buscarPorId(id);
        return new ResponseEntity<>(presupuestoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PresupuestoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        PresupuestoModel objeto = presupuestoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(presupuestoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodas() {
        List<PresupuestoModel> listado = presupuestoService.buscarTodas();
        ArrayList<PresupuestoDTO> presupuestos = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            presupuestos.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(presupuestos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PresupuestoDTO>> buscarTodasConEliminadas() {
        List<PresupuestoModel> listado = presupuestoService.buscarTodasConEliminadas();
        ArrayList<PresupuestoDTO> presupuestos = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            presupuestos.add(presupuestoMapper.toDto(presupuesto));
        }
        return new ResponseEntity<>(presupuestos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<PresupuestoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<PresupuestoModel> listado = presupuestoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<PresupuestoDTO> presupuestos = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            presupuestos.add(presupuestoMapper.toDto(presupuesto));
        }
        Slice slice = new SliceImpl(presupuestos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<PresupuestoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<PresupuestoModel> listado = presupuestoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<PresupuestoDTO> presupuestos = new ArrayList<>();
        for (PresupuestoModel presupuesto:listado) {
            presupuestos.add(presupuestoMapper.toDto(presupuesto));
        }
        Slice slice = new SliceImpl(presupuestos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= presupuestoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= presupuestoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PresupuestoDTO> guardar(@Valid @RequestBody PresupuestoCreation presupuestoCreation) {
        PresupuestoModel objeto = presupuestoService.guardar(presupuestoCreation);
        return new ResponseEntity<>(presupuestoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PresupuestoDTO> borrar(@PathVariable(name = "id") Long id) {
        PresupuestoModel objeto = presupuestoService.eliminar(id);
        return new ResponseEntity<>(presupuestoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PresupuestoDTO> reciclar(@PathVariable(name = "id") Long id) {
        PresupuestoModel objeto = presupuestoService.reciclar(id);
        return new ResponseEntity<>(presupuestoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        presupuestoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

