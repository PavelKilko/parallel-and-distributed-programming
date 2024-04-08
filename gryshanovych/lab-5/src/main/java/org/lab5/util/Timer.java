package org.lab5.util;

public class Timer {
    long start;
    long finish;

    public void start() {
        start = System.nanoTime();
        finish = start;
    }

    public void stop() {
        finish = System.nanoTime();
    }

    public void stopAndPrintTimeMillis(String label) {
        finish = System.nanoTime();
        System.out.println(label + ": " + showMillis() + " ms");
    }

    public long show() {
        return finish - start;
    }

    public double showMillis() {
        return (double) show() / 1000000;
    }

}
