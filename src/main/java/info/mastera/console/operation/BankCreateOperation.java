package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.model.Bank;
import info.mastera.repository.api.IBankRepository;
import info.mastera.util.StringUtils;

import java.math.BigDecimal;

public class BankCreateOperation extends RepositoryOperation<IBankRepository> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: bank create bankName individualTransferRate legalTransferRate \n Example: bank create \"Bank of China\" 0.5 0.6");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(3)
                || StringUtils.notDoubleNumber(getParams().get(1))
                || StringUtils.notDoubleNumber(getParams().get(2))) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var bank = new Bank()
                        .setName(getParams().get(0))
                        .setIndividualTransferRate(new BigDecimal(getParams().get(1)))
                        .setLegalTransferRate(new BigDecimal(getParams().get(2)));
                if (getRepository().save(bank)) {
                    System.out.println("Bank created: " + bank);
                } else {
                    System.out.println("Bank not created.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Bank not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
