package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.DireccionMapper;
import gloit.hiperionida.helios.mapper.creation.DireccionCreation;
import gloit.hiperionida.helios.mapper.dto.DireccionDTO;
import gloit.hiperionida.helios.model.CiudadModel;
import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.service.implementation.DireccionServiceImpl;
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
@RequestMapping(value = "/api/direccion")
@RequiredArgsConstructor
@RestController
@Slf4j
public class DireccionController extends AbsBaseController {
    private final DireccionServiceImpl direccionService;
    private final DireccionMapper direccionMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el direccion. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-ciudades")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CiudadModel>> buscarTodasCiudades() {
        List<CiudadModel> listado = direccionService.buscarTodasCiudad();

        return new ResponseEntity<>(listado, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-ciudad/{ciudad}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorCiudad(@PathVariable(name = "ciudad") String ciudad) {
        List<DireccionModel> listado = direccionService.buscarTodasPorCiudad(ciudad);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-ciudad-con-eliminadas/{ciudad}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorCiudadConEliminadas(@PathVariable(name = "ciudad") String ciudad) {
        List<DireccionModel> listado = direccionService.buscarTodasPorCiudadConEliminadas(ciudad);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-direccion/{direcion}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorDireccion(@PathVariable(name = "direcion") String direcion) {
        List<DireccionModel> listado = direccionService.buscarTodasPorDireccion(direcion);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-direccion-con-eliminadas/{direcion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorDireccionConEliminadas(@PathVariable(name = "direcion") String direcion) {
        List<DireccionModel> listado = direccionService.buscarTodasPorDireccionConEliminadas(direcion);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-nombre/{nombre}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorNombre(@PathVariable(name = "nombre") String nombre) {
        List<DireccionModel> listado = direccionService.buscarTodasPorNombre(nombre);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<DireccionModel> listado = direccionService.buscarTodasPorNombreConEliminadas(nombre);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<DireccionModel> listado = direccionService.buscarTodasPorNotas(notas);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<DireccionModel> listado = direccionService.buscarTodasPorNotasConEliminadas(notas);
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<DireccionDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        DireccionModel objeto = direccionService.buscarPorId(id);
        return new ResponseEntity<>(direccionMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DireccionDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        DireccionModel objeto = direccionService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(direccionMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<DireccionDTO>> buscarTodas() {
        List<DireccionModel> listado = direccionService.buscarTodas();
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<DireccionDTO>> buscarTodasConEliminadas() {
        List<DireccionModel> listado = direccionService.buscarTodasConEliminadas();
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        return new ResponseEntity<>(direccions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<DireccionDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<DireccionModel> listado = direccionService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        Slice slice = new SliceImpl(direccions, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<DireccionDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<DireccionModel> listado = direccionService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<DireccionDTO> direccions = new ArrayList<>();
        for (DireccionModel direccion:listado) {
            direccions.add(direccionMapper.toDto(direccion));
        }
        Slice slice = new SliceImpl(direccions, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= direccionService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= direccionService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<DireccionDTO> guardar(@Valid @RequestBody DireccionCreation direccionCreation) {
        DireccionModel objeto = direccionService.guardar(direccionCreation);
        return new ResponseEntity<>(direccionMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<DireccionDTO> borrar(@PathVariable(name = "id") Long id) {
        DireccionModel objeto = direccionService.eliminar(id);
        return new ResponseEntity<>(direccionMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<DireccionDTO> reciclar(@PathVariable(name = "id") Long id) {
        DireccionModel objeto = direccionService.reciclar(id);
        return new ResponseEntity<>(direccionMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        direccionService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

