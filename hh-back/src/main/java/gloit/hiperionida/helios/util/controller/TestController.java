package gloit.hiperionida.helios.util.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/prueba")
@RestController
public class TestController {

    @GetMapping(value = "/carga")
    @PreAuthorize("hasAuthority('CARGA')")
    public String carga() { return "Autoridad carga."; }

    @GetMapping(value = "/usuario")
    @PreAuthorize("hasAuthority('USUARIO')")
    public String usuario() { return "Autoridad usuario."; }

    @GetMapping(value = "/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String admin() { return "Autoridad admin."; }
}
