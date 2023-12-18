package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CuentaCorrienteClienteMapper;
import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteClienteCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteClienteDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteClienteModel;
import gloit.hiperionida.helios.service.implementation.CuentaCorrienteClienteServiceImpl;
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
@RequestMapping(value = "/api/cuenta-corriente-cliente")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CuentaCorrienteClienteController extends AbsBaseController {
    private final CuentaCorrienteClienteServiceImpl cuentaCorrienteClienteService;
    private final CuentaCorrienteClienteMapper cuentaCorrienteClienteMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el cuentaCorriente. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-cliente-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteClienteDTO>> buscarTodasPorClienteId(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasPorClienteId(id);
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteClienteService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con cliente id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-cliente-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteClienteDTO>> buscarTodasPorClienteIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasPorClienteIdConEliminadas(id);
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteClienteService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con cliente id: " + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-factura-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteClienteDTO>> buscarTodasPorFacturaId(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasPorFacturaId(id);
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteClienteService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con factura id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-factura-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteClienteDTO>> buscarTodasPorFacturaIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasPorFacturaIdConEliminadas(id);
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteClienteService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con factura id: " + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteClienteDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CuentaCorrienteClienteModel objeto = cuentaCorrienteClienteService.buscarPorId(id);
        return new ResponseEntity<>(cuentaCorrienteClienteMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CuentaCorrienteClienteDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CuentaCorrienteClienteModel objeto = cuentaCorrienteClienteService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(cuentaCorrienteClienteMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteClienteDTO>> buscarTodas() {
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodas();
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteClienteDTO>> buscarTodasConEliminadas() {
        List<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasConEliminadas();
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CuentaCorrienteClienteDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        Slice slice = new SliceImpl(cuentaCorrientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<CuentaCorrienteClienteDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CuentaCorrienteClienteModel> listado = cuentaCorrienteClienteService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CuentaCorrienteClienteDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteClienteModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteClienteMapper.toDto(cuentaCorriente));
        }
        Slice slice = new SliceImpl(cuentaCorrientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= cuentaCorrienteClienteService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= cuentaCorrienteClienteService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteClienteDTO> guardar(@Valid @RequestBody CuentaCorrienteClienteCreation cuentaCorrienteClienteCreation) {
        CuentaCorrienteClienteModel objeto = cuentaCorrienteClienteService.guardar(cuentaCorrienteClienteCreation);
        return new ResponseEntity<>(cuentaCorrienteClienteMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteClienteDTO> borrar(@PathVariable(name = "id") Long id) {
        CuentaCorrienteClienteModel objeto = cuentaCorrienteClienteService.eliminar(id);
        return new ResponseEntity<>(cuentaCorrienteClienteMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CuentaCorrienteClienteDTO> reciclar(@PathVariable(name = "id") Long id) {
        CuentaCorrienteClienteModel objeto = cuentaCorrienteClienteService.reciclar(id);
        return new ResponseEntity<>(cuentaCorrienteClienteMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        cuentaCorrienteClienteService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

