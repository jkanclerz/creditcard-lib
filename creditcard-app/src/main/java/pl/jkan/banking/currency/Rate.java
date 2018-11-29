package pl.jkan.banking.currency;

import java.math.BigDecimal;

public class Rate {
    private String code;
    private BigDecimal exchange;

    public Rate(String code, BigDecimal exchange) {
        this.code = code;
        this.exchange = exchange;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getExchange() {
        return exchange;
    }
}
