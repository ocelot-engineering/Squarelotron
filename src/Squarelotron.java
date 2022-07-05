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
        squarelotron.printSquarelotron();
    }

    public int[][] getSquarelotronArray() {
        return this.grid;
    }

    public void upsideDownFlip(int ring) {
    }

    public void mainDiagonalFlip(int ring) {
    }

    public void rotateRight(int numberOfTurns) {
    }

    /*
    * Nice printing of squarelotron
    */
    /**
     * Nice printing of squarelotron in console
     */
    public void printSquarelotron() {
        for (int row = 0; row < this.grid.length; row++) {
            System.out.println();
            for (int col = 0; col < this.grid.length; col++) {
                System.out.print(padRight(Integer.toString(this.grid[row][col]), 3));
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
