package info.mastera.repository.api;

import info.mastera.model.Account;

import java.math.BigDecimal;

public interface IAccountRepository extends IBaseRepository<Account> {

    boolean deleteByBankId(String id);

    boolean deleteByClientId(String id);

    boolean transfer(String fromAccountId, String toAccountId, BigDecimal amount);
}
