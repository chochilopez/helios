package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ViajeMapper;
import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.mapper.dto.ViajeDTO;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.service.implementation.ViajeServiceImpl;
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
@RequestMapping(value = "/api/viaje")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ViajeController extends AbsBaseController {
    private final ViajeServiceImpl viajeService;
    private final ViajeMapper viajeMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el viaje. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-chofer-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorChoferId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorChoferId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-chofer-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorChoferIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorChoferIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-nombre/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorNombre(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorNombre(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorNombreConEliminadas(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-nombre/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorNombre(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorNombre(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorNombreConEliminadas(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-direccion/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenDireccion(@PathVariable(name = "nombre") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenDireccion(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenDireccionConEliminadas(@PathVariable(name = "nombre") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenDireccionConEliminadas(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-direccion/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoDireccion(@PathVariable(name = "nombre") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoDireccion(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoDireccionConEliminadas(@PathVariable(name = "nombre") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoDireccionConEliminadas(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<ViajeDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ViajeModel objeto = viajeService.buscarPorId(id);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ViajeDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ViajeModel objeto = viajeService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodas() {
        List<ViajeModel> listado = viajeService.buscarTodas();
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasConEliminadas() {
        List<ViajeModel> listado = viajeService.buscarTodasConEliminadas();
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ViajeDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ViajeModel> listado = viajeService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        Slice slice = new SliceImpl(viajes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ViajeDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ViajeModel> listado = viajeService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        Slice slice = new SliceImpl(viajes, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= viajeService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= viajeService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ViajeDTO> guardar(@Valid @RequestBody ViajeCreation viajeCreation) {
        ViajeModel objeto = viajeService.guardar(viajeCreation);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ViajeDTO> borrar(@PathVariable(name = "id") Long id) {
        ViajeModel objeto = viajeService.eliminar(id);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ViajeDTO> reciclar(@PathVariable(name = "id") Long id) {
        ViajeModel objeto = viajeService.reciclar(id);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        viajeService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

