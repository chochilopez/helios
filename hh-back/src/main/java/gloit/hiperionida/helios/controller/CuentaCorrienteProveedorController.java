package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CuentaCorrienteProveedorMapper;
import gloit.hiperionida.helios.mapper.creation.CuentaCorrienteProveedorCreation;
import gloit.hiperionida.helios.mapper.dto.CuentaCorrienteProveedorDTO;
import gloit.hiperionida.helios.model.CuentaCorrienteProveedorModel;
import gloit.hiperionida.helios.service.implementation.CuentaCorrienteProveedorServiceImpl;
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
@RequestMapping(value = "/api/cuenta-corriente-proveedor")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CuentaCorrienteProveedorController extends AbsBaseController {
    private final CuentaCorrienteProveedorServiceImpl cuentaCorrienteProveedorService;
    private final CuentaCorrienteProveedorMapper cuentaCorrienteProveedorMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el cuentaCorriente. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-proveedor-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteProveedorDTO>> buscarTodasPorProveedorId(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasPorProveedorId(id);
        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteProveedorService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con proveedor id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-proveedor-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteProveedorDTO>> buscarTodasPorProveedorIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasPorProveedorIdConEliminadas(id);
        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrienteProveedorService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con proveedor id: " + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

//    @GetMapping(value = "/buscar-todas-por-factura-id/{id}")
//    @PreAuthorize("hasAuthority('USUARIO')")
//    public ResponseEntity<List<CuentaCorrienteProveedorDTO>> buscarTodasPorFacturaId(@PathVariable(name = "id") Long id) {
//        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasPorFacturaId(id);
//        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
//        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
//            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
//        }
//        return new ResponseEntity<>(cuentaCorrienteProveedorService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con factura id: " + id + "."), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/buscar-todas-por-factura-id-con-eliminadas/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<List<CuentaCorrienteProveedorDTO>> buscarTodasPorFacturaIdConEliminadas(@PathVariable(name = "id") Long id) {
//        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasPorFacturaIdConEliminadas(id);
//        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
//        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
//            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
//        }
//        return new ResponseEntity<>(cuentaCorrienteProveedorService.calcularSaldo(cuentaCorrientes), Helper.httpHeaders("Se encontraron " + listado.size() + " entidades con factura id: " + id + ", incluidas las eliminadas."), HttpStatus.OK);
//    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteProveedorDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CuentaCorrienteProveedorModel objeto = cuentaCorrienteProveedorService.buscarPorId(id);
        return new ResponseEntity<>(cuentaCorrienteProveedorMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CuentaCorrienteProveedorDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CuentaCorrienteProveedorModel objeto = cuentaCorrienteProveedorService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(cuentaCorrienteProveedorMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CuentaCorrienteProveedorDTO>> buscarTodas() {
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodas();
        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CuentaCorrienteProveedorDTO>> buscarTodasConEliminadas() {
        List<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasConEliminadas();
        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
        }
        return new ResponseEntity<>(cuentaCorrientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CuentaCorrienteProveedorDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
        }
        Slice slice = new SliceImpl(cuentaCorrientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<CuentaCorrienteProveedorDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CuentaCorrienteProveedorModel> listado = cuentaCorrienteProveedorService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CuentaCorrienteProveedorDTO> cuentaCorrientes = new ArrayList<>();
        for (CuentaCorrienteProveedorModel cuentaCorriente:listado) {
            cuentaCorrientes.add(cuentaCorrienteProveedorMapper.toDto(cuentaCorriente));
        }
        Slice slice = new SliceImpl(cuentaCorrientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= cuentaCorrienteProveedorService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= cuentaCorrienteProveedorService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteProveedorDTO> guardar(@Valid @RequestBody CuentaCorrienteProveedorCreation cuentaCorrienteProveedorCreation) {
        CuentaCorrienteProveedorModel objeto = cuentaCorrienteProveedorService.guardar(cuentaCorrienteProveedorCreation);
        return new ResponseEntity<>(cuentaCorrienteProveedorMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CuentaCorrienteProveedorDTO> borrar(@PathVariable(name = "id") Long id) {
        CuentaCorrienteProveedorModel objeto = cuentaCorrienteProveedorService.eliminar(id);
        return new ResponseEntity<>(cuentaCorrienteProveedorMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CuentaCorrienteProveedorDTO> reciclar(@PathVariable(name = "id") Long id) {
        CuentaCorrienteProveedorModel objeto = cuentaCorrienteProveedorService.reciclar(id);
        return new ResponseEntity<>(cuentaCorrienteProveedorMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        cuentaCorrienteProveedorService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

