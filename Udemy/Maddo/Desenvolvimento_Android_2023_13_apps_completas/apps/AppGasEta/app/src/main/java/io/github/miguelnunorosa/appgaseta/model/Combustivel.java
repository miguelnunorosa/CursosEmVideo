package io.github.miguelnunorosa.appgaseta.model;

public class Combustivel {

    private String fuelTipe, suggestion;
    private double fuelPrice;



    public String getFuelTipe() {
        return fuelTipe;
    }


    public void setFuelTipe(String fuelTipe) {
        this.fuelTipe = fuelTipe;
    }


    public String getSuggestion() {
        return suggestion;
    }


    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }


    public double getFuelPrice() {
        return fuelPrice;
    }


    public void setFuelPrice(double fuelPrice) {
        this.fuelPrice = fuelPrice;
    }


}
