package pokemon.type.chart.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pokemon.type.chart.dao.Database;
import pokemon.type.chart.domain.Pokemon;

@Service
public class PokemonService {

    @Autowired
    private Database database;

    public ArrayList<String> getPokemonListNameOrder() {
        return database.getPokemonListNameOrder();
    }

    public ArrayList<String> getPokemonListNumberOrder() {
        return database.getPokemonListNumberOrder();
    }

    public ArrayList<String> getPokemonListNameReverseOrder() {
        return database.getPokemonListNameReverseOrder();
    }

    public ArrayList<String> getPokemonListNumberReverseOrder() {
        return database.getPokemonListNumberReverseOrder();
    }

    public Pokemon getPokemon(String pokemon) {
        return database.getPokemon(pokemon);
    }

}
