package gloit.hiperionida.helios.controller;

import gloit.hiperionida.helios.mapper.ViajeMapper;
import gloit.hiperionida.helios.mapper.creation.ViajeCreation;
import gloit.hiperionida.helios.mapper.dto.ViajeDTO;
import gloit.hiperionida.helios.model.ViajeModel;
import gloit.hiperionida.helios.service.implementation.ViajeServiceImpl;
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
@RequestMapping(value = "/api/viaje")
@RequiredArgsConstructor
@RestController
@Slf4j
public class    ViajeController extends AbsBaseController {
    private final ViajeServiceImpl viajeService;
    private final ViajeMapper viajeMapper;

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorDTO> handleIOException(IOException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        String mensaje = "Ocurrio un error al guardar el viaje. " + e.getMessage();

        return new ResponseEntity<>(new ErrorDTO(status, mensaje), Helper.httpHeaders(mensaje), status);
    }

    @GetMapping(value = "/buscar-proximo")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<ViajeDTO> buscarProximo() {
        return new ResponseEntity<>(viajeMapper.toDto(viajeService.buscarProximo()), Helper.httpHeaders("Se devolvio el proximo viaje."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-ultimo")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<ViajeDTO> buscarUltimo() {
        return new ResponseEntity<>(viajeMapper.toDto(viajeService.buscarUltimo()), Helper.httpHeaders("Se devolvio el ultimo viaje."), HttpStatus.OK);
    }

    @GetMapping(value = "/sumar-cantidad-transportada")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<String> sumarCantidadTransportada() {
        Double cantidadTrasnportada = viajeService.sumarTodasCantidadTrasnportada();

        return new ResponseEntity<>(cantidadTrasnportada.toString(), Helper.httpHeaders("La sumatoria de cantidad transportada es + " + cantidadTrasnportada + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/sumar-km-vacio")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<String> sumarKmVacio() {
        Double kmVacio = viajeService.sumarTodasKmVacio();

        return new ResponseEntity<>(kmVacio.toString(), Helper.httpHeaders("La sumatoria de km vacio es + " + kmVacio + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/sumar-km-cargado")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<String> sumarKmCargado() {
        Double kmCargado = viajeService.sumarTodasKmCargado();

        return new ResponseEntity<>(kmCargado.toString(), Helper.httpHeaders("La sumatoria de km cargado es + " + kmCargado + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-entre-fechas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorFechaEntreFechas(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorFechaEntreFechas(inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-entre-fechas-con-eliminadas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorFechaEntreFechasConEliminadas(
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorFechaEntreFechasConEliminadas(inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-acoplado-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorAcopladoId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorAcopladoId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-acoplado-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorAcopladoIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorAcopladoIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-carga-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCargaId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCargaId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-carga-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCargaIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCargaIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-categoria-viaje-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCategoriaViajeId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCategoriaViajeId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-categoria-viaje-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCategoriaViajeIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCategoriaViajeIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-intermediario-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorIntermediarioId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorIntermediarioId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-intermediario-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorIntermediarioIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorIntermediarioIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-cantidad-transportada/{min}/{max}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoCantidadTransportada(@PathVariable(name = "min") Integer min, @PathVariable(name = "max") Integer max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoCantidadTransportada(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-cantidad-transportada-con-eliminadas/{min}/{max}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoCantidadTransportadaConEliminadas(@PathVariable(name = "min") Integer min, @PathVariable(name = "max") Integer max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoCantidadTransportadaConEliminadas(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-km-cargado/{min}/{max}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoKmCargado(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoKmCargado(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-km-cargado-con-eliminadas/{min}/{max}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoKmCargadoConEliminadas(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoKmCargadoConEliminadas(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-km-vacio/{min}/{max}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoKmVacio(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoKmVacio(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-km-vacio-con-eliminadas/{min}/{max}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>>  ConEliminadas(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoKmVacioConEliminadas(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas/{notas}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorNotas(@PathVariable(name = "notas") String notas) {
        List<ViajeModel> listado = viajeService.buscarTodasPorNotas(notas);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-notas-con-eliminadas/{notas}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorNotasConEliminadas(@PathVariable(name = "notas") String notas) {
        List<ViajeModel> listado = viajeService.buscarTodasPorNotasConEliminadas(notas);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-guia/{guia}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorGuia(@PathVariable(name = "guia") String guia) {
        List<ViajeModel> listado = viajeService.buscarTodasPorGuia(guia);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-guia-con-eliminadas/{guia}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorGuiaConEliminadas(@PathVariable(name = "guia") String guia) {
        List<ViajeModel> listado = viajeService.buscarTodasPorGuiaConEliminadas(guia);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-neto/{min}/{max}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoNeto(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoNeto(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-neto-con-eliminadas/{min}/{max}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoNetoConEliminadas(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoNetoConEliminadas(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-valor-km/{min}/{max}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoValorKm(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoValorKm(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-rango-valor-km-con-eliminadas/{min}/{max}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorRangoValorKmConEliminadas(@PathVariable(name = "min") Double min, @PathVariable(name = "max") Double max) {
        List<ViajeModel> listado = viajeService.buscarTodasPorRangoValorKmConEliminadas(min, max);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-conductor-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorConductorId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorConductorId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-conductor-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorConductorIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorConductorIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-id/{id}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorId(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorId(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-nombre/{nombre}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorNombre(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorNombre(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorNombreConEliminadas(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-nombre/{nombre}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorNombre(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorNombre(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-nombre-con-eliminadas/{nombre}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorNombreConEliminadas(@PathVariable(name = "nombre") String nombre) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorNombreConEliminadas(nombre);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-direccion/{direccion}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenDireccion(@PathVariable(name = "direccion") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenDireccion(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenDireccionConEliminadas(@PathVariable(name = "direccion") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenDireccionConEliminadas(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-direccion/{direccion}")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoDireccion(@PathVariable(name = "direccion") String direccion) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoDireccion(direccion);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-direccion-con-eliminadas/{direccion}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoDireccionConEliminadas(@PathVariable(name = "direccion") String direccion) {
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
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ViajeDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        ViajeModel objeto = viajeService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('CARGA')")
    public ResponseEntity<List<ViajeDTO>> buscarTodas() {
        List<ViajeModel> listado = viajeService.buscarTodas();
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasConEliminadas() {
        List<ViajeModel> listado = viajeService.buscarTodasConEliminadas();
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @PostMapping(value = "/buscar-todas-paginadas")
    @PreAuthorize("hasAuthority('CARGA')")
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

    @PostMapping(value = "/buscar-todas-con-eliminadas-paginadas")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= viajeService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('CARGA')")
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

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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

