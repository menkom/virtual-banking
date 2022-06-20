package info.mastera.repository;

import info.mastera.model.Account;
import info.mastera.repository.api.IAccountRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class AccountRepository extends BaseRepository<Account> implements IAccountRepository {

    @Override
    public boolean deleteByBankId(String id) {
        getValues().replaceAll(
                (key, oldValue) -> oldValue.getBankId().equals(id)
                        ? (Account) oldValue.setActive(false)
                        : oldValue
        );
        return true;
    }

    @Override
    public boolean deleteByClientId(String id) {
        getValues().replaceAll(
                (key, oldValue) -> oldValue.getClientId().equals(id)
                        ? (Account) oldValue.setActive(false)
                        : oldValue
        );
        return true;
    }

    @Override
    public boolean transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        var fromAccount = Optional.ofNullable(getValues().get(fromAccountId))
                .orElseThrow(() -> new IllegalArgumentException("Can't find outgoing account id=" + fromAccountId));
        var toAccount = Optional.ofNullable(getValues().get(toAccountId))
                .orElseThrow(() -> new IllegalArgumentException("Can't find recipient account id=" + toAccountId));
        getValues().put(fromAccountId, fromAccount.subtractBalance(amount));
        if (fromAccount.getBankId().equals(toAccount.getBankId())) {
            getValues().put(toAccountId, toAccount.addBalance(amount));
        } else {
            getValues().put(toAccountId,
                    toAccount.addBalance(
                            amount.subtract(amount.multiply(
                                    fromAccount.getClient().isIndividual()
                                            ? fromAccount.getBank().getIndividualTransferRate()
                                            : fromAccount.getBank().getLegalTransferRate())
                            )
                    ));
        }
        return true;
    }
}
