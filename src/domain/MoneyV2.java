package domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class MoneyV2 implements Serializable {

    public static final String DEFAULT_CURRENCY = "€";
    private static final long serialVersionUID = -709852238165275685L;
    private String currency;
    private BigDecimal amount;

    public MoneyV2() {
        this.amount = new BigDecimal(0);
    }
    public MoneyV2(BigDecimal amount) {
        this.currency = MoneyV2.DEFAULT_CURRENCY;
        this.amount = amount;
    }

    public MoneyV2(BigDecimal amount, String currency)
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
