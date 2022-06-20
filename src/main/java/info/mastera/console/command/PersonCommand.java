package info.mastera.console.command;

import info.mastera.console.command.core.ExpandableCommand;
import info.mastera.console.operation.PersonCreateOperation;
import info.mastera.console.operation.PersonFindByIdOperation;
import info.mastera.console.operation.PersonGetAllOperation;

public class PersonCommand extends ExpandableCommand {

    public PersonCommand() {
        getOperations().put("getAll", PersonGetAllOperation.class);
        getOperations().put("findById", PersonFindByIdOperation.class);
        getOperations().put("create", PersonCreateOperation.class);
    }
}
