package info.mastera.repository.api;

import info.mastera.model.Client;

public interface IClientRepository extends IBaseRepository<Client>{

    boolean deleteByBankId(String id);
}
