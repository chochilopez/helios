package gloit.hiperionida.helios.util.controller;

import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.service.implementation.RolServiceImpl;
import gloit.hiperionida.helios.util.service.implementation.UsuarioServiceImpl;
import lombok.RequiredArgsConstructor;
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

        usuarioServiceImpl.guardar(new UsuarioCreation(
                null,
                "Pablo Diaz",
                "",
                "",
                "",
                "true",
                "pablo@helios.com",
                "Pald4155",
                null,
                null,
                List.of("1")
        ));
        usuarioServiceImpl.guardar(new UsuarioCreation(
                null,
                "Agustín Albano",
                "",
                "",
                "",
                "true",
                "agustin@helios.com",
                "Vadd4915",
                null,
                null,
                List.of("1", "2")
        ));
        usuarioServiceImpl.guardar(new UsuarioCreation(
                null,
                "Juan Bauza",
                "",
                "",
                "",
                "true",
                "juan@helios.com",
                "Kdns1845",
                null,
                null,
                List.of("1","2","3")
        ));
        usuarioServiceImpl.guardar(new UsuarioCreation(
                null,
                "postman",
                "",
                "",
                "",
                "true",
                "postman@helios.com",
                "Casa9852",
                null,
                null,
                List.of("1","2","3")
        ));
        usuarioServiceImpl.guardar(new UsuarioCreation(
                null,
                "admin",
                "",
                "",
                "",
                "true",
                "admin@helios.com",
                "Casa9852",
                null,
                null,
                List.of("1","2","3")
        ));

        return ResponseEntity.status(HttpStatus.OK).body("Autoridades y usuarios de testeo creados");
    }
}
