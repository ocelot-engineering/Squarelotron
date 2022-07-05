import java.util.Arrays;

/**
 * A Squarelotron consists basically of a matrix of numbers. This matrix can be decomposed as square rings
 * which can flip independently in two different ways: Upside-Down and through the Main Diagonal.
 */
public class Squarelotron {

    int[][] grid;

    public Squarelotron(int n) {
        this.grid = new int[n][n]; // create empty grid

        // inefficient nested for loop to build squarelotron
        // fills from 1:n squared, in order.
        for (int row = 0; row < n; row++) { // row loop
            for (int col = 0; col < n; col++) { // col value loop
                this.grid[row][col] = (row * n) + col + 1;
            }
        }

    }

    public static void main(String[] args) {
        Squarelotron squarelotron = new Squarelotron(5);
        squarelotron.printSquarelotron(squarelotron.grid);
        squarelotron.printSquarelotron(squarelotron.upsideDownFlip(1));
        squarelotron.printSquarelotron(squarelotron.upsideDownFlip(2));
    }

    public int[][] upsideDownFlip(int ring) {
        // top and bottom row change position
        // all other rows have first and last value swapped
        // can always assume it is a square
        // easy to keep original grid for source of truth when updating output grid (outGrid)
        // todo better checks for possible operations

        // Setup indices and deep copy the grid
        int size = this.grid.length;
        int topRowIdx = ring - 1;
        int botRowIdx = size - ring;

        // Deep copy array - need to iterate over each row
        int[][] outGrid = new int[size][size];
        for (int row = 0; row < size; row++) {
            outGrid[row] = Arrays.copyOf(this.grid[row], size);
        }

        // Top row of ring
        for (int col = 0; col < size; col++) {
            if (col >= ring - 1 && col <= size - ring) { // if between these, update as it's running along top of ring
                outGrid[topRowIdx][col] = this.grid[botRowIdx][col];
            }
        }

        // Bottom row of ring
        for (int col = 0; col < size; col++) {
            if (col >= ring - 1 && col <= size - ring) { // if between these, update as it's running along bottem of ring
                outGrid[botRowIdx][col] = this.grid[topRowIdx][col];
            }
        }

        // Between rows in ring
        boolean colRingEdge;
        for (int row = ring; row < size - ring; row++) { // only loop through between ring edges
            for (int col = 0; col < size; col++) {
                colRingEdge = col == ring - 1 || col == size - ring; // checks if col index is on a ring edge
                if (colRingEdge) {
                    outGrid[row][col] = this.grid[size - row - 1][col];
                }
            }
        }

        return outGrid;
    }


    public int[][] mainDiagonalFlip(int ring) {
        return this.grid;
    }

    public int[][] rotateRight(int numberOfTurns) {
        return this.grid;
    }

    /*
    * Nice printing of squarelotron
    */
    /**
     * Nice printing of squarelotron in console
     */
    public void printSquarelotron(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            System.out.println();
            for (int col = 0; col < grid.length; col++) {
                System.out.print(padRight(Integer.toString(grid[row][col]), 3));
            }
        }
        System.out.println(); // print new line at end of grid printing
    }

    /**
     * Pad a string to the right with spaces
     */
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
