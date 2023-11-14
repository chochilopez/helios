package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.CamionMapper;
import gloit.hiperionida.helios.mapper.creation.CamionCreation;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;
import gloit.hiperionida.helios.mapper.dto.CamionDTO;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.model.CamionModel;
import gloit.hiperionida.helios.service.implementation.CamionServiceImpl;
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
@RequestMapping(value = "/api/camion")
@RequiredArgsConstructor
@RestController
@Slf4j
public class CamionController extends AbsBaseController {
    private final CamionServiceImpl camionService;
    private final CamionMapper camionMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el camion. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-todas-por-anio/{anio}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorAnio(@PathVariable(name = "anio") Integer anio) {
        List<CamionModel> listado = camionService.buscarTodasPorAnio(anio);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel viaje:listado) {
            camionDTOS.add(camionMapper.toDto(viaje));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-anio-con-eliminadas/{anio}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorRangoAnioConEliminadas(@PathVariable(name = "anio") Integer anio) {
        List<CamionModel> listado = camionService.buscarTodasPorAnioConEliminadas(anio);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel viaje:listado) {
            camionDTOS.add(camionMapper.toDto(viaje));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-marca-modelo/{marcaModelo}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorMarcaModelo(@PathVariable(name = "marcaModelo") String marcaModelo) {
        List<CamionModel> listado = camionService.buscarTodasPorMarcaModelo(marcaModelo);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel camion:listado) {
            camionDTOS.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-marca-modelo-con-eliminadas/{marcaModelo}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorMarcaModeloConEliminadas(@PathVariable(name = "marcaModelo") String marcaModelo) {
        List<CamionModel> listado = camionService.buscarTodasPorMarcaModeloConEliminadas(marcaModelo);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel camion:listado) {
            camionDTOS.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-numero-chasis/{numeroChasis}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorNumeroChasis(@PathVariable(name = "numeroChasis") String numeroChasis) {
        List<CamionModel> listado = camionService.buscarTodasPorNumeroChasis(numeroChasis);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel camion:listado) {
            camionDTOS.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-numero-chasis-con-eliminadas/{numeroChasis}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorNumeroChasisConEliminadas(@PathVariable(name = "numeroChasis") String numeroChasis) {
        List<CamionModel> listado = camionService.buscarTodasPorNumeroChasisConEliminadas(numeroChasis);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel camion:listado) {
            camionDTOS.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-numero-motor/{numeroMotor}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorNumeroMotor(@PathVariable(name = "numeroMotor") String numeroMotor) {
        List<CamionModel> listado = camionService.buscarTodasPorNumeroMotor(numeroMotor);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel camion:listado) {
            camionDTOS.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-numero-motor-con-eliminadas/{numeroMotor}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorNumeroMotorConEliminadas(@PathVariable(name = "numeroMotor") String numeroMotor) {
        List<CamionModel> listado = camionService.buscarTodasPorNumeroMotorConEliminadas(numeroMotor);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel camion:listado) {
            camionDTOS.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<CamionModel> listado = camionService.buscarTodasPorNotas(notas);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel viaje:listado) {
            camionDTOS.add(camionMapper.toDto(viaje));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<CamionModel> listado = camionService.buscarTodasPorNotasConEliminadas(notas);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel viaje:listado) {
            camionDTOS.add(camionMapper.toDto(viaje));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "  entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-patente/{patente}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorPatente(@PathVariable(name = "patente") String patente) {
        List<CamionModel> listado = camionService.buscarTodasPorPatente(patente);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel viaje:listado) {
            camionDTOS.add(camionMapper.toDto(viaje));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-patente-con-eliminadas/{patente}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasPorPatenteConEliminadas(@PathVariable(name = "patente") String patente) {
        List<CamionModel> listado = camionService.buscarTodasPorPatenteConEliminadas(patente);
        ArrayList<CamionDTO> camionDTOS = new ArrayList<>();
        for (CamionModel viaje:listado) {
            camionDTOS.add(camionMapper.toDto(viaje));
        }
        return new ResponseEntity<>(camionDTOS, Helper.httpHeaders("Se encontraron " + listado.size() + "  entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CamionDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        CamionModel objeto = camionService.buscarPorId(id);
        return new ResponseEntity<>(camionMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CamionDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        CamionModel objeto = camionService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(camionMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<CamionDTO>> buscarTodas() {
        List<CamionModel> listado = camionService.buscarTodas();
        ArrayList<CamionDTO> camions = new ArrayList<>();
        for (CamionModel camion:listado) {
            camions.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<CamionDTO>> buscarTodasConEliminadas() {
        List<CamionModel> listado = camionService.buscarTodasConEliminadas();
        ArrayList<CamionDTO> camions = new ArrayList<>();
        for (CamionModel camion:listado) {
            camions.add(camionMapper.toDto(camion));
        }
        return new ResponseEntity<>(camions, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Slice<CamionDTO>> buscarTodas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CamionModel> listado = camionService.buscarTodasPorOrdenPorPagina(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CamionDTO> camions = new ArrayList<>();
        for (CamionModel camion:listado) {
            camions.add(camionMapper.toDto(camion));
        }
        Slice slice = new SliceImpl(camions, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Slice<CamionDTO>> buscarTodasConEliminadas(@Valid @RequestBody PaginadoDTO paginadoDTO) {
        Slice<CamionModel> listado = camionService.buscarTodasPorOrdenPorPaginaConEliminadas(
                paginadoDTO.getDireccion(),
                paginadoDTO.getCampo(),
                paginadoDTO.getPagina(),
                paginadoDTO.getElementos()
        );
        ArrayList<CamionDTO> camions = new ArrayList<>();
        for (CamionModel camion:listado) {
            camions.add(camionMapper.toDto(camion));
        }
        Slice slice = new SliceImpl(camions, listado.getPageable(), listado.hasNext());
        return new ResponseEntity<>(slice, Helper.httpHeaders("Se encontraron " + slice.getSize() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= camionService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= camionService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CamionDTO> guardar(@Valid @RequestBody CamionCreation camionCreation) {
        CamionModel objeto = camionService.guardar(camionCreation);
        return new ResponseEntity<>(camionMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<CamionDTO> borrar(@PathVariable(name = "id") Long id) {
        CamionModel objeto = camionService.eliminar(id);
        return new ResponseEntity<>(camionMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<CamionDTO> reciclar(@PathVariable(name = "id") Long id) {
        CamionModel objeto = camionService.reciclar(id);
        return new ResponseEntity<>(camionMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        camionService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}

