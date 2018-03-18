package org.demo.people.flight.business;

import java.time.LocalDate;

public class FlyTicket {

    private Person[] passengers;
    private CreditCard creditCard;


    private String destinationCity;
    private LocalDate flyDate;
    private int purchaseAmount;

//● Usuario   comprador
//● Fecha   de   compra
//● Pasajero/s

//● Titular   de   la   tarjeta   de   crédito
//● Número   de   tarjeta   de   crédito

//● Persona   a   facturar
//● Destino   (puede   ser   ida   o   ida   y   vuelta)
//● Fecha   de   Viaje   (si   es   sólo   ida   será   una   sola,   si   es   ida   y   vuelta   serán   dos)
//● Importe   de   la   compra

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
}
