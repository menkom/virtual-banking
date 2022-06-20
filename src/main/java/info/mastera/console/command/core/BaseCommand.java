package info.mastera.console.command.core;

import org.apache.log4j.Logger;

import java.util.List;

public abstract class BaseCommand implements ICommand {

    private static final Logger logger = Logger.getLogger(BaseCommand.class);

    private static final String HELP_PARAMETER = "?";

    List<String> params;

    @Override
    public ICommand setParams(List<String> params) {
        this.params = params;
        return this;
    }

    protected List<String> getParams() {
        return params;
    }

    @Override
    public boolean execute() {
        logger.info("Execute with params:" + params);
        if (params == null || params.isEmpty() || params.get(0) == null || params.get(0).trim().equals(HELP_PARAMETER)) {
            showHelp();
            return true;
        }
        return false;
    }
}
