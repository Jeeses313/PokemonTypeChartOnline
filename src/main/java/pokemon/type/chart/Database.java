package pokemon.type.chart;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javafx.util.Pair;
import org.springframework.stereotype.Component;
@Component
public class Database {

    private ArrayList<String> typeList;
    private HashMap<String, Type> typeMap;
    private ArrayList<String> pokemonList;
    private HashMap<String, Pokemon> pokemonMap;
    private HashMap<String, ArrayList<ArrayList<String>>> effectivenessMap;
    
    public Database() {
        this.typeList = new ArrayList<>();
        this.typeMap = new HashMap<>();
        try {
            Scanner types = new Scanner(new ResourceUtilities().resourceToString("typechart.txt"));
//      File: Type:Super1,Super2...;Weak1,Weak2...;Inneffective1,Ineffective2...  
            while (types.hasNextLine()) {
                String line = types.nextLine();
                String[] firstSplit = line.split(":"); //name / types
                if (firstSplit.length == 1) {
                    String name = line.trim();
                    if (line.contains("ï")) {
                        name = name.substring(3);
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
        this.pokemonList = new ArrayList<>();
        this.pokemonMap = new HashMap<>();
        try {
            Scanner pokemons = new Scanner(new ResourceUtilities().resourceToString("pokemonchart.txt"));
//            File: Name,Number,Type1,Type2
            while(pokemons.hasNextLine()) {
                String line = pokemons.nextLine();
                String[] split = line.split(",");
                String name = split[0].trim();
                pokemonList.add(name);
                int num = Integer.parseInt(split[1].trim().substring(1));
                if(split.length == 3) {
                    pokemonMap.put(name, new Pokemon(name, num, split[2], "None"));
                } else {
                    pokemonMap.put(name, new Pokemon(name, num, split[2], split[3]));
                }
            }
            pokemons.close();
        } catch (Exception e) {
        }
        effectivenessMap = new HashMap<>();
        for (String type1: typeList) {
            for (String type2: typeList) {
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
                int againstType1 = typeMap.getOrDefault(type1, new Type("None", new String[0], new String[0], new String[0])).getEfftectiveness(attackType);
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
                int againstType1 = typeMap.getOrDefault(type1, new Type("None", new String[0], new String[0], new String[0])).getEfftectiveness(attackType);
                int againstType2 = typeMap.getOrDefault(type2, new Type("None", new String[0], new String[0], new String[0])).getEfftectiveness(attackType);
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
    
    public Pair<String,String> getPokemonTypes(String name) {
        Pokemon pokemon = pokemonMap.getOrDefault(name, null);
        if(pokemon == null) {
            return new Pair<String, String>("None", "None");
        }
        return pokemon.getType();
    }

    public ArrayList<String> getPokemonListNameOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok1).getName().compareTo(pokemonMap.get(pok2).getName()));
        return pokemonList;
    }
    
    public ArrayList<String> getPokemonListNumberOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok1).getNumber()- pokemonMap.get(pok2).getNumber());
        return pokemonList;
    }
    
    public ArrayList<String> getPokemonListNameReverseOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok2).getName().compareTo(pokemonMap.get(pok1).getName()));
        return pokemonList;
    }
    
    public ArrayList<String> getPokemonListNumberReverseOrder() {
        pokemonList.sort((pok1, pok2) -> pokemonMap.get(pok2).getNumber()- pokemonMap.get(pok1).getNumber());
        return pokemonList;
    }
    
//From BullyWiiPlaza: https://stackoverflow.com/questions/6068197/utils-to-read-resource-text-file-to-string-java

    public static class ResourceUtilities {

        public static String resourceToString(String filePath) throws IOException, URISyntaxException {
            try (InputStream inputStream = ResourceUtilities.class.getClassLoader().getResourceAsStream(filePath)) {
                return inputStreamToString(inputStream);
            }
        }

        private static String inputStreamToString(InputStream inputStream) {
            try (Scanner scanner = new Scanner(inputStream).useDelimiter("\\A")) {
                return scanner.hasNext() ? scanner.next() : "";
            }
        }
    }

    public boolean checkType(String type) {
        if(typeMap.containsKey(type)) {
            return true;
        }
        return false;
    }
    
    public boolean checkPokemon(String pokemon) {
        if(pokemonMap.containsKey(pokemon)) {
            return true;
        }
        return false;
    }
    
    
}
