package pokemon.type.chart;


import javafx.util.Pair;

public class Pokemon {
    private String name;
    private int number;
    private String type1;
    private String type2;

    public Pokemon(String name, int number, String type1, String type2) {
        this.name = name;
        this.number = number;
        this.type1 = type1;
        this.type2 = type2;
    }
    
    public Pair<String,String> getType() {
        return new Pair(type1, type2);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
    
    
}
