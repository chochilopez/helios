package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.SeguroMapper;
import gloit.hiperionida.helios.mapper.creation.SeguroCreation;
import gloit.hiperionida.helios.mapper.dto.SeguroDTO;
import gloit.hiperionida.helios.model.SeguroModel;
import gloit.hiperionida.helios.service.implementation.SeguroServiceImpl;
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
@RequestMapping(value = "/api/seguro")
@RequiredArgsConstructor
@RestController
@Slf4j
public class SeguroController extends AbsBaseController {
    private final SeguroServiceImpl seguroService;
    private final SeguroMapper seguroMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el seguro. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<SeguroDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        SeguroModel objeto = seguroService.buscarPorId(id);
        return new ResponseEntity<>(seguroMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<SeguroDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        SeguroModel objeto = seguroService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(seguroMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<SeguroDTO>> buscarTodas() {
        List<SeguroModel> listado = seguroService.buscarTodas();
        ArrayList<SeguroDTO> seguros = new ArrayList<>();
        for (SeguroModel seguro:listado) {
            seguros.add(seguroMapper.toDto(seguro));
        }
        return new ResponseEntity<>(seguros, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<SeguroDTO>> buscarTodasConEliminadas() {
        List<SeguroModel> listado = seguroService.buscarTodasConEliminadas();
        ArrayList<SeguroDTO> seguros = new ArrayList<>();
        for (SeguroModel seguro:listado) {
            seguros.add(seguroMapper.toDto(seguro));
        }
        return new ResponseEntity<>(seguros, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<SeguroDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<SeguroModel> listado = seguroService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<SeguroDTO> seguros = new ArrayList<>();
        for (SeguroModel seguro:listado) {
            seguros.add(seguroMapper.toDto(seguro));
        }
        Slice slice = new SliceImpl(seguros, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<SeguroDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<SeguroModel> listado = seguroService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<SeguroDTO> seguros = new ArrayList<>();
        for (SeguroModel seguro:listado) {
            seguros.add(seguroMapper.toDto(seguro));
        }
        Slice slice = new SliceImpl(seguros, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= seguroService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= seguroService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<SeguroDTO> guardar(@Valid @RequestBody SeguroCreation seguroCreation) {
        SeguroModel objeto = seguroService.guardar(seguroCreation);
        return new ResponseEntity<>(seguroMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<SeguroDTO> borrar(@PathVariable(name = "id") Long id) {
        SeguroModel objeto = seguroService.eliminar(id);
        return new ResponseEntity<>(seguroMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<SeguroDTO> reciclar(@PathVariable(name = "id") Long id) {
        SeguroModel objeto = seguroService.reciclar(id);
        return new ResponseEntity<>(seguroMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        seguroService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

