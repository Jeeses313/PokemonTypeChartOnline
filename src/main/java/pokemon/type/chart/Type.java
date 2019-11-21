package pokemon.type.chart;

import java.util.HashMap;
import java.util.Objects;

public class Type {

    private String name;
    private HashMap<String, Integer> effectiveness;

    public Type(String name, String[] strong, String[] weak, String[] ineffective) {
        this.name = name;
        this.effectiveness = new HashMap<>();
        for (int i = 0; i < strong.length; i++) {
            effectiveness.put(strong[i].trim(), 1);
        }
        for (int i = 0; i < weak.length; i++) {
            effectiveness.put(weak[i].trim(), -1);
        }
        for (int i = 0; i < ineffective.length; i++) {
            effectiveness.put(ineffective[i].trim(), -2);
        }
    }

    public int getEfftectiveness(String type) {
        return effectiveness.getOrDefault(type, 0);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Type other = (Type) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

}
