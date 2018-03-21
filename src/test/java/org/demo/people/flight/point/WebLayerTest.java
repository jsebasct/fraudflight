package org.demo.people.flight.point;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FraudController.class)
@ComponentScan("org.demo.people.flight")
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testGetRules() throws Exception {
        mockMvc.perform(get("/fraud/detector/rules"))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(content().string(containsString("Hello World")))
                .andDo(document("rules"));
    }

    @Test
    public void testGetUmbral() throws Exception {
        mockMvc.perform(get("/fraud/detector/umbral"))
                .andDo(print())
                .andExpect(status().isOk())
                //.andExpect(content().string(containsString("Hello World")))
                .andDo(document("umbral"));
    }

    @Test
    public void testPostUmbral() throws Exception {

        mockMvc.perform(
            patch("/fraud/detector/umbral")
                    .contentType(MediaType.APPLICATION_JSON)
                .content("{\"umbral\": 70}")
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andDo(document("umbral_patch"));
    }


    @Test
    public void testObtainScore() throws Exception {

//        Person[] passengers = new Person[2];
//        passengers[0] = new Person("Jhon", "Smith");
//        passengers[1] = new Person("Jane", "SmithSSSSS");
//
//        Person holder = new Person("Juan", "Perez");
//        CreditCard creditCard = new CreditCard(1234_5678_9012_3456L, holder);
//
//        FligthRule r1 = new PurchaseLimitRule();
//        FligthRule[] flights = {r1};
//
//        FlyTicket ticket = new FlyTicket();
//        ticket.setCreditCard(creditCard);
//        ticket.setDestinationCity("Guinea");
//        ticket.setFlyDate(LocalDate.now());
//        ticket.setPassengers(passengers);
//        ticket.setPurchaseAmount(60000);

//        FraudDetector detect = new FraudDetector();
//        int score = detect.getScore(ticket);
//        Assert.assertEquals(230, score, 0.1);

//        FraudDetector detect = new FraudDetector();
//        detect.setUmbral(60);

//        Gson gson = new Gson();
//        String json = gson.toJson(ticket);
//        System.out.println("json: " + json);


        String json = "{\"passengers\":[{\"name\":\"Jhon\",\"lastName\":\"Smith\"},{\"name\":\"Jane\",\"lastName\":\"SmithSSSSS\"}],\"creditCard\":{\"creditCardNumber\":1234567890123456,\"holder\":{\"name\":\"Juan\",\"lastName\":\"Perez\"}},\"destinationCity\":\"Guinea\",\"flyDate\":\"2018-03-18\",\"purchaseAmount\":60000}";

        mockMvc.perform(
                post("/ticket/score")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"umbral\": 70}")
                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("200")))
                .andDo(document("score"));
    }

    @Test
    public void testPostFraudulenta() throws Exception {

        String jsonTicket = "{\"passengers\":[{\"name\":\"Jhon\",\"lastName\":\"Smith\"},{\"name\":\"Jane\",\"lastName\":\"SmithSSSSS\"}],\"creditCard\":{\"creditCardNumber\":1234567890123456,\"holder\":{\"name\":\"Juan\",\"lastName\":\"Perez\"}},\"destinationCity\":\"Guinea\",\"flyDate\":\"2018-03-18\",\"purchaseAmount\":60000}";

        mockMvc.perform(
                post("/ticket/fraud")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTicket)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("true")))
                .andDo(document("fraud"));
    }

    @Test
    public void testPostNoFraudulenta() throws Exception {

        //setting umbral
        mockMvc.perform(
                patch("/fraud/detector/umbral")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"umbral\": 60}")
        )
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andDo(document("umbral_patch"));

        // testing ticket
        String jsonTicket = "{\"passengers\":[{\"name\":\"Jhon\",\"lastName\":\"Smith\"},{\"name\":\"Jane\",\"lastName\":\"Valdez\"}],\"creditCard\":{\"creditCardNumber\":9234567890123456,\"holder\":{\"name\":\"Juan\",\"lastName\":\"Valdez\"}},\"destinationCity\":\"Bolivia\",\"flyDate\":\"2018-03-18\",\"purchaseAmount\":40000}";

        mockMvc.perform(
                post("/ticket/fraud")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonTicket)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("false")))
                .andDo(document("fraud"));
    }

}
