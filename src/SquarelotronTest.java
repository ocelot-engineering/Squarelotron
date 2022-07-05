import static org.junit.jupiter.api.Assertions.*;

class SquarelotronTest {
    Squarelotron squarelotron;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        squarelotron = new Squarelotron(5);
    }

    @org.junit.jupiter.api.Test
    void upsideDownFlip() {
        assertEquals(5, squarelotron.grid.length); // assumes 5x5 as below tests are setup for 5x5

        // Outer ring flip
        squarelotron.upsideDownFlip(1); // flip outer ring
        assertArrayEquals(squarelotron.grid[0], new int[]{21, 22, 23, 24, 25});  // outer ring flips, bottom row on top
        assertArrayEquals(squarelotron.grid[4], new int[]{1, 2, 3, 4, 5});  // outer ring flips, bottom row on top
        assertArrayEquals(squarelotron.grid[1], new int[]{16, 7, 8, 9, 21});  // inner ring doesn't flip, checking second row
        assertEquals(13, squarelotron.grid[2][2]); // middle doesn't move

        // Flip outer ring again, effectively resetting the grid
        squarelotron.upsideDownFlip(1); // flip outer ring back
        assertArrayEquals(squarelotron.grid[0], new int[]{1, 2, 3, 4, 5});  // resets top row to original
        assertArrayEquals(squarelotron.grid[4], new int[]{21, 22, 23, 24, 25});  // resets bottom row to original
        assertArrayEquals(squarelotron.grid[1], new int[]{6, 7, 8, 9, 10});  // checking second row to be original
        assertEquals(13, squarelotron.grid[2][2]); // middle doesn't move

        // Flip the second ring (starting from reset grid)
        squarelotron.upsideDownFlip(2); // flip second ring
        assertArrayEquals(squarelotron.grid[0], new int[]{1, 2, 3, 4, 5});  // top row should stay as original
        assertEquals(13, squarelotron.grid[2][2]); // middle doesn't move
        assertArrayEquals(squarelotron.grid[1], new int[]{6, 17, 18, 19, 10});  // checking second row had 2nd ring flipped
    }

    @org.junit.jupiter.api.Test
    void mainDiagonalFlip() {
        assertEquals(5, squarelotron.grid.length); // assumes 5x5 as below tests are setup for 5x5

        // Outer ring flip
        //squarelotron.upsideDownFlip(1); // flip outer ring
        fail("Not yet implemented");
    }

    @org.junit.jupiter.api.Test
    void rotateRight() {
        fail("Not yet implemented");
    }
}