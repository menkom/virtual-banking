package info.mastera.repository;

import info.mastera.model.Transfer;
import info.mastera.repository.api.ITransferRepository;
import info.mastera.util.DateUtils;

import java.time.LocalDate;
import java.util.Collection;

public class TransferRepository extends BaseRepository<Transfer> implements ITransferRepository {

    @Override
    public Collection<Transfer> findForPeriod(LocalDate from, LocalDate to) {
        return getValues().values()
                .stream()
                .filter(transfer -> DateUtils.isDateBetween(transfer.getTime(), from, to))
                .toList();
    }
}
