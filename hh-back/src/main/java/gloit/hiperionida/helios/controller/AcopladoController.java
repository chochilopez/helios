package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.dto.ViajeDTO;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.controller.AbsBaseController;
import gloit.hiperionida.helios.util.mapper.dto.ErrorDTO;
import gloit.hiperionida.helios.mapper.creation.AcopladoCreation;
import gloit.hiperionida.helios.mapper.dto.AcopladoDTO;
import gloit.hiperionida.helios.model.AcopladoModel;
import gloit.hiperionida.helios.service.implementation.AcopladoServiceImpl;
import gloit.hiperionida.helios.mapper.AcopladoMapper;
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
@RequestMapping(value = "/api/acoplado")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AcopladoController extends AbsBaseController {
    private final AcopladoServiceImpl acopladoService;
    private final AcopladoMapper acopladoMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el acoplado. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-anio/{anio}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorAnio(@PathVariable(name = "anio") Integer anio) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorAnio(anio);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-anio-con-eliminadas/{anio}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorRangoAnioConEliminadas(@PathVariable(name = "anio") Integer anio) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorAnioConEliminadas(anio);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-marca-modelo/{marcaModelo}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorMarcaModelo(@PathVariable(name = "marcaModelo") String marcaModelo) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorMarcaModelo(marcaModelo);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-marca-modelo-con-eliminadas/{marcaModelo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorMarcaModeloConEliminadas(@PathVariable(name = "marcaModelo") String marcaModelo) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorMarcaModeloConEliminadas(marcaModelo);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "  entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorNotas(notas);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorNotasConEliminadas(notas);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "  entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-patente/{patente}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorPatente(@PathVariable(name = "patente") String patente) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorPatente(patente);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-patente-con-eliminadas/{patente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasPorPatenteConEliminadas(@PathVariable(name = "patente") String patente) {
        List<AcopladoModel> listado = acopladoService.buscarTodasPorPatenteConEliminadas(patente);
        ArrayList<AcopladoDTO> acopladoDTOS = new ArrayList<>();
        for (AcopladoModel viaje:listado) {
            acopladoDTOS.add(acopladoMapper.toDto(viaje));
        }
        return new ResponseEntity<>(acopladoDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "  entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AcopladoDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        AcopladoModel objeto = acopladoService.buscarPorId(id);
        return new ResponseEntity<>(acopladoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AcopladoDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        AcopladoModel objeto = acopladoService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(acopladoMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodas() {
        List<AcopladoModel> listado = acopladoService.buscarTodas();
        ArrayList<AcopladoDTO> acoplados = new ArrayList<>();
        for (AcopladoModel acoplado:listado) {
            acoplados.add(acopladoMapper.toDto(acoplado));
        }
        return new ResponseEntity<>(acoplados, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<AcopladoDTO>> buscarTodasConEliminadas() {
        List<AcopladoModel> listado = acopladoService.buscarTodasConEliminadas();
        ArrayList<AcopladoDTO> acoplados = new ArrayList<>();
        for (AcopladoModel acoplado:listado) {
            acoplados.add(acopladoMapper.toDto(acoplado));
        }
        return new ResponseEntity<>(acoplados, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<AcopladoDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<AcopladoModel> listado = acopladoService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<AcopladoDTO> acoplados = new ArrayList<>();
        for (AcopladoModel acoplado:listado) {
            acoplados.add(acopladoMapper.toDto(acoplado));
        }
        Slice slice = new SliceImpl(acoplados, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<AcopladoDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<AcopladoModel> listado = acopladoService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<AcopladoDTO> acoplados = new ArrayList<>();
        for (AcopladoModel acoplado:listado) {
            acoplados.add(acopladoMapper.toDto(acoplado));
        }
        Slice slice = new SliceImpl(acoplados, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= acopladoService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= acopladoService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AcopladoDTO> guardar(@Valid @RequestBody AcopladoCreation acopladoCreation) {
        AcopladoModel objeto = acopladoService.guardar(acopladoCreation);
        return new ResponseEntity<>(acopladoMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<AcopladoDTO> borrar(@PathVariable(name = "id") Long id) {
        AcopladoModel objeto = acopladoService.eliminar(id);
        return new ResponseEntity<>(acopladoMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<AcopladoDTO> reciclar(@PathVariable(name = "id") Long id) {
        AcopladoModel objeto = acopladoService.reciclar(id);
        return new ResponseEntity<>(acopladoMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        acopladoService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

