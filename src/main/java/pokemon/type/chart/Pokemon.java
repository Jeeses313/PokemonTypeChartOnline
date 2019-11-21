package pokemon.type.chart;


public class Pokemon {
    private String name;
    private int number;
    private String type1;
    private String type2;
    private Pair types;

    public Pokemon(String name, int number, String giventype1, String type2) {
        this.name = name;
        this.number = number;
        this.type1 = giventype1;
        this.type2 = type2;
        types = new Pair(giventype1, type2);

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
