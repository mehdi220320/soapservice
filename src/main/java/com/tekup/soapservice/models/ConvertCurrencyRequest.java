package com.tekup.soapservice.models;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "fromCurrency",
        "toCurrency",
        "amount"
})
@XmlRootElement(name = "ConvertCurrencyRequest", namespace = "http://example.com/currency")
public class ConvertCurrencyRequest {
    @XmlElement(namespace = "http://example.com/currency", required = true)
    private String fromCurrency;
    @XmlElement(namespace = "http://example.com/currency", required = true)
    private String toCurrency;
    @XmlElement(namespace = "http://example.com/currency")
    private double amount;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
