package info.mastera.console.command;

import info.mastera.console.IConsoleController;
import info.mastera.console.command.core.BaseCommand;
import info.mastera.dependencyInjection.DependencyInjection;
import org.apache.log4j.Logger;

public class ExitCommand extends BaseCommand {

    private static final Logger logger = Logger.getLogger(ExitCommand.class);

    private final IConsoleController consoleController;

    public ExitCommand() {
        consoleController = DependencyInjection.getInstance().getSingletonObject(IConsoleController.class);
    }

    @Override
    public void showHelp() {
        System.out.println("Command to close application");
    }

    @Override
    public boolean execute() {
        logger.info("Execute exit");
        consoleController.stop();
        System.out.println("Thank you for using application 'Virtual Banking'. Bye!");
        return true;
    }
}
