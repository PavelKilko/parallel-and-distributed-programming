package org.lab5.util;

import java.util.ArrayList;

public class ArrayListSplitter {
    public static <T> ArrayList<ArrayList<T>> split(ArrayList<T> list, int numOfChunks) {
        int chunkSize = (int) Math.ceil((double) list.size() / numOfChunks);
        ArrayList<ArrayList<T>> chunks = new ArrayList<>(numOfChunks);

        for (int i = 0; i < numOfChunks; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, list.size());
            chunks.add(new ArrayList<>(list.subList(start, end)));
        }

        return chunks;
    }
}
