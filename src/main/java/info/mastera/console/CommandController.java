package info.mastera.console;

import info.mastera.console.command.*;
import info.mastera.console.command.core.ICommand;
import info.mastera.dependencyInjection.DependencyInjection;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandController implements ICommandController {

    private final static Logger logger = Logger.getLogger(CommandController.class);
    private static final Pattern COMMAND_SPLITTER_PATTERN = Pattern.compile("([^\"]\\S*|\".+?\")\\s*");

    private final Map<String, Class<? extends ICommand>> commands = new HashMap<>();

    public CommandController() {
        commands.put("account", AccountCommand.class);
        commands.put("bank", BankCommand.class);
        commands.put("client", ClientCommand.class);
        commands.put("currency", CurrencyCommand.class);
        commands.put("person", PersonCommand.class);
        commands.put("transfer", TransferCommand.class);
        commands.put("help", HelpCommand.class);
        commands.put("exit", ExitCommand.class);
    }

    @Override
    public Map<String, Class<? extends ICommand>> getCommands() {
        return commands;
    }

    public void execute(String commandLine) {
        logger.info(String.format("Processing command line: %s", commandLine));
        if (commandLine.trim().isEmpty()) {
            System.out.println(TextResources.HELP);
        } else {
            var commandWithParams = getCommandParameters(commandLine);
            var command = commandWithParams.get(0);
            var params = commandWithParams.subList(1, commandWithParams.size());
            if (commands.containsKey(command)) {
                DependencyInjection.getInstance().getSingletonObject(commands.get(command)).setParams(params).execute();
            } else {
                System.out.printf("No command %s was found. Enter 'help' to see commands list%n", command);
            }
        }
    }

    private List<String> getCommandParameters(String commandLine) {
        List<String> commandParameters = new ArrayList<>();
        Matcher matcher = COMMAND_SPLITTER_PATTERN.matcher(commandLine);
        while (matcher.find()) {
            commandParameters.add(matcher.group(1));
        }
        return commandParameters;
    }
}
