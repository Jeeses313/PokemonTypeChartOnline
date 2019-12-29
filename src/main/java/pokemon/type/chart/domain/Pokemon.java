package pokemon.type.chart.domain;


public class Pokemon {
    private String name;
    private int number;
    private Pair types;

    public Pokemon(String name, int number, String type1, String type2) {
        this.name = name;
        this.number = number;
        types = new Pair(type1, type2);
    }
    
    public Pair getType() {
        return types;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
    
    
}
