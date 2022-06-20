package info.mastera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.repository.api.IBankRepository;
import info.mastera.repository.api.IClientRepository;
import info.mastera.repository.api.ICurrencyRepository;

import java.math.BigDecimal;

public class Account extends BaseEntity {

    String currencyId;
    @JsonIgnore
    Currency currency;
    BigDecimal balance;

    String bankId;
    @JsonIgnore
    Bank bank;

    String clientId;
    @JsonIgnore
    Client client;

    public String getCurrencyId() {
        return currencyId;
    }

    public Account setCurrencyId(String currencyId) {
        DependencyInjection.getInstance().getSingletonObject(ICurrencyRepository.class)
                .findById(currencyId)
                .ifPresentOrElse(item -> {
                            this.currency = item;
                            this.currencyId = currencyId;
                        },
                        () -> {
                            throw new IllegalArgumentException("No currency was found with id=" + currencyId);
                        });
        return this;
    }

    public Currency getCurrency() {
        if (currency == null && currencyId != null) {
            DependencyInjection.getInstance().getSingletonObject(ICurrencyRepository.class)
                    .findById(currencyId)
                    .ifPresentOrElse(item -> {
                                this.currency = item;
                            },
                            () -> {
                                throw new IllegalArgumentException("No currency was found with id=" + currencyId);
                            });
        }
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Account setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    public Account addBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this;
    }

    public Account subtractBalance(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
        return this;
    }

    public String getBankId() {
        return bankId;
    }

    public Account setBankId(String bankId) {
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

    public String getClientId() {
        return clientId;
    }

    public Account setClientId(String clientId) {
        DependencyInjection.getInstance().getSingletonObject(IClientRepository.class)
                .findById(clientId)
                .ifPresentOrElse(item -> {
                            this.client = item;
                            this.clientId = clientId;
                        },
                        () -> {
                            throw new IllegalArgumentException("No client was found with id=" + clientId);
                        }
                );
        return this;
    }

    public Client getClient() {
        if (client == null && clientId != null) {
            DependencyInjection.getInstance().getSingletonObject(IClientRepository.class)
                    .findById(clientId)
                    .ifPresentOrElse(item -> {
                                this.client = item;
                            },
                            () -> {
                                throw new IllegalArgumentException("No client was found with id=" + clientId);
                            }
                    );
        }
        return client;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + getId() +
                ", currency='" + currency + '\'' +
                ", balance=" + balance +
                ", bank='" + bank + '\'' +
                ", client='" + client + '\'' +
                "} ";
    }
}
