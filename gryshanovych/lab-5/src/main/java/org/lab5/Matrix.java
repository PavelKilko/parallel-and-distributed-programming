package org.lab5;

import java.util.Random;

public class Matrix {
    Long[][] field;

    private final int rows;
    private final int cols;

    public Matrix(Long[][] field) {
        this.field = field;
        this.rows = field.length;
        this.cols = field[0].length;
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        field = new Long[rows][cols];
    }

    public Long[] getRow(int row) {
        return field[row];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                stringBuilder.append(field[i][j]);

                if (j != cols - 1) {
                    stringBuilder.append(' ');
                }
            }

            if (i != rows - 1) {
                stringBuilder.append('\n');
            }
        }

        return stringBuilder.toString();
    }

    public void fillRandom(Long range) {
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                field[i][j] = random.nextLong() % range;
            }
        }
    }
}
