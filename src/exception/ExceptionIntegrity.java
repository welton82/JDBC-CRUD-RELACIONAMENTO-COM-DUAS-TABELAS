package exception;

public class ExceptionIntegrity extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ExceptionIntegrity(String msg) {
        super(msg);
    }
}
