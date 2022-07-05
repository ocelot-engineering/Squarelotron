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
        Squarelotron squarelotron = new Squarelotron(4);
        squarelotron.printSquarelotron(squarelotron.grid);
    }

    public int[][] upsideDownFlip(int ring) {
        return this.grid;
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
    }

    /**
     * Pad a string to the right with spaces
     */
    public static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
