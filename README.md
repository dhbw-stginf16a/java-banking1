# BankSimulation

This is an exercise for the course `Programming 2` at the Baden-Wuerttemberg Cooperative State University Stuttgart (DHBW Stuttgart).

The base specification was is given by `res/BankSimulation.pdf`.

# Class design
The first draft of the class structure in a UML class diagram is at `res/bank.dia`

# Functionality

- guaranteed
  - Create a customer new Customer()
    - Bank#addCustomer(customer)
  - Create an account for customer by customerId
  - Deposit
  - Invoice
  - Withdraw
- needed
  - show Accounts of customer
  - show balance of account
  - show transation log
  - show all customers
  - tick at year's end - apply interest
- optional
  - loan details
  - close account
  - save everything to file/database

# Issues
- Throw UnsupportedOperationException for action that subclasses of account don't support.
- Define SAVING_INTEREST, OVERDRAFT, BORROWING_INTEREST
- Specify details in javadoc for compareTo of DateTime and Money
- setup account Erik and Daniel
- javadoc for constructors of subclasses of Account
- add javadoc for methods and fields without
