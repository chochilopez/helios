package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.NeumaticoMapper;
import gloit.hiperionida.helios.mapper.creation.NeumaticoCreation;
import gloit.hiperionida.helios.mapper.dto.NeumaticoDTO;
import gloit.hiperionida.helios.model.NeumaticoModel;
import gloit.hiperionida.helios.service.implementation.NeumaticoServiceImpl;
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
@RequestMapping(value = "/api/neumatico")
@RequiredArgsConstructor
@RestController
@Slf4j
public class NeumaticoController extends AbsBaseController {
    private final NeumaticoServiceImpl neumaticoService;
    private final NeumaticoMapper neumaticoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el neumatico. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<NeumaticoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        NeumaticoModel objeto = neumaticoService.buscarPorId(id);
        return new ResponseEntity<>(neumaticoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<NeumaticoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        NeumaticoModel objeto = neumaticoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(neumaticoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<NeumaticoDTO>> buscarTodas() {
        List<NeumaticoModel> listado = neumaticoService.buscarTodas();
        ArrayList<NeumaticoDTO> neumaticos = new ArrayList<>();
        for (NeumaticoModel neumatico:listado) {
            neumaticos.add(neumaticoMapper.toDto(neumatico));
        }
        return new ResponseEntity<>(neumaticos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<NeumaticoDTO>> buscarTodasConEliminadas() {
        List<NeumaticoModel> listado = neumaticoService.buscarTodasConEliminadas();
        ArrayList<NeumaticoDTO> neumaticos = new ArrayList<>();
        for (NeumaticoModel neumatico:listado) {
            neumaticos.add(neumaticoMapper.toDto(neumatico));
        }
        return new ResponseEntity<>(neumaticos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<NeumaticoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<NeumaticoModel> listado = neumaticoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<NeumaticoDTO> neumaticos = new ArrayList<>();
        for (NeumaticoModel neumatico:listado) {
            neumaticos.add(neumaticoMapper.toDto(neumatico));
        }
        Slice slice = new SliceImpl(neumaticos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<NeumaticoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<NeumaticoModel> listado = neumaticoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<NeumaticoDTO> neumaticos = new ArrayList<>();
        for (NeumaticoModel neumatico:listado) {
            neumaticos.add(neumaticoMapper.toDto(neumatico));
        }
        Slice slice = new SliceImpl(neumaticos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= neumaticoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= neumaticoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<NeumaticoDTO> guardar(@Valid @RequestBody NeumaticoCreation neumaticoCreation) {
        NeumaticoModel objeto = neumaticoService.guardar(neumaticoCreation);
        return new ResponseEntity<>(neumaticoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<NeumaticoDTO> borrar(@PathVariable(name = "id") Long id) {
        NeumaticoModel objeto = neumaticoService.eliminar(id);
        return new ResponseEntity<>(neumaticoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<NeumaticoDTO> reciclar(@PathVariable(name = "id") Long id) {
        NeumaticoModel objeto = neumaticoService.reciclar(id);
        return new ResponseEntity<>(neumaticoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        neumaticoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

