
package pokemon.type.chart;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokemonRest {
    @Autowired
    private Database database;
    
    @GetMapping("/js/{type1}/{type2}")
    public List<List<String>> types(@PathVariable String type1, @PathVariable String type2) {
        return (List) database.getTypeEffectivenesses(type1, type2);
    }

    @GetMapping("/js/{pokemon}")
    public Pokemon pokemon(@PathVariable String pokemon) {
        return database.getPokemon(pokemon);
    }
}
