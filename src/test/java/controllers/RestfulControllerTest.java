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
public class RestfulControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void canGetPokemon() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/rest/Bulbasaur")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("Bulbasaur"));
        assertTrue(content.contains("Grass"));
        assertTrue(content.contains("Poison"));
    }

    @Test
    public void canGetTypeEffectiveness() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/rest/Normal/None")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("[\"Ghost\"]"));
        assertTrue(content.contains("[\"Fighting\"]"));
        assertTrue(content.contains("[]"));
        assertTrue(content.contains("[\"None\",\"Bug\",\"Dark\",\"Dragon\",\"Electric\",\"Fairy\",\"Fire\",\"Flying\",\"Grass\",\"Ground\",\"Ice\",\"Normal\",\"Poison\",\"Psychic\",\"Rock\",\"Steel\",\"Water\"]"));
    }

    @Test
    public void canGetPokemonNameOrder() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/rest/PokemonNameOrder")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("[\"Abomasnow\","));
        assertTrue(content.contains(",\"Zygarde\"]"));
    }
    
    @Test
    public void canGetPokemonNameOrderReverse() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/rest/PokemonNameOrderReverse")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains(",\"Abomasnow\"]"));
        assertTrue(content.contains("[\"Zygarde\","));
    }
    
    @Test
    public void canGetPokemonNumberOrder() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/rest/PokemonNumberOrder")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains("[\"Bulbasaur\","));
        assertTrue(content.contains(",\"Eternatus\"]"));
    }
    
    @Test
    public void canGetPokemonNumberOrderReverse() throws Exception {
        MvcResult res = mockMvc.perform(MockMvcRequestBuilders.get("/rest/PokemonNumberOrderReverse")).andReturn();
        String content = res.getResponse().getContentAsString();
        assertTrue(content.contains(",\"Bulbasaur\"]"));
        assertTrue(content.contains("[\"Eternatus\","));
    }
}
