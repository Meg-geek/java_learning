import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class PermutationsServiceTest {

    @Test
    public void generateNamePermutations() {
        String name = "1 2 2 3 3 4";
        PermutationsService permutationsService = new PermutationsService();
        List<String> combinations = permutationsService.generateNamePermutations(name);
        for(String combo : combinations){
            System.out.println(combo);
        }
        for(String combo : combinations){
            assertEquals(1, Collections.frequency(combinations, combo));
        }
        String anotherName = "1 2 3 4 5 6 7";
        assertEquals(5040, permutationsService.generateNamePermutations(anotherName).size());
    }
}