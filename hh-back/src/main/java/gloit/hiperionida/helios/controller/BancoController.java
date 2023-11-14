package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.BancoMapper;
import gloit.hiperionida.helios.mapper.creation.BancoCreation;
import gloit.hiperionida.helios.mapper.dto.BancoDTO;
import gloit.hiperionida.helios.model.BancoModel;
import gloit.hiperionida.helios.service.implementation.BancoServiceImpl;
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
@RequestMapping(value = "/api/banco")
@RequiredArgsConstructor
@RestController
@Slf4j
public class BancoController extends AbsBaseController {
    private final BancoServiceImpl bancoService;
    private final BancoMapper bancoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el banco. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<BancoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        BancoModel objeto = bancoService.buscarPorId(id);
        return new ResponseEntity<>(bancoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BancoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        BancoModel objeto = bancoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(bancoMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<BancoDTO>> buscarTodas() {
        List<BancoModel> listado = bancoService.buscarTodas();
        ArrayList<BancoDTO> bancos = new ArrayList<>();
        for (BancoModel banco:listado) {
            bancos.add(bancoMapper.toDto(banco));
        }
        return new ResponseEntity<>(bancos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BancoDTO>> buscarTodasConEliminadas() {
        List<BancoModel> listado = bancoService.buscarTodasConEliminadas();
        ArrayList<BancoDTO> bancos = new ArrayList<>();
        for (BancoModel banco:listado) {
            bancos.add(bancoMapper.toDto(banco));
        }
        return new ResponseEntity<>(bancos, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<BancoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<BancoModel> listado = bancoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<BancoDTO> bancos = new ArrayList<>();
        for (BancoModel banco:listado) {
            bancos.add(bancoMapper.toDto(banco));
        }
        Slice slice = new SliceImpl(bancos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<BancoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<BancoModel> listado = bancoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<BancoDTO> bancos = new ArrayList<>();
        for (BancoModel banco:listado) {
            bancos.add(bancoMapper.toDto(banco));
        }
        Slice slice = new SliceImpl(bancos, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= bancoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= bancoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<BancoDTO> guardar(@Valid @RequestBody BancoCreation bancoCreation) {
        BancoModel objeto = bancoService.guardar(bancoCreation);
        return new ResponseEntity<>(bancoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<BancoDTO> borrar(@PathVariable(name = "id") Long id) {
        BancoModel objeto = bancoService.eliminar(id);
        return new ResponseEntity<>(bancoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BancoDTO> reciclar(@PathVariable(name = "id") Long id) {
        BancoModel objeto = bancoService.reciclar(id);
        return new ResponseEntity<>(bancoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        bancoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

