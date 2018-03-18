package org.demo.people.flight.business;

public class CreditCard {

    private Long creditCardNumber;
    private Person holder;

    public CreditCard() {
    }

    public CreditCard(Long creditCardNumber, Person holder) {
        this.creditCardNumber = creditCardNumber;
        this.holder = holder;
    }

    public Long getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(Long creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Person getHolder() {
        return holder;
    }

    public void setHolder(Person holder) {
        this.holder = holder;
    }
}
