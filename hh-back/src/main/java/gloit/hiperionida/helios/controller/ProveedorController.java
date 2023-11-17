package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ProveedorMapper;
import gloit.hiperionida.helios.mapper.creation.ProveedorCreation;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.mapper.dto.ProveedorDTO;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.service.implementation.ProveedorServiceImpl;
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
@RequestMapping(value = "/api/proveedor")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ProveedorController extends AbsBaseController {
    private final ProveedorServiceImpl proveedorService;
    private final ProveedorMapper proveedorMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el proveedor. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-direccion/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorDireccion(@PathVariable(name = "direccion") String direccion) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorDireccion(direccion);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorDireccionConEliminadas(@PathVariable(name = "direccion") String direccion) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorDireccionConEliminadas(direccion);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-email/{email}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorEmail(@PathVariable(name = "email") String email) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorEmail(email);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-email-con-eliminadas/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorEmailConEliminadas(@PathVariable(name = "email") String email) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorEmailConEliminadas(email);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-identificacion/{identificacion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorIdentificacion(@PathVariable(name = "identificacion") String identificacion) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorIdentificacion(identificacion);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-identificacion-con-eliminadas/{identificacion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorIdentificacionConEliminadas(@PathVariable(name = "identificacion") String identificacion) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorIdentificacionConEliminadas(identificacion);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-nombre/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorNombre(@PathVariable(name = "nombre") String nombre) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorNombre(nombre);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorNombreConEliminadas(nombre);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorNotas(notas);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorNotasConEliminadas(notas);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-telefono/{telefono}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorTelefono(@PathVariable(name = "telefono") String telefono) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorTelefono(telefono);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-telefono-con-eliminadas/{telefono}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ProveedorDTO>> buscarTodasPorTelefonoConEliminadas(@PathVariable(name = "telefono") String telefono) {
        List<ProveedorModel> listado = proveedorService.buscarTodasPorTelefonoConEliminadas(telefono);
        ArrayList<ProveedorDTO> proveedores = new ArrayList<>();
        for (ProveedorModel cliente:listado) {
            proveedores.add(proveedorMapper.toDto(cliente));
        }
        return new ResponseEntity<>(proveedores, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ProveedorDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ProveedorModel objeto = proveedorService.buscarPorId(id);
        return new ResponseEntity<>(proveedorMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProveedorDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ProveedorModel objeto = proveedorService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(proveedorMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ProveedorDTO>> buscarTodas() {
        List<ProveedorModel> listado = proveedorService.buscarTodas();
        ArrayList<ProveedorDTO> proveedors = new ArrayList<>();
        for (ProveedorModel proveedor:listado) {
            proveedors.add(proveedorMapper.toDto(proveedor));
        }
        return new ResponseEntity<>(proveedors, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ProveedorDTO>> buscarTodasConEliminadas() {
        List<ProveedorModel> listado = proveedorService.buscarTodasConEliminadas();
        ArrayList<ProveedorDTO> proveedors = new ArrayList<>();
        for (ProveedorModel proveedor:listado) {
            proveedors.add(proveedorMapper.toDto(proveedor));
        }
        return new ResponseEntity<>(proveedors, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ProveedorDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ProveedorModel> listado = proveedorService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ProveedorDTO> proveedors = new ArrayList<>();
        for (ProveedorModel proveedor:listado) {
            proveedors.add(proveedorMapper.toDto(proveedor));
        }
        Slice slice = new SliceImpl(proveedors, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<ProveedorDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ProveedorModel> listado = proveedorService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ProveedorDTO> proveedors = new ArrayList<>();
        for (ProveedorModel proveedor:listado) {
            proveedors.add(proveedorMapper.toDto(proveedor));
        }
        Slice slice = new SliceImpl(proveedors, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= proveedorService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= proveedorService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ProveedorDTO> guardar(@Valid @RequestBody ProveedorCreation proveedorCreation) {
        ProveedorModel objeto = proveedorService.guardar(proveedorCreation);
        return new ResponseEntity<>(proveedorMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ProveedorDTO> borrar(@PathVariable(name = "id") Long id) {
        ProveedorModel objeto = proveedorService.eliminar(id);
        return new ResponseEntity<>(proveedorMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ProveedorDTO> reciclar(@PathVariable(name = "id") Long id) {
        ProveedorModel objeto = proveedorService.reciclar(id);
        return new ResponseEntity<>(proveedorMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        proveedorService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

