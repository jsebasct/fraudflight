package org.demo.people.flight.business.point;

import org.demo.people.flight.business.CreditCard;
import org.demo.people.flight.business.FlyTicket;
import org.demo.people.flight.business.FraudDetector;
import org.demo.people.flight.business.Person;
import org.demo.people.flight.business.rules.FligthRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@RestController
class FraudController {

    @Autowired
    private FraudDetector detector;

    //TODO why cant this be a GET ? cant send the body !
    //TODO the response cant be sent as naked integer ?
    @RequestMapping(value = "/ticket/score", method = RequestMethod.POST)
//    @ResponseBody
    public Integer score(@RequestBody FlyTicket ticket) {
        Integer res = -1;

        if (ticket != null) {
            res = detector.getScore(ticket);
        }
        return res;
    }

    //TODO why cant this be a GET ? cant send the body !
    //TODO the response cant be sent as naked bool ?
    @RequestMapping(value="/ticket/fraud", method = RequestMethod.POST)
    //@ResponseBody
    public boolean fraud(@RequestBody FlyTicket ticket) {

        boolean res = false;

        if (ticket != null) {
            //FraudDetector detector = new FraudDetector();
            res = detector.isFraud(ticket);
        }

        return res;
    }

    @RequestMapping(value="/fraud/detector/umbral", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void umbral(@RequestBody FraudDetector umbral) {
        //detector.setUmbral(umbral);
        detector.setUmbral(umbral.getUmbral());
    }

    @RequestMapping(value="/fraud/detector/umbral", method = RequestMethod.GET)
    public int umbral() {
        return detector.getUmbral();
    }


    @RequestMapping(value="/ticket/sample", method = RequestMethod.GET)
//    @ResponseBody
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


    @RequestMapping(value="/fraud/detector/rules", method = RequestMethod.GET)
//    @ResponseBody
    public Map<String, FligthRule> getRules() {
        return detector.getRules();
    }

    //TODO disable rules API
    @RequestMapping(value = "/fraud/detector/rules/{id}", method = RequestMethod.PATCH)
//    @ResponseBody
    public FligthRule disableRule(@PathVariable("id") String keyRule) {
        if (keyRule != null && !keyRule.isEmpty()) {
            return detector.disableRule(keyRule);
        }
        return null;
    }
}
