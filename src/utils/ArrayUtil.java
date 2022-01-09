package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtil {

    /**
     *
     * @param original is the original array that will be split
     * @param numChunks number of chunks that the original array will be split to
     * @return list of split arrays
     */
    public static List<List<String>> splitArray(List<String> original, int numChunks){
        int size = (original.size() + 1) / numChunks;
        List<List<String>> result = new ArrayList<>();
        int start = 0;
        int end = size;
        for (int i = 0; i < numChunks; i++) {
            result.add(original.subList(start, end));
            start = end;
            end += size;

            // last chunk edge cases
            if (end > original.size()) {
                end = original.size();
            }
            if (end < original.size() && i == numChunks - 2) {
                end = original.size();
            }
        }
        return result;
    }
}
