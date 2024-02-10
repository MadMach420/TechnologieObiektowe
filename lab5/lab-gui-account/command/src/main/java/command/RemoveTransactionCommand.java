package command;

import model.Account;
import model.Transaction;

public class RemoveTransactionCommand implements Command {
    private Transaction transactionToRemove;
    private Account account;

    public RemoveTransactionCommand(Transaction transactionToAdd, Account account) {
        this.transactionToRemove = transactionToAdd;
        this.account = account;
    }

    @Override
    public void execute() {
        account.removeTransaction(transactionToRemove);
    }

    @Override
    public String getName() {
        return "Removed transaction: " + transactionToRemove.toString();
    }
}
