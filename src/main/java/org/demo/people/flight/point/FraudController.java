package org.demo.people.flight.point;

import org.demo.people.flight.business.CreditCard;
import org.demo.people.flight.business.FlyTicket;
import org.demo.people.flight.business.FraudDetector;
import org.demo.people.flight.business.Person;
import org.demo.people.flight.business.rules.FligthRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;


@RestController
//@RequestMapping("api")
class FraudController {

    @Autowired
    private FraudDetector fraudDetector;

    @RequestMapping(value = "/ticket/score", method = RequestMethod.POST)
    public ResponseEntity<Integer> score(@RequestBody FlyTicket ticket) {

        if (ticket == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Integer res = fraudDetector.getScore(ticket);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @RequestMapping(value="/ticket/fraud", method = RequestMethod.POST)
    public ResponseEntity<Boolean> fraud(@RequestBody FlyTicket ticket) {

        if (ticket == null) {
            return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
        }

        Boolean res = fraudDetector.isFraud(ticket);
        return new ResponseEntity<Boolean>(res, HttpStatus.OK);
    }

    @RequestMapping(value="/fraud/detector/umbral", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> umbral(@RequestBody FraudDetector umbral) {
        if (umbral == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        fraudDetector.setUmbral(umbral.getUmbral());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/fraud/detector/umbral", method = RequestMethod.GET)
    public ResponseEntity<Integer> umbral() {
        return new ResponseEntity<>(fraudDetector.getUmbral(), HttpStatus.OK);
    }

    @RequestMapping(value="/fraud/detector/rules", method = RequestMethod.GET)
    public Map<String, FligthRule> getRules() {
        return fraudDetector.getRules();
    }

    @RequestMapping(value = "/fraud/detector/rules/{id}", method = RequestMethod.PATCH)
    public FligthRule disableRule(@PathVariable("id") String keyRule) {
        if (keyRule != null && !keyRule.isEmpty()) {
            return fraudDetector.disableRule(keyRule);
        }
        return null;
    }

    @RequestMapping(value="/ticket/sample", method = RequestMethod.GET)
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
