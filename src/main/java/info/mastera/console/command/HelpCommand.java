package info.mastera.console.command;

import info.mastera.console.ICommandController;
import info.mastera.console.command.core.BaseCommand;
import info.mastera.dependencyInjection.DependencyInjection;

public class HelpCommand extends BaseCommand {

    private final ICommandController commandController;

    public HelpCommand() {
        commandController = DependencyInjection.getInstance().getSingletonObject(ICommandController.class);
    }

    @Override
    public void showHelp() {
        System.out.println("List of available commands");
        var commands = commandController.getCommands();
        commands.keySet().forEach(System.out::println);
    }
}
