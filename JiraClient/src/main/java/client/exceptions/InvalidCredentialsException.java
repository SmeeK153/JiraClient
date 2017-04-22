package client.exceptions;

public class InvalidCredentialsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7329603662451701300L;
	
	public InvalidCredentialsException(){
		super();
	}
	
	public InvalidCredentialsException(String message){
		super(message);
	}
}
