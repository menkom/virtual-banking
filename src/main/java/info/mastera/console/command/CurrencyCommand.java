package info.mastera.console.command;

import info.mastera.console.command.core.ExpandableCommand;
import info.mastera.console.operation.CurrencyCreateOperation;
import info.mastera.console.operation.CurrencyFindByIdOperation;
import info.mastera.console.operation.CurrencyGetAllOperation;

public class CurrencyCommand extends ExpandableCommand {

    public CurrencyCommand() {
        getOperations().put("getAll", CurrencyGetAllOperation.class);
        getOperations().put("findById", CurrencyFindByIdOperation.class);
        getOperations().put("create", CurrencyCreateOperation.class);
    }
}
