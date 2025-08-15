package bank_operation;

public class InsufficientBalance extends Exception {

	public InsufficientBalance(String message) {
		super(message);
	}

}
