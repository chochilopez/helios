package gloit.hiperionida.helios.util.exception;

public class UsuarioExistenteException extends RuntimeException {
    public UsuarioExistenteException() {
        super();
    }

    public UsuarioExistenteException(String message) {
        super(message);
    }
}