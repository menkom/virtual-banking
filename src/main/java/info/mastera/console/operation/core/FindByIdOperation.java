package info.mastera.console.operation.core;

import info.mastera.model.BaseEntity;
import info.mastera.repository.api.IBaseRepository;

public abstract class FindByIdOperation<T extends IBaseRepository<C>, C extends BaseEntity> extends RepositoryOperation<T> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: ENTITY findById idNumber \n Example: ENTITY findById 123");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(1)) {
            System.out.println("Incorrect parameters for 'findById' method");
            showHelp();
        } else {
            getRepository().findById(getParams().get(0))
                    .ifPresentOrElse(
                            System.out::println,
                            () -> System.out.println("No data for id=" + getParams().get(0))
                    );
        }
        return true;
    }
}
