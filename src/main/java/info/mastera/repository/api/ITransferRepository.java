package info.mastera.repository.api;

import info.mastera.model.Transfer;

import java.time.LocalDate;
import java.util.Collection;

public interface ITransferRepository extends IBaseRepository<Transfer> {

    Collection<Transfer> findForPeriod(LocalDate from, LocalDate to);
}
