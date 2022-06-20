package info.mastera.console.operation.core;

import java.util.List;

public interface IOperation {

    void showHelp();

    boolean execute();

    IOperation setParams(List<String> params);
}
