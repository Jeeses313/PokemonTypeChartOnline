package pokemon.type.chart.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pokemon.type.chart.domain.Pokemon;
import pokemon.type.chart.services.PokemonService;
import pokemon.type.chart.services.TypeService;

@RestController
public class RestfulController {

    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/rest/{type1}/{type2}")
    public List<List<String>> getTypeEffectivenessChart(@PathVariable String type1, @PathVariable String type2) {
        return (List) typeService.getTypeEffectivenesses(type1, type2);
    }

    @GetMapping("/rest/{pokemon}")
    public Pokemon getPokemon(@PathVariable String pokemon) {
        return pokemonService.getPokemon(pokemon);
    }
    
    @GetMapping("/rest/PokemonNameOrder")
    public List<String> getPokemonNameOrder() {
        return pokemonService.getPokemonListNameOrder();
    }
    
    @GetMapping("/rest/PokemonNameOrderReverse")
    public List<String> getPokemonNameOrderResverse() {
        return pokemonService.getPokemonListNameReverseOrder();
    }
    
    @GetMapping("/rest/PokemonNumberOrder")
    public List<String> getPokemonNumberOrder() {
        return pokemonService.getPokemonListNumberOrder();
    }
    
    @GetMapping("/rest/PokemonNumberOrderReverse")
    public List<String> getPokemonNumberOrderResverse() {
        return pokemonService.getPokemonListNumberReverseOrder();
    }
}
