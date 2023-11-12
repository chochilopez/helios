package gloit.hiperionida.helios.service;

import gloit.hiperionida.helios.mapper.creation.DireccionCreation;
import gloit.hiperionida.helios.model.CiudadModel;
import gloit.hiperionida.helios.model.DireccionModel;
import gloit.hiperionida.helios.util.service.GenericService;

import java.util.List;

public interface DireccionService extends GenericService<DireccionModel, DireccionCreation> {
    List<CiudadModel> buscarTodasCiudad();

    List<DireccionModel> buscarTodasPorCiudad(String ciudad);
    List<DireccionModel> buscarTodasPorCiudadConEliminadas(String ciudad);
    List<DireccionModel> buscarTodasPorDireccion(String direccion);
    List<DireccionModel> buscarTodasPorDireccionConEliminadas(String direccion);
    List<DireccionModel> buscarTodasPorNombre(String nombre);
    List<DireccionModel> buscarTodasPorNombreConEliminadas(String nombre);
    List<DireccionModel> buscarTodasPorNotas(String notas);
    List<DireccionModel> buscarTodasPorNotasConEliminadas(String notas);
}

