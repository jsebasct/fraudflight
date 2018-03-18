package org.demo.people.flight.business;

import java.time.LocalDate;

public class FlyTicket {

    private Long creditCardNumber;
    private String destinationCity;
    private LocalDate flyDate;
    private Passenger[] passengers;

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

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
}
