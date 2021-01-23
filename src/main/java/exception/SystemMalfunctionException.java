package exception;

@SuppressWarnings("serial")
public class SystemMalfunctionException extends RuntimeException {
    public SystemMalfunctionException(String msg) {
        super(msg);
    }

}
