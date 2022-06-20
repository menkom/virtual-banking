package info.mastera.datasource;

import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.repository.api.*;

public class Datasource implements IDatasource {

    private final IAccountRepository accountRepository;
    private final IBankRepository bankRepository;
    private final IClientRepository clientRepository;
    private final ICurrencyRepository currencyRepository;
    private final IPersonRepository personRepository;
    private final ITransferRepository transferRepository;

    public Datasource() {
        currencyRepository = DependencyInjection.getInstance().getSingletonObject(ICurrencyRepository.class);
        bankRepository = DependencyInjection.getInstance().getSingletonObject(IBankRepository.class);
        clientRepository = DependencyInjection.getInstance().getSingletonObject(IClientRepository.class);
        accountRepository = DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class);
        transferRepository = DependencyInjection.getInstance().getSingletonObject(ITransferRepository.class);
        personRepository = DependencyInjection.getInstance().getSingletonObject(IPersonRepository.class);
    }

    @Override
    public void loadData() {
        personRepository.importData();
        currencyRepository.importData();
        bankRepository.importData();
        clientRepository.importData();
        accountRepository.importData();
        transferRepository.importData();
    }

    @Override
    public void saveData() {
        transferRepository.saveData();
        accountRepository.saveData();
        clientRepository.saveData();
        bankRepository.saveData();
        currencyRepository.saveData();
        personRepository.saveData();
    }
}
