package exception;

public class Excessao extends RuntimeException{
    private static final long seriaVersionlUID = 1L;

    public Excessao(String msg) {
        super(msg);
    }
}
