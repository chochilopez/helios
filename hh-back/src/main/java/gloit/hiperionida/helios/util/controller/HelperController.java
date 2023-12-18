package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.model.UsuarioModel;
import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.service.implementation.RolServiceImpl;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/ayuda")
@RequiredArgsConstructor
@RestController
public class HelperController {
    private final UsuarioServiceImpl usuarioServiceImpl;
    private final RolServiceImpl rolService;

    @PutMapping(value = "/cargar-autoridades")
    public ResponseEntity<?> autoridades() {
        rolService.guardar(new RolCreation(RolEnum.CARGA.name()));
        rolService.guardar(new RolCreation(RolEnum.USUARIO.name()));
        rolService.guardar(new RolCreation(RolEnum.ADMIN.name()));

        usuarioServiceImpl.guardar(UsuarioCreation.builder()
                .id(null)
                .direccion("")
                .dni("")
                .habilitada("true")
                .nombre("Pablo Diaz")
                .password("Pals4155")
                .roles_id(List.of("1"))
                .telefono("")
                .username("pablo@helios.com")
                .build()
        );

        usuarioServiceImpl.guardar(UsuarioCreation.builder()
                .id(null)
                .direccion("")
                .dni("")
                .habilitada("true")
                .nombre("Agustín Albano")
                .password("Vadd4915")
                .roles_id(List.of("1", "2"))
                .telefono("")
                .username("agustin@helios.com")
                .build()
        );

        usuarioServiceImpl.guardar(UsuarioCreation.builder()
                .id(null)
                .direccion("")
                .dni("")
                .habilitada("true")
                .nombre("Juan Bauza")
                .password("Kdns1845")
                .roles_id(List.of("1","2","3"))
                .telefono("")
                .username("juan@helios.com")
                .build()
        );

        usuarioServiceImpl.guardar(UsuarioCreation.builder()
                .id(null)
                .direccion("")
                .dni("")
                .habilitada("true")
                .nombre("postman")
                .password("Casa9852")
                .roles_id(List.of("1","2","3"))
                .telefono("")
                .username("postman@helios.com")
                .build()
        );

        usuarioServiceImpl.guardar(UsuarioCreation.builder()
                .id(null)
                .direccion("")
                .dni("")
                .habilitada("true")
                .nombre("admin")
                .password("Casa9852")
                .roles_id(List.of("1","2","3"))
                .telefono("")
                .username("admin@helios.com")
                .build()
        );

        return ResponseEntity.status(HttpStatus.OK).body("Autoridades y usuarios de testeo creados");
    }
}
