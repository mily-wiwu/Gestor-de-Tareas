package excepciones;

public class TareaInvalidaException extends RuntimeException {
    public TareaInvalidaException(String message) {
        super(message);
    }
}
