import org.junit.BeforeClass;
import org.junit.Test;
import org.lab5.Matrix;
import org.lab5.MatrixSolver;

public class MatrixSolverTestForLargeMatrices {
    static Matrix matrix;

    @BeforeClass
    public static void preparation() {
        matrix = new Matrix(10000, 10000);
        matrix.fillRandom(100L);
    }

    @Test
    public void testOnOneThread() {
        MatrixSolver.minScalarProductPair(matrix, 1, 1);

        System.out.println();
    }

    @Test
    public void testOnTwoThread() {
        MatrixSolver.minScalarProductPair(matrix, 2, 1);

        System.out.println();
    }

    @Test
    public void testOnFourThread() {
        MatrixSolver.minScalarProductPair(matrix, 4, 1);

        System.out.println();
    }

    @Test
    public void testOnEightThread() {
        MatrixSolver.minScalarProductPair(matrix, 8, 1);

        System.out.println();
    }

    @Test
    public void testOnSixteenThread() {
        MatrixSolver.minScalarProductPair(matrix, 16, 1);

        System.out.println();
    }

    @Test
    public void testOnTwelveThread() {
        MatrixSolver.minScalarProductPair(matrix, 20, 1);

        System.out.println();
    }
}
