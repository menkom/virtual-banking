package info.mastera.console;

import info.mastera.console.command.core.ICommand;

import java.util.Map;

public interface ICommandController {

    Map<String, Class<? extends ICommand>> getCommands();
}
