package info.mastera.console.command;

import info.mastera.console.command.core.ExpandableCommand;
import info.mastera.console.operation.BankCreateOperation;
import info.mastera.console.operation.BankDeleteOperation;
import info.mastera.console.operation.BankFindByIdOperation;
import info.mastera.console.operation.BankGetAllOperation;

public class BankCommand extends ExpandableCommand {

    public BankCommand() {
        getOperations().put("getAll", BankGetAllOperation.class);
        getOperations().put("findById", BankFindByIdOperation.class);
        getOperations().put("create", BankCreateOperation.class);
        getOperations().put("delete", BankDeleteOperation.class);
    }
}
