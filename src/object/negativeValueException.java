package object;

public class negativeValueException extends RuntimeException {
	 
	public negativeValueException() {
		super();
		System.out.println("NegativeValueException.");
	}
	
	public negativeValueException( String string ) {
		super( string ) ;
		System.out.println("NegativeValueException : " + string );
	}
	
	public negativeValueException( String string , Throwable throwable ) {
		super( string , throwable ) ;
		System.out.println("NegativeValueException : " + string + " with : " + throwable.toString() );

	}
	
	public negativeValueException( Throwable throwable ) {
		super( throwable ) ;
		System.out.println("NegativeValueException with " + throwable.toString() );

	}

}
