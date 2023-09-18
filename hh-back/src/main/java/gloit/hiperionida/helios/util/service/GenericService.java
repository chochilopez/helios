package gloit.hiperionida.helios.util.service;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.List;

public interface GenericService<M, C> {
    M buscarPorId(Long id);
    M buscarPorIdConEliminadas(Long id);
    List<M> buscarTodas();
    List<M> buscarTodasConEliminadas();
    Slice<M> buscarTodasPorOrdenPorPagina(String direccion, String campo, int pagina, int elementos);
    Slice<M> buscarTodasPorOrdenPorPaginaConEliminadas(String direccion, String campo, int pagina, int elementos);
    Long contarTodas();
    Long contarTodasConEliminadas();
    M guardar(C c);
    M reciclar(Long id);
    M eliminar(Long id);
    void destruir(Long id) throws IOException;
}