package org.demo.people.flight.business;

import java.util.ArrayList;
import java.util.List;

public class CountryRedListRule extends FligthRule {

    private List<String > redListCountries;

    public CountryRedListRule() {
        setScore(40);
        redListCountries = new ArrayList<>();
        initList();
    }

    private void initList() {
        redListCountries.add("Botswana");
        redListCountries.add("Moldova");
        redListCountries.add("Guinea");

    }

    @Override
    public boolean evaluate(FlyTicket ticket) {
        for (String city : redListCountries) {
            if ( city.equals(ticket.getDestinationCity()) ) {
                return true;
            }
        }
        return false;
    }
}
