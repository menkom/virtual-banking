package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.repository.api.IAccountRepository;
import info.mastera.repository.api.IBankRepository;

public class ClientDeleteOperation extends RepositoryOperation<IBankRepository> {

    private final IAccountRepository accountRepository;

    public ClientDeleteOperation() {
        super();
        accountRepository = DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class);
    }

    public void showHelp() {
        System.out.println("Way of use: client delete clientId \n Example: client delete 24");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(1)) {
            System.out.println("Incorrect parameters for 'delete' method");
            showHelp();
        } else {
            if (accountRepository.deleteByClientId(getParams().get(0))
                    && getRepository().delete(getParams().get(0))) {
                System.out.println("Client deleted.");
            } else {
                System.out.println("Client was not deleted.");
            }
        }
        return true;
    }
}
