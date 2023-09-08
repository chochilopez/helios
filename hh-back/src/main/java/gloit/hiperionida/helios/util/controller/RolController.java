package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.RolMapper;
import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.mapper.dto.RolDTO;
import gloit.hiperionida.helios.util.model.RolModel;
import gloit.hiperionida.helios.util.service.implementation.RolServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/rol")
@RequiredArgsConstructor
@RestController
@Slf4j
public class RolController extends AbsBaseController {
    private final RolServiceImpl rolService;
    private final RolMapper rolMapper;

    @GetMapping(value = "/buscar-por-rol/{rol}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> buscarTodasPorRol(@PathVariable(name = "rol")  String rol) {
        RolModel objeto = rolService.buscarPorRol(rol);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con rol :" + rol + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-rol-con-eliminadas/{rol}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> buscarPorRolConEliminadas(@PathVariable(name = "rol")  String rol) {
        RolModel objeto = rolService.buscarPorRolConEliminadas(rol);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con rol :" + rol + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        RolModel objeto = rolService.buscarPorId(id);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        RolModel objeto = rolService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<List<RolDTO>> buscarTodas() {
        List<RolModel> listado = rolService.buscarTodas();
        ArrayList<RolDTO> roles = new ArrayList<>();
        for (RolModel rol:listado) {
            roles.add(rolMapper.toDto(rol));
        }
        return new ResponseEntity<>(roles, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<List<RolDTO>> buscarTodasConEliminadas() {
        List<RolModel> listado = rolService.buscarTodasConEliminadas();
        ArrayList<RolDTO> roles = new ArrayList<>();
        for (RolModel rol:listado) {
            roles.add(rolMapper.toDto(rol));
        }
        return new ResponseEntity<>(roles, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad = rolService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad = rolService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> guardar(@Valid @RequestBody RolCreation rolCreation) {
        RolModel objeto = rolService.guardar(rolCreation);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> borrar(@PathVariable(name = "id") Long id) {
        RolModel objeto = rolService.eliminar(id);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @PostMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<RolDTO> reciclar(@PathVariable(name = "id") Long id) {
        RolModel objeto = rolService.reciclar(id);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('JEFE')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        rolService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}
