package gloit.hiperionida.helios.util.service;

import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.model.RolModel;

public interface RolService extends GenericService<RolModel, RolCreation> {
    RolModel buscarPorRol(String rol);
    RolModel buscarPorRolConEliminadas(String rol);
}
