package info.mastera.model;

import java.math.BigDecimal;

public class Bank extends BaseEntity {

    private String name;
    private BigDecimal individualTransferRate;
    private BigDecimal legalTransferRate;

    public String getName() {
        return name;
    }

    public Bank setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getIndividualTransferRate() {
        return individualTransferRate;
    }

    public Bank setIndividualTransferRate(BigDecimal individualTransferRate) {
        this.individualTransferRate = individualTransferRate;
        return this;
    }

    public BigDecimal getLegalTransferRate() {
        return legalTransferRate;
    }

    public Bank setLegalTransferRate(BigDecimal legalTransferRate) {
        this.legalTransferRate = legalTransferRate;
        return this;
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", individualTransferRate=" + individualTransferRate +
                ", legalTransferRate=" + legalTransferRate +
                "} ";
    }
}
