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

    /**
     * Ring definition:
     * If 4x4 Square looks like:
     * ....
     * ....
     * ....
     * ....
     *
     * then Ring 1 (shown in +):
     * ++++
     * +..+
     * +..+
     * ++++
     *
     * and Ring 2:
     * ....
     * .++.
     * .++.
     * ....
     */
    public static void main(String[] args) {
        Squarelotron squarelotron = new Squarelotron(5);
        squarelotron.printSquarelotron(squarelotron.grid);
//        squarelotron.printSquarelotron(squarelotron.upsideDownFlip(1));
//        squarelotron.printSquarelotron(squarelotron.upsideDownFlip(2));
        squarelotron.printSquarelotron(squarelotron.mainDiagonalFlip(1));
        squarelotron.printSquarelotron(squarelotron.mainDiagonalFlip(2));
    }

    /**
     * Generates a deep copy of the original grid when called.
     */
    public int[][] deepCopyGrid() {
        // Deep copy array - need to iterate over each row
        int size = this.grid.length;
        int[][] outGrid = new int[size][size];
        for (int row = 0; row < size; row++) {
            outGrid[row] = Arrays.copyOf(this.grid[row], size);
        }
        return outGrid;
    }

    /**
     * Updates the top and bottom edges of a ring for the upside down flip
     * Loops through all elements in row (defined by rowIdxToUpdateTo) and updates the values
     */
    public int[][] updateTopBotOfRingForUpsideDownFlip(int ring, int[][] outGrid, int rowIdxToUpdateTo, int rowIdxToUpdateFrom) {
        int size = this.grid.length;
        int topAndLeftIdx = ring - 1; // index is for top row of ring and left col of ring
        int botAndRightIdx = size - ring; // index if for bottom of ring and right side col of ring

        for (int col = 0; col < size; col++) {
            if (col >= topAndLeftIdx && col <= botAndRightIdx) { // if between these, update as it's running along top or bottom of ring
                outGrid[rowIdxToUpdateTo][col] = this.grid[rowIdxToUpdateFrom][col];
            }
        }
        return outGrid;
    }


    /**
     * Upside down flip of ring
     * flips a ring on the x-axis so top and bottom values are swapped
     * Axis shown below:
     * ...
     * ___
     * ...
     */
    public int[][] upsideDownFlip(int ring) {
        // top and bottom row change position
        // all other rows have first and last value swapped
        // can always assume it is a square
        // easy to keep original grid for source of truth when updating output grid (outGrid)
        // TODO better checks for possible operations

        // Setup indices and deep copy the grid
        int size = this.grid.length;
        int topRowIdx = ring - 1;
        int botRowIdx = size - ring;
        int[][] outGrid = deepCopyGrid();

        // Top row of ring
        outGrid = updateTopBotOfRingForUpsideDownFlip(ring, outGrid, topRowIdx, botRowIdx); // rowIdxToUpdateTo: topRowIdx, rowIdxToUpdateFrom: botRowIdx

        // Bottom row of ring
        outGrid = updateTopBotOfRingForUpsideDownFlip(ring, outGrid, botRowIdx, topRowIdx);  // rowIdxToUpdateTo: botRowIdx, rowIdxToUpdateFrom: topRowIdx

        // Between rows in ring
        boolean colRingEdge; // updated in each iteration of the inner loop to check if on a ring edge
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


    /**
     * Main diagonal flip of ring
     * this is basically swapping x and y coords for all values in the ring.
     * Axis is shown below:
     * \..
     * .\.
     * ..\
     */
    public int[][] mainDiagonalFlip(int ring) {
        // Setup indices and deep copy the grid
        int size = this.grid.length;
        int topAndLeftIdx = ring - 1; // index is for top row of ring and left col of ring
        int botAndRightIdx = size - ring; // index if for bottom of ring and right side col of ring
        int[][] outGrid = deepCopyGrid();

        // Just as easy to read these loops rather tha build a method than can be reused, which was done in upsideDownFlip.
        // It's easier to read, but more to maintain. Since this will never be worked on again after I submit the assignment
        // I've chosen to favour easier to read.

        // Top of ring
        for (int col = 0; col < size; col++) {
            if (col >= topAndLeftIdx && col <= botAndRightIdx) { // if between these, update as it's running along top of ring
                outGrid[topAndLeftIdx][col] = this.grid[col][topAndLeftIdx];
            }
        }

        // Bottom of ring
        for (int col = 0; col < size; col++) {
            if (col >= topAndLeftIdx && col <= botAndRightIdx) { // if between these, update as it's running along top of ring
                outGrid[botAndRightIdx][col] = this.grid[col][botAndRightIdx];
            }
        }

        // Between rows in ring
        boolean colRingEdge; // updated in each iteration of the inner loop to check if on a ring edge
        for (int row = ring; row < size - ring; row++) {
            for (int col = 0; col < size; col++) {
                colRingEdge = col == ring - 1 || col == size - ring; // checks if col index is on a ring edge
                if (colRingEdge) {
                    outGrid[row][col] = this.grid[col][row]; // notice the switch of row and col
                }
            }
        }
        return outGrid;
    }

    public int[][] rotateRight(int numberOfTurns) {
        return this.grid;
    }

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
