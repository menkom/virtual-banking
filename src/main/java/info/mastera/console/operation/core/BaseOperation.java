package info.mastera.console.operation.core;

import java.util.List;
import java.util.Objects;

public abstract class BaseOperation implements IOperation {

    List<String> params;

    protected List<String> getParams() {
        return params;
    }

    @Override
    public IOperation setParams(List<String> params) {
        this.params = params;
        return this;
    }

    protected boolean incorrectParams(int numberOfParams) {
        if (numberOfParams == 0) {
            return false;
        }
        return getParams() == null || getParams().isEmpty() || getParams().size() < numberOfParams
                || getParams().stream().anyMatch(Objects::isNull);
    }
}
