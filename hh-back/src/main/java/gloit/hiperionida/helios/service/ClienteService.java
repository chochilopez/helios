package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.ClienteCreation;
import gloit.hiperionida.helios.model.ClienteModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface ClienteService extends GenericService<ClienteModel, ClienteCreation> {
    List<ClienteModel> buscarTodasPorDireccion(String direccion);
    List<ClienteModel> buscarTodasPorDireccionConEliminadas(String direccion);
    List<ClienteModel> buscarTodasPorEmail(String email);
    List<ClienteModel> buscarTodasPorEmailConEliminadas(String email);
    List<ClienteModel> buscarTodasPorIdentificacion(String identificacion);
    List<ClienteModel> buscarTodasPorIdentificacionConEliminadas(String identificacion);
    List<ClienteModel> buscarTodasPorNombre(String nombre);
    List<ClienteModel> buscarTodasPorNombreConEliminadas(String nombre);
    List<ClienteModel> buscarTodasPorNotas(String notas);
    List<ClienteModel> buscarTodasPorNotasConEliminadas(String notas);
    List<ClienteModel> buscarTodasPorTelefono(String telefono);
    List<ClienteModel> buscarTodasPorTelefonoConEliminadas(String telefono);
}

