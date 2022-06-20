package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.repository.api.ITransferRepository;
import info.mastera.util.DateUtils;
import info.mastera.util.StringUtils;

public class TransferFindForPeriodOperation extends RepositoryOperation<ITransferRepository> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: transfer find fromDate toDate \n Example: transfer find 2022-05-11 2022-07-15");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(2)
                && StringUtils.notDate(getParams().get(0))
                && StringUtils.notDate(getParams().get(1))) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var fromDate = DateUtils.convert(getParams().get(0));
                var toDate = DateUtils.convert(getParams().get(1));
                getRepository().findForPeriod(fromDate, toDate)
                        .forEach(System.out::println);
            } catch (IllegalArgumentException ex) {
                System.out.println("Transfer not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
