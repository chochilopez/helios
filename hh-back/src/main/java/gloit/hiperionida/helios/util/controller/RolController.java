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
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RolDTO> buscarTodasPorRol(@PathVariable(name = "rol")  String rol) {
        RolModel objeto = rolService.buscarPorRol(rol);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con rol :" + rol + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RolDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        RolModel objeto = rolService.buscarPorId(id);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<RolDTO>> buscarTodas() {
        List<RolModel> listado = rolService.buscarTodas();
        ArrayList<RolDTO> roles = new ArrayList<>();
        for (RolModel rol:listado) {
            roles.add(rolMapper.toDto(rol));
        }
        return new ResponseEntity<>(roles, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<RolDTO> guardar(@Valid @RequestBody RolCreation rolCreation) {
        RolModel objeto = rolService.guardar(rolCreation);
        return new ResponseEntity<>(rolMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        rolService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}
