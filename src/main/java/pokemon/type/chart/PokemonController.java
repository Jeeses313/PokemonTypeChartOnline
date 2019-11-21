package pokemon.type.chart;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PokemonController {

    @Autowired
    private Database database;

//    @GetMapping("/")
//    public String index(Model model) {
//        return "redirect:/None/None";
//    }
    @GetMapping("/")
    public String index(Model model) {
        setModelAttributes(model, "None", "None");
        return "index";
    }

    @GetMapping("/{type1}/{type2}")
    public String showEffectiveness(Model model, @PathVariable String type1, @PathVariable String type2) {
//        if(!database.checkType(type1)) {
//            return "redirect:/None/" + type2;
//        }
//        if(!database.checkType(type2)) {
//            return "redirect:/" + type2 + "/None";
//        }
        setModelAttributes(model, type1, type2);
        return "index";
    }

//    @GetMapping("/{pokemon}")
//    public String showPokemon(Model model, @PathVariable String pokemon) {
//        if(!database.checkPokemon(pokemon)) {
//            return "redirect:/None/None";
//        }
//        Pair types = database.getPokemonTypes(pokemon);
//        return "redirect:/" + pokemon + "/" + types.getKey() + "/" + types.getValue();
//    }
    @GetMapping("/{pokemon}")
    public String showPokemon(Model model, @PathVariable String pokemon) {
//        if(!database.checkPokemon(pokemon)) {
//            return "redirect:/None/None";
//        }
        Pair types = database.getPokemonTypes(pokemon);
        setModelAttributes(model, types.getKey(), types.getValue());
        model.addAttribute("selectedPokemon", pokemon);

        return "index";
    }

//    @GetMapping("/{pokemon}/{type1}/{type2}")
//    public String showPokemonEffectiveness(Model model, @PathVariable String pokemon, @PathVariable String type1, @PathVariable String type2) {
//        if(!database.checkPokemon(pokemon)) {
//            return "redirect:/None/None";
//        }
//        if(!database.checkType(type1) || !database.checkType(type2)) {
//            return "redirect:/" + pokemon;
//        }
//        setModelAttributes(model, type1, type2);
//        model.addAttribute("selectedPokemon", pokemon);
//        return "index";
//    }
    @GetMapping("**")
    public String error(Model model) {
        return "redirect:/None/None";
    }

//    private void setModelAttributes(Model model, String type1, String type2) {
//        ArrayList<ArrayList<String>> typeEffectivenessMatrix = database.getTypeEffectivenesses(type1, type2);
//        model.addAttribute("hyper", typeEffectivenessMatrix.get(0));
//        model.addAttribute("strong", typeEffectivenessMatrix.get(1));
//        model.addAttribute("normal", typeEffectivenessMatrix.get(2));
//        model.addAttribute("weak", typeEffectivenessMatrix.get(3));
//        model.addAttribute("superWeak", typeEffectivenessMatrix.get(4));
//        model.addAttribute("ineffective", typeEffectivenessMatrix.get(5));
//        model.addAttribute("types", database.getTypeList());
//        model.addAttribute("pokemons", database.getPokemonListNameOrder());
//        model.addAttribute("type1", type1);
//        model.addAttribute("type2", type2);
//    }
    private void setModelAttributes(Model model, String type1, String type2) {
        ArrayList<ArrayList<String>> typeEffectivenessMatrix = database.getTypeEffectivenesses(type1, type2);
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
        model.addAttribute("types", database.getTypeList());
        model.addAttribute("pokemons", database.getPokemonListNameOrder());
        model.addAttribute("type1", type1);
        model.addAttribute("type2", type2);
    }
}
