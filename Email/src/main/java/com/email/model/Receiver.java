package com.email.model;

import jakarta.persistence.Embeddable;

import java.util.List;

@Embeddable
public class Receiver {
    private List<String> addressBCC;
    private List<String> addressCC;
    private List<String> address;

    public Receiver(List<String> addressBCC, List<String> addressCC, List<String> address) {
        this.addressBCC = addressBCC;
        this.addressCC = addressCC;
        this.address = address;
    }

    public Receiver() {

    }

    public List<String> getAddressBCC() {
        return addressBCC;
    }

    public void setAddressBCC(List<String> addressBCC) {
        this.addressBCC = addressBCC;
    }

    public List<String> getAddressCC() {
        return addressCC;
    }

    public void setAddressCC(List<String> addressCC) {
        this.addressCC = addressCC;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}