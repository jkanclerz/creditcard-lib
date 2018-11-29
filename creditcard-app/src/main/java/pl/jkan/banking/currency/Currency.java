package pl.jkan.banking.currency;

public enum Currency {
    EURO("EUR"),
    USD("USD");

    private String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
