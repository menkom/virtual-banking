package info.mastera.console.operation;

import info.mastera.console.operation.core.RepositoryOperation;
import info.mastera.model.Person;
import info.mastera.repository.api.IPersonRepository;

public class PersonCreateOperation extends RepositoryOperation<IPersonRepository> {

    @Override
    public void showHelp() {
        System.out.println("Way of use: person create userName \n Example: person create \"Ivan Ivanov\"");
    }

    @Override
    public boolean execute() {
        if (incorrectParams(1)) {
            System.out.println("Incorrect parameters for 'create' method");
            showHelp();
        } else {
            try {
                var person = new Person()
                        .setName(getParams().get(0));
                if (getRepository().save(person)) {
                    System.out.println("Person created: " + person);
                } else {
                    System.out.println("Person not created.");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println("Person not created. " + ex.getMessage());
                return false;
            }
        }
        return true;
    }
}
