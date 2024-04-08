import org.lab5.Matrix;
import org.lab5.MatrixSolver;
import org.lab5.util.Pair;

import static org.junit.Assert.assertEquals;

public class MatrixSolverTestForSmallMatrices {

    @org.junit.Test
    public void testOnOneThread() {
        Long[][] field = {
                {8L, 5L, 1L, 7L, 10L},
                {1L, 6L, 8L, 5L, 3L},
                {10L, 2L, 2L, 4L, 8L},
                {6L, 2L, 7L, 4L, 6L},
                {3L, 10L, 1L, 7L, 4L},
        };

        Matrix matrix = new Matrix(field);

        Pair<Long, Pair<Integer, Integer>> expectedValue = new Pair<>(82L, new Pair<>(1, 2));

        Pair<Long, Pair<Integer, Integer>> observedValue =
                MatrixSolver.minScalarProductPair(matrix, 1, 2);

        System.out.println();

        assertEquals(expectedValue, observedValue);
    }

    @org.junit.Test
    public void testOnTwoThreads() {
        Long[][] field = {
                {8L, 5L, 1L, 7L, 10L},
                {1L, 6L, 8L, 5L, 3L},
                {10L, 2L, 2L, 4L, 8L},
                {6L, 2L, 7L, 4L, 6L},
                {3L, 10L, 1L, 7L, 4L},
        };

        Matrix matrix = new Matrix(field);

        Pair<Long, Pair<Integer, Integer>> expectedValue = new Pair<>(82L, new Pair<>(1, 2));

        Pair<Long, Pair<Integer, Integer>> observedValue =
                MatrixSolver.minScalarProductPair(matrix, 2, 2);

        System.out.println();

        assertEquals(expectedValue, observedValue);
    }

    @org.junit.Test
    public void testOnFourThreads() {
        Long[][] field = {
                {8L, 5L, 1L, 7L, 10L},
                {1L, 6L, 8L, 5L, 3L},
                {10L, 2L, 2L, 4L, 8L},
                {6L, 2L, 7L, 4L, 6L},
                {3L, 10L, 1L, 7L, 4L},
        };

        Matrix matrix = new Matrix(field);

        Pair<Long, Pair<Integer, Integer>> expectedValue = new Pair<>(82L, new Pair<>(1, 2));

        Pair<Long, Pair<Integer, Integer>> observedValue =
                MatrixSolver.minScalarProductPair(matrix, 4, 2);

        System.out.println();

        assertEquals(expectedValue, observedValue);
    }
}
