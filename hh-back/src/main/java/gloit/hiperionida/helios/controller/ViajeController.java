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

    @GetMapping(value = "/buscar-ultimo")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ViajeDTO> buscarUltimoViaje() {
        return new ResponseEntity<>(viajeMapper.toDto(viajeService.buscarUltimoViaje()), Helper.httpHeaders("Se devolvio el ultimo viaje."), HttpStatus.OK);
    }

    @GetMapping(value = "/sumar-cantidad-transportada")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<String> sumarCantidadTransportada() {
        Double cantidadTrasnportada = viajeService.sumarTodasCantidadTrasnportada();

        return new ResponseEntity<>(cantidadTrasnportada.toString(), Helper.httpHeaders("La sumatoria de cantidad transportada es + " + cantidadTrasnportada + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/sumar-km-vacio")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<String> sumarKmVacio() {
        Double kmVacio = viajeService.sumarTodasKmVacio();

        return new ResponseEntity<>(kmVacio.toString(), Helper.httpHeaders("La sumatoria de km vacio es + " + kmVacio + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/sumar-km-cargado")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<String> sumarKmCargado() {
        Double kmCargado = viajeService.sumarTodasKmCargado();

        return new ResponseEntity<>(kmCargado.toString(), Helper.httpHeaders("La sumatoria de km cargado es + " + kmCargado + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-fecha-entre-fechas/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
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
    @PreAuthorize("hasAuthority('USUARIO')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id-entre-fechas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionIdEntreFechas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionIdEntreFechas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-camion-id-entre-fechas-con-eliminadas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCamionIdEntreFechasConEliminadas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCamionIdEntreFechasConEliminadas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorChoferIdConEliminadas(@PathVariable(name = "id") Long id) {
        List<ViajeModel> listado = viajeService.buscarTodasPorChoferIdConEliminadas(id);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-chofer-id-entre-fechas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorChoferIdEntreFechas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorChoferIdEntreFechas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-chofer-id-entre-fechas-con-eliminadas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorChoferIdEntreFechasConEliminadas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorChoferIdEntreFechasConEliminadas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
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

    @GetMapping(value = "/buscar-todas-por-comprador-id-entre-fechas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorIdEntreFechas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorIdEntreFechas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-comprador-id-entre-fechas-con-eliminadas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorCompradorIdEntreFechasConEliminadas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorCompradorIdEntreFechasConEliminadas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
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

    @GetMapping(value = "/buscar-todas-por-destino-id-entre-fechas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoIdEntreFechas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoIdEntreFechas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-destino-id-entre-fechas-con-eliminadas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorDestinoIdEntreFechasConEliminadas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorDestinoIdEntreFechasConEliminadas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
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

    @GetMapping(value = "/buscar-todas-por-origen-id-entre-fechas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenIdEntreFechas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenIdEntreFechas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-origen-id-entre-fechas-con-eliminadas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorOrigenIdEntreFechasConEliminadas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorOrigenIdEntreFechasConEliminadas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
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

    @GetMapping(value = "/buscar-todas-por-vendedor-id-entre-fechas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorIdEntreFechas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorIdEntreFechas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-por-vendedor-id-entre-fechas-con-eliminadas/{id}/{inicio}/{fin}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ViajeDTO>> buscarTodasPorVendedorIdEntreFechasConEliminadas(
            @PathVariable(name = "id") Long id,
            @PathVariable(name = "inicio") String inicio,
            @PathVariable(name = "fin") String fin
    ) {
        List<ViajeModel> listado = viajeService.buscarTodasPorVendedorIdEntreFechasConEliminadas(id, inicio, fin);
        ArrayList<ViajeDTO> viajes = new ArrayList<>();
        for (ViajeModel viaje:listado) {
            viajes.add(viajeMapper.toDto(viaje));
        }
        return new ResponseEntity<>(viajes, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
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
    @PreAuthorize("hasAuthority('USUARIO')")
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
    @PreAuthorize("hasAuthority('USUARIO')")
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
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<ViajeDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        ViajeModel objeto = viajeService.buscarPorId(id);
        return new ResponseEntity<>(viajeMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('USUARIO')")
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

