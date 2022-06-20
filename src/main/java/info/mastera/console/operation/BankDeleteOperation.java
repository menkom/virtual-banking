package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.repository.api.IAccountRepository;
import info.mastera.repository.api.IBankRepository;
import info.mastera.repository.api.IClientRepository;

public class BankDeleteOperation extends RepositoryOperation<IBankRepository> {

    private final IAccountRepository accountRepository;
    private final IClientRepository clientRepository;

    public BankDeleteOperation() {
        super();
        accountRepository = DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class);
        clientRepository = DependencyInjection.getInstance().getSingletonObject(IClientRepository.class);
    }

    @Override
    public void showHelp() {
        System.out.println("Way of use: bank delete bankId \n Example: bank delete 24");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(1)) {
            System.out.println("Incorrect parameters for 'delete' method");
            showHelp();
        } else {
            if (accountRepository.deleteByBankId(getParams().get(0))
                    && clientRepository.deleteByBankId(getParams().get(0))
                    && getRepository().delete(getParams().get(0))) {
                System.out.println("Bank deleted.");
            } else {
                System.out.println("Bank was not deleted.");
            }
        }
        return true;
    }
}
