import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PermutationsService {
    public List<String> generateNamePermutations(String fullName){
        if(fullName == null){
            return Collections.emptyList();
        }
        List<String> nameParts = Arrays.asList(fullName.split(" "));
        List<String> nameCombinations = new ArrayList<>();
        for(String namePart : nameParts){
            List<String> combination = new ArrayList<>(Collections.singletonList(namePart));
            List<String> leftNames = new ArrayList<>(nameParts);
            leftNames.remove(namePart);
            nameCombinations.addAll(makeCombinations(combination, leftNames));
        }
        return nameCombinations
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    private List<String> makeCombinations(List<String> curCombination, List<String> leftElements){
        if(leftElements.size() == 0){
            return Collections.singletonList(String.join(" ", curCombination));
        }
        List<String> combinations = new ArrayList<>();
        for(String leftElement : leftElements){
            List<String> nextCombination = new ArrayList<>(curCombination);
            nextCombination.add(leftElement);
            List<String> nextLeftElements = new ArrayList<>(leftElements);
            nextLeftElements.remove(leftElement);
            combinations.addAll(makeCombinations(nextCombination, nextLeftElements));
        }
        return combinations;
    }


}
