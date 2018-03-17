package org.demo.people.flight.business;

public class RedListRule implements FligthRule {
    @Override
    public boolean isEnabled() {
        return true;
    }
}
