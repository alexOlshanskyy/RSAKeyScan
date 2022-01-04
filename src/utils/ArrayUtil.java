package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    public static List<List<String>> splitArray(List<String> original, int numChunks){
        int size = (original.size() + 1) / numChunks;
        List<List<String>> result = new ArrayList<>();
        int start = 0;
        int end = size;
        for (int i = 0; i < numChunks; i++) {
            result.add(original.subList(start, end));
            start = end;
            end += size;
            if (end > original.size()) {
                end = original.size();
            }
            // last chunk
            if (end < original.size() && i == numChunks - 2) {
                end = original.size();
            }
        }
        return result;
    }
}
