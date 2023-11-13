package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.ProveedorCreation;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.model.ProveedorModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface ProveedorService extends GenericService<ProveedorModel, ProveedorCreation> {
    List<ProveedorModel> buscarTodasPorDireccion(String direccion);
    List<ProveedorModel> buscarTodasPorDireccionConEliminadas(String direccion);
    List<ProveedorModel> buscarTodasPorEmail(String email);
    List<ProveedorModel> buscarTodasPorEmailConEliminadas(String email);
    List<ProveedorModel> buscarTodasPorIdentificacion(String identificacion);
    List<ProveedorModel> buscarTodasPorIdentificacionConEliminadas(String identificacion);
    List<ProveedorModel> buscarTodasPorNombre(String nombre);
    List<ProveedorModel> buscarTodasPorNombreConEliminadas(String nombre);
    List<ProveedorModel> buscarTodasPorNotas(String notas);
    List<ProveedorModel> buscarTodasPorNotasConEliminadas(String notas);
    List<ProveedorModel> buscarTodasPorTelefono(String telefono);
    List<ProveedorModel> buscarTodasPorTelefonoConEliminadas(String telefono);
}

