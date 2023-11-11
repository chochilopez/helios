package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ClienteMapper;
import gloit.hiperionida.helios.mapper.creation.ClienteCreation;
import gloit.hiperionida.helios.mapper.dto.ClienteDTO;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.service.implementation.ClienteServiceImpl;
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
@RequestMapping(value = "/api/cliente")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ClienteController extends AbsBaseController {
    private final ClienteServiceImpl clienteService;
    private final ClienteMapper clienteMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el cliente. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-direccion/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorDireccion(@PathVariable(name = "direccion") String direccion) {
        List<ClienteModel> listado = clienteService.buscarTodasPorDireccion(direccion);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorDireccionConEliminadas(@PathVariable(name = "direccion") String direccion) {
        List<ClienteModel> listado = clienteService.buscarTodasPorDireccionConEliminadas(direccion);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-email/{email}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorEmail(@PathVariable(name = "email") String email) {
        List<ClienteModel> listado = clienteService.buscarTodasPorEmail(email);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-email-con-eliminadas/{email}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorEmailConEliminadas(@PathVariable(name = "email") String email) {
        List<ClienteModel> listado = clienteService.buscarTodasPorEmailConEliminadas(email);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-identificacion/{identificacion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorIdentificacion(@PathVariable(name = "identificacion") String identificacion) {
        List<ClienteModel> listado = clienteService.buscarTodasPorIdentificacion(identificacion);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-identificacion-con-eliminadas/{identificacion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorIdentificacionConEliminadas(@PathVariable(name = "identificacion") String identificacion) {
        List<ClienteModel> listado = clienteService.buscarTodasPorIdentificacionConEliminadas(identificacion);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-nombre/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorNombre(@PathVariable(name = "nombre") String nombre) {
        List<ClienteModel> listado = clienteService.buscarTodasPorNombre(nombre);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<ClienteModel> listado = clienteService.buscarTodasPorNombreConEliminadas(nombre);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<ClienteModel> listado = clienteService.buscarTodasPorNotas(notas);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<ClienteModel> listado = clienteService.buscarTodasPorNotasConEliminadas(notas);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-telefono/{telefono}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorTelefono(@PathVariable(name = "telefono") String telefono) {
        List<ClienteModel> listado = clienteService.buscarTodasPorTelefono(telefono);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-telefono-con-eliminadas/{telefono}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ArrayList<ClienteDTO>> buscarTodasPorTelefonoConEliminadas(@PathVariable(name = "telefono") String telefono) {
        List<ClienteModel> listado = clienteService.buscarTodasPorTelefonoConEliminadas(telefono);
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ClienteModel objeto = clienteService.buscarPorId(id);
        return new ResponseEntity<>(clienteMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ClienteDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ClienteModel objeto = clienteService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(clienteMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ClienteDTO>> buscarTodas() {
        List<ClienteModel> listado = clienteService.buscarTodas();
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ClienteDTO>> buscarTodasConEliminadas() {
        List<ClienteModel> listado = clienteService.buscarTodasConEliminadas();
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        return new ResponseEntity<>(clientes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ClienteDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ClienteModel> listado = clienteService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        Slice slice = new SliceImpl(clientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<ClienteDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ClienteModel> listado = clienteService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ClienteDTO> clientes = new ArrayList<>();
        for (ClienteModel cliente:listado) {
            clientes.add(clienteMapper.toDto(cliente));
        }
        Slice slice = new SliceImpl(clientes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= clienteService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= clienteService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ClienteDTO> guardar(@Valid @RequestBody ClienteCreation clienteCreation) {
        ClienteModel objeto = clienteService.guardar(clienteCreation);
        return new ResponseEntity<>(clienteMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ClienteDTO> borrar(@PathVariable(name = "id") Long id) {
        ClienteModel objeto = clienteService.eliminar(id);
        return new ResponseEntity<>(clienteMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ClienteDTO> reciclar(@PathVariable(name = "id") Long id) {
        ClienteModel objeto = clienteService.reciclar(id);
        return new ResponseEntity<>(clienteMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        clienteService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

