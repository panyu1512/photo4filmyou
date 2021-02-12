package domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Money implements Serializable {

    public static final String DEFAULT_CURRENCY = "€";
    private static final long serialVersionUID = -709852238165275685L;
    private String currency;
    private BigDecimal amount;

    public Money() {
        this.amount = new BigDecimal(0);
    }
    public Money(BigDecimal amount) {
        this.currency = Money.DEFAULT_CURRENCY;
        this.amount = amount;
    }

    public Money(BigDecimal amount, String currency)
    {
        this.currency = currency;
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean Equals(Money other) {
        return other.equals(this);
    }
    @Override
    public String toString() {
        return String.format("%0$.2f %s", this.amount, this.currency);
    }
}
