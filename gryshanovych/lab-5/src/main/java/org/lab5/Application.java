package org.lab5;

import org.lab5.app.ApplicationInterface;
import org.lab5.util.ArrayListSplitter;
import org.lab5.util.Pair;
import org.lab5.util.Timer;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Application implements ApplicationInterface {
    private Matrix matrix;
    private int rows, cols;

    private int threadCount;

    private Thread[] threads;
    CopyOnWriteArrayList<Pair<Long, Pair<Integer, Integer>>> results;

    Application(int rows, int cols, Long range, int threadCount) {
        this.rows = rows;
        this.cols = cols;
        this.threadCount = threadCount;

        this.matrix = new Matrix(rows, cols);
        matrix.fillRandom(range);

        this.threads = new Thread[threadCount];
        this.results = new CopyOnWriteArrayList<>();
    }

    @Override
    public void start() {
        MatrixSolver.minScalarProductPair(matrix, threadCount, 1);
    }

    public static void main(String[] args) {
        new Application(10000, 10000, 100L, 20).start();
    }

}
