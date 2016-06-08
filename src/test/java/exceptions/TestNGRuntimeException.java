package exceptions;

public class TestNGRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 629208203486252463L;

	public TestNGRuntimeException(String errorMessage) {
		super(errorMessage);
	}
}