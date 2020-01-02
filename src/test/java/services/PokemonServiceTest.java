package services;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pokemon.type.chart.dao.Database;
import pokemon.type.chart.domain.Pokemon;
import pokemon.type.chart.services.PokemonService;

@RunWith(SpringJUnit4ClassRunner.class)
public class PokemonServiceTest {

    @InjectMocks
    private PokemonService pokemonService;
    @Mock
    private Database database;

    @Before
    public void setUp() {
        when(database.getPokemon("Bulbasaur")).thenReturn(new Pokemon("Bulbasaur", 1, "Grass", "None"));
        when(database.getPokemonListNameOrder()).thenReturn(new ArrayList<>());
        when(database.getPokemonListNameReverseOrder()).thenReturn(new ArrayList<>());
        when(database.getPokemonListNumberOrder()).thenReturn(new ArrayList<>());
        when(database.getPokemonListNumberReverseOrder()).thenReturn(new ArrayList<>());
    }

    @Test
    public void getPokemonGetsPokemon() {
        pokemonService.getPokemon("Bulbasaur");
        verify(database, times(1)).getPokemon("Bulbasaur");
    }
    
    @Test
    public void getPokemonListNameOrderGetsNameOrder() {
        pokemonService.getPokemonListNameOrder();
        verify(database, times(1)).getPokemonListNameOrder();
    }
    
    @Test
    public void getPokemonListNameReverseOrderGetsNameOrderReverse() {
        pokemonService.getPokemonListNameReverseOrder();
        verify(database, times(1)).getPokemonListNameReverseOrder();
    }
    
    @Test
    public void getPokemonListNumberOrderGetsNumberOrder() {
        pokemonService.getPokemonListNumberOrder();
        verify(database, times(1)).getPokemonListNumberOrder();
    }
    
    @Test
    public void getPokemonListNumberReverseOrderGetsNumberOrderReverse() {
        pokemonService.getPokemonListNumberReverseOrder();
        verify(database, times(1)).getPokemonListNumberReverseOrder();
    }
}
