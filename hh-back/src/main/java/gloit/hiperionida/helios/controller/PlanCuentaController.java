package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.PlanCuentaMapper;
import gloit.hiperionida.helios.mapper.creation.PlanCuentaCreation;
import gloit.hiperionida.helios.mapper.dto.PlanCuentaDTO;
import gloit.hiperionida.helios.model.PlanCuentaModel;
import gloit.hiperionida.helios.service.implementation.PlanCuentaServiceImpl;
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
@RequestMapping(value = "/api/plan-cuenta")
@RequiredArgsConstructor
@RestController
@Slf4j
public class PlanCuentaController extends AbsBaseController {
    private final PlanCuentaServiceImpl planCuentaService;
    private final PlanCuentaMapper planCuentaMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el planCuenta. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PlanCuentaDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        PlanCuentaModel objeto = planCuentaService.buscarPorId(id);
        return new ResponseEntity<>(planCuentaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PlanCuentaDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        PlanCuentaModel objeto = planCuentaService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(planCuentaMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<PlanCuentaDTO>> buscarTodas() {
        List<PlanCuentaModel> listado = planCuentaService.buscarTodas();
        ArrayList<PlanCuentaDTO> planCuentas = new ArrayList<>();
        for (PlanCuentaModel planCuenta:listado) {
            planCuentas.add(planCuentaMapper.toDto(planCuenta));
        }
        return new ResponseEntity<>(planCuentas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<PlanCuentaDTO>> buscarTodasConEliminadas() {
        List<PlanCuentaModel> listado = planCuentaService.buscarTodasConEliminadas();
        ArrayList<PlanCuentaDTO> planCuentas = new ArrayList<>();
        for (PlanCuentaModel planCuenta:listado) {
            planCuentas.add(planCuentaMapper.toDto(planCuenta));
        }
        return new ResponseEntity<>(planCuentas, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<PlanCuentaDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<PlanCuentaModel> listado = planCuentaService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<PlanCuentaDTO> planCuentas = new ArrayList<>();
        for (PlanCuentaModel planCuenta:listado) {
            planCuentas.add(planCuentaMapper.toDto(planCuenta));
        }
        Slice slice = new SliceImpl(planCuentas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<PlanCuentaDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<PlanCuentaModel> listado = planCuentaService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<PlanCuentaDTO> planCuentas = new ArrayList<>();
        for (PlanCuentaModel planCuenta:listado) {
            planCuentas.add(planCuentaMapper.toDto(planCuenta));
        }
        Slice slice = new SliceImpl(planCuentas, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= planCuentaService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= planCuentaService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PlanCuentaDTO> guardar(@Valid @RequestBody PlanCuentaCreation planCuentaCreation) {
        PlanCuentaModel objeto = planCuentaService.guardar(planCuentaCreation);
        return new ResponseEntity<>(planCuentaMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<PlanCuentaDTO> borrar(@PathVariable(name = "id") Long id) {
        PlanCuentaModel objeto = planCuentaService.eliminar(id);
        return new ResponseEntity<>(planCuentaMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<PlanCuentaDTO> reciclar(@PathVariable(name = "id") Long id) {
        PlanCuentaModel objeto = planCuentaService.reciclar(id);
        return new ResponseEntity<>(planCuentaMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        planCuentaService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

