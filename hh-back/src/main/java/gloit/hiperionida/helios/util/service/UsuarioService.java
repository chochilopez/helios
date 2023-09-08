package gloit.hiperionida.helios.util.service;

import gloit.hiperionida.helios.util.mapper.creation.UsuarioCreation;
import gloit.hiperionida.helios.util.model.UsuarioModel;

public interface UsuarioService extends GenericService<UsuarioModel, UsuarioCreation> {
    UsuarioModel buscarPorNombreDeUsuario(String nombreUsuario);
    UsuarioModel buscarPorNombreDeUsuarioConEliminadas(String nombreUsuario);
    UsuarioModel darRol(UsuarioModel usuarioModel, String rolEnum);
    UsuarioModel obtenerUsuario();
    UsuarioModel buscarPorNombreDeUsuarioHabilitado(String nombreUsuario);
    Boolean existeUsuarioPorNombreDeUsuario(String username);
}
