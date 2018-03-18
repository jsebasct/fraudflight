package org.demo.people.flight.business.point;

import org.demo.people.flight.business.CreditCard;
import org.demo.people.flight.business.FlyTicket;
import org.demo.people.flight.business.FraudDetector;
import org.demo.people.flight.business.Person;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
class FraudController {

    //TODO why cant this be a GET ? cant send the body !
    //TODO the response cant be sent as naked integer ?
    @RequestMapping(value = "/score",method = RequestMethod.POST)
    @ResponseBody
    public Integer score(@RequestBody FlyTicket ticket) {
        Integer res = -1;
        FraudDetector detector = new FraudDetector();

        if (ticket != null) {
            res = detector.getScore(ticket);
        }
        return res;
    }

    @RequestMapping(value="/ticket", method = RequestMethod.GET)
    @ResponseBody
    public FlyTicket getTicket() {

        Person holder = new Person("Juan", "Valdez");
        CreditCard creditCard = new CreditCard(9234_5678_9012_3456L, holder);

        Person p1 = new Person("Jhon", "Smith");
        Person p2 = new Person("Jane", "Valdez");

        Person[] passengers = new Person[2];
        passengers[0] = p1;
        passengers[1] = p2;

        FlyTicket ticket = new FlyTicket();
        ticket.setCreditCard(creditCard);
        ticket.setDestinationCity("Bolivia");
        ticket.setFlyDate(LocalDate.now());
        ticket.setPassengers(passengers);
        ticket.setPurchaseAmount(40000);

        return ticket;
    }

}
