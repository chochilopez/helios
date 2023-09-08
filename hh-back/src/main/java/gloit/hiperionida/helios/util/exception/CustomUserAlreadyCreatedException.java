package gloit.hiperionida.helios.util.exception;

public class CustomUserAlreadyCreatedException extends RuntimeException {
    public CustomUserAlreadyCreatedException() {
        super();
    }

    public CustomUserAlreadyCreatedException(String message) {
        super(message);
    }
}