package info.mastera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.repository.api.IBankRepository;
import info.mastera.repository.api.IPersonRepository;

public class Client extends BaseEntity {

    boolean individual;

    String bankId;
    @JsonIgnore
    Bank bank;

    String personId;
    @JsonIgnore
    Person person;

    public boolean isIndividual() {
        return individual;
    }

    public Client setIndividual(boolean individual) {
        this.individual = individual;
        return this;
    }

    public String getBankId() {
        return bankId;
    }

    public Client setBankId(String bankId) {
        DependencyInjection.getInstance().getSingletonObject(IBankRepository.class)
                .findById(bankId)
                .ifPresentOrElse(item -> {
                            this.bank = item;
                            this.bankId = bankId;
                        },
                        () -> {
                            throw new IllegalArgumentException("No bank was found with id=" + bankId);
                        }
                );
        return this;
    }

    public Bank getBank() {
        if (bank == null && bankId != null) {
            DependencyInjection.getInstance().getSingletonObject(IBankRepository.class)
                    .findById(bankId)
                    .ifPresentOrElse(item -> {
                                this.bank = item;
                            },
                            () -> {
                                throw new IllegalArgumentException("No bank was found with id=" + bankId);
                            }
                    );
        }
        return bank;
    }

    public String getPersonId() {
        return personId;
    }

    public Client setPersonId(String personId) {
        DependencyInjection.getInstance().getSingletonObject(IPersonRepository.class)
                .findById(personId)
                .ifPresentOrElse(item -> {
                            this.person = item;
                            this.personId = personId;
                        },
                        () -> {
                            throw new IllegalArgumentException("No person was found with id=" + personId);
                        }
                );
        return this;
    }

    public Person getPerson() {
        if (person == null && personId != null) {
            DependencyInjection.getInstance().getSingletonObject(IPersonRepository.class)
                    .findById(personId)
                    .ifPresentOrElse(item -> {
                                this.person = item;
                            },
                            () -> {
                                throw new IllegalArgumentException("No person was found with id=" + personId);
                            }
                    );
        }
        return person;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + getId() +
                ", individual=" + individual +
                ", bank='" + bank + '\'' +
                ", person='" + person + '\'' +
                "} ";
    }
}
