package services;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pokemon.type.chart.dao.Database;
import pokemon.type.chart.services.TypeService;


@RunWith(SpringJUnit4ClassRunner.class)
public class TypeServiceTest {
    
    @InjectMocks
    private TypeService typeService;
    @Mock
    private Database database;

    @Before
    public void setUp() {
        when(database.getTypeEffectivenesses("None", "None")).thenReturn(new ArrayList<>());
        when(database.getTypeList()).thenReturn(new ArrayList<>());
    }
    
    @Test
    public void getTypeEffectivenessesGetsTypeEffectivenesses() {
        typeService.getTypeEffectivenesses("None", "None");
        verify(database, times(1)).getTypeEffectivenesses("None", "None");
    }
    
    @Test
    public void getTypeListGetsTypeList() {
        typeService.getTypeList();
        verify(database, times(1)).getTypeList();
    }
}
