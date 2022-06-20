package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.model.Account;
import info.mastera.repository.api.IAccountRepository;

import java.math.BigDecimal;

public class AccountCreateOperation extends RepositoryOperation<IAccountRepository> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: account create currencyId clientId \n Example: account create 12 5");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(2)) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var account = new Account()
                        .setBalance(BigDecimal.ZERO)
                        .setCurrencyId(getParams().get(0))
                        .setClientId(getParams().get(1));
                account.setBankId(account.getClient().getBankId());
                var bank = account.getBank();
                if (!bank.isActive()) {
                    System.out.printf("Bank %s with id=%s is inactive. Can't create account%n", bank.getName(), bank.getId());
                    return false;
                }
                var client = account.getClient();
                if (!client.isActive()) {
                    System.out.printf("Client id=%s is inactive. Can't create account%n", client.getId());
                    return false;
                }
                if (getRepository().save(account)) {
                    System.out.println("Account created: " + account);
                } else {
                    System.out.println("Account not created.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Account not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
