package shannon.algotithm;

import java.util.*;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.util.Collections.*;


public class ShannonCoder {
    private final static int LOG_BASE = 2;

    public Map<Byte, BitSet> getCodedMap(Map<Byte, Float> statistics) {
        List<Map.Entry<Byte, Float>> sortedSymbolProbability = getSortedProbabilities(statistics);
        List<Float> cumulativeProbabilities = getCumulativeProbabilities(sortedSymbolProbability);
        Map<Byte, BitSet> symbolCode = new HashMap<>();
        for (int i = 0; i < sortedSymbolProbability.size(); i++) {
            Map.Entry<Byte, Float> symbolProbability = sortedSymbolProbability.get(i);
            symbolCode.put(symbolProbability.getKey(),
                    getSymbolCode(symbolProbability.getValue(), cumulativeProbabilities.get(i)));
        }
        return symbolCode;
    }

    private BitSet getSymbolCode(float probability, float cumulativeProbability) {
        int codeLength = getCodeLength(probability);
        BitSet code = new BitSet(codeLength);
        boolean[] binaryDecomposition = getBinaryDecomposition(cumulativeProbability, codeLength);
        for (int i = 0; i < codeLength; i++) {
            if (binaryDecomposition[i]) {
                code.set(i, true);
            }
        }
        return code;
    }

    private boolean[] getBinaryDecomposition(float cumulativeProbability, int codeLength) {
        boolean[] binaryDecomposition = new boolean[codeLength];
        float decomposition = cumulativeProbability;
        for (int i = 0; i < codeLength; i++) {
            decomposition = decomposition * 2;
            if (decomposition < 1) {
                binaryDecomposition[i] = false;
            }
            if (decomposition >= 1) {
                binaryDecomposition[i] = true;
                decomposition = decomposition - 1;
            }
        }
        return binaryDecomposition;
    }

    private int getCodeLength(float probability) {
        return (int) ceil(-log(probability) / log(LOG_BASE));
    }

    private List<Float> getCumulativeProbabilities(List<Map.Entry<Byte, Float>> sortedProbabilities) {
        List<Float> cumulativeProbabilities = new ArrayList<>();
        cumulativeProbabilities.add(0f);
        for (int i = 1; i < sortedProbabilities.size(); i++) {
            float cumulativeProbability = cumulativeProbabilities.get(i - 1) +
                    sortedProbabilities.get(i - 1).getValue();
            cumulativeProbabilities.add(cumulativeProbability);
        }
        return cumulativeProbabilities;
    }

    private List<Map.Entry<Byte, Float>> getSortedProbabilities(Map<Byte, Float> statistics) {
        List<Map.Entry<Byte, Float>> sortedSymbolProbability = new ArrayList<>(statistics.entrySet());
        sort(sortedSymbolProbability,
                (firstEntry, secondEntry) -> -firstEntry.getValue().compareTo(secondEntry.getValue()));
        return sortedSymbolProbability;
    }
}
