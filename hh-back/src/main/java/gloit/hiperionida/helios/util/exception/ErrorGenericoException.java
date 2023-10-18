package gloit.hiperionida.helios.util.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorGenericoException extends RuntimeException {
    private HttpStatus status = null;

    private Object data = null;

    public ErrorGenericoException() {
        super();
    }

    public ErrorGenericoException(String message) {
        super(message);
    }

    public ErrorGenericoException(HttpStatus status, String message) {
        this(message);
        this.status = status;
    }

    public ErrorGenericoException(HttpStatus status, String message, Object data) {
        this(status, message);
        this.data = data;
    }
}