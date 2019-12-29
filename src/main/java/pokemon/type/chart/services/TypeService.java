package pokemon.type.chart.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import pokemon.type.chart.dao.Database;

@Service
public class TypeService {

    @Autowired
    private Database database;

    @Cacheable("types")
    public ArrayList<ArrayList<String>> getTypeEffectivenesses(String type1, String type2) {
        return database.getTypeEffectivenesses(type1, type2);
    }

    public ArrayList<String> getTypeList() {
        return database.getTypeList();
    }

}
