package org.demo.people.flight.business;

import java.time.LocalDate;

public class FlyTicket {

//    private String creditCardLastNameTitular;
    private CreditCard creditCard;

    private String destinationCity;
    private LocalDate flyDate;
    private Passenger[] passengers;

    private int purchaseAmount;


    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public LocalDate getFlyDate() {
        return flyDate;
    }

    public void setFlyDate(LocalDate flyDate) {
        this.flyDate = flyDate;
    }

    public Passenger[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Passenger[] passengers) {
        this.passengers = passengers;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }
}
