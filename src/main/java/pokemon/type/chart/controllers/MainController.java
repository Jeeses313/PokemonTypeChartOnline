package pokemon.type.chart.controllers;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pokemon.type.chart.services.PokemonService;
import pokemon.type.chart.services.TypeService;

@Controller
public class MainController {

    @Autowired
    private PokemonService pokemonService;
    @Autowired
    private TypeService typeService;

    @GetMapping("/")
    public String index(Model model) {
        setModelAttributes(model, "None", "None");
        return "index";
    }

    private void setModelAttributes(Model model, String type1, String type2) {
        ArrayList<ArrayList<String>> typeEffectivenessMatrix = typeService.getTypeEffectivenesses(type1, type2);
        if (typeEffectivenessMatrix.get(0) == null) {
            model.addAttribute("hyper", new ArrayList<String>());
        } else {
            model.addAttribute("hyper", typeEffectivenessMatrix.get(0));
        }
        if (typeEffectivenessMatrix.get(1) == null) {
            model.addAttribute("strong", new ArrayList<String>());
        } else {
            model.addAttribute("strong", typeEffectivenessMatrix.get(1));
        }
        if (typeEffectivenessMatrix.get(2) == null) {
            model.addAttribute("normal", new ArrayList<String>());
        } else {
            model.addAttribute("normal", typeEffectivenessMatrix.get(2));
        }
        if (typeEffectivenessMatrix.get(3) == null) {
            model.addAttribute("weak", new ArrayList<String>());
        } else {
            model.addAttribute("weak", typeEffectivenessMatrix.get(3));
        }
        if (typeEffectivenessMatrix.get(4) == null) {
            model.addAttribute("superWeak", new ArrayList<String>());
        } else {
            model.addAttribute("superWeak", typeEffectivenessMatrix.get(4));
        }
        if (typeEffectivenessMatrix.get(5) == null) {
            model.addAttribute("ineffective", new ArrayList<String>());
        } else {
            model.addAttribute("ineffective", typeEffectivenessMatrix.get(5));
        }
        model.addAttribute("types", typeService.getTypeList());
        model.addAttribute("pokemons", pokemonService.getPokemonListNameOrder());
        model.addAttribute("type1", type1);
        model.addAttribute("type2", type2);
    }
}
