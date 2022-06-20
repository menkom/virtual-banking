package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.model.Account;
import info.mastera.model.Client;
import info.mastera.repository.api.IAccountRepository;
import info.mastera.repository.api.IClientRepository;
import info.mastera.util.StringUtils;

import java.math.BigDecimal;

public class ClientCreateOperation extends RepositoryOperation<IClientRepository> {

    private final IAccountRepository accountRepository;

    public ClientCreateOperation() {
        super();
        this.accountRepository = DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class);
    }

    @Override
    public void showHelp() {
        System.out.println("Way of use: client create isIndividual bankId personId defaultCurrencyId \n Example: client create true 12 21 4");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(4)
                || StringUtils.notBoolean(getParams().get(0))) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var client = new Client()
                        .setIndividual(Boolean.parseBoolean(getParams().get(0)))
                        .setBankId(getParams().get(1))
                        .setPersonId(getParams().get(2));
                var bank = client.getBank();
                if (!bank.isActive()) {
                    System.out.printf("Bank %s with id=%s is inactive. Can't create account%n", bank.getName(), bank.getId());
                    return false;
                }
                var account = new Account()
                        .setBalance(BigDecimal.ZERO)
                        .setCurrencyId(getParams().get(3))
                        .setBankId(getParams().get(1));


                if (getRepository().save(client)) {
                    System.out.println("Client created: " + client);
                    account.setClientId(client.getId());
                    if (accountRepository.save(account)) {
                        System.out.println("Default account created: " + account);
                    } else {
                        System.out.println("Default account not created.");
                    }
                } else {
                    System.out.println("Client not created.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Client not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
