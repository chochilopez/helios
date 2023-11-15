package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.ConductorCreation;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.model.ConductorModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface ConductorService extends GenericService<ConductorModel, ConductorCreation> {
    List<ConductorModel> buscarTodasPorDireccion(String direccion);
    List<ConductorModel> buscarTodasPorDireccionConEliminadas(String direccion);
    List<ConductorModel> buscarTodasPorEmail(String email);
    List<ConductorModel> buscarTodasPorEmailConEliminadas(String email);
    List<ConductorModel> buscarTodasPorIdentificacion(String identificacion);
    List<ConductorModel> buscarTodasPorIdentificacionConEliminadas(String identificacion);
    List<ConductorModel> buscarTodasPorNombre(String nombre);
    List<ConductorModel> buscarTodasPorNombreConEliminadas(String nombre);
    List<ConductorModel> buscarTodasPorNotas(String notas);
    List<ConductorModel> buscarTodasPorNotasConEliminadas(String notas);
    List<ConductorModel> buscarTodasPorTelefono(String telefono);
    List<ConductorModel> buscarTodasPorTelefonoConEliminadas(String telefono);
}
