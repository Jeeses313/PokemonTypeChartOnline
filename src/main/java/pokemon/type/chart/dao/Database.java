package pokemon.type.chart.dao;

import pokemon.type.chart.domain.Pokemon;
import pokemon.type.chart.domain.Type;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.springframework.stereotype.Component;
import pokemon.type.chart.PokemonTypeChartOnlineApplication;

@Component
public class Database {

    private ArrayList<String> typeList;
    private HashMap<String, Type> typeMap;
    private ArrayList<String> pokemonList;
    private HashMap<String, Pokemon> pokemonMap;
    private HashMap<String, ArrayList<ArrayList<String>>> effectivenessMap;

    public Database() {
        initTypes();
        initPokemons();
        initAllTypeEffectivenesses();
    }

    private void initTypes() {
        this.typeList = new ArrayList<>();
        this.typeMap = new HashMap<>();
        try {
            Scanner types = new Scanner(resourceToString("typechart.txt"));
//      File: Type:Super1,Super2...;Weak1,Weak2...;Inneffective1,Ineffective2...  
            while (types.hasNextLine()) {
                String line = types.nextLine();
                String[] firstSplit = line.split(":"); //name / types
                if (firstSplit.length == 1) {
                    String name = line.trim();
                    if (line.contains("None")) {
                        name = "None";
                    }
                    typeList.add(name);
                    typeMap.put(name, new Type(line, new String[0], new String[0], new String[0]));
                    continue;
                }
                String name = firstSplit[0].trim();
                String[] secondSplit = firstSplit[1].split(";"); //strong / weak / ineffective 
                String[] strong = secondSplit[0].split(","); // type1 / type2 ...
                String[] weak = secondSplit[1].split(",");
                String[] inneffective = secondSplit[2].split(",");
                typeList.add(name);
                typeMap.put(name, new Type(name, strong, weak, inneffective));
            }
            types.close();
        } catch (Exception e) {
        }
    }

    private void initPokemons() {
        this.pokemonList = new ArrayList<>();
        this.pokemonMap = new HashMap<>();
        try {
            Scanner pokemons = new Scanner(resourceToString("pokemonchart.txt"));
//            File: Name,Number,Type1,Type2
            while (pokemons.hasNextLine()) {
                String line = pokemons.nextLine();
                String[] split = line.split(",");
                String name = split[0].trim();
                if (name.contains("Bulbasaur")) {
                    name = "Bulbasaur";
                }
                pokemonList.add(name);
                int num = Integer.parseInt(split[1].trim().substring(1));
                if (split.length == 3) {
                    pokemonMap.put(name, new Pokemon(name, num, split[2], "None"));
                } else {
                    pokemonMap.put(name, new Pokemon(name, num, split[2], split[3]));
                }
            }
            pokemons.close();
        } catch (Exception e) {
        }
    }
    
    private void initAllTypeEffectivenesses() {
        effectivenessMap = new HashMap<>();
        for (String type1 : typeList) {
            for (String type2 : typeList) {
                this.effectivenessMap.put(type1 + "-" + type2, initTypeEffectivenesses(type1, type2));
            }
        }
    }

    private ArrayList<ArrayList<String>> initTypeEffectivenesses(String type1, String type2) {
        ArrayList<ArrayList<String>> typeEffectiveness = new ArrayList<>();
        ArrayList<String> hyper = new ArrayList<>();
        ArrayList<String> strong = new ArrayList<>();
        ArrayList<String> normal = new ArrayList<>();
        ArrayList<String> weak = new ArrayList<>();
        ArrayList<String> superWeak = new ArrayList<>();
        ArrayList<String> inneffective = new ArrayList<>();
        if (type1.equals(type2)) {
            for (String attackType : typeList) {
                int againstType1 = typeMap.getOrDefault(type1, typeMap.get("None")).getEfftectiveness(attackType);
                if (againstType1 == -2) {
                    inneffective.add(attackType);
                } else if (againstType1 == 1) {
                    strong.add(attackType);
                } else if (againstType1 == -1) {
                    weak.add(attackType);
                } else {
                    normal.add(attackType);
                }
            }
        } else {
            for (String attackType : typeList) {
                int againstType1 = typeMap.getOrDefault(type1, typeMap.get("None")).getEfftectiveness(attackType);
                int againstType2 = typeMap.getOrDefault(type2, typeMap.get("None")).getEfftectiveness(attackType);
                if (againstType1 == -2 || againstType2 == -2) {
                    inneffective.add(attackType);
                } else if (againstType1 == 1 && againstType2 == 1) {
                    hyper.add(attackType);
                } else if ((againstType1 == 1 && againstType2 == 0) || (againstType1 == 0 && againstType2 == 1)) {
                    strong.add(attackType);
                } else if ((againstType1 == -1 && againstType2 == 0) || (againstType1 == 0 && againstType2 == -1)) {
                    weak.add(attackType);
                } else if (againstType1 == -1 && againstType2 == -1) {
                    superWeak.add(attackType);
                } else {
                    normal.add(attackType);
                }
            }
        }
        typeEffectiveness.add(hyper);
        typeEffectiveness.add(strong);
        typeEffectiveness.add(normal);
        typeEffectiveness.add(weak);
        typeEffectiveness.add(superWeak);
        typeEffectiveness.add(inneffective);
        return typeEffectiveness;
    }

    public ArrayList<ArrayList<String>> getTypeEffectivenesses(String type1, String type2) {
        return effectivenessMap.get(type1 + "-" + type2);
    }

    public ArrayList<String> getTypeList() {
        return typeList;
    }

    public Pokemon getPokemon(String name) {
        return pokemonMap.getOrDefault(name, null);
    }

    public ArrayList<String> getPokemonListNameOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok1).getName().compareTo(pokemonMap.get(pok2).getName()));
        return pokemonList;
    }

    public ArrayList<String> getPokemonListNumberOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok1).getNumber() - pokemonMap.get(pok2).getNumber());
        return pokemonList;
    }

    public ArrayList<String> getPokemonListNameReverseOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok2).getName().compareTo(pokemonMap.get(pok1).getName()));
        return pokemonList;
    }

    public ArrayList<String> getPokemonListNumberReverseOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok2).getNumber() - pokemonMap.get(pok1).getNumber());
        return pokemonList;
    }

    private static String resourceToString(String filePath) throws IOException, URISyntaxException {
        try (InputStream inputStream = PokemonTypeChartOnlineApplication.class.getClassLoader().getResourceAsStream(filePath)) {
            return inputStreamToString(inputStream);
        }
    }

    private static String inputStreamToString(InputStream inputStream) {
        try (Scanner scanner = new Scanner(inputStream).useDelimiter("\\A")) {
            return scanner.hasNext() ? scanner.next() : "";
        }
    }

}
