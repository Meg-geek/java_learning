package shannon.algotithm;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.emptyMap;

public class FileStatistics {
    private final static int BUFFER_SIZE = 256;
    private final static int NO_MORE_DATA = -1;
    private final static int BEGIN_AMOUNT = 1;
    private final static int NO_SYMBOLS = 0;

    private Map<Byte, Integer> getFileStatisticsMap(File file) throws IOException {
        if (!isCorrectFile(file)) {
            return emptyMap();
        }
        Map<Byte, Integer> fileStatistics = new HashMap<>();
        byte[] buffer = new byte[BUFFER_SIZE];
        try (DataInputStream fileStream = new DataInputStream(new FileInputStream(file))) {
            int bytesRead = fileStream.read(buffer);
            while (bytesRead != NO_MORE_DATA) {
                updateMap(fileStatistics, buffer, bytesRead);
                bytesRead = fileStream.read(buffer);
            }
        }
        return fileStatistics;
    }

    public Map<Byte, Float> getProbabilityMap(File file) throws IOException {
        Map<Byte, Integer> statisticsMap = getFileStatisticsMap(file);
        Map<Byte, Float> probability = new HashMap<>();
        int symbolsAmount = NO_SYMBOLS;
        for (Integer amount : statisticsMap.values()) {
            symbolsAmount += amount;
        }
        if (symbolsAmount == NO_SYMBOLS) {
            return emptyMap();
        }
        for (Map.Entry<Byte, Integer> symbolAmount : statisticsMap.entrySet()) {
            probability.put(symbolAmount.getKey(), symbolAmount.getValue() / (float) symbolsAmount);
        }
        return probability;
    }

    private void updateMap(Map<Byte, Integer> statistics, byte[] readBuffer, int bufferLength) {
        if (bufferLength <= 0 || readBuffer.length < bufferLength) {
            return;
        }
        for (int i = 0; i < bufferLength; i++) {
            Byte symbol = readBuffer[i];
            statistics.merge(symbol, BEGIN_AMOUNT, Integer::sum);
        }
    }

    private boolean isCorrectFile(File file) {
        return file != null && !file.isDirectory();
    }
}
