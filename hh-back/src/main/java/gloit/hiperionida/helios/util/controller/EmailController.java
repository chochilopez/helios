package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.Helper;
import gloit.hiperionida.helios.util.mapper.EmailMapper;
import gloit.hiperionida.helios.util.mapper.creation.EmailCreation;
import gloit.hiperionida.helios.util.mapper.dto.EmailDTO;
import gloit.hiperionida.helios.util.model.EmailModel;
import gloit.hiperionida.helios.util.service.implementation.EmailServiceImpl;
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
@RequestMapping(value = "/api/email")
@RequiredArgsConstructor
@RestController
@Slf4j
public class EmailController extends AbsBaseController {
    private final EmailServiceImpl emailService;
    private final EmailMapper emailMapper;

    @GetMapping(value = "/buscar-por-id/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<EmailDTO> buscarPorId(@PathVariable(name = "id") Long id) {
        EmailModel objeto = emailService.buscarPorId(id);
        return new ResponseEntity<>(emailMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-por-id-con-eliminadas/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EmailDTO> buscarPorIdConEliminadas(@PathVariable(name = "id") Long id) {
        EmailModel objeto = emailService.buscarPorIdConEliminadas(id);
        return new ResponseEntity<>(emailMapper.toDto(objeto), Helper.httpHeaders("Se encontro una entidad con id :" + id + ", incluidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<EmailDTO>> buscarTodas() {
        List<EmailModel> listado = emailService.buscarTodas();
        ArrayList<EmailDTO> emails = new ArrayList<>();
        for (EmailModel email:listado) {
            emails.add(emailMapper.toDto(email));
        }
        return new ResponseEntity<>(emails, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades."), HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<EmailDTO>> buscarTodasConEliminadas() {
        List<EmailModel> listado = emailService.buscarTodas();
        ArrayList<EmailDTO> emails = new ArrayList<>();
        for (EmailModel email:listado) {
            emails.add(emailMapper.toDto(email));
        }
        return new ResponseEntity<>(emails, Helper.httpHeaders("Se encontraron " + listado.size() + " entidades, inlcuidas las eliminadas."), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<Long> contarTodas() {
        Long cantidad= emailService.contarTodas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @GetMapping(value = "/contar-todas-con-eliminadas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Long> contarTodasConEliminadas() {
        Long cantidad= emailService.contarTodasConEliminadas();
        return new ResponseEntity<>(cantidad, Helper.httpHeaders(String.valueOf(cantidad)), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<EmailDTO> guardar(@Valid @RequestBody EmailCreation emailCreation) {
        EmailModel objeto = emailService.guardar(emailCreation);
        return new ResponseEntity<>(emailMapper.toDto(objeto), Helper.httpHeaders("Se persistio correctamente la entidad."), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<EmailDTO> borrar(@PathVariable(name = "id") Long id) {
        EmailModel objeto = emailService.eliminar(id);
        return new ResponseEntity<>(emailMapper.toDto(objeto), Helper.httpHeaders("Se elimino correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @GetMapping(value = "/reciclar/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<EmailDTO> reciclar(@PathVariable(name = "id") Long id) {
        EmailModel objeto = emailService.reciclar(id);
        return new ResponseEntity<>(emailMapper.toDto(objeto), Helper.httpHeaders("Se reciclo correctamente la entidad con id: " + id + "."), HttpStatus.OK);
    }

    @DeleteMapping(value = "/destruir/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> destruir(@PathVariable(name = "id") Long id) throws IOException {
        emailService.destruir(id);
        String mensaje = "Se destruyo correctamente la entidad con id: " + id + ".";
        return new ResponseEntity<>(mensaje, Helper.httpHeaders(mensaje), HttpStatus.OK);
    }
}
