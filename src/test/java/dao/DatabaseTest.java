package dao;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import pokemon.type.chart.dao.Database;
import pokemon.type.chart.domain.Pokemon;

public class DatabaseTest {
    
    private Database database;
    
    @Before
    public void setUp() {
        database = new Database();
    }
    
    @Test
    public void getPokemonGetsPokemon() {
        Pokemon pokemon = database.getPokemon("Bulbasaur");
        assertEquals("Bulbasaur", pokemon.getName());
        assertEquals(1, pokemon.getNumber());
        assertEquals("Grass", pokemon.getType().getKey());
        assertEquals("Poison", pokemon.getType().getValue());
    }
    
    @Test
    public void getTypeListGetsTypeList() {
        ArrayList<String> typeList = database.getTypeList();
        assertEquals(19, typeList.size());
        assertTrue(typeList.contains("None"));
        assertTrue(typeList.contains("Bug"));
        assertTrue(typeList.contains("Dark"));
        assertTrue(typeList.contains("Dragon"));
        assertTrue(typeList.contains("Electric"));
        assertTrue(typeList.contains("Fairy"));
        assertTrue(typeList.contains("Fighting"));
        assertTrue(typeList.contains("Fire"));
        assertTrue(typeList.contains("Flying"));
        assertTrue(typeList.contains("Ghost"));
        assertTrue(typeList.contains("Ground"));
        assertTrue(typeList.contains("Ice"));
        assertTrue(typeList.contains("Normal"));
        assertTrue(typeList.contains("Poison"));
        assertTrue(typeList.contains("Psychic"));
        assertTrue(typeList.contains("Rock"));
        assertTrue(typeList.contains("Steel"));
        assertTrue(typeList.contains("Water"));
    }
    
    @Test
    public void getTypeEffectivenessGetsTypeEffectiveness() {
        ArrayList<ArrayList<String>> typeEffectiveness = database.getTypeEffectivenesses("Normal", "None");
        assertEquals(0 ,typeEffectiveness.get(0).size());
        assertEquals(1 ,typeEffectiveness.get(1).size());
        assertEquals(17 ,typeEffectiveness.get(2).size());
        assertEquals(0 ,typeEffectiveness.get(3).size());
        assertEquals(0 ,typeEffectiveness.get(4).size());
        assertEquals(1 ,typeEffectiveness.get(5).size());
        assertTrue(typeEffectiveness.get(1).contains("Fighting"));
        assertTrue(typeEffectiveness.get(5).contains("Ghost"));
        typeEffectiveness = database.getTypeEffectivenesses("None", "Normal");
        assertEquals(0 ,typeEffectiveness.get(0).size());
        assertEquals(1 ,typeEffectiveness.get(1).size());
        assertEquals(17 ,typeEffectiveness.get(2).size());
        assertEquals(0 ,typeEffectiveness.get(3).size());
        assertEquals(0 ,typeEffectiveness.get(4).size());
        assertEquals(1 ,typeEffectiveness.get(5).size());
        assertTrue(typeEffectiveness.get(1).contains("Fighting"));
        assertTrue(typeEffectiveness.get(5).contains("Ghost"));
        typeEffectiveness = database.getTypeEffectivenesses("Normal", "Normal");
        assertEquals(0 ,typeEffectiveness.get(0).size());
        assertEquals(1 ,typeEffectiveness.get(1).size());
        assertEquals(17 ,typeEffectiveness.get(2).size());
        assertEquals(0 ,typeEffectiveness.get(3).size());
        assertEquals(0 ,typeEffectiveness.get(4).size());
        assertEquals(1 ,typeEffectiveness.get(5).size());
        assertTrue(typeEffectiveness.get(1).contains("Fighting"));
        assertTrue(typeEffectiveness.get(5).contains("Ghost"));
    }
    
    @Test
    public void getPokemonListNameOrderGetNameOrder() {
        ArrayList<String> pokemonList = database.getPokemonListNameOrder();
        assertEquals("Abomasnow", pokemonList.get(0));
        assertEquals("Zygarde", pokemonList.get(pokemonList.size()-1));
    }
    
    @Test
    public void getPokemonListNameReverseOrderGetNameOrderReverse() {
        ArrayList<String> pokemonList = database.getPokemonListNameReverseOrder();
        assertEquals("Zygarde", pokemonList.get(0));
        assertEquals("Abomasnow", pokemonList.get(pokemonList.size()-1));
    }
    
    @Test
    public void getPokemonListNumberOrderGetNumberOrder() {
        ArrayList<String> pokemonList = database.getPokemonListNumberOrder();
        assertEquals("Bulbasaur", pokemonList.get(0));
        assertEquals("Eternatus", pokemonList.get(pokemonList.size()-1));
    }
    
    @Test
    public void getPokemonListNumberReverseOrderGetNumberOrderReverse() {
        ArrayList<String> pokemonList = database.getPokemonListNumberReverseOrder();
        assertEquals("Eternatus", pokemonList.get(0));
        assertEquals("Bulbasaur", pokemonList.get(pokemonList.size()-1));
    }
}
