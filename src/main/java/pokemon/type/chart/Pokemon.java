package pokemon.type.chart;


public class Pokemon {
    private String name;
    private int number;
    private String type1;
    private String type2;
    private Pair types;

    public Pokemon(String name, int number, String type1, String type2) {
        this.name = name;
        this.number = number;
        this.type1 = type1;
        this.type2 = type2;
        if(type2 == null) {
            this.type2 = "None";
        }
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
