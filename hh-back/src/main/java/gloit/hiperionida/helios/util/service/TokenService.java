package gloit.hiperionida.helios.util.service;


import gloit.hiperionida.helios.util.model.TokenModel;
import gloit.hiperionida.helios.util.model.UsuarioModel;

import java.util.List;

public interface TokenService {
    void guardarTokenUsuario(UsuarioModel usuario, String jwt);

    Boolean revocarTokensUsuario(UsuarioModel user);

    TokenModel buscarPorId(Long id);
    List<TokenModel> buscarTodas();
    Long contarTodas();
    TokenModel guardar(TokenModel e);
    Boolean destruir(Long id);
}
