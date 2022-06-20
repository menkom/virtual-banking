package info.mastera.console;

import info.mastera.console.util.InputReader;
import info.mastera.datasource.IDatasource;
import info.mastera.dependencyInjection.DependencyInjection;

public class ConsoleController implements IConsoleController {

    private boolean inProgress = false;

    private final InputReader inputReader;
    private final CommandController commandController;
    private final IDatasource datasource;

    public ConsoleController() {
        inputReader = DependencyInjection.getInstance().getSingletonObject(InputReader.class);
        commandController = DependencyInjection.getInstance().getSingletonObject(CommandController.class);
        datasource = DependencyInjection.getInstance().getSingletonObject(IDatasource.class);
    }

    @Override
    public void run() {
        datasource.loadData();
        inProgress = true;
        while (inProgress) {
            var commandLine = inputReader.readCommand("Enter command:");
            commandController.execute(commandLine);
        }
        datasource.saveData();
    }

    @Override
    public void stop() {
        inProgress = false;
    }
}
