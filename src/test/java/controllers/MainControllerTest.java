package controllers;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pokemon.type.chart.PokemonTypeChartOnlineApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PokemonTypeChartOnlineApplication.class)
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void rootPathShowsPage() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Pokemon"));
        assertTrue(content.contains("is affected by moves as follows:"));
        assertTrue(content.contains("See code at github,"));
    }
}
