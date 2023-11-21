package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CuentaCorrienteMapper;
import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteModel;
import gloit.hiperionida.helios.service.implementation.CuentaCorrienteServiceImpl;
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
@RequestMapping(value = "/api/cuenta-corriente")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CuentaCorrienteController extends AbsBaseController {
    private final CuentaCorrienteServiceImpl cuentaCorrienteService;
    private final CuentaCorrienteMapper cuentaCorrienteMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el cuentaCorriente. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-cliente-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteDTO>> buscarTodas(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteModel> listado = cuentaCorrienteService.buscarTodas();
        ArrayList<CuentaCorrienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con cliente id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-cliente-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteDTO>> buscarTodasConEliminadas(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteModel> listado = cuentaCorrienteService.buscarTodasConEliminadas();
        ArrayList<CuentaCorrienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con cliente id: " + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CuentaCorrienteModel objeto = cuentaCorrienteService.buscarPorId(id);
        return new ResponseEntity<>(cuentaCorrienteMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CuentaCorrienteDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CuentaCorrienteModel objeto = cuentaCorrienteService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(cuentaCorrienteMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteDTO>> buscarTodas() {
        List<CuentaCorrienteModel> listado = cuentaCorrienteService.buscarTodas();
        ArrayList<CuentaCorrienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteDTO>> buscarTodasConEliminadas() {
        List<CuentaCorrienteModel> listado = cuentaCorrienteService.buscarTodasConEliminadas();
        ArrayList<CuentaCorrienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CuentaCorrienteDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CuentaCorrienteModel> listado = cuentaCorrienteService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CuentaCorrienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteMapper.toDto(cuentaCorriente));
        }
        Slice slice = new SliceImpl(cuentaCorrientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<CuentaCorrienteDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CuentaCorrienteModel> listado = cuentaCorrienteService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CuentaCorrienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteMapper.toDto(cuentaCorriente));
        }
        Slice slice = new SliceImpl(cuentaCorrientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= cuentaCorrienteService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= cuentaCorrienteService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteDTO> guardar(@Valid @RequestBody CuentaCorrienteCreation cuentaCorrienteCreation) {
        CuentaCorrienteModel objeto = cuentaCorrienteService.guardar(cuentaCorrienteCreation);
        return new ResponseEntity<>(cuentaCorrienteMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteDTO> borrar(@PathVariable(name = "id") Long id) {
        CuentaCorrienteModel objeto = cuentaCorrienteService.eliminar(id);
        return new ResponseEntity<>(cuentaCorrienteMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CuentaCorrienteDTO> reciclar(@PathVariable(name = "id") Long id) {
        CuentaCorrienteModel objeto = cuentaCorrienteService.reciclar(id);
        return new ResponseEntity<>(cuentaCorrienteMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        cuentaCorrienteService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

