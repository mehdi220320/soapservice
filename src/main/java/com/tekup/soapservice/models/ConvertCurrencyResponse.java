package com.tekup.soapservice.models;


import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "convertedAmount"
})
@XmlRootElement(name = "ConvertCurrencyResponse", namespace = "http://example.com/currency")
public class ConvertCurrencyResponse {
    @XmlElement(namespace = "http://example.com/currency")
    private double convertedAmount;

    public double getConvertedAmount() {
        return convertedAmount;
    }

    public void setConvertedAmount(double convertedAmount) {
        this.convertedAmount = convertedAmount;
    }


}
