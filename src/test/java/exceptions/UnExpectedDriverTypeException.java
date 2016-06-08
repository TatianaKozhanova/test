package exceptions;

public class UnExpectedDriverTypeException extends RuntimeException{
	
	private static final long serialVersionUID = 2062822177850698011L;

	public UnExpectedDriverTypeException(String errorMessage){
		super(errorMessage);
	}
}