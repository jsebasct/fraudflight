package org.demo.people.flight.point;

import com.google.gson.Gson;
import org.demo.people.flight.business.FraudDetector;
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
    public void shouldReturnSomething() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")))
                .andDo(document("home"));
    }

    @Test
    public void testRules() throws Exception {
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

        FraudDetector detect = new FraudDetector();
        detect.setUmbral(60);

//        Gson gson = new Gson();
//        String json = gson.toJson(detect);
//        System.out.println("json: " + json);

        mockMvc.perform(
            patch("/fraud/detector/umbral", detect)
                    .contentType(MediaType.APPLICATION_JSON)
                .content("{\"umbral\": 70}")
//                .content(json)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("umbral_patch"));
    }

}
