package info.mastera.console.command;

import info.mastera.console.command.core.ExpandableCommand;
import info.mastera.console.operation.TransferCreateOperation;
import info.mastera.console.operation.TransferFindForPeriodOperation;
import info.mastera.console.operation.TransferGetAllOperation;

public class TransferCommand extends ExpandableCommand {

    public TransferCommand() {
        getOperations().put("getAll", TransferGetAllOperation.class);
        getOperations().put("create", TransferCreateOperation.class);
        getOperations().put("find", TransferFindForPeriodOperation.class);
    }
}
