package info.mastera.console.operation.core;

import info.mastera.model.BaseEntity;
import info.mastera.repository.api.IBaseRepository;

public abstract class GetAllOperation<T extends IBaseRepository<C>, C extends BaseEntity> extends RepositoryOperation<T> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: ENTITY getAll");
    }

    @Override
    public boolean execute() {
        var values = getRepository().getAll();
        if (values.isEmpty()) {
            System.out.println("No records found.");
        } else {
            values.forEach(System.out::println);
        }
        return true;
    }
}
