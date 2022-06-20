package info.mastera.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import info.mastera.dependencyInjection.DependencyInjection;
import info.mastera.repository.api.IAccountRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transfer extends BaseEntity {

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime time;

    BigDecimal amount;

    String fromId;
    @JsonIgnore
    Account from;

    String toId;
    @JsonIgnore
    Account to;

    public LocalDateTime getTime() {
        return time;
    }

    public Transfer setTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transfer setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public String getFromId() {
        return fromId;
    }

    public Transfer setFromId(String fromId) {
        DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class)
                .findById(fromId)
                .ifPresentOrElse(item -> {
                            this.from = item;
                            this.fromId = fromId;
                        },
                        () -> {
                            throw new IllegalArgumentException("No account was found with id=" + fromId);
                        });
        return this;
    }

    public Account getFrom() {
        if (from == null && fromId != null) {
            DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class)
                    .findById(fromId)
                    .ifPresentOrElse(item -> {
                                this.from = item;
                            },
                            () -> {
                                throw new IllegalArgumentException("No account was found with id=" + fromId);
                            });
        }
        return from;
    }

    public String getToId() {
        return toId;
    }

    public Transfer setToId(String toId) {
        DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class)
                .findById(toId)
                .ifPresentOrElse(item -> {
                            this.to = item;
                            this.toId = toId;
                        },
                        () -> {
                            throw new IllegalArgumentException("No account was found with id=" + toId);
                        });
        return this;
    }

    public Account getTo() {
        if (to == null && toId != null) {
            DependencyInjection.getInstance().getSingletonObject(IAccountRepository.class)
                    .findById(toId)
                    .ifPresentOrElse(item -> {
                                this.to = item;
                            },
                            () -> {
                                throw new IllegalArgumentException("No account was found with id=" + toId);
                            });
        }
        return to;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + getId() +
                ", time=" + time +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", amount=" + amount +
                '}';
    }
}
