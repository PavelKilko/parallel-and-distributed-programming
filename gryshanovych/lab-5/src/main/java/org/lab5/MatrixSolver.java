package org.lab5;

import org.lab5.util.ArrayListSplitter;
import org.lab5.util.Pair;
import org.lab5.util.Timer;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MatrixSolver {
    public static Pair<Long, Pair<Integer, Integer>> minScalarProductPair(
            Matrix matrix, int threadCount, int logLevel
    ) {
        Thread[] threads = new Thread[threadCount];
        CopyOnWriteArrayList<Pair<Long, Pair<Integer, Integer>>> results = new CopyOnWriteArrayList<>();
        Long minScalarProduct = Long.MAX_VALUE;
        Pair<Integer, Integer> minScalarProductPair = new Pair<>(-1, -1);
        Timer timer = new Timer();


        if (logLevel == 2) {
            System.out.println("Matrix (" + matrix.getRows() + " x " + matrix.getCols() + "):");
            System.out.println(matrix);
            System.out.println();
        }

        if (logLevel >= 1) {
            timer.start();
        }

        ArrayList<Pair<Integer, Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < matrix.getRows() - 1; i++) {
            for (int j = i + 1; j < matrix.getRows(); j++) {
                pairs.add(new Pair<>(i, j));
            }
        }

        if (logLevel >= 1) {
            timer.stopAndPrintTimeMillis("Pair creation duration (mono-thread)");
        }

        if (logLevel >= 1) {
            timer.start();
        }

        ArrayList<ArrayList<Pair<Integer, Integer>>> chunks =
                ArrayListSplitter.split(pairs, threadCount);

        if (logLevel >= 1) {
            timer.stopAndPrintTimeMillis("Chunks creation duration (mono-thread)");
        }

        if (logLevel >= 1) {
            timer.start();
        }

        class Worker implements Runnable {
            private final int num;
            private final ArrayList<Pair<Integer, Integer>> pairs;

            private Long minScalarProduct = Long.MAX_VALUE;

            private Pair<Integer, Integer> minScalarProductPair = new Pair<>(-1, -1);

            public Worker(ArrayList<Pair<Integer, Integer>> pairs, int num) {
                this.pairs = pairs;
                this.num = num;
            }

            @Override
            public void run() {
                if (logLevel >= 1) {
                    System.out.println("Thread-worker (" + num + "): " + "pair count := " + pairs.size());
                }

                for (Pair<Integer, Integer> pair : pairs) {
                    Long[] row1 = matrix.getRow(pair.key());
                    Long[] row2 = matrix.getRow(pair.value());

                    long scalarProduct = computeScalarProduct(row1, row2);

                    if (logLevel == 2) {
                        System.out.println("Thread-worker (" + num + "): [" + scalarProduct +
                                ", (" + pair.key() + ", " + pair.value() + ")]");
                    }

                    if (minScalarProduct > scalarProduct) {
                        minScalarProduct = scalarProduct;
                        minScalarProductPair = pair;
                    }
                }

                results.add(new Pair<>(minScalarProduct, minScalarProductPair));
            }

            private long computeScalarProduct(Long[] row1, Long[] row2) {
                long product = 0L;

                for (int i = 0; i < row1.length; i++) {
                    product += row1[i] * row2[i];
                }

                return product;
            }
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Worker(chunks.get(i), i));
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                System.out.println("Thread error");
            }
        }

        if (logLevel >= 1) {
            timer.stopAndPrintTimeMillis("Pair scalar product calculation (" + threadCount + "-thread)");
        }

        if (logLevel >= 1) {
            timer.start();
        }

        for (Pair<Long, Pair<Integer, Integer>> result : results) {
            if (result.key() < minScalarProduct) {
                minScalarProduct = result.key();
                minScalarProductPair = result.value();
            }
        }

        if (logLevel >= 1) {
            timer.stopAndPrintTimeMillis("Finding min scalar product pair (mono-thread)");
            System.out.println("Min scalar product := " + minScalarProduct);
            System.out.println("Pair of rows := " +
                    "(" + minScalarProductPair.key() + ", " + minScalarProductPair.value() + ")");
        }

        return new Pair<>(minScalarProduct, minScalarProductPair);
    }
}
