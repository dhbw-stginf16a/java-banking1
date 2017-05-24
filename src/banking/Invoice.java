package banking;

class Invoice extends Transaction {
	private Account creditor;
	private Account debitor;

	public Invoice() {

	}

	@Override
	void apply() throws TransactionFailedException {

	}
}
