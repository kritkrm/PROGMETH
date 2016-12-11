package core;

public class NegativeValueException extends RuntimeException {
	 
	public NegativeValueException() {
		super();
		System.out.println("NegativeValueException.");
	}
	
	public NegativeValueException( String string ) {
		super( string ) ;
		System.out.println("NegativeValueException : " + string );
	}
	
	public NegativeValueException( String string , Throwable throwable ) {
		super( string , throwable ) ;
		System.out.println("NegativeValueException : " + string + " with : " + throwable.toString() );

	}
	
	public NegativeValueException( Throwable throwable ) {
		super( throwable ) ;
		System.out.println("NegativeValueException with " + throwable.toString() );

	}

}
