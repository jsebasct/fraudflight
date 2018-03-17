package org.demo.people.flight.business;

public class BlackListRule implements FligthRule {
    @Override
    public boolean isEnabled() {
        return true;
    }
}
