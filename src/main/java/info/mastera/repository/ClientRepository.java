package info.mastera.repository;

import info.mastera.model.Client;
import info.mastera.repository.api.IClientRepository;

public class ClientRepository extends BaseRepository<Client> implements IClientRepository {

    @Override
    public boolean deleteByBankId(String id) {
        getValues().replaceAll(
                (key, oldValue) -> oldValue.getBankId().equals(id)
                        ? (Client) oldValue.setActive(false)
                        : oldValue
        );
        return true;
    }
}
