package info.mastera.console.command.core;

import java.util.List;

public interface ICommand {

    void showHelp();

    ICommand setParams(List<String> params);

    boolean execute();

}
