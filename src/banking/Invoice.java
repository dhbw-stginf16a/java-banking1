package banking;

class Invoice extends Transaction {
	public Account creditor;
	public Account debitor;

	public Invoice() {

	}

	@Override
	void apply() throws TransactionFailedException {

	}
}
