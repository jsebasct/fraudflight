package org.demo.people.flight.business;

import java.time.LocalDate;

// Usuario   comprador
// Fecha   de   compra
// Persona   a   facturar
// Destino   (puede   ser   ida   o   ida   y   vuelta)
// Fecha   de   Viaje  (si es sólo ida   será   una   sola,   si   es   ida y vuelta serán dos)
public class FlyTicket {

    private Person[] passengers;
    private CreditCard creditCard;

    private String destinationCity;
    private LocalDate flyDate;

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

    public Person[] getPassengers() {
        return passengers;
    }

    public void setPassengers(Person[] passengers) {
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

    private boolean matchIndex(int index, String lastName) {
        if (index == 0) {
            return passengers[index].getLastName().equals(lastName);
        } else {
            return passengers[index].getLastName().equals(lastName) && matchIndex(index - 1, lastName);
        }
    }

    public boolean matchPassengersLastName() {

        String lastNameTemplate = "";

        if (passengers.length >= 1) {
            lastNameTemplate = passengers[0].getLastName();
        }

        boolean res = lastNameTemplate == "" || matchIndex(passengers.length - 1, lastNameTemplate);
        return res;
    }
}
