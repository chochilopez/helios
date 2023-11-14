package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ChequeMapper;
import gloit.hiperionida.helios.mapper.creation.ChequeCreation;
import gloit.hiperionida.helios.mapper.dto.ChequeDTO;
import gloit.hiperionida.helios.model.ChequeModel;
import gloit.hiperionida.helios.service.implementation.ChequeServiceImpl;
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
@RequestMapping(value = "/api/cheque")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ChequeController extends AbsBaseController {
    private final ChequeServiceImpl chequeService;
    private final ChequeMapper chequeMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el cheque. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ChequeDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ChequeModel objeto = chequeService.buscarPorId(id);
        return new ResponseEntity<>(chequeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ChequeDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ChequeModel objeto = chequeService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(chequeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ChequeDTO>> buscarTodas() {
        List<ChequeModel> listado = chequeService.buscarTodas();
        ArrayList<ChequeDTO> cheques = new ArrayList<>();
        for (ChequeModel cheque:listado) {
            cheques.add(chequeMapper.toDto(cheque));
        }
        return new ResponseEntity<>(cheques, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ChequeDTO>> buscarTodasConEliminadas() {
        List<ChequeModel> listado = chequeService.buscarTodasConEliminadas();
        ArrayList<ChequeDTO> cheques = new ArrayList<>();
        for (ChequeModel cheque:listado) {
            cheques.add(chequeMapper.toDto(cheque));
        }
        return new ResponseEntity<>(cheques, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<ChequeDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ChequeModel> listado = chequeService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ChequeDTO> cheques = new ArrayList<>();
        for (ChequeModel cheque:listado) {
            cheques.add(chequeMapper.toDto(cheque));
        }
        Slice slice = new SliceImpl(cheques, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<ChequeDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<ChequeModel> listado = chequeService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<ChequeDTO> cheques = new ArrayList<>();
        for (ChequeModel cheque:listado) {
            cheques.add(chequeMapper.toDto(cheque));
        }
        Slice slice = new SliceImpl(cheques, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= chequeService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= chequeService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ChequeDTO> guardar(@Valid @RequestBody ChequeCreation chequeCreation) {
        ChequeModel objeto = chequeService.guardar(chequeCreation);
        return new ResponseEntity<>(chequeMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ChequeDTO> borrar(@PathVariable(name = "id") Long id) {
        ChequeModel objeto = chequeService.eliminar(id);
        return new ResponseEntity<>(chequeMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ChequeDTO> reciclar(@PathVariable(name = "id") Long id) {
        ChequeModel objeto = chequeService.reciclar(id);
        return new ResponseEntity<>(chequeMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        chequeService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

