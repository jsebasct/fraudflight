package org.demo.people.flight.business;

public class CreditCard {

    private Long creditCardNumber;
    private Passenger holder;

    public CreditCard() {

    }

    public CreditCard(Long creditCardNumber, Passenger holder) {
        this.creditCardNumber = creditCardNumber;
        this.holder = holder;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Passenger getHolder() {
        return holder;
    }

    public void setHolder(Passenger holder) {
        this.holder = holder;
    }
}
