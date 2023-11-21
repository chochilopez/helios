package gloit.hiperionida.helios.util.service.implementation;

import gloit.hiperionida.helios.util.model.enums.RolEnum;
import gloit.hiperionida.helios.util.exception.DatosInexistentesException;
import gloit.hiperionida.helios.util.mapper.RolMapper;
import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.model.RolModel;
import gloit.hiperionida.helios.util.repository.RolDAO;
import gloit.hiperionida.helios.util.service.RolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RolServiceImpl implements RolService {
    private final RolDAO rolDAO;
    private final RolMapper rolMapper;

    @Override
    public RolModel buscarPorRol(String nombre) {
        log.info("Buscando todas las entidades Rol con nombre: {}.", nombre);
        RolModel rol = rolDAO.findByRol(RolEnum.valueOf(nombre)).orElseThrow(() -> new DatosInexistentesException("No se encontró la entidad Rol con nombre: " + nombre + "."));
        String mensaje = "Se encontró una entidad Rol con nombre: " + nombre + ".";
        log.info(mensaje);
        return rol;
    }

    @Override
    public RolModel buscarPorId(Long id) {
        log.info("Buscando la entidad Rol con id: {}.", id);
        RolModel rolModel = rolDAO.findById(id).orElseThrow(()-> new DatosInexistentesException("No se encontró la entidad con id " + id + "."));
        String mensaje = "Se encontró una entidad Rol.";
        log.info(mensaje);
        return rolModel;
    }

    @Override
    public List<RolModel> buscarTodas() {
        log.info("Buscando todas las entidades Rol.");
        List<RolModel> listado = rolDAO.findAll();
        if (listado.isEmpty())
            throw new DatosInexistentesException("No se encontraron entidades Rol.");
        return listado;
    }

    @Override
    public RolModel guardar(RolCreation creation) {
        log.info("Insertando la entidad Rol: {}.",  creation);
        RolModel rolModel = rolDAO.save(rolMapper.toEntity(creation));
        if (creation.getId() == null) {
            log.info("Se persistio correctamente la nueva entidad.");
        } else {
            log.info("Se persistio correctamente la entidad.");
        }
        return rolDAO.save(rolModel);
    }

    @Override
    public void destruir(Long id) {
        log.info("Destruyendo la entidad Rol con id: {}.", id);
        RolModel objeto = this.buscarPorId(id);
        rolDAO.delete(objeto);
        log.info("La entidad fue destruida y el rol " + objeto + " fue eliminado correctamente.");
    }
}
