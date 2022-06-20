package info.mastera.console.command;

import info.mastera.console.command.core.ExpandableCommand;
import info.mastera.console.operation.AccountFindByIdOperation;
import info.mastera.console.operation.AccountGetAllOperation;
import info.mastera.console.operation.AccountCreateOperation;

public class AccountCommand extends ExpandableCommand {

    public AccountCommand() {
        getOperations().put("getAll", AccountGetAllOperation.class);
        getOperations().put("findById", AccountFindByIdOperation.class);
        getOperations().put("create", AccountCreateOperation.class);
    }
}
