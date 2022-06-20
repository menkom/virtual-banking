package info.mastera.console.command;

import info.mastera.console.command.core.ExpandableCommand;
import info.mastera.console.operation.ClientCreateOperation;
import info.mastera.console.operation.ClientDeleteOperation;
import info.mastera.console.operation.ClientFindByIdOperation;
import info.mastera.console.operation.ClientGetAllOperation;

public class ClientCommand extends ExpandableCommand {

    public ClientCommand() {
        getOperations().put("getAll", ClientGetAllOperation.class);
        getOperations().put("findById", ClientFindByIdOperation.class);
        getOperations().put("create", ClientCreateOperation.class);
        getOperations().put("delete", ClientDeleteOperation.class);
    }
}
