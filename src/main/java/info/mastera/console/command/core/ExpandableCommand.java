package info.mastera.console.command.core;

import info.mastera.console.operation.core.IOperation;
import info.mastera.dependencyInjection.DependencyInjection;

import java.util.HashMap;
import java.util.Map;

public abstract class ExpandableCommand extends BaseCommand {

    private final Map<String, Class<? extends IOperation>> operations = new HashMap<>();

    protected Map<String, Class<? extends IOperation>> getOperations() {
        return operations;
    }

    @Override
    public void showHelp() {
        System.out.println("Available operations");
        operations.keySet().forEach(System.out::println);
    }

    @Override
    public boolean execute() {
        if (!super.execute()) {
            if (operations.containsKey(getParams().get(0))) {
                DependencyInjection.getInstance().getSingletonObject(operations.get(getParams().get(0))).setParams(getParams().subList(1, getParams().size())).execute();
            } else {
                showHelp();
            }
        }
        return true;
    }
}
