package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.UsuarioMapper;
import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.mapper.dto.UsuarioDTO;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
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
@RequestMapping(value = "/api/usuario")
@RequiredArgsConstructor
@RestController
@Slf4j
public class UsuarioController extends AbsBaseController {
    private final UsuarioServiceImpl usuarioService;
    private final UsuarioMapper usuarioMapper;

    @GetMapping(value = "/buscar-por-nombre-usuario/{nombreUsuario}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<UsuarioDTO> buscarPorNombreDeUsuario(@PathVariable(name = "nombreUsuario")  String nombreUsuario) {
        UsuarioModel objeto = usuarioService.buscarPorNombreDeUsuario(nombreUsuario);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se encontro un usuario con nombre de usuario: " + nombreUsuario + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-nombre-usuario-con-eliminadas/{nombreUsuario}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO> buscarPorNombreDeUsuarioConEliminadas(@PathVariable(name = "nombreUsuario")  String nombreUsuario) {
        UsuarioModel objeto = usuarioService.buscarPorNombreDeUsuarioConEliminadas(nombreUsuario);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se encontro un usuario con nombre de usuario: " + nombreUsuario + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        UsuarioModel objeto = usuarioService.buscarPorId(id);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        UsuarioModel objeto = usuarioService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se encontró una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<UsuarioDTO>> buscarTodas() {
        List<UsuarioModel> listado = usuarioService.buscarTodas();
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        for (UsuarioModel usuario:listado) {
            usuarios.add(usuarioMapper.toDto(usuario));
        }
        return new ResponseEntity<>(usuarios, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UsuarioDTO>> buscarTodasConEliminadas() {
        List<UsuarioModel> listado = usuarioService.buscarTodasConEliminadas();
        ArrayList<UsuarioDTO> usuarios = new ArrayList<>();
        for (UsuarioModel usuario:listado) {
            usuarios.add(usuarioMapper.toDto(usuario));
        }
        return new ResponseEntity<>(usuarios, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad = usuarioService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad = usuarioService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO> guardar(@Valid @RequestBody UsuarioCreation usuarioCreation) {
        UsuarioModel objeto = usuarioService.guardar(usuarioCreation);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO> borrar(@PathVariable(name = "id") Long id) {
        UsuarioModel objeto = usuarioService.eliminar(id);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UsuarioDTO> reciclar(@PathVariable(name = "id") Long id) {
        UsuarioModel objeto = usuarioService.reciclar(id);
        return new ResponseEntity<>(usuarioMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        usuarioService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}
