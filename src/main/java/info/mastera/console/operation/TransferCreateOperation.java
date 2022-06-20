package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.model.Transfer;
import info.mastera.repository.api.IAccountRepository;
import info.mastera.repository.api.ITransferRepository;
import info.mastera.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferCreateOperation extends RepositoryOperation<ITransferRepository> {

    private final IAccountRepository accountRepository;

    public TransferCreateOperation() {
        super();
        this.accountRepository = DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class);
    }

    @Override
    public void showHelp() {
        System.out.println("Way of use: transfer create fromAccountId toAccountId amount \n Example: transfer create 21 4 15.7");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(3) || StringUtils.notDoubleNumber(getParams().get(2))) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var amount = new BigDecimal(getParams().get(2));
                var transfer = new Transfer()
                        .setTime(LocalDateTime.now())
                        .setFromId(getParams().get(0))
                        .setToId(getParams().get(1))
                        .setAmount(amount);
                var fromAccount = transfer.getFrom();
                if (!fromAccount.isActive()) {
                    System.out.println("Can't create transfer. Outgoing account is inactive.");
                    return false;
                }
                var toAccount = transfer.getTo();
                if (!toAccount.isActive()) {
                    System.out.println("Can't create transfer. Recipient account is inactive.");
                    return false;
                }
                if (!fromAccount.getCurrencyId().equals(toAccount.getCurrencyId())) {
                    System.out.println("Can't create transfer. Accounts use different currency.");
                    return false;
                }
                if (accountRepository.transfer(getParams().get(0), getParams().get(1), amount) && getRepository().save(transfer)) {
                    System.out.println("Transfer created: " + transfer);
                } else {
                    System.out.println("Transfer not created.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Transfer not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
