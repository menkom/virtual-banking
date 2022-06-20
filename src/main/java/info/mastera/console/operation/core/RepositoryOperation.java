package info.mastera.console.operation.core;

import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.util.ClassUtils;

public abstract class RepositoryOperation<T> extends BaseOperation {

    private final T repository;

    public RepositoryOperation() {
        repository = DependencyInjection.getInstance().getSingletonObject(getClazz());
    }

    @SuppressWarnings("unchecked")
    private Class<T> getClazz() {
        return (Class<T>) ClassUtils.getGenericParameter(getClass());
    }

    protected T getRepository() {
        return repository;
    }
}
