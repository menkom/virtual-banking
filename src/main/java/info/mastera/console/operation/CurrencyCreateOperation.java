package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.model.Currency;
import info.mastera.repository.api.ICurrencyRepository;

public class CurrencyCreateOperation extends RepositoryOperation<ICurrencyRepository> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: currency create currencyCode \n Example: currency create USD");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(1)) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var currency = new Currency()
                        .setCode(getParams().get(0));
                if (getRepository().save(currency)) {
                    System.out.println("Currency created: " + currency);
                } else {
                    System.out.println("Currency not created.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Currency not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
