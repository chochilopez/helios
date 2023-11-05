package gloit.hiperionida.helios.util.service;

import gloit.hiperionida.helios.util.mapper.creation.RolCreation;
import gloit.hiperionida.helios.util.model.RolModel;
import org.springframework.data.domain.Slice;

import java.io.IOException;
import java.util.List;

public interface RolService {
    RolModel buscarPorRol(String rol);
    RolModel buscarPorId(Long id);
    List<RolModel> buscarTodas();
    RolModel guardar(RolCreation rolCreation);
    void destruir(Long id) throws IOException;
}
